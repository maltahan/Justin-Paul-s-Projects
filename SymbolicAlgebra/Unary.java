public abstract class Unary implements IExpression {
    IExpression expr;
    
    Unary(IExpression expr) {
        this.expr = expr;
    }
    
    public IExpression getExpr() {
        return this.expr;
    }
    
    public boolean containsVar() {
        return this.expr.containsVar();
   }
   
   public int getPrecedence() {
       return 2;
   }
   
   public abstract String getName();
   
   public String toString() {
       return this.getName() + "(" + this.expr + ")";
   }
   
   public String toSmartString() {
       if (this.expr.getPrecedence() < this.getPrecedence()) {
           return this.getName() + "(" + this.expr.toSmartString() + ")";
       } else if (this instanceof Neg) {
           return this.getName() + this.expr.toSmartString();
       } else {
           return this.getName() + " " + this.expr.toSmartString();
       }
   }
   
   public abstract boolean equals(Object that);
   
   public IExpression simplify() {
      IExpression exprSimplified = this.expr.simplify();
         if (this instanceof Sin) {
             return ((Sin) this).simplify();
         } else if (this instanceof Cos) {
             return ((Cos) this).simplify();
         } else if (this instanceof Ln) {
             return ((Ln) this).simplify();
         } else { // Neg
             return ((Neg) this).simplify();
         }
   }
   
   public IExpression derivative() {
       if (this instanceof Sin) {
           return ((Sin) this).simplify();
       } else if (this instanceof Cos) {
           return ((Cos) this).simplify();
       } else if (this instanceof Ln) {
           return ((Ln) this).simplify();
       } else { // Neg
           return ((Neg) this).simplify();
       }
   }
}