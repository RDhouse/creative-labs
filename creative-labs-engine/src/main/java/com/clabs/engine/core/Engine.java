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
    private Thread gameThread;

    public Engine(Game game) {

        LOGGER.trace("In constructor of: " + this.getClass().getName());

        gameThread = new Thread();
        this.game = game;
    }

    public void run() {
        try {
            LOGGER.trace("trace");
            LOGGER.debug("debug");
            LOGGER.info("info");
            LOGGER.error("error");
            LOGGER.fatal("fatal");
            init();
            loop();
            LOGGER.info("Run finished");
        } catch (EngineException ee) {
            ee.printStackTrace();
        }
    }

    private void init() throws EngineException {}
    private void loop() {}
}
