public class Light {

    private LightType type;
    private double intensity;
    private Vector3d position;

    public Light(LightType type, double intensity, Vector3d position) {
        this.type = type;
        this.intensity = intensity;
        this.position = position;
    }

    public Light(LightType type, double intensity) {
        this.type = type;
        this.intensity = intensity;
    }

    public LightType getType() {
        return type;
    }

    public void setType(LightType type) {
        this.type = type;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public Vector3d getPosition() {
        return position;
    }

    public void setPosition(Vector3d position) {
        this.position = position;
    }
}
