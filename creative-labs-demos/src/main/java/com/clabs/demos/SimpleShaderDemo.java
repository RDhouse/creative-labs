package com.clabs.demos;

import com.clabs.engine.api.Game;
import com.clabs.engine.core.EngineException;
import com.clabs.engine.shader.ShaderProgram;
import com.clabs.engine.util.FileUtils;

public class SimpleShaderDemo implements Game {

    private ShaderProgram shaderProgram;

    @Override
    public void init() throws EngineException {
        // create shader program.
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(FileUtils.loadResource(this.getClass().getResource("simple_shader_demo_vertex.glsl").toString()));
        shaderProgram.createFragmentShader(FileUtils.loadResource(this.getClass().getResource("simple_shader_demo_fragment.glsl").toString()));
        shaderProgram.link();

        // create vertices
        float[] vertices = new float[] {
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        shaderProgram.bind();
        // TODO:
        shaderProgram.unbind();
    }

    @Override
    public void destroy() throws EngineException {
        if (shaderProgram != null) {
            shaderProgram.destroy();
        }
    }
}
