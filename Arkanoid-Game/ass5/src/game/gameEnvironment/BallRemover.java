package game.gameEnvironment;

import game.shapes.Ball;
import game.shapes.Block;

/**
 * BallRemover is responsible for removing balls from the game and keeping count
 * of the remaining balls.
 *
 * @author Yair Lavy 322214073
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructs a BallRemover instance.
     *
     * @param game the game
     * @param remainingBalls the counter for the remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
        hitter.setColor(beingHit.getColor());
    }
}
