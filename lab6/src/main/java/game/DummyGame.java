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

        // sphere
        int layers = 20;
        int circumferenceTiles = 20;

        float[] positions10 = new float[(layers + 1)* (circumferenceTiles + 1) * 3];
        // create the vertex attributes
        int index = 0;
        for (int il = 0; il <= layers; ++il)
        {
            float layer_rel = (float)il / (float)layers;
            float layer_ang = (float) ((1.0f - 2.0f * layer_rel) * Math.PI / 2.0f);
            float layer_sin = (float)Math.sin(layer_ang);
            float layer_cos = (float)Math.cos(layer_ang);
            for (int ic = 0; ic <= circumferenceTiles; ic++)
            {
                float circum_rel = (float)ic / (float)circumferenceTiles;
                float cricum_ang = (float) (circum_rel * 2.0f * Math.PI - Math.PI);
                float circum_sin = (float)Math.sin(cricum_ang);
                float circum_cos = (float)Math.cos(cricum_ang);

                positions10[index] = layer_cos * circum_cos; // x
                positions10[index + 1] = layer_cos * circum_sin; // y
                positions10[index + 2] = layer_sin; // z
                index += 3;
            }
        }

        // create the face indices
        index = 0;
        int[] positions11 = new int[layers * circumferenceTiles * 6];
        for (int il = 0; il < layers; ++il)
        {
            for (int ic = 0; ic < circumferenceTiles; ic++)
            {
                int i0 = il * (circumferenceTiles + 1) + ic;
                int i1 = i0 + 1;
                int i3 = i0 + circumferenceTiles + 1;
                int i2 = i3 + 1;

                positions11[index] = i0;
                positions11[index + 1] = i1;
                positions11[index + 2] = i2;
                positions11[index + 3] = i0;
                positions11[index + 4] = i2;
                positions11[index + 5] = i3;
                index += 6;
            }
        }

        //torus
        var rings = 20;
        var nsides = 20;
        var R = 1.0f;
        var r = 0.1f;
        index = 0;
        float[] torus = new float[(rings + 1)* (nsides + 1) * 3];
        float ringDelta = 2.0f * (float) Math.PI / rings;
        float sideDelta = 2.0f * (float) Math.PI / nsides;
        float theta = 0.0f, cosTheta = 1.0f, sinTheta = 0.0f;
        for (int i = rings; i >= 0; i--) {
            float theta1 = theta + ringDelta;
            float cosTheta1 = (float) Math.cos(theta1);
            float sinTheta1 = (float) Math.sin(theta1);
            float phi = 0.0f;
            for (int j = nsides; j >= 0; j--) {
                phi += sideDelta;
                float cosPhi = (float) Math.cos(phi);
                float sinPhi = (float) Math.sin(phi);
                float dist = R + r * cosPhi;
                torus[index] = cosTheta1 * dist; // x
                torus[index + 1] = -sinTheta1 * dist; // y
                torus[index + 2] = r * sinPhi; // z
                index += 3;
                //GL11.glNormal3f(cosTheta * cosPhi, -sinTheta * cosPhi, sinPhi);
            }
            theta = theta1;
        }

        System.out.println(positions10.length);
        System.out.println(torus.length);
        //System.out.println(Arrays.toString(torus));

        Mesh mesh = new Mesh(positions10, torus, new float[0], positions11, GL_TRIANGLES);
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
