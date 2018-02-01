package com.clabs.demos;

import com.clabs.engine.EngineException;
import com.clabs.engine.api.Game;
import com.clabs.engine.core.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Simple {

    private static final Logger LOGGER = LogManager.getLogger(Simple.class);

    public static void main(String[] args) {

        new Engine(new Game() {
            @Override
            public void init() throws EngineException {

            }

            @Override
            public void update() {

            }

            @Override
            public void render() {

            }

            @Override
            public void destroy() throws EngineException {

            }
        }).run();
    }
    
}
