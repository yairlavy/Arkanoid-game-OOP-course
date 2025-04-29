package game.shapes;

import biuoop.DrawSurface;
import game.Sprite.Sprite;
import game.gameEnvironment.Game;


import java.awt.Color;

/**
 * @author Yair Lavy 322214073
 * The Background class represents the background of the game,
 * implementing the Sprite interface to be drawn on the game surface.
 */
public class Background implements Sprite {
    private final Color color;

    /**
     * Constructs a game.gui.game.gui.shapes.shapes.Background with the specified color.
     *
     * @param color the color of the background.
     */
    public Background(Color color) {
        this.color = color;
    }

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the background on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    /**
     * This method is called to notify the sprite that time has passed.
     * For Background, no action is needed.
     */
    @Override
    public void timePassed() {
        // No need for any action
    }

    /**
     * Adds the background to the specified game.
     *
     * @param game the game to add the background to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
