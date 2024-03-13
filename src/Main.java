import game.Game;
import game.Display;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Display display = new Display(Game);
        display.start();
    }

}
