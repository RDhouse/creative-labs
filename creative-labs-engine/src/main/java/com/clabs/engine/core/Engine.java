package com.clabs.engine.core;

import com.clabs.engine.api.Game;
import com.clabs.engine.input.Input;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

/**
 * Created by rutgerd on 1-2-2018.
 */
public class Engine implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Engine.class);

    private Game game;
    private Window window;
    private Thread gameThread;
    
    private boolean running = false;
    
    private double tickRate = 1_000_000_000.0 / 60.0;

    public Engine(Game game) {
        gameThread = new Thread(this, "GAME_THREAD");
        this.game = game;
    }

    public void run() {
        try {
            init();
            loop();
        } catch (EngineException ee) {
            LOGGER.error(ee.getMessage(), ee);
        } finally {
        	try {
        		game.destroy();
        		window.destroy();
        	} catch (EngineException ee) {
        		LOGGER.error(ee.getMessage(), ee);
        	}
        	LOGGER.info("succesfully stopped the engine");
        }
    }

    private void init() throws EngineException {
    	// initialize window
        this.window = new Window(800, 600, "Some Game", true);
        window.init();
        
        // initialize input
        GLFW.glfwSetKeyCallback(window.getID(), new Input());
        
        // initialize game
        game.init();
    }

    private void loop() {
    	long lastTime = System.nanoTime();
		float delta = 0.0f;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / tickRate;
			lastTime = now;

			if (delta >= 1.0) {
				update(delta);
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				LOGGER.debug(String.format("%d ups, %d fps", updates, frames));
				updates = 0;
				frames = 0;
			}

		}
    }

    public void start() {
    	running = true;
        gameThread.start();
    }
    
    public void update(float delta) {
    	// handle input
    	if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
    		GLFW.glfwSetWindowShouldClose(window.getID(), true);
    	}
    	if (GLFW.glfwWindowShouldClose(window.getID())) {
    		running = false;
    	}
    	
    	// update game
    	game.update();
    }
    
    public void render() {
    	window.update();
    	game.render();
    }

    public void stop() {
        try {
        	running = false;
            gameThread.join();
        } catch (InterruptedException ie) {
            LOGGER.error(ie.getMessage(), ie);
        }
    }
   
}
