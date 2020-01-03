package com.clabs.demos.shaderdemo;

import com.clabs.engine.api.Game;
import com.clabs.engine.core.Engine;
import com.clabs.engine.core.EngineException;
import com.clabs.engine.shader.ShaderProgram;
import com.clabs.engine.util.FileUtils;

public class ShaderDemo implements Game {

    private ShaderProgram shaderProgram;

    @Override
    public void init() throws EngineException {
        // create shader program.
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(FileUtils.loadResource(this.getClass().getResource("shader_demo_vertex.glsl").getPath()));
        shaderProgram.createFragmentShader(FileUtils.loadResource(this.getClass().getResource("shader_demo_fragment.glsl").getPath()));
        shaderProgram.link();

        // // create vertices
        // float[] vertices = new float[] {
        //         -0.5f, 0.5f, 0.5f,
        //         -0.5f, -0.5f, 0.5f,
        //         0.5f, -0.5f, 0.5f,
        //         0.5f, 0.5f, 0.5f
        // };
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        shaderProgram.bind();
        shaderProgram.unbind();
    }

    @Override
    public void destroy() throws EngineException {
        if (shaderProgram != null) {
            shaderProgram.destroy();
        }
    }

    public static void main(String[] args) {
        new Engine(new ShaderDemo()).start();
    }
}
