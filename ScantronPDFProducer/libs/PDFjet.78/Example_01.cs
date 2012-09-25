using System;
using System.IO;
using System.Collections;using System.Collections.Generic;
using System.Text;

using PDFjet.NET;


/**
 *  Example_01.cs
 *  We will draw the American flag using Box, Line and Point objects.
 */
public class Example_01 {

    public Example_01() {

        FileStream fos = new FileStream("Example_01.pdf", FileMode.Create);
        BufferedStream bos = new BufferedStream(fos);

        PDF pdf = new PDF(bos);
        pdf.setCompressor(Compressor.ORIGINAL_ZLIB);

        Page page = new Page(pdf, Letter.PORTRAIT);

        Box flag = new Box();
        flag.SetPosition(100.0, 100.0);
        flag.SetSize(190.0, 100.0);
        flag.SetColor(RGB.WHITE);
        flag.DrawOn(page);

        double sw = 7.69;   // stripe width
        Line stripe = new Line(0.0, sw/2, 190.0, sw/2);
        stripe.SetWidth(sw);
        stripe.SetColor(RGB.OLD_GLORY_RED);
        for (int row = 0; row < 7; row++) {
            stripe.PlaceIn(flag, 0.0, row * 2 * sw);
            stripe.DrawOn(page);
        }

        Box union = new Box();
        union.SetSize(76.0, 53.85);
        union.SetColor(RGB.OLD_GLORY_BLUE);
        union.SetFillShape(true);
        union.PlaceIn(flag, 0.0, 0.0);
        union.DrawOn(page);

        double h_si = 12.6; // horizontal star interval
        double v_si = 10.8; // vertical star interval
        Point star = new Point(h_si/2, v_si/2);
        star.SetShape(Point.STAR);
        star.SetRadius(3.0);
        star.SetColor(RGB.WHITE);
        star.SetFillShape(true);

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                star.PlaceIn(union, row * h_si, col * v_si);
                star.DrawOn(page);
            }
        }
        star.SetPosition(h_si, v_si);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                star.PlaceIn(union, row * h_si, col * v_si);
                star.DrawOn(page);
            }
        }

        pdf.Flush();
        bos.Close();
    }


    public static void Main(String[] args) {
        try {
            new Example_01();
        } catch (Exception e) {
            Console.WriteLine(e.StackTrace);
        }
    }

}   // End of Example_01.cs
