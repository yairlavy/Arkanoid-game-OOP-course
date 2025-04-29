/*************.
 * Yair Lavy 322214073
 * Assignment 1
 *************/
/**
 * A class to count pairs of integers in an array that sum to less than a specified target.
 */
public class CountPairs {
    /**
     * This method counts pairs of numbers in the given array that sum to less than the specified target.
     * @param numbers An array of integers to find pairs in.
     * @param target The target sum, where pairs must sum to less than this value.
     * @return The count of such pairs.
     */
    private static int countPairs(int[] numbers, int target) {
        int c = 0; // Initialize a counter to store the number of valid pairs.

        // Iterate over all elements in the array.
        for (int i = 0; i < numbers.length; i++) {
            // Nested loop to compare the current element with subsequent elements in the array.
            for (int j = i + 1; j < numbers.length; j++) {
                // Check if the sum of the current pair is less than the target.
                if (numbers[i] + numbers[j] < target) {
                    c++; // If true, increment the counter.
                }
            }
        }
        return c; // Return the total count of valid pairs.
    }

    /**
     * The main method, serving as the entry point for the program.
     * It expects command line arguments where the last argument is the target sum and the others are array elements.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        // Check if there are at least three command line arguments.
        if (args.length < 3) {
            System.out.println("Invalid input");
            System.exit(1);
        } else {
            // Initialize an array to hold the integers parsed from the command line arguments, excluding the last one.
            int[] numbers = new int[args.length - 1];
            int i;
            // Loop through the arguments to convert them to integers and store in the array.
            for (i = 0; i < args.length - 1; i++) {
                numbers[i] = Integer.parseInt(args[i]);
            }
            // The last argument is the target sum.
            int target = Integer.parseInt(args[i]);

            // Call the countPairs method to find and count pairs.
            int count = countPairs(numbers, target);

            // Output the number of valid pairs.
            System.out.println(count);
        }

    }
}
