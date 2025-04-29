/**
 * The Xor class represents a logical XOR binary expression.
 * It extends the BinaryExpression class.
 *
 * @author Yair Lavy 322214073
 */
public class Xor extends BinaryExpression {

    /**
     * Constructs  Xor expression with the specified left and right sub-expressions.
     *
     * @param left  the left sub-expression
     * @param right the right sub-expression
     */
    public Xor(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Computes the result of the XOR operation on two boolean values.
     *
     * @param first  the first boolean value
     * @param second the second boolean value
     * @return the result of first ^ second
     */
    @Override
    public boolean compute(boolean first, boolean second) {
        return first ^ second;
    }

    /**
     * Returns a string representation of the XOR expression.
     *
     * @return a string representing the XOR expression
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " ^ " + right.toString() + ")";
    }

    /**
     * Assigns a specified expression to a variable within the XOR expression.
     *
     * @param var        the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Xor expression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(left.assign(var, expression), right.assign(var, expression));
    }

    /**
     * Converts the XOR expression into its NAND form.
     *
     * @return a new Expression representing the NAND form of the XOR expression
     */
    @Override
    public Expression nandify() {
        Expression nandifiedLeft = getLeftExpression().nandify();
        Expression nandifiedRight = getRightExpression().nandify();
        return new Nand(
                new Nand(nandifiedLeft, new Nand(nandifiedLeft, nandifiedRight)),
                new Nand(nandifiedRight, new Nand(nandifiedLeft, nandifiedRight)));
    }

    /**
     * Converts the XOR expression into it's NOR form.
     *
     * @return a new Expression representing the NOR form of the XOR expression
     */
    @Override
    public Expression norify() {
        Expression norifiedLeft = getLeftExpression().norify();
        Expression norifiedRight = getRightExpression().norify();

        Expression aNorA = new Nor(norifiedLeft, norifiedLeft);
        Expression bNorB = new Nor(norifiedRight, norifiedRight);
        Expression aNorB = new Nor(norifiedLeft, norifiedRight);

        return new Nor(new Nor(aNorA, bNorB), aNorB);
    }


    /**
     * Simplifies the XOR expression.
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

        if (left.toString().compareTo("T") == 0) return new Not(right).simplify();
        if (right.toString().compareTo("T") == 0) return new Not(left).simplify();
        if (left.toString().compareTo("F") == 0) return right;
        if (right.toString().compareTo("F") == 0) return left;
        if (left.toString().compareTo(right.toString()) == 0) return new Val(false);

        return new Xor(left, right);
    }
}
