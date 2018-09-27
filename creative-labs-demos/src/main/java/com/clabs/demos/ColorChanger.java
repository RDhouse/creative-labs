package com.clabs.demos;

import com.clabs.engine.api.Game;
import com.clabs.engine.core.Engine;
import com.clabs.engine.core.EngineException;
import com.clabs.engine.input.Input;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.opengl.GL11.glClearColor;

public class ColorChanger implements Game {

    private Random random;
    private float r, g, b;

    @Override
    public void init() throws EngineException {
        random = new Random();
    }

    @Override
    public void update() {
        if (Input.isKeyDown(GLFW_KEY_SPACE)) {
            r = random.nextFloat();
            g = random.nextFloat();
            b = random.nextFloat();
        }
    }

    @Override
    public void render() {
        glClearColor(r, g, b, 1.0f);
    }

    @Override
    public void destroy() throws EngineException {

    }

    public static void main(String[] args) {
        new Engine(new ColorChanger()).start();
    }
}
