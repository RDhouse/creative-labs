package com.clabs.demos.shaderdemo;

import com.clabs.engine.api.Game;
import com.clabs.engine.core.Engine;
import com.clabs.engine.core.EngineException;
import com.clabs.engine.shader.ShaderProgram;
import com.clabs.engine.util.FileUtils;

import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

/**
 * A VBO is just a memory buffer stored in the graphics card memory that stores vertices. 
 * This is where we will transfer our array of floats that model a triangle. 
 * As we said before, OpenGL does not know anything about our data structure. 
 * In fact it can hold not just coordinates but other information, such as textures, colour, etc.
 * A Vertex Array Objects (VAOs) is an object that contains one or more VBOs which are usually called attribute lists. 
 * Each attribute list can hold one type of data: position, colour, texture, etc. You are free to store whichever you want in each slot.
 * A VAO is like a wrapper that groups a set of definitions for the data that is going to be stored in the graphics card. 
 * When we create a VAO we get an identifier. 
 * We use that identifier to render it and the elements it contains using the definitions we specified during its creation.
 */
public class ShaderDemo implements Game {

    private ShaderProgram shaderProgram;
    
    private int vaoId;
    private int vboId;

    @Override
    public void init() throws EngineException {
        // create shader program.
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(FileUtils.loadResource(this.getClass().getResource("shader_demo_vertex.glsl").getPath()));
        shaderProgram.createFragmentShader(FileUtils.loadResource(this.getClass().getResource("shader_demo_fragment.glsl").getPath()));
        shaderProgram.link();

        // create vertices for a Triangle
        float[] vertices = new float[] {
            0.0f,  0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
        };

        // Create buffer in off heap memory so opengl can access it, however this is unmanaged, so clean up afterwards
        FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
        verticesBuffer.put(vertices).flip();

        // create the vertex array object
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // create the vertex buffer object, storing our vertices
        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

        // Enable location 0
        glEnableVertexAttribArray(0);

        // Define the structure of the data
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        // We can unbind the VBO and VAO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        // clean off heap memory
        if (verticesBuffer != null) {
            MemoryUtil.memFree(verticesBuffer);
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        shaderProgram.bind();

        // bind the VAO
        glBindVertexArray(vaoId);

        // Draw the vertices
        glDrawArrays(GL_TRIANGLES, 0, 3);

        // Unbind the VAO
        glBindVertexArray(0);

        // Unbind the shader program
        shaderProgram.unbind();

    }

    @Override
    public void destroy() throws EngineException {
        if (shaderProgram != null) {
            shaderProgram.destroy();
        }

        glDisableVertexAttribArray(0);

        // Delete the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);

    }

    public static void main(String[] args) {
        new Engine(new ShaderDemo()).start();
    }
}
