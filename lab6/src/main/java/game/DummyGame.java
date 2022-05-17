package game;

import engine.GameItem;
import engine.IGameLogic;
import engine.MouseInput;
import engine.Window;
import engine.graph.Camera;
import engine.graph.Mesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;

public class DummyGame implements IGameLogic {

    private final Renderer renderer;

    private GameItem[] gameItems;

    private final Camera camera;


    private static final float CAMERA_POS_STEP = 3f;

    private static final float MOUSE_SENSITIVITY = 0.2f;

    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        camera.setPosition(0, 0, CAMERA_POS_STEP);
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
        float[] positions2 = positions.clone();
        for (int i = 0; i < positions2.length; i++) {
            positions2[i] = positions2[i] * 2;
        }

        Mesh mesh = new Mesh(positions, positions2, colors, indices, GL_TRIANGLES);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(0, 0, 0);
        gameItems = new GameItem[] { gameItem };
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {

        // Update camera based on mouse
        if (mouseInput.isLeftButtonPressed()) {
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
