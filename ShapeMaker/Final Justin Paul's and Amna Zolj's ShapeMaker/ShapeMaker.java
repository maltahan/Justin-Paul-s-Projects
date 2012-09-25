public class ShapeMaker {
    private String solidLine(int length) {
        String retern = "";
        for (int i = length; i > 0; i--) {
            if (i == 1) {
                retern = retern + "*";
            } else {
                retern = retern + "* ";
            }
        }
        return retern;      
    }
    
    private String openLine(int length) {
        String retern = "*";
        for (int i = length; i > 1; i--) {
            if (i == 2) {
                retern = retern + " *";
            } else {
                retern = retern + "  ";
            }
        }
        return retern;
    }
    
    private String space(int length) {
        String retern = "";
        for (int i = length; i > 0; i--) {
            retern = retern + "  ";
        }
        return retern;
    }
        

    public String makeRectangle(int width, int height) {
        String rect = solidLine(width) + "\n";
        for (int i = 0; i < height - 2; i++) {
           rect += openLine(width) + "\n";
        }
        rect += solidLine(width);
        return rect;
     }
   
     public String makeSquare(int side) {
        return makeRectangle(side, side);
     }
     
     public String makeLeftTriangle(int height) {
         String tri = "";
         for (int i = 1; i <= height; i++) {
             if (i == height) {
                 tri = tri + solidLine(i);
             } else {
                 tri = tri + openLine(i) + "\n";
             }
         }
         return tri;
     }
     
     public String makeRightTriangle(int height) {
         String tri = "";
         for (int i = 1; i <= height; i++) {
             if (i == height) {
                 tri = tri + solidLine(i);
             } else {
                 tri = tri + space(height - i) + openLine(i) + "\n";
             }
         }
         return tri;
     }
     
//      public String makeEqualateralTriangle(int height) {// Not Needed
//          String tri = "";
//          for (int i = 1; i <= height; i++) {
//              if (i == height) {
//                  tri = tri + solidLine(i);
//              } else {
//                  tri = tri + space(height - i) + solidLine(i) + "\n";
//              }
//          }
//          return tri;
//      }
     
     public String makeHexagon(int side) {
         String hex = "";
         hex = hex + space(side - 1) + solidLine(side) + "\n";
         for (int i = 1; i < side; i++) {
             hex = hex + space(side - i - 1) + openLine(side + 2 * i) + "\n";
         }
         for (int i = side - 2; i > 0; i--) {
             hex = hex + space(side - i - 1) + openLine(2 * i + side) + "\n";
         }
         hex = hex + space(side - 1) + solidLine(side);
         return hex;
     }
     
     public String makeOctagon(int side) {
         String oct = "";
         oct = oct + space(side - 1) + solidLine(side) + "\n";
         for (int i = 1; i < side; i++) {
             oct = oct + space(side - i - 1) + openLine(side + 2 * i) + "\n";
         }
         for (int i = 1; i < side; i++) {
             oct = oct + openLine(side + 2 * (side - 1)) + "\n";
         }
         for (int i = side - 2; i > 0; i--) {
             oct = oct + space(side - i - 1) + openLine(2 * i + side) + "\n";
         }
         oct = oct + space(side - 1) + solidLine(side);
         return oct;
     }
     
     public String makeX(int side) {
         String x = "";
//         x = x + openLine(2 * (side - 2) + 1) + "\n";
         if (side % 2 == 0) {
             for (int i = side - 3; i > 0; i--) {
                 x = x + space(side - i - 3) + openLine(2 * i) + "\n";
             }
             for (int i = 1; i < side - 2; i++) {
                 if (i == side - 3) {
                     x = x + space(side - i - 3) + openLine(2 * i);
                 } else {
                     x = x + space(side - i - 3) + openLine(2 * i) + "\n";
                 }
             }
         } else {
              for (int i = side - 2; i >= 0; i--) {
                 x = x + space(side - i - 2) + openLine(2 * i + 1) + "\n";
              }
              for (int i = 1; i <= side - 2; i++) {
                 if (i == side - 2) {
                     x = x + space(side - i - 2) + openLine(2 * i + 1);
                 } else {
                     x = x + space(side - i - 2) + openLine(2 * i + 1) + "\n";
                 }
              }
         }
         
         return x;
     }
     
     public String makeFall(int height) {
         String r = "";
         for (int i = 1; i <= height; i++) {
             for (int k = height; k > 0; k--) {
                 r = r + space(k) + solidLine(i) + "\n";
             }
         }
         return r;
     }
     
     public static void main(String[] args) {
         ShapeMaker sm = new ShapeMaker();
         for (int i = 2; i <= 8; i++) {
             // put what you're trying to test here
             System.out.println(sm.makeRectangle(i, i + 1));
         }
     }
}
