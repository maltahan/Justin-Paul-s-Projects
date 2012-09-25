public class Quotient extends Binary {
   
   public Quotient(IExpression left, IExpression right) {
      super(left, right, "/");
   }
   
   public double eval(double x) {
      return super.getLeft().eval(x) / super.getRight().eval(x);
   }
   
   public boolean equals(Quotient that) {
      return this.getLeft().equals(that.getLeft())
             &&
             this.getRight().equals(that.getRight());
   }

   public boolean equals(Object that) {
      if (that instanceof Quotient) {
         return this.equals((Quotient) that);
      } else {
         return false;
      }
   }
   
   public IExpression simplify() {
      IExpression leftSimplified = this.getLeft().simplify();
      IExpression rightSimplified = this.getRight().simplify();
      IExpression zero = new Number(0.0);
      IExpression one = new Number(1.0);
      if (!(leftSimplified.containsVar()
            ||
            rightSimplified.containsVar())) { // rule 1
          return new Number(this.eval(0.0)).simplify();
      } else if (leftSimplified.equals(zero)) { // rule 6
          return zero;
      } else if (rightSimplified.equals(one)) { // rule 7
          return leftSimplified;
      } else if (leftSimplified.equals(rightSimplified)) { // rule 22
          return one;
      } else if (leftSimplified instanceof Neg) { // rule 33
          return new Neg(new Quotient(((Neg) leftSimplified).expr, 
                                      rightSimplified).simplify());
      } else if (rightSimplified instanceof Neg) { // rule 34
          return new Neg(new Quotient(leftSimplified,
                                      ((Neg) rightSimplified).expr).simplify());
      } else if (leftSimplified instanceof Quotient) { // rule 35
          return new Quotient(((Quotient) leftSimplified).getLeft().simplify(),
                              new Product(((Quotient) leftSimplified).getRight(),
                                          rightSimplified)).simplify();
      } else if (rightSimplified instanceof Quotient) { // rule 36
          return new Quotient(new Product(leftSimplified,
                                          ((Quotient) rightSimplified).getRight()).simplify(),
                              ((Quotient) rightSimplified).getLeft()).simplify();
      } else {
          return new Quotient(leftSimplified, rightSimplified); //this;
      }
   }
   
   public IExpression derivative() {
       return new Quotient(new Difference(new Product(this.getLeft().derivative(), 
                                          this.getRight()),
                                          new Product(this.getRight().derivative(),
                                          this.getLeft())),
                           new Exponentiation(this.getRight(), new Number(2.0)));                                   
   }
}