package game.shapes;

/**
 * Represents a frame with a starting point, length, and width.
 * Provides methods to retrieve the frame's dimensions and starting point.
 * @auther Yair Lavy 322214073
 */
public class Frame {
    /** The starting point of the frame. */
    private final Point pointStart;
    /** The length of the frame. */
    private final int length;
    /** The width of the frame. */
    private final int width;

    /**
     * Constructs a frame with the specified starting point, length, and width.
     *
     * @param pointStart the starting point of the frame
     * @param length the length of the frame
     * @param width the width of the frame
     */
    public Frame(Point pointStart, int length, int width) {
        this.pointStart = pointStart;
        this.length = length;
        this.width = width;
    }

    /**
     * Returns the length of the frame.
     *
     * @return the length of the frame
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Returns the width of the frame.
     *
     * @return the width of the frame
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the starting point of the frame.
     *
     * @return the starting point of the frame
     */
    public Point getPointStart() {
        return this.pointStart;
    }
}
