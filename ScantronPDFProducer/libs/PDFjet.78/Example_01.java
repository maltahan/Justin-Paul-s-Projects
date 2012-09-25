import java.lang.*;
import java.io.*;
import java.util.*;
import java.text.*;

import com.pdfjet.*;


/**
 *  Example_01.java
 *  We will draw the American flag using Box, Line and Point objects.
 */
public class Example_01 {

    public Example_01() throws Exception {

        FileOutputStream fos = new FileOutputStream("Example_01.pdf");

        PDF pdf = new PDF(fos);

        Page page = new Page(pdf, Letter.PORTRAIT);

        Box flag = new Box();
        flag.setPosition(100.0, 100.0);
        flag.setSize(190.0, 100.0);
        flag.setColor(RGB.WHITE);
        flag.drawOn(page);

        double sw = 7.69;   // stripe width
        Line stripe = new Line(0.0, sw/2, 190.0, sw/2);
        stripe.setWidth(sw);
        stripe.setColor(RGB.OLD_GLORY_RED);
        for (int row = 0; row < 7; row++) {
            stripe.placeIn(flag, 0.0, row * 2 * sw);
            stripe.drawOn(page);
        }

        Box union = new Box();
        union.setSize(76.0, 53.85);
        union.setColor(RGB.OLD_GLORY_BLUE);
        union.setFillShape(true);
        union.placeIn(flag, 0.0, 0.0);
        union.drawOn(page);

        double h_si = 12.6; // horizontal star interval
        double v_si = 10.8; // vertical star interval
        Point star = new Point(h_si/2, v_si/2);
        star.setShape(Point.STAR);
        star.setRadius(3.0);
        star.setColor(RGB.WHITE);
        star.setFillShape(true);

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                star.placeIn(union, row * h_si, col * v_si);
                star.drawOn(page);
            }
        }
        star.setPosition(h_si, v_si);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                star.placeIn(union, row * h_si, col * v_si);
                star.drawOn(page);
            }
        }

        pdf.flush();
        fos.close();

    }


    public static void main(String[] args) {
        try {
            new Example_01();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}   // End of Example_01.java
