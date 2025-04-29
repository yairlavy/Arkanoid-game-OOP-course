/**
 * The Not class represents a logical NOT unary expression.
 * It extends the UnaryExpression class.
 *
 * @author Yair Lavy 322214073
 */
public class Not extends UnaryExpression {

    /**
     * Constructs a Not expression with the specified sub-expression.
     *
     * @param expression the sub-expression
     */
    public Not(Expression expression) {
        super(expression);
    }

    /**
     * Computes the result of the NOT operation on a boolean value.
     *
     * @param first the boolean value
     * @return the result of !first
     */
    @Override
    public boolean compute(boolean first) {
        return !first;
    }

    /**
     * Returns a string representation of the NOT expression.
     *
     * @return a string representing the NOT expression
     */
    @Override
    public String toString() {
        return "~(" + expression.toString() + ")";
    }

    /**
     * Assigns a specified expression to a variable within the NOT expression.
     *
     * @param var the variable to be assigned
     * @param expression the expression to assign to the variable
     * @return a new Not expression with the assigned expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(this.expression.assign(var, expression));
    }

    /**
     * Converts the NOT expression into its NAND form.
     *
     * @return a new Expression representing the NAND form of the NOT expression
     */
    @Override
    public Expression nandify() {
        Expression nandifiedExpr = expression.nandify();
        return new Nand(nandifiedExpr, nandifiedExpr);
    }

    /**
     * Converts the NOT expression into it's NOR form.
     *
     * @return a new Expression representing the NOR form of the NOT expression
     */
    @Override
    public Expression norify() {
        Expression norifiedExpr = expression.norify();
        return new Nor(norifiedExpr, norifiedExpr);
    }

    /**
     * Simplifies the NOT expression.
     *
     * @return a simplified Expression
     */
    @Override
    public Expression simplify() {
        Expression simplifiedExpression = getExpression().simplify();

        if (simplifiedExpression.toString().compareTo("T") == 0) return new Val(false);
        if (simplifiedExpression.toString().compareTo("F") == 0) return new Val(true);

        return new Not(simplifiedExpression);
    }
}
