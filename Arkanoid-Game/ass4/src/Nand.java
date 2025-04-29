/**
 * The Nand class represents a logical NAND binary expression.
 * It extends the BinaryExpression class.
 *
 * @author Yair Lavy 322214073
 */
public class Nand extends BinaryExpression {

    /**
     * Constructs a Nand expression with the specified left and right sub-expressions.
     *
     * @param left  the left sub-expression
     * @param right the right sub-expression
     */
    public Nand(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Computes the result of the NAND operation on two boolean values.
     *
     * @param first  the first boolean value
     * @param second the second boolean value
     * @return the result of !(first && second)
     */
    @Override
    public boolean compute(boolean first, boolean second) {
        return !(first && second);
    }

    /**
     * Returns a string representation of the NAND expression.
     *
     * @return a string representing the NAND expression
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " A " + right.toString() + ")";
    }

    /**
     * Assigns a specified expression to a variable within the NAND expression.
     *
     * @param var        the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Nand expression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(left.assign(var, expression), right.assign(var, expression));
    }

    /**
     * Converts the NAND expression into its NAND form (self).
     *
     * @return a new Nand expression
     */
    @Override
    public Expression nandify() {
        return new Nand(getLeftExpression().nandify(), getRightExpression().nandify());
    }

    /**
     * Converts the NAND expression into it's NOR form.
     *
     * @return a new Expression representing the NOR form of the NAND expression
     */
    @Override
    public Expression norify() {
        Expression norifiedLeft = getLeftExpression().norify();
        Expression norifiedRight = getRightExpression().norify();

        return new Nor(
                new Nor(new Nor(norifiedLeft, norifiedLeft),
                        new Nor(norifiedRight, norifiedRight)),
                new Nor(new Nor(norifiedLeft, norifiedLeft),
                        new Nor(norifiedRight, norifiedRight)));
    }

    /**
     * Simplifies the NAND expression.
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

        if (left.toString().compareTo("F") == 0 || right.toString().compareTo("F") == 0) return new Val(true);
        if (left.toString().compareTo("T") == 0) return new Not(right).simplify();
        if (right.toString().compareTo("T") == 0) return new Not(left).simplify();
        if (left.toString().compareTo(right.toString()) == 0) return new Not(left).simplify();

        return new Nand(left, right);
    }
}
