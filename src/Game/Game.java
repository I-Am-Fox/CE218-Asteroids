package Game;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game implements ActionListener {
    private JFrame window;
    private GameDisplay display;
    private GameState currentState;
    private Spaceship spaceship;
    private ArrayList<EnemyShip> enemyShips;
    private ArrayList<Bullet> bullets;
    private ArrayList<Asteroid> asteroids;
    private int score;
    private Random rand;


    public enum GameState { MENU, PLAYING }

    public Game() {
        currentState = GameState.MENU;
        spaceship = new Spaceship(400, 300);
        enemyShips = new ArrayList<>();
        bullets = new ArrayList<>();
        asteroids = new ArrayList<>();
        score = 0;
        rand = new Random();


        setupWindow();
        setupInput();
        startGameLoop();
    }

    private void setupWindow() {
        window = new JFrame("Asteroids Game");
        display = new GameDisplay();
        window.add(display);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setVisible(true);
    }

    private void setupInput() {
    window.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (currentState == GameState.PLAYING) {
                spaceship.keyPressed(e);
            } else if (currentState == GameState.MENU && e.getKeyCode() == KeyEvent.VK_SPACE) {
                currentState = GameState.PLAYING;
                resetGame();
            }
        }
    });
}

    private void resetGame() {
        spaceship.resetPosition();
        enemyShips.clear();
        bullets.clear();
        score = 0;
    }

    private void startGameLoop() {
        Timer timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentState == GameState.PLAYING) {
            updateGameObjects();
            checkCollisions();
            spawnEnemies();
            removeOffScreenBullets();
        }
        updateDisplay();
        display.repaint();
    }

    private void updateGameObjects() {
        spaceship.update();
        enemyShips.forEach(enemyShip -> enemyShip.update(spaceship));
        bullets.forEach(bullet -> bullet.update());
        asteroids.forEach(asteroid -> asteroid.update());
    }

    private void checkCollisions() {
    Iterator<Bullet> bulletIter = bullets.iterator();
    while (bulletIter.hasNext()) {
        Bullet bullet = bulletIter.next();
        if (bullet.isOffScreen()) {
            bulletIter.remove();
            continue;
        }

        enemyShips.removeIf(enemy -> {
            if (bullet.getBounds().intersects(enemy.getBounds())) {
                bulletIter.remove();
                spaceship.addScore(35);
                return true;
            }
            return false;
        });

        asteroids.removeIf(asteroid -> {
            if (bullet.getBounds().intersects(asteroid.getHitbox().getBounds2D())) {
                bulletIter.remove();
                spaceship.addScore(20);
                return true;
            }
            return false;
        });
    }

    if (spaceship.isInvulnerable()) {
        return;
    }

    for (EnemyShip enemy : enemyShips) {
        if (spaceship.getBounds().intersects(enemy.getBounds())) {
            spaceship.loseLife();
            currentState = GameState.MENU;
            break;
        }
    }

    for (Asteroid asteroid : asteroids) {
        if (spaceship.getBounds().intersects(asteroid.getHitbox().getBounds2D())) {
            spaceship.loseLife();
            currentState = GameState.MENU;
            break;
        }
    }
}

    private void spawnEnemies() {
        if (enemyShips.size() < 5) {
            int x = rand.nextBoolean() ? -100 : 900;
            int y = rand.nextInt(600);
            enemyShips.add(new EnemyShip(x, y));
        }
    }

    public void spawnAsteroids() {
        if (asteroids.size() < 5) {
            asteroids.add(new Asteroid(800, 600));
        }
    }

    private void removeOffScreenBullets() {
        bullets.removeIf(Bullet::isOffScreen);
    }

    private void updateDisplay() {
        display.setSpaceship(spaceship);
        display.setEnemyShips(enemyShips);
        display.setBullets(bullets);
        display.setScore(score);
        display.setCurrentState(currentState);
    }

    public static void main(String[] args) {
        new Game();
    }
}