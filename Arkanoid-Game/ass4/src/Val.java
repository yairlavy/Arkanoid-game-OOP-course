import java.util.List;
import java.util.Map;

/**
 * The Val class represents a boolean value expression.
 * This class implements the Expression interface.
 *
 * @author Yair Lavy 322214073
 */
public class Val implements Expression {
    private final boolean value;

    /**
     * Constructs a Val expression with the specified boolean value.
     *
     * @param value the boolean value
     */
    public Val(boolean value) {
        this.value = value;
    }

    /**
     * Evaluates the value expression using the provided variable assignment.
     *
     * @param assignment the map containing variable assignments
     * @return the boolean value of the expression
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) {
        return value;
    }

    /**
     * Evaluates the value expression without any variable assignment.
     *
     * @return the boolean value of the expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Boolean evaluate() throws Exception {
        return value;
    }

    /**
     * Returns an empty list as there are no variables in a value expression.
     *
     * @return an empty list
     */
    @Override
    public List<String> getVariables() {
        return List.of();
    }

    /**
     * Returns a string representation of the value expression.
     *
     * @return "T" if the value is true, "F" if the value is false
     */
    @Override
    public String toString() {
        return value ? "T" : "F";
    }

    /**
     * Returns the same value expression as there are no variables to assign.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return the same Val expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * Converts the value expression into its NAND form.
     *
     * @return the same Val expression
     */
    @Override
    public Expression nandify() {
        return this;
    }

    /**
     * Converts the value expression into it's NOR form.
     *
     * @return the same Val expression
     */
    @Override
    public Expression norify() {
        return this;
    }

    /**
     * Simplifies the value expression.
     *
     * @return the same Val expression
     */
    @Override
    public Expression simplify() {
        return this;
    }
}
