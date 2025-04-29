package game.gameEnvironment;

import game.Collision.Collidable;
import game.Collision.CollisionInfo;
import game.shapes.Line;
import game.shapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class manages a collection of collidable objects.
 * It provides methods to add collidables and to find the closest collision
 * that a moving object will encounter.
 *
 * @author Yair Lavy 322214073
 */
public class GameEnvironment {
    private final List<Collidable> collidableList;

    /**
     * Constructs an empty GameEnvironment.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * Returns the list of collidables in the environment.
     *
     * @return the list of collidables.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Finds the closest collision that will occur for an object moving along the given trajectory.
     * If no collision will occur, returns null.
     *
     * @param trajectory the path along which the object is moving.
     * @return the information about the closest collision, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDistance = Double.MAX_VALUE;
        CollisionInfo closestCollision = null;

        for (Collidable collidable : collidableList) {
            Point collisionPoint =
                    trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint != null) {
                double distance = trajectory.start().distance(collisionPoint);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCollision = new CollisionInfo(collisionPoint, collidable);
                }
            }
        }

        return closestCollision;
    }
}
