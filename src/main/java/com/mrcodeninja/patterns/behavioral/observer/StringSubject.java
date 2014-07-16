package com.mrcodeninja.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mrcodeninja
 */
public class StringSubject implements Subject<String> {
    // In production code, you probably want to inject this.
    private List<Observer<String>> observers = new ArrayList<>();

    @Override
    public void attach(Observer<String> observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer<String> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.update(message));
    }
}
