/**
 *  Cell.cs
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
using System.Collections;using System.Collections.Generic;


namespace PDFjet.NET {
public class Cell {

    internal double width = 70.0;
    internal double height = 0.0;

    internal Font font = null;
    internal String text = " ";
    internal int align = Align.LEFT;
    internal Point point = null;

    public Border border = null;

    internal int colspan = 1;
    internal double[] bgColor = {1.0, 1.0, 1.0};
    internal double[] penColor = {0.0, 0.0, 0.0};
    internal double[] brushColor = {0.0, 0.0, 0.0};

    internal double lineWidth = 0.0;

    
    public Cell(Font font) {
        this.font = font;
        this.border = new Border();
    }


    public Cell(Font font, String text) {
        this.font = font;
        this.text = text;
        this.border = new Border();
    }


    public void SetFont(Font font) {
        this.font = font;
    }


    public Font GetFont() {
        return font;
    }


    public void SetText(String text) {
        this.text = text;
    }


    public String GetText() {
        return text;
    }


    public void SetPoint(Point point) {
        this.point = point;
    }


    public Point GetPoint() {
        return point;
    }


    public Border GetBorder() {
        return border;
    }


    public double GetWidth() {
        return width;
    }


    public double GetHeight() {
        return height;
    }


    public double getColspan() {
        return colspan;
    }


    public void SetBorder(Border border) {
        this.border = border;
    }


    public void SetNoBorders() {
        this.border.top = false;
        this.border.bottom = false;
        this.border.left = false;
        this.border.right = false;
    }


    public void SetBgColor(double[] bgColor) {
        this.bgColor = bgColor;
    }


    public void SetBgColor(int[] rgb) {
        this.bgColor =
                new double[] { rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0 };
    }


    public void SetFgColor(double[] fgColor) {
        this.penColor = fgColor;
        this.brushColor = fgColor;
    }


    public void SetFgColor(int[] rgb) {
        this.penColor =
                new double[] { rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0 };
        this.brushColor =
                new double[] { rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0 };
    }


    public void SetPenColor(double[] fgColor) {
        this.penColor = fgColor;
    }


    public void SetPenColor(int[] rgb) {
        this.penColor =
                new double[] { rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0 };
    }


    public void SetBrushColor(double[] fgColor) {
        this.brushColor = fgColor;
    }


    public void SetBrushColor(int[] rgb) {
        this.brushColor =
                new double[] { rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0 };
    }


    public void SetTextAlignment(int alignment) {
        this.align = alignment;
    }


    public void SetColSpan( int colspan ) {
        this.colspan = colspan;
    }


    public void SetWidth( double width ) {
        this.width = width;
    }


    public void SetHeight( double height ) {
        this.height = height;
    }
    
    
    public void setColspan(int colspan) {
    	this.colspan = colspan;
    }


    public virtual void Paint(
            Page page,
            double x,
            double y,
            double w,
            double h,
            double margin) {

        width = w;
        height = h;

        DrawBackground(page, x, y, w, h);
        DrawBorders(page, x, y, w, h);
        DrawText(page, x, y, w, margin);

    }


    private void DrawBackground(
            Page page,
            double x,
            double y,
            double cell_w,
            double cell_h) {

        page.SetBrushColor(brushColor[0], brushColor[1], brushColor[2]);
        Box box = new Box(x, y, cell_w, cell_h);
        box.SetColor(bgColor);
        box.SetFillShape(true);
        box.DrawOn(page);

    }


    private void DrawBorders(
            Page page,
            double x,
            double y,
            double cell_w,
            double cell_h) {

        page.setPenWidth(lineWidth);
        page.SetPenColor(penColor[0], penColor[1], penColor[2]);

        if (border.left) {
            page.moveTo(x, y);
            page.lineTo(x, y + cell_h);
            page.strokePath();
        }

        if (border.right) {
            page.moveTo(x + cell_w, y);
            page.lineTo(x + cell_w, y + cell_h);
            page.strokePath();
        }

        if (border.top) {
            page.moveTo(x, y);
            page.lineTo(x + cell_w, y);
            page.strokePath();
        }

        if (border.bottom) {
            page.moveTo(x, y + cell_h);
            page.lineTo(x + cell_w, y + cell_h);
            page.strokePath();
        }

    }


    private void DrawText(
            Page page,
            double x,
            double y,
            double cell_w,
            double margin) {

        double y_text = y + font.ascent + margin;
        page.SetPenColor(penColor[0], penColor[1], penColor[2]);
        page.SetBrushColor(brushColor[0], brushColor[1], brushColor[2]);

        if (align == Align.RIGHT) {
            page.drawString(
                    font,
                    text,
                    (x + cell_w) - (font.StringWidth(text) + margin), y_text);
        } else if (align == Align.CENTER) {
            page.drawString(
                    font,
                    text,
                    x + (cell_w - font.StringWidth(text)) / 2, y_text);
        } else {
            // Use the default - Align.LEFT
            page.drawString(
                    font,
                    text,
                    x + margin,
                    y_text);
        }

        if (point != null) {
            point.x = (x + cell_w) - (font.ascent / 2 + margin);
            point.y = y + (font.ascent / 2 + margin);
            point.r = font.ascent / 3;
            page.drawPoint(point);
        }

    }

}   // End of Cell.cs
}   // End of namespace PDFjet.NET
