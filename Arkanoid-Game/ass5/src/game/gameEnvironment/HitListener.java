package game.gameEnvironment;

import game.shapes.Ball;
import game.shapes.Block;

/**
 * @author Yair Lavy 322214073
 *  An interface for objects that want to be notified of hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
