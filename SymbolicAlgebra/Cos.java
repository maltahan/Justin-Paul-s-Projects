public class Cos extends Unary{
   
   public Cos(IExpression expr) {
      super(expr);
   }
   
   //eval is in radians
   public double eval(double x) {
        return Math.cos(this.expr.eval(x));
   }
   
   public String getName() {
       return "cos";
   }
   
   public boolean equals(Cos that) {
      return this.expr.equals(that.expr);
   }

   public boolean equals(Object that) {
      if (that instanceof Cos) {
         return this.equals((Cos) that);
      } else {
         return false;
      }
   }
   
   public IExpression simplify() {
      IExpression exprSimplified = this.expr.simplify();
      IExpression zero = new Number(0.0);
      IExpression one = new Number(1.0);
      if (!exprSimplified.containsVar()) { // rule 1
          return new Number(this.eval(0.0)).simplify();
      } else if (exprSimplified.equals(zero)) { // rule 16
          return one;
      } else {
          return new Cos(exprSimplified);
      }
   }
   
   public IExpression derivative() {
       return new Product(new Neg(new Sin(this.getExpr())),
                          this.getExpr().derivative());
   }
}