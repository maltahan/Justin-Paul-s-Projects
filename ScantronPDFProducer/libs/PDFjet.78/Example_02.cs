using System;
using System.IO;

using PDFjet.NET;


/**
 *  Example_02.cs
 *
 *  Draw the Canadian flag using a Path object that contains both lines
 *  and curve segments. Every curve segment must have exactly 3 curve points.
 */
public class Example_02 {

    public Example_02() {

        FileStream fos = new FileStream("Example_02.pdf", FileMode.Create);
        BufferedStream bos = new BufferedStream(fos);

        PDF pdf = new PDF(bos);
        pdf.setCompressor(Compressor.ORIGINAL_ZLIB);

        Page page = new Page(pdf, Letter.PORTRAIT);

        Box flag = new Box(85, 85, 64, 32);

        PDFjet.NET.Path path = new PDFjet.NET.Path();
        path.Add(new Point(13.0,  0.0));
        path.Add(new Point(15.5,  4.5));
        path.Add(new Point(18.0,  3.5));

        path.Add(new Point(15.5, 13.5, Point.IS_CURVE_POINT));
        path.Add(new Point(15.5, 13.5, Point.IS_CURVE_POINT));
        path.Add(new Point(20.5,  7.5, Point.IS_CURVE_POINT));

        path.Add(new Point(21.0,  9.5));
        path.Add(new Point(25.0,  9.0));
        path.Add(new Point(24.0, 13.0));
        path.Add(new Point(25.5, 14.0));
        path.Add(new Point(19.0, 19.0));
        path.Add(new Point(20.0, 21.5));
        path.Add(new Point(13.5, 20.5));
        path.Add(new Point(13.5, 27.0));
        path.Add(new Point(12.5, 27.0));
        path.Add(new Point(12.5, 20.5));
        path.Add(new Point( 6.0, 21.5));
        path.Add(new Point( 7.0, 19.0));
        path.Add(new Point( 0.5, 14.0));
        path.Add(new Point( 2.0, 13.0));
        path.Add(new Point( 1.0,  9.0));
        path.Add(new Point( 5.0,  9.5));
        path.Add(new Point( 5.5,  7.5));

        path.Add(new Point(10.5, 13.5, Point.IS_CURVE_POINT));
        path.Add(new Point(10.5, 13.5, Point.IS_CURVE_POINT));
        path.Add(new Point( 8.0,  3.5, Point.IS_CURVE_POINT));

        path.Add(new Point(10.5,  4.5));
        path.setClosePath(true);
        path.SetColor(RGB.RED);
        path.SetFillShape(true);
        path.PlaceIn(flag, 19.0, 3.0);
        path.DrawOn(page);

        Box box = new Box();
        box.SetSize(16, 32);
        box.SetColor(RGB.RED);
        box.SetFillShape(true);
        box.PlaceIn(flag, 0.0, 0.0);
        box.DrawOn(page);
        box.PlaceIn(flag, 48.0, 0.0);
        box.DrawOn(page);

        path.ScaleBy(15.0);
        path.SetFillShape(false);
        path.DrawOn(page);

        pdf.Flush();
        bos.Close();
    }


    public static void Main(String[] args) {
        try {
            new Example_02();
        } catch (Exception e) {
            Console.WriteLine(e.StackTrace);
        }
    }

}   // End of Example_02.cs
