public class IntersectionDto {
    private Shape closestShape;
    private double closestT;

    private Vector3d normal;

    public IntersectionDto(Shape closestShape, double closestT) {
        this.closestShape = closestShape;
        this.closestT = closestT;
    }

    public Shape getClosestShape() {
        return closestShape;
    }

    public void setClosestShape(Shape closestShape) {
        this.closestShape = closestShape;
    }

    public double getClosestT() {
        return closestT;
    }

    public void setClosestT(double closestT) {
        this.closestT = closestT;
    }

    public Vector3d getNormal() {
        return normal;
    }

    public void setNormal(Vector3d normal) {
        this.normal = normal;
    }
}
