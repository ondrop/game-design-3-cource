package engine.graph;

import engine.GameItem;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {

    private final Matrix4f projectionMatrix;

    private final Matrix4f modelMatrix;

    private final Matrix4f viewMatrix;

    public Transformation() {
        modelMatrix = new Matrix4f();
        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
    }

    public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
        return projectionMatrix.setPerspective(fov, width / height, zNear, zFar);
    }

    public Matrix4f getModelMatrix(GameItem gameItem) {
        Vector3f rotation = gameItem.getRotation();
        modelMatrix
                .identity()
                .translate(gameItem.getPosition())
                .rotateXYZ(
                        (float)Math.toRadians(-rotation.x),
                        (float)Math.toRadians(-rotation.y),
                        (float)Math.toRadians(-rotation.z)
                )
                .scale(gameItem.getScale());

        return modelMatrix;
    }

    public Matrix4f getViewMatrix(Camera camera) {
        Vector3f cameraPos = camera.getPosition();
        Vector3f rotation = camera.getRotation();

        viewMatrix.identity();

        // First do the rotation so camera rotates over its position
        viewMatrix.rotateXYZ(
                (float)Math.toRadians(rotation.x),
                (float)Math.toRadians(rotation.y),
                (float)Math.toRadians(rotation.z)
        );
        // Then do the translation
        viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        return viewMatrix;
    }
}
