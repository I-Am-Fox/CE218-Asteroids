package Game;

import java.awt.Graphics2D;

public class Shot extends Obj {
    private int dx, dy; // Velocity

    public Shot(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw the shot
    }
}