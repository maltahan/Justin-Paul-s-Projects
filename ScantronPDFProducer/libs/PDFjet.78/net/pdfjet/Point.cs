/**
 *  Point.cs
 *
Copyright (c) 2007, 2008, 2009 Innovatics Inc.

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
public class Point {

    public const int INVISIBLE = -1;
    public const int CIRCLE = 0;
    public const int DIAMOND = 1;
    public const int BOX = 2;
    public const int PLUS = 3;
    public const int H_DASH = 4;
    public const int V_DASH = 5;
    public const int MULTIPLY = 6;
    public const int STAR = 7;
    public const int X_MARK = 8;
    public const int UP_ARROW = 9;
    public const int DOWN_ARROW = 10;
    public const int LEFT_ARROW = 11;
    public const int RIGHT_ARROW = 12;

    public const bool IS_CURVE_POINT = true;

    internal double x = 0.0;
    internal double y = 0.0;
    internal double r = 2.0;

    internal int shape = 0;
    internal double[] color = {0.0, 0.0, 0.0};
    internal double line_width = 0.3;
    internal String line_pattern = "[] 0";
    internal bool fill_shape = false;

    internal bool isCurvePoint = false;

    internal String text = null;
    internal String uri = null;
    internal List<String> info = null;

    // drawLineTo == false means:
    //      Don't draw a line to this point from the previous
    internal bool drawLineTo = false;

    private double box_x = 0.0;
    private double box_y = 0.0;


    public Point() {
    }


    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public Point(double x, double y, bool isCurvePoint) {
        this.x = x;
        this.y = y;
        this.isCurvePoint = isCurvePoint;
    }


    public void SetPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public void SetX(double x) {
        this.x = x;
    }


    public double GetX() {
        return x;
    }


    public void SetY(double y) {
        this.y = y;
    }


    public double GetY() {
        return y;
    }


    public void SetRadius(double r) {
        this.r = r;
    }


    public double GetRadius() {
        return r;
    }


    public void SetShape(int shape) {
        this.shape = shape;
    }


    public int GetShape() {
        return shape;
    }


    public void SetFillShape(bool fill) {
        this.fill_shape = fill;
    }


    public bool GetFillShape() {
        return fill_shape;
    }


    public void SetColor(double[] color) {
        this.color = color;
    }


    public void SetColor(int[] rgb) {
        this.color = new double[] {rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0};
    }


    public double[] GetColor() {
        return color;
    }


    public void SetLineWidth(double line_width) {
        this.line_width = line_width;
    }


    public double GetLineWidth() {
        return line_width;
    }


    public void SetLinePattern(String line_pattern) {
        this.line_pattern = line_pattern;
    }


    public String GetLinePattern() {
        return line_pattern;
    }


    public void SetDrawLineTo(bool drawLineTo) {
        this.drawLineTo = drawLineTo;
    }


    public bool GetDrawLineTo() {
        return drawLineTo;
    }


    public void SetURIAction(String uri) {
        this.uri = uri;
    }


    public String GetURIAction() {
        return uri;
    }


    public void SetText(String text) {
        this.text = text;
    }


    public String GetText() {
        return text;
    }


    public void SetInfo(List<String> info) {
        this.info = info;
    }


    public List<String> GetInfo() {
        return info;
    }


    public void PlaceIn(
            Box box,
            double x_offset,
            double y_offset) {
        box_x = box.x + x_offset;
        box_y = box.y + y_offset;
    }


    public void DrawOn(Page page) {
        page.setPenWidth(line_width);
        page.SetLinePattern(line_pattern);

        if (fill_shape) {
            page.SetBrushColor(
                    color[0], color[1], color[2]);
        } else {
            page.SetPenColor(
                    color[0], color[1], color[2]);
        }

        x += box_x;
        y += box_y;
        page.drawPoint(this);
        x -= box_x;
        y -= box_y;
    }

}   // End of Point.cs
}   // End of namespace PDFjet.NET
