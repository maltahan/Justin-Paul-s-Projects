import tester.*;

public class Examples {
   private ShapeMaker sm = new ShapeMaker();
       
   public void testMakeRectangle1(Tester t) {
      t.checkExpect(sm.makeRectangle(4, 3),
            "* * * *\n"
          + "*     *\n"
          + "* * * *");
   }
    
   public void testMakeSquare1(Tester t) {
      t.checkExpect(sm.makeSquare(5),
            "* * * * *\n"
          + "*       *\n"
          + "*       *\n"
          + "*       *\n"
          + "* * * * *");
   }
   
   public void testMakeHexagon1(Tester t) {
      t.checkExpect(sm.makeHexagon(4),
            "      * * * *\n"
          + "    *         *\n"
          + "  *             *\n"
          + "*                 *\n"
          + "  *             *\n"
          + "    *         *\n"
          + "      * * * *");
   }
   
   public void testMakeOctagon1(Tester t) {
      t.checkExpect(sm.makeOctagon(3),
            "    * * *\n"
          + "  *       *\n"
          + "*           *\n"
          + "*           *\n"
          + "*           *\n"
          + "  *       *\n"
          + "    * * *");
   }
   
   public void testMakeX1(Tester t) {
      t.checkExpect(sm.makeX(6),
            "*         *\n"
          + "  *     *\n"
          + "    * *\n"
          + "    * *\n"
          + "  *     *\n"
          + "*         *");
   }
   
   public void testMakeX2(Tester t) {
      t.checkExpect(sm.makeX(3),
            "*   *\n"
          + "  *\n"
          + "*   *");
   }
   
   public void testLeftTriangle1(Tester t) {
      t.checkExpect(sm.makeLeftTriangle(6),
            "*\n"
          + "* *\n"
          + "*   *\n"
          + "*     *\n"
          + "*       *\n"
          + "* * * * * *");
   }
   
   public void testRightTriangle1(Tester t) {
       t.checkExpect(sm.makeRightTriangle(4),
             "      *\n"
           + "    * *\n"
           + "  *   *\n"
           + "* * * *");
   }
   
   public static void main(String[] args) {
       Tester.run(new Examples());
    }
}