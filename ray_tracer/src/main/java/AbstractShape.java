public abstract class AbstractShape implements Shape {

    protected Vector3d color;
    protected double specular;
    protected double reflective;

    public AbstractShape(Vector3d color, double specular, double reflective) {
        this.color = color;
        this.specular = specular;
        this.reflective = reflective;
    }

    @Override
    public Vector3d getColor() {
        return color;
    }

    public void setColor(Vector3d color) {
        this.color = color;
    }

    @Override
    public double getSpecular() {
        return specular;
    }

    public void setSpecular(double specular) {
        this.specular = specular;
    }

    @Override
    public double getReflective() {
        return reflective;
    }

    public void setReflective(double reflective) {
        this.reflective = reflective;
    }
}
