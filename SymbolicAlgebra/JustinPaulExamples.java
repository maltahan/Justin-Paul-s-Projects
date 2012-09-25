import tester.*;

public class JustinPaulExamples {
   IExpressionParser parser = new IExpressionParser();
   
   IExpression num1 = parser.parse("5");
   IExpression num2 = parser.parse("6.25");
   public void testNumber(Tester t) {
       t.checkExpect(num1.containsVar(), false);
       t.checkExpect(num1.eval(5.1), 5.0);
       t.checkExpect(num1.toString(), "5.0");
       t.checkExpect(num1.toSmartString(), "5");
       t.checkExpect(num2.containsVar(), false);
       t.checkExpect(num2.eval(5.0), 6.25);
       t.checkExpect(num2.toString(), "6.25");
       t.checkExpect(num2.toSmartString(), "6.25");
       t.checkExpect(num1.derivative(), new Number(0.0));
   }
   
   IExpression var1 = parser.parse("x");
   IExpression var2 = parser.parse("x + 2");
   public void testVar(Tester t) {
       t.checkExpect(var1.containsVar(), true);
       t.checkExpect(var1.eval(4.35), 4.35);
       t.checkExpect(var1.toString(), "x");
       t.checkExpect(var1.toSmartString(), "x");
       t.checkExpect(var2.containsVar(), true);
       t.checkExpect(var2.eval(16.0), 18.0);
       t.checkExpect(var2.toString(), "(x + 2.0)");
       t.checkExpect(var2.toSmartString(), "x + 2");
       t.checkExpect(var1.derivative(), new Number(1.0));
   }
   
   IExpression sum1 = parser.parse("6 + 4.3");
   IExpression sum2 = parser.parse("x + 2.5");
   public void testSum(Tester t) {
       t.checkExpect(sum1.containsVar(), false);
       t.checkExpect(sum1.eval(200.0), 10.3);
       t.checkExpect(sum1.toString(), "(6.0 + 4.3)");
       t.checkExpect(sum1.toSmartString(), "6 + 4.3");
       t.checkExpect(sum2.containsVar(), true);
       t.checkExpect(sum2.eval(2.6), 5.1);
       t.checkExpect(sum2.toString(), "(x + 2.5)");
       t.checkExpect(sum2.toSmartString(), "x + 2.5");
       t.checkExpect(sum2.derivative(), new Sum(new Number(1.0),
                                                new Number(0.0)));
   }
   
   IExpression diff1 = parser.parse("8 - 5");
   IExpression diff2 = parser.parse("x - 6");
   IExpression diff3 = parser.parse("18 - x");
   IExpression diff4 = parser.parse("10 - 2 * x");
   IExpression diff5 = parser.parse("0 - x");
   IExpression diff6 = parser.parse("~x - 3");
   public void testDifference(Tester t) {
       t.checkExpect(diff1.containsVar(), false);
       t.checkExpect(diff1.eval(15.5), 3.0);
       t.checkExpect(diff1.toString(), "(8.0 - 5.0)");
       t.checkExpect(diff1.toSmartString(), "8 - 5");
       t.checkExpect(diff2.containsVar(), true);
       t.checkExpect(diff2.eval(22.2), 16.2);
       t.checkExpect(diff2.toString(), "(x - 6.0)");
       t.checkExpect(diff2.toSmartString(), "x - 6");
       t.checkExpect(diff3.containsVar(), true);
       t.checkExpect(diff3.eval(18.0), 0.0);
       t.checkExpect(diff3.toString(), "(18.0 - x)");
       t.checkExpect(diff3.toSmartString(), "18 - x");
       t.checkExpect(diff4.containsVar(), true);
       t.checkExpect(diff4.eval(3.0), 4.0);
       t.checkExpect(diff4.toString(), "(10.0 - (2.0 * x))");
       t.checkExpect(diff4.toSmartString(), "10 - 2 * x");
       t.checkExpect(diff5.simplify(), new Neg(new Var()));
       t.checkExpect(diff6.simplify(), new Neg(new Sum(new Var(),
                                                       new Number(3.0))));
       t.checkExpect(diff2.derivative(), new Difference(new Number(1.0),
                                                         new Number(0.0)));
   }
   
   IExpression prod1 = parser.parse("2 * 5");
   IExpression prod2 = parser.parse("x * 0");
   IExpression prod3 = parser.parse("4 * x");
   IExpression prod4 = parser.parse("1 * x");
   IExpression prod5 = parser.parse("x * x");
   IExpression prod6 = parser.parse("x * (1 / 2)");
   public void testProduct(Tester t) {
       t.checkExpect(prod1.containsVar(), false);
       t.checkExpect(prod1.eval(1.0), 10.0);
       t.checkExpect(prod1.toString(), "(2.0 * 5.0)");
       t.checkExpect(prod1.toSmartString(), "2 * 5");
       t.checkExpect(prod2.containsVar(), true);
       t.checkExpect(prod2.eval(100000000.0), 0.0);
       t.checkExpect(prod2.toString(), "(x * 0.0)");
       t.checkExpect(prod2.toSmartString(), "x * 0");
       t.checkExpect(prod3.containsVar(), true);
       t.checkExpect(prod3.eval(15.0), 60.0);
       t.checkExpect(prod3.toString(), "(4.0 * x)");
       t.checkExpect(prod3.toSmartString(), "4 * x");
       t.checkExpect(prod4.simplify(), new Var());
       t.checkExpect(prod5.simplify(), new Exponentiation(new Var(),
                                                          new Number(2.0)));
       t.checkExpect(prod6.simplify(), new Product(new Var(),
                                                    new Number(0.5)));
       t.checkExpect(prod3.derivative(), new Sum(new Product(new Number(0.0),
                                                             new Var()),
                                                 new Product(new Number(1.0),
                                                             new Number(4.0))));
   }   
   
   IExpression quot1 = parser.parse("14 / 7 - 1");
   IExpression quot2 = parser.parse("x / 6");
   IExpression quot3 = parser.parse("9 / x");
   IExpression quot4 = parser.parse("5 / (3 / x)");
   public void testQuotient(Tester t) {
       t.checkExpect(quot1.containsVar(), false);
       t.checkExpect(quot1.eval(6.3), 1.0);
       t.checkExpect(quot1.toString(), "((14.0 / 7.0) - 1.0)");
       t.checkExpect(quot1.toSmartString(), "14 / 7 - 1");
       t.checkExpect(quot2.containsVar(), true);
       t.checkExpect(quot2.eval(12.0), 2.0);
       t.checkExpect(quot2.toString(), "(x / 6.0)");
       t.checkExpect(quot2.toSmartString(), "x / 6");
       t.checkExpect(quot3.containsVar(), true);
       t.checkExpect(quot3.eval(2.0), 4.5);
       t.checkExpect(quot3.toString(), "(9.0 / x)");
       t.checkExpect(quot3.toSmartString(), "9 / x");
       t.checkExpect(quot4.simplify(), new Quotient(new Product(new Number(5.0),
                                                                new Var()),
                                                    new Number(3.0)));
       t.checkExpect(quot2.derivative(), new Quotient(new Difference(new Product(new Number(1.0),
                                                                                 new Number(6.0)),
                                                                     new Product(new Number(0.0),
                                                                                 new Var())),
                                                      new Exponentiation(new Number(6.0),
                                                                         new Number(2.0))));
   }
   
   IExpression exp1 = parser.parse("2 ^ 2");
   IExpression exp2 = parser.parse("16 ^ 0.5");
   IExpression exp3 = parser.parse("x ^ 1");
   IExpression exp4 = parser.parse("3 ^ x / 3");
   IExpression exp5 = parser.parse("32.0 / 2 ^ x");
   IExpression exp6 = parser.parse("x ^ 0");
   IExpression exp7 = parser.parse("2 ^ x");
   public void testExponentiation(Tester t) {
       t.checkExpect(exp1.containsVar(), false);
       t.checkExpect(exp1.eval(2.1), 4.0);
       t.checkExpect(exp1.toString(), "(2.0 ^ 2.0)");
       t.checkExpect(exp1.toSmartString(), "2 ^ 2");
       t.checkExpect(exp2.containsVar(), false);
       t.checkExpect(exp2.eval(4.0), 4.0);
       t.checkExpect(exp2.toString(), "(16.0 ^ 0.5)");
       t.checkExpect(exp2.toSmartString(), "16 ^ 0.5");
       t.checkExpect(exp3.containsVar(), true);
       t.checkExpect(exp3.eval(31.6), 31.6);
       t.checkExpect(exp3.toString(), "(x ^ 1.0)");
       t.checkExpect(exp3.toSmartString(), "x ^ 1");
       t.checkExpect(exp4.containsVar(), true);
       t.checkExpect(exp4.eval(4.0), 27.0);
       t.checkExpect(exp4.toString(), "((3.0 ^ x) / 3.0)");
       t.checkExpect(exp4.toSmartString(), "3 ^ x / 3");
       t.checkExpect(exp5.containsVar(), true);
       t.checkExpect(exp5.eval(4.0), 2.0);
       t.checkExpect(exp5.toString(), "(32.0 / (2.0 ^ x))");
       t.checkExpect(exp5.toSmartString(), "32 / 2 ^ x");
       t.checkExpect(exp6.simplify(), new Number(1.0));
       t.checkExpect(exp3.derivative(), new Product(new Product(new Number(1.0),
                                                                new Exponentiation(new Var(),
                                                                                   new Difference(new Number(1.0),
                                                                                                  new Number(1.0)))),
                                                    new Number(1.0)));
       t.checkExpect(exp7.derivative(), new Product(new Exponentiation(new Number(2.0),
                                                                       new Var()),
                                                    new Sum(new Product(new Number(1.0),
                                                                        new Ln(new Number(2.0))),
                                                            new Quotient(new Product(new Number(0.0),
                                                                                     new Var()),
                                                                         new Number(2.0)))));
   }
   
   IExpression neg1 = parser.parse("~3");
   IExpression neg2 = parser.parse("~-5");
   IExpression neg3 = parser.parse("~(x + 1)");
   IExpression neg4 = parser.parse("~~x");
   public void testNeg(Tester t) {
       t.checkExpect(neg1.containsVar(), false);
       t.checkExpect(neg1.eval(3.75), -3.0);
       t.checkExpect(neg1.toString(), "~(3.0)");
       t.checkExpect(neg1.toSmartString(), "~3");
       t.checkExpect(neg2.containsVar(), false);
       t.checkExpect(neg2.eval(4.0), 5.0);
       t.checkExpect(neg2.toString(), "~(-5.0)");
       t.checkExpect(neg2.toSmartString(), "~-5");
       t.checkExpect(neg3.containsVar(), true);
       t.checkExpect(neg3.eval(5.0), -6.0);
       t.checkExpect(neg3.toString(), "~((x + 1.0))");
       t.checkExpect(neg3.toSmartString(), "~(x + 1)");
       t.checkExpect(neg4.simplify(), new Var());
       t.checkExpect(neg1.derivative(), new Neg(new Number(0.0)));
   }
   
   IExpression sin1 = parser.parse("sin 60");
   IExpression sin2 = parser.parse("sin x");
   IExpression sin3 = parser.parse("sin(x * 2)");
   IExpression sin4 = parser.parse("sin 0");
   public void testSin(Tester t) {
       t.checkExpect(sin1.containsVar(), false);
       t.checkInexact(sin1.eval(80.0),  -0.3048106, .0001);
       t.checkExpect(sin1.toString(), "sin(60.0)");
       t.checkExpect(sin1.toSmartString(), "sin 60");
       t.checkExpect(sin2.containsVar(), true);
       t.checkExpect(sin2.eval(3.0 * Math.PI / 2), -1.0);
       t.checkExpect(sin2.toString(), "sin(x)");
       t.checkExpect(sin2.toSmartString(), "sin x");
       t.checkExpect(sin3.containsVar(), true);
       t.checkExpect(sin3.eval(3.0 * Math.PI / 4), -1.0);
       t.checkExpect(sin3.toString(), "sin((x * 2.0))");
       t.checkExpect(sin3.toSmartString(), "sin(x * 2)");
       t.checkExpect(sin4.simplify(), new Number(0.0));
       t.checkExpect(sin2.derivative(), new Product(new Cos(new Var()),
                                                    new Number(1.0)));
   }
   
   IExpression cos1 = parser.parse("cos(x - 2)");
   IExpression cos2 = parser.parse("cos ~x");
   IExpression cos3 = parser.parse("cos 60");
   public void testCos(Tester t) {
       t.checkExpect(cos1.containsVar(), true);
       t.checkExpect(cos1.eval(2.0), 1.0);
       t.checkExpect(cos1.toString(), "cos((x - 2.0))");
       t.checkExpect(cos1.toSmartString(), "cos(x - 2)");
       t.checkExpect(cos2.containsVar(), true);
       t.checkExpect(cos2.eval(-1 * Math.PI), -1.0);
       t.checkExpect(cos2.toString(), "cos(~(x))");
       t.checkExpect(cos2.toSmartString(), "cos ~x");
       t.checkExpect(cos3.containsVar(), false);
       t.checkInexact(cos3.eval(20.0), -0.9524129, .0001);
       t.checkExpect(cos3.toString(), "cos(60.0)");
       t.checkExpect(cos3.toSmartString(), "cos 60");
       t.checkExpect(cos3.derivative(), new Product(new Neg(new Sin(new Number(60.0))),
                                                    new Number(0.0)));
   }
   
   IExpression ln1 = parser.parse("ln x ^ 1");
   IExpression ln2 = parser.parse("ln(x - 3)");
   IExpression ln3 = parser.parse("ln 7.389056099");
   IExpression ln4 = parser.parse("ln x");
   public void testLn(Tester t) {
       t.checkExpect(ln1.containsVar(), true);
       t.checkExpect(ln1.eval(1.0), 0.0);
       t.checkExpect(ln1.toString(), "ln((x ^ 1.0))");
       t.checkExpect(ln1.toSmartString(), "ln x ^ 1");
       t.checkExpect(ln2.containsVar(), true);
       t.checkInexact(ln2.eval(Math.E + 3.0), 1.0, .0001);
       t.checkExpect(ln2.toString(), "ln((x - 3.0))");
       t.checkExpect(ln2.toSmartString(), "ln(x - 3)");
       t.checkExpect(ln3.containsVar(), false);
       t.checkInexact(ln3.eval(100.0), 2.0, .0001);
       t.checkExpect(ln3.toString(), "ln(7.389056099)");
       t.checkExpect(ln3.toSmartString(), "ln 7.389056099");
       t.checkExpect(ln4.derivative(), new Quotient(new Number(1.0),
                                                    new Var()));
   }
   
   public static void main(String[] args) {
       Tester.run(new JustinPaulExamples());
   }
}