import tester.*;

public class Examples08 {
	IExpressionParser parser = new IExpressionParser();

	// W1

	public void testvannadur10(Tester t) {
		IExpression two = new Number(2.0);
		IExpression pi = new Number(3.14159265358979323846);
		IExpression var1 = new Var();
		IExpression twoandsix = new Sum(two, new Number(6));
		IExpression fourtyminusvar = new Difference(new Number(40), var1);
		IExpression sixtimesfive = new Product(new Number(6), new Number(5));
		IExpression seventyoverx = new Quotient(new Number(70), var1);
		IExpression sinx = new Sin(var1);
		IExpression cospi = new Cos(pi);
		IExpression lnone = new Ln(new Number(1));
		IExpression sixcubed = new Exponentiation(new Number(6), new Number(3));
		IExpression xsqr = new Exponentiation(new Var(), two);
		IExpression negfive = new Neg(new Number(5));
		IExpression eightandfive = new Sum(twoandsix, new Number(5));
		IExpression fiftyovertwo = new Quotient(new Product(new Number(25), two), two);
		IExpression fiftyovertwo2 = new Quotient(new Number(50.0), new Number(2.0));
		IExpression lntwoandsix = new Ln(twoandsix);
		IExpression cosxsqr = new Cos(xsqr);
		IExpression sevenx = new Product(new Number(7), new Var());
		IExpression xtothetenth = new Exponentiation(new Var(), new Number(10));

		t.checkExpect(pi.containsVar(), false);
		t.checkExpect(var1.containsVar(), true);
		t.checkExpect(twoandsix.containsVar(), false);
		t.checkExpect(fourtyminusvar.containsVar(), true);
		t.checkExpect(sixtimesfive.containsVar(), false);
		t.checkExpect(seventyoverx.containsVar(), true);
		t.checkExpect(sinx.containsVar(), true);
		t.checkExpect(cospi.containsVar(), false);
		t.checkExpect(lnone.containsVar(), false);
		t.checkExpect(xsqr.containsVar(), true);
		t.checkExpect(negfive.containsVar(), false);
		t.checkInexact(two.eval(5.0), 2.0, 0.000001);
		t.checkInexact(var1.eval(10.0), 10.0, 0.000001);
		t.checkInexact(eightandfive.eval(17.0), 13.0, 0.000001);
		t.checkInexact(fourtyminusvar.eval(32.0), 8.0, 0.000001);
		t.checkInexact(sixtimesfive.eval(90.0), 30.0, 0.000001);
		t.checkInexact(seventyoverx.eval(7.0), 10.0, 0.000001);
		t.checkInexact(sinx.eval(0.0), 0.0, 0.000001);
		t.checkInexact(cospi.eval(-9435.0), -1.0, 0.000001);
		t.checkInexact(lnone.eval(7.0), 0.0, 0.000001);
		t.checkInexact(sixcubed.eval(24.0), 216.0, 0.000001);
		t.checkInexact(negfive.eval(2.0), -5.0, 0.000001);
		t.checkExpect(two.toString(), "2.0");
		t.checkExpect(var1.toString(), "x");
		t.checkExpect(eightandfive.toString(), "((2.0 + 6.0) + 5.0)");
		t.checkExpect(fourtyminusvar.toString(), "(40.0 - x)");
		t.checkExpect(sixtimesfive.toString(), "(6.0 * 5.0)");
		t.checkExpect(fiftyovertwo.toString(), "((25.0 * 2.0) / 2.0)");
		t.checkExpect(sinx.toString(), "sin(x)");
		t.checkExpect(cospi.toString(), "cos(3.141592653589793)");
		t.checkExpect(lnone.toString(), "ln(1.0)");
		t.checkExpect(xsqr.toString(), "(x ^ 2.0)");
		t.checkExpect(negfive.toString(), "~(5.0)");
		t.checkExpect(two.toSmartString(), "2");
		t.checkExpect(var1.toSmartString(), "x");
		t.checkExpect(eightandfive.toSmartString(), "2 + 6 + 5");
		t.checkExpect(fourtyminusvar.toSmartString(), "40 - x");
		t.checkExpect(sixtimesfive.toSmartString(), "6 * 5");
		t.checkExpect(fiftyovertwo.toSmartString(), "25 * 2 / 2");
		t.checkExpect(sinx.toSmartString(), "sin x");
		t.checkExpect(cosxsqr.toSmartString(), "cos x ^ 2");
		t.checkExpect(lntwoandsix.toSmartString(), "ln(2 + 6)");
		t.checkExpect(xsqr.toSmartString(), "x ^ 2");
		t.checkExpect(negfive.toSmartString(), "~5");
		t.checkExpect(twoandsix.simplify(), new Number(8.0));
		t.checkExpect(new Difference(new Number(7.0), new Number(0.0)).simplify(), new Number(7.0));
		t.checkExpect(new Product(new Number(2.0), new Number(1.0)).simplify(), new Number(2.0));
		t.checkExpect(new Exponentiation(new Number(0.0), new Number(70.0)).simplify(), new Number(0.0));
		t.checkExpect(new Cos(new Number(0.0)).simplify(), new Number(1.0));
		t.checkExpect(new Difference(new Number(2.0), new Number(2.0)).simplify(), new Number(0.0));
		t.checkExpect(new Sum(new Neg(new Number(2.0)), new Number(3.0)).simplify(), new Number(1.0));
		t.checkExpect(new Product(new Neg(new Number(3.0)), new Number(5.0)).simplify(), new Neg(new Number(15.0)));
		t.checkExpect(new Quotient(new Neg(new Number(20.0)), new Number(5.0)).simplify(), new Neg(new Number(4.0)));
		t.checkExpect(new Exponentiation(new Var(), new Neg(new Number(2.0))).simplify(), new Quotient(new Number(1.0), new Exponentiation(new Var(), new Number(2.0))));
		t.checkExpect(two.derivative(), new Number(0.0));
		t.checkExpect(var1.derivative(), new Number(1.0));
		t.checkExpect(twoandsix.derivative(), new Sum(new Number(2.0).derivative(), new Number(6.0).derivative()));
		t.checkExpect(fourtyminusvar.derivative(), new Difference(new Number(40.0).derivative(), new Number(1.0)));
		t.checkExpect(sevenx.derivative(), new Sum(new Product(new Number(7.0).derivative(), new Var()), new Product(new Var().derivative(), new Number(7.0))));
		t.checkExpect(fiftyovertwo2.derivative(), new Quotient(new Difference(new Product(new Number(50.0).derivative(), new Number(2.0)), 
				new Product(new Number(2.0).derivative(), new Number(50.0))),
				new Exponentiation(new Number(2.0), new Number(2.0))));
		t.checkExpect(sixcubed.derivative().toSmartString(), "3 * 6 ^ (3 - 1) * 0");
		t.checkExpect(new Sin(new Number(0.0)).derivative(), new Product(new Cos(new Number(0.0)), new Number(0.0).derivative()));
		t.checkExpect(cospi.derivative(), new Product(new Neg(new Sin(pi)), pi.derivative()));
		t.checkExpect(negfive.derivative(), new Neg(new Number(-5.0)).derivative());
		t.checkExpect(lnone.derivative(), new Quotient(new Number(1.0).derivative(), new Number(1.0)));
	}
	
	public void testjcockeri10(Tester t) {
		t.checkExpect(parser.parse("5.0").containsVar(), false);
		t.checkExpect(parser.parse("x").containsVar(), true);
		t.checkExpect(parser.parse("3.0 * 5.0 * x + x").containsVar(), true);
		t.checkExpect(parser.parse("3.0 ^ 2.0 / 5.0 - 1.0").containsVar(), false);
		t.checkExpect(parser.parse("11.0 + 3.0 * x ^ 2.0 * 7.0").containsVar(), true);
		t.checkExpect(parser.parse("1.5 - sin 0.0 / 2.0").containsVar(), false);
		t.checkExpect(parser.parse("6.0 / sin x + 2.5 ^ x").containsVar(), true);
		t.checkExpect(parser.parse("~(3.0 - 7.5 / 9.0)").containsVar(), false);
		t.checkExpect(parser.parse("sin x ^ 3.0").containsVar(), true);
		t.checkExpect(parser.parse("cos(9.0 + 7.0 ^ x - 2 * x)").containsVar(), true);
		t.checkExpect(parser.parse("cos sin ln 5.0").containsVar(), false);

		t.checkInexact(parser.parse("5.0").eval(3.0), 5.0, 0.000001);
		t.checkInexact(parser.parse("x").eval(7.5), 7.5, 0.000001);
		t.checkInexact(parser.parse("3.0 * 5.0 * x + x").eval(2.0), 32.0, 0.000001);
		t.checkInexact(parser.parse("3.0 ^ 2.0 / 5.0 - 1.0").eval(4.0), 0.8, 0.000001);
		t.checkInexact(parser.parse("11.0 + 3.0 * x ^ 2.0 * 7.0").eval(5.0), 536.0, 0.000001);
		t.checkInexact(parser.parse("1.5 - sin 0.0 / 2.0").eval(0.0), 1.5, 0.000001);
		t.checkInexact(parser.parse("6.0 / sin x + 2.5 ^ x").eval(1.0), (6 / Math.sin(1) + 2.5), 0.000001);
		t.checkInexact(parser.parse("~(3.0 - 7.5 / 9.0)").eval(10.0), -(3 - 7.5 / 9), 0.000001);
		t.checkInexact(parser.parse("sin x ^ 3.0").eval(0.0), 0.0, 0.000001);
		t.checkInexact(parser.parse("cos(9.0 + 7.0 ^ x - 2 * x)").eval(2.0), Math.cos(54.0), 0.000001);
		t.checkInexact(parser.parse("cos sin ln 5.0").eval(3.0), Math.cos(Math.sin(Math.log(5.0))), 0.000001);

		t.checkExpect(parser.parse("5.0").toString(), "5.0");
		t.checkExpect(parser.parse("x").toString(), "x");
		t.checkExpect(parser.parse("3.0 * 5.0 * x + x").toString(), "(((3.0 * 5.0) * x) + x)");
		t.checkExpect(parser.parse("3.0 ^ 2.0 / 5.0 - 1.0").toString(), "(((3.0 ^ 2.0) / 5.0) - 1.0)");
		t.checkExpect(parser.parse("11.0 + 3.0 * x ^ 2.0 * 7.0").toString(), "(11.0 + ((3.0 * (x ^ 2.0)) * 7.0))");
		t.checkExpect(parser.parse("1.5 - sin 0.0 / 2.0").toString(), "(1.5 - (sin(0.0) / 2.0))");
		t.checkExpect(parser.parse("6.0 / sin x + 2.5 ^ x").toString(), "((6.0 / sin(x)) + (2.5 ^ x))");
		t.checkExpect(parser.parse("~(3.0 - 7.5 / 9.0)").toString(), "~((3.0 - (7.5 / 9.0)))");
		t.checkExpect(parser.parse("sin x ^ 3.0").toString(), "sin((x ^ 3.0))");
		t.checkExpect(parser.parse("cos(9.0 + 7.0 ^ x - 2 * x)").toString(), "cos(((9.0 + (7.0 ^ x)) - (2.0 * x)))");
		t.checkExpect(parser.parse("cos sin ln 5.0").toString(), "cos(sin(ln(5.0)))");

		t.checkExpect(parser.parse("5.0").toSmartString(), "5");
		t.checkExpect(parser.parse("x").toSmartString(), "x");
		t.checkExpect(parser.parse("3.0 * 5.0 * x + x").toSmartString(), "3 * 5 * x + x");
		t.checkExpect(parser.parse("3.0 ^ 2.0 / 5.0 - 1.0").toSmartString(), "3 ^ 2 / 5 - 1");
		t.checkExpect(parser.parse("11.0 + 3.0 * x ^ 2.0 * 7.0").toSmartString(), "11 + 3 * x ^ 2 * 7");
		t.checkExpect(parser.parse("1.5 - sin 0.0 / 2.0").toSmartString(), "1.5 - sin 0 / 2");
		t.checkExpect(parser.parse("6.0 / sin x + 2.5 ^ x").toSmartString(), "6 / sin x + 2.5 ^ x");
		t.checkExpect(parser.parse("~(3.0 - 7.5 / 9.0)").toSmartString(), "~(3 - 7.5 / 9)");
		t.checkExpect(parser.parse("sin x ^ 3.0").toSmartString(), "sin x ^ 3");
		t.checkExpect(parser.parse("cos(9.0 + 7.0 ^ x - 2 * x)").toSmartString(), "cos(9 + 7 ^ x - 2 * x)");
		t.checkExpect(parser.parse("cos sin ln 5.0").toSmartString(), "cos sin ln 5");

		t.checkExpect(parser.parse("5.0 - 7.0 * x ^ 2 + 0.0").simplify(), parser.parse("5.0 - 7.0 * x ^ 2"));
		t.checkExpect(parser.parse("1.5 - sin 0.0 / 1.0").simplify(), parser.parse("1.5"));
		t.checkExpect(parser.parse("11.0 + 3.0 * x ^ 2.0 * 0.0").simplify(), parser.parse("11.0"));
		t.checkExpect(parser.parse("ln(sin(x) ^ 0.0)").simplify(), parser.parse("0.0"));
		t.checkExpect(parser.parse("(3.0 * 5.0 * x) + (3.0 * 5.0 * x)").simplify().toSmartString(), "2 * (15 * x)");
		t.checkExpect(parser.parse("-2.5 * x").simplify().toSmartString(), "~(2.5 * x)");
		t.checkExpect(parser.parse("3.0 ^ x - ~2.5 * x").simplify(), parser.parse("3.0 ^ x + 2.5 * x"));
		t.checkExpect(parser.parse("sin 0.0 / 2.0 ^ 5.0 * x").simplify(), parser.parse("0"));
		t.checkExpect(parser.parse("6.0 / sin x / 2.5 ^ x").simplify().toSmartString(), "6 / (sin x * 2.5 ^ x)");

		t.checkExpect(parser.parse("5.0").derivative(), parser.parse("0"));
		t.checkExpect(parser.parse("x").derivative(), parser.parse("1"));
		t.checkExpect(parser.parse("7.1 + x").derivative(), parser.parse("0 + 1"));
		t.checkExpect(parser.parse("3.0 ^ 2.0 - 1.0").derivative().simplify(), new Number(0));
		t.checkExpect(parser.parse("x * x").derivative(), parser.parse("1 * x + 1 * x"));
		t.checkExpect(parser.parse("x / 2.0").derivative(), parser.parse("(1 * 2 - 0 * x) / 2 ^ 2"));
		t.checkExpect(parser.parse("6.0 ^ x").derivative().toSmartString(), "6 ^ x * (1 * ln 6 + 0 * x / 6)");
		t.checkExpect(parser.parse("sin(3.0 * x)").derivative(), parser.parse("cos(3 * x) * (0 * x + 1 * 3)"));
		t.checkExpect(parser.parse("cos 4 ^ 1").derivative().toSmartString(), "~sin 4 ^ 1 * (1 * 4 ^ (1 - 1) * 0)");
		t.checkExpect(parser.parse("~(3.0 + x)").derivative(), parser.parse("~(0 + 1)"));
		t.checkExpect(parser.parse("ln(5.0 ^ 3.0 - x)").derivative().toSmartString(),
			"(3 * 5 ^ (3 - 1) * 0 - 1) / (5 ^ 3 - x)");
	}

	public void testndo10(Tester t) {
		IExpression seven = new Number(7.0);
		IExpression two = new Number(2.0);
		IExpression six = new Number(6.0);
		IExpression v1 = new Var();
		IExpression v2 = new Var();
		IExpression twoPlusSeven = new Sum(two, seven);
		IExpression twoMinusSeven = new Difference(two, seven);
		IExpression twoTimesSeven = new Product(two, seven);
		IExpression twoOverSix = new Quotient(two, six);
		IExpression twoExpoSeven = new Exponentiation(two, seven);
		IExpression L1 = new Ln(two);
		IExpression C1 = new Cos(two);
		IExpression S1 = new Sin(two);
		IExpression negSix = new Neg(six);
		IExpression p1 = new Product(two, v1);
		IExpression q1 = new Quotient(v2, seven);
		IExpression e1 = new Exponentiation(two, v1);
		IExpression m1 = new Ln(e1);
		IExpression mNeg = new Neg(p1);
		IExpression a = new Sin(new Ln(twoExpoSeven));
		IExpression a2 = new Sin(new Ln(new Product(new Number(2.0), new Number(7.0))));

		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(two.containsVar(), false);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkExpect(twoMinusSeven.containsVar(), false);
		t.checkExpect(twoTimesSeven.containsVar(), false);
		t.checkExpect(twoOverSix.containsVar(), false);
		t.checkExpect(twoExpoSeven.containsVar(), false);
		t.checkExpect(p1.containsVar(), true);
		t.checkExpect(q1.containsVar(), true);
		t.checkExpect(e1.containsVar(), true);
		t.checkExpect(m1.containsVar(), true);
		t.checkExpect(mNeg.containsVar(), true);
		t.checkInexact(seven.eval(10.0), 7.0, 0.000001);
		t.checkInexact(two.eval(41.0), 2.0, 0.000001);
		t.checkInexact(twoPlusSeven.eval(-57.1), 9.0, 0.000001);
		t.checkInexact(twoMinusSeven.eval(400.1), -5.0, 0.000001);
		t.checkInexact(twoTimesSeven.eval(1.1), 14.0, 0.000001);
		t.checkInexact(twoOverSix.eval(32.5), 0.333333333333, 0.000001);
		t.checkInexact(twoExpoSeven.eval(4.1), 128.0, 0.000001);
		t.checkInexact(L1.eval(3.7), 0.693147, 0.000001);
		t.checkInexact(C1.eval(54.1), -0.4161468365471424, 0.000001);
		t.checkInexact(S1.eval(1.1), 0.9092974268256817, 0.000001);
		t.checkInexact(p1.eval(1.1), 2.2, 0.000001);
		t.checkInexact(q1.eval(14.0), 2.0, 0.000001);
		t.checkInexact(e1.eval(3.0), 8.0, 0.000001);
		t.checkInexact(m1.eval(3.0), 2.07944, 0.000001);
		t.checkInexact(negSix.eval(1.1), -6.0, 0.000001);
		t.checkInexact(mNeg.eval(3.0), -6.0, 0.000001);
		t.checkExpect(negSix.toString(), "~(6.0)");
		t.checkExpect(mNeg.toString(), "~((2.0 * x))");
		t.checkExpect(twoMinusSeven.toString(), "(2.0 - 7.0)");
		t.checkExpect(twoOverSix.toString(), "(2.0 / 6.0)");
		t.checkExpect(a.toString(), "sin(ln((2.0 ^ 7.0)))");
		t.checkExpect(mNeg.toString(), "~((2.0 * x))");
		t.checkExpect(m1.toString(), "ln((2.0 ^ x))");
		t.checkExpect(seven.toString(), "7.0");
		t.checkExpect(twoPlusSeven.toString(), "(2.0 + 7.0)");
		t.checkExpect(S1.toString(), "sin(2.0)");
		t.checkExpect(v1.toString(), "x");
		t.checkExpect(negSix.toSmartString(), "~6");
		t.checkExpect(mNeg.toSmartString(), "~(2 * x)");
		t.checkExpect(twoMinusSeven.toSmartString(), "2 - 7");
		t.checkExpect(twoOverSix.toSmartString(), "2 / 6");
		t.checkExpect(a2.toSmartString(), "sin ln(2 * 7)");
		t.checkExpect(a.toSmartString(), "sin ln 2 ^ 7");
		t.checkExpect(mNeg.toSmartString(), "~(2 * x)");
		t.checkExpect(m1.toSmartString(), "ln 2 ^ x");
		t.checkExpect(seven.toSmartString(), "7");
		t.checkExpect(twoPlusSeven.toSmartString(), "2 + 7");
		t.checkExpect(S1.toSmartString(), "sin 2");
		t.checkExpect(v1.toSmartString(), "x");
		IExpression s1 = new Number(0.0);
		IExpression s2 = new Number(42.12);
		IExpression d1 = new Sum(s2, s1);
		IExpression d = new Sum(s1, s2);
		IExpression d2 = new Cos(new Number(0.0));
		IExpression d3 = new Quotient(new Neg(new Number(3.4)), new Var());
		IExpression d4 = new Quotient(new Number(2.0), new Quotient(new Var(),
				new Number(1.0)));
		IExpression d5 = new Exponentiation(new Product(new Var(),
				new Number(0.0)), new Number(45.2));
		IExpression d6 = new Exponentiation(new Var(), new Number(0.0));
		IExpression d7 = new Difference(new Number(0.0), new Number(2.1));
		IExpression d8 = new Product(new Number(1.0), new Number(2.4));
		IExpression d9 = new Sin(new Number(0.0));
		IExpression d10 = new Product(new Var(), new Var());
		IExpression d11 = new Neg(new Neg(new Var()));
		IExpression d12 = new Difference(new Neg(new Var()), new Number(13.0));
		IExpression d13 = new Product(new Number(4.12), new Quotient(new Var(),
				new Number(1.6)));
		t.checkExpect(seven.simplify(), seven);
		t.checkExpect(d.simplify(), s2);
		t.checkExpect(d1.simplify(), s2);
		t.checkExpect(d2.simplify(), new Number(1.0));
		t.checkExpect(d3.simplify(), new Neg(new Quotient(new Number(3.4),
				new Var())));
		t.checkExpect(d5.simplify(), new Number(0.0));
		t.checkExpect(mNeg.simplify(), mNeg);
		t.checkExpect(v2.simplify(), v2);
		t.checkExpect(s2.simplify(), s2);
		t.checkExpect(d7.simplify(), new Neg(new Number(2.1)));
		t.checkExpect(d8.simplify(), new Number(2.4));
		t.checkExpect(d6.simplify(), new Number(1.0));
		t.checkExpect(d9.simplify(), new Number(0.0));
		t.checkExpect(d10.simplify(), new Exponentiation(new Var(), new Number(
				2.0)));
		t.checkExpect(d11.simplify(), new Var());
		t.checkExpect(d12.simplify(), new Neg(
				new Sum(new Var(), new Number(13.0))));
		t.checkExpect(d13.simplify(), new Quotient(new Product(new Number(4.12),
				new Var()), new Number(1.6)));
		t.checkExpect(d4.simplify(), new Quotient(new Number(2.0), new Var()));
		t.checkExpect(d2.simplify().toSmartString(), "1");
		t.checkExpect(d3.simplify().toSmartString(), "~(3.4 / x)");
		t.checkExpect(d4.simplify().toSmartString(), "2 / x");
		t.checkExpect(d5.simplify().toSmartString(), "0");
		t.checkExpect(d.simplify().toSmartString(), "42.12");
		IExpression de1 = new Sum(new Number(3.0), new Var());
		IExpression de2 = new Difference(new Number(3.0), new Var());
		IExpression de3 = new Sum(new Number(2.0), new Var());
		IExpression de4 = new Ln(new Number(4.0));
		IExpression de5 = new Exponentiation(new Number(2.0), new Var());
		IExpression de6 = new Sin(new Sum(new Var(), new Number(3.0)));
		IExpression de7 = new Quotient(new Exponentiation(new Var(), new Number(
				3.0)), new Number(3.0));
		IExpression de8 = new Exponentiation(new Sum(new Number(3.0), new Var()),
				new Number(2.0));
		IExpression de9 = new Exponentiation(new Number(4.0), new Var());
		IExpression de10 = new Sin(
				new Exponentiation(new Var(), new Number(13.0)));
		IExpression de11 = new Ln(new Exponentiation(new Var(), new Number(4.0)));
		t
		.checkExpect(de1.derivative(), new Sum(new Number(0.0), new Number(
				1.0)));
		t.checkExpect(de2.derivative().simplify(), new Neg(new Number(1.0)));
		t.checkExpect(de3.derivative().simplify(), new Number(1.0));
		t.checkExpect(de4.derivative().simplify(), new Number(0.0));
		t
		.checkExpect(de5.derivative().toSmartString(), "2 ^ x * (1 * ln 2 + 0 * x / 2)");
		t.checkExpect(de6.derivative().simplify(), new Cos(new Sum(new Var(),
				new Number(3.0))));
		t.checkExpect(de7.derivative().simplify(), new Quotient(new Product(
				new Product(new Number(3.0), new Exponentiation(new Var(),
						new Number(2.0))), new Number(3.0)), new Number(9.0)));
		t.checkExpect(de8.derivative().simplify(), new Product(new Number(2.0),
				new Sum(new Number(3.0), new Var())));
		t.checkExpect(de9.derivative().simplify().toSmartString(), "4 ^ x * 1.3862943611198906");
		t.checkExpect(de10.derivative().simplify(), new Product(new Cos(
				new Exponentiation(new Var(), new Number(13.0))),
				new Product(new Number(13.0), new Exponentiation(new Var(),
						new Number(12.0)))));
		t.checkExpect(de11.derivative().simplify(), new Quotient(new Product(
				new Number(4.0), new Exponentiation(new Var(), new Number(3.0))),
				new Exponentiation(new Var(), new Number(4.0))));
	}
  
	public void testeharshfi10(Tester t){
		IExpression x = new Var();
		double y = 2.0;
		IExpression zero = new Number(0.0);
		IExpression one = new Number(1.0);
		IExpression two = new Number(2.0);
		IExpression three = new Number(3.0);
		IExpression four = new Number(4.0);
		IExpression five = new Number(5.0);
		IExpression six = new Number(6.0);
		IExpression seven = new Number(7.0);
		IExpression eight = new Number(8.0);
		IExpression nine = new Number(9.0);
		IExpression ten = new Number(10.0);
		IExpression rule4 = new Difference(zero, four);
		IExpression rule8 = new Product(one, five);
		IExpression rule12 = new Exponentiation(ten, zero);
		IExpression rule16 = new Sin(zero);
		IExpression rule20 = new Product(x, x);
		IExpression rule24 = new Neg(new Neg(five));
		IExpression rule28 = new Difference(new Neg(x), three);
		IExpression rule32 = new Product(two, new Quotient(x, three));
		IExpression rule36 = new Quotient(ten, new Quotient(one, x));

		IExpression sum1 = new Sum(five, three);
		IExpression sum2 = new Sum(five, x);
		IExpression difference1 = new Difference(ten, five);
		IExpression difference2 = new Difference(ten, x);
		IExpression product1 = new Product(two, three);
		IExpression product2 = new Product(two, x);
		IExpression quotient1 = new Quotient(ten, two);
		IExpression quotient2 = new Quotient(x, two);
		IExpression exponent1 = new Exponentiation(two, three);
		IExpression exponent2 = new Exponentiation(two, x);
		IExpression sin1 = new Sin(zero);
		IExpression sin2 = new Sin(x);
		IExpression cos1 = new Cos(zero);
		IExpression cos2 = new Cos(x);
		IExpression neg1 = new Neg(five);
		IExpression neg2 = new Neg(x);
		IExpression ln1 = new Ln(ten);
		IExpression ln2 = new Ln(x);
		IExpression complex1 = new Sum(new Sum(new Exponentiation(new Product(two, x), two), new Product(three, x)), three);
		IExpression complex2 = new Quotient(new Product(new Sum(x, ten), two), five);
		IExpression deriv1 = new Number(0.0);             
		//tests of containsVar
		t.checkExpect(six.containsVar(), false);
		t.checkExpect(x.containsVar(), true);
		t.checkExpect(sum1.containsVar(), false);
		t.checkExpect(sum2.containsVar(), true);
		t.checkExpect(difference1.containsVar(), false);
		t.checkExpect(difference2.containsVar(), true);
		t.checkExpect(product1.containsVar(), false);
		t.checkExpect(product2.containsVar(), true);
		t.checkExpect(quotient1.containsVar(), false);
		t.checkExpect(quotient2.containsVar(), true); //10
		t.checkExpect(exponent1.containsVar(), false);
		t.checkExpect(exponent2.containsVar(), true);
		t.checkExpect(sin1.containsVar(), false);
		t.checkExpect(sin2.containsVar(), true);
		t.checkExpect(cos1.containsVar(), false);
		t.checkExpect(cos2.containsVar(), true);
		t.checkExpect(neg1.containsVar(), false);
		t.checkExpect(neg2.containsVar(), true);
		t.checkExpect(ln1.containsVar(), false);
		t.checkExpect(ln2.containsVar(), true); //20

		//tests of eval
		t.checkInexact(five.eval(y), 5.0, 0.000001);
		t.checkInexact(x.eval(y), 2.0, 0.000001);
		t.checkInexact(sum1.eval(y), 8.0, 0.000001);
		t.checkInexact(sum2.eval(y), 7.0, 0.000001);
		t.checkInexact(difference1.eval(y), 5.0, 0.000001);
		t.checkInexact(difference2.eval(y), 8.0, 0.000001);
		t.checkInexact(product1.eval(y), 6.0, 0.000001);
		t.checkInexact(product2.eval(y), 4.0, 0.000001);
		t.checkInexact(quotient1.eval(y), 5.0, 0.000001);
		t.checkInexact(quotient2.eval(y), 1.0, 0.000001); //30
		t.checkInexact(exponent1.eval(y), 8.0, 0.000001);
		t.checkInexact(exponent2.eval(y), 4.0, 0.000001);
		t.checkInexact(sin1.eval(y), 0.0, 0.000001);
		t.checkInexact(sin2.eval(y), 0.9092974268256817, 0.000001);
		t.checkInexact(cos1.eval(y), 1.0, 0.000001);
		t.checkInexact(cos2.eval(y), -0.4161468365471424, 0.000001);
		t.checkInexact(neg1.eval(y), -5.0, 0.000001);
		t.checkInexact(neg2.eval(y), -2.0, 0.000001);
		t.checkInexact(ln1.eval(y), 2.302585092994046, 0.000001);
		t.checkInexact(ln2.eval(y), 0.6931471805599453, 0.000001);

		//tests of toString
		t.checkExpect(five.toString(), "5.0"); //40
		t.checkExpect(x.toString(), "x");
		t.checkExpect(sum1.toString(), "(5.0 + 3.0)");
		t.checkExpect(sum2.toString(), "(5.0 + x)");
		t.checkExpect(difference1.toString(), "(10.0 - 5.0)");
		t.checkExpect(difference2.toString(), "(10.0 - x)");
		t.checkExpect(product1.toString(), "(2.0 * 3.0)");
		t.checkExpect(product2.toString(), "(2.0 * x)");
		t.checkExpect(quotient1.toString(), "(10.0 / 2.0)");
		t.checkExpect(quotient2.toString(), "(x / 2.0)");
		t.checkExpect(exponent1.toString(), "(2.0 ^ 3.0)"); //50
		t.checkExpect(exponent2.toString(), "(2.0 ^ x)");
		t.checkExpect(sin1.toString(), "sin(0.0)");
		t.checkExpect(sin2.toString(), "sin(x)");
		t.checkExpect(cos1.toString(), "cos(0.0)");
		t.checkExpect(cos2.toString(), "cos(x)");
		t.checkExpect(neg1.toString(), "~(5.0)");
		t.checkExpect(neg2.toString(), "~(x)");
		t.checkExpect(ln1.toString(), "ln(10.0)");
		t.checkExpect(ln2.toString(), "ln(x)");

		//tests of toSmartString
		t.checkExpect(five.toSmartString(), "5"); //60
		t.checkExpect(x.toSmartString(), "x");
		t.checkExpect(sum1.toSmartString(), "5 + 3");
		t.checkExpect(sum2.toSmartString(), "5 + x");
		t.checkExpect(difference1.toSmartString(), "10 - 5");
		t.checkExpect(difference2.toSmartString(), "10 - x");
		t.checkExpect(product1.toSmartString(), "2 * 3");
		t.checkExpect(product2.toSmartString(), "2 * x");
		t.checkExpect(quotient1.toSmartString(), "10 / 2");
		t.checkExpect(quotient2.toSmartString(), "x / 2"); //70
		t.checkExpect(exponent1.toSmartString(), "2 ^ 3");
		t.checkExpect(exponent2.toSmartString(), "2 ^ x");
		t.checkExpect(sin1.toSmartString(), "sin 0");
		t.checkExpect(sin2.toSmartString(), "sin x");
		t.checkExpect(cos1.toSmartString(), "cos 0");
		t.checkExpect(cos2.toSmartString(), "cos x");
		t.checkExpect(neg1.toSmartString(), "~5");
		t.checkExpect(neg2.toSmartString(), "~x");
		t.checkExpect(ln1.toSmartString(), "ln 10");
		t.checkExpect(ln2.toSmartString(), "ln x"); //80
		t.checkExpect(complex1.toSmartString(), "(2 * x) ^ 2 + 3 * x + 3");
		t.checkExpect(complex2.toSmartString(), "(x + 10) * 2 / 5");

		//tests of simplify
		t.checkExpect(rule4.simplify(), new Neg(four));
		t.checkExpect(rule8.simplify(), five);
		t.checkExpect(rule12.simplify(), one);
		t.checkExpect(rule16.simplify(), zero);
		t.checkExpect(rule20.simplify(), new Exponentiation(x, two));
		t.checkExpect(rule24.simplify(), five);
		t.checkExpect(rule28.simplify(), new Neg(new Sum(x, three)));
		t.checkExpect(rule32.simplify(), new Quotient(new Product(two, x), three)); //90
		t.checkExpect(rule36.simplify(), new Product(ten, x));

		//tests of derivative
		t.checkExpect(five.derivative(), zero);
		t.checkExpect(x.derivative(), one);
		t.checkExpect(sum1.derivative(), new Sum(zero, zero));
		t.checkExpect(difference2.derivative(), new Difference(zero, one));
		t.checkExpect(product1.derivative(), new Sum(new Product(zero, three), new Product(zero, two)));
		t.checkExpect(quotient2.derivative(), new Quotient(new Difference(new Product(one, two), new Product(zero, x)), new Exponentiation(two, two)));
		t.checkExpect(exponent1.derivative(), new Product(new Product(three, new Exponentiation(two, new Difference(three, one))), zero));
		t.checkExpect(exponent2.derivative().toSmartString(), "2 ^ x * (1 * ln 2 + 0 * x / 2)");
		t.checkExpect(sin1.derivative(), new Product(new Cos(zero), zero));
		t.checkExpect(cos2.derivative(), new Product(new Neg(new Sin(x)), one));
		t.checkExpect(neg1.derivative(), new Neg(zero));
		t.checkExpect(ln1.derivative(), new Quotient(zero, ten));
		t.checkExpect(complex1.derivative(), new Sum(new Sum(new Product(new Product(two, new Exponentiation(new Product(two, x), new Difference(two, one))), new Sum(new Product(zero, x), new Product(one, two))), new Sum(new Product(zero, x), new Product(one, three))), zero));
		t.checkExpect(complex2.derivative(), new Quotient(new Difference(new Product(new Sum(new Product(new Sum(one, zero), two), new Product(zero, new Sum(x, ten))), five), new Product(zero, new Product(new Sum(x, ten), two))), new Exponentiation(five, two)));
	}
	
	public void testp1jadav10(Tester t) {
		IExpression x = new Var();
		IExpression zero = new Number(0.0);
		IExpression one = new Number(1.0);
		IExpression two = new Number(2.0);
		IExpression three = new Number(3.0);
		IExpression four = new Number(4.0);
		IExpression five = new Number(5.0);
		IExpression six = new Number(6.0);
		IExpression seven = new Number(7.0);
		IExpression eight = new Number(8.0);
		IExpression nine = new Number(9.0);
		IExpression ten = new Number(10.0);

		IExpression rule2 = new Sum(six, zero);
		IExpression rule6 = new Quotient(zero, nine);
		IExpression rule10 = new Product(zero, new Sum(x, seven));
		IExpression rule14 = new Exponentiation(four, one);
		IExpression rule18 = new Neg(zero);
		IExpression rule22 = new Quotient(six, six);
		IExpression rule26 = new Sum(seven, new Neg(six));
		IExpression rule30 = new Product(two, new Neg(nine));
		IExpression rule34 = new Quotient(one, new Neg(four));
		IExpression rule38 = new Exponentiation(new Exponentiation (new Var(), six), seven);

		IExpression ex1 = new Exponentiation(two, new Exponentiation(three, new Product(x, five)));
		IExpression ex2 = new Sum(x, new Exponentiation(three, two));
		IExpression ex3 = new Difference(new Sum(four, five), new Product(three, two));
		IExpression ex4 = new Sin(x);
		IExpression ex5 = new Cos(seven);
		IExpression ex6 = new Neg(x);
		IExpression ex7 = new Ln(x);
		IExpression ex8 = new Sum(x, nine);
		IExpression ex9 = new Difference(x, three);
		IExpression ex10 = new Product(x, three);
		IExpression ex11 = new Quotient(x, three);
		IExpression ex12 = new Exponentiation(x, three);
		IExpression ex13 = new Exponentiation(three, x);
		IExpression ex14 = new Quotient(two, new Difference(new Exponentiation(x, two), eight));


		t.checkExpect(five.containsVar(), false);
		t.checkExpect(x.containsVar(), true);
		t.checkExpect(ex1.containsVar(), true);
		t.checkExpect(ex2.containsVar(), true);
		t.checkExpect(ex3.containsVar(), false);
		t.checkExpect(ex4.containsVar(), true);
		t.checkExpect(ex5.containsVar(), false);
		t.checkExpect(ex6.containsVar(), true);
		t.checkExpect(ex7.containsVar(), true);
		t.checkExpect(ex8.containsVar(), true);
		t.checkExpect(ex9.containsVar(), true);

		t.checkInexact(four.eval(8.0), 4.0, 0.000001);
		t.checkInexact(x.eval(14.0), 14.0, 0.000001);
		t.checkInexact(one.eval(0.0), 1.0, 0.000001);
		t.checkInexact(x.eval(5.0), 5.0, 0.000001);
		t.checkInexact(ex3.eval(8.0), 3.0, 0.000001);
		t.checkInexact(ex4.eval(5.0),  -0.9589242747, 0.000001);
		t.checkInexact(ex5.eval(9.0),  0.7539022543433046, 0.000001);
		t.checkInexact(ex6.eval(15.0), -15.0, 0.000001);
		t.checkInexact(ex7.eval(5.0),  1.609437912, 0.000001);
		t.checkInexact(ex8.eval(3.0), 12.0, 0.000001);
		t.checkInexact(ex9.eval(7.0), 4.0, 0.000001);

		t.checkExpect(ex1.toString(), "(2.0 ^ (3.0 ^ (x * 5.0)))");
		t.checkExpect(ex2.toString(), "(x + (3.0 ^ 2.0))");
		t.checkExpect(ex3.toString(), "((4.0 + 5.0) - (3.0 * 2.0))");
		t.checkExpect(ex4.toString(), "sin(x)");
		t.checkExpect(ex5.toString(), "cos(7.0)");
		t.checkExpect(ex6.toString(), "~(x)");
		t.checkExpect(ex7.toString(), "ln(x)");
		t.checkExpect(ex8.toString(), "(x + 9.0)");
		t.checkExpect(ex9.toString(), "(x - 3.0)");
		t.checkExpect(ex10.toString(), "(x * 3.0)");
		t.checkExpect(ex14.toString(), "(2.0 / ((x ^ 2.0) - 8.0))");

		t.checkExpect(ex1.toSmartString(), "2 ^ 3 ^ (x * 5)");
		t.checkExpect(ex2.toSmartString(), "x + 3 ^ 2");
		t.checkExpect(ex3.toSmartString(), "4 + 5 - 3 * 2");
		t.checkExpect(ex4.toSmartString(), "sin x");
		t.checkExpect(ex5.toSmartString(), "cos 7");
		t.checkExpect(ex6.toSmartString(), "~x");
		t.checkExpect(ex7.toSmartString(), "ln x");
		t.checkExpect(ex8.toSmartString(), "x + 9");
		t.checkExpect(ex9.toSmartString(), "x - 3");
		t.checkExpect(ex10.toSmartString(), "x * 3");
		t.checkExpect(ex14.toSmartString(), "2 / (x ^ 2 - 8)");


		t.checkExpect(rule2.simplify(), six);
		t.checkExpect(rule6.simplify(), zero);
		t.checkExpect(rule10.simplify(), zero);
		t.checkExpect(rule14.simplify(), four);
		t.checkExpect(rule18.simplify(), zero);
		t.checkExpect(rule22.simplify(), one);
		t.checkExpect(rule26.simplify(), new Number(1));
		t.checkExpect(rule30.simplify().toSmartString(), "~18");
		t.checkExpect(rule34.simplify(), new Neg(new Number(0.25)));
		t.checkExpect(rule38.simplify().toSmartString(), "x ^ 42");

		t.checkExpect(ex4.derivative().simplify(), new Cos(x));
		t.checkExpect(ex5.derivative().simplify(), zero);
		t.checkExpect(ex6.derivative().simplify(), new Neg(one));
		t.checkExpect(ex7.derivative().simplify(), new Quotient(one, x));
		t.checkExpect(five.derivative().simplify(), zero);
		t.checkExpect(x.derivative().simplify(), one);
		t.checkExpect(ex8.derivative().simplify(), one);
		t.checkExpect(ex9.derivative().simplify(), one);
		t.checkExpect(ex10.derivative().simplify(), three);
		t.checkExpect(ex11.derivative().simplify(), new Number(1.0 / 3));
		t.checkExpect(ex12.derivative().simplify(), new Product(three, new Exponentiation(x, two)));
		t.checkExpect(ex13.derivative().simplify().toSmartString(), "3 ^ x * 1.0986122886681098");
	}

	public void testP2jadav10(Tester t) {
		IExpression x = new Var();
		IExpression zero = new Number(0.0);
		IExpression one = new Number(1.0);
		IExpression two = new Number(2.0);
		IExpression three = new Number(3.0);
		IExpression four = new Number(4.0);
		IExpression five = new Number(5.0);
		IExpression six = new Number(6.0);
		IExpression seven = new Number(7.0);
		IExpression eight = new Number(8.0);
		IExpression nine = new Number(9.0);
		IExpression ten = new Number(10.0);

		IExpression rule2 = new Sum(five, zero);
		IExpression rule6 = new Quotient(zero, eight);
		IExpression rule10 = new Product(zero, new Sum(x, four));
		IExpression rule14 = new Exponentiation(ten, one);
		IExpression rule18 = new Neg(zero);
		IExpression rule22 = new Quotient(five, five);
		IExpression rule26 = new Sum(new Var(), new Neg(six));
		IExpression rule30 = new Product(new Var(), new Neg(nine));
		IExpression rule34 = new Quotient(one, new Neg(new Var()));
		IExpression rule38 = new Exponentiation(new Exponentiation (new Var(), two), new Var());

		IExpression exmp1 = new Exponentiation(two, new Exponentiation(three, new Product(x, five)));
		IExpression exmp2 = new Sum(x, new Exponentiation(three, two));
		IExpression exmp3 = new Difference(new Sum(four, five), new Product(three, two));
		IExpression exmp4 = new Sin(x);
		IExpression exmp5 = new Cos(seven);
		IExpression exmp6 = new Neg(x);
		IExpression exmp7 = new Ln(x);
		IExpression exmp8 = new Sum(x, nine);
		IExpression exmp9 = new Difference(x, three);
		IExpression exmp10 = new Product(x, three);
		IExpression exmp11 = new Quotient(x, three);
		IExpression exmp12 = new Exponentiation(x, three);
		IExpression exmp13 = new Exponentiation(three, x);
		IExpression exmp14 = new Quotient(two, new Difference(new Exponentiation(x, two), eight));
		//containsVar
		t.checkExpect(five.containsVar(), false);
		t.checkExpect(x.containsVar(), true);
		t.checkExpect(exmp1.containsVar(), true);
		t.checkExpect(exmp2.containsVar(), true);
		t.checkExpect(exmp3.containsVar(), false);
		t.checkExpect(exmp4.containsVar(), true);
		t.checkExpect(exmp5.containsVar(), false);
		t.checkExpect(exmp6.containsVar(), true);
		t.checkExpect(exmp7.containsVar(), true);
		t.checkExpect(exmp8.containsVar(), true);
		t.checkExpect(exmp9.containsVar(), true);

		//eval
		t.checkInexact(x.eval(20.0), 20.0, 0.000001);
		t.checkInexact(x.eval(3.0), 3.0, 0.000001);
		t.checkInexact(ten.eval(0.0), 10.0, 0.000001);
		t.checkInexact(five.eval(10.0), 5.0, 0.000001);
		t.checkInexact(exmp3.eval(2.0), 3.0, 0.000001);
		t.checkInexact(exmp4.eval(4.0),  -0.7568024953079282, 0.000001);
		t.checkInexact(exmp5.eval(5.0),  0.7539022543433046, 0.000001);
		t.checkInexact(exmp6.eval(6.0), -6.0, 0.000001);
		t.checkInexact(exmp7.eval(7.0),  1.9459101490553132, 0.000001);
		t.checkInexact(exmp8.eval(8.0), 17.0, 0.000001);
		t.checkInexact(exmp9.eval(5.0), 2.0, 0.000001);

		//toString
		t.checkExpect(exmp1.toString(), "(2.0 ^ (3.0 ^ (x * 5.0)))");
		t.checkExpect(exmp2.toString(), "(x + (3.0 ^ 2.0))");
		t.checkExpect(exmp3.toString(), "((4.0 + 5.0) - (3.0 * 2.0))");
		t.checkExpect(exmp4.toString(), "sin(x)");
		t.checkExpect(exmp5.toString(), "cos(7.0)");
		t.checkExpect(exmp6.toString(), "~(x)");
		t.checkExpect(exmp7.toString(), "ln(x)");
		t.checkExpect(exmp8.toString(), "(x + 9.0)");
		t.checkExpect(exmp9.toString(), "(x - 3.0)");
		t.checkExpect(exmp10.toString(), "(x * 3.0)");
		t.checkExpect(exmp14.toString(), "(2.0 / ((x ^ 2.0) - 8.0))");

		//toSmartString
		t.checkExpect(exmp1.toSmartString(), "2 ^ 3 ^ (x * 5)");
		t.checkExpect(exmp2.toSmartString(), "x + 3 ^ 2");
		t.checkExpect(exmp3.toSmartString(), "4 + 5 - 3 * 2");
		t.checkExpect(exmp4.toSmartString(), "sin x");
		t.checkExpect(exmp5.toSmartString(), "cos 7");
		t.checkExpect(exmp6.toSmartString(), "~x");
		t.checkExpect(exmp7.toSmartString(), "ln x");
		t.checkExpect(exmp8.toSmartString(), "x + 9");
		t.checkExpect(exmp9.toSmartString(), "x - 3");
		t.checkExpect(exmp10.toSmartString(), "x * 3");
		t.checkExpect(exmp14.toSmartString(), "2 / (x ^ 2 - 8)");

		//simplify
		//First letter of last name is J = 10.
		//10 % 4 = 2. Rules with the same remainder: 2, 6, 10, 14, 18, 22, 26, 30, 34, 38
		t.checkExpect(rule2.simplify(), five);
		t.checkExpect(rule6.simplify(), zero);
		t.checkExpect(rule10.simplify(), zero);
		t.checkExpect(rule14.simplify(), ten);
		t.checkExpect(rule18.simplify(), zero);
		t.checkExpect(rule22.simplify(), one);
		t.checkExpect(rule26.simplify(), new Difference(new Var(), six));
		t.checkExpect(rule30.simplify().toSmartString(), "~(x * 9)");
		t.checkExpect(rule34.simplify().toSmartString(), "~(1 / x)");
		t.checkExpect(rule38.simplify().toSmartString(), "x ^ (2 * x)");

		//derivative
		t.checkExpect(five.derivative().simplify(), zero);
		t.checkExpect(x.derivative().simplify(), one);
		t.checkExpect(exmp4.derivative().simplify(), new Cos(x));
		t.checkExpect(exmp5.derivative().simplify(), zero);
		t.checkExpect(exmp6.derivative().simplify(), new Neg(one));
		t.checkExpect(exmp7.derivative().simplify(), new Quotient(one, x));
		t.checkExpect(exmp8.derivative().simplify(), one);
		t.checkExpect(exmp9.derivative().simplify(), one);
		t.checkExpect(exmp10.derivative().simplify(), three);
		t.checkExpect(exmp11.derivative().simplify(), new Number(1.0 / 3));
		t.checkExpect(exmp12.derivative().simplify(), new Product(three, new Exponentiation(x, two)));
		t.checkExpect(exmp13.derivative().simplify().toSmartString(), "3 ^ x * 1.0986122886681098");
	}

	public void testNleclair10(Tester t) {
		IExpression num = new Number(0);
		IExpression var = new Var();
		IExpression sum = new Sum(num, var);
		IExpression dif = new Difference(num, var);
		IExpression pro = new Product(num, var);
		IExpression quo = new Quotient(num, var);
		IExpression neg = new Neg(num);
		IExpression exp = new Exponentiation(num, var);
		IExpression sin = new Sin(num);
		IExpression cos = new Cos(num);
		IExpression log = new Ln(var);
		//containsVar()
		t.checkExpect(num.containsVar(), false);
		t.checkExpect(var.containsVar(), true);
		t.checkExpect(sum.containsVar(), true);
		t.checkExpect(dif.containsVar(), true);
		t.checkExpect(pro.containsVar(), true);
		t.checkExpect(quo.containsVar(), true);
		t.checkExpect(neg.containsVar(), false);
		t.checkExpect(exp.containsVar(), true);
		t.checkExpect(sin.containsVar(), false);
		t.checkExpect(cos.containsVar(), false);
		t.checkExpect(log.containsVar(), true);
		//eval()
		t.checkInexact(num.eval(0), 0.0, 0.000001);
		t.checkInexact(var.eval(0), 0.0, 0.000001);
		t.checkInexact(sum.eval(0), 0.0, 0.000001);
		t.checkInexact(dif.eval(0), 0.0, 0.000001);
		t.checkInexact(pro.eval(0), 0.0, 0.000001);
		t.checkInexact(quo.eval(1), 0.0, 0.000001);
		t.checkInexact(neg.eval(0), 0.0, 0.000001);
		t.checkInexact(exp.eval(1), 0.0, 0.000001);
		t.checkInexact(sin.eval(0), 0.0, 0.000001);
		t.checkInexact(cos.eval(0), 1.0, 0.000001);
		t.checkInexact(log.eval(1), 0.0, 0.000001);
		//toString()
		t.checkExpect(num.toString(), "0.0");
		t.checkExpect(var.toString(), "x");
		t.checkExpect(sum.toString(), "(0.0 + x)");
		t.checkExpect(dif.toString(), "(0.0 - x)");
		t.checkExpect(pro.toString(), "(0.0 * x)");
		t.checkExpect(quo.toString(), "(0.0 / x)");
		t.checkExpect(neg.toString(), "~(0.0)");
		t.checkExpect(exp.toString(), "(0.0 ^ x)"); 
		t.checkExpect(sin.toString(), "sin(0.0)");
		t.checkExpect(cos.toString(), "cos(0.0)");
		t.checkExpect(log.toString(), "ln(x)");
		//toSmartString()
		t.checkExpect(num.toSmartString(), "0");
		t.checkExpect(var.toSmartString(), "x");
		t.checkExpect(sum.toSmartString(), "0 + x"); 
		t.checkExpect(dif.toSmartString(), "0 - x");
		t.checkExpect(pro.toSmartString(), "0 * x");
		t.checkExpect(quo.toSmartString(), "0 / x");
		t.checkExpect(neg.toSmartString(), "~0");
		t.checkExpect(exp.toSmartString(), "0 ^ x"); 
		t.checkExpect(sin.toSmartString(), "sin 0");
		t.checkExpect(cos.toSmartString(), "cos 0");
		t.checkExpect(log.toSmartString(), "ln x");
		//simplify()
		t.checkExpect(num.simplify(), new Number(0)); 
		t.checkExpect(var.simplify(), new Var());
		t.checkExpect(sum.simplify(), new Var());
		t.checkExpect(dif.simplify(), new Neg(new Var()));
		t.checkExpect(pro.simplify(), new Number(0));
		t.checkExpect(quo.simplify(), new Number(0));
		t.checkExpect(neg.simplify(), new Number(0));
		t.checkExpect(exp.simplify(), new Number(0));
		t.checkExpect(sin.simplify(), new Number(0));
		t.checkExpect(cos.simplify(), new Number(1));
		t.checkExpect(log.simplify(), new Ln(new Var())); 
		//derivative()
		t.checkExpect(num.derivative(), new Number(0));
		t.checkExpect(var.derivative(), new Number(1));
//		t.checkExpect(sum.derivative(), new Sum(sum.getLeft().derivative(), sum.getRight().derivative()));
//		t.checkExpect(dif.derivative(), new Difference(dif.getLeft().derivative(), dif.getRight().derivative()));
//		t.checkExpect(pro.derivative(), new Sum(new Product(pro.getLeft().derivative(), pro.getRight()), 
//				new Product(pro.getRight().derivative(), pro.getLeft())));
//		t.checkExpect(quo.derivative(), new Quotient(new Difference(new Product(quo.getLeft().derivative(), 
//				quo.getRight()), 
//				new Product(quo.getRight().derivative(), 
//						quo.getLeft())), 
//						new Exponentiation(quo.getRight(), new Number(2))));
//		t.checkExpect(neg.derivative(), new Neg(neg.derivative()));
//		t.checkExpect(exp.derivative(), new Product(new Exponentiation(exp.getLeft(), exp.getRight()), 
//				new Quotient(new Sum(new Product(exp.getRight().derivative(), 
//						exp.getLeft()), 
//						new Product(exp.getLeft().derivative(), 
//								exp.getRight())), 
//								exp.getLeft())));
//		t.checkExpect(sin.derivative(), new Product(new Cos(sin.getExpr()), sin.getExpr().derivative()));
//		t.checkExpect(cos.derivative(), new Product(new Neg(new Sin(cos.getExpr())), cos.getExpr().derivative())); 
//		t.checkExpect(log.derivative(), new Quotient(log.getExpr().derivative(), log.getExpr()));
	}	

	public void testklister10(Tester t) {
		IExpression seven = parser.parse("7");
		IExpression two = parser.parse("2.0");
		IExpression twoPlusSeven = parser.parse("2 + 7");
		IExpression x = new Var();
		IExpression twoTimesX = new Product(two, x);
		IExpression twoDividedBySeven = new Quotient(two, seven);
		IExpression twoSquared = new Exponentiation(two, two);
		IExpression negTwoTimesX = new Neg(twoTimesX);
		IExpression sinTwo = new Sin(two);
		IExpression cosTwoTimesX = new Cos(twoTimesX);
		IExpression logSeven = new Ln(seven);
		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkExpect(x.containsVar(), true);
		t.checkExpect(twoTimesX.containsVar(), true);
		t.checkExpect(twoDividedBySeven.containsVar(), false);
		t.checkExpect(twoSquared.containsVar(), false);
		t.checkExpect(negTwoTimesX.containsVar(), true);
		t.checkExpect(sinTwo.containsVar(), false);
		t.checkExpect(cosTwoTimesX.containsVar(), true);
		t.checkExpect(logSeven.containsVar(), false);
		t.checkInexact(seven.eval(10.0), 7.0, 0.000001);
		t.checkInexact(twoPlusSeven.eval(-34.3), 9.0, 0.000001);
		t.checkInexact(x.eval(10.0), 10.0, 0.000001);
		t.checkInexact(twoTimesX.eval(20.0), 40.0, 0.000001);
		t.checkInexact(twoDividedBySeven.eval(42.9), 2.0/7.0, 0.000001);
		t.checkInexact(twoSquared.eval(65.4), 4.0, 0.000001);
		t.checkInexact(negTwoTimesX.eval(2.0), -4.0, 0.000001);
		t.checkInexact(sinTwo.eval(3.3), Math.sin(2.0), 0.000001);
		t.checkInexact(cosTwoTimesX.eval(4.0), Math.cos(8.0), 0.000001);
		t.checkInexact(logSeven.eval(30.0), Math.log(7.0), 0.000001);
		t.checkExpect(seven.toString(), "7.0");
		t.checkExpect(twoPlusSeven.toString(),"(2.0 + 7.0)");
		t.checkExpect(x.toString(), "x");
		t.checkExpect(twoTimesX.toString(), "(2.0 * x)");
		t.checkExpect(twoDividedBySeven.toString(), "(2.0 / 7.0)");
		t.checkExpect(twoSquared.toString(), "(2.0 ^ 2.0)");
		t.checkExpect(negTwoTimesX.toString(), "~((2.0 * x))"); // one set of parens from *, another from ~
		t.checkExpect(sinTwo.toString(), "sin(2.0)");
		t.checkExpect(cosTwoTimesX.toString(), "cos((2.0 * x))");
		t.checkExpect(logSeven.toString(), "ln(7.0)");
		t.checkExpect(seven.toSmartString(), "7");
		t.checkExpect(twoPlusSeven.toSmartString(),"2 + 7");
		t.checkExpect(x.toSmartString(), "x");
		t.checkExpect(twoTimesX.toSmartString(), "2 * x");
		t.checkExpect(twoDividedBySeven.toSmartString(), "2 / 7");
		t.checkExpect(twoSquared.toSmartString(), "2 ^ 2");
		t.checkExpect(negTwoTimesX.toSmartString(), "~(2 * x)");
		t.checkExpect(sinTwo.toSmartString(), "sin 2");
		t.checkExpect(cosTwoTimesX.toSmartString(), "cos(2 * x)");
		t.checkExpect(logSeven.toSmartString(), "ln 7");
		t.checkExpect(parser.parse("0 - 4").simplify(), parser.parse("~4"));
		t.checkExpect(parser.parse("1 * 7").simplify(), seven);
		t.checkExpect(parser.parse("2 ^ 0").simplify(), new Number(1));
		t.checkExpect(parser.parse("2 * sin(0)").simplify(), new Number(0));
		t.checkExpect(parser.parse("267 * 267").simplify(), new Number(71289));
		t.checkExpect(parser.parse("x * x").simplify(), new Exponentiation(x, new Number(2)));
		t.checkExpect(parser.parse("5 - (0 - 4)").simplify(), new Number(9));
		t.checkExpect(parser.parse("~6 - 10").simplify(), parser.parse("~16"));
		t.checkExpect(parser.parse("~x - 15").simplify(), parser.parse("~(x + 15)"));
		t.checkExpect(parser.parse("4 * (x / 2)").simplify().toSmartString(), "4 * x / 2");
		t.checkExpect(parser.parse("4 / (x / 2)").simplify().toSmartString(), "8 / x");
		t.checkExpect(seven.derivative(), new Number(0));
		t.checkExpect(twoPlusSeven.derivative().toSmartString(), "0 + 0");
		t.checkExpect(x.derivative(), new Number(1));
		t.checkExpect(twoTimesX.derivative().toSmartString(), "0 * x + 1 * 2");
		t.checkExpect(twoDividedBySeven.derivative().toSmartString(), "(0 * 7 - 0 * 2) / 7 ^ 2");
		t.checkExpect(twoSquared.derivative().toSmartString(), "2 * 2 ^ (2 - 1) * 0");
		t.checkExpect(negTwoTimesX.derivative().toSmartString(), "~(0 * x + 1 * 2)");
		t.checkExpect(sinTwo.derivative().toSmartString(), "cos 2 * 0");
		t.checkExpect(cosTwoTimesX.derivative().toSmartString(), "~sin(2 * x) * (0 * x + 1 * 2)");
		t.checkExpect(logSeven.derivative().toSmartString(), "0 / 7");
	}
	
	public void testEmccaule10(Tester t) {
		IExpression num1 = parser.parse("10");
		IExpression num2 = parser.parse("6.7");
		IExpression var = parser.parse("x");
		IExpression sum1 = parser.parse("17 + 4");
		IExpression sum2 = parser.parse("x + 12");
		IExpression diff1 = parser.parse("16 - 5");
		IExpression diff2 = parser.parse("8 - x");
		IExpression diff3 = parser.parse("x - 3.5");
		IExpression diff4 = parser.parse("5 - 0");
		IExpression diff5 = parser.parse("7 - 7");
		IExpression prod1 = parser.parse("3 * 4");
		IExpression prod2 = parser.parse("6 * x");
		IExpression prod3 = parser.parse("x * 3");
		IExpression prod4 = parser.parse("9 * 1");
		IExpression quot1 = parser.parse("20 / 5");
		IExpression quot2 = parser.parse("10 / x");
		IExpression quot3 = parser.parse("x / 4");
		IExpression exp1 = parser.parse("25 ^ 0.5");
		IExpression exp2 = parser.parse("x ^ 2");
		IExpression exp3 = parser.parse("2 ^ x");
		IExpression exp4 = parser.parse("0 ^ 4");
		IExpression neg1 = parser.parse("~x");
		IExpression neg2 = parser.parse("~-2");
		IExpression sin1 = parser.parse("sin x");
		IExpression sin2 = parser.parse("sin(x + 2)");
		IExpression sin3 = parser.parse("sin 4.71238898038469");
		IExpression cos1 = parser.parse("cos x ^ 2");
		IExpression cos2 = parser.parse("cos(x - 5)");
		IExpression cos3 = parser.parse("cos 1.0471975511965976");
		IExpression cos4 = parser.parse("cos(0)");
		IExpression ln1 = parser.parse("ln x");
		IExpression ln2 = parser.parse("ln(x) ^ 2");
		IExpression ln3 = parser.parse("ln 20.085536923187664");

		t.checkExpect(sum2.containsVar(), true);
		t.checkExpect(diff1.containsVar(), false);
		t.checkExpect(quot1.containsVar(), false);
		t.checkExpect(neg1.containsVar(), true);
		t.checkExpect(num1.containsVar(), false);
		t.checkExpect(diff3.containsVar(), true);
		t.checkExpect(prod1.containsVar(), false);
		t.checkExpect(cos3.containsVar(), false);
		t.checkExpect(quot2.containsVar(), true);
		t.checkExpect(sin3.containsVar(), false);
		t.checkExpect(ln2.containsVar(), true);
		t.checkInexact(num1.eval(24), 10.0, 0.000001);
		t.checkInexact(sum1.eval(13), 21.0, 0.000001);
		t.checkInexact(sum2.eval(10), 22.0, 0.000001);
		t.checkInexact(diff1.eval(5), 11.0, 0.000001);
		t.checkInexact(sum2.eval(3), 15.0, 0.000001);
		t.checkInexact(quot3.eval(8), 2.0, 0.000001);
		t.checkInexact(prod1.eval(35), 12.0, 0.000001);
		t.checkInexact(exp2.eval(8), 64.0, 0.000001);
		t.checkInexact(prod3.eval(6), 18.0, 0.000001);
		t.checkInexact(exp3.eval(5), 32.0, 0.000001);
		t.checkInexact(quot2.eval(2), 5.0, 0.000001);
		t.checkExpect(sum2.toString(), "(x + 12.0)");
		t.checkExpect(num1.toString(), "10.0");
		t.checkExpect(diff1.toString(), "(16.0 - 5.0)");
		t.checkExpect(quot2.toString(), "(10.0 / x)");
		t.checkExpect(exp3.toString(), "(2.0 ^ x)");
		t.checkExpect(prod2.toString(), "(6.0 * x)");
		t.checkExpect(sin3.toString(), "sin(4.71238898038469)");
		t.checkExpect(diff3.toString(), "(x - 3.5)");
		t.checkExpect(sum2.toString(), "(x + 12.0)");
		t.checkExpect(cos2.toString(), "cos((x - 5.0))");
		t.checkExpect(num2.toString(), "6.7");
		t.checkExpect(exp2.toSmartString(), "x ^ 2");
		t.checkExpect(num2.toSmartString(), "6.7");
		t.checkExpect(var.toSmartString(), "x");
		t.checkExpect(diff1.toSmartString(), "16 - 5");
		t.checkExpect(prod2.toSmartString(), "6 * x");
		t.checkExpect(sin3.toSmartString(), "sin 4.71238898038469");
		t.checkExpect(num1.toSmartString(), "10");
		t.checkExpect(cos2.toSmartString(), "cos(x - 5)");
		t.checkExpect(sum1.toSmartString(), "17 + 4");
		t.checkExpect(diff3.toSmartString(), "x - 3.5");
		t.checkExpect(cos2.toSmartString(), "cos(x - 5)");
		t.checkExpect(diff4.simplify(), new Number(5.0));
		t.checkExpect(prod4.simplify(), new Number(9.0));
		t.checkExpect(exp4.simplify(), new Number(0.0));
		t.checkExpect(cos4.simplify(), new Number(1.0));
		t.checkExpect(diff5.simplify(), new Number(0.0));
		t.checkExpect(num2.derivative(), new Number(0));
		t.checkExpect(num1.derivative(), new Number(0));
		t.checkExpect(var.derivative(), new Number(1));
		t.checkExpect(sum1.derivative().toSmartString(), "0 + 0");
		t.checkExpect(diff3.derivative().toSmartString(), "1 - 0");
		t.checkExpect(prod1.derivative().toSmartString(), "0 * 4 + 0 * 3");
		t.checkExpect(prod3.derivative().toSmartString(), "1 * 3 + 0 * x");
		t.checkExpect(quot1.derivative().toSmartString(), "(0 * 5 - 0 * 20) / 5 ^ 2");
		t.checkExpect(sin3.derivative().toSmartString(), "cos 4.71238898038469 * 0");
		t.checkExpect(cos3.derivative().toSmartString(), "~sin 1.0471975511965976 * 0");
		t.checkExpect(ln3.derivative().toSmartString(), "0 / 20.085536923187664");
	}

	public void testsmitchel10(Tester t) {
		IExpression seven = new Number(7.0);
		IExpression two = new Number(2.0);
		IExpression three = new Number(3.0);
		IExpression four = new Number(4.0);
		IExpression nine = new Number(9.0);
		IExpression five = new Number(5.0);
		IExpression threeTimesFour = new Product(three,four);
		IExpression twoPlusSeven = new Sum(two,seven);
		IExpression fourDividedByTwo = new Quotient(four, two);
		IExpression sinOfThree = new Sin(three);
		IExpression cosOfThree = new Cos(three);
		IExpression twoToTheThirdPower = new Exponentiation(two,three);
		IExpression natLogOfThree= new Ln(three);
		IExpression threePlusNine = new Sum(three, nine);
		IExpression nineToTheThirdPwr = new Exponentiation(nine,three);
		IExpression sevenMinusThree = new Difference(seven, three);
		IExpression fourPlusThreeToTheThird = new Sum(four,new Exponentiation(three, three));
		IExpression threeMinusFiveTimesTwo = new Difference(three, new Product(five, two));
		IExpression sevenPlusFiveMinusNine = new Sum(seven, new Difference(five, nine));
		IExpression fourTimesThreePlusTwo = new Product(four, new Sum(three, two));
		IExpression ninePlusThreeMinusTwo = new Sum(nine, new Difference(three, two));
		IExpression threeTimesNinePlusSeven = new Product(three, new Sum(nine, seven));
		IExpression sevenMinusThreePlusCosTwo = new Difference(seven, new Sum(three, new Cos(two)));
		IExpression fourPlusNinePlusTwo = new Sum(four, new Sum(nine, two));
		IExpression sevenMinusThreeMinusTwo = new Difference(seven, new Difference(three, two));
		IExpression cosThreePlusNine = new Cos(new Sum(three, nine));
		IExpression fourPlusSevenMinusNine = new Sum(four, new Difference(seven, nine));
		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(cosOfThree.containsVar(), false);
		t.checkExpect(nineToTheThirdPwr.containsVar(), false);
		t.checkExpect(sevenMinusThree.containsVar(), false);
		t.checkExpect(natLogOfThree.containsVar(), false);
		t.checkExpect(twoToTheThirdPower.containsVar(), false);
		t.checkExpect(sinOfThree.containsVar(), false);
		t.checkExpect(fourDividedByTwo.containsVar(), false);
		t.checkExpect(two.containsVar(), false);
		t.checkExpect(three.containsVar(), false);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkInexact(seven.eval(10.0), 7.0, 0.0000001);
		t.checkInexact(twoPlusSeven.eval(-34.3), 9.0, 0.0000001);
		t.checkInexact(threeTimesFour.eval(39),12.0, 0.0000001);
		t.checkInexact(fourDividedByTwo.eval(89),2.0, 0.0000001);
		t.checkInexact(twoToTheThirdPower.eval(84),8.0, 0.0000001);
		t.checkInexact(sinOfThree.eval(40),0.1411200080598672, 0.0000001);
		t.checkInexact(cosOfThree.eval(49),-0.9899924966004454, 0.0000001);
		t.checkInexact(twoToTheThirdPower.eval(34),8.0, 0.0000001);
		t.checkInexact(natLogOfThree.eval(49),1.0986122886681098, 0.00000001);
		t.checkInexact(threePlusNine.eval(48),12.0, 0.0000001);
		t.checkInexact(natLogOfThree.eval(89),1.0986122886681098, 0.0000001);
		t.checkExpect(fourDividedByTwo.toString(),"(4.0 / 2.0)");
		t.checkExpect(threeTimesFour.toString(),"(3.0 * 4.0)");
		t.checkExpect(sinOfThree.toString(),"sin(3.0)");
		t.checkExpect(cosOfThree.toString(),"cos(3.0)");
		t.checkExpect(twoToTheThirdPower.toString(),"(2.0 ^ 3.0)");
		t.checkExpect(natLogOfThree.toString(),"ln(3.0)");
		t.checkExpect(threePlusNine.toString(),"(3.0 + 9.0)");
		t.checkExpect(nineToTheThirdPwr.toString(),"(9.0 ^ 3.0)");
		t.checkExpect(sevenMinusThree.toString(),"(7.0 - 3.0)");
		t.checkExpect(four.toString(),"4.0");
		t.checkExpect(twoPlusSeven.toString(),"(2.0 + 7.0)");
		t.checkExpect(fourPlusThreeToTheThird.toSmartString(),"4 + 3 ^ 3");
		t.checkExpect(threeMinusFiveTimesTwo.toSmartString(),"3 - 5 * 2");
		t.checkExpect(sevenPlusFiveMinusNine.toSmartString(),"7 + (5 - 9)");
		t.checkExpect(fourTimesThreePlusTwo.toSmartString(),"4 * (3 + 2)");
		t.checkExpect(ninePlusThreeMinusTwo.toSmartString(),"9 + (3 - 2)");
		t.checkExpect(threeTimesNinePlusSeven.toSmartString(),"3 * (9 + 7)");
		t.checkExpect(sevenMinusThreePlusCosTwo.toSmartString(),"7 - (3 + cos 2)");
		t.checkExpect(fourPlusNinePlusTwo.toSmartString(),"4 + (9 + 2)");
		t.checkExpect(sevenMinusThreeMinusTwo.toSmartString(),"7 - (3 - 2)");
		t.checkExpect(cosThreePlusNine.toSmartString(),"cos(3 + 9)");
		t.checkExpect(fourPlusSevenMinusNine.toSmartString(),"4 + (7 - 9)");
		// I attempted the simplify method, but I kept coming up with errors when I tried to do
		// the name check. Therefore I left that out because I simply have no idea where
		//to start with that.
		// I also tried the derivatives, but I'm really unsure about what exactly I'm doing since
		// we haven't done these in math yet...
		//         t.checkExpect(cosThreePlusNine.derivative(),".9781476 (d/dx .9781476)"); 
		//         t.checkExpect(fourPlusSevenMinusNine.derivative(),"(d/dx 4) + (d/dx 7-9)");
		//         t.checkExpect(sevenMinusThreeMinusTwo.derivative(),"(d/dx 7-3) - (d/dx 2)");
		//         t.checkExpect(threeTimesNinePlusSeven.derivative(),"((d/dx 3) * 16) + (d/dx 16) * 3)");
		//         t.checkExpect(ninePlusThreeMinusTwo.derivative(),"(d/dx 12) - (d/dx 2)");
		//         t.checkExpect(fourTimesThreePlusTwo.derivative(),"(d/dx 12) + (d/dx 2)");
		//         t.checkExpect(sevenPlusFiveMinusNine.derivative(),"(d/dx 12) - (d/dx 9)");
		//         t.checkExpect(threeTimesNinePlusSeven.derivative(),"(d/dx 27) + (d/dx 7)");
		//         t.checkExpect(natLogOfThree.derivative(),"(d/dx 1.0986) / 1.0986");
		//         t.checkExpect(sinOfThree.derivative(),".9986 + (d/dx .9986)");
		//         t.checkExpect(nineToTheThirdPwr.derivative(),"729 * (((d/dx 729) * 729 * (((d/dx 729) * 
		//                                (729) / 729");
	}
	
	public void testariedelm10(Tester t) {
		IExpression num1 = parser.parse("8");
		IExpression num2 = parser.parse("3.4");
		IExpression var = parser.parse("x");
		IExpression sum1 = parser.parse("3 + 8");
		IExpression sum2 = parser.parse("x + 7");
		IExpression sum3 = parser.parse("x + 0");
		IExpression sum4 = parser.parse("x + (~5)");
		IExpression diff1 = parser.parse("6 - 1");
		IExpression diff2 = parser.parse("8 - x");
		IExpression diff3 = parser.parse("x - 3.5");
		IExpression prod1 = parser.parse("3 * 4");
		IExpression prod2 = parser.parse("6 * x");
		IExpression prod3 = parser.parse("x * 6");
		IExpression prod4 = parser.parse("x * 0");
		IExpression prod5 = parser.parse("x * (~5)");
		IExpression quot1 = parser.parse("20 / 5");
		IExpression quot2 = parser.parse("10 / x");
		IExpression quot3 = parser.parse("x / 3");
		IExpression quot4= parser.parse("0 / x");
		IExpression quot5 = parser.parse("x / x");
		IExpression quot6 = parser.parse("x / ~1");
		IExpression exp1 = parser.parse("25 ^ 0.5");
		IExpression exp2 = parser.parse("x ^ 2");
		IExpression exp3 = parser.parse("2 ^ x");
		IExpression exp4 = parser.parse("x ^ 1");
		IExpression exp5 = parser.parse("(x ^ x)^ x");
		IExpression neg1 = parser.parse("~x");
		IExpression neg2 = parser.parse("~-2");
		IExpression neg3 = parser.parse("~0");
		IExpression sin1 = parser.parse("sin x");
		IExpression sin2 = parser.parse("sin(x + 2)");
		IExpression sin3 = parser.parse("sin 4.71238898");
		IExpression cos1 = parser.parse("cos x ^ 2");
		IExpression cos2 = parser.parse("cos(x - 5)");
		IExpression cos3 = parser.parse("cos 1.0471975511965976");
		IExpression cos4 = parser.parse("cos x");
		IExpression ln1 = parser.parse("ln x");
		IExpression ln2 = parser.parse("ln x ^ 2");
		IExpression ln3 = parser.parse("ln 20.085536923187664");
		t.checkExpect(num1.containsVar(), false);
		t.checkInexact(num1.eval(3.0), 8.0, 0.0000001);
		t.checkExpect(num1.toString(), "8.0");
		t.checkExpect(num1.toSmartString(), "8");
		t.checkExpect(num2.containsVar(), false);
		t.checkInexact(num2.eval(5.0), 3.4, 0.0000001);
		t.checkExpect(num2.toString(), "3.4");
		t.checkExpect(num2.derivative(), new Number(0.0));
		t.checkExpect(num2.toSmartString(), "3.4");

		t.checkExpect(var.containsVar(), true);
		t.checkInexact(var.eval(3.0), 3.0, 0.0000001);
		t.checkExpect(var.toString(), "x");
		t.checkExpect(var.derivative(),new Number(1.0));
		t.checkExpect(var.toSmartString(), "x");

		t.checkExpect(sum1.containsVar(), false);
		t.checkInexact(sum1.eval(10.0), 11.0, 0.0000001);
		t.checkExpect(sum1.toString(), "(3.0 + 8.0)");
		t.checkExpect(sum1.toSmartString(), "3 + 8");
		t.checkExpect(sum1.derivative().simplify(), new Number(0.0));
		t.checkExpect(sum2.containsVar(), true);
		t.checkInexact(sum2.eval(3.5), 10.5, 0.0000001);
		t.checkExpect(sum2.toString(), "(x + 7.0)");
		t.checkExpect(sum2.toSmartString(), "x + 7");
		t.checkExpect(sum3.simplify(), new Var());
		t.checkExpect(sum4.simplify(), new Difference(new Var(), new Number(5.0)));

		t.checkExpect(diff1.containsVar(), false);
		t.checkInexact(diff1.eval(4.2), 5.0, 0.0000001);
		t.checkExpect(diff1.toString(), "(6.0 - 1.0)");
		t.checkExpect(diff1.toSmartString(), "6 - 1");
		t.checkExpect(diff2.containsVar(), true);
		t.checkInexact(diff2.eval(4.2), 3.8, 0.0000001);
		t.checkExpect(diff2.toString(), "(8.0 - x)");
		t.checkExpect(diff2.toSmartString(), "8 - x");
		t.checkExpect(diff3.containsVar(), true);
		t.checkInexact(diff3.eval(4.2), 0.7, 0.0000001);
		t.checkExpect(diff3.toString(), "(x - 3.5)");
		t.checkExpect(diff3.toSmartString(), "x - 3.5");
		t.checkExpect(diff3.derivative().simplify(), new Number(1.0));

		t.checkExpect(prod1.containsVar(), false);
		t.checkInexact(prod1.eval(4.2), 12.0, 0.0000001);
		t.checkExpect(prod1.toString(), "(3.0 * 4.0)");
		t.checkExpect(prod1.toSmartString(), "3 * 4");
		t.checkExpect(prod2.containsVar(), true);
		t.checkInexact(prod2.eval(12.5), 75.0, 0.0000001);
		t.checkExpect(prod2.toString(), "(6.0 * x)");
		t.checkExpect(prod2.toSmartString(), "6 * x");
		t.checkExpect(prod2.derivative().simplify(), new Number(6.0));
		t.checkExpect(prod3.containsVar(), true);
		t.checkInexact(prod3.eval(4.0), 24.0, 0.0000001);
		t.checkExpect(prod3.toString(), "(x * 6.0)");
		t.checkExpect(prod3.toSmartString(), "x * 6");
		t.checkExpect(prod4.simplify(), new Number(0.0));
		t.checkExpect(prod5.simplify(), new Neg(new Product(new Var(), new Number(5.0))));

		t.checkExpect(quot1.containsVar(), false);
		t.checkInexact(quot1.eval(4.2), 4.0, 0.0000001);
		t.checkExpect(quot1.toString(), "(20.0 / 5.0)");
		t.checkExpect(quot1.toSmartString(), "20 / 5");
		t.checkExpect(quot2.containsVar(), true);
		t.checkInexact(quot2.eval(2.0), 5.0, 0.0000001);
		t.checkExpect(quot2.toString(), "(10.0 / x)");
		t.checkExpect(quot2.toSmartString(), "10 / x");
		t.checkExpect(quot2.derivative().simplify().toSmartString(), "~(10 / x ^ 2)");
		t.checkExpect(quot3.containsVar(), true);
		t.checkInexact(quot3.eval(12.0), 4.0, 0.0000001);
		t.checkExpect(quot3.toString(), "(x / 3.0)");
		t.checkExpect(quot3.toSmartString(), "x / 3");
		t.checkExpect(quot4.simplify(), new Number(0.0));
		t.checkExpect(quot5.simplify(), new Number(1.0));
		t.checkExpect(quot6.simplify(), new Neg(new Var()));

		t.checkExpect(exp1.containsVar(), false);
		t.checkInexact(exp1.eval(4.2), 5.0, 0.0000001);
		t.checkExpect(exp1.toString(), "(25.0 ^ 0.5)");
		t.checkExpect(exp1.toSmartString(), "25 ^ 0.5");
		t.checkExpect(exp1.containsVar(), false);
		t.checkInexact(exp1.eval(4.2), 5.0, 0.0000001);
		t.checkExpect(exp1.toString(), "(25.0 ^ 0.5)");
		t.checkExpect(exp1.toSmartString(), "25 ^ 0.5");
		t.checkExpect(exp1.containsVar(), false);
		t.checkInexact(exp1.eval(4.2), 5.0, 0.0000001);
		t.checkExpect(exp1.toString(), "(25.0 ^ 0.5)");
		t.checkExpect(exp1.toSmartString(), "25 ^ 0.5");
		t.checkExpect(exp2.derivative().toSmartString(), "2 * x ^ (2 - 1) * 1");
		t.checkExpect(exp4.simplify(), new Var());
		t.checkExpect(exp5.simplify().toSmartString(), "x ^ x ^ 2"); 

		t.checkExpect(neg1.containsVar(), true);
		t.checkInexact(neg1.eval(7.3), -7.3, 0.0000001);
		t.checkExpect(neg1.toString(), "~(x)");
		t.checkExpect(neg1.toSmartString(), "~x");
		t.checkExpect(neg1.derivative(), new Neg(new Number(1.0)));
		t.checkExpect(neg2.containsVar(), false);
		t.checkInexact(neg2.eval(7.3), 2.0, 0.0000001);
		t.checkExpect(neg2.toString(), "~(-2.0)");
		t.checkExpect(neg2.toSmartString(), "~-2");
		t.checkExpect(neg2.simplify(), new Number(2.0));

		t.checkExpect(sin1.containsVar(), true);
		t.checkInexact(sin1.eval(0.0), 0.0, 0.0000001);
		t.checkExpect(sin1.toString(), "sin(x)");
		t.checkExpect(sin1.toSmartString(), "sin x");
		t.checkExpect(sin1.derivative().toSmartString(), "cos x * 1");
		t.checkExpect(sin2.containsVar(), true);
		t.checkInexact(sin2.eval(3.0 * Math.PI / 2 - 2), -1.0, 0.0000001);
		t.checkExpect(sin2.toString(), "sin((x + 2.0))");
		t.checkExpect(sin2.toSmartString(), "sin(x + 2)");
		t.checkExpect(sin3.containsVar(), false);
		t.checkInexact(sin3.eval(7.3), -1.0, 0.0000001);
		t.checkExpect(sin3.toString(), "sin(4.71238898)");
		t.checkExpect(sin3.toSmartString(), "sin 4.71238898");

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
		t.checkExpect(cos4.derivative().toSmartString(), "~sin x * 1");

		t.checkExpect(ln1.containsVar(), true);
		t.checkInexact(ln1.eval(1.0), 0.0, 0.0000001);
		t.checkExpect(ln1.toString(), "ln(x)");
		t.checkExpect(ln1.toSmartString(), "ln x");
		t.checkExpect(ln1.derivative(), new Quotient(new Number(1.0), new Var()));
		t.checkExpect(ln2.containsVar(), true);
		t.checkInexact(ln2.eval(Math.E * Math.E), 4.0, 0.0000001);
		t.checkExpect(ln2.toString(), "ln((x ^ 2.0))");
		t.checkExpect(ln2.toSmartString(), "ln x ^ 2");
		t.checkExpect(ln3.containsVar(), false);
		t.checkInexact(ln3.eval(7.3), 3.0, 0.0000001);
		t.checkExpect(ln3.toString(), "ln(20.085536923187664)");
		t.checkExpect(ln3.toSmartString(), "ln 20.085536923187664");
	}



	public void testpthompso10(Tester t) {
		IExpression seven = new Number(7.0);
		IExpression two = new Number(2.0);
		IExpression x = new Var();
		IExpression twoPlusSeven = new Sum(two, seven);
		IExpression twoPlusX = new Sum(two, x);
		IExpression xPlusSeven = new Sum(x, seven);
		IExpression xPlusX = new Sum(x, x);
		IExpression sevenMinusTwo = new Difference(seven, two);
		IExpression sevenMinusX = new Difference(seven, x);
		IExpression xMinusTwo = new Difference(x, two);
		IExpression xMinusX = new Difference(x, x);
		IExpression sevenTimesTwo = parser.parse("7 * 2");
		IExpression sevenTimesX = parser.parse("7 * x");
		IExpression xTimesTwo = parser.parse("x * 2");
		IExpression xTimesX = parser.parse("x * x");
		IExpression sevenOverTwo = parser.parse("7 / 2");
		IExpression sevenOverX = parser.parse("7 / x");
		IExpression xOverTwo = parser.parse("x / 2");
		IExpression xOverX = parser.parse("x / x");
		IExpression sevenUpTwo = parser.parse("7 ^ 2");
		IExpression sevenUpX = parser.parse("7 ^ x");
		IExpression xUpTwo = parser.parse("x ^ 2");
		IExpression xUpX = parser.parse("x ^ x");
		IExpression cosZero = parser.parse("cos 0");
		IExpression cosOne = parser.parse("cos 1");
		IExpression cosX = parser.parse("cos x");
		IExpression cosXUpOne = parser.parse("cos x ^ 1");
		IExpression sinZero = parser.parse("sin 0");
		IExpression sinOne = parser.parse("sin 1");
		IExpression sinX = parser.parse("sin x");
		IExpression sinXPlusThree = parser.parse("sin (x + 3)");
		IExpression lnOne = parser.parse("ln 1");
		IExpression lnXPlusThree = parser.parse("ln (x + 3)");
		IExpression negFive = parser.parse("~5");
		IExpression negX = parser.parse("~x");
		IExpression negEightPlusTwo = parser.parse("~(8 + 2)");
		IExpression negNegNegNegNegThree = parser.parse("~~~~~3");
		//expressions for testing simplify
		IExpression sim1 = parser.parse("0 - x");
		IExpression sim2 = parser.parse("2 + -2 - (2 * x)");
		IExpression sim3 = parser.parse("1 * x");
		IExpression sim4 = parser.parse("(0 ^ 0) * (x + 3)");
		IExpression sim5 = parser.parse("x ^ 0");
		IExpression sim6 = parser.parse("((2 + x) * 9) ^ 0");
		IExpression sim7 = parser.parse("sin(3 - 3)");
		IExpression sim8 = parser.parse("(8 * x) * (8 * x)");
		IExpression sim9 = parser.parse("x * x");
		IExpression sim10 = parser.parse("~~5");
		IExpression sim11 = parser.parse("~~(x + 3)");
		IExpression sim12 = parser.parse("~x - 4");
		IExpression sim13 = parser.parse("~(x * 2) - 8");
		IExpression sim14 = parser.parse("5 * (x / 9)");
		IExpression sim15 = parser.parse("x * (x / 8)");
		IExpression sim16 = parser.parse("9 / (4 / x)");
		IExpression sim17 = parser.parse("x / (6 / x)");
		//expressions for testing derivative
		IExpression ie1 = parser.parse("5"); //der = 0
		IExpression ie2 = parser.parse("x"); //der = 1
		IExpression ie3 = parser.parse("x + 5"); //der = 1 + 0
		IExpression ie4 = parser.parse("x - 3"); //der = 1 - 0
		IExpression ie5 = parser.parse("2 * x"); //der = 0 * x + 1 * 2
		IExpression ie6 = parser.parse("2 / x"); //der = (0 * x + 1 * ~2) / x ^ 2
		IExpression ie7 = parser.parse("x ^ 3"); //der = 3 * x ^ 2 * 1
		IExpression ie8 = parser.parse("x ^ (x + 1)"); //der = x ^ (x + 1) * ((1 + 0) * x + 1 * (x + 1) / x );
		IExpression ie9 = parser.parse("sin(2 * x)"); //der = cos(~2 * x) * (0 * x + 1 * 2 );
		IExpression ie10 = parser.parse("cos(x + 5)"); //der = ~sin(x + 5) * (1 + 0 );
		IExpression ie11 = parser.parse("~x"); //der = ~1
		IExpression ie12 = parser.parse("ln(x + 5)"); //der = (1 + 0) / (x + 5 );
		IExpression der1 = parser.parse("0");
		IExpression der2 = parser.parse("1");
		IExpression der3 = parser.parse("1 + 0");
		IExpression der4 = parser.parse("1 - 0");
		IExpression der5 = parser.parse("0 * x + 1 * 2");
		IExpression der6 = parser.parse("(0 * x - 1 * 2) / x ^ 2");
		IExpression der7 = parser.parse("3 * x ^ (3 - 1) * 1");
		IExpression der8 = parser.parse("x ^ (x + 1) * ((1 + 0) * ln x + 1 * (x + 1) / x)");
		IExpression der9 = parser.parse("cos(2 * x) * (0 * x + 1 * 2)");
		IExpression der10 = parser.parse("~sin(x + 5) * (1 + 0)");
		IExpression der11 = parser.parse("~1");
		IExpression der12 = parser.parse("(1 + 0) / (x + 5)");
		//containsVar tests
		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(two.containsVar(), false);
		t.checkExpect(x.containsVar(), true);
		t.checkExpect(twoPlusSeven.containsVar(), false);        
		t.checkExpect(twoPlusX.containsVar(), true);
		t.checkExpect(xPlusSeven.containsVar(), true);
		t.checkExpect(xPlusX.containsVar(), true);
		t.checkExpect(sevenMinusTwo.containsVar(), false);
		t.checkExpect(sevenMinusX.containsVar(), true);
		t.checkExpect(xMinusTwo.containsVar(), true);
		t.checkExpect(xMinusX.containsVar(), true);
		t.checkExpect(sevenTimesTwo.containsVar(), false);
		t.checkExpect(sevenTimesX.containsVar(), true);
		t.checkExpect(xTimesTwo.containsVar(), true);
		t.checkExpect(xTimesX.containsVar(), true);
		t.checkExpect(sevenOverTwo.containsVar(), false);
		t.checkExpect(sevenOverX.containsVar(), true);
		t.checkExpect(xOverTwo.containsVar(), true);
		t.checkExpect(xOverX.containsVar(), true);
		t.checkExpect(sevenUpTwo.containsVar(), false );
		t.checkExpect(sevenUpX.containsVar(), true );
		t.checkExpect(xUpTwo.containsVar(), true );
		t.checkExpect(xUpX.containsVar(), true );
		t.checkExpect(cosZero.containsVar(), false );
		t.checkExpect(cosOne.containsVar(), false );
		t.checkExpect(cosX.containsVar(), true );
		t.checkExpect(cosXUpOne.containsVar(), true );
		t.checkExpect(sinZero.containsVar(), false );
		t.checkExpect(sinOne.containsVar(), false );
		t.checkExpect(sinX.containsVar(), true );
		t.checkExpect(sinXPlusThree.containsVar(), true );
		t.checkExpect(lnOne.containsVar(), false );
		t.checkExpect(lnXPlusThree.containsVar(), true );
		t.checkExpect(negFive.containsVar(), false );
		t.checkExpect(negX.containsVar(), true );
		t.checkExpect(negEightPlusTwo.containsVar(), false );
		t.checkExpect(negNegNegNegNegThree.containsVar(), false );
		//eval tests
		t.checkInexact(seven.eval(10.0), 7.0 , 0.0000001);
		t.checkInexact(two.eval(-34.93), 2.0 , 0.0000001);
		t.checkInexact(x.eval(-33.0), -33.0 , 0.0000001);
		t.checkInexact(twoPlusSeven.eval(5.0), 9.0, 0.0000001);       
		t.checkInexact(twoPlusX.eval(6.1), 8.1 , 0.0000001);
		t.checkInexact(xPlusSeven.eval(9.5), 16.5 , 0.0000001);
		t.checkInexact(xPlusX.eval(8.0), 16.0 , 0.0000001);
		t.checkInexact(sevenMinusTwo.eval(7.0), 5.0 , 0.0000001);
		t.checkInexact(sevenMinusX.eval(8.0), -1.0 , 0.0000001);
		t.checkInexact(xMinusTwo.eval(-.33), -2.33 , 0.0000001);
		t.checkInexact(xMinusX.eval(1000.0), 0.0 , 0.0000001);
		t.checkInexact(sevenTimesTwo.eval(9.21), 14.0 , 0.0000001);
		t.checkInexact(sevenTimesX.eval(8.0), 56.0 , 0.0000001);
		t.checkInexact(xTimesTwo.eval(-.33), -.66 , 0.0000001);
		t.checkInexact(xTimesX.eval(25.0), 625.0 , 0.0000001);
		t.checkInexact(sevenOverTwo.eval(9.21), 3.5 , 0.0000001);
		t.checkInexact(sevenOverX.eval(1.75), 4.0 , 0.0000001);
		t.checkInexact(xOverTwo.eval(-6.4), -3.2 , 0.0000001);
		t.checkInexact(xOverX.eval(300.0), 1.0 , 0.0000001);
		t.checkInexact(sevenUpTwo.eval(9.21), 49.0 , 0.0000001);
		t.checkInexact(sevenUpX.eval(3.0), 343.0 , 0.0000001);
		t.checkInexact(xUpTwo.eval(-6.0), 36.0 , 0.0000001);
		t.checkInexact(xUpX.eval(4.0), 256.0 , 0.0000001);
		t.checkInexact(cosZero.eval(9.21), 1.0 , 0.0000001);
		t.checkInexact(cosX.eval(0.0), 1.0 , 0.0000001);
		t.checkInexact(cosXUpOne.eval(0.0), 1.0 , 0.0000001);
		t.checkInexact(sinZero.eval(9.9), 0.0 , 0.0000001);
		t.checkInexact(sinX.eval(0.0), 0.0 , 0.0000001);
		t.checkInexact(sinXPlusThree.eval(-3.0), 0.0 , 0.0000001);
		t.checkInexact(lnOne.eval(9.3), 0.0 , 0.0000001);
		t.checkInexact(lnXPlusThree.eval(-2.0), 0.0 , 0.0000001);
		t.checkInexact(negFive.eval(234.967), -5.0 , 0.0000001);
		t.checkInexact(negX.eval(31.0), -31.0 , 0.0000001);
		t.checkInexact(negEightPlusTwo.eval(8435.0), -10.0 , 0.0000001);
		t.checkInexact(negNegNegNegNegThree.eval(0.0), -3.0 , 0.0000001);
		//toString tests
		t.checkExpect(seven.toString(), "7.0" );
		t.checkExpect(two.toString(), "2.0" );
		t.checkExpect(x.toString(), "x" );
		t.checkExpect(twoPlusSeven.toString(), "(2.0 + 7.0)");      
		t.checkExpect(twoPlusX.toString(), "(2.0 + x)" );
		t.checkExpect(xPlusSeven.toString(), "(x + 7.0)" );
		t.checkExpect(xPlusX.toString(), "(x + x)" );
		t.checkExpect(sevenMinusTwo.toString(), "(7.0 - 2.0)" );
		t.checkExpect(sevenMinusX.toString(), "(7.0 - x)" );
		t.checkExpect(xMinusTwo.toString(), "(x - 2.0)" );
		t.checkExpect(xMinusX.toString(), "(x - x)" );
		t.checkExpect(sevenTimesTwo.toString(), "(7.0 * 2.0)" );
		t.checkExpect(sevenTimesX.toString(), "(7.0 * x)" );
		t.checkExpect(xTimesTwo.toString(), "(x * 2.0)" );
		t.checkExpect(xTimesX.toString(), "(x * x)" );
		t.checkExpect(sevenOverTwo.toString(), "(7.0 / 2.0)" );
		t.checkExpect(sevenOverX.toString(), "(7.0 / x)" );
		t.checkExpect(xOverTwo.toString(), "(x / 2.0)" );
		t.checkExpect(xOverX.toString(), "(x / x)" );
		t.checkExpect(sevenUpTwo.toString(), "(7.0 ^ 2.0)" );
		t.checkExpect(sevenUpX.toString(), "(7.0 ^ x)" );
		t.checkExpect(xUpTwo.toString(), "(x ^ 2.0)" );
		t.checkExpect(xUpX.toString(), "(x ^ x)" );
		t.checkExpect(cosZero.toString(), "cos(0.0)" );
		t.checkExpect(cosOne.toString(), "cos(1.0)" );
		t.checkExpect(cosX.toString(), "cos(x)" );
		t.checkExpect(cosXUpOne.toString(), "cos((x ^ 1.0))" );
		t.checkExpect(sinZero.toString(), "sin(0.0)" );
		t.checkExpect(sinOne.toString(), "sin(1.0)" );
		t.checkExpect(sinX.toString(), "sin(x)" );
		t.checkExpect(sinXPlusThree.toString(), "sin((x + 3.0))" );
		t.checkExpect(lnOne.toString(), "ln(1.0)" );
		t.checkExpect(lnXPlusThree.toString(), "ln((x + 3.0))" );
		t.checkExpect(negFive.toString(), "~(5.0)" );
		t.checkExpect(negX.toString(), "~(x)" );
		t.checkExpect(negEightPlusTwo.toString(), "~((8.0 + 2.0))" );
		t.checkExpect(negNegNegNegNegThree.toString(), "~(~(~(~(~(3.0)))))" );
		//toSmartString tests
		t.checkExpect(seven.toSmartString(), "7" );
		t.checkExpect(two.toSmartString(), "2" );
		t.checkExpect(x.toSmartString(), "x" );
		t.checkExpect(twoPlusSeven.toSmartString(), "2 + 7");
		t.checkExpect(twoPlusX.toSmartString(), "2 + x" );
		t.checkExpect(xPlusSeven.toSmartString(), "x + 7" );
		t.checkExpect(xPlusX.toSmartString(), "x + x" );
		t.checkExpect(sevenMinusTwo.toSmartString(), "7 - 2" );
		t.checkExpect(sevenMinusX.toSmartString(), "7 - x" );
		t.checkExpect(xMinusTwo.toSmartString(), "x - 2" );
		t.checkExpect(xMinusX.toSmartString(), "x - x" );
		t.checkExpect(sevenTimesTwo.toSmartString(), "7 * 2" );
		t.checkExpect(sevenTimesX.toSmartString(), "7 * x" );
		t.checkExpect(xTimesTwo.toSmartString(), "x * 2" );
		t.checkExpect(xTimesX.toSmartString(), "x * x" );
		t.checkExpect(sevenOverTwo.toSmartString(), "7 / 2" );
		t.checkExpect(sevenOverX.toSmartString(), "7 / x" );
		t.checkExpect(xOverTwo.toSmartString(), "x / 2" );
		t.checkExpect(xOverX.toSmartString(), "x / x" );
		t.checkExpect(sevenUpTwo.toSmartString(), "7 ^ 2" );
		t.checkExpect(sevenUpX.toSmartString(), "7 ^ x" );
		t.checkExpect(xUpTwo.toSmartString(), "x ^ 2" );
		t.checkExpect(xUpX.toSmartString(), "x ^ x" );
		t.checkExpect(cosZero.toSmartString(), "cos 0" );
		t.checkExpect(cosOne.toSmartString(), "cos 1" );
		t.checkExpect(cosX.toSmartString(), "cos x" );
		t.checkExpect(cosXUpOne.toSmartString(), "cos x ^ 1" );
		t.checkExpect(sinZero.toSmartString(), "sin 0" );
		t.checkExpect(sinOne.toSmartString(), "sin 1" );
		t.checkExpect(sinX.toSmartString(), "sin x" );
		t.checkExpect(sinXPlusThree.toSmartString(), "sin(x + 3)" );
		t.checkExpect(lnOne.toSmartString(), "ln 1" );
		t.checkExpect(lnXPlusThree.toSmartString(), "ln(x + 3)" );
		t.checkExpect(negFive.toSmartString(), "~5" );
		t.checkExpect(negX.toSmartString(), "~x" );
		t.checkExpect(negEightPlusTwo.toSmartString(), "~(8 + 2)" );
		t.checkExpect(negNegNegNegNegThree.toSmartString(), "~~~~~3" );
		//simplify tests
		t.checkExpect(sim1.simplify(), parser.parse("~x") );
		t.checkExpect(sim2.simplify(), parser.parse("~(2 * x)") );
		t.checkExpect(sim3.simplify(), parser.parse("x") );
		t.checkExpect(sim4.simplify(), parser.parse("x + 3") );
		t.checkExpect(sim5.simplify(), parser.parse("1") );
		t.checkExpect(sim6.simplify(), parser.parse("1") );
		t.checkExpect(sim7.simplify(), parser.parse("0") );
		t.checkExpect(sim8.simplify(), parser.parse("(8 * x) ^ 2") );
		t.checkExpect(sim9.simplify(), parser.parse("x ^ 2") );
		t.checkExpect(sim10.simplify(), parser.parse("5") );
		t.checkExpect(sim11.simplify(), parser.parse("x + 3") );
		t.checkExpect(sim12.simplify(), parser.parse("~(x + 4)") );
		t.checkExpect(sim13.simplify(), parser.parse("~(x * 2 + 8)") );
		t.checkExpect(sim14.simplify(), parser.parse("5 * x / 9") );
		t.checkExpect(sim15.simplify(), parser.parse("x ^ 2 / 8") );
		t.checkExpect(sim16.simplify(), parser.parse("9 * x / 4") );
		t.checkExpect(sim17.simplify(), parser.parse("x ^ 2 / 6") );
		//derivative tests
		t.checkExpect(ie1.derivative(), der1 );
		t.checkExpect(ie2.derivative(), der2 );
		t.checkExpect(ie3.derivative(), der3 );
		t.checkExpect(ie4.derivative(), der4 );
		t.checkExpect(ie5.derivative(), der5 );
		t.checkExpect(ie6.derivative(), der6 );
		t.checkExpect(ie7.derivative(), der7 );
		t.checkExpect(ie8.derivative(), der8 );
		t.checkExpect(ie9.derivative(), der9 );
		t.checkExpect(ie10.derivative(), der10 );
		t.checkExpect(ie11.derivative(), der11 );
		t.checkExpect(ie12.derivative(), der12 );
		//complicated tests
		t.checkExpect(ie3.derivative().simplify(), parser.parse("1") );
		t.checkExpect(ie4.derivative().simplify(), parser.parse("1") );
		t.checkExpect(ie5.derivative().simplify(), parser.parse("2") );
		t.checkExpect(ie6.derivative().simplify(), parser.parse("~(2 / x ^ 2)") );
		t.checkExpect(ie7.derivative().simplify(), parser.parse("3 * x ^ 2") );
		t.checkExpect(ie8.derivative().simplify().toSmartString(), "x ^ (x + 1) * (ln x + (x + 1) / x)");
		t.checkExpect(ie9.derivative().simplify(), parser.parse("cos(2 * x) * 2") );
		t.checkExpect(ie10.derivative().simplify(), parser.parse("~sin(x + 5)") );
		t.checkExpect(ie12.derivative().simplify(), parser.parse("1 / (x + 5)"));
	}	 

	  
	public void testKuhlenhu10(Tester t){
		IExpression seven = new Number(7.0);
		IExpression two = new Number(2.0);
		IExpression zero = new Number(0.0);
		IExpression one = new Number(1.0);
		IExpression ex = new Var();
		IExpression twoPlusSeven = new Sum(two, seven);
		IExpression twoPlusEx = new Sum(two, ex);
		IExpression sevenMinusTwo = new Difference(seven, two);
		IExpression twoTimesEx = new Product(two, ex);
		IExpression tpsDividesTwo = new Quotient(twoPlusSeven, two);
		IExpression sevenPowerTwo = new Exponentiation(seven, two);
		IExpression ttexNeg = new Neg(twoTimesEx);
		IExpression sinTwo = new Sin(two);
		IExpression cosTwo = new Cos(two);
		IExpression lnSeven = new Ln(seven);
		IExpression lnEx = new Ln(ex);
		IExpression cosEx = new Cos(ex);
		IExpression sinTPEx = new Sin(twoPlusEx);
		IExpression exDividesTwo = new Quotient(ex, two);
		IExpression lnOne = new Ln(one);
		IExpression onePlusZero = new Sum(one, zero);
		IExpression sevenDividesOne = new Quotient (seven , one);
		IExpression exPlusEx = new Sum(ex,ex);
		IExpression sevenTimesZero = new Product(seven, zero);
		IExpression neg = new Difference(one, seven);
		IExpression negDiff = new Difference(ex, new Neg(seven));
		IExpression prodQuo = new Product(new Quotient(ex, two), seven);
		IExpression quoQuo = new Quotient(new Quotient(ex, two), seven);
		t.checkExpect(twoPlusEx.derivative(), new Sum(zero,one));
		t.checkExpect(ex.derivative(), one);
		t.checkExpect(seven.derivative(), zero);
		t.checkExpect(lnEx.derivative(), new Quotient(one, ex));
		t.checkExpect(cosEx.derivative(), new Product(new Neg(new Sin(ex)), one));
		t.checkExpect(sinTPEx.derivative(), new Product(new Cos(twoPlusEx), new Sum(zero, one)));
		t.checkExpect(twoPlusEx.derivative(), new Sum(zero, one));
		t.checkExpect(sevenMinusTwo.derivative(), new Difference(zero,zero));
		t.checkExpect(twoTimesEx.derivative(), new Sum(new Product(zero, ex), new Product(one, two)));
		t.checkExpect(exDividesTwo.derivative(), new Quotient(new Difference(new Product(one, two), new Product(zero, ex)), new Exponentiation(two, two)));
		t.checkExpect(ttexNeg.derivative().toSmartString(), "~(0 * x + 1 * 2)");
		t.checkExpect(lnOne.simplify(), new Number(0.0));
		t.checkExpect(onePlusZero.simplify(), one);
		t.checkExpect(sevenDividesOne.simplify(), seven);
		t.checkExpect(sevenTimesZero.simplify(), zero);
		t.checkExpect(neg.simplify(), new Neg(new Number(6.0)));
		t.checkExpect(exPlusEx.simplify(), new Product(two, ex));
		t.checkExpect(negDiff.simplify(), new Sum(ex, seven));
		t.checkExpect(prodQuo.simplify(), new Quotient(new Product(ex, seven), two));
		t.checkExpect(quoQuo.simplify().toSmartString(), "x / 14");
		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(ex.containsVar(), true);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkExpect(twoPlusEx.containsVar(), true);
		t.checkExpect(sevenMinusTwo.containsVar(), false);
		t.checkExpect(twoTimesEx.containsVar(), true);
		t.checkExpect(tpsDividesTwo.containsVar(), false);
		t.checkExpect(sevenPowerTwo.containsVar(), false);
		t.checkExpect(ttexNeg.containsVar(), true);
		t.checkExpect(sinTwo.containsVar(), false);
		t.checkExpect(cosTwo.containsVar(), false);
		t.checkExpect(lnSeven.containsVar(), false);
		t.checkInexact(seven.eval(10.0), 7.0, 0.0000001);
		t.checkInexact(ex.eval(2.0), 2.0, 0.0000001);
		t.checkInexact(twoPlusSeven.eval(-34.3), 9.0, 0.0000001);
		t.checkInexact(twoPlusEx.eval(4.0), 6.0, 0.0000001);
		t.checkInexact(sevenMinusTwo.eval(44.5), 5.0, 0.0000001);
		t.checkInexact(twoTimesEx.eval(5.0), 10.0, 0.0000001);
		t.checkInexact(tpsDividesTwo.eval(6.4), 4.5, 0.0000001);
		t.checkInexact(sevenPowerTwo.eval(5.1), 49.0, 0.0000001);
		t.checkInexact(ttexNeg.eval(4.0), -8.0, 0.0000001);
		t.checkInexact(sinTwo.eval(2.0), Math.sin(2.0), 0.0000001);
		t.checkInexact(cosTwo.eval(2.0), Math.cos(2.0), 0.0000001);
		t.checkInexact(lnSeven.eval(3.3), Math.log(7.0), 0.0000001);
		t.checkExpect(seven.toString(), "7.0");
		t.checkExpect(ex.toString(), "x");
		t.checkExpect(twoPlusSeven.toString(), "(2.0 + 7.0)");
		t.checkExpect(twoPlusEx.toString(), "(2.0 + x)");
		t.checkExpect(sevenMinusTwo.toString(), "(7.0 - 2.0)");
		t.checkExpect(twoTimesEx.toString(), "(2.0 * x)");
		t.checkExpect(tpsDividesTwo.toString(), "((2.0 + 7.0) / 2.0)");
		t.checkExpect(sevenPowerTwo.toString(), "(7.0 ^ 2.0)");
		t.checkExpect(ttexNeg.toString(), "~((2.0 * x))");
		t.checkExpect(sinTwo.toString(), "sin(2.0)");
		t.checkExpect(cosTwo.toString(), "cos(2.0)");
		t.checkExpect(lnSeven.toString(), "ln(7.0)");
		t.checkExpect(seven.toSmartString(), "7");
		t.checkExpect(ex.toSmartString(), "x");
		t.checkExpect(twoPlusSeven.toSmartString(), "2 + 7");
		t.checkExpect(twoPlusEx.toSmartString(), "2 + x");
		t.checkExpect(sevenMinusTwo.toSmartString(), "7 - 2");
		t.checkExpect(twoTimesEx.toSmartString(), "2 * x");
		t.checkExpect(tpsDividesTwo.toSmartString(), "(2 + 7) / 2");
		t.checkExpect(sevenPowerTwo.toSmartString(), "7 ^ 2");
		t.checkExpect(ttexNeg.toSmartString(), "~(2 * x)");
		t.checkExpect(sinTwo.toSmartString(), "sin 2");
		t.checkExpect(cosTwo.toSmartString(), "cos 2");
		t.checkExpect(lnSeven.toSmartString(), "ln 7");
	}
	
   public void testazeltser10(Tester t) {
      IExpressionParser parser = new IExpressionParser();
      IExpression num1 = parser.parse("10"); // --> new Number(10.0)
      IExpression num2 = parser.parse("3.5");
      IExpression var = parser.parse("x");
      IExpression sum1 = parser.parse("5 + 3");
      IExpression sum2 = parser.parse("x + 8");
      IExpression diff1 = parser.parse("9 - 2");
      IExpression diff2 = parser.parse("6 - x");
      IExpression diff3 = parser.parse("x - 100.0");
      IExpression prod1 = parser.parse("4 * 5");
      IExpression prod2 = parser.parse("10 * x");
      IExpression prod3 = parser.parse("x * 23");
      IExpression quot1 = parser.parse("10 / 2");
      IExpression quot2 = parser.parse("8 / x");
      IExpression quot3 = parser.parse("x / 4");
      IExpression exp1 = parser.parse("36 ^ 0.5");
      IExpression exp2 = parser.parse("x ^ 3");
      IExpression exp3 = parser.parse("2 ^ x");
      IExpression neg1 = parser.parse("~x");
      IExpression neg2 = parser.parse("~-3");
      IExpression sin1 = parser.parse("sin x");
      IExpression sin2 = parser.parse("sin(x + 2)");
      IExpression sin3 = parser.parse("sin 4.71238898038469"); // 3 * pi / 2
      IExpression ln1 = parser.parse("ln x");
      IExpression ln2 = parser.parse("ln x ^ 2");
      IExpression ln3 = parser.parse("ln 20.085536923187664"); // e ^ 3
      IExpression cos1 = parser.parse("cos x ^ 2");
      IExpression cos2 = parser.parse("cos(x - 5)");
      IExpression cos3 = parser.parse("cos 1.0471975511965976"); // pi / 3

      t.checkExpect(num1.containsVar(), false);
      t.checkInexact(num1.eval(3.0), 10.0, 0.0000001);
      t.checkExpect(num1.toString(), "10.0");
      t.checkExpect(num1.toSmartString(), "10");
      t.checkExpect(num2.containsVar(), false);
      t.checkInexact(num2.eval(5.0), 3.5, 0.0000001);
      t.checkExpect(num2.toString(), "3.5");
      t.checkExpect(num2.toSmartString(), "3.5");
      t.checkExpect(var.containsVar(), true);
      t.checkInexact(var.eval(3.0), 3.0, 0.0000001);
      t.checkExpect(var.toString(), "x");
      t.checkExpect(var.toSmartString(), "x");
      t.checkExpect(sum1.containsVar(), false);
      t.checkInexact(sum1.eval(10.0), 8.0, 0.0000001);
      t.checkExpect(sum1.toString(), "(5.0 + 3.0)");
      t.checkExpect(sum1.toSmartString(), "5 + 3");
      t.checkExpect(sum2.containsVar(), true);
      t.checkInexact(sum2.eval(3.5), 11.5, 0.0000001);
      t.checkExpect(sum2.toString(), "(x + 8.0)");
      t.checkExpect(sum2.toSmartString(), "x + 8");
      t.checkExpect(diff1.containsVar(), false);
      t.checkInexact(diff1.eval(4.1), 7.0, 0.0000001);
      t.checkExpect(diff1.toString(), "(9.0 - 2.0)");
      t.checkExpect(diff1.toSmartString(), "9 - 2");
      t.checkExpect(diff2.containsVar(), true);
      t.checkInexact(diff2.eval(5.0), 1.0, 0.0000001);
      t.checkExpect(diff2.toString(), "(6.0 - x)");
      t.checkExpect(diff2.toSmartString(), "6 - x");
      t.checkExpect(diff3.containsVar(), true);
      t.checkInexact(diff3.eval(5.0), -95.0, 0.0000001);
      t.checkExpect(diff3.toString(), "(x - 100.0)");
      t.checkExpect(diff3.toSmartString(), "x - 100");
      t.checkExpect(prod1.containsVar(), false);
      t.checkInexact(prod1.eval(4.2), 20.0, 0.0000001);
      t.checkExpect(prod1.toString(), "(4.0 * 5.0)");
      t.checkExpect(prod1.toSmartString(), "4 * 5");
      t.checkExpect(prod2.containsVar(), true);
      t.checkInexact(prod2.eval(13.5), 135.0, 0.0000001);
      t.checkExpect(prod2.toString(), "(10.0 * x)");
      t.checkExpect(prod2.toSmartString(), "10 * x");
      t.checkExpect(prod3.containsVar(), true);
      t.checkInexact(prod3.eval(4.0), 92.0, 0.0000001);
      t.checkExpect(prod3.toString(), "(x * 23.0)");
      t.checkExpect(prod3.toSmartString(), "x * 23");
      t.checkExpect(quot1.containsVar(), false);
      t.checkInexact(quot1.eval(4.2), 5.0, 0.0000001);
      t.checkExpect(quot1.toString(), "(10.0 / 2.0)");
      t.checkExpect(quot1.toSmartString(), "10 / 2");
      t.checkExpect(quot2.containsVar(), true);
      t.checkInexact(quot2.eval(2.0), 4.0, 0.0000001);
      t.checkExpect(quot2.toString(), "(8.0 / x)");
      t.checkExpect(quot2.toSmartString(), "8 / x");
      t.checkExpect(quot3.containsVar(), true);
      t.checkInexact(quot3.eval(20.0), 5.0, 0.0000001);
      t.checkExpect(quot3.toString(), "(x / 4.0)");
      t.checkExpect(quot3.toSmartString(), "x / 4");
      t.checkExpect(exp1.containsVar(), false);
      t.checkInexact(exp1.eval(9999999999.2), 6.0, 0.0000001);
      t.checkExpect(exp1.toString(), "(36.0 ^ 0.5)");
      t.checkExpect(exp1.toSmartString(), "36 ^ 0.5");
      t.checkExpect(exp1.containsVar(), false);
      t.checkInexact(exp2.eval(2.0), 8.0, 0.0000001);
      t.checkExpect(exp2.toString(), "(x ^ 3.0)");
      t.checkExpect(exp2.toSmartString(), "x ^ 3");
      t.checkExpect(exp2.containsVar(), true);
      t.checkInexact(exp3.eval(3), 8.0, 0.0000001);
      t.checkExpect(exp3.toString(), "(2.0 ^ x)");
      t.checkExpect(exp3.toSmartString(), "2 ^ x");
      t.checkExpect(neg1.containsVar(), true);
      t.checkInexact(neg1.eval(11.3), -11.3, 0.0000001);
      t.checkExpect(neg1.toString(), "~(x)");
      t.checkExpect(neg1.toSmartString(), "~x");
      t.checkExpect(neg2.containsVar(), false);
      t.checkInexact(neg2.eval(7.3), 3.0, 0.0000001);
      t.checkExpect(neg2.toString(), "~(-3.0)");
      t.checkExpect(neg2.toSmartString(), "~-3");
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
//      t.checkExpect(four.simplify(), "4");
//      t.checkExpect(twoPlusNine.simplify(), "11");
//      t.checkExpect(varX.simplify(), "x");
//      t.checkExpect(threeUpSeven.simplify(), "3 ^ 7");
//      t.checkExpect(cosOne.simplify(), "cos 1");
//      t.checkExpect(lnTwo.simplify(), "ln 2");
//      t.checkExpect(sinThree.simplify(), "sin 3");
//      t.checkExpect(negOne.simplify(), "~ 1");
//      t.checkExpect(sevenMinusVar.simplify(), "7 - x");
//      t.checkExpect(varTimesThree.simplify(), "x * 3.");
//      t.checkExpect(sevenDividedBySeven.simplify(), "1.0");
//      t.checkExpect(seven.derivative(), "0.0");
//      t.checkExpect(cosOne.derivative(), "0.0");
//      t.checkExpect(lnTwo.derivative(), "0.0");
//      t.checkExpect(sinTwo.derivative(), "0.0");
//      t.checkExpect(negTwo.derivative(), "0.0");
//      t.checkExpect(cosOneUpTwo.derivative(), "0.0");
//      t.checkExpect(varUpFive.derivative(), "5 * x ^ 4");
//      t.checkExpect(twoPlusSeven.derivative(), "0.0");
//      t.checkExpect(varX.derivative(), "1.0");
//      t.checkExpect(sevenMinusVar.derivative(), "7 - x");
//      t.checkExpect(varTimesThree.derivative(), "3.0");
//      t.checkExpect(sevenDividedByTwo.derivative(), "0.0");
//      t.checkExpect(twoUpSeven.derivative(), "0.0");
   }
      
	// W3

	public void testZBranson(Tester t) {
		IExpression seven = new Number(7.0);
		IExpression two = new Number(2.0);
		IExpression var = new Var();
		IExpression twoPlusSeven = new Sum(two, seven);
		IExpression sevenMinusTwo = new Difference(seven, two);
		IExpression sevenTimesTwo = new Product(seven, two);
		IExpression sevenDividedByTwo = new Quotient(seven, two);
		IExpression sevenToPowerTwo = new Exponentiation(seven, two);
		IExpression sevenNeg = new Neg(seven);
		IExpression sevenSin = new Sin(seven);
		IExpression sevenCos = new Cos(seven);
		IExpression sevenLn = new Ln(seven);

		IExpression twoTimesX = new Product(two, new Var());
		IExpression sevenDividedByX = new Quotient(seven, new Var());
		IExpression XSquared = new Exponentiation(new Var(), two);
		IExpression XSin = new Sin(new Var());
		IExpression expr1 = new Product(new Sum(two, seven),
				new Difference(seven, two));
		IExpression expr2 = new Sin(new Sum(two, new Var()));

		IExpression zero = new Number(0.0);
		IExpression five = new Number(5.0);
		IExpression one = new Number(1.0);
		IExpression three = new Number(3.0);
		IExpression x = new Var();
		IExpression fiveMinusOne = new Difference(five, one);
		IExpression negFiveTimesOne = new Neg(new Product(five, one));
		IExpression negFiveDividedByOne = new Neg(new Quotient(five, one));
		IExpression fiveToPowerOneTimesThree = new Exponentiation(five,
				new Product(one, three));

		IExpression s1 = new Sum(zero, five);
		IExpression s2 = new Quotient(zero, five);
		IExpression s3 = new Product(zero, five);
		IExpression s4 = new Exponentiation(five, one);
		IExpression s5 = new Neg(zero);
		IExpression s6 = new Quotient(five, five);
		IExpression s7 = new Sum(five, new Neg(one));
		IExpression s8 = new Product(five, new Neg(one));
		IExpression s9 = new Quotient(five, new Neg(one));
		IExpression s10 = new Exponentiation(new Exponentiation(five, one), three);

		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkExpect(sevenMinusTwo.containsVar(), false);
		t.checkExpect(sevenTimesTwo.containsVar(), false);
		t.checkExpect(sevenDividedByTwo.containsVar(), false);
		t.checkExpect(sevenToPowerTwo.containsVar(), false);
		t.checkExpect(sevenNeg.containsVar(), false);
		t.checkExpect(sevenSin.containsVar(), false);
		t.checkExpect(sevenCos.containsVar(), false);
		t.checkExpect(sevenLn.containsVar(), false);
		t.checkExpect(twoTimesX.containsVar(), true);
		t.checkExpect(sevenDividedByX.containsVar(), true);
		t.checkExpect(XSquared.containsVar(), true);
		t.checkExpect(XSin.containsVar(), true);
		t.checkExpect(expr1.containsVar(), false);
		t.checkExpect(expr2.containsVar(), true);
		t.checkInexact(seven.eval(10.0), 7.0, 0.0000001);
		t.checkInexact(var.eval(10.0), 10.0, 0.0000001);
		t.checkInexact(twoPlusSeven.eval(-34.3), 9.0, 0.0000001);
		t.checkInexact(sevenMinusTwo.eval(1.0), 5.0, 0.0000001);
		t.checkInexact(sevenTimesTwo.eval(1.0), 14.0, 0.0000001);
		t.checkInexact(sevenDividedByTwo.eval(1.0), 3.5, 0.0000001);
		t.checkInexact(sevenToPowerTwo.eval(1.0), 49.0, 0.0000001);
		t.checkInexact(sevenNeg.eval(25.0), -7.0, 0.0000001);
		t.checkInexact(sevenSin.eval(6.9), Math.sin(7), 0.0000001);
		t.checkInexact(sevenCos.eval(89.4), Math.cos(7), 0.0000001);
		t.checkInexact(sevenLn.eval(99.6), Math.log(7), 0.0000001);
		t.checkInexact(twoTimesX.eval(2.0), 4.0, 0.0000001);
		t.checkInexact(sevenDividedByX.eval(7.0), 1.0, 0.0000001);
		t.checkInexact(XSquared.eval(4.0), 16.0, 0.0000001);
		t.checkInexact(XSin.eval(2.0), Math.sin(2), 0.0000001);
		t.checkInexact(expr1.eval(19.0), 45.0, 0.0000001);
		t.checkInexact(expr2.eval(1.0), Math.sin(3), 0.0000001);
		t.checkExpect(seven.toString(), "7.0");
		t.checkExpect(var.toString(), "x");
		t.checkExpect(twoPlusSeven.toString(), "(2.0 + 7.0)");
		t.checkExpect(sevenMinusTwo.toString(), "(7.0 - 2.0)");
		t.checkExpect(sevenTimesTwo.toString(), "(7.0 * 2.0)" );
		t.checkExpect(sevenDividedByTwo.toString(), "(7.0 / 2.0)");
		t.checkExpect(sevenToPowerTwo.toString(), "(7.0 ^ 2.0)" );
		t.checkExpect(sevenNeg.toString(), "~(7.0)" );
		t.checkExpect(sevenSin.toString(), "sin(7.0)");
		t.checkExpect(sevenCos.toString(), "cos(7.0)");
		t.checkExpect(sevenLn.toString(), "ln(7.0)");
		t.checkExpect(twoTimesX.toString(), "(2.0 * x)");
		t.checkExpect(sevenDividedByX.toString(), "(7.0 / x)");
		t.checkExpect(XSquared.toString(), "(x ^ 2.0)");
		t.checkExpect(XSin.toString(), "sin(x)");
		t.checkExpect(expr1.toString(), "((2.0 + 7.0) * (7.0 - 2.0))");
		t.checkExpect(expr2.toString(), "sin((2.0 + x))");
		t.checkExpect(seven.toSmartString(), "7");
		t.checkExpect(var.toSmartString(), "x");
		t.checkExpect(twoPlusSeven.toSmartString(), "2 + 7");
		t.checkExpect(sevenMinusTwo.toSmartString(), "7 - 2");
		t.checkExpect(sevenTimesTwo.toSmartString(), "7 * 2" );
		t.checkExpect(sevenDividedByTwo.toSmartString(), "7 / 2");
		t.checkExpect(sevenToPowerTwo.toSmartString(), "7 ^ 2" );
		t.checkExpect(sevenNeg.toSmartString(), "~7" );
		t.checkExpect(sevenSin.toSmartString(), "sin 7");
		t.checkExpect(sevenCos.toSmartString(), "cos 7");
		t.checkExpect(sevenLn.toSmartString(), "ln 7");
		t.checkExpect(twoTimesX.toSmartString(), "2 * x");
		t.checkExpect(sevenDividedByX.toSmartString(), "7 / x");
		t.checkExpect(XSquared.toSmartString(), "x ^ 2");
		t.checkExpect(XSin.toSmartString(), "sin x");
		t.checkExpect(expr1.toSmartString(), "(2 + 7) * (7 - 2)");
		t.checkExpect(expr2.toSmartString(), "sin(2 + x)");
		t.checkExpect(s1.simplify(), five);
		t.checkExpect(s2.simplify(), zero);
		t.checkExpect(s3.simplify(), zero);
		t.checkExpect(s4.simplify(), five);
		t.checkExpect(s5.simplify(), zero);
		t.checkExpect(s6.simplify(), one);
		t.checkExpect(s7.simplify(), new Number(4.0));
		t.checkExpect(s8.simplify().toSmartString(), "~5");
		t.checkExpect(s9.simplify().toSmartString(), "~5");
		t.checkExpect(s10.simplify(), new Number(125.0));
		t.checkExpect(two.derivative(), zero);
		t.checkExpect(x.derivative(), one);
		t.checkExpect(twoPlusSeven.derivative(), new Sum(two.derivative() , seven.derivative()));
		t.checkExpect(sevenMinusTwo.derivative(), new Difference(seven.derivative() , two.derivative()));
		t.checkExpect(sevenTimesTwo.derivative(), new Sum(new Product(seven.derivative() , two) , new Product(two.derivative() , seven)));
		t.checkExpect(sevenDividedByTwo.derivative(), new Quotient(new Difference(new Product(seven.derivative() , two) , new Product(two.derivative() , seven)) , new Exponentiation(two, two)));
		t.checkExpect(sevenToPowerTwo.derivative().toSmartString(), "2 * 7 ^ (2 - 1) * 0");
		t.checkExpect(sevenSin.derivative(), new Product(new Cos(seven) , seven.derivative()));
		t.checkExpect(sevenCos.derivative(), new Product(new Neg(new Sin(seven)) , seven.derivative()));
		t.checkExpect(sevenNeg.derivative(), new Neg(seven.derivative()));
		t.checkExpect(sevenLn.derivative(), new Quotient(seven.derivative() , seven));
	}

	public void testrcooper10(Tester t) {
		IExpression num = parser.parse("(42)");
		IExpression var = parser.parse("(x)");
		IExpression sum = parser.parse("(x+1337)");
		IExpression diff = parser.parse("(x - 1024)");
		IExpression prod = parser.parse("(x * 0.5)");
		IExpression quot = parser.parse("(x/2)");
		IExpression expo = parser.parse("(x^2)");
		IExpression neg = parser.parse("~(x + 3.14159)");
		IExpression sin = parser.parse("sin(x + 2)");
		IExpression cos = parser.parse("cos(x ^ 2)");
		IExpression ln = parser.parse("ln(x * 2)");

		t.checkExpect(num.containsVar(), false);
		t.checkInexact(num.eval(9001), 42.0, 0.0000001);
		t.checkExpect(num.toString(), "42.0");
		t.checkExpect(num.toSmartString(), "42");
		t.checkExpect(num.derivative().simplify().toSmartString(), "0");
		t.checkExpect(var.containsVar(), true);
		t.checkInexact(var.eval(4311.0), 4311.0, 0.0000001);
		t.checkExpect(var.toString(), "x");
		t.checkExpect(var.toSmartString(), "x");
		t.checkExpect(var.derivative().simplify().toSmartString(), "1");
		t.checkExpect(sum.containsVar(), true);
		t.checkInexact(sum.eval(1.0), 1338.0, 0.0000001);
		t.checkExpect(sum.toString(), "(x + 1337.0)");
		t.checkExpect(sum.toSmartString(), "x + 1337");
		t.checkExpect(sum.derivative().simplify().toSmartString(), "1");
		t.checkExpect(diff.containsVar(), true);
		t.checkInexact(diff.eval(512), -512.0, 0.0000001);
		t.checkExpect(diff.toString(), "(x - 1024.0)");
		t.checkExpect(diff.toSmartString(), "x - 1024");
		t.checkExpect(diff.derivative().simplify().toSmartString(), "1");
		t.checkExpect(prod.containsVar(), true);
		t.checkInexact(prod.eval(5), 2.5, 0.0000001);
		t.checkExpect(prod.toString(), "(x * 0.5)");
		t.checkExpect(prod.toSmartString(), "x * 0.5");
		t.checkExpect(prod.derivative().simplify().toSmartString(), "0.5");
		t.checkExpect(quot.containsVar(), true);
		t.checkInexact(quot.eval(5), 2.5, 0.0000001);
		t.checkExpect(quot.toString(), "(x / 2.0)");
		t.checkExpect(quot.toSmartString(), "x / 2");
		t.checkExpect(quot.derivative().toString(), "(((1.0 * 2.0) - (0.0 * x)) / (2.0 ^ 2.0))");
		t.checkExpect(expo.containsVar(), true);
		t.checkInexact(expo.eval(11.0), 121.0, 0.0000001);
		t.checkExpect(expo.toString(), "(x ^ 2.0)");
		t.checkExpect(expo.toSmartString(), "x ^ 2");
		//t.checkExpect(expo.derivative().toString(), "((0.0 * x) + ((1 * 2.0) / x))");
		t.checkExpect(neg.containsVar(), true);
		t.checkInexact(neg.eval(1.0), -4.14159, 0.0000001);
		t.checkExpect(neg.toString(), "~((x + 3.14159))");
		t.checkExpect(neg.toSmartString(), "~(x + 3.14159)");
		t.checkExpect(new Neg(new Var()).toSmartString(), "~x");
		t.checkExpect(new Neg(new Exponentiation(new Var(), new Number(2))).toSmartString(), "~x ^ 2");
		t.checkExpect(neg.derivative().simplify().toSmartString(), "~1");
		t.checkExpect(sin.containsVar(), true);
		t.checkInexact(sin.eval(1.0), 0.1411200080598672, 0.0000001);
		t.checkExpect(sin.toString(), "sin((x + 2.0))");
		t.checkExpect(sin.toSmartString(), "sin(x + 2)");
		t.checkExpect(new Sin(new Var()).toSmartString(), "sin x");
		t.checkExpect(new Sin(new Exponentiation(new Var(), new Number(2))).toSmartString(), "sin x ^ 2");
		//t.checkExpect(sin.derivative().toString(), "((cos(x + 2.0) * (d/dx(x + 2.0)))");
		t.checkExpect(cos.containsVar(), true);
		t.checkInexact(cos.eval(2.0), -0.6536436208636119, 0.0000001);
		t.checkExpect(cos.toString(), "cos((x ^ 2.0))");
		t.checkExpect(cos.toSmartString(), "cos x ^ 2");
		t.checkExpect(new Cos(new Var()).toSmartString(), "cos x");
		//t.checkExpect(cos.derivative().toString(), "((~(sin(x ^ 2.0))) * (d/dx(x ^ 2.0)))");
		t.checkExpect(ln.containsVar(), true);
		t.checkInexact(ln.eval(11.57035), 3.141592971956217, 0.0000001);
		t.checkExpect(ln.toString(), "ln((x * 2.0))");
		t.checkExpect(ln.toSmartString(), "ln(x * 2)");
		t.checkExpect(new Ln(new Var()).toSmartString(), "ln x");
		t.checkExpect(new Ln(new Exponentiation(new Var(), new Number(2))).toSmartString(), "ln x ^ 2");
		//t.checkExpect(ln.derivative().toString(), "(d/dx(x * 2.0)) / (x * 2.0))");

		t.checkExpect(parser.parse("x + 0").simplify(), parser.parse("x"));
		t.checkExpect(parser.parse("x / 1").simplify(), parser.parse("x"));
		t.checkExpect(parser.parse("x * 0").simplify(), parser.parse("0"));
		t.checkExpect(parser.parse("ln 1").simplify(), parser.parse("0"));
		t.checkExpect(parser.parse("x + x").simplify(), parser.parse("2 * x"));
		t.checkExpect(parser.parse("-5").simplify(), parser.parse("~5"));//! //failed here
		t.checkExpect(parser.parse("x - (~2)").simplify(), parser.parse("x + 2"));
		t.checkExpect(parser.parse("x - (~(~2))").simplify(), parser.parse("x - 2"));
		t.checkExpect(parser.parse("(x / 2) * 3").simplify(), parser.parse("(x * 3) / 2"));
		t.checkExpect(parser.parse("(2 / x) / 3").simplify(), parser.parse("2 / (x * 3)"));
	}

	public void testjcroom10(Tester t) {
		IExpressionParser parser = new IExpressionParser();
		IExpression num1 = parser.parse("7"); // --> new Number(7.0);
		IExpression num2 = parser.parse("3.5");
		IExpression var = parser.parse("x");
		IExpression s = new Sum(new Var(), new Sum(new Number(3.0), new Number (2.0)));
		IExpression s2 = new Sum(new Var(), new Sum(new Number(3.0), new Number (0.0)));
		IExpression sum1 = parser.parse("3 + 2");
		IExpression sum2 = parser.parse("x + 7");
		IExpression diff1 = parser.parse("7 - 1");
		IExpression diff2 = parser.parse("8 - x"); 
		IExpression diff3 = parser.parse("x - 3.5");
		IExpression prod1 = parser.parse("3 * 4");
		IExpression prod2 = parser.parse("6 * x"); 
		IExpression prod3 = parser.parse("x * 3");
		IExpression p = new Product(s, new Difference(s, diff1));
		IExpression quot1 = parser.parse("20 / 5");
		IExpression quot2 = parser.parse("10 / x"); 
		IExpression quot3 = parser.parse("x / 4");
		IExpression exp1 = parser.parse("25 ^ 0.5");
		IExpression exp2 = parser.parse("x ^ 2");
		IExpression exp3 = parser.parse("2 ^ x");
		IExpression e = new Exponentiation(exp1, new Number(2.0));
		IExpression neg1 = parser.parse("~x"); 
		IExpression neg2 = parser.parse("~-2");
		IExpression n = new Neg(new Sum(parser.parse("1"), parser.parse("3.9 ^ 2")));
		IExpression sin1 = parser.parse("sin x"); 
		IExpression sin2 = parser.parse("sin(x + 2)");
		IExpression sin3 = parser.parse("sin 4.71238898038469"); // 3 * pi / 2;
		IExpression cos1 = parser.parse("cos x ^ 2");
		IExpression cos2 = parser.parse("cos(x - 5)");
		IExpression cos3 = parser.parse("cos x");
		IExpression ln1 = parser.parse("ln x"); 
		IExpression ln2 = parser.parse("ln x ^ 2");
		IExpression ln3 = parser.parse("ln 2");

		t.checkExpect(num1.containsVar(), false);
		t.checkInexact(num1.eval(3.0), 7.0, 0.0000001);
		t.checkExpect(num1.toString(), "7.0");
		t.checkExpect(num1.toSmartString(), "7");
		t.checkExpect(new Number(-27.0).simplify(), new Neg(new Number(27.0)));
		t.checkExpect(num1.derivative(), new Number(0.0));
		t.checkExpect(var.containsVar(), true);
		t.checkInexact(var.eval(12.0), 12.0, 0.0000001);
		t.checkExpect(var.toString(), "x");
		t.checkExpect(var.toSmartString(), "x");
		t.checkExpect(var.derivative(), new Number(1.0));
		t.checkExpect(new Sum(sum1, sum2).containsVar(), true);
		t.checkInexact(sum2.eval(10.0), 17.0, 0.0000001);
		t.checkExpect(new Sum(var, sum1).toString(), "(x + (3.0 + 2.0))");
		t.checkExpect(s.toSmartString(), "x + (3 + 2)");
		t.checkExpect(s2.simplify(), new Sum(var, new Number(3.0)));
		t.checkExpect(new Sum(s, s).simplify(), new Product(new Number(2.0), new Sum(new Var(), new Number(5.0))));
		t.checkExpect(sum2.derivative(), new Sum(new Number(1.0), new Number(0.0)));
		t.checkExpect(diff2.containsVar(), true);
		t.checkInexact(diff1.eval(4.2), 6.0, 0.0000001);
		t.checkExpect(new Difference(diff1, s).toString(), "((7.0 - 1.0) - (x + (3.0 + 2.0)))");
		t.checkExpect(new Difference(diff1, s).toSmartString(), "7 - 1 - (x + (3 + 2))");
		//t.checkExpect(new Difference(s, new Neg(num1)).simplify(), new Sum(new Var(), new Number(12.0)));
		t.checkExpect(diff3.derivative(), new Difference(new Number(1.0), new Number(0.0)));
		t.checkExpect(new Product(prod1, sum2).containsVar(), true);
		t.checkInexact(new Product(prod1, sum2).eval(4.0), 132.0, 0.0000001);
		t.checkExpect(new Sum(prod1, s).toString(), "((3.0 * 4.0) + (x + (3.0 + 2.0)))");
		t.checkExpect(p.toSmartString(),  "(x + (3 + 2)) * (x + (3 + 2) - (7 - 1))");
		t.checkExpect(new Quotient(parser.parse("5 * 0"), var).simplify(), new Number(0.0));
		t.checkExpect(new Product(new Quotient(var, num1), new Number(9.0)).simplify(), new Quotient(new Product(var, new Number(9.0)), num1));
		t.checkExpect(prod1.derivative(), parser.parse("0 * 4 + 0 * 3"));
		t.checkExpect(quot1.containsVar(), false);
		t.checkInexact(quot1.eval(4.2), 4.0, 0.0000001);
		t.checkExpect(quot1.toString(), "(20.0 / 5.0)");
		t.checkExpect(new Quotient(s, quot1).toSmartString(), "(x + (3 + 2)) / (20 / 5)");
		t.checkExpect(parser.parse("x ^ 2 / 1").simplify(), new Exponentiation(var, new Number(2.0)));
		t.checkExpect(parser.parse(" x / 4 / 7").simplify().toString(), "(x / 28.0)");
		t.checkExpect(quot1.derivative(), parser.parse("(0 * 5 - 0 * 20) / 5 ^ 2"));
		t.checkExpect(exp1.containsVar(), false);
		t.checkInexact(exp2.eval(15), 225.0, 0.0000001);
		t.checkExpect(exp1.toString(), "(25.0 ^ 0.5)");
		t.checkExpect(e.toSmartString(), "(25 ^ 0.5) ^ 2");
		t.checkExpect(exp3.derivative().toSmartString(), "2 ^ x * (1 * ln 2 + 0 * x / 2)");
		t.checkExpect(neg1.containsVar(), true);
		t.checkInexact(neg1.eval(7.3), -7.3, 0.0000001);
		t.checkExpect(neg1.toString(), "~(x)");
		t.checkExpect(n.toSmartString(), "~(1 + 3.9 ^ 2)");
		t.checkExpect(neg1.derivative(), new Neg(new Number(1.0)));
		t.checkExpect(sin1.containsVar(), true);
		t.checkInexact(sin1.eval(0.0), 0.0, 0.0000001);
		t.checkExpect(sin1.toString(), "sin(x)");
		t.checkExpect(sin2.toSmartString(), "sin(x + 2)");
		t.checkExpect(sin1.derivative(), parser.parse("cos x * 1"));
		t.checkExpect(cos1.containsVar(), true);
		t.checkInexact(cos1.eval(2.0), Math.cos(4.0), 0.0000001);
		t.checkExpect(cos1.toString(), "cos((x ^ 2.0))");
		t.checkExpect(cos1.toSmartString(), "cos x ^ 2");
		t.checkExpect(cos3.derivative(), new Product(new Neg(new Sin(var)), new Number(1.0)));;
		t.checkExpect(ln1.containsVar(), true);
		t.checkInexact(ln2.eval(1.0), 0.0, 0.0000001);
		t.checkExpect(ln1.toString(), "ln(x)");
		t.checkExpect(ln1.toSmartString(), "ln x");
		t.checkExpect(new Product(parser.parse("ln 1"), num1).simplify(), new Number(0.0));
		t.checkExpect(ln3.derivative(), new Quotient(new Number(0.0), new Number(2.0)));
	}

	public void testajones10(Tester t) {
		IExpression seven = new Number(7.0);
		IExpression x = new Var();
		IExpression twoPlusX = new Sum(new Number(2.0), new Var());
		IExpression sevenMinusX = new Difference(new Number(7.0), new Var());
		IExpression twoTimesX = new Product(new Number(2.0), new Var());
		IExpression fourDividedByTwo = new Quotient(new Number(4.0), new Number(2.0));
		IExpression fourDividedByX = new Quotient(new Number(4.0), new Var());
		IExpression xToTheThird = new Exponentiation(new Var(), new Number(3.0));
		IExpression twoToTheX = new Exponentiation(new Number(2.0), new Var());
		IExpression oppositeOfFive = new Neg(new Number(5.0));
		IExpression oppositeOfX= new Neg(new Var());
		IExpression sinOfShfiftyShfive = new Sin(new Number(55.0));
		IExpression sinOfX = new Sin(new Var());
		IExpression cosOfShfiftyShfive = new Cos(new Number(55.0));
		IExpression cosOfX = new Cos(new Var());
		IExpression lnOfTen = new Ln(new Number(10.0));
		IExpression lnOfX = new Ln(new Var());

		// examples for simplify specifically (will add more later obviously but
		// for the assignment of the examples this should do)
		// also: I assumed that we'd combine like variables in my test simplify cases as well(i.e. x + x = 2x)
		// so if we don't: I have the non-truely-simplified version commented out under each =) (just realized I have only one like this... =) )
		IExpression fivePlusFive = new Sum(new Number(5.0), new Number(5.0));
		IExpression xSquaredMinusZero = new Difference(new Exponentiation(new Var(), new Number(2.0)), new Number(0.0));
		IExpression twoXTimesOne = new Product(new Product(new Number(2.0), new Var()), new Number(1.0));
		IExpression zeroToTheX = new Exponentiation(new Number(0.0), new Var());
		IExpression zeroToTheZero = new Exponentiation(new Number(0.0), new Number(0.0));
		IExpression cosOfZero = new Cos(new Number(0.0));
		IExpression XMinusX = new Difference(new Var(), new Var());
		IExpression PRNxMinusFourPRNminusPRNxMinusFourPRN = new Difference(new Difference(new Var(), new Number(4.0)),
				new Difference(new Var(), new Number(4.0)));
		IExpression negationOfThreeXPlusPRNfivePlusX = new Sum(new Neg(new Product(new Number(3.0), new Var())),
				new Sum(new Number(5.0), new Var()));
		IExpression negationOfXTimesSeven = new Product(new Neg(new Var()),
				new Number(7.0));
		IExpression negationOfXSquaredDividedBySevenMinusX = new Quotient(new Exponentiation(new Var(),
				new Number(2.0)),
				new Difference(new Number(7.0),
						new Var()));
		IExpression xToTheNegativeThirdPower = new Exponentiation(new Neg(new Var()),
				new Number(3.0));
		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(x.containsVar(), true);
		t.checkExpect(twoPlusX.containsVar(), true);
		t.checkExpect(sevenMinusX.containsVar(), true);
		t.checkExpect(twoTimesX.containsVar(), true);
		t.checkExpect(fourDividedByTwo.containsVar(), false);
		t.checkExpect(twoToTheX.containsVar(), true);
		t.checkExpect(oppositeOfFive.containsVar(), false);
		t.checkExpect(sinOfShfiftyShfive.containsVar(), false);
		t.checkExpect(cosOfShfiftyShfive.containsVar(), false);
		t.checkExpect(lnOfTen.containsVar(), false);
		t.checkInexact(seven.eval(3.0), 7.0, 0.0000001);
		t.checkInexact(x.eval(10.0), 10.0, 0.0000001);
		t.checkInexact(twoPlusX.eval(2.0), 4.0, 0.0000001);
		t.checkInexact(sevenMinusX.eval(2.0), 5.0, 0.0000001);
		t.checkInexact(twoTimesX.eval(75.0), 150.0, 0.0000001);
		t.checkInexact(fourDividedByTwo.eval(5.0), 2.0, 0.0000001);
		t.checkInexact(twoToTheX.eval(3.0), 8.0, 0.0000001);
		t.checkInexact(oppositeOfFive.eval(5.0), -5.0, 0.0000001);
		t.checkInexact(sinOfShfiftyShfive.eval(5.0), Math.sin(55.0), 0.0000001);
		t.checkInexact(cosOfShfiftyShfive.eval(1.0), Math.cos(55.0), 0.0000001);
		t.checkInexact(lnOfTen.eval(23.0), Math.log(10.0), 0.0000001);
		t.checkExpect(seven.toString(), "7.0");
		t.checkExpect(x.toString(), "x");
		t.checkExpect(twoPlusX.toString(), "(2.0 + x)");
		t.checkExpect(sevenMinusX.toString(), "(7.0 - x)");
		t.checkExpect(twoTimesX.toString(), "(2.0 * x)");
		t.checkExpect(fourDividedByTwo.toString(), "(4.0 / 2.0)");
		t.checkExpect(twoToTheX.toString(), "(2.0 ^ x)");
		t.checkExpect(oppositeOfFive.toString(), "~(5.0)");
		t.checkExpect(sinOfShfiftyShfive.toString(), "sin(55.0)");
		t.checkExpect(cosOfShfiftyShfive.toString(), "cos(55.0)");
		t.checkExpect(lnOfTen.toString(), "ln(10.0)");
		t.checkExpect(seven.toSmartString(), "7");
		t.checkExpect(x.toSmartString(), "x");
		t.checkExpect(twoPlusX.toSmartString(), "2 + x");
		t.checkExpect(sevenMinusX.toSmartString(), "7 - x");
		t.checkExpect(twoTimesX.toSmartString(), "2 * x");
		t.checkExpect(fourDividedByTwo.toSmartString(), "4 / 2");
		t.checkExpect(twoToTheX.toSmartString(), "2 ^ x");
		t.checkExpect(oppositeOfFive.toSmartString(), "~5");
		t.checkExpect(sinOfShfiftyShfive.toSmartString(), "sin 55");
		t.checkExpect(cosOfShfiftyShfive.toSmartString(), "cos 55");
		t.checkExpect(lnOfTen.toSmartString(), "ln 10");
		t.checkExpect(fivePlusFive.simplify(), new Number(10.0));
		t.checkExpect(xSquaredMinusZero.simplify(), new Exponentiation(new Var(),
				new Number(2.0)));
		t.checkExpect(twoXTimesOne.simplify(), new Product(new Number(2.0),
				new Var()));
		t.checkExpect(zeroToTheX.simplify(), new Number(0.0));
		t.checkExpect(zeroToTheZero.simplify(), new Number(1.0));
		t.checkExpect(cosOfZero.simplify(), new Number(1.0));
		t.checkExpect(XMinusX.simplify(), new Number(0.0));
		t.checkExpect(PRNxMinusFourPRNminusPRNxMinusFourPRN.simplify(), new Number(0.0));
		t.checkExpect(negationOfThreeXPlusPRNfivePlusX.simplify().toSmartString(), "5 + x - 3 * x");
		t.checkExpect(negationOfXTimesSeven.simplify(), new Neg(new Product(new Var(),
				new Number(7.0))));
		t.checkExpect(negationOfXSquaredDividedBySevenMinusX.simplify().toSmartString(), "x ^ 2 / (7 - x)");
		t.checkExpect(xToTheNegativeThirdPower.simplify().toSmartString(), "(~x) ^ 3");
		t.checkExpect(seven.derivative(), new Number(0.0));
		t.checkExpect(x.derivative(), new Number(1.0));
		t.checkExpect(twoPlusX.derivative(), new Sum(new Number(2.0).derivative(),
				new Var().derivative()));
		t.checkExpect(sevenMinusX.derivative(), new Difference(new Number(7.0).derivative(),
				new Var().derivative()));
		t.checkExpect(twoTimesX.derivative(), new Sum(new Product(new Number(2.0).derivative(),
				new Var()),
				new Product(new Var().derivative(),
						new Number(2.0))));
		t.checkExpect(fourDividedByX.derivative(), new Quotient(new Difference(new Product(new Number(4.0).derivative(),
				new Var()),
				new Product(new Var().derivative(),
						new Number(4.0))),
						new Exponentiation(new Var(),
								new Number(2.0))));
		t.checkExpect(fourDividedByX.derivative().simplify().toSmartString(), "~(4 / x ^ 2)");
		t.checkExpect(xToTheThird.derivative().toSmartString(), "3 * x ^ (3 - 1) * 1");
		t.checkExpect(twoToTheX.derivative().toSmartString(), "2 ^ x * (1 * ln 2 + 0 * x / 2)");
		t.checkExpect(sinOfX.derivative(), new Product(new Cos(new Var()),
				new Var().derivative()));
		t.checkExpect(cosOfX.derivative(), new Product(new Neg(new Sin(new Var())),
				new Var().derivative()));
		t.checkExpect(oppositeOfX.derivative(), new Neg(new Var().derivative()));
		t.checkExpect(lnOfX.derivative(), new Quotient(new Var().derivative(),
				new Var()));
	}	

	public void testLgarrett10(Tester t) {
		IExpression seven = new Number(7.0);
		IExpression two = new Number(2.0);
		IExpression six = new Number(6.0);
		IExpression zero = new Number(0.0);
		IExpression twoPlusSeven = new Sum(two, seven);
		IExpression var = new Var();
		IExpression twoMinusSeven = new Difference(two, seven);
		IExpression twoTimesSeven = new Product(two, seven);
		IExpression sixDividedbyTwo = new Quotient(six, two);
		IExpression sevenSquared = new Exponentiation(seven, two);
		IExpression neg = new Neg(sevenSquared);
		IExpression sin = new Sin(two);
		IExpression cos = new Cos(var);
		IExpression ln = new Ln(var);
		IExpression sixPlusZero = new Sum(six, zero);
		IExpression one = new Number(1.0);
		IExpression lnCosX = new Ln(cos);
		IExpression xSquared = new Exponentiation(var, two);
		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkExpect(var.containsVar(), true);
		t.checkExpect(twoMinusSeven.containsVar(), false);
		t.checkExpect(twoTimesSeven.containsVar(), false);
		t.checkExpect(sixDividedbyTwo.containsVar(), false);
		t.checkExpect(sevenSquared.containsVar(), false);
		t.checkExpect(neg.containsVar(), false);
		t.checkExpect(sin.containsVar(), false);
		t.checkExpect(cos.containsVar(), true);
		t.checkExpect(ln.containsVar(), true);
		t.checkInexact(seven.eval(10.0), 7.0, 0.0000001);
		t.checkInexact(twoPlusSeven.eval(-34.3), 9.0, 0.0000001);
		t.checkInexact(var.eval(-45.9), -45.9, 0.0000001);
		t.checkInexact(twoMinusSeven.eval(18.3), -5.0, 0.0000001);
		t.checkInexact(twoTimesSeven.eval(21.7), 14.0, 0.0000001);
		t.checkInexact(sixDividedbyTwo.eval(71.9), 3.0, 0.0000001);
		t.checkInexact(sevenSquared.eval(99.0), 49.0, 0.0000001);
		t.checkInexact(neg.eval(8.0), -49.0, 0.0000001);
		t.checkInexact(sin.eval(6.0), Math.sin(2.0), 0.0000001);
		t.checkInexact(cos.eval(1.0), Math.cos(1.0), 0.0000001);
		t.checkInexact(ln.eval(1.0), 0.0, 0.0000001);
		t.checkExpect(seven.toString(), "7.0");
		t.checkExpect(var.toString(), "x");
		t.checkExpect(new Sum(twoTimesSeven, six).toString(), "((2.0 * 7.0) + 6.0)");
		t.checkExpect(new Difference(seven, twoPlusSeven).toString(),
		"(7.0 - (2.0 + 7.0))");
		t.checkExpect(new Product(twoTimesSeven, var).toString(),
		"((2.0 * 7.0) * x)");
		t.checkExpect(new Quotient(twoTimesSeven, sin).toString(),
		"((2.0 * 7.0) / sin(2.0))");
		t.checkExpect(new Sin(new Var()).toString(), "sin(x)");
		t.checkExpect(new Cos(twoPlusSeven).toString(), "cos((2.0 + 7.0))");
		t.checkExpect(new Ln(sevenSquared).toString(), "ln((7.0 ^ 2.0))");
		t.checkExpect(new Exponentiation(new Var(), cos).toString(),
		"(x ^ cos(x))");
		t.checkExpect(new Neg(sixPlusZero).toString(),
		"~((6.0 + 0.0))");
		t.checkExpect(seven.toSmartString(), "7");
		t.checkExpect(var.toSmartString(), "x");
		t.checkExpect(new Sum(twoTimesSeven, six).toSmartString(), "2 * 7 + 6");
		t.checkExpect(new Difference(seven, twoPlusSeven).toSmartString(),
		"7 - (2 + 7)");
		t.checkExpect(new Product(twoTimesSeven, var).toSmartString(),
		"2 * 7 * x");
		t.checkExpect(new Quotient(twoTimesSeven, sin).toSmartString(),
		"2 * 7 / sin 2");
		t.checkExpect(new Sin(new Var()).toSmartString(), "sin x");
		t.checkExpect(new Cos(twoPlusSeven).toSmartString(), "cos(2 + 7)");
		t.checkExpect(new Ln(sevenSquared).toSmartString(), "ln 7 ^ 2");
		t.checkExpect(new Exponentiation(new Var(), cos).toSmartString(),
		"x ^ (cos x)");
		t.checkExpect(new Neg(sixPlusZero).toSmartString(),
		"~(6 + 0)");
		t.checkExpect(seven.derivative(), zero);
		t.checkExpect(var.derivative(), one);
		t.checkExpect(new Sum(new Exponentiation(var, two), six).derivative(),
				new Sum(new Product(new Product(two,
						new Exponentiation(var,
								new Difference(two, one))), one), zero));
		t.checkExpect(new Difference(var, seven).derivative(), new Difference(one, zero));
		//           t.checkExpect(new Product(new Exponentiation(var, two), seven).derive(),
		//                                   new Sum(new Product(zero, new Exponentiation(var, two)),
		//                                           new Product(new Product(new Product(two,
		//                                                                    new Exponentiation(var,
		//                                                                                       new Difference(two, one))),
		//                                                                     one), seven)));
		t.checkExpect(new Product(seven, var).derivative(),
				new Sum(new Product(zero, var),
						new Product(one, seven)));
		t.checkExpect(new Quotient(new Sum( var, two), seven).derivative(),
				new Quotient(new Difference(new Product(new Sum(one, zero), seven),
						new Product(zero, new Sum(var, two))),
						new Exponentiation(seven, two)));
		t.checkExpect(new Neg(seven).derivative(), new Neg(zero));
		t.checkExpect(new Sin(var).derivative(), new Product(new Cos(var), one));
		t.checkExpect(cos.derivative(), new Product(new Neg(new Sin(var)), one));
		t.checkExpect(ln.derivative(), new Quotient(new Number(1.0), var));
		t.checkExpect(new Exponentiation(two, var).derivative().toSmartString(), "2 ^ x * (1 * ln 2 + 0 * x / 2)");
		t.checkExpect(sixPlusZero.simplify(), six);
		t.checkExpect(new Quotient(var, one).simplify(),
				var);
		t.checkExpect(new Product(zero, sevenSquared).simplify(), zero);
		t.checkExpect(new Sin(zero).simplify(), zero);
		t.checkExpect(new Sum(lnCosX, lnCosX).simplify(), new Product(two, lnCosX));

		//          t.checkExpect(new Difference(var, new Neg(seven)).simplify(),
		//                                        new Sum(var, seven));
		t.checkExpect(new Product(new Quotient(var, two), seven).simplify(),
				new Quotient(new Product(var, seven), two));
		t.checkExpect(new Quotient(new Quotient(seven, var), two).simplify(),
				new Quotient(seven, new Product(var, two)));
		t.checkExpect(new Product(six, two).simplify(), new Number(12.0));
		t.checkExpect(new Sum(new Sum(two, six), var).simplify(), new Sum(new Number(8.0), var));
		t.checkExpect(new Sum(new Number(-8.0), two).simplify(), new Neg(new Number(6.0)));
		t.checkExpect(new Number(-9.0).simplify(), new Neg(new Number(9.0)));
		t.checkExpect(new Difference(two, six).simplify(), new Neg(new Number(4.0)));
		IExpressionParser parser = new IExpressionParser();
		IExpression num1 = parser.parse("7"); // --> new Number(7.0);
		IExpression num2 = parser.parse("3.5");
		t.checkExpect(num1.containsVar(), false);
		t.checkInexact(num1.eval(3.0), 7.0, 0.0000001);
		t.checkExpect(num1.toString(), "7.0");
		t.checkExpect(num1.toSmartString(), "7");
		t.checkExpect(num2.containsVar(), false);
		t.checkInexact(num2.eval(5.0), 3.5, 0.0000001);
		t.checkExpect(num2.toString(), "3.5");
		t.checkExpect(num2.toSmartString(), "3.5");
		IExpression var1 = parser.parse("x");
		t.checkExpect(var1.containsVar(), true);
		t.checkInexact(var1.eval(3.0), 3.0, 0.0000001);
		t.checkExpect(var1.toString(), "x");
		t.checkExpect(var1.toSmartString(), "x");
		IExpression sum1 = parser.parse("3 + 2");
		IExpression sum2 = parser.parse("x + 7");
		t.checkExpect(sum1.containsVar(), false);
		t.checkInexact(sum1.eval(10.0), 5.0, 0.0000001);
		t.checkExpect(sum1.toString(), "(3.0 + 2.0)");
		t.checkExpect(sum1.toSmartString(), "3 + 2");
		t.checkExpect(sum2.containsVar(), true);
		t.checkInexact(sum2.eval(3.5), 10.5, 0.0000001);
		t.checkExpect(sum2.toString(), "(x + 7.0)");
		t.checkExpect(sum2.toSmartString(), "x + 7");
		IExpression diff1 = parser.parse("7 - 1");
		IExpression diff2 = parser.parse("8 - x");
		IExpression diff3 = parser.parse("x - 3.5");
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
		IExpression prod1 = parser.parse("3 * 4");
		IExpression prod2 = parser.parse("6 * x");
		IExpression prod3 = parser.parse("x * 3");
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
		IExpression quot1 = parser.parse("20 / 5");
		IExpression quot2 = parser.parse("10 / x");
		IExpression quot3 = parser.parse("x / 4");
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
		IExpression exp1 = parser.parse("25 ^ 0.5");
		IExpression exp2 = parser.parse("x ^ 2");
		IExpression exp3 = parser.parse("2 ^ x");
		t.checkExpect(exp1.containsVar(), false);
		t.checkInexact(exp1.eval(4.2), 5.0, 0.0000001);
		t.checkExpect(exp1.toString(), "(25.0 ^ 0.5)");
		t.checkExpect(exp1.toSmartString(), "25 ^ 0.5");
		t.checkExpect(exp1.containsVar(), false);
		t.checkInexact(exp1.eval(4.2), 5.0, 0.0000001);
		t.checkExpect(exp1.toString(), "(25.0 ^ 0.5)");
		t.checkExpect(exp1.toSmartString(), "25 ^ 0.5");
		t.checkExpect(exp1.containsVar(), false);
		t.checkInexact(exp1.eval(4.2), 5.0, 0.0000001);
		t.checkExpect(exp1.toString(), "(25.0 ^ 0.5)");
		t.checkExpect(exp1.toSmartString(), "25 ^ 0.5");
		IExpression neg1 = parser.parse("~x");
		IExpression neg2 = parser.parse("~-2");
		t.checkExpect(neg1.containsVar(), true);
		t.checkInexact(neg1.eval(7.3), -7.3, 0.0000001);
		t.checkExpect(neg1.toString(), "~(x)");
		t.checkExpect(neg1.toSmartString(), "~x");
		t.checkExpect(neg2.containsVar(), false);
		t.checkInexact(neg2.eval(7.3), 2.0, 0.0000001);
		t.checkExpect(neg2.toString(), "~(-2.0)");
		t.checkExpect(neg2.toSmartString(), "~-2");
		IExpression sin1 = parser.parse("sin x");
		IExpression sin2 = parser.parse("sin(x + 2)");
		IExpression sin3 = parser.parse("sin 4.71238898038469"); // 3 * pi / 2
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
		IExpression cos1 = parser.parse("cos x ^ 2");
		IExpression cos2 = parser.parse("cos(x - 5)");
		IExpression cos3 = parser.parse("cos 1.0471975511965976"); // pi / 3
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
		IExpression ln1 = parser.parse("ln x");
		IExpression ln2 = parser.parse("ln x ^ 2");
		IExpression ln3 = parser.parse("ln 20.085536923187664"); // e ^ 3
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

	public void testJgissend10(Tester t) {
		IExpression num1 = parser.parse("19");
		IExpression num2 = parser.parse("-19");
		IExpression var1 = parser.parse("x");
		IExpression sum1 = parser.parse("3 + 5");
		IExpression sum2 = parser.parse("x + 9");
		IExpression sum3 = parser.parse("12 + x");
		IExpression sum4 = parser.parse("12 + 0");
		IExpression sum5 = parser.parse("0 + 19");
		IExpression sum6 = parser.parse("x + x");
		IExpression diff1 = parser.parse("1 - 0");
		IExpression diff2 = parser.parse("3 - x");
		IExpression diff3 = parser.parse("x - 12");
		IExpression diff4 = parser.parse("12 - 5");
		IExpression diff5 = parser.parse("x - x");
		IExpression diff6 = parser.parse("0 - 9");
		IExpression diff7 = parser.parse("1 - ~9");
		IExpression prod1 = parser.parse("x * 1");
		IExpression prod2 = parser.parse("5 * 9");
		IExpression prod3 = parser.parse("9 * 0");
		IExpression prod4 = parser.parse("(12/4) * 3");
		IExpression quot1 = parser.parse("2 / 6");
		IExpression quot2 = parser.parse("x / 1");
		IExpression quot3 = parser.parse("(12 / 4) / 3");
		IExpression exp1 = parser.parse("25 ^ 3");
		IExpression exp2 = parser.parse("2 ^ x");
		IExpression neg1 = parser.parse("~8");
		IExpression neg2 = parser.parse("~x");
		IExpression sin1 = parser.parse("sin x");
		IExpression sin2 = parser.parse("sin(x + 2)");
		IExpression cos1 = parser.parse("cos 90");
		IExpression cos2 = parser.parse("cos(x - 5)");
		IExpression ln1 = parser.parse("ln x");
		IExpression ln2 = parser.parse("ln(2 ^ 7)");
		IExpression ln3 = parser.parse("ln 1");         t.checkExpect(num1.containsVar(), false);
		t.checkExpect(var1.containsVar(), true);
		t.checkExpect(sum3.containsVar(), true);
		t.checkExpect(diff1.containsVar(), false);
		t.checkExpect(prod1.containsVar(), true);
		t.checkExpect(quot1.containsVar(), false);
		t.checkExpect(exp1.containsVar(), false);
		t.checkExpect(neg1.containsVar(), false);
		t.checkExpect(sin1.containsVar(), true);
		t.checkExpect(cos1.containsVar(), false);
		t.checkExpect(ln1.containsVar(), true);
		t.checkInexact(num1.eval(3.0), 19.0, 0.0000001);
		t.checkInexact(var1.eval(3.0), 3.0, 0.0000001);
		t.checkInexact(sum3.eval(3.0), 15.0, 0.0000001);
		t.checkInexact(diff1.eval(3.0), 1.0, 0.0000001);
		t.checkInexact(prod1.eval(3.0), 3.0, 0.0000001);
		t.checkInexact(quot1.eval(3.0), 1.0/3, 0.0000001);
		t.checkInexact(exp1.eval(3.0), 15625.0, 0.0000001);
		t.checkInexact(neg1.eval(3.0), -8.0, 0.0000001);
		t.checkInexact(sin1.eval(3.0), 0.14112000805986721, 0.0000001);
		t.checkInexact(cos1.eval(3.0), -0.44807361612917013, 0.0000001);
		t.checkInexact(ln1.eval(3.0), 1.0986122886681098, 0.0000001);
		t.checkExpect(num1.toString(), "19.0");
		t.checkExpect(var1.toString(), "x");
		t.checkExpect(sum3.toString(), "(12.0 + x)");
		t.checkExpect(diff1.toString(), "(1.0 - 0.0)");
		t.checkExpect(prod1.toString(), "(x * 1.0)");
		t.checkExpect(quot1.toString(), "(2.0 / 6.0)");
		t.checkExpect(exp1.toString(), "(25.0 ^ 3.0)");
		t.checkExpect(neg1.toString(), "~(8.0)");
		t.checkExpect(sin1.toString(), "sin(x)");
		t.checkExpect(cos1.toString(), "cos(90.0)");
		t.checkExpect(ln1.toString(), "ln(x)");
		t.checkExpect(num1.toSmartString(), "19");
		t.checkExpect(var1.toSmartString(), "x");
		t.checkExpect(sum3.toSmartString(), "12 + x");
		t.checkExpect(diff1.toSmartString(), "1 - 0");
		t.checkExpect(prod1.toSmartString(), "x * 1");
		t.checkExpect(quot1.toSmartString(), "2 / 6");
		t.checkExpect(exp1.toSmartString(), "25 ^ 3");
		t.checkExpect(neg1.toSmartString(), "~8");
		t.checkExpect(sin1.toSmartString(), "sin x");
		t.checkExpect(cos1.toSmartString(), "cos 90");
		t.checkExpect(ln1.toSmartString(), "ln x");
		t.checkExpect(sum4.simplify(), new Number(12.0));
		t.checkExpect(quot2.simplify(), new Var());
		t.checkExpect(prod3.simplify(), new Number(0.0));
		t.checkExpect(ln3.simplify(), new Number(0.0));
		t.checkExpect(sum6.simplify(), new Product(new Number(2.0), new Var()));
		t.checkExpect(num2.simplify(), new Neg(new Number(19.0)));
		t.checkExpect(diff7.simplify(), new Number(10.0));
		t.checkExpect(prod4.simplify(), new Number(9.0));
		t.checkExpect(quot3.simplify(), new Number(1.0));
		t.checkExpect(num1.derivative(), new Number(0.0));
		t.checkExpect(var1.derivative(), new Number(1.0));
		t.checkExpect(sum1.derivative(), new Sum(new Number(3.0).derivative(),new Number(5.0).derivative()));
		t.checkExpect(diff1.derivative(), new Difference(new Number(3.0).derivative(), new Number(5.0).derivative()));
		t.checkExpect(prod1.derivative(), new Sum(new Product(new Var().derivative(), new Number(1.0)), new Product(new Number(1.0).derivative(), new Var())));
		t.checkExpect(quot1.derivative(), new Quotient(new Difference(new Product(new Number(2.0).derivative(), new Number(6.0)), new Product(new Number(6.0).derivative(), new Number(2.0))), new Exponentiation(new Number(6.0), new Number(2.0))));
		t.checkExpect(exp1.derivative(), new Product(new Product(new Number(3.0), new Exponentiation(new Number(25.0), new Difference(new Number(3.0), new Number(1.0)))), new Number(25.0).derivative()));
		t.checkExpect(neg1.derivative(), new Neg(new Number(8.0).derivative()));
		t.checkExpect(sin1.derivative(), new Product(new Cos(new Var()), new Var().derivative()));
		t.checkExpect(cos1.derivative(), new Product(new Neg(new Sin(new Number(90.0))), new Number(90.0).derivative()));
		t.checkExpect(ln1.derivative(), new Quotient(new Var().derivative(), new Var()));
	}	
	
	public void testvkumar11(Tester t) {
		// Test IExpressions
		Number num = new Number(42);
		Var var = new Var();
		Ln ln = new Ln(var);
		Sin sin = new Sin(num);
		Cos cos = new Cos(var);
		Neg neg = new Neg(num);
		Quotient quot = new Quotient(num, var);
		Sum sum = new Sum(var, num);
		Difference diff = new Difference(num, var);
		Product prod = new Product(var, num);
		Exponentiation expo = new Exponentiation(num, var);

		IExpressionParser parser = new IExpressionParser();
		IExpression mega = parser.parse("sin(cos x) / (4 ^ 3 - 2)");

		IExpression zero = new Number(0);
		IExpression one = new Number(1);

		// containsVar()
		t.checkExpect(num.containsVar(), false);
		t.checkExpect(var.containsVar(), true);
		t.checkExpect(ln.containsVar(), true);
		t.checkExpect(sin.containsVar(), false);
		t.checkExpect(cos.containsVar(), true); 
		t.checkExpect(neg.containsVar(), false);
		t.checkExpect(quot.containsVar(), true);
		t.checkExpect(sum.containsVar(), true);
		t.checkExpect(diff.containsVar(), true);
		t.checkExpect(prod.containsVar(), true);
		t.checkExpect(expo.containsVar(), true);
		// eval()
		t.checkInexact(num.eval(1), 42.0, 0.0000001);
		t.checkInexact(var.eval(1), 1.0, 0.0000001);
		t.checkInexact(ln.eval(1), 0.0, 0.0000001);
		t.checkInexact(sin.eval(1), -0.9165215479156338, 0.0000001);
		t.checkInexact(cos.eval(1), 0.5403023058681398, 0.0000001);
		t.checkInexact(neg.eval(1), -42.0, 0.0000001);
		t.checkInexact(quot.eval(1), 42.0, 0.0000001);
		t.checkInexact(sum.eval(1), 43.0, 0.0000001);
		t.checkInexact(diff.eval(1), 41.0, 0.0000001);
		t.checkInexact(prod.eval(1), 42.0, 0.0000001);
		t.checkInexact(expo.eval(1), 42.0, 0.0000001);
		// toString()
		t.checkExpect(num.toString(), "42.0");
		t.checkExpect(var.toString(), "x");
		t.checkExpect(ln.toString(), "ln(x)");
		t.checkExpect(sin.toString(), "sin(42.0)");
		t.checkExpect(cos.toString(), "cos(x)");
		t.checkExpect(neg.toString(), "~(42.0)");
		t.checkExpect(quot.toString(), "(42.0 / x)");
		t.checkExpect(sum.toString(), "(x + 42.0)");
		t.checkExpect(diff.toString(), "(42.0 - x)");
		t.checkExpect(prod.toString(), "(x * 42.0)");
		t.checkExpect(expo.toString(), "(42.0 ^ x)");
		t.checkExpect(mega.toString(), "(sin(cos(x)) / ((4.0 ^ 3.0) - 2.0))"); 
		// toSmartString()
		t.checkExpect(num.toSmartString(), "42");
		t.checkExpect(var.toSmartString(), "x");
		t.checkExpect(ln.toSmartString(), "ln x");
		t.checkExpect(sin.toSmartString(), "sin 42");
		t.checkExpect(cos.toSmartString(), "cos x");
		t.checkExpect(neg.toSmartString(), "~42");
		t.checkExpect(quot.toSmartString(), "42 / x");
		t.checkExpect(sum.toSmartString(), "x + 42");
		t.checkExpect(diff.toSmartString(), "42 - x");
		t.checkExpect(prod.toSmartString(), "x * 42");
		t.checkExpect(expo.toSmartString(), "42 ^ x");
		t.checkExpect(mega.toSmartString(), "sin cos x / (4 ^ 3 - 2)");
		// simplifications
		t.checkExpect(new Sum(zero, var).simplify(), var); // 0 + f = f
		t.checkExpect(new Sum(num, zero).simplify(), num); // f + 0 = f
		t.checkExpect(new Difference(zero, var).simplify(), new Neg(var)); // 0 ? f = ~f
		t.checkExpect(new Difference(num, zero).simplify(), num); // f ? 0 = f
		t.checkExpect(new Quotient(zero, var).simplify(), zero); // 0 / f = 0
		t.checkExpect(new Quotient(num, one).simplify(), num); // f / 1 = f
		t.checkExpect(new Product(one, var).simplify(), var); // 1 * f = f
		t.checkExpect(new Product(num, one).simplify(), num); // f * 1 = f
		t.checkExpect(new Product(zero, var).simplify(), zero); // 0 * f = 0
		t.checkExpect(new Product(num, zero).simplify(), zero); // f * 0 = 0
		t.checkExpect(new Exponentiation(var, zero).simplify(), one); // f ^ 0 = 1
		t.checkExpect(new Exponentiation(zero, num).simplify(), zero); // 0 ^ f = 0
		t.checkExpect(new Exponentiation(zero, zero).simplify(), one); // 0 ^ 0 = 1
		t.checkExpect(new Exponentiation(var, one).simplify(), var); // f ^ 1 = f
		t.checkExpect(new Ln(one).simplify(), zero); // ln(1) = 0
		t.checkExpect(new Sin(zero).simplify(), zero); // sin(0) = 0
		t.checkExpect(new Cos(zero).simplify(), one); // cos(0) = 1
		t.checkExpect(new Neg(zero).simplify(), zero); // ~0 = 0
		t.checkExpect(new Sum(var, var).simplify(), new Product(new Number(2), var)); // f + f = 2 * f
		t.checkExpect(new Product(var, var).simplify(), new Exponentiation(var, new Number(2))); // f * f = f ^ 2
		t.checkExpect(new Difference(var, var).simplify(), zero); // f ? f = 0
		t.checkExpect(new Quotient(num, num).simplify(), one); // f / f = 1
		t.checkExpect(new Number(-10).simplify(), new Neg(new Number(10))); // ?n = ~n
		t.checkExpect(new Neg(new Neg(var)).simplify(), var); // ~~f = f
		t.checkExpect(new Sum(new Neg(num), var).simplify(), new Difference(var, num)); // ~f + g = g ? f
		t.checkExpect(new Sum(num, new Neg(var)).simplify(), new Difference(num, var)); // f + (~g) = f ? g
		t.checkExpect(new Difference(var, new Neg(num)).simplify(), new Sum(var, num)); // f ? (~g) = f + g
		t.checkExpect(new Difference(new Neg(num), var).simplify(), new Neg(new Sum(num, var))); // ~f ? g = ~(f + g)
		t.checkExpect(new Product(new Neg(var), num).simplify(), new Neg(new Product(var, num))); // (~f) * g = ~(f * g) ((75))
		t.checkExpect(new Product(num, new Neg(var)).simplify(), new Neg(new Product(num, var))); // f * (~g) = ~(f * g)
		t.checkExpect(new Product(new Quotient(var, num), var).simplify(), new Quotient(new Exponentiation(var, new Number(2)), num)); // (f/g) * h = (f * h) / g
		t.checkExpect(new Product(var, new Quotient(var, num)).simplify(), new Quotient(new Exponentiation(var, new Number(2)), num)); // f * (g/h) = (f * g) / h
		t.checkExpect(new Quotient(new Neg(num), var).simplify(), new Neg(new Quotient(num, var))); // (~f) / g = ~(f / g)
		t.checkExpect(new Quotient(var, new Neg(num)).simplify(), new Neg(new Quotient(var, num))); // f / (~g) = ~(f / g)
		t.checkExpect(new Quotient(new Quotient(num, var), num).simplify(), new Quotient(num, new Product(var, num))); // (f / g) / h = f / (g * h)
		t.checkExpect(new Quotient(var, new Quotient(num, var)).simplify().toSmartString(), "x ^ 2 / 42"); // f / (g / h) = (f * h) / g
		t.checkExpect(new Exponentiation(num, new Neg(var)).simplify(), new Quotient(one, new Exponentiation(num, var))); // f ^ (~g) = 1 / (f ^ g)
		t.checkExpect(new Exponentiation(new Exponentiation(var, num), var).simplify(), new Exponentiation(var, new Product(num, var))); // (f ^ g) ^ h = f ^ (g * h)
	}
	
	public void testobabarin11(Tester t) {
		t.checkExpect(parser.parse("ln (1)").containsVar(), false);// ln 1
		t.checkExpect(parser.parse("sin(x)").containsVar(), true);// sin 2
		t.checkExpect(parser.parse("7 + x").containsVar(), true);// sum 3
		t.checkExpect(parser.parse("cos(4 + (sin (ln 3)))").containsVar(), false);// cos 4
		t.checkExpect(parser.parse("(x + ( 7 + 2))/ (x + 1)").containsVar(),
				true);// Division 5
		t.checkExpect(parser.parse("8 * 4 + 2 + 1 * 5").containsVar(), false);// Product 6
		t.checkExpect(parser.parse("5 ^ 4 ^ 2 ^ x * 2").containsVar(), true);// Power 7
		t.checkExpect(parser.parse("7").containsVar(), false);// Number 8
		t.checkExpect(parser.parse("x").containsVar(), true);// Variable 9
		t.checkExpect(parser.parse("(32 * 23) - (4 / 2) ").containsVar(), false); // Difference 10
		t.checkExpect(parser.parse("~(32 + 23)").containsVar(), false); // Negation 11
		t.checkInexact(parser.parse("ln ( 4 + 4)").eval(1), Math.log(8.0), 0.0000001);// Ln 1
		t.checkInexact(parser.parse("sin(cos(1))").eval(1), Math.sin(Math.cos(1)), 0.0000001); // Sin 2
		t.checkInexact(parser.parse("cos( 3 + x)").eval(5), Math.cos(3 + 5), 0.0000001); // Cos 3
		t.checkInexact(parser.parse(" ~( 34 + 4)").eval(0), -38.0, 0.0000001);// Neg 4
		t.checkInexact(parser.parse(" x + ( 2 + 4)").eval(2), 2.0 + 2.0 + 4.0, 0.0000001); // Sum 5
		t.checkInexact(parser.parse(" x * 3 * x").eval(4), 4.0 * 4 * 3, 0.0000001); // Product 6
		t.checkInexact(parser.parse(" (14.0 + (x ^ 2)) / 4 ").eval(8),
				(14.0 + (8 * 8)) / 4.0, 0.0000001);// Divide 7
		t.checkInexact(parser.parse("4").eval(0), 4.0, 0.0000001);// Number 8
		t.checkInexact(parser.parse("x").eval(1), 1.0, 0.0000001);// Var 9
		t.checkInexact(parser.parse("(3 ^ 2) - ( sin (1) - 1)").eval(1),
				(9 - (Math.sin(1) - 1)), 0.0000001);// Subtract 10
		t.checkInexact(parser.parse("8 ^ 2 * 3").eval(1), (Math.pow(8, 2)) * 3.0, 0.0000001); // Power 11
		t.checkExpect(parser.parse("x + 5") + "", "(x + 5.0)");// Sum 1
		t.checkExpect(parser.parse("4") + "", "4.0");// Number 2
		t.checkExpect(parser.parse("x") + "", "x");// Var 3
		t.checkExpect(parser.parse("(3 - (x - 3))") + "", "(3.0 - (x - 3.0))"); // Subtract 4
		t.checkExpect(parser.parse("3 * sin(x + 3)") + "",
		"(3.0 * sin((x + 3.0)))");// Product 5
		t.checkExpect(parser.parse("(3 - 1) / 3") + "", "((3.0 - 1.0) / 3.0)"); // Divide 6
		t.checkExpect(parser.parse("( 7 ^ (3  + x))") + "", "(7.0 ^ (3.0 + x))"); // Power 7
		t.checkExpect(parser.parse("sin(x + (2 + 5))") + "",
		"sin((x + (2.0 + 5.0)))");// Sin 8
		t.checkExpect(parser.parse("cos(4 - (x * 2))") + "",
		"cos((4.0 - (x * 2.0)))");// Cos 9
		t.checkExpect(parser.parse("ln(x + 4.0)") + "", "ln((x + 4.0))");// ln 10
		t.checkExpect(parser.parse("~(3 + 4)") + "", "~((3.0 + 4.0))"); // Neg 11
		t.checkExpect(parser.parse("x + 3 + 2").toSmartString(), "x + 3 + 2"); // Sum 1
		t.checkExpect(parser.parse("4 * 4 * 4").toSmartString(), "4 * 4 * 4"); // Product 2
		t.checkExpect(parser.parse("6 / 4 / 2").toSmartString(), "6 / 4 / 2"); // Divide 3
		t.checkExpect(parser.parse("4 ^ (x + 2)").toSmartString(), "4 ^ (x + 2)"); // Power
		t.checkExpect(parser.parse("3 * 4 - 5").toSmartString(), "3 * 4 - 5"); // Subtract
		t.checkExpect(parser.parse("~(5 + 6)").toSmartString(), "~(5 + 6)"); // Neg
		t.checkExpect(parser.parse("ln x").toSmartString(), "ln x");// Ln 7
		t.checkExpect(parser.parse("sin(2 + 4)").toSmartString(), "sin(2 + 4)");// Sin
		t.checkExpect(parser.parse("cos x ^ 2").toSmartString(), "cos x ^ 2"); // Cos
		t.checkExpect(parser.parse("4.0").toSmartString(), "4");// Num 10
		t.checkExpect(parser.parse("x").toSmartString(), "x"); // Var 11
		t.checkExpect(parser.parse("4 + 4 + 4").simplify(), new Number(12));// Sum
		t.checkExpect(parser.parse(" x + ( 2  * 4)").simplify(), new Sum(
				new Var(), new Number(8)));// Multiply 2
		t.checkExpect(parser.parse("7").simplify(), new Number(7));// Number 3
		t.checkExpect(parser.parse("x").simplify(), new Var());// Var 4
		t.checkExpect(parser.parse("(2 / (1 + 3)) * x").simplify(), new Product(
				new Number(0.5), new Var()));// Quotient 5
		t.checkExpect(parser.parse(" 7 ^ (1 + x)").simplify(), new Exponentiation(
				new Number(7), new Sum(new Number(1), new Var())));// Power 6
		t.checkExpect(parser.parse("sin((3 + 2) -(5 * 1))").simplify(),
				new Number(0));// Sine 7
		t.checkExpect(parser.parse("cos((2 * 2) + ( ~4 / 1))").simplify(),
				new Number(1));// Cos 8
		t.checkExpect(parser.parse("ln(5 - 4)").simplify(), new Number(0));// Ln 9
		t.checkExpect(parser.parse("~(~7)").simplify(), new Number(7));// Neg 10
		t.checkExpect(parser.parse("(2 - ~x)").simplify(), new Sum(new Number(2),
				new Var())); // Subtract 11
		t.checkExpect(parser.parse("2").derivative(), new Number(0));// Number 1
		t.checkExpect(parser.parse("x").derivative(), new Number(1));// Var 2
		t.checkExpect(parser.parse("x - 1").derivative(), new Difference(
				new Number(1), new Number(0)));// Subtract 3
		t.checkExpect(parser.parse("2 + x").derivative(), new Sum(new Number(0),
				new Number(1)));// Sum 4
		t.checkExpect(parser.parse("2 * 3").derivative().simplify(), parser
				.parse("0 * 3 * 0 * 2").simplify());// Product 5
		t.checkExpect(parser.parse("x ^ 2").derivative().simplify().simplify(),
				parser.parse(" (2.0 * ((x ^ (2.0 - 1.0)) * 1.0))").simplify()
				.simplify());// Power 6
		t.checkExpect(parser.parse("1/1").derivative().simplify(), parser.parse(
		"(((0.0 * 1.0) - (0.0 * 1.0)) / (1.0 ^ 2.0))").simplify()); // Quotient
		t.checkExpect(parser.parse("~(x)").derivative(), new Neg(new Number(1)));// Neg
		t.checkExpect(parser.parse("sin(x)").derivative(), parser
				.parse(" (cos(x) * 1.0)"));// Sin 9
		t.checkExpect(parser.parse("cos(x)").derivative(), parser
				.parse("(~(sin(x)) * 1.0)"));// Cos 10
		t.checkExpect(parser.parse("ln(x)").derivative(), parser
				.parse("(1.0 / x)"));// Ln 11
		t.checkExpect(parser.parse("x ^ x").derivative(), parser
				.parse("x ^ x * (1 * ln x + 1 * x / x)"));// Power 12
	}
	
	public void testjmcgeene09(Tester t){
		IExpression x = new Var();
		IExpression zero = new Number(0.0);
		IExpression one = new Number(1.0);
		IExpression two = new Number(2.0);
		IExpression three = new Number(3.0);
		IExpression four = new Number(4.0);
		IExpression five = new Number(5.0);
		IExpression six = new Number(6.0);
		IExpression seven = new Number(7.0);
		IExpression eight = new Number(8.0);
		IExpression nine = new Number(9.0);
		IExpression negFive = new Number(-5.0);
		IExpression onePlusOne = new Sum(one, one);
		IExpression threePlusFour = new Sum(three, four);
		IExpression ninePlusZero = new Sum(nine, zero);
		IExpression sevenPlusNegFive = new Sum(seven, negFive);
		IExpression fivePlusNegFive = new Sum(five, negFive);
		IExpression twoPlusNegFive = new Sum(two, negFive);
		IExpression oneMinusOne = new Difference (one, one);
		IExpression zeroMinusEight = new Difference(zero, eight);
		IExpression sevenMinusZero = new Difference(seven, zero);
		IExpression threeMinusNegFive = new Difference(three, negFive);
		IExpression oneTimesFive = new Product(one, five);
		IExpression sixTimesSix = new Product(six, six);
		IExpression halfTimesTwo = new Product(new Number(0.5), two);
		IExpression negFiveTimesFive = new Product (negFive, five);
		IExpression sevenTimesNegFive = new Product(seven, negFive);
		IExpression zeroTimesEight = new Product(zero, eight);
		IExpression negFiveTimesNegFive = new Product(negFive, negFive);
		IExpression oneOverOne = new Quotient(one, one);
		IExpression oneOverTwo = new Quotient(one, two);
		IExpression sevenOverSeven = new Quotient(seven, seven);
		IExpression eightOverFour = new Quotient(eight, four);
		IExpression zeroOverNine = new Quotient(zero, nine);
		IExpression sinZero = new Sin(zero);
		IExpression cosZero = new Cos(zero);
		IExpression lnOne = new Ln(one);
		IExpression nineSquared = new Exponentiation(nine, two);
		IExpression oneToFifth = new Exponentiation(one, five);
		IExpression zeroToNinth = new Exponentiation(zero, nine);
		IExpression zeroToZero = new Exponentiation(zero, zero);
		IExpression eightToFirst = new Exponentiation(eight, one);
	   t.checkExpect(x.toString(), "x");
	   t.checkExpect(one.toString(), "1.0");
	   t.checkExpect(ninePlusZero.toString(), "(9.0 + 0.0)");
	   t.checkExpect(oneMinusOne.toString(), "(1.0 - 1.0)");
	   t.checkExpect(negFiveTimesFive.toString(), "(-5.0 * 5.0)");
	   t.checkExpect(sevenOverSeven.toString(), "(7.0 / 7.0)");
	   t.checkExpect(sinZero.toString(), "sin(0.0)");
	   t.checkExpect(cosZero.toString(), "cos(0.0)");
	   t.checkExpect(lnOne.toString(), "ln(1.0)");
	   t.checkExpect(zeroToNinth.toString(), "(0.0 ^ 9.0)");
	   t.checkInexact(x.eval(1.0), 1.0, 0.0000001);
	   t.checkInexact(six.eval(4.0), 6.0, 0.0000001);
	   t.checkInexact(ninePlusZero.eval(3.0), 9.0, 0.0000001);
	   t.checkInexact(twoPlusNegFive.eval(-3.0), -3.0, 0.0000001);
	   t.checkInexact(sevenTimesNegFive.eval(934.0), -35.0, 0.0000001);
		t.checkInexact(zeroTimesEight.eval(943.0), 0.0, 0.0000001);
	   t.checkInexact(sevenOverSeven.eval(3.0), 1.0, 0.0000001);
	   t.checkInexact(eightOverFour.eval(2.0), 2.0, 0.0000001);
	   t.checkInexact(sinZero.eval(0.0), 0.0, 0.0000001);
	   t.checkInexact(cosZero.eval(0.0), 1.0, 0.0000001);
	   t.checkInexact(lnOne.eval(3.0), 0.0, 0.0000001);
	   t.checkInexact(oneToFifth.eval(4.6), 1.0, 0.0000001);
	   t.checkExpect(x.containsVar(), true);
	   t.checkExpect(onePlusOne.containsVar(), false);
	   t.checkExpect(threeMinusNegFive.containsVar(), false);
	   t.checkExpect(sixTimesSix.containsVar(), false);
	   t.checkExpect(zeroOverNine.containsVar(), false);
	   t.checkExpect(sinZero.containsVar(), false);
	   t.checkExpect(cosZero.containsVar(), false);
	   t.checkExpect(lnOne.containsVar(), false);
	   t.checkExpect(zeroToNinth.containsVar(), false);
	   t.checkExpect(x.toSmartString(), "x");
	   t.checkExpect(seven.toSmartString(), "7");
	   t.checkExpect(new Sum(onePlusOne, six).toSmartString(), "1 + 1 + 6");
	   t.checkExpect(new Sum(six, onePlusOne).toSmartString(), "6 + (1 + 1)");
	   t.checkExpect(new Sum(negFiveTimesNegFive, oneOverTwo).toSmartString(), "-5 * -5 + 1 / 2");
	   t.checkExpect(new Difference(sixTimesSix, zeroTimesEight).toSmartString(), "6 * 6 - 0 * 8");
	   t.checkExpect(new Product(onePlusOne, zeroTimesEight).toSmartString(), "(1 + 1) * (0 * 8)");
	   t.checkExpect(new Product(oneTimesFive, zeroTimesEight).toSmartString(), "1 * 5 * (0 * 8)");
	   t.checkExpect(new Quotient(oneOverTwo, oneOverTwo).toSmartString(), "1 / 2 / (1 / 2)");
	   t.checkExpect(new Quotient(oneMinusOne, zeroToNinth).toSmartString(), "(1 - 1) / 0 ^ 9");
	   t.checkExpect(new Quotient(sinZero, cosZero).toSmartString(), "sin 0 / cos 0");
	   t.checkExpect(new Quotient(lnOne, seven).toSmartString(), "ln 1 / 7");
	   t.checkExpect(new Neg(sixTimesSix).toSmartString(), "~(6 * 6)");
	   t.checkExpect(new Neg(oneOverTwo).toSmartString(), "~(1 / 2)");
	   t.checkExpect(new Neg(eightToFirst).toSmartString(), "~8 ^ 1");
	   t.checkExpect(new Neg(onePlusOne).toSmartString(), "~(1 + 1)");
	   t.checkExpect(new Neg(x).toSmartString(), "~x");
	   t.checkExpect(new Sin(sixTimesSix).toSmartString(), "sin(6 * 6)");
	   t.checkExpect(new Sin(oneOverTwo).toSmartString(), "sin(1 / 2)");
	   t.checkExpect(new Sin(eightToFirst).toSmartString(), "sin 8 ^ 1");
	   t.checkExpect(new Sin(onePlusOne).toSmartString(), "sin(1 + 1)");
	   t.checkExpect(new Sin(x).toSmartString(), "sin x");
	   t.checkExpect(new Cos(sixTimesSix).toSmartString(), "cos(6 * 6)");
	   t.checkExpect(new Cos(oneOverTwo).toSmartString(), "cos(1 / 2)");
	   t.checkExpect(new Cos(eightToFirst).toSmartString(), "cos 8 ^ 1");
	   t.checkExpect(new Cos(onePlusOne).toSmartString(), "cos(1 + 1)");
	   t.checkExpect(new Cos(x).toSmartString(), "cos x");
	   t.checkExpect(new Ln(sixTimesSix).toSmartString(), "ln(6 * 6)");
	   t.checkExpect(new Ln(oneOverTwo).toSmartString(), "ln(1 / 2)");
	   t.checkExpect(new Ln(eightToFirst).toSmartString(), "ln 8 ^ 1");
	   t.checkExpect(new Ln(onePlusOne).toSmartString(), "ln(1 + 1)");
	   t.checkExpect(new Ln(x).toSmartString(), "ln x");
	   t.checkExpect(new Exponentiation(eightToFirst, oneToFifth).toSmartString(), "(8 ^ 1) ^ 1 ^ 5");
	   t.checkExpect(new Exponentiation(cosZero, sixTimesSix).toSmartString(), "(cos 0) ^ (6 * 6)");
	   t.checkExpect(new Exponentiation(new Neg(one), four).toSmartString(), "(~1) ^ 4");
	}


	public void testNschaal10(Tester t) {
		IExpression zero = new Number(0.0);
		IExpression one = new Number(1.0);
		IExpression two = new Number(2.0);
		IExpression three = new Number(3.0);
		IExpression four = new Number(4.0);
		IExpression five = new Number(5.0);
		IExpression six = new Number(6.0);
		IExpression seven = new Number(7.0);
		IExpression eight = new Number(8.0);
		IExpression nine = new Number(9.0);
		IExpression ten = new Number(10.0);
		IExpression x = new Var();

		IExpression threeTimesX = new Product(three, x);
		IExpression twoPlusSeven = new Sum(two, seven);
		IExpression sixMinusFour = new Difference(six, four);
		IExpression fiveTimesTen = new Product(five, ten);
		IExpression tenOverTwo = new Quotient(ten, two);
		IExpression tenSquared = new Exponentiation(ten, two);
		IExpression negativeTen = new Neg(ten);
		IExpression sin1 = new Sin(one);
		IExpression cos8 = new Cos(eight);
		IExpression log10 = new Ln(ten);
		IExpression rule3 = new Sum( new Difference(x, four), zero);
		IExpression rule7 = new Quotient( new Exponentiation(new Var(), three), new Difference(two, one));
		IExpression rule11 = new Product( seven, new Difference(four, four));
		IExpression rule15 = new Ln(one);
		IExpression rule19 = new Sum(x, x);
		IExpression rule23 = new Number(-11.0);
		IExpression rule27 = new Difference(new Var(), new Neg(six));
		IExpression rule31 = new Product(new Quotient(new Var(), five), six);
		IExpression rule35 = new Quotient(new Quotient(x, three), ten);
		IExpression a = new Difference(new Sum(four, five), new Product(three, two));
		IExpression b = new Exponentiation(two, new Exponentiation(three, new Product(x, five)));
		IExpression c = new Sum(x, new Exponentiation(three, two));
		IExpression d = new Quotient(x, new Difference(new Exponentiation(x, two), one));
		IExpression e = new Sum(x, three);
		IExpression f = new Difference(x, three);
		IExpression g = new Product(x, three);
		IExpression h = new Quotient(x, three);
		IExpression i = new Exponentiation(x, three);
		IExpression i2 = new Exponentiation(three, x);
		IExpression j = new Sin(x);
		IExpression k = new Cos(two);
		IExpression l = new Neg(x);
		IExpression m = new Ln(x);


		//containsVar
		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkExpect(x.containsVar(), true);
		t.checkExpect(sixMinusFour.containsVar(), false);
		t.checkExpect(fiveTimesTen.containsVar(), false);
		t.checkExpect(threeTimesX.containsVar(), true);
		t.checkExpect(tenOverTwo.containsVar(), false);
		t.checkExpect(tenSquared.containsVar(), false);
		t.checkExpect(negativeTen.containsVar(), false);
		t.checkExpect(sin1.containsVar(), false);
		t.checkExpect(cos8.containsVar(), false);

		//eval
		t.checkInexact(seven.eval(10.0), 7.0, 0.0000001);
		t.checkInexact(twoPlusSeven.eval(2.0), 9.0, 0.0000001);
		t.checkInexact(x.eval(10.0), 10.0, 0.0000001);
		t.checkInexact(sixMinusFour.eval(4.0), 2.0, 0.0000001);
		t.checkInexact(fiveTimesTen.eval(10.0), 50.0, 0.0000001);
		t.checkInexact(threeTimesX.eval(5.0), 15.0, 0.0000001);
		t.checkInexact(tenOverTwo.eval(13.2), 5.0, 0.0000001);
		t.checkInexact(tenSquared.eval(1.1), 100.0, 0.0000001);
		t.checkInexact(negativeTen.eval(13.0), -10.0, 0.0000001);
		t.checkInexact(sin1.eval(5.0), 0.8414709848078965, 0.0000001);
		t.checkInexact(cos8.eval(5.0), -0.14550003380861354, 0.0000001);

		//toString
		t.checkExpect(threeTimesX.toString(), "(3.0 * x)");
		t.checkExpect(tenOverTwo.toString(), "(10.0 / 2.0)");
		t.checkExpect(fiveTimesTen.toString(), "(5.0 * 10.0)");
		t.checkExpect(negativeTen.toString(), "~(10.0)");
		t.checkExpect(tenSquared.toString(), "(10.0 ^ 2.0)");
		t.checkExpect(sin1.toString(), "sin(1.0)");
		t.checkExpect(a.toString(), "((4.0 + 5.0) - (3.0 * 2.0))");
		t.checkExpect(b.toString(), "(2.0 ^ (3.0 ^ (x * 5.0)))");
		t.checkExpect(c.toString(), "(x + (3.0 ^ 2.0))");
		t.checkExpect(d.toString(), "(x / ((x ^ 2.0) - 1.0))");
		t.checkExpect(sixMinusFour.toString(), "(6.0 - 4.0)");

		//toSmartString
		t.checkExpect(threeTimesX.toSmartString(), "3 * x");
		t.checkExpect(tenOverTwo.toSmartString(), "10 / 2");
		t.checkExpect(fiveTimesTen.toSmartString(), "5 * 10");
		t.checkExpect(negativeTen.toSmartString(), "~10");
		t.checkExpect(tenSquared.toSmartString(), "10 ^ 2");
		t.checkExpect(sin1.toSmartString(), "sin 1");
		t.checkExpect(a.toSmartString(), "4 + 5 - 3 * 2");
		t.checkExpect(b.toSmartString(), "2 ^ 3 ^ (x * 5)");
		t.checkExpect(c.toSmartString(), "x + 3 ^ 2");
		t.checkExpect(d.toSmartString(), "x / (x ^ 2 - 1)");
		t.checkExpect(sixMinusFour.toSmartString(), "6 - 4");

		//simplify
		//my first letter of last name is S, aka 19. So, 19 % 4 = 3
		//I'm doing rules: 3, 7, 11, 15, 19, 23, 27, 31, 35
		t.checkExpect(rule3.simplify(), new Difference(x, four));
		t.checkExpect(rule7.simplify().toSmartString(), "x ^ 3");
		t.checkExpect(rule11.simplify(), zero);
		t.checkExpect(rule15.simplify(), zero);
		t.checkExpect(rule19.simplify(), new Product(two, x));
		t.checkExpect(rule23.simplify(), new Neg(new Number(11.0)));
		t.checkExpect(rule27.simplify(), new Sum(new Var(), six));
		t.checkExpect(rule31.simplify(), new Quotient(new Product(new Var(), six), five));
		t.checkExpect(rule35.simplify(), new Quotient(x, new Number(30)));

		//derivative
		t.checkExpect(three.derivative().simplify(), zero);
		t.checkExpect(x.derivative().simplify(), one);
		t.checkExpect(e.derivative().simplify(), one);
		t.checkExpect(f.derivative().simplify(), one);
		t.checkExpect(g.derivative().simplify(), three);
		t.checkExpect(h.derivative().simplify(), new Number(1.0 / 3));
		t.checkExpect(i.derivative().simplify(), new Product(three, new Exponentiation(x, two)));
		t.checkExpect(i2.derivative().toSmartString(), "3 ^ x * (1 * ln 3 + 0 * x / 3)");
		t.checkExpect(j.derivative().simplify(), new Cos(x));
		t.checkExpect(k.derivative().simplify(), zero);
		t.checkExpect(l.derivative().simplify(), new Neg(one));
		t.checkExpect(m.derivative().simplify(), new Quotient(one, x));

	}

	public void testTscott10(Tester t){
		IExpression seven = parser.parse("7.0");
		IExpression two = parser.parse("2.0");
		IExpression one = parser.parse("1.0");
		IExpression varX = parser.parse("x");
		IExpression twoPlusSeven = parser.parse("2 + 7");
		IExpression sevenMinusVar = parser.parse("7 - x");
		IExpression varTimesTwo = parser.parse("x * 2");
		IExpression sevenDividedByTwo = parser.parse("7 / 2");
		IExpression twoUpSeven = parser.parse("2 ^ 7");
		IExpression cosOne = parser.parse("cos(1.0)");
		IExpression lnOne = parser.parse("ln(1.0)");
		IExpression sinOne = parser.parse("sin(1.0)");
		IExpression negOne = parser.parse("~(1.0)");
		IExpression cosOneUpTwo = parser.parse("~(1.0 ^ 2)");
		IExpression varUpFive = parser.parse("x ^ 5");
		IExpression prodEx = parser.parse("18.0 * x");
		IExpression quotEx = parser.parse("(2.0 + x) / 4");
		IExpression cosEx = parser.parse("cos(2.0 + x)");
		t.checkExpect(seven.containsVar(), false);
		t.checkExpect(twoPlusSeven.containsVar(), false);
		t.checkExpect(varX.containsVar(), true);
		t.checkExpect(sevenMinusVar.containsVar(), true);
		t.checkExpect(varTimesTwo.containsVar(), true);
		t.checkExpect(sevenDividedByTwo.containsVar(), false);
		t.checkExpect(twoUpSeven.containsVar(), false);
		t.checkInexact(seven.eval(10.0), 7.0, 0.0000001);
		t.checkInexact(twoPlusSeven.eval(-34.3), 9.0, 0.0000001);
		t.checkInexact(varX.eval(1.0), 1.0, 0.0000001);
		t.checkInexact(sevenMinusVar.eval(2.0), 5.0, 0.0000001);
		t.checkInexact(varTimesTwo.eval(5.0), 10.0, 0.0000001);
		t.checkInexact(sevenDividedByTwo.eval(5.0), 3.5, 0.0000001);
		t.checkInexact(twoUpSeven.eval(3.0), 128.0, 0.0000001);
		t.checkInexact(cosOne.eval(2.0), 0.5403023058681398, 0.0000001);
		t.checkInexact(sinOne.eval(2.0), 0.8414709848078965, 0.0000001);
		t.checkInexact(lnOne.eval(2.0), 0.0, 0.0000001);
		t.checkInexact(negOne.eval(2.0), -1.0, 0.0000001);
		t.checkExpect(seven.toString(), "7.0");
		t.checkExpect(twoPlusSeven.toString(), "(2.0 + 7.0)");
		t.checkExpect(varX.toString(), "x");
		t.checkExpect(sevenMinusVar.toString(), "(7.0 - x)");
		t.checkExpect(varTimesTwo.toString(), "(x * 2.0)");
		t.checkExpect(sevenDividedByTwo.toString(), "(7.0 / 2.0)");
		t.checkExpect(twoUpSeven.toString(), "(2.0 ^ 7.0)");
		t.checkExpect(cosOne.toString(), "cos(1.0)");
		t.checkExpect(lnOne.toString(), "ln(1.0)");
		t.checkExpect(sinOne.toString(), "sin(1.0)");
		t.checkExpect(negOne.toString(), "~(1.0)");
		t.checkExpect(seven.toSmartString(), "7");
		t.checkExpect(twoPlusSeven.toSmartString(), "2 + 7");
		t.checkExpect(varX.toSmartString(), "x");
		t.checkExpect(sevenMinusVar.toSmartString(), "7 - x");
		t.checkExpect(varTimesTwo.toSmartString(), "x * 2");
		t.checkExpect(sevenDividedByTwo.toSmartString(), "7 / 2");
		t.checkExpect(twoUpSeven.toSmartString(), "2 ^ 7");
		t.checkExpect(cosOne.toSmartString(), "cos 1");
		t.checkExpect(lnOne.toSmartString(), "ln 1");
		t.checkExpect(sinOne.toSmartString(), "sin 1");
		t.checkExpect(negOne.toSmartString(), "~1");
		t.checkExpect(cosOneUpTwo.toSmartString(), "~1 ^ 2");
		t.checkExpect(seven.simplify(), parser.parse("7"));
		t.checkExpect(varX.simplify(), parser.parse("x"));
		t.checkExpect(sevenMinusVar.simplify(), parser.parse("7 - x"));
		t.checkExpect(varTimesTwo.simplify(), parser.parse("x * 2."));
		t.checkExpect(sevenDividedByTwo.simplify(), parser.parse("3.5"));
		t.checkExpect(twoUpSeven.simplify(), parser.parse("128"));
		t.checkExpect(cosOne.simplify(), new Number(0.5403023058681398));
		t.checkExpect(lnOne.simplify(), parser.parse("0"));
		t.checkExpect(sinOne.simplify(), new Number(0.8414709848078965));
		t.checkExpect(negOne.simplify(), parser.parse("~1"));
		t.checkExpect(seven.derivative(), parser.parse("0.0"));
		t.checkExpect(twoPlusSeven.derivative().toSmartString(), "0 + 0");
		t.checkExpect(varX.derivative(), parser.parse("1.0"));
		t.checkExpect(sevenMinusVar.derivative(), parser.parse("0 - 1"));
		t.checkExpect(varTimesTwo.derivative().toSmartString(), "1 * 2 + 0 * x");
		t.checkExpect(sevenDividedByTwo.derivative().toSmartString(), "(0 * 2 - 0 * 7) / 2 ^ 2");
		t.checkExpect(twoUpSeven.derivative().toSmartString(), "7 * 2 ^ (7 - 1) * 0");
		t.checkExpect(cosOne.derivative().toSmartString(), "~sin 1 * 0");
		t.checkExpect(lnOne.derivative().toSmartString(), "0 / 1");
		t.checkExpect(negOne.derivative().toSmartString(), "~0");
		t.checkExpect(cosOneUpTwo.derivative().toSmartString(), "~(2 * 1 ^ (2 - 1) * 0)");
		t.checkExpect(varUpFive.derivative().toSmartString(), "5 * x ^ (5 - 1) * 1");
		t.checkExpect(prodEx.derivative().toSmartString(), "0 * x + 1 * 18");
		t.checkExpect(quotEx.derivative().toSmartString(), "((0 + 1) * 4 - 0 * (2 + x)) / 4 ^ 2");
		t.checkExpect(cosEx.derivative().toSmartString(), "~sin(2 + x) * (0 + 1)");
	}
	
	public static void main(String[] args) {
		Tester.run(new Examples08());
	}
}
