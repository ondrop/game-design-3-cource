package elements;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

public class Ellipse implements Drawable {

    private final float xCenter;
    private final float yCenter;
    private final float rx;
    private final float ry;
    private int points = 360;

    public Ellipse(float xCenter, float yCenter, float rx, float ry) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.rx = rx;
        this.ry = ry;
    }

    public Ellipse(float xCenter, float yCenter, float rx, float ry, int points) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.rx = rx;
        this.ry = ry;
        this.points = points;
    }

    @Override
    public void draw() {
        float step = (float) (2 * PI / points);

        glBegin(GL_POLYGON);
        for (float angle = 0; angle <= points; angle += step) {
            float a = ((angle - 2 * PI) < 1e-5) ? 0 : angle;
            double x = cos(a) * rx;
            double y = sin(a) * ry;
            glVertex2d(xCenter + x, yCenter + y);
        }

        glEnd();
    }
}
