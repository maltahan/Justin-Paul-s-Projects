public class Sin extends Unary {
   
   public Sin(IExpression expr) {
       super(expr);
   }
  
   //eval is in radians
   public double eval(double x) {
        return Math.sin(super.getExpr().eval(x));
   }
   
   public String getName() {
       return "sin";
   }
   
   public boolean equals(Sin that) {
      return this.expr.equals(that.expr);
   }

   public boolean equals(Object that) {
      if (that instanceof Sin) {
         return this.equals((Sin) that);
      } else {
         return false;
      }
   }
   
   public IExpression simplify() {
      IExpression exprSimplified = this.expr.simplify();
      IExpression zero = new Number(0.0);
      if (!exprSimplified.containsVar()) { // rule 1
          return new Number(this.eval(0.0)).simplify();
      } else if (exprSimplified.equals(zero)) { // rule 16
          return zero;
      } else {
          return new Sin(exprSimplified);
      }
   }
   
   public IExpression derivative() {
       return new Product(new Cos(this.getExpr()),
                          this.getExpr().derivative());
   }
}