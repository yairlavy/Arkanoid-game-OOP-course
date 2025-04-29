import java.util.Map;

/**
 * The Xnor class represents a logical XNOR binary expression.
 * It extends the BinaryExpression class.
 *
 * @author Yair Lavy 322214073
 */
public class Xnor extends BinaryExpression {

    /**
     * Constructs Xnor expression with the specified left and right sub-expressions.
     *
     * @param left  the left sub-expression
     * @param right the right sub-expression
     */
    public Xnor(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Evaluates the XNOR expression using the provided variable assignment.
     *
     * @param assignment the map containing variable assignments
     * @return the boolean result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return compute(left.evaluate(assignment), right.evaluate(assignment));
    }

    /**
     * Computes the result of the XNOR operation on two boolean values.
     *
     * @param first  the first boolean value
     * @param second the second boolean value
     * @return the result of first == second
     */
    @Override
    public boolean compute(boolean first, boolean second) {
        return first == second;
    }

    /**
     * Returns a string representation of the XNOR expression.
     *
     * @return a string representing the XNOR expression
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " # " + right.toString() + ")";
    }

    /**
     * Assigns a specified expression to a variable within the XNOR expression.
     *
     * @param var        the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Xnor expression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(left.assign(var, expression), right.assign(var, expression));
    }

    /**
     * Converts the XNOR expression into its NAND form.
     *
     * @return a new Expression representing the NAND form of the XNOR expression
     */
    @Override
    public Expression nandify() {
        Expression nandifiedLeft = getLeftExpression().nandify();
        Expression nandifiedRight = getRightExpression().nandify();
        return new Nand(
                new Nand(new Nand(nandifiedLeft, nandifiedLeft),new Nand(nandifiedRight, nandifiedRight)),
                new Nand(nandifiedLeft, nandifiedRight));
    }

    /**
     * Converts the XNOR expression into it's NOR form.
     *
     * @return a new Expression representing the NOR form of the XNOR expression
     */
    @Override
    public Expression norify() {
        Expression norifiedLeft = getLeftExpression().norify();
        Expression norifiedRight = getRightExpression().norify();
        return new Nor(
                new Nor(norifiedLeft,new Nor(norifiedLeft,norifiedRight)),
                new Nor(norifiedRight,new Nor(norifiedLeft, norifiedRight)));
    }

    /**
     * Simplifies the XNOR expression.
     *
     * @return a simplified Expression
     */
    @Override
    public Expression simplify() {
        Expression simplifiedResult = super.simplify();

        // Return early if the result is already a constant
        if (simplifiedResult.toString().compareTo("T") == 0 || simplifiedResult.toString().compareTo("F") == 0) {
            return simplifiedResult;
        }
        Expression left = getLeftExpression().simplify();
        Expression right = getRightExpression().simplify();

        if (left.toString().compareTo(right.toString()) == 0) {
            return new Val(true);
        }

        return new Xnor(left, right);
    }
}
