import window.BaseWindow;
import window.Yozhik;

public class Main {

    public static void main(String[] args) {
        BaseWindow window = new Yozhik(1000, 1000, "Test title");
        window.run();
    }
}
