package com.clabs.demos;

import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.opengl.GL;

import com.clabs.engine.EngineException;
import com.clabs.engine.api.Game;
import com.clabs.engine.core.Engine;

public class Simple {

    public static void main(String[] args) {

        new Engine(new Game() {
            @Override
            public void init() throws EngineException {
            	GL.createCapabilities();
            	
            	glClearColor(1.0f, 0f, 0f, 0f);
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
        }).start();
    }
    
}
