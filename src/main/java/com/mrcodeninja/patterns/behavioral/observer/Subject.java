package com.mrcodeninja.patterns.behavioral.observer;

/**
 * @author mrcodeninja
 */
public interface Subject<T> {
    void attach(Observer<T> observer);
    void detach(Observer<T> observer);
    void notifyObservers(T message);
}
