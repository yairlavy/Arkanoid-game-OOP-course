/*************.
 * Yair Lavy 322214073
 * Assignment 1
 *************/
public class SumOfInteger {
    /**.
     * A recursive method to sum the digits of a given integer.
     * @param n The integer whose digits are to be summed.
     * @return The sum of the digits of the integer. If the integer is 0, returns 0.
     */
    private static int sumDigits(int n) {
        // Base case: if n is 0, return 0 to end recursion.
        if (n == 0) {
            return 0;
        }
        // Recursive case: return the last digit of n plus the sum of the digits of n divided by 10.
        return n % 10 + sumDigits(n / 10);
    }

    /**
     * The main method which serves as the entry point for the program.
     * It expects exactly one command line argument which should be an integer.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        // Check if exactly one argument is provided.
        if (args.length != 1) {
            System.out.println("Invalid input");
            return;
        }

        // Loop through each character in the provided argument to ensure it is a digit.
        for (int i = 0; i < args[0].length(); i++) {
            // Check if the character at position i is not a digit.
            if (!Character.isDigit(args[0].charAt(i))) {
                System.out.println("Invalid input");
                return;
            }
        }

        // Convert the string argument to an integer.
        int n = Integer.parseInt(args[0]);

        // Calculate the sum of the digits of the integer.
        int sum = sumDigits(n);

        // Print the result.
        System.out.println(sum);
    }
}
