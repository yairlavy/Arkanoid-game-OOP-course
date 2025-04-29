import java.util.List;
import java.util.Map;

/**
 * The BaseExpression class represents an abstract base class for logical expressions.
 * This class provides abstract methods that must be implemented by subclasses.
 *
 * @author Yair Lavy 322214073
 */
public abstract class BaseExpression implements Expression {
    /**
     * Evaluates the expression using the provided variable assignment.
     *
     * @param assignment a map containing variable assignments
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * Evaluates the expression without any variable assignment.
     *
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Boolean evaluate() throws Exception {
        return evaluate(Map.of());
    }

    /**
     * Retrieves a list of variables used in the expression.
     *
     * @return a list of variable names
     */
    @Override
    public abstract List<String> getVariables();

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representing the expression
     */
    @Override
    public abstract String toString();

    /**
     * Assigns a specified expression to a variable within the expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new expression with the assigned expression
     */
    @Override
    public abstract Expression assign(String var, Expression expression);

    /**
     * Converts the expression into its NAND form.
     *
     * @return a new expression representing the NAND form of the expression
     */
    @Override
    public abstract Expression nandify();

    /**
     * Converts the expression into it's NOR form.
     *
     * @return a new expression representing the NOR form of the expression
     */
    @Override
    public abstract Expression norify();

    /**
     * Simplifies the expression.
     *
     * @return a simplified expression
     */
    @Override
    public abstract Expression simplify();
}
