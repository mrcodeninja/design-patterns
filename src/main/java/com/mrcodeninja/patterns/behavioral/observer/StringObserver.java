package com.mrcodeninja.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mrcodeninja
 */
public class StringObserver implements Observer<String> {
    private Logger logger = LoggerFactory.getLogger(StringObserver.class);

    @Override
    public void update(String message) {
        logger.debug("Received message: {}", message);
    }
}
