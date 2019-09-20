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

import java.util.Objects;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * An attempt of a call, which resulted either in a result returned by the call, or in a Throwable thrown by the call.
 * <p>
 * </p>
 * not thread-safe
 *
 * @param <V> The type returned by the wrapped callable.
 * @author xiangji
 */
@NotThreadSafe
public class Attempt<V> {

    /**
     * last result
     */
    private V result;

    /**
     * base zero
     */
    private int attemptNumber = 0;

    /**
     * exception
     */
    private Throwable exceptionCause;

    /**
     * need retry, default is true
     */
    private Boolean needRetry = true;

    /**
     * next trigger time.
     */
    private Long nextTriggerTimeMillis;

    public Attempt() {
    }

    public void incrAttemptNumber() {
        this.attemptNumber++;
    }

    public V getResult() {
        return result;
    }

    public Attempt<V> withResult(V result) {
        this.result = result;
        return this;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public Throwable getExceptionCause() {
        return exceptionCause;
    }

    public Attempt<V> withExceptionCause(Throwable exceptionCause) {
        this.exceptionCause = exceptionCause;
        return this;
    }

    public Boolean getNeedRetry() {
        return needRetry;
    }

    public Attempt<V> withNeedRetry(Boolean needRetry) {
        this.needRetry = needRetry;
        return this;
    }

    public Long getNextTriggerTimeMillis() {
        return nextTriggerTimeMillis;
    }

    public Attempt<V> withNextTriggerTimeMillis(Long nextTriggerTimeMillis) {
        this.nextTriggerTimeMillis = nextTriggerTimeMillis;
        return this;
    }

    public boolean hasResult() {
        return !Objects.equals(null, result);
    }

    public boolean hasException() {
        return !Objects.equals(null, exceptionCause);
    }
}
