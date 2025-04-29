package game.Sprite;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Yair Lavy 322214073
 * The SpriteCollection class manages a collection of sprites.
 * It provides methods to add, remove, and update sprites.
 */
public class SpriteCollection {
    private final List<Sprite> collectionOfSprite;

    /**
     * Constructs a SpriteCollection with the specified list of sprites.
     *
     * @param collectionOfSprite the initial list of sprites
     */
    public SpriteCollection(List<Sprite> collectionOfSprite) {
        this.collectionOfSprite = collectionOfSprite;
    }

    /**
     * Constructs an SpriteCollection.
     */
    public SpriteCollection() {
        this.collectionOfSprite = new ArrayList<>();
    }

    /**
     * Returns the list of sprites in the collection.
     *
     * @return the list of sprites
     */
    public List<Sprite> getCollectionOfSprite() {
        return collectionOfSprite;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.collectionOfSprite.add(s);
    }

    /**
     * Deletes a sprite from the collection.
     *
     * @param s the sprite to delete
     */
    public void deleteSprite(Sprite s) {
        this.collectionOfSprite.remove(s);
    }

    /**
     * Notifies all sprites that time has passed by calling their timePassed() method.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.collectionOfSprite);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : collectionOfSprite) {
            sprite.drawOn(d);
        }
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.collectionOfSprite.remove(s);
    }
}
