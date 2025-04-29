package game.Sprite;

import biuoop.DrawSurface;
import game.gameEnvironment.Game;

/**
 * @author Yair Lavy 322214073
 * The game.gui.game.gui.shapes.shapes.Sprite.game.gui.game.gui.shapes.shapes.Sprite interface represents an object that can be drawn on the screen
 * and can be updated over time. Sprites can be added to a game.
 */
public interface Sprite {
    /**
     * Draws the sprite to the screen.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();

    /**
     * Adds the sprite to the game.
     *
     * @param game the game to add the sprite to
     */
    void addToGame(Game game);
}
