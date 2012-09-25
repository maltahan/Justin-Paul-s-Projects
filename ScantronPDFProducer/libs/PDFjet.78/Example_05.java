import java.lang.*;
import java.io.*;

import com.pdfjet.*;


/**
 *  Example_05.java
 *
 */
public class Example_05 {

    public Example_05() throws Exception {

        FileOutputStream fos = new FileOutputStream("Example_05.pdf");

        PDF pdf = new PDF(fos);

        Font f1 = new Font(pdf, "Helvetica");

        Page page = new Page(pdf, Letter.PORTRAIT);

        TextLine text = new TextLine(f1);
        text.setPosition(300.0, 300.0);
        for (int i = 0; i < 360; i += 15) {
            text.setTextDirection(i);
            text.setUnderline(true);
            // text.setStrikeLine(true);
            text.setText("             Hello, World -- " + i + " degrees.");
            text.drawOn(page);
        }

        text = new TextLine(f1, "WAVE AWAY");
        text.setPosition(70.0, 50.0);
        text.drawOn(page);

        f1.setKernPairs(true);
        text.setPosition(70.0, 70.0);
        text.drawOn(page);

        f1.setKernPairs(false);
        text.setPosition(70.0, 90.0);
        text.drawOn(page);

        f1.setSize(8);
        text = new TextLine(f1, "-- font.setKernPairs(false);");
        text.setPosition(150.0, 50.0);
        text.drawOn(page);
        text.setPosition(150.0, 90.0);
        text.drawOn(page);
        text = new TextLine(f1, "-- font.setKernPairs(true);");
        text.setPosition(150.0, 70.0);
        text.drawOn(page);

        Point point = new Point(300.0, 300.0);
        point.setShape(Point.CIRCLE);
        point.setFillShape(true);
        point.setColor(RGB.BLUE);
        point.setRadius(37.0);
        point.drawOn(page);
        point.setRadius(25.0);
        point.setColor(RGB.WHITE);
        point.drawOn(page);

        pdf.flush();
        fos.close();
    }


    public static void main(String[] args) {
        try {
            new Example_05();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}   // End of Example_05.java
