/**
 * * @author Yair Lavy 322214073
 * The And class represents a logical AND binary expression.
 * It extends the BinaryExpression class.
 */
public class And extends BinaryExpression {

    /**
     * Constructs an And expression with the specified left and right sub-expressions.
     *
     * @param left  the left sub-expression
     * @param right the right sub-expression
     */
    public And(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Computes the result of the AND operation on two boolean values.
     *
     * @param first  the first boolean value
     * @param second the second boolean value
     * @return the result of first AND second
     */
    @Override
    public boolean compute(boolean first, boolean second) {
        return first && second;
    }

    /**
     * Returns a string representation of the AND expression.
     *
     * @return a string representing the AND expression
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " & " + right.toString() + ")";
    }

    /**
     * Assigns a specified expression to a variable within the AND expression.
     *
     * @param var        the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new And expression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new And(left.assign(var, expression), right.assign(var, expression));
    }

    /**
     * Converts the AND expression into a NAND expression.
     *
     * @return a new Expression representing the NAND form of the AND expression
     */
    @Override
    public Expression nandify() {
        Expression nandifiedLeft = getLeftExpression().nandify();
        Expression nandifiedRight = getRightExpression().nandify();
        return new Nand(new Nand(nandifiedLeft, nandifiedRight),
                new Nand(nandifiedLeft, nandifiedRight));
    }

    /**
     * Converts the AND expression into a NOR expression.
     *
     * @return a new Expression representing the NOR form of the AND expression
     */
    @Override
    public Expression norify() {
        Expression norifiedLeft = getLeftExpression().norify();
        Expression norifiedRight = getRightExpression().norify();
        return new Nor(new Nor(norifiedLeft, norifiedLeft),
                new Nor(norifiedRight, norifiedRight));
    }

    /**
     * Simplifies the AND expression.
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
        Expression left = getLeftExpression();
        Expression right = getRightExpression();

        if (left.toString().compareTo("F") == 0 || right.toString().compareTo("F") == 0) return new Val(false);
        if (left.toString().compareTo("T") == 0) return right;
        if (right.toString().compareTo("T") == 0) return left;
        if (left.toString().compareTo(right.toString()) == 0) return left;

        return new And(left, right);
    }
}
