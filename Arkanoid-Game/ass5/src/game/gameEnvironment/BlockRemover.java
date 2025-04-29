package game.gameEnvironment;

import game.shapes.Ball;
import game.shapes.Block;

/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Yair Lavy 322214073
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructs a BlockRemover instance.
     *
     * @param game the game
     * @param remainingBlocks the counter for the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game. Remember to remove this listener
     * from the block that is being removed from the game.
     *
     * @param beingHit the block that is hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.getBorder()) { // Check if the block is not a border
            hitter.setColor(beingHit.getColor()); // Change the ball color to the block color
            beingHit.setShouldBeRemoved(true); // Mark the block to be removed
            beingHit.removeFromGame(game);
            this.remainingBlocks.decrease(1);
        }
    }
}
