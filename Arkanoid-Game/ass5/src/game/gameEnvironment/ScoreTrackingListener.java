package game.gameEnvironment;

import game.shapes.Ball;
import game.shapes.Block;

/**
 * @author Yair Lavy 322214073
 * class of Score Tracking Listener that implement game.gui.game.gui.shapes.shapes.gameEnvironment.HitListener.
 */
public class ScoreTrackingListener implements HitListener {
    // fields
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter - the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * getter.
     *
     * @return - game.gui.game.gui.shapes.shapes.gameEnvironment.Counter
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * setter of the new score.
     *
     * @param newCounter - the new score.
     */
    public void setCurrentScore(Counter newCounter) {
        this.currentScore = newCounter;
    }

    /**
     * hit, so increase the score for 5.
     *
     * @param beingHit - the block.
     * @param hitter   - the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}