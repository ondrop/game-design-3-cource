public class Sphere extends AbstractShape {
    private Vector3d center;
    private double radius;

    public Sphere(Vector3d center, double radius, Vector3d color, double specular, double reflective) {
        super(color, specular, reflective);
        this.center = center;
        this.radius = radius;
    }

    public Vector3d getCenter() {
        return center;
    }

    public void setCenter(Vector3d center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double[] intersect(Vector3d origin, Vector3d direction) { // returns distance, 0 if nohit
        Vector3d oc = origin.sub(center);

        double k1 = direction.dot(direction);
        double k2 = oc.dot(direction) * 2;
        double k3 = oc.dot(oc) - radius * radius;

        var discriminant = k2 * k2 - 4 * k1 * k3;
        if (discriminant < 0) {
            return new double[]{Double.POSITIVE_INFINITY};
        }

        var t1 = (-k2 + Math.sqrt(discriminant)) / (2 * k1);
        var t2 = (-k2 - Math.sqrt(discriminant)) / (2 * k1);

        return new double[]{t1, t2};
    }

    public Vector3d getNormal(Vector3d point) {
        Vector3d normal = point.sub(getCenter());

        return normal.mul(1.0 / normal.length());
    }
}
