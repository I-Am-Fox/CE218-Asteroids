package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Bullet {
    private double x, y;
    private double dx, dy;
    private boolean offScreen;
    private int timeToLive; // Time to live in milliseconds
    private static final int screenWidth = 800;
    private static final int screenHeight = 600;

    public Bullet(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.dx = Math.cos(Math.toRadians(angle)) * 2;
        this.dy = Math.sin(Math.toRadians(angle)) * 2;
        this.offScreen = false;
        this.timeToLive = 2000; // Bullets live for 2 seconds
    }

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y, 4, 4);
    }

    // Update the bullet's position and time to live
    public void update() {
        x += dx;
        y += dy;
        timeToLive -= 16;

        // Check if the bullet has gone off-screen or lived its life
        if (x < 0 || x > screenWidth || y < 0 || y > screenHeight || timeToLive <= 0) {
            offScreen = true;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval((int)(x - 2), (int)(y - 2), 4, 4);
    }

    public boolean isOffScreen() {
        return offScreen;
    }
}