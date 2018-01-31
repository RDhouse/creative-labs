package com.clabs.demos;

import com.clabs.engine.EngineException;
import com.clabs.engine.api.Game;
import com.clabs.engine.core.Engine;

public class Simple {

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
