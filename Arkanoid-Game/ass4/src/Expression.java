import java.util.List;
import java.util.Map;

/**
 * The Expression interface represents a logical expression.
 * This interface provides methods for evaluating, assigning, and transforming expressions.
 *
 * @author Yair Lavy 322214073
 */
public interface Expression {

    /**
     * Evaluates the expression using the variable values provided
     * in the assignment, and returns the result.
     * If the expression contains a variable which is not in the assignment,
     * an exception is thrown.
     *
     * @param assignment the map containing variable assignments
     * @return the result of the evaluation
     * @throws Exception if a variable in the expression is not in the assignment
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the evaluate(assignment) method above,
     * but uses an empty assignment.
     *
     * @return the result of the evaluation
     * @throws Exception if the expression contains variables
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return list of variable names
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return string representation of the expression
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression.
     * Does not modify the current expression.
     *
     * @param var the variable to replace
     * @param expression the expression to replace the variable with
     * @return the new expression with the variable replaced
     */
    Expression assign(String var, Expression expression);

    /**
     * Converts the expression into its NAND form.
     *
     * @return a new expression representing the NAND form of the expression
     */
    Expression nandify();

    /**
     * Converts the expression into it's NOR form.
     *
     * @return a new expression representing the NOR form of the expression
     */
    Expression norify();

    /**
     * Returns a simplified version of the current expression.
     *
     * @return a simplified expression
     */
    Expression simplify();
}
