import java.util.List;
import java.util.Map;

/**
 * The Var class represents a variable expression.
 * This class implements the Expression interface.
 *
 * @author Yair Lavy 322214073
 */
public class Var implements Expression {
    private final String name;

    /**
     * Constructs a Var expression with the specified variable name.
     *
     * @param name the name of the variable
     */
    public Var(String name) {
        this.name = name;
    }

    /**
     * Evaluates the variable expression using the provided variable assignment.
     *
     * @param assignment the map containing variable assignments
     * @return the boolean value of the variable
     * @throws Exception if the variable is not found in the assignment
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(name)) {
            return assignment.get(name);
        }
        throw new Exception(String.format("'%s' variable was not set in assignment.", name));
    }

    /**
     * Evaluates the variable expression without any variable assignment.
     *
     * @return never returns normally
     * @throws Exception as the variable has no value
     */
    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Variable " + name + " has no value");
    }

    /**
     * Returns a list containing the name of the variable.
     *
     * @return a list containing the variable name
     */
    @Override
    public List<String> getVariables() {
        return List.of(name);
    }

    /**
     * Returns a string representation of the variable expression.
     *
     * @return the name of the variable
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Assigns a specified expression to the variable within the variable expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return the assigned expression if the variable matches, otherwise the same Var expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(name)) {
            return expression;
        }
        return this;
    }

    /**
     * Converts the variable expression into its NAND form.
     *
     * @return the same Var expression
     */
    @Override
    public Expression nandify() {
        return this;
    }

    /**
     * Converts the variable expression into it's NOR form.
     *
     * @return the same Var expression
     */
    @Override
    public Expression norify() {
        return this;
    }

    /**
     * Simplifies the variable expression.
     *
     * @return the same Var expression
     */
    @Override
    public Expression simplify() {
        return this;
    }
}
