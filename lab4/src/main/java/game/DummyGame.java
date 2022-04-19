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

        gameItems = new GameItem[3];

        float[] linePositions = new float[]{
                //front
                -0.5f,  0.5f,  1f,
                -0.5f, -0.5f,  1f,
                0.5f, -0.5f,  1f,
                0.5f,  0.5f,  1f,
                //back
                0.5f,  0.5f, -1f,
                -0.5f,  0.5f, -1f,
                -0.5f, -0.5f, -1f,
                0.5f, -0.5f, -1f,
                // left
                -1f, 0.5f,  -0.5f,
                -1f,  0.5f,  0.5f,
                -1f, -0.5f,  0.5f,
                -1f,  -0.5f,  -0.5f,
                // right
                1f, 0.5f,  -0.5f,
                1f,  0.5f,  0.5f,
                1f, -0.5f,  0.5f,
                1f,  -0.5f,  -0.5f,
                // bottom
                0.5f, -1f,  -0.5f,
                0.5f,  -1f,  0.5f,
                -0.5f, -1f,  0.5f,
                -0.5f,  -1f,  -0.5f,
                // top
                0.5f, 1f,  -0.5f,
                0.5f,  1f,  0.5f,
                -0.5f, 1f,  0.5f,
                -0.5f,  1f,  -0.5f,
                // front left
                -1f, 0.5f,  0.5f,
                -0.5f,  0.5f,  1f,
                -0.5f, -0.5f,  1f,
                -1f,  -0.5f,  0.5f,
                // front right
                1f, 0.5f,  0.5f,
                0.5f,  0.5f,  1f,
                0.5f, -0.5f,  1f,
                1f,  -0.5f,  0.5f,
                // back left
                -1f, 0.5f,  -0.5f,
                -0.5f,  0.5f,  -1f,
                -0.5f, -0.5f,  -1f,
                -1f,  -0.5f,  -0.5f,
                // back right
                1f, 0.5f,  -0.5f,
                0.5f,  0.5f, -1f,
                0.5f, -0.5f, -1f,
                1f,  -0.5f, -0.5f,
                // front top
                -0.5f, 1f,  0.5f,
                -0.5f,  0.5f,  1f,
                0.5f, 0.5f,  1f,
                0.5f,  1f,  0.5f,
                // back top
                -0.5f, 1f,  -0.5f,
                -0.5f,  0.5f,  -1f,
                0.5f, 0.5f,  -1f,
                0.5f,  1f,  -0.5f,
                // front bottom
                -0.5f, -1f,  0.5f,
                -0.5f,  -0.5f,  1f,
                0.5f, -0.5f,  1f,
                0.5f,  -1f,  0.5f,
                // back bottom
                -0.5f, -1f,  -0.5f,
                -0.5f,  -0.5f,  -1f,
                0.5f, -0.5f,  -1f,
                0.5f,  -1f,  -0.5f,
                // left top
                -0.5f, 1f, -0.5f,
                -0.5f,  1f, 0.5f,
                -1f, 0.5f, 0.5f,
                -1f,  0.5f, -0.5f,
                // right top
                0.5f, 1f, -0.5f,
                0.5f,  1f, 0.5f,
                1f, 0.5f, 0.5f,
                1f,  0.5f, -0.5f,
                // left bottom
                -0.5f, -1f, -0.5f,
                -0.5f, -1f, 0.5f,
                -1f, -0.5f, 0.5f,
                -1f,  -0.5f, -0.5f,
                // right bottom
                0.5f, -1f, -0.5f,
                0.5f, -1f, 0.5f,
                1f, -0.5f, 0.5f,
                1f,  -0.5f, -0.5f
        };
        float[] lineColors = new float[]{
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
                0, 0, 0, 1,
        };
        int[] lineIndices = new int[]{
                0, 1, 1, 2, 2, 3, 3, 0,
                4, 5, 5, 6, 6, 7, 7, 4,
                8, 9, 9, 10, 10, 11, 11, 8,
                12, 13, 13, 14, 14, 15, 15, 12,
                16, 17, 17, 18, 18, 19, 19, 16,
                20, 21, 21, 22, 22, 23, 23, 20,
                24, 25, 25, 26, 26, 27, 27, 24,
                28, 29, 29, 30, 30, 31, 31, 28,
                32, 33, 33, 34, 34, 35, 35, 32,
                36, 37, 37, 38, 38, 39, 39, 36,
                40, 41, 41, 42, 42, 43, 43, 40,
                44, 45, 45, 46, 46, 47, 47, 44,
                48, 49, 49, 50, 50, 51, 51, 48,
                52, 53, 53, 54, 54, 55, 55, 52,
                56, 57, 57, 58, 58, 59, 59, 56,
                60, 61, 61, 62, 62, 63, 63, 60,
                64, 65, 65, 66, 66, 67, 67, 64,
                68, 69, 69, 70, 70, 71, 71, 68
        };
        Mesh linesMesh = new Mesh(linePositions, lineColors, lineIndices, GL_LINES);
        GameItem lines = new GameItem(linesMesh);
        lines.setPosition(0, 0, 0);

        gameItems[0] = lines;

        float[] shapePositions = new float[]{
            //back
            0.5f,  0.5f, -1f,
            -0.5f,  0.5f, -1f,
            -0.5f, -0.5f, -1f,
            0.5f, -0.5f, -1f,
            // back top
            -0.5f, 1f,  -0.5f,
            -0.5f,  0.5f,  -1f,
            0.5f, 0.5f,  -1f,
            0.5f,  1f,  -0.5f,
            // back bottom
            -0.5f, -1f,  -0.5f,
            -0.5f,  -0.5f,  -1f,
            0.5f, -0.5f,  -1f,
            0.5f,  -1f,  -0.5f,
            // back left
            -1f, 0.5f,  -0.5f,
            -0.5f,  0.5f,  -1f,
            -0.5f, -0.5f,  -1f,
            -1f,  -0.5f,  -0.5f,
            // back right
            1f, 0.5f,  -0.5f,
            0.5f,  0.5f, -1f,
            0.5f, -0.5f, -1f,
            1f,  -0.5f, -0.5f,
            // left top back
            -0.5f, 1f, -0.5f,
            -1f, 0.5f, -0.5f,
            -0.5f, 0.5f, -1f,
            // right top back
            0.5f, 1f, -0.5f,
            1f, 0.5f, -0.5f,
            0.5f, 0.5f, -1f,
            // left bottom back
            -0.5f, -1f, -0.5f,
            -1f, -0.5f, -0.5f,
            -0.5f, -0.5f, -1f,
            // right bottom back
            0.5f, -1f, -0.5f,
            1f, -0.5f, -0.5f,
            0.5f, -0.5f, -1f,
            // left
            -1f, 0.5f,  -0.5f,
            -1f,  0.5f,  0.5f,
            -1f, -0.5f,  0.5f,
            -1f,  -0.5f,  -0.5f,
            // right
            1f, 0.5f,  -0.5f,
            1f,  0.5f,  0.5f,
            1f, -0.5f,  0.5f,
            1f,  -0.5f,  -0.5f,
            // bottom
            0.5f, -1f,  -0.5f,
            0.5f,  -1f,  0.5f,
            -0.5f, -1f,  0.5f,
            -0.5f,  -1f,  -0.5f,
            // top
            0.5f, 1f,  -0.5f,
            0.5f,  1f,  0.5f,
            -0.5f, 1f,  0.5f,
            -0.5f,  1f,  -0.5f,
            // left top
            -0.5f, 1f, -0.5f,
            -0.5f,  1f, 0.5f,
            -1f, 0.5f, 0.5f,
            -1f,  0.5f, -0.5f,
            // right top
            0.5f, 1f, -0.5f,
            0.5f,  1f, 0.5f,
            1f, 0.5f, 0.5f,
            1f,  0.5f, -0.5f,
            // left bottom
            -0.5f, -1f, -0.5f,
            -0.5f, -1f, 0.5f,
            -1f, -0.5f, 0.5f,
            -1f,  -0.5f, -0.5f,
            // right bottom
            0.5f, -1f, -0.5f,
            0.5f, -1f, 0.5f,
            1f, -0.5f, 0.5f,
            1f,  -0.5f, -0.5f,
            //front
            -0.5f,  0.5f,  1f,
            -0.5f, -0.5f,  1f,
            0.5f, -0.5f,  1f,
            0.5f,  0.5f,  1f,
            // front left
            -1f, 0.5f,  0.5f,
            -0.5f,  0.5f,  1f,
            -0.5f, -0.5f,  1f,
            -1f,  -0.5f,  0.5f,
            // front right
            1f, 0.5f,  0.5f,
            0.5f,  0.5f,  1f,
            0.5f, -0.5f,  1f,
            1f,  -0.5f,  0.5f,
            // front top
            -0.5f, 1f,  0.5f,
            -0.5f,  0.5f,  1f,
            0.5f, 0.5f,  1f,
            0.5f,  1f,  0.5f,
            // front bottom
            -0.5f, -1f,  0.5f,
            -0.5f,  -0.5f,  1f,
            0.5f, -0.5f,  1f,
            0.5f,  -1f,  0.5f,
            // left top front
            -0.5f, 1f, 0.5f,
            -1f, 0.5f, 0.5f,
            -0.5f, 0.5f, 1f,
            // right top front
            0.5f, 1f, 0.5f,
            1f, 0.5f, 0.5f,
            0.5f, 0.5f, 1f,
            // left bottom front
            -0.5f, -1f, 0.5f,
            -1f, -0.5f, 0.5f,
            -0.5f, -0.5f, 1f,
            // right bottom front
            0.5f, -1f, 0.5f,
            1f, -0.5f, 0.5f,
            0.5f, -0.5f, 1f,
        };

        float[] shapeColors = new float[]{
            //front
            0.5f, 0, 0, 0.5f,
            0.5f, 0, 0, 0.5f,
            0.5f, 0, 0, 0.5f,
            0.5f, 0, 0, 0.5f,
            //back
            0, 0.5f, 0, 0.5f,
            0, 0.5f, 0, 0.5f,
            0, 0.5f, 0, 0.5f,
            0, 0.5f, 0, 0.5f,
            // left
            0, 0, 0.5f, 0.5f,
            0, 0, 0.5f, 0.5f,
            0, 0, 0.5f, 0.5f,
            0, 0, 0.5f, 0.5f,
            // right
            0, 0.5f, 0.5f, 0.5f,
            0, 0.5f, 0.5f, 0.5f,
            0, 0.5f, 0.5f, 0.5f,
            0, 0.5f, 0.5f, 0.5f,
            // bottom
            0.5f, 0, 0.5f, 0.5f,
            0.5f, 0, 0.5f, 0.5f,
            0.5f, 0, 0.5f, 0.5f,
            0.5f, 0, 0.5f, 0.5f,
            // left bottom back
            0.5f, 1f, 0.5f, 0.5f,
            0.5f, 1f, 0.5f, 0.5f,
            0.5f, 1f, 0.5f, 0.5f,
            // right bottom back
            0.3f, 1f, 0.5f, 0.5f,
            0.3f, 1f, 0.5f, 0.5f,
            0.3f, 1f, 0.5f, 0.5f,
            // left top back
            0.3f, 0.3f, 0.7f, 0.5f,
            0.3f, 0.3f, 0.7f, 0.5f,
            0.3f, 0.3f, 0.7f, 0.5f,
            // right top back
            0.3f, 0.3f, 1f, 0.5f,
            0.3f, 0.3f, 1f, 0.5f,
            0.3f, 0.3f, 1f, 0.5f,
            // top
            0.5f, 0.5f, 0, 0.5f,
            0.5f, 0.5f, 0, 0.5f,
            0.5f, 0.5f, 0, 0.5f,
            0.5f, 0.5f, 0, 0.5f,
            // front left
            0.2f, 0, 0.7f, 0.5f,
            0.2f, 0, 0.7f, 0.5f,
            0.2f, 0, 0.7f, 0.5f,
            0.2f, 0, 0.7f, 0.5f,
            // front right
            0.2f, 0.5f, 0.7f, 0.5f,
            0.2f, 0.5f, 0.7f, 0.5f,
            0.2f, 0.5f, 0.7f, 0.5f,
            0.2f, 0.5f, 0.7f, 0.5f,
            // back left
            0.2f, 1, 0.7f, 0.5f,
            0.2f, 1, 0.7f, 0.5f,
            0.2f, 1, 0.7f, 0.5f,
            0.2f, 1, 0.7f, 0.5f,
            // back right
            0.7f, 1, 0.2f, 0.5f,
            0.7f, 1, 0.2f, 0.5f,
            0.7f, 1, 0.2f, 0.5f,
            0.7f, 1, 0.2f, 0.5f,
            // front top
            0.7f, 0.5f, 0.2f, 0.5f,
            0.7f, 0.5f, 0.2f, 0.5f,
            0.7f, 0.5f, 0.2f, 0.5f,
            0.7f, 0.5f, 0.2f, 0.5f,
            // back top
            0.7f, 0.1f, 0.2f, 0.5f,
            0.7f, 0.1f, 0.2f, 0.5f,
            0.7f, 0.1f, 0.2f, 0.5f,
            0.7f, 0.1f, 0.2f, 0.5f,
            // front bottom
            0.4f, 0.1f, 0.5f, 0.5f,
            0.4f, 0.1f, 0.5f, 0.5f,
            0.4f, 0.1f, 0.5f, 0.5f,
            0.4f, 0.1f, 0.5f, 0.5f,
            // back bottom
            0.4f, 0.9f, 0.5f, 0.5f,
            0.4f, 0.9f, 0.5f, 0.5f,
            0.4f, 0.9f, 0.5f, 0.5f,
            0.4f, 0.9f, 0.5f, 0.5f,
            // left top
            0.5f, 0.5f, 0.5f, 0.5f,
            0.5f, 0.5f, 0.5f, 0.5f,
            0.5f, 0.5f, 0.5f, 0.5f,
            0.5f, 0.5f, 0.5f, 0.5f,
            // right top
            0.7f, 0.7f, 0.7f, 0.5f,
            0.7f, 0.7f, 0.7f, 0.5f,
            0.7f, 0.7f, 0.7f, 0.5f,
            0.7f, 0.7f, 0.7f, 0.5f,
            // left bottom
            0.7f, 0.7f, 0, 0.5f,
            0.7f, 0.7f, 0, 0.5f,
            0.7f, 0.7f, 0, 0.5f,
            0.7f, 0.7f, 0, 0.5f,
            // right bottom
            0, 0.7f, 0.5f, 0.5f,
            0, 0.7f, 0.5f, 0.5f,
            0, 0.7f, 0.5f, 0.5f,
            0, 0.7f, 0.5f, 0.5f,
            // left top front
            0.3f, 0.7f, 0.5f, 0.5f,
            0.3f, 0.7f, 0.5f, 0.5f,
            0.3f, 0.7f, 0.5f, 0.5f,
            // right top front
            0.3f, 0.7f, 0.1f, 0.5f,
            0.3f, 0.7f, 0.1f, 0.5f,
            0.3f, 0.7f, 0.1f, 0.5f,
            // left bottom front
            1f, 0.3f, 1f, 0.5f,
            1f, 0.3f, 1f, 0.5f,
            1f, 0.3f, 1f, 0.5f,
            // right bottom front
            1f, 0.3f, 0.5f, 0.5f,
            1f, 0.3f, 0.5f, 0.5f,
            1f, 0.3f, 0.5f, 0.5f,
        };
        int[] shapeIndices = new int[]{
            0, 1, 3, 3, 1, 2,
            4, 5, 7, 7, 5, 6,
            8, 9, 11, 11, 9, 10,
            12, 13, 15, 15, 13, 14,
            16, 17, 19, 19, 17, 18,
            20, 21, 22,
            23, 24, 25,
            26, 27, 28,
            29, 30, 31,
            32, 33, 35, 35, 33, 34,
            36, 37, 39, 39, 37, 38,
            40, 41, 43, 43, 41, 42,
            44, 45, 47, 47, 45, 46,
            48, 49, 51, 51, 49, 50,
            52, 53, 55, 55, 53, 54,
            56, 57, 59, 59, 57, 58,
            60, 61, 63, 63, 61, 62,
            64, 65, 67, 67, 65, 66,
            68, 69, 71, 71, 69, 70,
            72, 73, 75, 75, 73, 74,
            76, 77, 79, 79, 77, 78,
            80, 81, 83, 83, 81, 82,
            84, 85, 86,
            87, 88, 89,
            90, 91, 92,
            93, 94, 95
        };

        Mesh shapeFrontMesh = new Mesh(shapePositions, shapeColors, shapeIndices, GL_TRIANGLES);
        shapeFrontMesh.setCullFace(GL_FRONT);
        GameItem shapeFront = new GameItem(shapeFrontMesh);
        shapeFront.setPosition(0, 0, 0);
        gameItems[1] = shapeFront;

        Mesh shapeBackMesh = new Mesh(
                Arrays.copyOf(shapePositions, shapePositions.length),
                Arrays.copyOf(shapeColors, shapeColors.length),
                Arrays.copyOf(shapeIndices, shapeIndices.length),
                GL_TRIANGLES
        );
        shapeBackMesh.setCullFace(GL_BACK);
        GameItem shapeBack = new GameItem(shapeBackMesh);
        shapeBack.setPosition(0, 0, 0);
        gameItems[2] = shapeBack;
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
