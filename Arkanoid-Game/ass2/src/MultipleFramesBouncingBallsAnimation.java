import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.*;
import java.util.Random;

/**
*@auther Yair Lavy 322214073
 * This class represents an animation of multiple bouncing balls within two frames.
 * The sizes and speeds of the balls are determined by the input arguments.
 * The balls can move inside or outside the frames, with collisions handled accordingly.

 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * Generates a valid random point within the system boundaries and outside the specified frames.
     *
     * @param systemStart the starting point of the system
     * @param systemEnd the ending point of the system
     * @param frame1 the first frame
     * @param frame2 the second frame
     * @param diameter the diameter of the ball
     * @return a valid random point within the system boundaries and outside the frames
     */
    public static Point getValidPoint(Point systemStart, Point systemEnd, Frame frame1, Frame frame2, int diameter) {
        Random random = new Random();
        int radius = diameter / 2;
        int systemWidth = (int) (systemEnd.getX() - systemStart.getX());
        int systemHeight = (int) (systemEnd.getY() - systemStart.getY());

        while (true) {
            // Generate random point within the system boundaries considering the radius
            double x = systemStart.getX() + radius + random.nextInt(systemWidth - diameter);
            double y = systemStart.getY() + radius + random.nextInt(systemHeight - diameter);
            Point randomPoint = new Point(x, y);

            if (isValidPoint(randomPoint, systemStart, systemEnd, frame1, frame2, radius)) {
                return randomPoint;
            }
        }
    }

    /**
     * Checks if a point is within a specified frame considering the radius.
     *
     * @param point the point to check
     * @param frame the frame to check against
     * @param radius the radius to consider
     * @return true if the point is within the frame, false otherwise
     */
    private static boolean isPointInFrame(Point point, Frame frame, int radius) {
        double x = point.getX();
        double y = point.getY();
        double frameX1 = frame.getPointStart().getX() - radius;
        double frameY1 = frame.getPointStart().getY() - radius;
        double frameX2 = frame.getPointStart().getX() + frame.getLength() + radius;
        double frameY2 = frame.getPointStart().getY() + frame.getWidth() + radius;

        return (x > frameX1 && x < frameX2 && y > frameY1 && y < frameY2);
    }

    /**
     * Checks if a point is valid within the system boundaries and outside the specified frames.
     *
     * @param point the point to check
     * @param systemStart the starting point of the system
     * @param systemEnd the ending point of the system
     * @param frame1 the first frame
     * @param frame2 the second frame
     * @param radius the radius to consider
     * @return true if the point is valid, false otherwise
     */
    private static boolean isValidPoint(Point point, Point systemStart, Point systemEnd, Frame frame1, Frame frame2, int radius) {
        double x = point.getX();
        double y = point.getY();

        // Check if point is within system boundaries considering the radius
        if (x - radius < systemStart.getX() || y - radius < systemStart.getY() ||
                x + radius > systemEnd.getX() || y + radius > systemEnd.getY()) {
            return false;
        }

        // Check if point is not within any frame
        return !isPointInFrame(point, frame1, radius) && !isPointInFrame(point, frame2, radius);
    }

    /**
     * Draws the animation of bouncing balls within two frames.
     *
     * @param inSizes an array of integers representing the sizes of the balls inside the frames
     * @param outSizes an array of integers representing the sizes of the balls outside the frames
     */
    static private void drawAnimationFrames(int[] inSizes, int[] outSizes) {
        int width=700,heigth=600;
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", width, heigth);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();

        Point pointDLGray = new Point(50, 50);
        Point pointURGray = new Point(500, 500);
        Point pointDLYellow = new Point(450, 450);
        Point pointURYellow = new Point(600, 600);
        Frame gray = new Frame(pointDLGray, 450, 450);
        Frame yellow = new Frame(pointDLYellow, 150, 150);
        Ball[] ballsIn = new Ball[inSizes.length];
        Ball[] ballsOut = new Ball[outSizes.length];
        int maxAngle = 360;
        int minAngle = 0;

        // Initialize balls inside the frames
        for (int i = 0; i < inSizes.length; i++) {
            int x = 51 + inSizes[i] + rand.nextInt(449 - 2 * inSizes[i]);
            int y = 51 + inSizes[i] + rand.nextInt(449 - 2 * inSizes[i]);
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            ballsIn[i] = new Ball(new Point(x, y), inSizes[i], randomColor, gray);
            double speed=0;
            if (inSizes[i]>=5){
                speed = Math.max(1, 50.0 / inSizes[i]); // Larger balls move slower
            }
            else{
                speed = Math.max(1, 20.0 / inSizes[i]); // Larger balls move slower
            }
            double angle = rand.nextInt(maxAngle - minAngle + 1) + minAngle;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ballsIn[i].setVelocity(v);
        }

        // Initialize balls outside the frames
        for (int i = 0; i < outSizes.length; i++) {
            Point p = getValidPoint(new Point(0, 0), new Point(600, 600), gray, yellow, outSizes[i] * 2);
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            ballsOut[i] = new Ball(p, outSizes[i], randomColor, new Frame(new Point(0, 0), heigth, width));
            double speed=0;
            if (outSizes[i]>=5){
                speed = Math.max(1, 50.0 / outSizes[i]); // Larger balls move slower
            }
            else{
                speed = Math.max(1, 20.0 / outSizes[i]); // Larger balls move slower
            }
            double angle = rand.nextInt(maxAngle - minAngle + 1) + minAngle;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ballsOut[i].setVelocity(v);
        }

        // Main animation loop
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // Draw gray rectangle
            d.setColor(Color.GRAY);
            d.fillRectangle((int) pointDLGray.getX(), (int) pointDLGray.getY(),
                    (int) (pointURGray.getX() - pointDLGray.getX()), (int) (pointURGray.getY() - pointDLGray.getY()));
            d.drawRectangle((int) pointDLGray.getX(), (int) pointDLGray.getY(),
                    (int) (pointURGray.getX() - pointDLGray.getX()), (int) (pointURGray.getY() - pointDLGray.getY()));


            // Move and draw balls inside the frames
            for (Ball ball : ballsIn) {
                ball.moveOneStep();
                ball.drawOn(d);
            }

            // Move and draw balls outside the frames
            for (Ball ball : ballsOut) {
                ball.moveOneStepOutOfTwoFrames(pointDLGray, pointURGray, pointDLYellow, pointURYellow, 700, 600);
                ball.drawOn(d);
            }
            // Draw yellow rectangle
            d.setColor(Color.YELLOW);
            d.fillRectangle((int) pointDLYellow.getX(), (int) pointDLYellow.getY(),
                    (int) (pointURYellow.getX() - pointDLYellow.getX()), (int) (pointURYellow.getY() - pointDLYellow.getY()));
            d.drawRectangle((int) pointDLYellow.getX(), (int) pointDLYellow.getY(),
                    (int) (pointURYellow.getX() - pointDLYellow.getX()), (int) (pointURYellow.getY() - pointDLYellow.getY()));


            gui.show(d);
            sleeper.sleepFor(10); // Wait for 50 milliseconds
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
        int totalArgs = args.length;
        int numBalls = (totalArgs + 1) / 2;
        int[] inSizes = new int[numBalls];
        int[] outSizes = new int[numBalls];

        int inSizesCount = totalArgs / 2;
        int outSizesCount = totalArgs - inSizesCount;
        for (int i = 0; i < args.length; i++) {
            int x = Math.abs(Integer.parseInt(args[i]));
            if (x > maxSize) {
                System.out.println("Input size " + inSizes[i] + " is larger than the maximum allowed size of " + maxSize);
                return;
            }
        }

        for (int i = 0; i < inSizesCount; i++) {
            inSizes[i] = Math.abs(Integer.parseInt(args[i]));
        }
        for (int i = 0; i < outSizesCount; i++) {
            outSizes[i] = Math.abs(Integer.parseInt(args[inSizesCount + i]));
        }

        drawAnimationFrames(inSizes, outSizes);
    }
}
