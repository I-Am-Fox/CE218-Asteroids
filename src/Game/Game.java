package Game;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game class represents the main game logic and state.
 */
public class Game extends JPanel {

    private List<Obj> objs = new ArrayList<>();

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
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_W) {
                    // Move spaceship up
                } else if (key == KeyEvent.VK_S) {
                    // Move spaceship down
                } else if (key == KeyEvent.VK_A) {
                    // Move spaceship left
                } else if (key == KeyEvent.VK_D) {
                    // Move spaceship right
                } else if (key == KeyEvent.VK_SPACE) {
                    // Fire a shot
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
                    // Stop moving spaceship
                } else if (key == KeyEvent.VK_SPACE) {
                    // Stop firing
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Fire a shot
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Stop firing
                }
            }
        });
        setFocusable(true);
    }

    public void init() {
        spaceship = new Spaceship(WIDTH / 2, HEIGHT / 2);
        score = 0;
        lives = 3;
    }

    public void draw(Graphics2D g) {
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, WIDTH, HEIGHT);

        for (Obj obj : objs) {
            obj.draw(g);
        }
    }
}