package com.clabs.engine.api;

import com.clabs.engine.core.EngineException;

/**
 * Created by rutgerd on 1-2-2018.
 */
public interface Game {

    void init() throws EngineException;

    void update();

    void render();

    void destroy() throws EngineException;

}