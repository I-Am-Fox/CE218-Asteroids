package Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Display display = new Display(game); // Pass the game instance to the Display constructor
        display.start();
    }

}