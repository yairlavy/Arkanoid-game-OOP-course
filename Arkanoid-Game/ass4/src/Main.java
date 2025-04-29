import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Expression expression = new And(
                new Nand(
                        new Not(
                                new Xor(new Var("z"),
                                        new Val(true))
                        ),
                        new Xnor(
                                new And(new Var("x"),
                                        new Var("y")),
                                new Val(false))
                ),

                new Or(new Val(false),
                        new Var("x"))
        );
        System.out.println(expression);
        //assigning
        System.out.println(expression.assign("z", new Var("F")));
        System.out.println(expression.assign("x", new Val(true)));
        System.out.println(expression.assign("y", new Var("w")));
        //dandifying
        System.out.println(expression.nandify());
        //norifying
        System.out.println(expression.norify());
        //simplifying
        System.out.println(expression.simplify());
    }
}
