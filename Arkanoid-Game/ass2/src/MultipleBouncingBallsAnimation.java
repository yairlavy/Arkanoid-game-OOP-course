import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 * This class represents an animation of multiple bouncing balls.
 * The size and speed of the balls are determined by the input arguments.
 *
 * @auther Yair Lavy 322214073
 */
public class MultipleBouncingBallsAnimation {

    /**
     * Draws the animation of bouncing balls.
     *
     * @param arr an array of integers representing the sizes of the balls
     */
    static private void drawAnimation(int[] arr) {
        GUI gui = new GUI("Bouncing Balls Animation", 500, 500);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();
        int maxAngle = 360;
        int minAngle = 0;
        Ball[] balls = new Ball[arr.length];

        // Initialize the balls with random positions, colors, and velocities
        for (int i = 0; i < arr.length; i++) {
            int x = rand.nextInt(gui.getDrawSurface().getWidth() - 2 * arr[i]) + arr[i];
            int y = rand.nextInt(gui.getDrawSurface().getHeight() - 2 * arr[i]) + arr[i];
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            balls[i] = new Ball(new Point(x, y), arr[i], randomColor, new Frame(new Point(0, 0), 500, 500));
            double speed=0;
            if (arr[i]>=5){
                 speed = Math.max(1, 50.0 / arr[i]); // Larger balls move slower
            }
            else{
                speed = Math.max(1, 30.0 / arr[i]); // Larger balls move slower
            }
            double angle = rand.nextInt(maxAngle - minAngle + 1) + minAngle;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            balls[i].setVelocity(v);
        }

        // Main animation loop
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(10); // Wait for 10 milliseconds
        }
    }

    /**
     * The main method to run the animation. Takes the sizes of the balls as command-line arguments.
     *
     * @param args command-line arguments representing the sizes of the balls
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        int maxSize = 75;
        for (int i = 0; i < args.length ; i++) {
            int x = Math.abs(Integer.parseInt(args[i]));
            if (x > maxSize) {
                System.out.println("Input size " + args[i] + " is larger than the maximum allowed size of " + maxSize);
                return;
            }
        }
        int[] sizes = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            sizes[i] = Math.abs(Integer.parseInt(args[i]));
        }

        drawAnimation(sizes);
    }
}
