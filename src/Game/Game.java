package Game;

import java.awt.*;

/**
 * The Game class represents the main game logic and state.
 */
public class Game {

    // The title of the game
    public static final String Title = "Asteroids";

    // The width and height of the game window
    public static final int WIDTH = 800, HEIGHT = 800;

    /**
     * The STATE enum represents the different states that the game can be in.
     * Menu: The game is in the main menu.
     * Title: The game is in the title screen.
     * Game: The game is currently being played.
     * Hit: The player's spaceship has been hit.
     * GameOver: The game is over.
     */
    public static enum STATE {Menu, Title, Game, Hit, GameOver};

    // The current state of the game
    private static STATE State = STATE.Menu;

    // The player's spaceship
    private Spaceship spaceship;

    // The number of lives the player has left
    private int lives = 3;

    // The player's current score
    private int score;

    public Game() {
        init();
    }








    public void draw(Graphics2D g) {
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, WIDTH, HEIGHT);

        for (Obj obj : objs) {
            obj.draw(g);
        }
    }
}