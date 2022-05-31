import java.io.File;
import java.io.IOException;

public class Renderer {

    private int width;
    private int height;

    private FrameBuffer frameBuffer;

    private Vector3d cameraPos;
    private Matrix3d cameraRotation;

    private BasicBitmapStorage storage;

    private final Shape[] shapes = {
            new Sphere(new Vector3d(0, 0, 4), 1, new Vector3d(255, 0, 0), 1000, 1),
            new Sphere(new Vector3d(2, -0.5, 3), 0.5, new Vector3d(0, 0, 255), 50, 0.1),
            //new Torus(new Vector3d(2, -1, 4), 0.5, 0.1, new Vector3d(0, 0, 255), 10, 0),
            new Sphere(new Vector3d(-2, 0, 3), 1, new Vector3d(0, 255, 0), 500, 0.5),
            new Sphere(new Vector3d(0, -5001, 0), 5000, new Vector3d(255, 0, 0), 10, 0.2),
            new Sphere(new Vector3d(0, 5010, 0), 5000, new Vector3d(255, 0, 0), 10, 0.2),
            new Sphere(new Vector3d(5010, 0, 0), 5000, new Vector3d(128, 128, 128), 10, 0.2),
            new Sphere(new Vector3d(-5010, 0, 0), 5000, new Vector3d(128, 128, 128), 10, 0.2),
            new Sphere(new Vector3d(0, 0, 5010), 5000, new Vector3d(255, 255, 0), 10, 0.2),
            new Sphere(new Vector3d(0, 0, -5010), 5000, new Vector3d(255, 255, 0), 1000, 1)
    };

    private final Light[] lights = {
            new Light(LightType.AMBIENT, 0.2),
            new Light(LightType.POINT, 0.6, new Vector3d(2, 1, 0)),
            new Light(LightType.DIRECTIONAL, 0.6, new Vector3d(1, 4, 4))
    };

    public Renderer(int width, int height) {
        frameBuffer = new FrameBuffer(width, height);
        cameraPos = new Vector3d(3, 0, 1);
        cameraRotation = new Matrix3d(new double[][]{
                {0.7071, 0, -0.7071},
                {0, 1, 0},
                {0.7071, 0, 0.7071}
        });
        storage = new BasicBitmapStorage(width, height);
        this.width = width;
        this.height = height;
    }

    public void putPixel(int x, int y, Vector3d color) {
        x = width/2 + x;
        y = height/2 - y - 1;

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }

        frameBuffer.setPixel(x, y, color);
    }

    private double computeLighting(Vector3d point, Vector3d normal, Vector3d view, double specular) {
        double intensity = 0;
        double lengthN = normal.length();
        double lengthV = view.length();

        for (Light light : lights) {
            if (light.getType() == LightType.AMBIENT) {
                intensity += light.getIntensity();
            } else {
                Vector3d vecL;
                double tMax;
                if (light.getType() == LightType.POINT) {
                    vecL = light.getPosition().sub(point);
                    tMax = 1;
                } else {  // Light.DIRECTIONAL
                    vecL = light.getPosition();
                    tMax = Double.POSITIVE_INFINITY;
                }

                // Shadow check.
                IntersectionDto blocker = closestIntersection(point, vecL, MathService.EPSILON, tMax);
                if (blocker != null) {
                    continue;
                }

                // Diffuse reflection.
                double nDotL = normal.dot(vecL);
                if (nDotL > 0) {
                    intensity += light.getIntensity() * nDotL / (lengthN * vecL.length());
                }

                // Specular reflection.
                if (specular != -1) {
                    Vector3d vecR = normal.mul(2.0 * normal.dot(vecL)).sub(vecL);
                    double rDotV = vecR.dot(view);
                    if (rDotV > 0) {
                        intensity += light.getIntensity() * Math.pow(rDotV / (vecR.length() * lengthV), specular);
                    }
                }
            }
        }

        return intensity;
    }

    private IntersectionDto closestIntersection(Vector3d origin, Vector3d direction, double minT, double maxT) {
        double closestT = Double.POSITIVE_INFINITY;
        Shape closestShape = null;

        for (Shape shape : shapes) {
            double[] ts = shape.intersect(origin, direction);
            if (ts.length > 0) {
                for (int i = 0; i  < ts.length; i++) {
                    if (ts[i] < closestT && minT < ts[i] && ts[i] < maxT) {
                        closestT = ts[i];
                        closestShape = shape;
                    }
                }
            }
        }

        if (closestShape != null) {
            return new IntersectionDto(closestShape, closestT);
        }

        return null;
    }

    // Computes the reflection of v1 respect to v2.
    public Vector3d reflectRay(Vector3d v1, Vector3d v2) {
        return v2.mul(v1.dot(v2) * 2).sub(v1);
    }

    public Vector3d traceRay(Vector3d origin, Vector3d direction, double minT, double maxT, double depth) {
        IntersectionDto intersectionDto = closestIntersection(origin, direction, minT, maxT);
        if (intersectionDto == null) {
            return new Vector3d(0, 0, 0);
        }

        var closestShape = intersectionDto.getClosestShape();
        var closestT = intersectionDto.getClosestT();

        Vector3d point = origin.add(direction.mul(closestT));
        Vector3d normal = closestShape.getNormal(point);

        Vector3d view = direction.mul(-1);
        double lighting = computeLighting(point, normal, view, closestShape.getSpecular());

        Vector3d localColor = closestShape.getColor().mul(lighting);

        if (closestShape.getReflective() <= 0 || depth <= 0) {
            return localColor;
        }

        Vector3d reflectedRay = reflectRay(view, normal);
        Vector3d reflectedColor = traceRay(point, reflectedRay, MathService.EPSILON, Double.POSITIVE_INFINITY, depth - 1);

        return localColor.mul(1 - closestShape.getReflective()).add(reflectedColor.mul(closestShape.getReflective()));
    }

    private static Vector3d multiplyMV(Matrix3d matrix, Vector3d vec) {
        return new Vector3d(
                matrix.getRow(0).dot(vec),
                matrix.getRow(1).dot(vec),
                matrix.getRow(2).dot(vec)
        );
    }

    public void loop() {
        for (int x = -width/2; x < width/2; x++) {
            for (int y = -height/2; y < height/2; y++) {
                Vector3d direction = new Vector3d((double)x / width, (double)y / height, 1);
                direction = multiplyMV(cameraRotation, direction);
                Vector3d color = traceRay(cameraPos, direction, 1, Double.POSITIVE_INFINITY, 3);
                putPixel(x, y, color.clamp());
            }
        }
    }

    public void print() throws IOException {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                storage.setPixel(x, y, frameBuffer.getPixel(x, y));
            }
        }
        File file = new File("image.ppm");
        PPMWriter.bitmapToPPM(file, storage);
    }
}
