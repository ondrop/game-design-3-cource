package game;

import engine.GameItem;
import engine.IGameLogic;
import engine.MouseInput;
import engine.Window;
import engine.graph.Camera;
import engine.graph.Mesh;
import org.joml.Quaterniond;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;

public class DummyGame implements IGameLogic {

    private final Renderer renderer;

    private GameItem[] gameItems;

    private final Camera camera;

    private final Vector3f cameraInc;

    private static final float CAMERA_POS_STEP = 3f;

    private static final float MOUSE_SENSITIVITY = 0.2f;

    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        camera.setPosition(0, 0, CAMERA_POS_STEP);
        cameraInc = new Vector3f();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        float[] positions = new float[]{
                //front
                -0.5f,  0.5f,  0.5f,
                -0.5f, -0.5f,  0.5f,
                0.5f, -0.5f,  0.5f,
                0.5f,  0.5f,  0.5f,
                //back
                0.5f,  0.5f, -0.5f,
                -0.5f,  0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                // left
                -0.5f, 0.5f,  -0.5f,
                -0.5f,  0.5f,  0.5f,
                -0.5f, -0.5f,  0.5f,
                -0.5f,  -0.5f,  -0.5f,
                // right
                0.5f, 0.5f,  -0.5f,
                0.5f,  0.5f,  0.5f,
                0.5f, -0.5f,  0.5f,
                0.5f,  -0.5f,  -0.5f,
                // bottom
                0.5f, -0.5f,  -0.5f,
                0.5f,  -0.5f,  0.5f,
                -0.5f, -0.5f,  0.5f,
                -0.5f,  -0.5f,  -0.5f,
                // top
                0.5f, 0.5f,  -0.5f,
                0.5f,  0.5f,  0.5f,
                -0.5f, 0.5f,  0.5f,
                -0.5f,  0.5f,  -0.5f,
        };
        float[] colors = new float[]{
                0.5f, 0.0f, 0.0f, 0.5f,
                0.5f, 0.0f, 0.0f, 0.5f,
                0.5f, 0.0f, 0.0f, 0.5f,
                0.5f, 0.0f, 0.0f, 0.5f,
                0.0f, 0.0f, 0.5f, 0.5f,
                0.0f, 0.0f, 0.5f, 0.5f,
                0.0f, 0.0f, 0.5f, 0.5f,
                0.0f, 0.0f, 0.5f, 0.5f,
                0.0f, 0.5f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.0f, 0.5f,
                1.0f, 0.0f, 0.0f, 0.5f,
                1.0f, 0.0f, 0.0f, 0.5f,
                1.0f, 0.0f, 0.0f, 0.5f,
                1.0f, 0.0f, 0.0f, 0.5f,
                0.5f, 0.5f, 0.0f, 0.5f,
                0.5f, 0.5f, 0.0f, 0.5f,
                0.5f, 0.5f, 0.0f, 0.5f,
                0.5f, 0.5f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f, 0.5f,
                0.0f, 0.5f, 0.5f, 0.5f,
                0.0f, 0.5f, 0.5f, 0.5f,
                0.0f, 0.5f, 0.5f, 0.5f
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
                4, 5, 7, 7, 5, 6,
                8, 9, 11, 11, 9, 10,
                12, 13, 15, 15, 13, 14,
                16, 17, 19, 19, 17, 18,
                20, 21, 23, 23, 21, 22,
        };
        Mesh mesh = new Mesh(positions, colors, indices);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(0, 0, 0);
        gameItems = new GameItem[] { gameItem };
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {

        // Update camera based on mouse
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec().mul(MOUSE_SENSITIVITY);

            camera.moveRotation(
                    rotVec.x,
                    rotVec.y,
                    0
            );

            Vector3f rotation = camera.getRotation();
            float square = (float) (Math.cos(Math.toRadians(rotation.x)) * CAMERA_POS_STEP);
            // Update camera position
            camera.setPosition(
                    (float) Math.sin(Math.toRadians(rotation.y)) * -square,
                    (float) Math.sin(Math.toRadians(rotation.x)) * CAMERA_POS_STEP,
                    (float) Math.cos(Math.toRadians(-rotation.y)) * square
            );
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}
