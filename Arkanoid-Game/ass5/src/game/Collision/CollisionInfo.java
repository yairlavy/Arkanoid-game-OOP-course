package game.Collision;

import game.shapes.Point;

/**
 * @author Yair Lavy 322214073
 * The CollisionInfo class stores information about a collision event,
 * including the collision point and the collidable object involved.
 */
public class CollisionInfo {
    // Fields
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs CollisionInfo object with the specified collision point and object.
     *
     * @param collisionPoint  the point at which the collision occurs.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Sets the collidable object involved in the collision.
     *
     * @param collisionObject the new collidable object.
     */
    public void setCollidableObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }

    /**
     * Sets the point at which the collision occurs.
     *
     * @param collisionPoint the new collision point.
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }
}
