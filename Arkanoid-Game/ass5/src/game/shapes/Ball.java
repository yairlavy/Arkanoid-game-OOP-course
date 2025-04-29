package game.shapes;

import biuoop.DrawSurface;
import game.Collision.Collidable;
import game.Collision.CollisionInfo;
import game.Sprite.Sprite;
import game.gameEnvironment.Game;
import game.gameEnvironment.GameEnvironment;

import java.awt.Color;

/**
 * Represents a ball with a center point, radius, color, and velocity.
 * Provides methods to draw the ball on a surface, set and get its velocity,
 * and move the ball within specified boundaries.
 * Yair Lavy 322214073
 */
public class Ball implements Sprite {

    /** The center point of the ball. */
    private Point center;
    /** The radius of the ball. */
    private final int radius;
    /** The color of the ball. */
    private Color color;
    /** The velocity of the ball. */
    private Velocity velocity;
    /** The frame within which the ball moves. */
    private Frame frame;
    private GameEnvironment gameEnvironment;

    private static final double EPSILON = Math.pow(10, -10); // a THRESHOLD value.
    private static final double CORRECTION = 1;
    private static final int LEFT_UPPER_BORDER = 21;
    private static final int RIGHT_BORDER = 779;
    private static final double CORNER_MARGIN = 2; // Margin for considering corner collisions

    /**
     * Constructs a ball with the specified center point, radius, color, and frame.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color the color of the ball
     * @param frame the frame within which the ball moves
     * @param gameEnvironment the game environment where the ball exists
     */
    public Ball(Point center, int radius, Color color, Frame frame, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frame = frame;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Constructs a ball with the specified radius and color.
     *
     * @param radius the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    /**
     * Returns the frame of the ball.
     *
     * @return the frame of the ball
     */
    public Frame getFrame() {
        return this.frame;
    }

    /**
     * Sets the frame of the ball.
     *
     * @param frame the new frame of the ball
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * Returns the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the ball's center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the ball's center.
     *
     * @return the y-coordinate of the ball's center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the surface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(this.color);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Adds the ball to the specified game.
     *
     * @param game the game to add the ball to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using the specified delta x and delta y values.
     *
     * @param dx the change in x-coordinate
     * @param dy the change in y-coordinate
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Returns the game environment of the ball.
     *
     * @return the game environment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets the game environment of the ball.
     *
     * @param environment the new game environment of the ball
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Sets the x-coordinate of the ball's center, ensuring it is within the game boundaries.
     *
     * @param x1 the new x-coordinate
     */
    public void setX(double x1) {
        // If the x value is not inside the borders, put it inside.
        if (x1 + EPSILON >= RIGHT_BORDER) {
            x1 = RIGHT_BORDER - (2 * CORRECTION);
        } else if (x1 - EPSILON <= LEFT_UPPER_BORDER) {
            x1 = LEFT_UPPER_BORDER + (2 * CORRECTION);
        }
        this.center.setX(x1);
    }

    /**
     * Sets the y-coordinate of the ball's center, ensuring it is within the game boundaries.
     *
     * @param y1 the new y-coordinate
     */
    public void setY(double y1) {
        // If the y value is not inside the borders, put it inside.
        if (y1 - EPSILON <= LEFT_UPPER_BORDER) {
            y1 = LEFT_UPPER_BORDER + (2 * CORRECTION);
        }
        this.center.setY(y1);
    }

    /**
     * Checks if the collision point is near any corner of the collidable object.
     *
     * @param collisionPoint the point of collision
     * @param collidable the collidable object
     * @return true if the collision point is near a corner, false otherwise
     */
    private boolean isCornerCollision(Point collisionPoint, Collidable collidable) {
        Rectangle rect = collidable.getCollisionRectangle();
        Point upperLeft = rect.getUpperLeft();
        Point upperRight = rect.getUpperRight();
        Point bottomLeft = rect.getBottomLeft();
        Point bottomRight = rect.getBottomRight();

        return collisionPoint.distance(upperLeft) < CORNER_MARGIN
                || collisionPoint.distance(upperRight) < CORNER_MARGIN
                || collisionPoint.distance(bottomLeft) < CORNER_MARGIN
                || collisionPoint.distance(bottomRight) < CORNER_MARGIN;
    }

    /**
     * Handles corner collision by reversing both x and y directions of the velocity.
     *
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity after handling the corner collision
     */
    private Velocity handleCornerCollision(Velocity currentVelocity) {
        // Reverse both x and y directions
        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * Moves the ball one step, handling collisions if they occur.
     */
    public void moveOneStep() {
        Point nextPosition = this.velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextPosition);
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);

        if (collisionInfo != null) {
            Point collisionPoint = collisionInfo.collisionPoint();
            Collidable collisionObject = collisionInfo.collisionObject();
            // Check and handle if the ball is inside a block
            insideBlock(collisionInfo);
            // Adjust the ball's position to be just before the collision point
            double adjustmentFactor = 0.99; // Move it slightly back from the collision point
            this.center = new Point(
                    this.center.getX() + adjustmentFactor * (collisionPoint.getX() - this.center.getX()),
                    this.center.getY() + adjustmentFactor * (collisionPoint.getY() - this.center.getY())
            );

            // Update the velocity based on the collision
            this.velocity = collisionObject.hit(this, collisionPoint, this.velocity);
        } else {
            this.center = nextPosition;
        }
    }

    /**
     * Ensures the ball is not inside a block after a collision.
     *
     * @param collisionInfo the collision information about the collided object and the collision point.
     */
    public void insideBlock(CollisionInfo collisionInfo) {
        Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();
        if (Math.abs(collisionInfo.collisionPoint().getX() - rectangle.getUpperLeft().getX()) < EPSILON) {
            // Left side collision
            setX(collisionInfo.collisionPoint().getX() - CORRECTION);
        } else if (Math.abs(collisionInfo.collisionPoint().getX() - rectangle.getUpperRight().getX()) < EPSILON) {
            // Right side collision
            setX(collisionInfo.collisionPoint().getX() + CORRECTION);
        }
        if (Math.abs(collisionInfo.collisionPoint().getY() - rectangle.getUpperLeft().getY()) < EPSILON) {
            // Top side collision
            setY(collisionInfo.collisionPoint().getY() - CORRECTION);
        } else if (Math.abs(collisionInfo.collisionPoint().getY() - rectangle.getBottomLeft().getY()) < EPSILON) {
            // Bottom side collision
            setY(collisionInfo.collisionPoint().getY() + CORRECTION);
        }
    }
}
