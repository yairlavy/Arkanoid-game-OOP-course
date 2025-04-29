package game.shapes;

import biuoop.DrawSurface;
import game.Collision.Collidable;
import game.Sprite.Sprite;
import game.gameEnvironment.Game;
import game.gameEnvironment.HitListener;
import game.gameEnvironment.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block in the game, which is both a collidable and a sprite.
 * It has a rectangle shape and a color, and can be drawn on a surface and interact with collisions.
 * @author Yair Lavy 322214073
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // Fields
    private final Rectangle rectangle;
    private final Color color;
    private final List<HitListener> hitListeners;
    private boolean shouldBeRemoved;
    private Game game; // Added reference to the game
    private boolean border = false;

    /**
     * Constructs a Block with the specified rectangle and a default black color.
     *
     * @param rectangle the shape of the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.color = Color.black;
        this.hitListeners = new ArrayList<>();
        this.shouldBeRemoved = false;
    }

    /**
     * Constructs a Block with the specified rectangle and color.
     *
     * @param rectangle the shape of the block
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.shouldBeRemoved = false;
    }

    public boolean shouldBeRemoved() {
        return shouldBeRemoved;
    }

    public void setShouldBeRemoved(boolean shouldBeRemoved) {
        this.shouldBeRemoved = shouldBeRemoved;
    }

    /**
     * Returns the rectangle of the block.
     *
     * @return the rectangle of the block
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public boolean getBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public boolean isBorder() {
        return border;
    }

    /**
     * Returns the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        Line right = rectangle.getRightLine();
        Line left = rectangle.getLeftline();
        Line upper = rectangle.getUpperLine();
        Line bottom = rectangle.getBottomLine();

        boolean hitVertical = right.isPointOnLine(collisionPoint, right)
                || left.isPointOnLine(collisionPoint, left);
        boolean hitHorizontal = upper.isPointOnLine(collisionPoint, upper)
                || bottom.isPointOnLine(collisionPoint, bottom);

        // Check if the collision is on a corner
        boolean hitCorner = (right.isPointOnLine(collisionPoint, right)
                && (upper.isPointOnLine(collisionPoint, upper)
                || bottom.isPointOnLine(collisionPoint, bottom)))
                || (left.isPointOnLine(collisionPoint, left)
                && (upper.isPointOnLine(collisionPoint, upper)
                || bottom.isPointOnLine(collisionPoint, bottom)));

        if (!ballColorMatch(hitter)) {
            this.setShouldBeRemoved(true); // Set the block to be removed
            this.notifyHit(hitter);
        }

        if (hitCorner) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }

        if (hitVertical) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        if (hitHorizontal) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        double xUpperLeft = rectangle.getUpperLeft().getX();
        double yUpperLeft = rectangle.getUpperLeft().getY();
        double width = rectangle.getWidth();
        double height = rectangle.getHeight();
        d.setColor(this.color);
        d.fillRectangle((int) xUpperLeft, (int) yUpperLeft, (int) width, (int) height);
        if (this.border) {
            d.setColor(this.color);
        } else {
            d.setColor(Color.BLACK);
        }
        d.drawRectangle((int) xUpperLeft, (int) yUpperLeft, (int) width, (int) height);
    }

    @Override
    public void timePassed() {
        if (this.shouldBeRemoved && !this.isBorder()) {
            // Check if the block is marked to be removed and is not a border
            removeFromGame(game);
        }
    }

    @Override
    public void addToGame(Game game) {
        this.game = game; // Keep reference to the game
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Removes this block from the game.
     *
     * @param game the game
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Checks if the color of the block matches the color of the ball.
     *
     * @param ball the ball to check
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
