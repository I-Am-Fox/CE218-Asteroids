package Game;

import java.awt.Graphics2D;

public class Spaceship extends Obj {
    private int dx, dy; // Velocity

    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw the spaceship
    }
}