package com.clabs.engine.core;

import com.clabs.engine.EngineException;
import com.clabs.engine.api.Game;

/**
 * Created by rutgerd on 1-2-2018.
 */
public class Engine implements Runnable {

    private Game game;
    private Thread gameThread;

    public Engine(Game game) {
        gameThread = new Thread();
        this.game = game;
    }

    public void run() {
        try {
            init();
            loop();
            System.out.println("run finished");
        } catch (EngineException ee) {
            ee.printStackTrace();
        }
    }

    private void init() throws EngineException {}
    private void loop() {}
}
