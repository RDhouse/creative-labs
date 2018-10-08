package com.clabs.demos;

import com.clabs.engine.api.Game;
import com.clabs.engine.core.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class dedicated to starting the demo applications.
 *
 * When adding a new Demo application, update this class to include the demo.
 *
 * TODO: Clean up this starter application, or remove it completly.
 */
public class DemoStarter {

    private static final Logger LOGGER = LogManager.getLogger(DemoStarter.class);

    private static int[] demoIndexes = {1, 2, 3};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = null;
        int selection = 0;

        boolean found = false;
        do {
            printDemos();
            selection = scanner.nextInt();
            found = find(selection);
        } while(!found);


        switch(selection) {
            case 1:
                game = new Simple();
                break;
            case 2:
                game = new ColorChanger();
                break;
            case 3:
                game = new SimpleShaderDemo();
                break;
        }

        LOGGER.info("Starting Demo: " + selection);
        new Engine(game).start();


    }

    private static boolean find(int selection) {
        for (int index: demoIndexes) {
            if (selection == index) {
                return true;
            }
        }
        System.out.println("Did not find selection, try again...");
        LOGGER.info("Did not find Demo with selection: " + selection);
        return false;
    }

    private static void printDemos() {
        // Add your demo here.
        System.out.println("Please select a demo to run:");
        System.out.println("1: Simple");
        System.out.println("2: ColorChanger");
        System.out.println("3: SimpleShaderDemo");
    }
}
