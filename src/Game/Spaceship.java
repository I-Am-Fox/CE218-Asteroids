package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Spaceship {
    private double x, y; // Spaceship position
    private double dx, dy; // Velocity
    private double angle; // Rotation angle
    private List<Bullet> bullets; // Bullets fired by the spaceship
    private int lives; // Number of lives
    private int score; // Player's score
    private boolean isInvlunerable; // Flag to indicate invulnerability

    public Spaceship(double x, double y) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.angle = 0;
        this.bullets = new ArrayList<>();
        this.lives = 3; // Start with 3 lives
        this.score = 0; // Start with 0 score
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void resetPosition() {
        x = 400;
        y = 300;
        dx = 0;
        dy = 0;
        angle = 0;
        bullets.clear();
        lives = 3;
        score = 0;
    }

    public void update() {
        x += dx;
        y += dy;

        // Screen wrapping logic
        if (x < 0) {
            x = 800;
        } else if (x > 800) {
            x = 0;
        }

        if (y < 0) {
            y = 600;
        } else if (y > 600) {
            y = 0;
        }

        // Update bullets
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            // Remove bullets that have expired
            if (bullet.isOffScreen()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    //Rotate the spaceship to the left
    public void rotateLeft() {
    if (angle <= 0) {
        angle = 360;
    }
    angle -= 10; // Increase rotation speed
}

    // Rotate the spaceship to the right
public void rotateRight() {
    if (angle >= 360) {
        angle = 0;
    }
    angle += 10; // Increase rotation speed
}

// Accelerate the spaceship in the direction it is facing
public void accelerate() {
    dx += Math.cos(Math.toRadians(angle)) * 0.15; // Increase acceleration
    dy += Math.sin(Math.toRadians(angle)) * 0.15; // Increase acceleration
}

    // Decelerate the spaceship
    public void decelerate() {
        dx *= 0.9;
        dy *= 0.9;
    }

    // Fire a bullet from the spaceship
    public void fire() {
        Bullet bullet = new Bullet(x, y, angle);
        bullets.add(bullet);
    }

    // Check if the spaceship has collided with an asteroid or enemy ship
    public void loseLife() {
        lives--;
    }

    public boolean isInvulnerable() {
        return isInvlunerable;
    }

    public void addScore(int points) {
        score += points;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                rotateLeft();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                rotateRight();
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                accelerate();
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                decelerate();
                break;
            case KeyEvent.VK_SPACE:
                fire();
                break;
        }
    }

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x - 15, y - 10, 30, 20);
    }

    public void draw(Graphics2D g) {
    Graphics2D g2d = (Graphics2D) g.create(); // Create a copy of the graphics instance
    g2d.rotate(Math.toRadians(angle), x, y); // Rotate the graphics context around the spaceship's center

    int[] xPoints = {(int)x, (int)x - 10, (int)x + 10};
    int[] yPoints = {(int)y, (int)y + 20, (int)y + 20};
    g2d.setColor(Color.WHITE);
    g2d.drawPolygon(xPoints, yPoints, 3);

    g2d.dispose(); // Dispose the copy to prevent memory leaks
}

    // Getters and setters for x, y, lives, score, etc.
}