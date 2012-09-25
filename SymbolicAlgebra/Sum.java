public class Sum extends Binary {
   
   public Sum(IExpression left, IExpression right) {
      super(left, right, "+");
   }
  
   public double eval(double x) {
       return super.getLeft().eval(x) + super.getRight().eval(x);
   }
   
   public boolean equals(Sum that) {
      return this.getLeft().equals(that.getLeft())
             &&
             this.getRight().equals(that.getRight());
   }

   public boolean equals(Object that) {
      if (that instanceof Sum) {
         return this.equals((Sum) that);
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
      } else if (leftSimplified.equals(zero)) { // rule 2
          return rightSimplified;
      } else if (rightSimplified.equals(zero)) { // rule 3
          return leftSimplified;
      } else if (leftSimplified.equals(rightSimplified)) { // rule 19
          return new Product(new Number(2.0), leftSimplified).simplify();
      } else if (leftSimplified instanceof Neg) { //rule 25
          return new Difference(rightSimplified,
                                new Neg(leftSimplified)).simplify();
      } else if (rightSimplified instanceof Neg) { //rule 26
          return new Difference(leftSimplified,
                                new Neg(rightSimplified)).simplify();
      } else {
          return new Sum(leftSimplified, rightSimplified);
      }
   }
   
   public IExpression derivative() {
       return new Sum(this.getLeft().derivative(),
                      this.getRight().derivative());
   }
}