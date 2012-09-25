/**
 *  Path.cs
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
public class Path {

    private double[] color = {0.0, 0.0, 0.0};
    private double width = 0.3;
    private String pattern = "[] 0";
    private bool fill_shape = false;
    private bool close_path = false;

    private List<Point> points = null;

    private double box_x = 0.0;
    private double box_y = 0.0;


    public Path() {
        points = new List<Point>();
    }


    public void Add(Point point) {
        points.Add(point);
    }


    public void SetPattern(String pattern) {
        this.pattern = pattern;
    }


    public void SetWidth(double width) {
        this.width = width;
    }


    public void SetColor(double[] color) {
        this.color = color;
    }


    public void SetColor(int[] rgb) {
        this.color = new double[] {rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255};
    }


    public void setClosePath(bool close_path) {
        this.close_path = close_path;
    }


    public void SetFillShape(bool fill_shape) {
        this.fill_shape = fill_shape;
    }


    public void PlaceIn(
            Box box,
            double x_offset,
            double y_offset) {
        box_x = box.x + x_offset;
        box_y = box.y + y_offset;
    }


    public void ScaleBy(double factor) {
        for (int i = 0; i < points.Count; i++) {
            Point point = points[i];
            point.x *= factor;
            point.y *= factor;
        }
    }


    public void DrawOn(Page page) {
        if (fill_shape) {
            page.SetBrushColor(
                    color[0], color[1], color[2]);
        } else {
            page.SetPenColor(
                    color[0], color[1], color[2]);
        }
        page.setPenWidth(width);
        page.SetLinePattern(pattern);

        for (int i = 0; i < points.Count; i++) {
            Point point = points[i];
            point.x += box_x;
            point.y += box_y;
        }

        if (fill_shape) {
            page.drawPath(points, 'f');
        } else {
            if (close_path) {
                page.drawPath(points, 's');
            } else {
                page.drawPath(points, 'S');
            }
        }

        for (int i = 0; i < points.Count; i++) {
            Point point = points[i];
            point.x -= box_x;
            point.y -= box_y;
        }
    }

}   // End of Path.cs
}   // End of namespace PDFjet.NET
