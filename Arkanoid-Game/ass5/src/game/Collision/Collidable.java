package game.Collision;

import game.shapes.Ball;
import game.shapes.Point;
import game.shapes.Rectangle;
import game.shapes.Velocity;

/**
 * @author Yair Lavy 322214073
 * The Collidable interface represents objects that can be collided with
 * within a game. Implementing classes should define the shape of the
 * object for collision detection and how the object reacts to collisions.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     *
     * @return the Rectangle representing the collision shape of the object.
     */
    default Rectangle getCollisionRectangle() {
        return null;
    }

    /**
     * Notifies the object that it has been collided with at the specified
     * collision point with the given velocity. The method returns the new
     * velocity expected after the hit, based on the force the object inflicted.
     *
     * @param collisionPoint the point at which the collision occurred.
     * @param currentVelocity the velocity of the object at the time of collision.
     * @return the new game.gui.game.gui.shapes.shapes.Velocity expected after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
