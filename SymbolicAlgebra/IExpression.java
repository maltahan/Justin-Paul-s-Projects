interface IExpression {
   boolean containsVar();
   double eval(double x);
   String toString();
   String toSmartString();
   int getPrecedence();
   IExpression simplify();
   IExpression derivative();
}