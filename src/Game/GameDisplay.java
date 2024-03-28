package Game;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GameDisplay extends JPanel {
    private Spaceship spaceship;
    private ArrayList<EnemyShip> enemyShips;
    private ArrayList<Bullet> bullets;
    private int score;
    public Game.GameState currentState;
    private ArrayList<Asteroid> asteroids;

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public void setEnemyShips(ArrayList<EnemyShip> enemyShips) {
        this.enemyShips = enemyShips;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setAsteroids(ArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(Game.GameState currentState) {
        this.currentState = currentState;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.BLACK);

        // Draw the spaceship
        if (spaceship != null) {
            spaceship.draw(g2d);
        }

        // Draw the enemy ships
        if (enemyShips != null) {
            for (EnemyShip enemyShip : enemyShips) {
                enemyShip.draw(g2d);
            }
        }

        // Draw the bullets
        if (bullets != null) {
            for (Bullet bullet : bullets) {
                bullet.draw(g2d);
            }
        }

        if (asteroids != null) {
            for (Asteroid asteroid : asteroids) {
                asteroid.draw(g2d);
            }
        }

        // Draw the score and game state
        g2d.drawString("Score: " + score, 10, 20);
        if (currentState != null) {
            g2d.drawString("State: " + currentState, 10, 40);
        }

        // Draw the main menu
        if (currentState == Game.GameState.MENU) {
            g2d.drawString("Press Space to Play", 350, 300);
        }
    }
}