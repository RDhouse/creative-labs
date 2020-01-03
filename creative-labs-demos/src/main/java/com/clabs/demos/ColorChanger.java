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
    private final float minRangeColor = 0f;
    private final float maxRangeColor = 1f;
    private final float colorStepValue = 0.01f;
    private boolean rChannelMovingUp = false;
    private boolean gChannelMovingUp = false;
    private boolean bChannelMovingUp = false;

    @Override
    public void init() throws EngineException {
        random = new Random();

        // Setting initial random starting value for color
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();

        // initial direction setup colorstepping
        rChannelMovingUp = random.nextBoolean();
        gChannelMovingUp = random.nextBoolean();
        bChannelMovingUp = random.nextBoolean();
    }

    @Override
    public void update() {
        if (Input.isKeyDown(GLFW_KEY_SPACE)) {
            updateColor();
        }
    }

    private void updateColor() {
        // updating R(ed) channel of color
        if (rChannelMovingUp) {
            r += colorStepValue;
            if (r >= maxRangeColor) {
                rChannelMovingUp = false;
            }
        } else {
            r -= colorStepValue;
            if (r <= minRangeColor) {
                rChannelMovingUp = true;
            }
        }

        // updating G(reen) channel of color
        if (gChannelMovingUp) {
            g += colorStepValue;
            if (g >= maxRangeColor) {
                gChannelMovingUp = false;
            }
        } else {
            g -= colorStepValue;
            if (g <= minRangeColor) {
                gChannelMovingUp = true;
            }
        }

        // updating B(lue) channel of color
        if (bChannelMovingUp) {
            b += colorStepValue;
            if (b >= maxRangeColor) {
                bChannelMovingUp = false;
            }
        } else {
            b -= colorStepValue;
            if (b <= minRangeColor) {
                bChannelMovingUp = true;
            }
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
