/**
 *  Font.cs
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
using System.Text;
using System.Collections;using System.Collections.Generic;
using System.IO.Compression;


namespace PDFjet.NET {
public class Font {

    internal String name = null;
    internal int objNumber = 0;

    // The object number of the embedded font file
    internal int fileObjNumber = -1;

    // Font attributes
    internal double size = 12.0;
    internal int unitsPerEm = 1000;
    internal double ascent = 0.0;
    internal double descent = 0.0;
    internal double body_height = 0.0;

    // Font metrics
    internal int[][] metrics = null;

    // Don't change the following default values!
    internal bool isStandard = true;
    internal bool kernPairs = false;
    internal bool isComposite = false;
    internal int firstChar = 32;
    internal int lastChar = 255;

    private PDF pdf = null;

    private bool isCJK = false;
    private int codePage = CodePage.CP1252;

    // Font bounding box
    private double bBoxLLx = 0.0;
    private double bBoxLLy = 0.0;
    private double bBoxURx = 0.0;
    private double bBoxURy = 0.0;

    private int[] advanceWidths = null;
    private int[] glyphWidth = null;
    
    private int fontUnderlinePosition = 0;
    private int fontUnderlineThickness = 0;
    
    internal double underlinePosition = 0.0;
    internal double underlineThickness = 0.0;


    // Constructor for standard fonts
    public Font(PDF pdf, String fontName) {
        this.pdf = pdf;
        this.name = fontName;

        pdf.newobj();
        pdf.Append("<<\n");
        pdf.Append("/Type /Font\n");
        pdf.Append("/Subtype /Type1\n");
        pdf.Append("/BaseFont /");
        pdf.Append(fontName);
        pdf.Append('\n');
        if (fontName.Equals("Symbol") || fontName.Equals("ZapfDingbats")) {
            // Use the built-in encoding
        } else {
            pdf.Append("/Encoding /WinAnsiEncoding\n");
        }
        pdf.Append(">>\n");
        pdf.endobj();
        objNumber = pdf.objNumber;

        CoreFont font = (CoreFont) Activator.CreateInstance(Type.GetType("PDFjet.NET." + name.Replace('-', '_')));
        bBoxLLx = font.getBBoxLLx();
        bBoxLLy = font.getBBoxLLy();
        bBoxURx = font.getBBoxURx();
        bBoxURy = font.getBBoxURy();
        metrics = font.getMetrics();
        ascent = bBoxURy * size / unitsPerEm;
        descent = bBoxLLy * size / unitsPerEm;
        body_height = ascent - descent;
        
        fontUnderlineThickness = font.getUnderlineThickness();
        fontUnderlinePosition = font.getUnderlinePosition();
        
        underlineThickness = fontUnderlineThickness * size / unitsPerEm;
        underlinePosition = fontUnderlinePosition * size / -unitsPerEm + underlineThickness / 2.0;

        pdf.fonts.Add(this);
    }


    // Constructor for CJK fonts
    public Font(PDF pdf, String fontName, int codePage) {
        this.pdf = pdf;
        this.name = fontName;
        this.codePage = codePage;
        isCJK = true;
        isStandard = false;
        isComposite = true;

        firstChar = 0x0020;
        lastChar = 0xFFEE;

        // Font Descriptor
        pdf.newobj();
        pdf.Append("<<\n");
        pdf.Append("/Type /FontDescriptor\n");
        pdf.Append("/FontName /");
        pdf.Append(fontName);
        pdf.Append('\n');
        pdf.Append("/Flags 4\n");
        pdf.Append("/FontBBox [0 0 0 0]\n");
        pdf.Append(">>\n");
        pdf.endobj();

        // CIDFont Dictionary
        pdf.newobj();
        pdf.Append("<<\n");
        pdf.Append("/Type /Font\n");
        pdf.Append("/Subtype /CIDFontType0\n");
        pdf.Append("/BaseFont /");
        pdf.Append(fontName);
        pdf.Append('\n');
        pdf.Append("/FontDescriptor ");
        pdf.Append(pdf.objNumber - 1);
        pdf.Append(" 0 R\n");
        pdf.Append("/CIDSystemInfo <<\n");
        pdf.Append("/Registry (Adobe)\n");
        if (fontName.StartsWith("AdobeMingStd")) {
            pdf.Append("/Ordering (CNS1)\n");
            pdf.Append("/Supplement 4\n");
        } else if (fontName.StartsWith("AdobeSongStd")) {
            pdf.Append("/Ordering (GB1)\n");
            pdf.Append("/Supplement 4\n");
        } else if (fontName.StartsWith("KozMinPro")) {
            pdf.Append("/Ordering (Japan1)\n");
            pdf.Append("/Supplement 4\n");
        } else if (fontName.StartsWith("AdobeMyungjoStd")) {
            pdf.Append("/Ordering (Korea1)\n");
            pdf.Append("/Supplement 1\n");
        } else {
            throw new Exception("Unsupported font: " + fontName);
        }
        pdf.Append(">>\n");
        pdf.Append(">>\n");
        pdf.endobj();

        // Type0 Font Dictionary
        pdf.newobj();
        pdf.Append("<<\n");
        pdf.Append("/Type /Font\n");
        pdf.Append("/Subtype /Type0\n");
        pdf.Append("/BaseFont /");
        if (fontName.StartsWith("AdobeMingStd")) {
            pdf.Append(fontName + "-UniCNS-UTF16-H\n");
            pdf.Append("/Encoding /UniCNS-UTF16-H\n");
        } else if (fontName.StartsWith("AdobeSongStd")) {
            pdf.Append(fontName + "-UniGB-UTF16-H\n");
            pdf.Append("/Encoding /UniGB-UTF16-H\n");
        } else if (fontName.StartsWith("KozMinPro")) {
            pdf.Append(fontName + "-UniJIS-UCS2-H\n");
            pdf.Append("/Encoding /UniJIS-UCS2-H\n");
        } else if (fontName.StartsWith("AdobeMyungjoStd")) {
            pdf.Append(fontName + "-UniKS-UCS2-H\n");
            pdf.Append("/Encoding /UniKS-UCS2-H\n");
        } else {
            throw new Exception("Unsupported font: " + fontName);
        }
        pdf.Append("/DescendantFonts [");
        pdf.Append(pdf.objNumber - 1);
        pdf.Append(" 0 R]\n");
        pdf.Append(">>\n");
        pdf.endobj();
        objNumber = pdf.objNumber;

        ascent = size;
        descent = -ascent/4;
        body_height = ascent - descent;

        pdf.fonts.Add(this);
    }


    public void SetSize(double fontSize) {
        size = fontSize;
        if (isCJK) {
            ascent = size;
            descent = -ascent/4;
            return;
        }
        ascent = bBoxURy * size / unitsPerEm;
        descent = bBoxLLy * size / unitsPerEm;
        body_height = ascent - descent;
        
      	underlineThickness = fontUnderlineThickness * size / unitsPerEm;
       	underlinePosition = fontUnderlinePosition * size / -unitsPerEm + underlineThickness / 2.0;
    }
    
    
    public double getSize() {
    	return size;
    }


    public void SetKernPairs(bool kernPairs) {
        this.kernPairs = kernPairs;
    }


    public double StringWidth(String str) {
        int width = 0;
        if (isCJK) {
            return str.Length * ascent;
        }

        for (int i = 0; i < str.Length; i++) {
            int c1 = str[i];
            if (isStandard == false) {
                if (c1 < firstChar || c1 > lastChar) {
                    width += advanceWidths[0];
                } else {
                    width += nonStandardFontGlyphWidth(c1);
                }
                continue;
            }

            if (c1 < firstChar || c1 > lastChar) {
                c1 = 32;
            }
            c1 -= 32;
            width += metrics[c1][1];

            if (kernPairs == false) continue;
            if (name.StartsWith("C") || // Courier
                name.StartsWith("S") || // Symbol
                name.StartsWith("Z")) { // ZapfDingbats
                continue;
            }

            if (i == str.Length - 1) break;

            int c2 = str[i + 1];
            if (c2 < firstChar || c2 > lastChar) {
                c2 = 32;
            }
            for (int j = 2; j < metrics[c1].Length; j += 2) {
                if (metrics[c1][j] == c2) {
                    width += metrics[c1][j + 1];
                    break;
                }
            }
        }

        return width * size / unitsPerEm;
    }


    private int nonStandardFontGlyphWidth(int c1) {
        int width = 0;

        if (isComposite) {
            width = glyphWidth[c1];
        } else {
            if (c1 < 127) {
                width = glyphWidth[c1];
            } else {
                if (codePage == 0) {
                    width = glyphWidth[CP1250.codes[c1 - 127]];
                } else if (codePage == 1) {
                    width = glyphWidth[CP1251.codes[c1 - 127]];
                } else if (codePage == 2) {
                    width = glyphWidth[CP1252.codes[c1 - 127]];
                } else if (codePage == 3) {
                    width = glyphWidth[CP1253.codes[c1 - 127]];
                } else if (codePage == 4) {
                    width = glyphWidth[CP1254.codes[c1 - 127]];
                } else if (codePage == 7) {
                    width = glyphWidth[CP1257.codes[c1 - 127]];
                }
            }
        }

        return width;
    }

}   // End of Font.cs
}   // End of namespace PDFjet.NET
