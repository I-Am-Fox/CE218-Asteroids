package Game;

import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Asteroid {
    private double x, y; // Asteroid position
    private double dx, dy; // Velocity for movement
    private int size; // Size of the asteroid, influencing speed, health, and score
    private final int MAX_SPEED = 2;
    private final int screenWidth = 800; // Screen width for wrapping and spawning
    private final int screenHeight = 600; // Screen height for wrapping and spawning
    private Ellipse2D.Double hitbox; // Collision detection hitbox
    private Random rand = new Random();

    public Asteroid(int screenWidth, int screenHeight) {
        // Determine starting edge for the asteroid spawn
        boolean spawnLeftRight = rand.nextBoolean();
        if (spawnLeftRight) {
            x = rand.nextBoolean() ? 0 : screenWidth;
            y = rand.nextInt(screenHeight);
        } else {
            x = rand.nextInt(screenWidth);
            y = rand.nextBoolean() ? 0 : screenHeight;
        }

        // Randomize velocity
        double speedX = rand.nextDouble() * MAX_SPEED;
        double speedY = rand.nextDouble() * MAX_SPEED;
        dx = rand.nextBoolean() ? -speedX : speedX;
        dy = rand.nextBoolean() ? -speedY : speedY;

        // Determine size
        size = rand.nextInt(30) + 20; // Sizes between 20 and 50
        hitbox = new Ellipse2D.Double(x, y, size, size);
    }

    public void update() {
        x += dx;
        y += dy;
        hitbox.setFrame(x, y, size, size);

        // Screen wrapping
        if (x < 0) {
            x += screenWidth;
        } else if (x > screenWidth) {
            x -= screenWidth;
        }
        if (y < 0) {
            y += screenHeight;
        } else if (y > screenHeight) {
            y -= screenHeight;
        }
    }

    public boolean checkCollision(Ellipse2D.Double otherHitbox) {
        // Check for intersection with another hitbox (e.g., spaceship or bullet)
        return hitbox.intersects(otherHitbox.getBounds2D());
    }

    // Method for drawing the asteroid in the game display
    public void draw(java.awt.Graphics2D g) {
        g.fillOval((int)x, (int)y, size, size);
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public Ellipse2D.Double getHitbox() {
        return hitbox;
    }
}
