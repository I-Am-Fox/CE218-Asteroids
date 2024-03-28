package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyShip {
    private double x, y;
    private double dx, dy;
    private Ellipse2D.Double hitbox;
    private List<Bullet> bullets = new ArrayList<>();
    private static final Random rand = new Random();

    public EnemyShip(double startX, double startY) {
        this.x = startX;
        this.y = startY;
        hitbox = new Ellipse2D.Double(x - 15, y - 10, 30, 20);
        dx = (rand.nextDouble() * 2 - 1) * 2;
        dy = (rand.nextDouble() * 2 - 1) * 2;
    }

    public void update(Spaceship player) {
        x += dx;
        y += dy;
        hitbox.setFrame(x - 15, y - 10, 30, 20);
        bullets.forEach(Bullet::update);
        bullets.removeIf(Bullet::isOffScreen);

        // Fire a bullet at the player every so often
        if (rand.nextInt(100) < 1) { // 1% chance to fire a bullet every update
            fire(player);
        }
    }

    public void draw(Graphics2D g) {
    g.setColor(Color.RED);
    g.fillRect((int)x, (int)y, 20, 10);
    g.setColor(Color.WHITE);
    g.drawRect((int)x, (int)y, 20, 10);
}

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x - 15, y - 10, 30, 20);
    }

    public void fire(Spaceship player) {
        double angleToPlayer = Math.atan2(player.getY() - y, player.getX() - x);
        bullets.add(new Bullet(x, y, Math.toDegrees(angleToPlayer)));
    }

    //Returns the hitbox of the enemy ship
    public Ellipse2D.Double getHitbox() {
        return hitbox;
    }

    //Returns the list of bullets for the enemy ship
    public List<Bullet> getBullets() {
        return bullets;
    }
}