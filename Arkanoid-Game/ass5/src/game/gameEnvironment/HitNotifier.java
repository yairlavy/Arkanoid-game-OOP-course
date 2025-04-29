package game.gameEnvironment;

/**
 * @author Yair Lavy 322214073
 * An interface for objects that can notify listeners about hit events.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener we add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener we remove
     */
    void removeHitListener(HitListener hl);
}
