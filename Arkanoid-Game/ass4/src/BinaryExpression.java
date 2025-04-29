import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The BinaryExpression class represents an abstract base class for binary logical expressions.
 * This class provides common functionality for binary expressions and must be extended by specific binary expression types.
 *
 * @author Yair Lavy 322214073
 */
public abstract class BinaryExpression extends BaseExpression {
    Expression left;
    Expression right;

    /**
     * Constructs a BinaryExpression with the specified left and right sub-expressions.
     *
     * @param left  the left sub-expression
     * @param right the right sub-expression
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Retrieves a list of variables used in the binary expression.
     *
     * @return a list of variable names
     */
    @Override
    public List<String> getVariables() {
        Set<String> variables = new HashSet<>();
        variables.addAll(left.getVariables());
        variables.addAll(right.getVariables());
        return new ArrayList<>(variables);
    }

    /**
     * Assigns a specified expression to a variable within the binary expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new BinaryExpression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        try {
            return this.getClass().getConstructor(Expression.class, Expression.class)
                    .newInstance(left.assign(var, expression), right.assign(var, expression));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Computes the result of the binary expression on two boolean values.
     *
     * @param first  the first boolean value
     * @param second the second boolean value
     * @return the result of the binary operation
     */
    public abstract boolean compute(boolean first, boolean second);

    /**
     * Retrieves the left sub-expression of the binary expression.
     *
     * @return the left sub-expression
     */
    public Expression getLeftExpression() {
        return left;
    }

    /**
     * Evaluates the binary expression using the provided variable assignment.
     *
     * @param assignment a map containing variable assignments
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return compute(left.evaluate(assignment), right.evaluate(assignment));
    }

    /**
     * Retrieves the right sub-expression of the binary expression.
     *
     * @return the right sub-expression
     */
    public Expression getRightExpression() {
        return right;
    }

    /**
     * Converts the binary expression into its NAND form.
     *
     * @return a new expression representing the NAND form of the binary expression
     */
    @Override
    public abstract Expression nandify();

    /**
     * Converts the binary expression into it's NOR form.
     *
     * @return a new expression representing the NOR form of the binary expression
     */
    @Override
    public abstract Expression norify();

    /**
     * Simplifies the binary expression.
     *
     * @return a simplified binary expression
     */
    @Override
    public Expression simplify() {
        left = left.simplify();
        right = right.simplify();
        return this;
    }
}
