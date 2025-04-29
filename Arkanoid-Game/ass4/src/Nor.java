/**
 * The Nor class represents a logical NOR binary expression.
 * It extends the BinaryExpression class.
 *
 * @author Yair Lavy 322214073
 */
public class Nor extends BinaryExpression {

    /**
     * Constructs a Nor expression with the specified left and right sub-expressions.
     *
     * @param left  the left sub-expression
     * @param right the right sub-expression
     */
    public Nor(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Computes the result of the NOR operation on two boolean values.
     *
     * @param first  the first boolean value
     * @param second the second boolean value
     * @return the result of !(first || second)
     */
    @Override
    public boolean compute(boolean first, boolean second) {
        return !(first || second);
    }

    /**
     * Returns a string representation of the NOR expression.
     *
     * @return a string representing the NOR expression
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " V " + right.toString() + ")";
    }

    /**
     * Assigns a specified expression to a variable within the NOR expression.
     *
     * @param var        the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Nor expression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(left.assign(var, expression), right.assign(var, expression));
    }

    /**
     * Converts the NOR expression into its NAND form.
     *
     * @return a new Expression representing the NAND form of the NOR expression
     */
    @Override
    public Expression nandify() {
        Expression nandifiedLeft = getLeftExpression().nandify();
        Expression nandifiedRight = getRightExpression().nandify();
        return new Nand(
                new Nand(
                        new Nand(nandifiedLeft, nandifiedLeft),
                        new Nand(nandifiedRight, nandifiedRight)),
                new Nand(new Nand(nandifiedLeft, nandifiedLeft),
                        new Nand(nandifiedRight, nandifiedRight)));
    }

    /**
     * Converts the NOR expression into it's NOR form (self).
     *
     * @return a new Nor expression
     */
    @Override
    public Expression norify() {
        return new Nor(getLeftExpression().norify(), getRightExpression().norify());
    }

    /**
     * Simplifies the NOR expression.
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

        if (left.toString().compareTo("T") == 0 || right.toString().compareTo("T") == 0) return new Val(false);
        if (left.toString().compareTo("F") == 0) return new Not(right).simplify();
        if (right.toString().compareTo("F") == 0) return new Not(left).simplify();
        if (left.toString().compareTo(right.toString()) == 0) return new Not(left).simplify();

        return new Nor(left, right);
    }
}
