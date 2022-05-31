import java.awt.*;
import java.awt.image.BufferedImage;

public class BasicBitmapStorage {

    private int width;
    private int height;
    private final BufferedImage image;

    public BasicBitmapStorage(int width, int height) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void fill(Vector3d c) {
        Graphics g = image.getGraphics();
        g.setColor(new Color((int) c.getX(), (int) c.getY(), (int) c.getZ()));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    public void setPixel(int x, int y, Vector3d c) {
        Color color = new Color((int) c.getX(), (int) c.getY(), (int) c.getZ());
        image.setRGB(x, y, color.getRGB());
    }

    public Vector3d getPixel(int x, int y) {
        Color color = new Color(image.getRGB(x, y));
        return new Vector3d(color.getRed(), color.getGreen(), color.getBlue());
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
