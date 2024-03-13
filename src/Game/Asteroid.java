package Game;

import java.awt.Graphics2D;
import java.util.Random;

public class Asteroid extends Obj {
    private int x, y; // Position
    private int size; // Size of the asteroid
    private int dx, dy; // Velocity

    public Asteroid(int x, int y, int dx, int dy) {
        this.x = x; //Set the x position of the asteroid
        this.y = y; //Set the y position of the asteroid
        this.dx = dx; //Set the x velocity of the asteroid
        this.dy = dy; //Set the y velocity of the asteroid

        // Generate a random size between 20 and 50
        Random rand = new Random();
        this.size = rand.nextInt(31) + 20; // This will generate a random number between 20 and 50
    }

    // Update the asteroid's position
    public void update() {
        x += dx;
        y += dy;

        // If the asteroid goes off the screen, wrap it around to the other side
        if (x < 0) x += Game.WIDTH;
        if (x > Game.WIDTH) x -= Game.WIDTH;
        if (y < 0) y += Game.HEIGHT;
        if (y > Game.HEIGHT) y -= Game.HEIGHT;
    }

    // Draw the asteroid
    @Override
    public void draw(Graphics2D g) {
        g.drawOval(x - size / 2, y - size / 2, size, size);
    }
}
