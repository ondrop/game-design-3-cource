package elements;

public enum Color {

    BLACK(0, 0, 0),
    WHITE(1, 1, 1),
    CHERRY(0.55f, 0.14f, 0.24f),
    FUCHSIA_ROSE(0.78f, 0.325f, 0.47f),
    CYBER_GRAPE(0.35f, 0.24f, 0.49f),
    RUSSIAN_VIOLET(0.24f, 0.09f, 0.35f);

    private float red;
    private float green;
    private float blue;

    Color(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }
}
