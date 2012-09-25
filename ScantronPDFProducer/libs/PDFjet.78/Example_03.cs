using System;
using System.IO;

using PDFjet.NET;


/**
 *  Example_03.cs
 *
 */
public class Example_03 {

    public Example_03() {

        FileStream fos = new FileStream("Example_03.pdf", FileMode.Create);
        BufferedStream bos = new BufferedStream(fos);

        PDF pdf = new PDF(bos);
        pdf.setCompressor(Compressor.ORIGINAL_ZLIB);

        // Before you enable this flag please read README.ZLIB.TXT
        // in the 'optional' directory.

//        Font f1 = new Font(pdf, "Helvetica");
/*
        Font f1 = new Font(pdf, new FileStream(
                "fonts/DroidFonts/DroidSans.otf", FileMode.Open),
                CodePage.UNICODE,
                Embed.YES);
*/
        String fileName = "images/eu-map.png";
        FileStream fis1 = new FileStream(fileName, FileMode.Open);
        Image image1 = new Image(pdf, fis1, ImageType.PNG);

        fileName = "images/fruit.jpg"; 
        FileStream fis2 = new FileStream(fileName, FileMode.Open);
        Image image2 = new Image(pdf, fis2, ImageType.JPEG);

        fileName = "images/mt-map.bmp"; 
        FileStream fis3 = new FileStream(fileName, FileMode.Open);
        Image image3 = new Image(pdf, fis3, ImageType.BMP);

        
        Page page = new Page(pdf, A4.PORTRAIT);
/*
        TextLine text = new TextLine(f1,
                "The map below is an embedded PNG image");
        text.SetPosition(90, 30);
        text.DrawOn(page);
*/
        image1.SetPosition(90, 40);
        image1.DrawOn(page);
/*
        text.SetText(
                "JPG image file embedded once and drawn 3 times");
        text.SetPosition(90, 550);
        text.DrawOn(page);
*/
        image2.SetPosition(90, 560);
        image2.ScaleBy(0.5);
        image2.DrawOn(page);

        image2.SetPosition(260, 560);
        image2.ScaleBy(0.5);
        image2.DrawOn(page);

        image2.SetPosition(350, 560);
        image2.ScaleBy(0.5);
        image2.DrawOn(page);
/*
        text.SetText(
                "The map on the right is an embedded BMP image");
        text.SetUnderline(true);
        text.SetStrikeLine(true);
        text.SetTextDirection(15);
        text.SetPosition(90, 800);
        text.DrawOn(page);
*/
        image3.SetPosition(390, 630);
        image3.ScaleBy(0.5);
        image3.DrawOn(page);

        pdf.Flush();
        bos.Close();
    }


    public static void Main(String[] args) {
        try {
            new Example_03();
        } catch (Exception e) {
            Console.WriteLine(e.StackTrace);
        }
    }

}   // End of Example_03.cs
