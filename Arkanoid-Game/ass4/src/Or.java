/**
 * The Or class represents a logical OR binary expression.
 * It extends the BinaryExpression class.
 *
 * @author Yair Lavy 322214073
 */
public class Or extends BinaryExpression {

    /**
     * Constructs an Or expression with the specified left and right sub-expressions.
     *
     * @param left  the left sub-expression
     * @param right the right sub-expression
     */
    public Or(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Computes the result of the OR operation on two boolean values.
     *
     * @param first  the first boolean value
     * @param second the second boolean value
     * @return the result of first || second
     */
    @Override
    public boolean compute(boolean first, boolean second) {
        return first || second;
    }

    /**
     * Returns a string representation of the OR expression.
     *
     * @return a string representing the OR expression
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " | " + right.toString() + ")";
    }

    /**
     * Assigns a specified expression to a variable within the OR expression.
     *
     * @param var        the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Or expression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(left.assign(var, expression), right.assign(var, expression));
    }

    /**
     * Converts the OR expression into its NAND form.
     *
     * @return a new Expression representing the NAND form of the OR expression
     */
    @Override
    public Expression nandify() {
        Expression nandifiedLeft = left.nandify();
        Expression nandifiedRight = right.nandify();
        return new Nand(new Nand(nandifiedLeft, nandifiedLeft),
                new Nand(nandifiedRight, nandifiedRight));
    }

    /**
     * Converts the OR expression into it's NOR form.
     * @return a new Expression representing the NOR form of the OR expression
     */
    @Override
    public Expression norify() {
        Expression norifiedLeft = left.norify();
        Expression norifiedRight = right.norify();
        return new Nor(new Nor(norifiedLeft, norifiedRight),
                new Nor(norifiedLeft, norifiedRight));
    }

    /**
     * Simplifies the OR expression.
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

        if (left.toString().compareTo("T") == 0 || right.toString().compareTo("T") == 0) return new Val(true);
        if (left.toString().compareTo("F") == 0) return right;
        if (right.toString().compareTo("F") == 0) return left;
        if (left.toString().compareTo(right.toString()) == 0) return left;

        return new Or(left, right);
    }
}
