package com.clabs.engine.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShaderUtils {

    private static final Logger LOGGER = LogManager.getLogger(ShaderUtils.class);

    private ShaderUtils() {}

    public static String loadResource(String resourceLocation) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(resourceLocation))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException fnfe) {
            LOGGER.error(fnfe);
        } catch (IOException ioe) {
            LOGGER.error(ioe);
        }
        return sb.toString();
    }
}
