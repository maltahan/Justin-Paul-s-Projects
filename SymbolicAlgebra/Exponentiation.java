public class Exponentiation extends Binary {
   
   public Exponentiation(IExpression left, IExpression right) {
      super(left, right, "^");
   }
   
   public double eval(double x) {
      return Math.pow(super.getLeft().eval(x),  super.getRight().eval(x));
   }
   
   public boolean equals(Exponentiation that) {
      return this.getLeft().equals(that.getLeft())
             &&
             this.getRight().equals(that.getRight());
   }

   public boolean equals(Object that) {
      if (that instanceof Exponentiation) {
         return this.equals((Exponentiation) that);
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
      } else if (rightSimplified.equals(zero)) { // rule 12
          return one;
      } else if (leftSimplified.equals(zero)) { // rule 13
          return zero;
      } else if (rightSimplified.equals(one)) { //rule 14
          return leftSimplified;
      } else if (rightSimplified instanceof Neg) { // rule 37
          return new Quotient(one,
                              new Exponentiation(leftSimplified,
                                                 ((Neg) rightSimplified).expr)).simplify();
      } else if (leftSimplified instanceof Exponentiation) { // rule 38
          return new Exponentiation(((Exponentiation) leftSimplified).getLeft(),
                                    new Product(((Exponentiation) leftSimplified).getRight(),
                                                rightSimplified).simplify());
      } else {
          return new Exponentiation(leftSimplified, rightSimplified);
      }
   }
   
   public IExpression derivative() {
       if (this.getRight() instanceof Number) {
           return new Product(new Product(this.getRight(),
                                          new Exponentiation(this.getLeft(),
                                                             new Difference(this.getRight(),
                                                                            new Number(1.0)))),
                              this.getLeft().derivative());
       } else {
           return new Product(new Exponentiation(this.getLeft(), this.getRight()),
                              new Sum(new Product(this.getRight().derivative(),
                                                         new Ln(this.getLeft())),
                                      new Quotient(new Product(this.getLeft().derivative(),
                                                               this.getRight()),
                                                   this.getLeft())));
       }
   }
}