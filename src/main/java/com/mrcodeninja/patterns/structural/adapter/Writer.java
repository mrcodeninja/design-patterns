package com.mrcodeninja.patterns.structural.adapter;

import java.io.IOException;

/**
 * Apply the Single Responsibility Principle
 * Not to be confused with {@link java.io.Writer}.
 *
 * @author mrcodeninja
 */
public interface Writer<T> {
    void write(T data) throws IOException;
}
