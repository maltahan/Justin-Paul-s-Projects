public class Neg extends Unary{
   
   public Neg(IExpression expr) {
         super(expr);
   }
   
   public double eval(double x) {
         return -1 * super.getExpr().eval(x);
   }
   
   public String getName() {
       return "~";
   }
   
   public boolean equals(Neg that) {
      return this.expr.equals(that.expr);
   }

   public boolean equals(Object that) {
      if (that instanceof Neg) {
         return this.equals((Neg) that);
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
          return zero;
      } else if (this.getExpr() instanceof Neg) { // rule 24
          return ((Neg) this.getExpr()).getExpr().simplify();
      } else {
          return new Neg(exprSimplified);
      }
   }
   
   public IExpression derivative() {
       return new Neg(this.getExpr().derivative());
   }
}