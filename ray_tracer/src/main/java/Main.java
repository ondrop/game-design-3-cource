import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Renderer renderer = new Renderer(600, 600);
        renderer.loop();
        renderer.print();
    }
}
