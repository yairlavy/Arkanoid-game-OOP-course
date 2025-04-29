import java.util.List;
import java.util.Map;

/**
 * The UnaryExpression class represents an abstract base class for unary logical expressions.
 * This class provides common functionality for unary expressions and must be extended by specific unary expression types.
 *
 * @author Yair Lavy 322214073
 */
public abstract class UnaryExpression extends BaseExpression {
    Expression expression;

    /**
     * Constructs a UnaryExpression with the specified sub-expression.
     *
     * @param expression the sub-expression
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Evaluates the unary expression using the provided variable assignment.
     *
     * @param assignment a map containing variable assignments
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return compute(expression.evaluate(assignment));
    }

    /**
     * Computes the result of the unary operation on a boolean value.
     *
     * @param first the boolean value
     * @return the result of the unary operation
     */
    public abstract boolean compute(boolean first);

    /**
     * Retrieves the sub-expression of the unary expression.
     *
     * @return the sub-expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Retrieves a list of variables used in the unary expression.
     *
     * @return a list of variable names
     */
    @Override
    public List<String> getVariables() {
        return expression.getVariables();
    }

    /**
     * Assigns a specified expression to a variable within the unary expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new UnaryExpression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        try {
            return this.getClass().getConstructor(Expression.class).newInstance(expression.assign(var, expression));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Converts the unary expression into its NAND form.
     *
     * @return a new Expression representing the NAND form of the unary expression
     */
    @Override
    public abstract Expression nandify();

    /**
     * Converts the unary expression into it's NOR form.
     *
     * @return a new Expression representing the NOR form of the unary expression
     */
    @Override
    public abstract Expression norify();

    /**
     * Simplifies the unary expression.
     *
     * @return a simplified UnaryExpression
     */
    @Override
    public Expression simplify() {
        expression = expression.simplify();
        return this;
    }
}
