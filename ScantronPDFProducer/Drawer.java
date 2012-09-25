import java.lang.*;
import java.io.*;
import java.util.List;
import java.util.*;

import com.pdfjet.*;

public class Drawer
{
 
    public Drawer()
    {
    }
    
    public static void test()throws Exception{
        //TO DO!
        //test, make sure it works
        //add column and page overflow
        FileOutputStream fos = new FileOutputStream("Steve.pdf");
        PDF pdf = new PDF(fos);
        
        Font f2 = new Font(pdf, "Helvetica");
        f2.setSize(10);
        
        Page page = new Page(pdf, Letter.PORTRAIT);
        
        TextLine text = new TextLine(f2, "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        text.setPosition(50,50);
        text.setColor(RGB.GREY);
        
        
        text.drawOn(page);
        
        pdf.flush();
        fos.close();
        
    }
    
    public static void runQuest(LinkedList<String> questions, int numCols)throws Exception{
        FileOutputStream fos = new FileOutputStream("Steve.pdf");
        PDF pdf = new PDF(fos);
        
        Font f2 = new Font(pdf, "Helvetica");
        f2.setSize(10);
       
        
        Page page = new Page(pdf, Letter.PORTRAIT);
        
        Column col = new Column(numCols);
        
        TextLine texty = new TextLine(f2, col.create(col.arrayToString(questions)));
        texty.setPosition(50,50);
        texty.drawOn(page);
        
        pdf.flush();
        fos.close();
    }

    public static void run(int questions) throws Exception
    {
        //magic numeber area!!
        int letboxY = 7;
        int letboxX = 15;
        int letOffset = 165;
        int questNum = 1;
        double letterPositionX = 19.0;
        double boxPositionX = 19.0;
        double boxPositionY = 189;
        double letterPositionY = boxPositionY + 5;
        double magNumCon = 40;
        //end magic number area!!!
        
        FileOutputStream fos = new FileOutputStream("Bob.pdf");
        PDF pdf = new PDF(fos);
        //font for all names and the numbering of multiple choice
        Font f1 = new Font(pdf, "Helvetica");
        f1.setSize(8);
        //font for numbers in Student ID Grid
        Font f2 = new Font(pdf, "Helvetica");
        f2.setSize(10);
        //font for letters in multiple choice
        Font f3 = new Font(pdf, "Helvetica");
        f3.setSize(6);

        //Page page = new Page(pdf, Letter.PORTRAIT);
        int numCols;
        if (questions % 25.0 == 0) {
            numCols = questions / 25;
        } else {
            numCols = questions / 25 + 1;
        }
        
        int numPages;
        if (numCols % 4.0 == 0) {
            numPages = numCols/4;
        } else {
            numPages = numCols / 4 + 1;
        }
        int quest;  
        
        // allows the production of new pages once it exceeds the limit of
        //multiple choice answer spaces per page
        List<Page> pages = new ArrayList();
        for (int k = 0 ; k < numPages ; k++){
            pages.add(new Page(pdf, Letter.PORTRAIT));
        }
        
        for(Page page : pages){
            int x = 1;
            int y = 0;
            //int z = 0;
            //int w = 0;
            
            Point magicCircle = new Point(magNumCon, magNumCon);
            magicCircle.setShape(Point.CIRCLE);
            magicCircle.setFillShape(true);
            magicCircle.setColor(RGB.BLACK);
            magicCircle.setRadius(magNumCon/4);
            magicCircle.drawOn(page);
            
            Point magicSquare = new Point(612.0 - magNumCon, magNumCon);
            magicSquare.setShape(Point.BOX);
            magicSquare.setFillShape(true);
            magicSquare.setColor(RGB.BLACK);
            magicSquare.setRadius(magNumCon/4);
            magicSquare.drawOn(page);
            
            List<Box> boxes = new ArrayList();
            for(int theCount = 0 ; theCount < 5 ; theCount++){
                boxes.add(new Box());
                boxes.get(theCount).setPosition(100, 100);
                boxes.get(theCount).setSize(11.25,5.25);
                boxes.get(theCount).setColor(RGB.GREY);
            }
            // produces the letter "A"(s)
            TextLine letA = new TextLine(f3, "A");
            letA.setPosition(104.5,110);
            letA.setColor(RGB.GREY);
        

            // produces the letter "B"(s)
            TextLine letB = new TextLine(f3, "B");
            letB.setPosition(127,110);
            letB.setColor(RGB.GREY);
                                                                                                                                                                                                                                                                                                                            
            
            // produces the letter "C"(s)
            TextLine letC = new TextLine(f3, "C");
            letC.setPosition(149.5,110);
            letC.setColor(RGB.GREY);
            
            
            // produces the letter "D"(s)
            TextLine letD = new TextLine(f3, "D");
            letD.setPosition(172,110);
            letD.setColor(RGB.GREY);
            
            // produces the letter "E"(s)
            TextLine letE = new TextLine(f3, "E");
            letE.setPosition(194.5,110);
            letE.setColor(RGB.GREY);
            
            // produces the words "Student ID Number" on top of the Student ID Grid
            TextLine idNum = new TextLine(f1, "Student ID Number");
            idNum.setPosition(100, 36);
            idNum.drawOn(page);
            
            // produces the words "Student ID Number" on top of the Student ID Grid
            TextLine idForm = new TextLine(f1, "Form");
            idForm.setPosition(265.5, 36);
            idForm.drawOn(page);
            
            // produces the word "Name" on top of the Name box
            TextLine name = new TextLine(f1, "Name");
            name.setPosition(395,36);
            
            // produces the word "Name" on top of the Name box
            TextLine testID = new TextLine(f1, "Test ID");
            testID.setPosition(220,36);
            testID.drawOn(page);
            
            //This box is the place for the Name of the Student
            Box bame = new Box();
            bame.setPosition(290, 38);
            bame.setSize(250, 45);
            
            bame.drawOn(page);
            name.drawOn(page);
            
            //Test ID number grid
            Box formIDRow = new Box();
            formIDRow.setSize(13,13);
            formIDRow.setColor(RGB.GREY);
            
            // IS THE FORM ID
            for (int j = 0; j <= 10; j++){
                // sets the position of the columns for the Form ID
                formIDRow.setPosition(255.5 + 13, 38 + 13 *j);                    
                //These are the numbers in the Student ID Grid
                if( j < 10) {
                    TextLine numThree = new TextLine(f2, new Integer(j).toString());
                    numThree.setPosition(259 + 13, 61 + 13*j);
                    numThree.setColor(RGB.GREY);
                    numThree.drawOn(page);
                }
                formIDRow.drawOn(page);
            }
            
        
            
           
            
            //Test ID grid
            Box testIDRow = new Box();
            testIDRow.setSize(13,13);
            testIDRow.setColor(RGB.GREY);
            
            //THIS IS THE TEST ID
            for (int j = 0; j <= 10; j++){
                // sets the position of the columns for the Test ID
                for (int k = 1; k <= 4; k++){
                    testIDRow.setPosition(195 + 13 * k, 38 + 13 *j);                    
                    //These are the numbers in the TEST ID Grid
                    if( j < 10) {
                        TextLine numTwo = new TextLine(f2, new Integer(j).toString());
                        numTwo.setPosition(198.5 + 13 * k, 61 + 13*j);
                        numTwo.setColor(RGB.GREY);
                        numTwo.drawOn(page);
                    }
                    testIDRow.drawOn(page);
                }
            }
            
            
            //These rows make the Student ID Grid
            Box row = new Box();
            row.setSize(13,13);
            row.setColor(RGB.GREY);
            
            
            // produces the Student ID Grid at the top of each page
            for (int j = 0; j <= 10; j++){
                // sets the position of the columns for the Student ID
                for (int k = 1; k <= 10; k++){
                    row.setPosition(57 + 13 * k, 38 + 13 *j);                    
                    //These are the numbers in the Student ID Grid
                    if(j < 10) {
                        TextLine numOne = new TextLine(f2, new Integer(j).toString());
                        numOne.setPosition(60.5 + 13 * k, 61 + 13*j);
                        numOne.setColor(RGB.GREY);
                        numOne.drawOn(page);
                    }
                    row.drawOn(page);    
                }
                //These numbers are for the Student ID Grid
                //y++;
            }
            
            // allows only four columns of multiple choice per page
            int numColsTemp = 4;
            if(numCols > 4){
                numColsTemp = 4;
                numCols = numCols - 4;
            }else{
                numColsTemp = numCols;
                numCols = 0;
            }
            
            int letYDis = 22;
            int letXDis = 125;
            int letXOff = 100;
            
            List<TextLine> textLines = new ArrayList();
            textLines.add(letA);
            textLines.add(letB);
            textLines.add(letC);
            textLines.add(letD);
            textLines.add(letE);
            
            // allows only 25 multiple choice per column
            for(int j = 0; j < numColsTemp ; j++){
                if (questions > 25){
                    quest = 25;
                    questions = questions - 25;
                }else{
                    quest = questions;
                    questions = 0;
                }
                for(int i = 0; i < quest; i++){
                    
                    //Position of boxes
                    for(int h = 0; h < 5 ; h++){//100
                        boxes.get(h).setPosition(75 + h*boxPositionX + letXDis*j, boxPositionY + letYDis*i);
                    }
                   
                    //Poistion of Letters
                    for(int h = 0; h < 5 ; h++){   //104.5
                        textLines.get(h).setPosition(74 + 4.5 + h*letterPositionX + letXDis*j, letterPositionY + letYDis*i);
                    }

                    //Draw boxes on page
                    for(Box boxTemp : boxes){
                        boxTemp.drawOn(page);
                    }
                    
                    //Draws Letters on Page
                    for(TextLine textTemp : textLines){
                        textTemp.drawOn(page);
                    }
                    
                    // Produces Numbers next to the multiple choice
                    TextLine num = new TextLine(f1, new Integer(questNum).toString() + ".");
                    num.setPosition(58 + letXDis*j, 196 + letYDis * i);
                    num.drawOn(page);
                    questNum++;
                }
            }
        }
        pdf.flush();
        fos.close();
    }
}
