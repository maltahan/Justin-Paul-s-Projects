import java.lang.*;
import java.io.*;

import com.pdfjet.*;


/**
 *  Example_02.java
 *
 *  Draw the Canadian flag using a Path object that contains both lines
 *  and curve segments. Every curve segment must have exactly 3 curve points.
 */
public class Example_02 {

    public Example_02() throws Exception {

        FileOutputStream fos = new FileOutputStream("Example_02.pdf");

        PDF pdf = new PDF(fos);

        Page page = new Page(pdf, Letter.PORTRAIT);

        Box flag = new Box(85, 85, 64, 32);

        com.pdfjet.Path path = new com.pdfjet.Path();
        path.add(new Point(13.0,  0.0));
        path.add(new Point(15.5,  4.5));
        path.add(new Point(18.0,  3.5));

        path.add(new Point(15.5, 13.5, Point.IS_CURVE_POINT));
        path.add(new Point(15.5, 13.5, Point.IS_CURVE_POINT));
        path.add(new Point(20.5,  7.5, Point.IS_CURVE_POINT));

        path.add(new Point(21.0,  9.5));
        path.add(new Point(25.0,  9.0));
        path.add(new Point(24.0, 13.0));
        path.add(new Point(25.5, 14.0));
        path.add(new Point(19.0, 19.0));
        path.add(new Point(20.0, 21.5));
        path.add(new Point(13.5, 20.5));
        path.add(new Point(13.5, 27.0));
        path.add(new Point(12.5, 27.0));
        path.add(new Point(12.5, 20.5));
        path.add(new Point( 6.0, 21.5));
        path.add(new Point( 7.0, 19.0));
        path.add(new Point( 0.5, 14.0));
        path.add(new Point( 2.0, 13.0));
        path.add(new Point( 1.0,  9.0));
        path.add(new Point( 5.0,  9.5));
        path.add(new Point( 5.5,  7.5));

        path.add(new Point(10.5, 13.5, Point.IS_CURVE_POINT));
        path.add(new Point(10.5, 13.5, Point.IS_CURVE_POINT));
        path.add(new Point( 8.0,  3.5, Point.IS_CURVE_POINT));

        path.add(new Point(10.5,  4.5));
        path.setClosePath(true);
        path.setColor(RGB.RED);
        path.setFillShape(true);
        path.placeIn(flag, 19.0, 3.0);
        path.drawOn(page);

        Box box = new Box();
        box.setSize(16, 32);
        box.setColor(RGB.RED);
        box.setFillShape(true);
        box.placeIn(flag, 0.0, 0.0);
        box.drawOn(page);
        box.placeIn(flag, 48.0, 0.0);
        box.drawOn(page);

        path.scaleBy(15.0);
        path.setFillShape(false);
        path.drawOn(page);

        pdf.flush();
        fos.close();
    }


    public static void main(String[] args) {
        try {
            new Example_02();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}   // End of Example_02.java
