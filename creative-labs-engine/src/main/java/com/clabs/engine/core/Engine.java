package com.clabs.engine.core;

import com.clabs.engine.EngineException;
import com.clabs.engine.api.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rutgerd on 1-2-2018.
 */
public class Engine implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Engine.class);

    private Game game;
    private Window window;
    private Thread gameThread;

    public Engine(Game game) {
        gameThread = new Thread(this);
        this.game = game;
    }

    public void run() {
        try {
            init();
            loop();
        } catch (EngineException ee) {
            LOGGER.error(ee.getMessage(), ee);
        }
    }

    private void init() throws EngineException {
        this.window = new Window(800, 600);
        window.init();

        game.init();
    }

    private void loop() {
        // update game
        game.update();
        game.render();
    }

    public void start() {
        gameThread.start();
    }

    public void stop() {
        try {
            gameThread.join();
        } catch (InterruptedException ie) {
            LOGGER.error(ie.getMessage(), ie);
        }
    }
}
