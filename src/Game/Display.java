package Game;

import javax.swing.*;

public class Display extends JFrame {

    public Display(Game game) {
        // Set the title of the window
        setTitle(Game.Title);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window
        setSize(Game.WIDTH, Game.HEIGHT);

        // Add the game to the window
        add(game);

        // Pack the window
        pack();

        // Center the window on the screen
        setLocationRelativeTo(null);
    }

    public void start() {
        // Make the window visible
        setVisible(true);
    }
}