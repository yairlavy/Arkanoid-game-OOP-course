/**
 * @author Yair Lavy 322214073
 * This class represents a point in a two-dimensional space.
 * It provides methods to calculate the distance to another point,
 * check equality with another point, and get or set the x and y coordinates.
 */
public class Point {
    /** The x coordinate of the point. */
    private double x;

    /** The y coordinate of the point. */
    private double y;

    /**
     * Constructs a point with the specified x and y coordinates.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     * @param other the other point to which the distance is calculated
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks if this point is equal to another point.
     * Two points are considered equal if their x and y coordinates are both equal.
     * @param other the other point to compare with
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Gets the x coordinate of this point.
     * @return the x coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y coordinate of this point.
     * @return the y coordinate of this point
     */
    public double getY() {
        return y;
    }
    /**
     * Sets the x coordinate of this point.
     * @param x the new x coordinate of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of this point.
     * @param y the new y coordinate of this point
     */
    public void setY(double y) {
        this.y = y;
    }
}
