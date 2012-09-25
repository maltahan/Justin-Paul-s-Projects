public class Number implements IExpression {
      private double value;   

   public Number(double value) {
       this.value = value;
   }
   
   public boolean containsVar() {
       return false;
   }
   
   public double eval(double x) {
       return this.value;
   }
   
   public String toString() {
       return this.value + "";
   }
   
   public String toSmartString() {
       if (this.value == (int) this.value) {
           return "" + (int) this.value;
       } else {
            return "" + this.value ;
       }
   }
   
   public int getPrecedence() {
       return 4;
   }
   
   public boolean equals(Number that) {
      return this.value == that.value;
   }

   public boolean equals(Object that) {
      if (that instanceof Number) {
         return this.equals((Number) that);
      } else {
         return false;
      }
   }
   
   public IExpression simplify() {
      if (this.value < 0) { // rule 23
          return new Neg(new Number(Math.abs(this.value)));
      } else {
          return this;
      }
   }
   
   public IExpression derivative() {
       return new Number(0.0);
   }
}