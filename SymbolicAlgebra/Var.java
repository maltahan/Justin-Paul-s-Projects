public class Var implements IExpression {
    
   public Var() {
   }
    
   public boolean containsVar() {
       return true;
   }
    
   public double eval(double x) {
       return x;
   }
    
   public String toString() {
       return "x";
   }
   
   public String toSmartString() {
       return "x";
   }
   
   public int getPrecedence() {
       return 4;
   }
   
   public boolean equals(Object that) {
      if (that instanceof Var) {
         return true;
      } else {
         return false;
      }
   }
   
   public IExpression simplify() {
       return this;
   }
   
   public IExpression derivative() {
       return new Number(1.0);
   }
}