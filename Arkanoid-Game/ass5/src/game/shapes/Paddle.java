package game.shapes;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import game.Collision.Collidable;
import game.Sprite.Sprite;
import game.gameEnvironment.Game;
import java.awt.Color;
import java.util.ArrayList;

/**
 * The Paddle class represents the paddle in the game, which is both a collidable and a sprite.
 * It is controlled by the keyboard and can move left and right.
 * @author Yair Lavy 322214073
 */
public class Paddle implements Sprite, Collidable {

    private final KeyboardSensor keyboard;
    private Rectangle paddle;
    private final Color color;
    private final ArrayList<Ball> balls;
    // an array of balls to check if any ball is inside the paddle.

    private static final int LEFT_PADDLE_BORDER = 20;
    private static final int RIGHT_PADDLE_BORDER = 780;
    private static final int STEP = 10; // a step of the paddle.
    private static final double EPSILON = Math.pow(10, -10); //a THRESHOLD value.

    /**
     * Constructor for the paddle.
     *
     * @param keyboard the keyboard sensor
     * @param paddle   the rectangle representing the paddle
     * @param color    the color of the paddle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle paddle, Color color) {
        this.keyboard = keyboard;
        this.paddle = paddle;
        this.color = color;
        this.balls = new ArrayList<>();
    }

    /**
     * Returns the keyboard sensor.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * Returns the rectangle representing the paddle.
     *
     * @return the rectangle representing the paddle
     */
    public Rectangle getPaddle() {
        return this.paddle;
    }

    /**
     * Moves the paddle to the left unless it has reached the edge of the screen.
     */
    public void moveLeft() {
        Point nextUpperLeft;
        if (getCollisionRectangle().getUpperLeft().getX() > LEFT_PADDLE_BORDER) {
            // The new upper left point of the rectangle.
            nextUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX() - STEP,
                    getCollisionRectangle().getUpperLeft().getY());
        } else {
            nextUpperLeft = new Point(RIGHT_PADDLE_BORDER - getCollisionRectangle().getWidth(),
                    getCollisionRectangle().getUpperLeft().getY());
        }
        this.paddle = new Rectangle(nextUpperLeft,
                getCollisionRectangle().getWidth(), getCollisionRectangle().getHeight());
    }

    /**
     * Moves the paddle to the right unless it has reached the edge of the screen.
     */
    public void moveRight() {
        Point nextUpperLeft;
        if (getCollisionRectangle().getUpperRight().getX() < RIGHT_PADDLE_BORDER) {
            // The new upper left point of the rectangle.
            nextUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX() + STEP,
                    getCollisionRectangle().getUpperLeft().getY());
        } else {
            nextUpperLeft = new Point(LEFT_PADDLE_BORDER, getCollisionRectangle().getUpperLeft().getY());
        }
        this.paddle = new Rectangle(nextUpperLeft,
                getCollisionRectangle().getWidth(), getCollisionRectangle().getHeight());
    }

    /**
     * Checks if any ball is inside the paddle and adjusts the ball's position and velocity if necessary.
     */
    public void ballsInside() {
        for (Ball ball : balls) {
            // Check if the ball is inside the paddle
            if (ball.getY() > getCollisionRectangle().getUpperLeft().getY()
                    && ball.getY() <= getCollisionRectangle().getBottomLeft().getY()) {
                if (ball.getX() >= getCollisionRectangle().getUpperLeft().getX()
                        && ball.getX() <= getCollisionRectangle().getUpperRight().getX()) {
                    // Set the ball outside the paddle
                    if (ball.getX() <= getCollisionRectangle().getUpperLeft().getX()
                            + getCollisionRectangle().getWidth() / 2) {
                        ball.setX(ball.getX() - 1.5 * STEP);
                    } else {
                        ball.setX(ball.getX() + 1.5 * STEP);
                    }
                    ball.setVelocity(ball.getVelocity().getDx() * (-1), ball.getVelocity().getDy());
                }
            }
        }
    }

    /**
     * Returns the list of balls.
     *
     * @return the list of balls
     */
    public ArrayList<Ball> getBalls() {
        return balls;
    }

    /**
     * Adds a ball to the list of balls.
     *
     * @param ball the ball to add
     */
    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        ballsInside();
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Printing the color of the paddle.
        d.setColor(this.color);
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        // Setting the black frame of the paddle.
        d.setColor(Color.BLACK);
        d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(), (int) getCollisionRectangle().getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Check for collision with the sides of the paddle
        if (collisionPoint.getX() == getCollisionRectangle().getUpperLeft().getX()
                || collisionPoint.getX() == getCollisionRectangle().getUpperRight().getX()) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
        } else if (Math.abs(collisionPoint.getY() - getCollisionRectangle().getUpperLeft().getY()) < EPSILON) {
            // Check for collision with the top of the paddle
            currentVelocity = checkHitSpot(collisionPoint, currentVelocity);
        } else {
            // Check for collision with the bottom of the paddle and prevent the ball from going inside
            if (Math.abs(collisionPoint.getY() - getCollisionRectangle().getBottomLeft().getY()) < EPSILON) {
                currentVelocity.setDy(-currentVelocity.getDy());
                // Move the ball slightly above the paddle to prevent it from going inside
                collisionPoint.setY(getCollisionRectangle().getUpperLeft().getY() - EPSILON);
            }
        }
        return currentVelocity;
    }

    /**
     * Checks where on the paddle the ball is hitting and changes the angle of the ball accordingly.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity after the hit
     */
    public Velocity checkHitSpot(Point collisionPoint, Velocity currentVelocity) {
        double section = getCollisionRectangle().getWidth() / 5; // dividing the paddle into 5 sections.
        double startPoint = getCollisionRectangle().getUpperLeft().getX();
        double x = collisionPoint.getX();
        // Checking where on the paddle the ball is hitting and changing the angle of the ball accordingly.
        if (x >= startPoint && x <= startPoint + section) { // section 1
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getVelocity());
        } else if (x >= startPoint + section && x <= startPoint + (section * 2)) { // section 2
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getVelocity());
        } else if (x >= startPoint + (section * 2) && x <= startPoint + (section * 3)) { // section 3
            currentVelocity.setDy(-currentVelocity.getDy());
            return currentVelocity;
        } else if (x >= startPoint + (section * 3) && x <= startPoint + (section * 4)) { // section 4
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getVelocity());
        } else if (x >= startPoint + (section * 4) && x <= startPoint + (section * 5)) { // section 5
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getVelocity());
        } else {
            currentVelocity.setDy(-currentVelocity.getDy()); // a default setter.
            return currentVelocity;
        }
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);

    }
}