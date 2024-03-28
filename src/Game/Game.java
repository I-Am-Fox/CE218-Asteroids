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


    //The game has two states: MENU and PLAYING
    public enum GameState { MENU, PLAYING }

    //Constructor for the game
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

    // Set up the game window
    private void setupWindow() {
        window = new JFrame("Asteroids Game");
        display = new GameDisplay();
        window.add(display);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setVisible(true);
    }

    // Set up keyboard input
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

    // Reset the game to its initial state
    private void resetGame() {
        spaceship.resetPosition();
        enemyShips.clear();
        bullets.clear();
        score = 0;
    }

    // Start the game loop
    private void startGameLoop() {
        Timer timer = new Timer(16, this);
        timer.start();
    }

    // Game loop
    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentState == GameState.PLAYING) {
            updateGameObjects();
            checkCollisions();
            spawnEnemies();
            spawnAsteroids();
            removeOffScreenBullets();
        }
        updateDisplay();
        display.repaint();
    }

    // Update the game objects (spaceship, enemies, bullets, asteroids)
    private void updateGameObjects() {
        spaceship.update();
        enemyShips.forEach(enemyShip -> enemyShip.update(spaceship));
        bullets.forEach(bullet -> bullet.update());
        asteroids.forEach(asteroid -> asteroid.update());
    }

    // Check for collisions between game objects
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

    // Checks if the spaceship is invulnerable
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

    // Spawn new enemy ships
    private void spawnEnemies() {
        if (enemyShips.size() < 5) {
            int x = rand.nextBoolean() ? -100 : 900;
            int y = rand.nextInt(600);
            enemyShips.add(new EnemyShip(x, y));
        }
    }

    // Spawn new asteroids
    public void spawnAsteroids() {
        if (asteroids.size() < 5) {
            asteroids.add(new Asteroid(800, 600));
        }
    }

    // Remove bullets that have gone off-screen
    private void removeOffScreenBullets() {
        bullets.removeIf(Bullet::isOffScreen);
    }

    //Update the display with spaceship, enemies, bullets, and score
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