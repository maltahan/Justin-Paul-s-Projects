import java.lang.*;
import java.text.*;
import java.io.*;
import java.util.*;

import com.pdfjet.*;


/**
 *  Example_19.java
 *
 */
class Example_19 {

    public Example_19() throws Exception {

        try {

            FileOutputStream fos = new FileOutputStream("Example_19.pdf");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            PDF pdf = new PDF( bos );

            FileInputStream fis = new FileInputStream( "Example_03.pdf" );

/*
            FileInputStream fis =
                    new FileInputStream( "PDF32000_2008.pdf" );
*/
            BufferedInputStream bis = new BufferedInputStream( fis );
            List< PDFobj > objects = pdf.read(bis);
            for ( PDFobj obj : objects ) {
                for ( int i = 0; i < obj.dict.size(); i++ ) {
                    System.out.println(obj.dict.get(i));
                }
                System.out.println();
            }
            bis.close();

            pdf.flush();
            bos.close();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        try {
            new Example_19();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}   // End of Example_19.java
