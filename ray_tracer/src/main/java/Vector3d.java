public class Vector3d {
    private double x, y, z;                  // position, also color (r,g,b)

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Vector3d add(Vector3d b) {
        return new Vector3d(x + b.getX(),y + b.getY(),z + b.getZ());
    }
    public Vector3d sub(Vector3d b) {
        return new Vector3d(x - b.getX(),y - b.getY(),z - b.getZ());
    }

    public Vector3d mul(double b) {
        return new Vector3d(x * b,y * b,z * b);
    }

    public Vector3d mul(Vector3d b) {
        return new Vector3d(x * b.getX(),y * b.getY(),z * b.getZ());
    }

    private Vector3d replace(Vector3d b) {
        this.x = b.getX();
        this.y = b.getY();
        this.z = b.getZ();

        return this;
    }

    public Vector3d normalize() {
        return replace(this.mul(1 / Math.sqrt(x * x + y * y + z * z)));
    }

    public double dot(Vector3d b) {
        return x * b.getX() + y * b.getY() + z * b.getZ();
    }

    public Vector3d modulus(Vector3d b){
        return new Vector3d(y * b.getZ() - z * b.getY(),z * b.getX() - x * b.getZ(),x * b.getY() - y * b.getX());
    }

    public double length() {
        return Math.sqrt(this.dot(this));
    }

    public Vector3d clamp() {
        return new Vector3d(
                Math.min(255, Math.max(0, x)),
                Math.min(255, Math.max(0, y)),
                Math.min(255, Math.max(0, z))
        );
    }
}
