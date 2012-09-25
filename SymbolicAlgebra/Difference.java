public class Difference extends Binary {
   
   public Difference(IExpression left, IExpression right) {
       super(left, right, "-");
   }
   
   public double eval(double x) {
      return super.getLeft().eval(x) - super.getRight().eval(x);
   }
   
   public boolean equals(Difference that) {
      return this.getLeft().equals(that.getLeft())
             &&
             this.getRight().equals(that.getRight());
   }

   public boolean equals(Object that) {
      if (that instanceof Difference) {
         return this.equals((Difference) that);
      } else {
         return false;
      }
   }
   
   public IExpression simplify() {
      IExpression leftSimplified = this.getLeft().simplify();
      IExpression rightSimplified = this.getRight().simplify();
      IExpression zero = new Number(0.0);
      if (!(leftSimplified.containsVar()
            ||
            rightSimplified.containsVar())) { // rule 1
          return new Number(this.eval(0.0)).simplify();
      } else if (leftSimplified.equals(zero)) { //rule 4
          return new Neg(rightSimplified).simplify();
      } else if (rightSimplified.equals(zero)) { // rule 5
          return leftSimplified;
      } else if (leftSimplified.equals(rightSimplified)) { // rule 21
          return zero;
      } else if (rightSimplified instanceof Neg) { // rule 27
          return new Sum(leftSimplified, ((Neg) rightSimplified).expr).simplify();
      } else if (leftSimplified instanceof Neg) { // rule 28
          return new Neg(new Sum(((Neg) leftSimplified).expr,
                                 rightSimplified).simplify());
      } else {
          return new Difference(leftSimplified, rightSimplified);
      }
   }
   
   public IExpression derivative() {
       return new Difference(this.getLeft().derivative(),
                             this.getRight().derivative());
   }
}