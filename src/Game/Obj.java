package Game;

import java.awt.Graphics2D;

public abstract class Obj {
    protected int x, y; // Position

    public abstract void draw(Graphics2D g);
}