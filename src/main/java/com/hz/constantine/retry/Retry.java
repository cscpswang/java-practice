/*
 * Copyright 2012-2015 Ray Holder
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hz.constantine.retry;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * A retry, which executes a call, and retries it until it succeeds, or a stop strategy decides to stop retrying. A wait
 * strategy is used to sleep between attempts. The strategy to decide if the call succeeds or not is also configurable.
 * <p>
 * </p>
 * Retry instances are better constructed with a {@link RetryBuilder}. A retry is thread-safe, provided the arguments
 * passed to its constructor are thread-safe.
 *
 * @param <V> the type of the call return value
 * @author xiangji
 */
public final class Retry<V> {
    private final Predicate<Attempt<V>> rejectionPredicate;

    private final int stopAfterAttemptNums;

    private final long baseWaitMillisecond;

    private final Attempt<V> attempt;

    private final Callable<V> callable;

    Attempt getAttempt() {
        return attempt;
    }

    Callable<V> getCallable() {
        return callable;
    }

    /**
     * Constructor
     *
     * @param rejectionPredicate the predicate used to decide if the attempt must be rejected or not. If an attempt is
     *            rejected, the retry will retry the call, unless the stop strategy indicates otherwise or the thread is
     *            interrupted.
     * @param stopAfterAttemptNums indicate stop after how mach times.
     * @param baseWaitTime eventually wait time : baseWaitTime * fibonacci
     * @param baseWaitTimeUnit eventually wait time unit;
     */
    public Retry(@Nonnull Predicate<Attempt<V>> rejectionPredicate, int stopAfterAttemptNums, int baseWaitTime,
            @Nonnull TimeUnit baseWaitTimeUnit, @Nonnull Callable<V> callable) {
        Preconditions.checkNotNull(rejectionPredicate, "rejectionPredicate may not be null");
        Preconditions.checkArgument(stopAfterAttemptNums > 0, "stopAfterAttemptNums may not be positive");
        Preconditions.checkArgument(baseWaitTime > 0, "baseWaitTime may not be positive");
        Preconditions.checkNotNull(baseWaitTimeUnit, "baseWaitTimeUnit may not be null");

        this.rejectionPredicate = rejectionPredicate;
        this.stopAfterAttemptNums = stopAfterAttemptNums;
        this.baseWaitMillisecond = baseWaitTimeUnit.toMillis(baseWaitTime);
        this.attempt = new Attempt<V>().withNextTriggerTimeMillis(System.currentTimeMillis());
        this.callable = callable;
    }

    /**
     * Executes the given callable. If the rejection predicate accepts the attempt, the stop strategy is used to decide
     * if a new attempt must be made. Then the wait strategy is used to decide how much time to sleep and a new attempt
     * is made.
     *
     * @return next trigger time
     */
    public Attempt<V> execute() {
        attempt.incrAttemptNumber();
        try {
            attempt.withResult(callable.call());
        } catch (Throwable t) {
            attempt.withExceptionCause(t);
        }

        if (!rejectionPredicate.apply(attempt)) {
            return attempt.withNeedRetry(false);
        }

        if (attempt.getAttemptNumber() >= stopAfterAttemptNums) {
            return attempt.withNeedRetry(false);
        }

        return attempt.withNeedRetry(true)
                .withNextTriggerTimeMillis(System.currentTimeMillis() + computeNextTriggerTimeWithFibonacci(attempt));
    }

    private long computeNextTriggerTimeWithFibonacci(Attempt failedAttempt) {
        long result = baseWaitMillisecond * fibonacci(failedAttempt.getAttemptNumber());
        return result >= 0L ? result : 0L;
    }

    private long fibonacci(long n) {
        // make fibonacci start at index:1
        n = n + 1;

        if (n == 0L) {
            return 0L;
        }
        if (n == 1L) {
            return 1L;
        }

        long prevPrev = 0L;
        long prev = 1L;
        long result = 0L;

        for (long i = 2L; i <= n; i++) {
            result = prev + prevPrev;
            prevPrev = prev;
            prev = result;
        }

        return result;
    }
}
