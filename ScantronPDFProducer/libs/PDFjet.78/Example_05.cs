using System;
using System.IO;

using PDFjet.NET;


/**
 *  Example_05.cs
 *
 */
public class Example_05 {

    public Example_05() {

        FileStream fos = new FileStream("Example_05.pdf", FileMode.Create);
        BufferedStream bos = new BufferedStream(fos);

        PDF pdf = new PDF(bos);
        pdf.setCompressor(Compressor.ORIGINAL_ZLIB);

        // Before you enable this flag please read README.ZLIB.TXT
        // in the 'optional' directory.

        // If PDF/A is not required use Helvetica, TimesRoman or Courier
        Font f1 = new Font(pdf, "Helvetica");

        Page page = new Page(pdf, Letter.PORTRAIT);

        TextLine text = new TextLine(f1);
        text.SetPosition(300.0, 300.0);
        for (int i = 0; i < 360; i += 15) {
            text.SetTextDirection(i);
            text.SetUnderline(true);
            // text.SetStrikeLine(true);
            text.SetText("             Hello, World -- " + i + " degrees.");
            text.DrawOn(page);
        }

        text = new TextLine(f1, "WAVE AWAY");
        text.SetPosition(70.0, 50.0);
        text.DrawOn(page);

        f1.SetKernPairs(true);
        text.SetPosition(70.0, 70.0);
        text.DrawOn(page);

        f1.SetKernPairs(false);
        text.SetPosition(70.0, 90.0);
        text.DrawOn(page);

        f1.SetSize(8);
        text = new TextLine(f1, "-- font.SetKernPairs(false);");
        text.SetPosition(150.0, 50.0);
        text.DrawOn(page);
        text.SetPosition(150.0, 90.0);
        text.DrawOn(page);
        text = new TextLine(f1, "-- font.SetKernPairs(true);");
        text.SetPosition(150.0, 70.0);
        text.DrawOn(page);

        Point point = new Point(300.0, 300.0);
        point.SetShape(Point.CIRCLE);
        point.SetFillShape(true);
        point.SetColor(RGB.BLUE);
        point.SetRadius(37.0);
        point.DrawOn(page);
        point.SetRadius(25.0);
        point.SetColor(RGB.WHITE);
        point.DrawOn(page);

        pdf.Flush();
        bos.Close();
    }


    public static void Main(String[] args) {
        try {
            new Example_05();
        } catch (Exception e) {
            Console.WriteLine(e.StackTrace);
        }
    }

}   // End of Example_05.cs
