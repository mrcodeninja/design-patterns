package com.mrcodeninja.patterns.behavioral.observer;

/**
 * @author mrcodeninja
 */
public interface Observer<T> {
    void update(T message);
}
