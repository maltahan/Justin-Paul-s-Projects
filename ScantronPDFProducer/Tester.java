import java.lang.*;
import java.io.*;
import java.util.List;
import java.util.*;

public class Tester
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Tester
     */
    public Tester()
    {
        // initialise instance variables
        x = 0;
    }

    public void test()throws Exception
    {
         LinkedList<String> questions = new LinkedList<String>();
         
         
             questions.add("What is 2 + 2?");
             questions.add("What are the five states that start with A?");
             questions.add("What are the names ofthe people in this group who have acutally done stuff and are jewish?");
             questions.add("Name every person in the Special Topics Computer Class beginning with the left side, then middle, then right side of the room and for each name them alphabeticallly. Dont forget Mr. Obryan!?");
             questions.add("How many pages are there in the Biology book, AP U.S. History Book, the Physics book, the Math book, and the English book put togetther if all of there pages were ripped out?");
             questions.add("Why is the sky blue, the grass green, the fire red, the desktop pinkish orangish thing, the floor tanish speckled spots, the ceiling also tani, the backpack blue, the name matthew, the femles called females and vice versa with the males, the sun yellowm, the clouds white, the trees brown and black and yellow and orange and red, the blue jay blue, and this question so long?");
        

         
         Drawer d = new Drawer();
         d.runQuest(questions, 2);
    }
}
