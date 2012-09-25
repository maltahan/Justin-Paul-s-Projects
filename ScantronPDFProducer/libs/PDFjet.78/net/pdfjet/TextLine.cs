/**
 *  TextLine.cs
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
using System.Text;
using System.Collections;using System.Collections.Generic;


namespace PDFjet.NET {
public class TextLine {

    internal double x = 0.0;
    internal double y = 0.0;

    internal Font font = null;
    internal String str = "";
    internal String uri = null;
    internal bool underline = false;
    internal bool strike = false;
    internal int degrees = 0;

    internal double[] color = {0.0, 0.0, 0.0};

    private double box_x = 0.0;
    private double box_y = 0.0;


    public TextLine(Font font) {
        this.font = font;
    }


    public TextLine(Font font, String str) {
        this.font = font;
        this.str = str;
    }


    public void SetPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public void SetText(String str) {
        this.str = str;
    }


    public void SetFont(Font font) {
        this.font = font;
    }


    public void SetColor(double[] color) {
        this.color = color;
    }


    public void SetColor(int[] rgb) {
        this.color = new double[] {rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0};
    }


    public String GetText() {
        return str;
    }


    public double[] GetColor() {
        return color;
    }


    public void SetURIAction(String uri) {
        this.uri = uri;
    }


    public void SetUnderline(bool underline) {
        this.underline = underline;
    }


    public void SetStrikeLine(bool strike) {
        this.strike = strike;
    }


    public void SetTextDirection(int degrees) {
        this.degrees = degrees;
    }


    public void PlaceIn(Box box) {
        box_x = box.x;
        box_y = box.y;
    }


    public void DrawOn(Page page) {
        DrawOn(page, true);
    }


    public void DrawOn(Page page, bool draw) {
        if (!draw) return;

        page.SetTextDirection(degrees);
        x += box_x;
        y += box_y;
        if (uri != null) {
            page.annots.Add(new Annotation(uri,
                    x,
                    page.height - (y - font.ascent),
                    x + font.StringWidth(str),
                    page.height - (y - font.descent)));
        }

        if (str != null) {
            page.SetBrushColor(
                    color[0], color[1], color[2]);
            page.drawString(font, str, x, y);
        }

        if (underline) {
            page.setPenWidth(font.underlineThickness);
            page.SetPenColor(color[0], color[1], color[2]);
            double lineLength = font.StringWidth(str);
            double radians = Math.PI * degrees / 180.0;
            double x_adjust = font.underlinePosition * Math.Sin(radians);
            double y_adjust = font.underlinePosition * Math.Cos(radians);
            double x2 = x + lineLength * Math.Cos(radians);
            double y2 = y - lineLength * Math.Sin(radians);
            page.moveTo(x + x_adjust, y + y_adjust);
            page.lineTo(x2 + x_adjust, y2 + y_adjust);
            page.strokePath();
        }

        if (strike) {
            page.setPenWidth(font.underlineThickness);
            page.SetPenColor(color[0], color[1], color[2]);
            double lineLength = font.StringWidth(str);
            double radians = Math.PI * degrees / 180.0;
            double x_adjust = ( font.body_height / 4.0 ) * Math.Sin(radians);
            double y_adjust = ( font.body_height / 4.0 ) * Math.Cos(radians);
            double x2 = x + lineLength * Math.Cos(radians);
            double y2 = y - lineLength * Math.Sin(radians);
            page.moveTo(x - x_adjust, y - y_adjust);
            page.lineTo(x2 - x_adjust, y2 - y_adjust);
            page.strokePath();
        }

        page.SetTextDirection(0);
    }

}   // End of TextLine.cs
}   // End of namespace PDFjet.NET
