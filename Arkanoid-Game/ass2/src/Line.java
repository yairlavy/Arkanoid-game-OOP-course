/**
 *  @author Yair Lavy 322214073
 * A line (actually a line-segment) connects two points -- a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    /** The start point of the line. */
    private Point start;

    /** The end point of the line. */
    private Point end;

    /** A small constant used for floating-point comparison. */
    private final double epsilon;

    /**
     * Constructs a line with the specified start and end points.
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        epsilon = Math.pow(10, -10);
    }

    /**
     * Constructs a line with the specified coordinates.
     * @param x1 the x coordinate of the start point
     * @param y1 the y coordinate of the start point
     * @param x2 the x coordinate of the end point
     * @param y2 the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        epsilon = Math.pow(10, -5);
    }

    /**
     * Checks if this line is actually a single point.
     * @return true if the start and end points are equal, false otherwise
     */
    private boolean thisIsPoint() {
        return this.start.equals(this.end);
    }

    /**
     * Checks if the specified line is actually a single point.
     * @param other the line to check
     * @return true if the start and end points of the other line are equal, false otherwise
     */
    private boolean otherIsPoint(Line other) {
        return other.start.equals(other.end);
    }

    /**
     * Checks if this line is parallel to the y-axis.
     * @return true if the line is parallel to the y-axis, false otherwise
     */
    private boolean parallelToY() {
        if (this.start != null) {
            return this.start.getX() == this.end.getX();
        }
        return false;
    }

    /**
     * Checks if this line is parallel to the x-axis.
     * @return true if the line is parallel to the x-axis, false otherwise
     */
    private boolean parallelToX() {
        if (this.start != null) {
            return this.start.getY() == this.end.getY();
        }
        return false;
    }

    /**
     * Returns the length of the line
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the slope of the line.
     * @return the slope of the line
     */
    public double slope() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * Returns the middle point of the line.
     * @return the middle point of the line
     */
    public Point middle() {
        double x0 = (this.start.getX() + this.end.getX()) / 2;
        double y0 = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x0, y0);
    }

    /**
     * Checks if this line is equal to another line.
     * Two lines are considered equal if they have the same length.
     * @param other the other line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean lineEquals(Line other) {
        return this.length() == other.length();
    }

    /**
     * Checks if this line is equal to another line.
     * Two lines are considered equal if their start and end points are equal.
     * @param other the other line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end)) ||
                (this.end.equals(other.start) && this.start.equals(other.end));
    }

    /**
     * Returns the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if this line intersects with another line.
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point intersectionPoint = this.intersectionWith(other);
        return intersectionPoint != null;
    }

    /**
     * Checks if the intersection of this line and another line occurs at a single point.
     * @param other the other line to check
     * @return true if the intersection occurs at a single point, false otherwise
     */
    private boolean isIntersectionInOnePoint(Line other) {
        Point midThis = this.middle();
        Point midOther = other.middle();
        double diff = midThis.distance(this.start) + midOther.distance(other.start) - midThis.distance(midOther);
        return Math.abs(diff) < epsilon;
    }

    /**
     * Checks if a point is within the range of two other points.
     * @param intersectPoint the point to check
     * @param begin the beginning point of the range
     * @param finish the ending point of the range
     * @return true if the point is within the range, false otherwise
     */
    public boolean isInRange(Point intersectPoint, Point begin, Point finish) {
        double xMax = Math.max(begin.getX(), finish.getX());
        double yMax = Math.max(begin.getY(), finish.getY());
        double xMin = Math.min(begin.getX(), finish.getX());
        double yMin = Math.min(begin.getY(), finish.getY());
        double intersectX = intersectPoint.getX();
        double intersectY = intersectPoint.getY();
        return intersectX >= xMin && intersectX <= xMax && intersectY >= yMin && intersectY <= yMax;
    }

    /**
     * Returns the incline (slope) of the line.
     * @return the incline of the line
     */
    private double getIncline() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * Returns the y-intercept of the line.
     * @return the y value of the intercept point
     */
    private double getYIntercept() {
        return this.start.getY() - getIncline() * this.start.getX();
    }

    /**
     * Compares two double values with a small tolerance to account for floating-point inaccuracies.
     * @param x the first value to compare
     * @param y the second value to compare
     * @return true if the values are approximately equal, false otherwise
     */
    private boolean epsilonCmp(double x, double y) {
        return Math.abs(x - y) < epsilon;
    }

    /**
     * Finds the intersection point of this line with another line.
     * @param other the other line to find the intersection with
     * @return the point of intersection, or null if there is no intersection
     */
    private Point findIntersection(Line other) {
        if (this.start.equals(other.start()) || this.start.equals(other.end())) {
            return this.start;
        }
        return this.end;
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * @param other the other line to find the intersection with
     * @return the point of intersection, or null if there is no intersection
     */
    public Point intersectionWith(Line other) {
        // Case 1: Same line or one continues the other.
        if (this.equals(other)) {
            return null;
        }
        if (this.start.equals(other.end) || this.end.equals(other.start)) {
            return this.start.equals(other.end) ? this.start : this.end;
        }

        // Case 2: Both lines are parallel to x or y.
        if (parallelToY() && other.parallelToY()) {
            if (this.start.getX() != other.start.getX()) {
                return null;
            }
            if (isIntersectionInOnePoint(other)) {
                return findIntersection(other);
            }
        }
        if (parallelToX() && other.parallelToX()) {
            if (this.start.getY() != other.start.getY()) {
                return null;
            }
            if (isIntersectionInOnePoint(other)) {
                return findIntersection(other);
            }
        }

        if (parallelToY() || other.parallelToY()) {
            Line ver = parallelToY() ? this : other;
            Line notVertical = parallelToY() ? other : this;
            double minXNotVert = Math.min(notVertical.end.getX(), notVertical.start.getX());
            double maxXNotVert = Math.max(notVertical.end.getX(), notVertical.start.getX());
            if (ver.start.getX() >= minXNotVert && ver.start.getX() <= maxXNotVert) {
                double vsx = ver.start.getX();
                Point intersect = new Point(vsx, vsx * notVertical.getIncline() + notVertical.getYIntercept());
                if (isInRange(intersect, ver.start, ver.end)) {
                    return intersect;
                }
                return null;
            }
            return null;
        }

        // Calculate the inclines and the y-intercepts.
        double a1 = getIncline();
        double a2 = other.getIncline();
        double b1 = getYIntercept();
        double b2 = other.getYIntercept();

        // Case 3: Both lines are points.
        if (thisIsPoint() && otherIsPoint(other)) {
            if (this.start.equals(other.start)) {
                return new Point(other.start.getX(), other.end.getY());
            }
            return null;
        }

        // Case 4: This line is a point, the other is a line.
        if (thisIsPoint() && !otherIsPoint(other)) {
            if (epsilonCmp(this.start.getY(), a2 * this.start.getX() + b2)) {
                if (isInRange(this.start, other.start, other.end)) {
                    return new Point(this.start.getX(), this.start.getY());
                }
            }
        }

        // Case 5: The other line is a point, this line is a line.
        if (otherIsPoint(other) && !thisIsPoint()) {
            if (epsilonCmp(other.start.getY(), a1 * other.start.getX() + b1)) {
                if (isInRange(other.start(), this.start, this.end)) {
                    return new Point(other.start.getX(), other.start.getY());
                }
            }
        }

        // Case 6: General case.
        if (a1 != a2) {
            double intersectX = (b2 - b1) / (a1 - a2);
            double intersectY = a1 * intersectX + b1;
            Point intersectPoint = new Point(intersectX, intersectY);
            if (isInRange(intersectPoint, other.start(), other.end()) &&
                    isInRange(intersectPoint, this.start, this.end)) {
                return intersectPoint;
            }
            return null;
        }

        // Case 7: Lines are parallel but not coincident.
        if (a1 == a2) {
            if (b1 != b2) {
                return null;
            } else if (b1 == b2) {
                if (isIntersectionInOnePoint(other)) {
                    return findIntersection(other);
                }
            }
        }
        return null;
    }

    /**
     * Checks if this line intersects with two other lines.
     * @param other1 the first other line to check for intersection
     * @param other2 the second other line to check for intersection
     * @return true if this line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        boolean a = this.isIntersecting(other1);
        boolean b = this.isIntersecting(other2);
        return a && b;
    }
}
