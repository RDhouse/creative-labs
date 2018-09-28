package com.clabs.engine.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {

    private BufferUtils() {}

    public static FloatBuffer createFloatBuffer(float[] src) {
        FloatBuffer result = ByteBuffer.allocate(src.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        result.put(src).flip();
        return result;
    }

    public static IntBuffer createIntBuffer(int[] src) {
        IntBuffer result = ByteBuffer.allocate(src.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        result.put(src).flip();
        return result;
    }
}
