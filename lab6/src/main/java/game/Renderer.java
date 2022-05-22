package game;

import engine.GameItem;
import engine.Utils;
import engine.Window;
import engine.graph.Camera;
import engine.graph.ShaderProgram;
import engine.graph.Transformation;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    /**
     * Field of View in Radians
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private static final int SLEEP_TIME = 1000;

    private ShaderProgram shaderProgram;

    private Transformation transformation;

    private float progress = 0;

    private float step = 0.005f;

    public Renderer() {
        transformation = new Transformation();
    }

    public void init(Window window) throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/vertex.glsl"));
        shaderProgram.link();

        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelMatrix");
        shaderProgram.createUniform("viewMatrix");
        shaderProgram.createUniform("progress");

        window.setClearColor(1, 1, 1, 1);
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, Camera camera, GameItem[] gameItems) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }
        shaderProgram.bind();

        // Update projection Matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        countProgress();
        shaderProgram.setUniform("progress", progress);

        // Render each gameItem
        for (GameItem gameItem : gameItems) {
            Matrix4f modelMatrix = transformation.getModelMatrix(gameItem);
            shaderProgram.setUniform("modelMatrix", modelMatrix);
            Matrix4f viewMatrix = transformation.getViewMatrix(camera);
            shaderProgram.setUniform("viewMatrix", viewMatrix);
            // Render the mes for this game item
            gameItem.getMesh().render();
        }

        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void countProgress() {
        if (progress <= 0) {
            sleep();
            step = 0.005f;
        }

        if (progress >= 1) {
            sleep();
            step = -0.005f;
        }

        progress += step;
    }
}
