package com.clabs.engine.shader;

import com.clabs.engine.core.EngineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * Class that encapsulates the creation of openGL shaders.
 *
 */
public class ShaderProgram {

    private static final Logger LOGGER = LogManager.getLogger(ShaderProgram.class);

    private int programID;
    private int vsID;
    private int fsID;

    private Map<String, Integer> uniforms;

    public ShaderProgram() throws EngineException {
        programID = glCreateProgram();
        if (programID == 0) {
            throw new EngineException("Could not create shader program");
        }
        uniforms = new HashMap<>();
    }

    public void createVertexShader(String shaderCode) throws EngineException {
        vsID = createShader(shaderCode, GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderCode) throws EngineException {
        fsID = createShader(shaderCode, GL_FRAGMENT_SHADER);
    }

    private int createShader(String shaderCode, int shaderType) throws EngineException {
        int shaderID = glCreateShader(shaderType);
        if (shaderID == 0) {
            throw new EngineException("Could not create shader");
        }

        glShaderSource(shaderID, shaderCode);
        glCompileShader(shaderID);

        // check status
        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == 0 ) {
            throw new EngineException("Could not compile shader code: " + glGetShaderInfoLog(shaderID, 1024));
        }

        glAttachShader(programID, shaderID);
        
        return shaderID;
    }

    public void link() throws EngineException {
        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == 0) {
            throw new EngineException("Could not link program: " + glGetShaderInfoLog(programID, 1024));
        }
        glValidateProgram(programID);
        if (glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) {
            throw new EngineException("Could not validate program: " + glGetShaderInfoLog(programID, 1024));
        }
    }

    public void bind() {
        glUseProgram(programID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void destroy() {
        unbind();
        if (programID != 0) {
            if (vsID != 0) {
                glDetachShader(programID, vsID);
            }
            if (fsID != 0) {
                glDetachShader(programID, fsID);
            }
            glDeleteProgram(programID);
        }
    }

    public void createUniform(String uniformName) throws EngineException {
        int uniformLocation = glGetUniformLocation(programID, uniformName);
        if (uniformLocation < 0) {
            throw new EngineException("Could not find uniform: " + uniformName);
        }
        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, Vector3f value) {
        glUniform3f(uniforms.get(uniformName), value.x, value.y, value.z);
    }
}
