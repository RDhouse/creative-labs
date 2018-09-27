package com.clabs.engine.core;

/**
 * Created by rutgerd on 1-2-2018.
 */
public class EngineException extends Exception {

    /**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -1379585722771172014L;

	public EngineException() {}

    public EngineException(String message) {
        super(message);
    }

    public EngineException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
