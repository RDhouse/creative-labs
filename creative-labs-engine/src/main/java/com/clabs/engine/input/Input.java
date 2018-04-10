package com.clabs.engine.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {
	
	private static boolean[] keys = new boolean[(int) Math.pow(2, 16)];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action != GLFW.GLFW_RELEASE;
	}
	
	public static boolean isKeyDown(int keyCode) {
		return keys[keyCode];
	}
	
}
