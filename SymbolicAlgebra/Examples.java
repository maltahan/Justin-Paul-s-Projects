import tester.*;

public class Examples {
    IExpressionParser parser = new IExpressionParser();

    IExpression num1 = parser.parse("7"); // --> new Number(7.0)
    IExpression num2 = parser.parse("3.5");
    public void testNumber(Tester t) {
        t.checkExpect(num1.containsVar(), false);
        t.checkInexact(num1.eval(3.0), 7.0, 0.0000001);
        t.checkExpect(num1.toString(), "7.0");
        t.checkExpect(num1.toSmartString(), "7");
        t.checkExpect(num2.containsVar(), false);
        t.checkInexact(num2.eval(5.0), 3.5, 0.0000001);
        t.checkExpect(num2.toString(), "3.5");
        t.checkExpect(num2.toSmartString(), "3.5");
    }
   
    IExpression var = parser.parse("x");
    public void testVar(Tester t) {
        t.checkExpect(var.containsVar(), true);
        t.checkInexact(var.eval(3.0), 3.0, 0.000001);
        t.checkExpect(var.toString(), "x");
        t.checkExpect(var.toSmartString(), "x");
    }
   
    IExpression sum1 = parser.parse("3 + 2");
    IExpression sum2 = parser.parse("x + 7");
    public void testSum(Tester t) {
        t.checkExpect(sum1.containsVar(), false);
        t.checkInexact(sum1.eval(10.0), 5.0, 0.000001);
        t.checkExpect(sum1.toString(), "(3.0 + 2.0)");
        t.checkExpect(sum1.toSmartString(), "3 + 2");
        t.checkExpect(sum2.containsVar(), true);
        t.checkInexact(sum2.eval(3.5), 10.5, 0.000001);
        t.checkExpect(sum2.toString(), "(x + 7.0)");
        t.checkExpect(sum2.toSmartString(), "x + 7");
    }

    IExpression diff1 = parser.parse("7 - 1");
    IExpression diff2 = parser.parse("8 - x"); 
    IExpression diff3 = parser.parse("x - 3.5"); 
    public void testDifference(Tester t) {
        t.checkExpect(diff1.containsVar(), false);
        t.checkInexact(diff1.eval(4.2), 6.0, 0.0000001);
        t.checkExpect(diff1.toString(), "(7.0 - 1.0)");
        t.checkExpect(diff1.toSmartString(), "7 - 1");
        t.checkExpect(diff2.containsVar(), true);
        t.checkInexact(diff2.eval(4.2), 3.8, 0.0000001);
        t.checkExpect(diff2.toString(), "(8.0 - x)");
        t.checkExpect(diff2.toSmartString(), "8 - x");
        t.checkExpect(diff3.containsVar(), true);
        t.checkInexact(diff3.eval(4.2), 0.7, 0.0000001);
        t.checkExpect(diff3.toString(), "(x - 3.5)");
        t.checkExpect(diff3.toSmartString(), "x - 3.5"); 
    }
   
    IExpression prod1 = parser.parse("3 * 4");
    IExpression prod2 = parser.parse("6 * x"); 
    IExpression prod3 = parser.parse("x * 3");
    public void testProduct(Tester t) {
        t.checkExpect(prod1.containsVar(), false);
        t.checkInexact(prod1.eval(4.2), 12.0, 0.0000001);
        t.checkExpect(prod1.toString(), "(3.0 * 4.0)");
        t.checkExpect(prod1.toSmartString(), "3 * 4");
        t.checkExpect(prod2.containsVar(), true);
        t.checkInexact(prod2.eval(12.5), 75.0, 0.0000001);
        t.checkExpect(prod2.toString(), "(6.0 * x)");
        t.checkExpect(prod2.toSmartString(), "6 * x");
        t.checkExpect(prod3.containsVar(), true);
        t.checkInexact(prod3.eval(4.0), 12.0, 0.0000001);
        t.checkExpect(prod3.toString(), "(x * 3.0)");
        t.checkExpect(prod3.toSmartString(), "x * 3");
    }
    
    IExpression quot1 = parser.parse("20 / 5");
    IExpression quot2 = parser.parse("10 / x"); 
    IExpression quot3 = parser.parse("x / 4");
    public void testQuotient(Tester t) {
        t.checkExpect(quot1.containsVar(), false);
        t.checkInexact(quot1.eval(4.2), 4.0, 0.0000001);
        t.checkExpect(quot1.toString(), "(20.0 / 5.0)");
        t.checkExpect(quot1.toSmartString(), "20 / 5");
        t.checkExpect(quot2.containsVar(), true);
        t.checkInexact(quot2.eval(2.0), 5.0, 0.0000001);
        t.checkExpect(quot2.toString(), "(10.0 / x)");
        t.checkExpect(quot2.toSmartString(), "10 / x");
        t.checkExpect(quot3.containsVar(), true);
        t.checkInexact(quot3.eval(14.0), 3.5, 0.0000001);
        t.checkExpect(quot3.toString(), "(x / 4.0)");
        t.checkExpect(quot3.toSmartString(), "x / 4");
    }
   
    IExpression exp1 = parser.parse("25 ^ 0.5");
    IExpression exp2 = parser.parse("x ^ 2");
    IExpression exp3 = parser.parse("2 ^ x");
    public void testExponentiation(Tester t) {
        t.checkExpect(exp1.containsVar(), false);
        t.checkInexact(exp1.eval(4.2), 5.0, 0.0000001);
        t.checkExpect(exp1.toString(), "(25.0 ^ 0.5)");
        t.checkExpect(exp1.toSmartString(), "25 ^ 0.5");
        t.checkExpect(exp1.containsVar(), false);
        t.checkInexact(exp2.eval(4.2), 17.64, 0.0000001);
        t.checkExpect(exp2.toString(), "(x ^ 2.0)");
        t.checkExpect(exp2.toSmartString(), "x ^ 2");
        t.checkExpect(exp2.containsVar(), true);
        t.checkInexact(exp3.eval(4.2), 18.37917368, 0.0000001);
        t.checkExpect(exp3.toString(), "(2.0 ^ x)");
        t.checkExpect(exp3.toSmartString(), "2 ^ x");
    }
    
    IExpression neg1 = parser.parse("~x"); 
    IExpression neg2 = parser.parse("~-2");
    public void testNeg(Tester t) {
        t.checkExpect(neg1.containsVar(), true);
        t.checkInexact(neg1.eval(7.3), -7.3, 0.0000001);
        t.checkExpect(neg1.toString(), "~(x)");
        t.checkExpect(neg1.toSmartString(), "~x");
        t.checkExpect(neg2.containsVar(), false);
        t.checkInexact(neg2.eval(7.3), 2.0, 0.0000001);
        t.checkExpect(neg2.toString(), "~(-2.0)");
        t.checkExpect(neg2.toSmartString(), "~-2");
    }

    IExpression sin1 = parser.parse("sin x"); 
    IExpression sin2 = parser.parse("sin(x + 2)");
    IExpression sin3 = parser.parse("sin 4.71238898038469"); // 3 * pi / 2
    public void testSin(Tester t) {
        t.checkExpect(sin1.containsVar(), true);
        t.checkInexact(sin1.eval(0.0), 0.0, 0.0000001);
        t.checkExpect(sin1.toString(), "sin(x)");
        t.checkExpect(sin1.toSmartString(), "sin x");
        t.checkExpect(sin2.containsVar(), true);
        t.checkInexact(sin2.eval(3.0 * Math.PI / 2 - 2), -1.0, 0.0000001);
        t.checkExpect(sin2.toString(), "sin((x + 2.0))");
        t.checkExpect(sin2.toSmartString(), "sin(x + 2)");
        t.checkExpect(sin3.containsVar(), false);
        t.checkInexact(sin3.eval(7.3), -1.0, 0.0000001);
        t.checkExpect(sin3.toString(), "sin(4.71238898038469)");
        t.checkExpect(sin3.toSmartString(), "sin 4.71238898038469");
    }
   
    IExpression cos1 = parser.parse("cos x ^ 2");
    IExpression cos2 = parser.parse("cos(x - 5)");
    IExpression cos3 = parser.parse("cos 1.0471975511965976"); // pi / 3
    public void testCos(Tester t) {
        t.checkExpect(cos1.containsVar(), true);
        t.checkInexact(cos1.eval(2.5), Math.cos(6.25), 0.0000001);
        t.checkExpect(cos1.toString(), "cos((x ^ 2.0))");
        t.checkExpect(cos1.toSmartString(), "cos x ^ 2");
        t.checkExpect(cos2.containsVar(), true);
        t.checkInexact(cos2.eval(Math.PI + 5), -1.0, 0.0000001);
        t.checkExpect(cos2.toString(), "cos((x - 5.0))");
        t.checkExpect(cos2.toSmartString(), "cos(x - 5)");
        t.checkExpect(cos3.containsVar(), false);
        t.checkInexact(cos3.eval(7.3), 0.5, 0.0000001);
        t.checkExpect(cos3.toString(), "cos(1.0471975511965976)");
        t.checkExpect(cos3.toSmartString(), "cos 1.0471975511965976");
    }
    
    IExpression ln1 = parser.parse("ln x"); 
    IExpression ln2 = parser.parse("ln x ^ 2");
    IExpression ln3 = parser.parse("ln 20.085536923187664"); // e ^ 3
    public void testLn(Tester t) {
        t.checkExpect(ln1.containsVar(), true);
        t.checkInexact(ln1.eval(1.0), 0.0, 0.0000001);
        t.checkExpect(ln1.toString(), "ln(x)");
        t.checkExpect(ln1.toSmartString(), "ln x");
        t.checkExpect(ln2.containsVar(), true);
        t.checkInexact(ln2.eval(Math.E * Math.E), 4.0, 0.0000001);
        t.checkExpect(ln2.toString(), "ln((x ^ 2.0))");
        t.checkExpect(ln2.toSmartString(), "ln x ^ 2");
        t.checkExpect(ln3.containsVar(), false);
        t.checkInexact(ln3.eval(7.3), 3.0, 0.0000001);
        t.checkExpect(ln3.toString(), "ln(20.085536923187664)");
        t.checkExpect(ln3.toSmartString(), "ln 20.085536923187664");
    }
    
    public static void main(String[] args) {
        Tester.run(new Examples());
    }
}
