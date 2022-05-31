public interface Shape {

    Vector3d getColor();

    double getSpecular();

    double getReflective();

    double[] intersect(Vector3d origin, Vector3d direction);

    Vector3d getNormal(Vector3d point);
}
