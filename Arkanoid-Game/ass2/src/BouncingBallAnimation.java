import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author Yair Lavy 322214073
 * This class represents a bouncing ball animation.
 * It provides a method to animate a ball bouncing within a 200x200 pixel window.
 **/
public class BouncingBallAnimation {
    /**
     * Draws and animates a ball bouncing within a 200x200 pixel window.
     * The ball starts at the specified starting point and moves with the specified velocity.
     *
     * @param start the starting point of the ball
     * @param dx the change in x-coordinate (not used in the current implementation)
     * @param dy the change in y-coordinate (not used in the current implementation)
     */
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(new Point(start.getX(), start.getY()), 30, Color.RED,new Frame(new Point(0,0),200,200));
        Velocity v = Velocity.fromAngleAndSpeed(90, 2);
        ball.setVelocity(v);

        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds
        }
    }

    /**
     * The main method to run the bouncing ball animation.
     * It starts the animation with the ball starting at (100, 100) and moving with a specified velocity.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        drawAnimation(new Point(100, 100), 100, 100);
    }
}
