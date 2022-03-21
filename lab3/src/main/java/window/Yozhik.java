package window;

import elements.Drawable;
import elements.Ellipse;

import static elements.Color.*;
import static org.lwjgl.opengl.GL11.*;

public class Yozhik extends BaseWindow {

    public Yozhik(int width, int height, String title) {
        super(width, height, title);
    }

    private void drawHead() {
        // needles
        glColor3f(CYBER_GRAPE.getRed(), CYBER_GRAPE.getGreen(), CYBER_GRAPE.getBlue());
        glBegin(GL_POLYGON);
        glVertex2d(-0.4f, 0);
        glVertex2d(-0.6f, 0.2f);
        glVertex2d(-0.4f, 0.32f);
        glVertex2d(-0.43f, 0.53f);
        glVertex2d(-0.18f, 0.5f);
        glVertex2d(0, 0.7f);
        glVertex2d(0.18f, 0.5f);
        glVertex2d(0.43f, 0.53f);
        glVertex2d(0.4f, 0.32f);
        glVertex2d(0.6f, 0.2f);
        glVertex2d(0.4f, 0);
        glEnd();

        glColor3f(FUCHSIA_ROSE.getRed(), FUCHSIA_ROSE.getGreen(), FUCHSIA_ROSE.getBlue());
        Drawable head = new Ellipse(0, 0, 0.4f, 0.38f);
        head.draw();

        //cutout
        glColor3f(CYBER_GRAPE.getRed(), CYBER_GRAPE.getGreen(), CYBER_GRAPE.getBlue());
        glBegin(GL_POLYGON);
        glVertex2d(0, 0.3f);
        glVertex2d(0.12f, 0.365f);
        glVertex2d(0, 0.4f);
        glVertex2d(-0.12f, 0.365f);
        glEnd();

        glColor3f(FUCHSIA_ROSE.getRed(), FUCHSIA_ROSE.getGreen(), FUCHSIA_ROSE.getBlue());
        Drawable rightForehead = new Ellipse(0.1f, 0.3f, 0.1f, 0.063f);
        rightForehead.draw();

        Drawable leftForehead = new Ellipse(-0.1f, 0.3f, 0.1f, 0.063f);
        leftForehead.draw();

        //right eyebrow
        glColor3f(CHERRY.getRed(), CHERRY.getGreen(), CHERRY.getBlue());
        glBegin(GL_POLYGON);
        glVertex2d(0.05f, 0.32f);
        glVertex2d(0.1f, 0.3f);
        glVertex2d(0.15f, 0.27f);
        glVertex2d(0.145f, 0.26f);
        glVertex2d(0.13f, 0.25f);
        glVertex2d(0.1f, 0.265f);
        glVertex2d(0.04f, 0.28f);
        glVertex2d(0.041f, 0.3f);
        glEnd();

        //left eyebrow
        glBegin(GL_POLYGON);
        glVertex2d(-0.05f, 0.32f);
        glVertex2d(-0.1f, 0.3f);
        glVertex2d(-0.15f, 0.27f);
        glVertex2d(-0.145f, 0.26f);
        glVertex2d(-0.13f, 0.25f);
        glVertex2d(-0.1f, 0.265f);
        glVertex2d(-0.04f, 0.28f);
        glVertex2d(-0.041f, 0.3f);
        glEnd();


        glColor3f(FUCHSIA_ROSE.getRed(), FUCHSIA_ROSE.getGreen(), FUCHSIA_ROSE.getBlue());
        Drawable rightEar = new Ellipse(0.36f, 0.175f, 0.05f, 0.05f);
        rightEar.draw();

        Drawable leftEar = new Ellipse(-0.36f, 0.175f, 0.05f, 0.05f);
        leftEar.draw();

        //nose
        glColor3f(CHERRY.getRed(), CHERRY.getGreen(), CHERRY.getBlue());
        glBegin(GL_POLYGON);
        glVertex2d(0, -0.02f);
        glVertex2d(0.05f, -0.02f);
        glVertex2d(0.005f, -0.07f);
        glVertex2d(0.005f, -0.11f);
        glVertex2d(-0.005f, -0.11f);
        glVertex2d(-0.005f, -0.07f);
        glVertex2d(-0.05f, -0.02f);
        glVertex2d(0, -0.02f);
        glEnd();

        //mouth
        glColor3f(RUSSIAN_VIOLET.getRed(), RUSSIAN_VIOLET.getGreen(), RUSSIAN_VIOLET.getBlue());
        glBegin(GL_POLYGON);
        glVertex2d(0, -0.11f);
        glVertex2d(0.01f, -0.11f);
        glVertex2d(0.065f, -0.09f);
        glVertex2d(0.065f, -0.095f);
        glVertex2d(0, -0.14f);
        glVertex2d(-0.065f, -0.095f);
        glVertex2d(-0.065f, -0.09f);
        glVertex2d(-0.01f, -0.11f);
        glVertex2d(0, -0.11f);
        glEnd();
    }

    private void drawLegs() {
        glColor3f(FUCHSIA_ROSE.getRed(), FUCHSIA_ROSE.getGreen(), FUCHSIA_ROSE.getBlue());
        Drawable leftLegStart = new Ellipse(-0.09f, -0.39f, 0.06f, 0.06f);
        leftLegStart.draw();

        Drawable leftFoot = new Ellipse(-0.09f, -0.5f, 0.09f, 0.06f);
        leftFoot.draw();

        //left leg connection
        glBegin(GL_POLYGON);
        glVertex2d(-0.15f, -0.39f);
        glVertex2d(-0.18f, -0.5f);
        glVertex2d(0, -0.5f);
        glVertex2d(-0.03f, -0.39f);
        glEnd();

        Drawable rightLegStart = new Ellipse(0.09f, -0.39f, 0.06f, 0.06f);
        rightLegStart.draw();

        Drawable rightFoot = new Ellipse(0.09f, -0.5f, 0.09f, 0.06f);
        rightFoot.draw();

        //right leg connection
        glBegin(GL_POLYGON);
        glVertex2d(0.15f, -0.39f);
        glVertex2d(0.18f, -0.5f);
        glVertex2d(0, -0.5f);
        glVertex2d(0.03f, -0.39f);
        glEnd();
    }

    private void drawHands() {
        glColor3f(FUCHSIA_ROSE.getRed(), FUCHSIA_ROSE.getGreen(), FUCHSIA_ROSE.getBlue());
        Drawable leftArm = new Ellipse(-0.4f, -0.2f, 0.06f, 0.12f);
        leftArm.draw();

        Drawable leftHand = new Ellipse(-0.37f, -0.28f, 0.065f, 0.065f);
        leftHand.draw();

        Drawable rightArm = new Ellipse(0.4f, -0.2f, 0.06f, 0.12f);
        rightArm.draw();

        Drawable rightHand = new Ellipse(0.37f, -0.28f, 0.065f, 0.065f);
        rightHand.draw();
    }

    private void drawGlassesAndEyes() {
        glColor3f(RUSSIAN_VIOLET.getRed(), RUSSIAN_VIOLET.getGreen(), RUSSIAN_VIOLET.getBlue());
        Drawable leftGlassesFrame = new Ellipse(-0.13f, 0.08f, 0.145f, 0.145f);
        leftGlassesFrame.draw();

        Drawable leftGlassesStickStart = new Ellipse(0.28f, 0.25f, 0.04f, 0.04f);
        leftGlassesStickStart.draw();

        //leftGlassesStick
        glBegin(GL_POLYGON);
        glVertex2d(0.24, 0.257f);
        glVertex2d(0.315, 0.23f);
        glVertex2d(0.22, 0.1f);
        glVertex2d(0.1, 0.1f);
        glEnd();

        Drawable rightGlassesFrame = new Ellipse(0.13f, 0.08f, 0.145f, 0.145f);
        rightGlassesFrame.draw();

        Drawable rightGlassesStickStart = new Ellipse(-0.28f, 0.25f, 0.04f, 0.04f);
        rightGlassesStickStart.draw();

        //rightGlassesStick
        glBegin(GL_POLYGON);
        glVertex2d(-0.24, 0.257f);
        glVertex2d(-0.315, 0.23f);
        glVertex2d(-0.22, 0.1f);
        glVertex2d(-0.1, 0.1f);
        glEnd();

        // eyes
        glColor3f(WHITE.getRed(), WHITE.getGreen(), WHITE.getBlue());
        Drawable rightEye = new Ellipse(0.13f, 0.08f, 0.105f, 0.105f);
        rightEye.draw();
        Drawable leftEye = new Ellipse(-0.13f, 0.08f, 0.105f, 0.105f);
        leftEye.draw();

        glColor3f(BLACK.getRed(), BLACK.getGreen(), BLACK.getBlue());
        Drawable rightAppleOfEye = new Ellipse(0.06f, 0.07f, 0.03f, 0.04f);
        rightAppleOfEye.draw();
        Drawable leftAppleOfEye = new Ellipse(-0.06f, 0.07f, 0.03f, 0.04f);
        leftAppleOfEye.draw();

        glColor3f(WHITE.getRed(), WHITE.getGreen(), WHITE.getBlue());
        Drawable rightEyeLight = new Ellipse(0.075f, 0.09f, 0.008f, 0.015f);
        rightEyeLight.draw();
        Drawable leftEyeLight = new Ellipse(-0.045f, 0.09f, 0.008f, 0.015f);
        leftEyeLight.draw();
    }

    public void draw(int width, int height) {
        glViewport(0, 0, width, height);

        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        drawHead();
        drawGlassesAndEyes();
        drawHands();
        drawLegs();
    }
}
