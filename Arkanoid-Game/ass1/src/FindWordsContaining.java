/*************.
 * Yair Lavy 322214073
 * Assignment 1
 *************/
/**
 * A class to find and print words from command line arguments that contain a specific character.
 */
public class FindWordsContaining {
    /**
     * The main method, serving as the entry point for the program.
     * It expects at least two command line arguments: one or more words followed by a character to search for.
     * It prints all words that contain the specified character.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        // Check if there are at least two command line arguments (at least one word and one character).
        if (args.length < 2) {
            System.out.println("Invalid input");
            return; // Exit the main method if the number of arguments is less than 2.
        }

        // Check if the last argument, expected to be the search character, is actually a single character.
        if ((Character.isLetter(args[args.length - 1].charAt(0))) && args[args.length - 1].length() != 1) {
            System.out.println("Invalid input");
            return; // Exit the main method if the last argument is not a single character.
        }

        // Extract the character to search for from the last argument.
        char x = args[args.length - 1].charAt(0);

        // Iterate through each argument (excluding the last one, which is the character).
        for (int i = 0; i < args.length - 1; i++) {
            // Check if the current word contains the specified character.
            if (args[i].indexOf(x) != -1) {
                System.out.println(args[i]); // Print the word if it contains the character.
            }
        }
    }
}
