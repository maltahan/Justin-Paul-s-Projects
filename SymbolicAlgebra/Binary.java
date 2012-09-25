public abstract class Binary implements IExpression {
    private IExpression left;
    private IExpression right;
    private String operation;
    
    public Binary(IExpression left, IExpression right, String operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }
    
    public IExpression getLeft() {
        return this.left;
    }
    
    public IExpression getRight() {
        return this.right;
    }
    
    public String getOperation() {
        return this.operation;
    }
    
    public boolean containsVar() {
        return this.left.containsVar() || this.right.containsVar();
    }
    
    public String toString() {
        return "(" + this.left + " " + this.operation + " " + this.right + ")";
    }
    
    public String toSmartString() {
        if ((this.left.getPrecedence() < this.getPrecedence())
            &&
            (this.right.getPrecedence() <= this.getPrecedence())) {
            return "(" + this.left.toSmartString() + ") " + this.operation
                   +
                   " (" + this.right.toSmartString() + ")";
        } else if (this instanceof Exponentiation
                   &&
                   this.left instanceof Exponentiation) {
            return "(" + this.left.toSmartString() + ") " + this.operation
                   +
                   " " + this.right.toSmartString();
        } else if (this instanceof Exponentiation
                   &&
                   this.right instanceof Exponentiation) {
            return this.left.toSmartString() + " " + this.operation
                   +
                   " " + this.right.toSmartString();
        } else if (this.left.getPrecedence() < this.getPrecedence()) {
            return "(" + this.left.toSmartString() + ") " + this.operation
                   + 
                   " " + this.right.toSmartString();
        } else if (this.right.getPrecedence() <= this.getPrecedence()) {
            return this.left.toSmartString() + " " + this.operation
                   + 
                   " (" + this.right.toSmartString() + ")";
        } else {
            return this.left.toSmartString() + " " + this.operation
                   +
                   " " + this.right.toSmartString();
        }
    }
   
   public int getPrecedence() {
       if (this instanceof Sum || this instanceof Difference) {
           return 0;
       } else if (this instanceof Quotient || this instanceof Product) {
           return 1;
       } else { //Exponentiation
           return 3;
       }
   }
   
   public abstract boolean equals(Object that);
   
   public IExpression simplify() {
          if (this instanceof Sum) {
              return ((Sum) this).simplify();
          } else if (this instanceof Difference) {
              return ((Difference) this).simplify();
          } else if (this instanceof Product) {
              return ((Product) this).simplify();
          } else if (this instanceof Quotient) {
              return ((Quotient) this).simplify();
          } else { // Exponentiation
              return ((Exponentiation) this).simplify();
          }
   }
   
   public IExpression derivative() {
      if (this instanceof Sum) {
          return ((Sum) this).derivative();
      } else if (this instanceof Difference) {
          return ((Difference) this).derivative();
      } else if (this instanceof Product) {
          return ((Product) this).derivative();
      } else if (this instanceof Quotient) {
          return ((Quotient) this).derivative();
      } else { // Exponentiation
          return ((Exponentiation) this).derivative();
      }
   }
}