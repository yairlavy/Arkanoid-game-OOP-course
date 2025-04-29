package game.gameEnvironment;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import game.Collision.Collidable;
import game.Sprite.Sprite;
import game.Sprite.SpriteCollection;
import game.shapes.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Game class manages the game environment, sprites, and the game loop.
 * It initializes the game setup and runs the main game loop.
 *
 * @author Yair Lavy 322214073
 */
public class Game {
    private GUI gui; // The GUI frame that will host the game.
    private SpriteCollection sprites; // The array of all sprites related to the game.
    private GameEnvironment environment; // The game environment of the current game.
    private final Counter remainingBlocks; // Counter for the number of remaining blocks.
    private final Counter remainingBalls; // Counter for the number of available balls
    private final Counter score; // Counter for the score

    /**
     * Constructs a Game instance with the specified GUI.
     *
     * @param gui the GUI for the game.
     */
    public Game(GUI gui) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.gui = gui;
        this.remainingBlocks = new Counter(); // Initialize the counter for remaining blocks
        this.remainingBalls = new Counter(); // Initialize the counter for remaining balls
        this.score = new Counter(); // Initialize the score counter
    }

    /**
     * Returns the GUI of the game.
     *
     * @return the GUI.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Sets the GUI of the game.
     *
     * @param gui the new GUI.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Creates the borders of the game.
     *
     * @return an array of blocks representing the borders.
     */
    public Block[] createBorders() {
        int width = 800, height = 600;
        // create sides blocks
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0), width, 20), Color.WHITE);
        scoreBlock.setBorder(true);

        Block topBorder = new Block(new Rectangle(new Point(20, 20), width, 20), Color.gray);
        topBorder.setBorder(true); // Mark as border

        Block leftBorder = new Block(new Rectangle(new Point(0, 20), 20, height), Color.gray);
        leftBorder.setBorder(true); // Mark as border
        Block rightBorder = new Block(new Rectangle(new Point(width - 20, 20), 20, height), Color.gray);
        rightBorder.setBorder(true); // Mark as border

        topBorder.addToGame(this);
        scoreBlock.addToGame(this);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);

        return new Block[]{scoreBlock, topBorder, leftBorder, rightBorder};
    }

    /**
     * Generates a random color.
     *
     * @param start the seed for the random number generator.
     * @return a random color.
     */
    public Color randomColor(int start) {
        Random rand = new Random(start); // create a random-number generator
        int r = rand.nextInt(256); // get a float in range 0-256
        int g = rand.nextInt(256); // get float in range 0-256
        int b = rand.nextInt(256); // get integer in range 0-256
        return new Color(r, g, b);
    }

    /**
     * Creates a specified number of balls and adds them to the game.
     *
     * @param num the number of balls to create.
     */
    public void createBalls(int num) {
        ArrayList<Ball> balls = new ArrayList<>();
        int width = 800, height = 600;
        Random rand = new Random();

        // Create a BlockRemover instance with the remainingBlocks counter
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);

        // Create ScoreTrackingListener
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        // Create and add death-region block
        Block deathRegion = new Block(new Rectangle(new Point(0, 600), 800, 20), Color.BLACK);
        deathRegion.setBorder(true); // Mark as border
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);

        // Add blocks in pattern
        int x = 180;
        int y = 170;
        int blockWidth = 50;
        int blockHeight = 20;
        Color[] colors = {Color.cyan, Color.RED, Color.YELLOW, Color.WHITE, Color.PINK, Color.GREEN};
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 12 - row; col++) {
                Point upperLeft = new Point(x + col * blockWidth, y + row * blockHeight);
                Block block = new Block(new Rectangle(upperLeft, blockWidth, blockHeight), colors[row]);
                block.addToGame(this);
                block.addHitListener(scoreTrackingListener); // Register ScoreTrackingListener
                block.addHitListener(blockRemover); // Register BlockRemover as a listener
                remainingBlocks.increase(1); // Increase the block counter
            }
            x += blockWidth;
        }
        for (int i = 0; i < num; i++) {
            Color randomColor = randomColor(i);
            Point center = new Point(600, 520);
            Ball ball = new Ball(center, 5, randomColor,
                    new Frame(new Point(23, 23), height - 20, width - 20), environment);
            double speed = Math.max(1, 40.0 / 10);
            double angle = rand.nextInt(360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
            balls.add(ball);
            ball.addToGame(this);
            remainingBalls.increase(1); // Increase the ball counter
        }
    }

    /**
     * Initializes the game, setting up sprites, environment, and adding objects to the game.
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        // Add background
        Background background = new Background(Color.blue);
        addSprite(background);

        // Create borders
        Block[] borders = createBorders();
        for (Block border : borders) {
            border.addToGame(this);
        }
        Block deathRegion = new Block(new Rectangle(new Point(0, 600), 800, 20), Color.BLACK);
        deathRegion.setBorder(true); // Mark as border
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);

        // Create paddle
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle paddleRect = new Rectangle(new Point(570, 570), 100, 10); // Paddle is just above the bottom border
        Paddle paddle = new Paddle(keyboard, paddleRect, Color.yellow);
        paddle.addToGame(this);

        // Create ScoreIndicator and add to game
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);

        // Add balls
        createBalls(3);
    }

    /**
     * Runs the main game loop, drawing all sprites and notifying them that time has passed.
     */
    public void run() {
        System.out.println("num of blocks " + remainingBlocks.getValue());
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();

        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // Check if no more blocks are available
            if (this.remainingBlocks.getValue() == 0 || this.remainingBalls.getValue() == 0) {
                gui.close();
                return;
            }
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidableList().remove(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The main method to start and run the game.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        int width = 800, height = 600;
        GUI gui = new GUI("Game", width, height);
        Game game = new Game(gui);
        game.initialize();
        game.run();
    }
}
