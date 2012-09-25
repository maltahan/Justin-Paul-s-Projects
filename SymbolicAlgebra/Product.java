public class Product extends Binary {
   
   public Product(IExpression left, IExpression right) {
       super(left, right, "*");
   }
   
   public double eval(double x) {
      return super.getLeft().eval(x) * super.getRight().eval(x);
   }
   
   public boolean equals(Product that) {
      return this.getLeft().equals(that.getLeft())
             &&
             this.getRight().equals(that.getRight());
   }

   public boolean equals(Object that) {
      if (that instanceof Product) {
         return this.equals((Product) that);
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
      } else if (leftSimplified.equals(one)) { // rule 8
          return rightSimplified;
      } else if (rightSimplified.equals(one)) { // rule 9
          return leftSimplified;
      } else if (leftSimplified.equals(zero)) { // rule 10
          return zero;
      } else if (rightSimplified.equals(zero)) { // rule 11
          return zero;
      } else if (leftSimplified.equals(rightSimplified)) { // rule 20
          return new Exponentiation(leftSimplified, new Number(2.0)).simplify();
      } else if (leftSimplified instanceof Neg) { // rule 29
          return new Neg(new Product(((Neg) leftSimplified).expr,
                                     rightSimplified).simplify());
      } else if (rightSimplified instanceof Neg) { // rule 30
          return new Neg(new Product(leftSimplified,
                         ((Neg) rightSimplified).expr).simplify());
      } else if (leftSimplified instanceof Quotient) { // rule 31
          return new Quotient(new Product(((Quotient) leftSimplified).getLeft().simplify(), 
                                          rightSimplified).simplify(),
                              ((Quotient) leftSimplified).getRight().simplify());
      } else if (rightSimplified instanceof Quotient) { // rule 32
          return new Quotient(new Product(leftSimplified, 
                                          ((Quotient) rightSimplified).getLeft().simplify()).simplify(),
                              ((Quotient) rightSimplified).getRight().simplify());
      } else {
          return new Product(leftSimplified, rightSimplified);
      }
   }
   
   public IExpression derivative() {
       return new Sum(new Product(this.getLeft().derivative(), this.getRight()),
                      new Product(this.getRight().derivative(),this.getLeft()));
   }
}