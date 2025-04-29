import java.util.HashMap;

public class ExpressionsTest {
    public static void main(String[] args) throws Exception {
        // Create a complex expression with at least three variables
        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression z = new Var("z");
        Expression expression = new Or(new And(x, new Not(y)), new Xnor(z, new Or(x, y)));

        // Print the expression
        System.out.println(expression);

        // Print the value of the expression with an assignment to every variable
        HashMap<String, Boolean> assignment = new HashMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", true);
        System.out.println(expression.evaluate(assignment));

        // Print the Nandified version of the expression
        System.out.println(expression.nandify());

        // Print the Norified version of the expression
        System.out.println(expression.norify());

        // Print the simplified version of the expression
        System.out.println(expression.simplify());
    }
}
