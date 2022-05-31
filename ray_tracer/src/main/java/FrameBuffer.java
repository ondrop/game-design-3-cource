import java.util.Arrays;

public class FrameBuffer {

    private Vector3d[][] pixels;

    private int width;

    private int height;

    public FrameBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new Vector3d[width][height];
    }

    // Ширина буфера в пикселях
    public int getWidth() {
        return width;
    }

    // Высота буфера в пикселях
    public int getHeight() {
        return height;
    }

    // Очистка содержимого буфера заданным цветом
    public void clear(Vector3d color) {
        Arrays.fill(pixels, color);
    }

    public void clear() {
        Arrays.fill(pixels, new Vector3d(0, 0, 0));
    }

    // Vector3d адреса начала соотв. строки пикселей (для чтения)
	public Vector3d[] getPixels(int row) {
        if (row < height) {
            return pixels[row];
        }

        throw new RuntimeException("Row is bigger than height");
    }

    public Vector3d getPixel(int x, int y) {
        if (x < width && y < height) {
            return pixels[x][y];
        }

        throw new RuntimeException("Error");
    }

    // Получение цвета пикселя с заданными координатами
    public Vector3d[] getPixels(int x, int y) {
        if (x < width && y < height) {
            return pixels[y * height];
        }

        throw new RuntimeException("Error");
    }

    // Установка цвета пикселя с заданными координатами
    public void setPixel(int x, int y, Vector3d color) {
        if (x < width && y < height) {
            pixels[x][y] = color;
            return;
        }

        throw new RuntimeException("set pixel error");
    }
}
