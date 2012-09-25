
using System;
using System.Collections.Generic;
using System.IO;

using PDFjet.NET;


/**
 *  Example_19.cs
 *
 */
class Example_19 {

    public Example_19() {

        try {

            FileStream fos = new FileStream("Example_19.pdf", FileMode.Create);
            BufferedStream bos = new BufferedStream(fos);

            PDF pdf = new PDF( bos );

            FileStream fis = new FileStream( "Example_03.pdf", FileMode.Open );

            // FileInputStream fis = new FileInputStream( "PDF32000_2008.pdf" );

            BufferedStream bis = new BufferedStream( fis );
            List< PDFobj > objects = pdf.read(bis);
            for ( int j = 0; j < objects.Count; j++ ) {
                PDFobj obj = ( PDFobj ) objects[j];
                for ( int i = 0; i < obj.dict.Count; i++ ) {
                    Console.WriteLine(obj.dict[i]);
                }
                Console.WriteLine();
            }
            bis.Close();

            pdf.Flush();
            bos.Close();
        }
        catch ( Exception e ) {
            Console.WriteLine(e.StackTrace);
        }

    }


    public static void Main(String[] args) {
        try {
            new Example_19();
        } catch (Exception e) {
            Console.WriteLine(e.StackTrace);
        }
    }

}   // End of Example_19.cs
