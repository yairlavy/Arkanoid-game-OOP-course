package game.shapes;

import biuoop.DrawSurface;
import game.Sprite.Sprite;
import game.gameEnvironment.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle in the game, which is also a sprite.
 * It has four vertices and four sides, and can be drawn on a surface.
 * @author Yair Lavy 322214073
 */
public class Rectangle implements Sprite {
    // Fields - 4 vertices and 4 sides of the rectangle
    private final Point upperLeft;
    private final Point upperRight;
    private final Point bottomRight;
    private final Point bottomLeft;
    private final Line upperLine;
    private final Line bottomLine;
    private final Line leftline;
    private final Line rightLine;
    private final double width;
    private final double height;

    /**
     * Creates a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperLine = new Line(upperLeft, upperRight);
        this.bottomLine = new Line(bottomLeft, bottomRight);
        this.leftline = new Line(upperLeft, bottomLeft);
        this.rightLine = new Line(bottomRight, upperRight);
    }

    /**
     * Creates an array of the lines that make up the rectangle.
     *
     * @return an array of the lines that make up the rectangle
     */
    public Line[] createRecArray() {
        Line[] recArr = new Line[4];
        recArr[0] = this.upperLine; // upper line
        recArr[1] = this.bottomLine; // bottom line
        recArr[2] = this.leftline; // left line
        recArr[3] = this.rightLine; // right line
        return recArr;
    }

    /**
     * Finds the intersection points of a line with this rectangle.
     *
     * @param line the line to check for intersection points
     * @return a list of the intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        Line[] recArr = createRecArray();
        Point inter;
        List<Point> pointList = new ArrayList<>();
        for (Line value : recArr) {
            inter = value.intersectionWith(line);
            if (inter != null) {
                pointList.add(inter);
            }
        }
        return pointList;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper left point of the rectangle.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper right point of the rectangle.
     *
     * @return the upper right point of the rectangle
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Returns the bottom right point of the rectangle.
     *
     * @return the bottom right point of the rectangle
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }

    /**
     * Returns the bottom left point of the rectangle.
     *
     * @return the bottom left point of the rectangle
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * Returns the upper line of the rectangle.
     *
     * @return the upper line of the rectangle
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeftline() {
        return this.leftline;
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * Checks if this rectangle intersects with another rectangle.
     *
     * @param other the other rectangle to check for intersection
     * @return true if the rectangles intersect, false otherwise
     */
    public boolean isIntersecting(Rectangle other) {
        double thisLeft = this.upperLeft.getX();
        double thisRight = this.upperLeft.getX() + this.width;
        double thisTop = this.upperLeft.getY();
        double thisBottom = this.upperLeft.getY() + this.height;

        double otherLeft = other.getUpperLeft().getX();
        double otherRight = other.getUpperLeft().getX() + other.getWidth();
        double otherTop = other.getUpperLeft().getY();
        double otherBottom = other.getUpperLeft().getY() + other.getHeight();

        return (thisLeft < otherRight) && (thisRight > otherLeft) && (thisTop < otherBottom) && (thisBottom > otherTop);
    }

    /**
     * Adds this rectangle to the game as a sprite.
     *
     * @param game the game to add the rectangle to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.fillRectangle((int) bottomLeft.getX(), (int) bottomLeft.getY(),
                (int) (upperRight.getX() - bottomLeft.getX()), (int) (upperRight.getY() - bottomLeft.getY()));
        d.drawRectangle((int) bottomLeft.getX(), (int) bottomLeft.getY(),
                (int) (upperRight.getX() - bottomLeft.getX()), (int) (upperRight.getY() - bottomLeft.getY()));
    }

    @Override
    public void timePassed() {
        // This method can be used to update the rectangle's state if needed.
    }
}
