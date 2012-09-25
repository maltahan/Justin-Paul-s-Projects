import java.lang.*;
import java.io.*;

import com.pdfjet.*;


/**
 *  Example_03.java
 *
 */
public class Example_03 {

    public Example_03() throws Exception {

        FileOutputStream fos = new FileOutputStream("Example_03.pdf");

        PDF pdf = new PDF(fos);

        Font f1 = new Font(pdf, "Helvetica");

        String fileName = "images/eu-map.png";
        BufferedInputStream bis1 =
                new BufferedInputStream(
                        getClass().getResourceAsStream(fileName));
        Image image1 = new Image(pdf, bis1, ImageType.PNG);

        fileName = "images/fruit.jpg"; 
        BufferedInputStream bis2 =
                new BufferedInputStream(
                        getClass().getResourceAsStream(fileName));
        Image image2 = new Image(pdf, bis2, ImageType.JPEG);

        fileName = "images/mt-map.bmp"; 
        BufferedInputStream bis3 =
                new BufferedInputStream(
                        getClass().getResourceAsStream(fileName));
        Image image3 = new Image(pdf, bis3, ImageType.BMP);

        
        Page page = new Page(pdf, A4.PORTRAIT);

        TextLine text = new TextLine(f1,
                "The map below is an embedded PNG image");
        text.setPosition(90, 30);
        text.drawOn(page);

        image1.setPosition(90, 40);
        image1.drawOn(page);

        text.setText(
                "JPG image file embedded once and drawn 3 times");
        text.setPosition(90, 550);
        text.drawOn(page);

        image2.setPosition(90, 560);
        image2.scaleBy(0.5);
        image2.drawOn(page);

        image2.setPosition(260, 560);
        image2.scaleBy(0.5);
        image2.drawOn(page);

        image2.setPosition(350, 560);
        image2.scaleBy(0.5);
        image2.drawOn(page);

        text.setText(
                "The map on the right is an embedded BMP image");
        text.setUnderline(true);
        text.setStrikeLine(true);
        text.setTextDirection(15);
        text.setPosition(90, 800);
        text.drawOn(page);

        image3.setPosition(390, 630);
        image3.scaleBy(0.5);
        image3.drawOn(page);

        pdf.flush();
        fos.close();
    }


    public static void main(String[] args) {
        try {
            new Example_03();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}   // End of Example_03.java
