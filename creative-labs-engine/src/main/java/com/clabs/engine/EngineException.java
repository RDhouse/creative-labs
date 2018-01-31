package com.clabs.engine;

/**
 * Created by rutgerd on 1-2-2018.
 */
public class EngineException extends Exception {

    public EngineException() {}

    public EngineException(String message) {
        super(message);
    }

    public EngineException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
