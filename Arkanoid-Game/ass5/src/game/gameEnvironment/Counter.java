package game.gameEnvironment;

/**
 * The Counter class is used to count occurrences or keep track of a numerical value.
 * It provides methods to increase, decrease, and get the current count.
 *
 * @author Yair Lavy 322214073
 */
public class Counter {
    private int count;

    /**
     * Constructs a Counter instance with the initial count set to 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Constructs a Counter instance with the initial count set to the specified number.
     *
     * @param num the initial count
     */
    public Counter(int num) {
        this.count = num;
    }

    /**
     * Adds a specified number to the count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtracts a specified number from the count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the count
     */
    public int getValue() {
        return this.count;
    }
}
