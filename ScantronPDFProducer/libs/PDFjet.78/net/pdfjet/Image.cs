/**
 *  Image.cs
 *
Copyright (c) 2007, 2008, 2009, 2010 Innovatics Inc.

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
      this list of conditions and the following disclaimer.
 
    * Redistributions in binary form must reproduce the above copyright notice,
      this list of conditions and the following disclaimer in the documentation
      and / or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/




using System;
using System.IO;
using System.Collections;using System.Collections.Generic;
using System.IO.Compression;


namespace PDFjet.NET {
public class Image {

    internal int objNumber = 0;

    internal double x = 0.0;   // Position of the image on the page
    internal double y = 0.0;
    internal double w = 0;     // Image width
    internal double h = 0;     // Image height

    private double box_x = 0.0;
    private double box_y = 0.0;

    private byte[] data = null;


    public Image(PDF pdf, System.IO.Stream inputStream, int imageType)
            {

        if (imageType == ImageType.JPEG) {
            JPEGImage jpg = new JPEGImage(inputStream);
            data = jpg.GetData();
            w = jpg.GetWidth();
            h = jpg.GetHeight();
            if ( jpg.GetColorComponents() == 1 ) {
                addImage(pdf, data, imageType, "DeviceGray", 8);            
            }
            else if ( jpg.GetColorComponents() == 3 ) {
                addImage(pdf, data, imageType, "DeviceRGB", 8);
            }
        }
        else if (imageType == ImageType.PNG) {
            PNGImage png = new PNGImage(inputStream);
            data = png.GetData();
            w = png.GetWidth();
            h = png.GetHeight();
            if ( png.colorType == 0 ) {
                addImage(pdf, data, imageType, "DeviceGray", png.bitDepth);                
            }
            else {
                if ( png.bitDepth == 16 ) {
                    addImage(pdf, data, imageType, "DeviceRGB", 16);
                }
                else {
                    addImage(pdf, data, imageType, "DeviceRGB", 8);
                }
            }
        }
        else if (imageType == ImageType.BMP) {
            BMPImage bmp = new BMPImage(inputStream);
            data = bmp.GetData();
            w = bmp.GetWidth();
            h = bmp.GetHeight();
            addImage(pdf, data, imageType, "DeviceRGB", 8);
        }

        inputStream.Close();
    }


    public void SetPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public void ScaleBy(double factor) {
        this.w *= factor;
        this.h *= factor;
    }


    public void PlaceIn(Box box) {
        box_x = box.x;
        box_y = box.y;
    }


    public void DrawOn(Page page) {
        x += box_x;
        y += box_y;
        page.Append("q\n");
        page.Append(w);
        page.Append(" 0 0 ");
        page.Append(h);
        page.Append(' ');
        page.Append(x);
        page.Append(' ');
        page.Append((page.height - y) - h);
        page.Append(" cm\n");
        page.Append("/Im");
        page.Append(objNumber);
        page.Append(" Do\n");
        page.Append("Q\n");
    }


    private void addImage(
            PDF pdf,
            byte[] data,
            int imageType,
            String colorSpace,
            int bitsPerComponent) {
        // Add the image
        pdf.newobj();
        pdf.Append("<<\n");
        pdf.Append("/Type /XObject\n");
        pdf.Append("/Subtype /Image\n");
        if (imageType == ImageType.JPEG) {
            pdf.Append("/Filter /DCTDecode\n");
        }
        else if (imageType == ImageType.PNG || imageType == ImageType.BMP) {
            pdf.Append("/Filter /FlateDecode\n");
        }
        pdf.Append("/Width ");
        pdf.Append(( int ) w);
        pdf.Append('\n');
        pdf.Append("/Height ");
        pdf.Append(( int ) h);
        pdf.Append('\n');
        pdf.Append("/ColorSpace /");
        pdf.Append(colorSpace);
        pdf.Append('\n');
        pdf.Append("/BitsPerComponent ");
        pdf.Append(bitsPerComponent);
        pdf.Append('\n');
        pdf.Append("/Length ");
        pdf.Append(data.Length);
        pdf.Append('\n');
        pdf.Append(">>\n");
        pdf.Append("stream\n");
        pdf.Append(data, 0, data.Length);
        pdf.Append("\nendstream\n");
        pdf.endobj();
        pdf.images.Add(this);
        objNumber = pdf.objNumber;
    }


    public Image(PDF pdf, PDFobj obj) {
        pdf.newobj();
        pdf.Append("<<\n");
        pdf.Append("/Type /XObject\n");
        pdf.Append("/Subtype /Image\n");
        pdf.Append("/Filter ");
        pdf.Append(obj.getValue(PDFobj.FILTER));
        pdf.Append('\n');
        pdf.Append("/Width ");
        pdf.Append(obj.getValue(PDFobj.WIDTH));
        pdf.Append('\n');
        pdf.Append("/Height ");
        pdf.Append(obj.getValue(PDFobj.HEIGHT));
        pdf.Append('\n');
        pdf.Append("/ColorSpace ");
        pdf.Append(obj.getValue(PDFobj.COLORSPACE));
        pdf.Append('\n');
        pdf.Append("/BitsPerComponent ");
        pdf.Append(obj.getValue(PDFobj.BITSPERCOMPONENT));
        pdf.Append('\n');
        pdf.Append("/Length ");
        pdf.Append(obj.stream.Length);
        pdf.Append('\n');
        pdf.Append(">>\n");
        pdf.Append("stream\n");
        pdf.Append(obj.stream, 0, obj.stream.Length);
        pdf.Append("\nendstream\n");
        pdf.endobj();
        pdf.images.Add(this);
        objNumber = pdf.objNumber;
    }


    public double GetWidth() {
        return this.w;
    }


    public double GetHeight() {
        return this.h;
    }

}   // End of Image.cs

}   // End of namespace PDFjet.NET
