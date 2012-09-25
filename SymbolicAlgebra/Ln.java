public class Ln extends Unary{
   
   public Ln(IExpression expr) {
        super(expr);
   }
   
   //eval is in radians
   public double eval(double x) {
       return Math.log(this.expr.eval(x));
   }
   
   public String getName() {
       return "ln";
   }
   
   public boolean equals(Ln that) {
      return this.expr.equals(that.expr);
   }

   public boolean equals(Object that) {
      if (that instanceof Ln) {
         return this.equals((Ln) that);
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
      } else if (exprSimplified.equals(one)) { // rule 16
          return zero;
      } else {
          return new Ln(exprSimplified);
      }
   }
   
   public IExpression derivative() {
       return new Quotient(this.getExpr().derivative(),
                           this.getExpr());
   }
}