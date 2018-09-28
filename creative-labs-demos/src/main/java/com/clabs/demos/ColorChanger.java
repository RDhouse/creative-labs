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

    private boolean rUp, gUp, bUp;

    @Override
    public void init() throws EngineException {
        random = new Random();
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
    }

    @Override
    public void update() {
        if (Input.isKeyDown(GLFW_KEY_SPACE)) {
            // Red channel
            if (r <= 0) {
                rUp = true;
            }
            if (r >= 1.0) {
                rUp = false;
            }
            r = updateColor(r, rUp);

            // Green channel
            if (g <= 0) {
                gUp = true;
            }
            if (g >= 1.0) {
                gUp = false;
            }
            g = updateColor(g, gUp);

            // Blue channel
            if (b <= 0) {
                bUp = true;
            }
            if (b >= 1.0) {
                bUp = false;
            }
            b = updateColor(b, bUp);
        }
    }

    private float updateColor(float color, boolean up) {
        float colorStep = 1.0f / 60.0f;

        if (up) {
            color += colorStep;
        } else {
            color -= colorStep;
        }

        return color;
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
