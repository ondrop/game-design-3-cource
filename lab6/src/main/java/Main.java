import engine.GameEngine;
import engine.IGameLogic;
import game.DummyGame;

public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("Sphere and torus", 1280, 720, vSync, gameLogic);
            gameEng.run();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(-1);
        }
    }
}
