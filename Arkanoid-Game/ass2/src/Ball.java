import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * Represents a ball with a center point, radius, color, and velocity.
 * Provides methods to draw the ball on a surface, set and get its velocity,
 * and move the ball within specified boundaries.
 *
 * @auther Yair Lavy 322214073
 */
public class Ball {
    /** The center point of the ball. */
    private Point center;
    /** The radius of the ball. */
    private final int radius;
    /** The color of the ball. */
    private final java.awt.Color color;
    /** The velocity of the ball. */
    private Velocity velocity;
    /** The frame within which the ball moves. */
    private Frame frame;

    /**
     * Constructs a ball with the specified center point, radius, color, and frame.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color the color of the ball
     * @param frame the frame within which the ball moves
     */
    public Ball(Point center, int radius, java.awt.Color color, Frame frame) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frame = frame;
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
    public java.awt.Color getColor() {
        return this.color;
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
     * Moves the ball one step, checking for collisions with the frame boundaries
     * and reversing its velocity if a collision occurs.
     */
    public void moveOneStep() {
        // Move the center point according to the velocity
        this.center = this.velocity.applyToPoint(this.center);

        // Check for collisions with the frame boundaries and reverse velocity if needed
        if (this.center.getX() - this.radius <= this.frame.getPointStart().getX()
                || this.center.getX() + this.radius >= this.frame.getPointStart().getX() + this.frame.getLength()) {
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (this.center.getY() - this.radius <= this.frame.getPointStart().getY()
                || this.center.getY() + this.radius >= this.frame.getPointStart().getY() + this.frame.getWidth()) {
            this.velocity.setDy(-this.velocity.getDy());
        }
    }

    /**
     * Moves the ball one step outside two specified frames, checking for collisions
     * with the frames' boundaries and reversing its velocity if a collision occurs.
     *
     * @param frame1BottomLeft the bottom-left point of the first frame
     * @param frame1TopRight the top-right point of the first frame
     * @param frame2BottomLeft the bottom-left point of the second frame
     * @param frame2TopRight the top-right point of the second frame
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public void moveOneStepOutOfTwoFrames(Point frame1BottomLeft, Point frame1TopRight,
                                          Point frame2BottomLeft, Point frame2TopRight,
                                          int screenWidth, int screenHeight) {
        // Move the center point according to the velocity
        this.center = this.velocity.applyToPoint(this.center);

        // Check if the ball hits the boundaries of the screen and reverse its velocity if it does
        if (this.center.getX() - this.radius <= 0 || this.center.getX() + this.radius >= screenWidth) {
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (this.center.getY() - this.radius <= 0 || this.center.getY() + this.radius >= screenHeight) {
            this.velocity.setDy(-this.velocity.getDy());
        }

        // Define the boundaries of the two frames
        double frame1MinX = frame1BottomLeft.getX();
        double frame1MinY = frame1BottomLeft.getY();
        double frame1MaxX = frame1TopRight.getX();
        double frame1MaxY = frame1TopRight.getY();

        double frame2MinX = frame2BottomLeft.getX();
        double frame2MinY = frame2BottomLeft.getY();
        double frame2MaxX = frame2TopRight.getX();
        double frame2MaxY = frame2TopRight.getY();

        // Check if the ball is in either of the frames
        if (isInFrame(this.center, this.radius, frame1MinX, frame1MaxX, frame1MinY, frame1MaxY) ||
                isInFrame(this.center, this.radius, frame2MinX, frame2MaxX, frame2MinY, frame2MaxY)) {

            // Handle collisions with frame boundaries
            if (isInFrame(this.center, this.radius, frame1MinX, frame1MaxX, frame1MinY, frame1MaxY)) {
                handleCollisionWithFrame(frame1MinX, frame1MaxX, frame1MinY, frame1MaxY);
            }
            if (isInFrame(this.center, this.radius, frame2MinX, frame2MaxX, frame2MinY, frame2MaxY)) {
                handleCollisionWithFrame(frame2MinX, frame2MaxX, frame2MinY, frame2MaxY);
            }
        }
    }

    /**
     * Handles collisions with the frame boundaries by reversing the ball's velocity.
     *
     * @param minX the minimum x-coordinate of the frame
     * @param maxX the maximum x-coordinate of the frame
     * @param minY the minimum y-coordinate of the frame
     * @param maxY the maximum y-coordinate of the frame
     */
    private void handleCollisionWithFrame(double minX, double maxX, double minY, double maxY) {
        if (this.center.getX() - this.radius <= minX || this.center.getX() + this.radius >= maxX) {
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (this.center.getY() - this.radius <= minY || this.center.getY() + this.radius >= maxY) {
            this.velocity.setDy(-this.velocity.getDy());
        }
    }

    /**
     * Checks if the ball is within the specified frame boundaries.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param minX the minimum x-coordinate of the frame
     * @param maxX the maximum x-coordinate of the frame
     * @param minY the minimum y-coordinate of the frame
     * @param maxY the maximum y-coordinate of the frame
     * @return true if the ball is within the frame boundaries, false otherwise
     */
    private boolean isInFrame(Point center, int radius, double minX, double maxX, double minY, double maxY) {
        return (center.getX() - radius <= maxX && center.getX() + radius >= minX &&
                center.getY() - radius <= maxY && center.getY() + radius >= minY);
    }
}
