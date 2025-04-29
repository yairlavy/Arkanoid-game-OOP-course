import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * @author Yair Lavy 322214073
 * This class represents an abstract art drawing, which can draw random circles and lines on a GUI surface.
 * It provides methods to draw random circles and lines, and a main method to run the example.
 *
 */
public class AbstractArtDrawing {
    /**
     * Draws 10 random lines on a GUI surface.
     * The lines are drawn with random positions, and their midpoints and intersection points are highlighted.
     */
    public void drawRandomLines() {
        Line[] lines = new Line[10];
        Random rand = new Random(); // create a random-number generator

        // Create a window with the title "Random lines Example" which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random lines Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        d.setColor(Color.BLACK);

        // Draw 10 random lines and their midpoints.
        for (int i = 0; i < 10; ++i) {
            double x1 = rand.nextInt(400) + 1;
            double y1 = rand.nextInt(300) + 1;
            double x2 = rand.nextInt(400) + 1;
            double y2 = rand.nextInt(300) + 1;
            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
            d.drawLine((int) lines[i].start().getX(), (int) lines[i].start().getY(),
                    (int) lines[i].end().getX(), (int) lines[i].end().getY());
            Point mid = lines[i].middle();
            d.setColor(Color.BLUE);
            d.fillCircle((int) mid.getX(), (int) mid.getY(), 3);
            d.setColor(Color.BLACK);
        }

        // Highlight the intersection points of the lines.
        d.setColor(Color.RED);
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                Point p = lines[i].intersectionWith(lines[j]);
                if (p != null) {
                    d.fillCircle((int) p.getX(), (int) p.getY(), 3);
                }
            }
        }

        // Draw triangles formed by intersection points.
        d.setColor(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                for (int k = j + 1; k < 10; k++) {
                    Point p1 = lines[i].intersectionWith(lines[j]);
                    Point p2 = lines[j].intersectionWith(lines[k]);
                    Point p3 = lines[k].intersectionWith(lines[i]);
                    if (p1 != null && p2 != null && p3 != null) {
                        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
                        d.drawLine((int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());
                        d.drawLine((int) p3.getX(), (int) p3.getY(), (int) p1.getX(), (int) p1.getY());
                    }
                }
            }
        }

        gui.show(d);
    }

    /**
     * The main method to run the abstract art drawing example.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}
