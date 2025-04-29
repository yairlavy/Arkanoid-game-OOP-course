/**
 * @author Yair Lavy 322214073
 * The Velocity class specifies the change in position on the x and y axes.
 * It provides methods to get and set the velocity components,
 * apply the velocity to a point, and create a velocity from an angle and speed.
 *
 */
public class Velocity {
    /** The change in position on the x-axis. */
    private double dx;

    /** The change in position on the y-axis. */
    private double dy;

    /**
     * Constructs a Velocity with the specified changes in position on the x and y axes.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the change in position on the x-axis.
     *
     * @return the change in position on the x-axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in position on the y-axis.
     *
     * @return the change in position on the y-axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the change in position on the x-axis.
     *
     * @param dx the new change in position on the x-axis
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the change in position on the y-axis.
     *
     * @param dy the new change in position on the y-axis
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Applies the velocity to a point, returning a new point with the updated position.
     * The new position is calculated as (x + dx, y + dy).
     *
     * @param p the point to which the velocity is applied
     * @return a new point with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Creates a velocity from an angle and speed.
     * The angle is specified in degrees, and the speed is the magnitude of the velocity.
     *
     * @param angle the angle in degrees
     * @param speed the speed (magnitude) of the velocity
     * @return a new velocity with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
