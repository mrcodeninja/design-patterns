package com.mrcodeninja.patterns.structural.adapter;

import java.io.IOException;
import java.util.Queue;

/**
 * Wrap a required interface (e.g. {@link java.util.Queue}) to make it compatible with your system's interfaces.
 *
 * @author mrcodeninja
 */
public class StringQueueAdapter implements Writer<String> {
    private final Queue<String> queue;

    public StringQueueAdapter(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void write(String data) throws IOException {
        boolean offerSuccessful;

        try {
            offerSuccessful = queue.offer(data);
        } catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
            throw new IOException("Failed to write the data.", e);
        }

        if (!offerSuccessful) {
            throw new IOException("Failed to write the data due to capacity restrictions.");
        }
    }
}
