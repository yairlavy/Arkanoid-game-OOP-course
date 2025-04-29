package game.gameEnvironment;

import biuoop.GUI;
/**
 * @author Yair Lavy 322214073
 * The Ass3Game class contains the main method to start and run the game.
 * It sets up the game window and initializes and runs the game.
 */
public class Ass5Game {

    /**
     * The main method to start the game.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        GUI gui = new GUI("Ass3 game.gui.game.gui.shapes.shapes.gameEnvironment.Game", width, height);
        Game game = new Game(gui);

        // Initialize the game
        game.initialize();

        // Run the game
        game.run();
    }
}
