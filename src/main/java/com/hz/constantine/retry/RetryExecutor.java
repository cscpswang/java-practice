package com.hz.constantine.retry;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Retry executor, include a priorityQueue which sort by NextTriggerTime of {@link Retry}.
 * <p>
 * </p>
 * a thread use to execute retry task
 *
 * @author xiangji
 */
public final class RetryExecutor {

    /**
     * is singleton
     */
    private static RetryExecutor retryExecutor;

    /**
     * a priorityQueue which sort by NextTriggerTime of {@link Retry}
     */
    private PriorityBlockingQueue<Retry> priorityBlockingQueue;

    /**
     * use for notify add retry task
     */
    private final Object lock = new Object();

    /**
     * is start
     */
    private boolean isStart = false;

    private RetryExecutor() {
        int queueCapacity = 1000;
        priorityBlockingQueue = new PriorityBlockingQueue<>(queueCapacity, new Comparator<Retry>() {
            @Override
            public int compare(Retry o1, Retry o2) {
                return o1.getAttempt().getNextTriggerTimeMillis().compareTo(o2.getAttempt().getNextTriggerTimeMillis());
            }
        });
    }

    /**
     * @return a singleton instance
     */
    public static RetryExecutor executor() {
        if (Objects.equals(null, retryExecutor)) {
            synchronized (RetryExecutor.class) {
                if (Objects.equals(null, retryExecutor)) {
                    retryExecutor = new RetryExecutor();
                }
            }
        }
        return retryExecutor;
    }

    /**
     * add task, will add to a priority queue.
     * 
     * @param retry retryTask
     * @param <V> result of a {@link Retry}'s call, the type of the call return value
     */
    synchronized public <V> void addRetryTask(Retry<V> retry) {
        if (!isStart) {
            start();
        }
        priorityBlockingQueue.put(retry);
        synchronized (lock) {
            lock.notify();
        }
    }

    /**
     * start thread.
     */
    @SuppressWarnings("all")
    private void start() {
        final String threadName = "LightWeightRetry";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Retry currentRetry;
                        while (nextTriggerTimeInterval(currentRetry = priorityBlockingQueue.take()) <= 0) {
                            Attempt executeResult = currentRetry.execute();
                            if (executeResult.getNeedRetry()) {
                                priorityBlockingQueue.put(currentRetry);
                            }
                        }
                        // is not the time to exeuct currentRetry ,rejoin to queue.
                        priorityBlockingQueue.put(currentRetry);

                        // wait for next trigger time
                        synchronized (lock) {
                            lock.wait(nextTriggerTimeInterval(priorityBlockingQueue.peek()));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();/* can't be interrupt */
                    }
                }
            }
        }, threadName);
        thread.start();
        isStart = true;
    }

    private long nextTriggerTimeInterval(Retry retry) {
        return retry.getAttempt().getNextTriggerTimeMillis() - System.currentTimeMillis()-1;
    }

}