/**
 *  Page.cs
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
public class Page {

    internal MemoryStream buf = null;
    internal String writingMode = "1 0 0 1 ";
    internal int renderingMode = 0;
    internal double width = 0.0;
    internal double height = 0.0;
    internal List<Annotation> annots = null;
    internal PDF pdf = null;

    private double[] pen_color = {0.0, 0.0, 0.0};
    private double[] brush_color = {0.0, 0.0, 0.0};
    private double pen_width = 0.0;
    private String line_pattern = "[] 0";


    public Page(PDF pdf, double[] pageSize) {
        this.pdf = pdf;
        annots = new List<Annotation>();
        width = pageSize[0];
        height = pageSize[1];
        buf = new MemoryStream(8192);
        pdf.pages.Add(this);
    }


    internal void drawLine(
            double x1,
            double y1,
            double x2,
            double y2) {
        moveTo(x1, y1);
        lineTo(x2, y2);
        strokePath();
    }


    internal void drawString(
            Font font,
            String str,
            double x,
            double y) {
        Append("BT\n");
        Append("/F");
        Append(font.objNumber);
        Append(' ');
        Append(font.size);
        Append(" Tf\n");
        if (renderingMode != 0) {
            Append(renderingMode);
            Append(" Tr\n");
        }
        Append(writingMode);
        Append(x);
        Append(' ');
        Append(height - y);
        Append(" Tm\n");

        Append("[ (");
        for (int i = 0; i < str.Length; i++) {
            int c1 = str[i];
            if (font.isComposite) {
                if (c1 < font.firstChar || c1 > font.lastChar) {
                    Append((byte) 0x0000);
                    Append((byte) 0x0020);
                    continue;
                }

                byte hi = (byte) (c1 >> 8);
                byte lo = (byte) (c1);
                if (hi == '(' || hi == ')' || hi == '\\') {
                    Append((byte) '\\');
                }
                Append((byte) (c1 >> 8));
                if (lo == '(' || lo == ')' || lo == '\\') {
                    Append((byte) '\\');
                }
                Append((byte) (c1));
                continue;
            }

            if (c1 < font.firstChar || c1 > font.lastChar) {
                c1 = 0x0020;
            }
            if (c1 == '(' || c1 == ')' || c1 == '\\') {
                Append((byte) '\\');
            }
            Append((byte) c1);

            if (font.isStandard == false) continue;
            if (font.kernPairs == false) continue;
            if (font.name.StartsWith("C") ||    // Courier
                font.name.StartsWith("S") ||    // Symbol
                font.name.StartsWith("Z")) {    // ZapfDingbats
                continue;
            }

            if (i == str.Length - 1) break;
            c1 -= 32;
            int c2 = str[i + 1];
            if (c2 < font.firstChar || c2 > font.lastChar) {
                c2 = 32;
            }
            for (int j = 2; j < font.metrics[c1].Length; j += 2) {
                if (font.metrics[c1][j] == c2) {
                    Append(") ");
                    Append(-font.metrics[c1][j + 1]);
                    Append(" (");
                    break;
                }
            }
        }
        Append(") ] TJ\n");

        Append("ET\n");
    }


    /**
     * Set color for stroking operations
     */
    internal void SetPenColor(
            double r, double g, double b) {
        if (pen_color[0] == r &&
                pen_color[1] == g &&
                pen_color[2] == b) {
            return;
        } else {
            pen_color[0] = r;
            pen_color[1] = g;
            pen_color[2] = b;
        }
        Append(r);
        Append(' ');
        Append(g);
        Append(' ');
        Append(b);
        Append(" RG\n");
    }


    /**   
     * Set color for nonstroking operations
     */
    internal void SetBrushColor(
            double r, double g, double b) {
        if (brush_color[0] == r &&
                brush_color[1] == g &&
                brush_color[2] == b) {
            return;
        } else {
            brush_color[0] = r;
            brush_color[1] = g;
            brush_color[2] = b;
        }
        Append(r);
        Append(' ');
        Append(g);
        Append(' ');
        Append(b);
        Append(" rg\n");
    }


    internal void setDefaultLineWidth() {
        Append(0.0);
        Append(" w\n");
    }


    internal void SetLinePattern(String pattern) {
        if (pattern.Equals(line_pattern)) {
            return;
        } else {
            line_pattern = pattern;
        }
        if (pattern.StartsWith("[")) {
            Append(pattern);
        } else {
            int dash = 0;
            int space = 0;
            for (int i = 0; i < pattern.Length; i++) {
                if (pattern[i] == '-') {
                    dash++;
                } else {
                    space++;
                }
            }
            if (dash == 0 || space == 0) {
                Append("[] 0");
            } else {
                Append("[" + dash/2 + " " + space/2 + "] 0");
            }
        }
        Append(" d\n");
    }


    internal void setDefaultLinePattern() {
        Append("[] 0");
        Append(" d\n");
    }


    internal void setPenWidth(double width) {
        if (pen_width == width) {
            return;
        }
        pen_width = width;
        Append(pen_width);
        Append(" w\n");
    }


    internal void moveTo(double x, double y) {
        Append(x);
        Append(' ');
        Append(height - y);
        Append(" m\n");
    }


    internal void lineTo(double x, double y) {
        Append(x);
        Append(' ');
        Append(height - y);
        Append(" l\n");
    }


    internal void closePath() {
        Append("h\n");
    }


    internal void strokePath() {
        Append("S\n");
    }


    internal void fillPath() {
        Append("f\n");
    }


    internal void drawPath(
            List<Point> list, char operand) {
        if (list.Count < 2) {
            throw new Exception(
                    "The Path object must contain at least 2 points");
        }
        Point point = list[0];
        moveTo(point.x, point.y);
        int numOfCurvePoints = 0;
        for (int i = 1; i < list.Count; i++) {
            point = list[i];
            if (point.isCurvePoint) {
                Append(point.x);
                Append(' ');
                Append(height - point.y);
                if (numOfCurvePoints < 2) {
                    Append(' ');
                    numOfCurvePoints++;
                } else {
                    Append(" c\n");
                    numOfCurvePoints = 0;
                }
            } else {
                lineTo(point.x, point.y);
            }
        }
        if (numOfCurvePoints != 0) {
            throw new Exception(
                    "Invalid number of curve points in the Path object");
        }
        Append(operand);
        Append('\n');
    }


    internal void drawBezierCurve(
            List<Point> list, char operand) {
        Point point = list[0];
        moveTo(point.x, point.y);
        for (int i = 1; i < list.Count; i++) {
            point = list[i];
            Append(point.x);
            Append(' ');
            Append(height - point.y);
            if (i % 3 == 0) {
                Append(" c\n");
            } else {
                Append(' ');
            }
        }

        Append(operand);
        Append('\n');
    }


    internal void drawCircle(
            double x,
            double y,
            double r,
            char operand) {
        List<Point> list = new List<Point>();

        Point point = new Point();
        point.x = x;
        point.y = y - r;
        list.Add(point);    // Starting point

        point = new Point();
        point.x = x + 0.55*r;
        point.y = y - r;
        list.Add(point);
        point = new Point();
        point.x = x + r;
        point.y = y - 0.55*r;
        list.Add(point);
        point = new Point();
        point.x = x + r;
        point.y = y;
        list.Add(point);

        point = new Point();
        point.x = x + r;
        point.y = y + 0.55*r;
        list.Add(point);
        point = new Point();
        point.x = x + 0.55*r;
        point.y = y + r;
        list.Add(point);
        point = new Point();
        point.x = x;
        point.y = y + r;
        list.Add(point);

        point = new Point();
        point.x = x - 0.55*r;
        point.y = y + r;
        list.Add(point);
        point = new Point();
        point.x = x - r;
        point.y = y + 0.55*r;
        list.Add(point);
        point = new Point();
        point.x = x - r;
        point.y = y;
        list.Add(point);

        point = new Point();
        point.x = x - r;
        point.y = y - 0.55*r;
        list.Add(point);
        point = new Point();
        point.x = x - 0.55*r;
        point.y = y - r;
        list.Add(point);
        point = new Point();
        point.x = x;
        point.y = y - r;
        list.Add(point);

        drawBezierCurve(list, operand);
    }


    internal void drawPoint(Point p) {
        if (p.shape == Point.INVISIBLE) return;

        List<Point> list = null;
        Point point = null;

        if (p.shape == Point.CIRCLE) {
            if (p.fill_shape) {
                drawCircle(p.x, p.y, p.r, 'f');
            } else {
                drawCircle(p.x, p.y, p.r, 'S');
            }
        } else if (p.shape == Point.DIAMOND) {
            list = new List<Point>();
            point = new Point();
            point.x = p.x;
            point.y = p.y - p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x + p.r;
            point.y = p.y;
            list.Add(point);
            point = new Point();
            point.x = p.x;
            point.y = p.y + p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x - p.r;
            point.y = p.y;
            list.Add(point);
            if (p.fill_shape) {
                drawPath(list, 'f');
            } else {
                drawPath(list, 's');
            }
        } else if (p.shape == Point.BOX) {
            list = new List<Point>();
            point = new Point();
            point.x = p.x - p.r;
            point.y = p.y - p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x + p.r;
            point.y = p.y - p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x + p.r;
            point.y = p.y + p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x - p.r;
            point.y = p.y + p.r;
            list.Add(point);
            if (p.fill_shape) {
                drawPath(list, 'f');
            } else {
                drawPath(list, 's');
            }
        } else if (p.shape == Point.PLUS) {
            drawLine(p.x - p.r, p.y, p.x + p.r, p.y);
            drawLine(p.x, p.y - p.r, p.x, p.y + p.r);
        } else if (p.shape == Point.UP_ARROW) {
            list = new List<Point>();
            point = new Point();
            point.x = p.x;
            point.y = p.y - p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x + p.r;
            point.y = p.y + p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x - p.r;
            point.y = p.y + p.r;
            list.Add(point);
            if (p.fill_shape) {
                drawPath(list, 'f');
            } else {
                drawPath(list, 's');
            }
        } else if (p.shape == Point.DOWN_ARROW) {
            list = new List<Point>();
            point = new Point();
            point.x = p.x - p.r;
            point.y = p.y - p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x + p.r;
            point.y = p.y - p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x;
            point.y = p.y + p.r;
            list.Add(point);
            if (p.fill_shape) {
                drawPath(list, 'f');
            } else {
                drawPath(list, 's');
            }
        } else if (p.shape == Point.LEFT_ARROW) {
            list = new List<Point>();
            point = new Point();
            point.x = p.x + p.r;
            point.y = p.y + p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x - p.r;
            point.y = p.y;
            list.Add(point);
            point = new Point();
            point.x = p.x + p.r;
            point.y = p.y - p.r;
            list.Add(point);
            if (p.fill_shape) {
                drawPath(list, 'f');
            } else {
                drawPath(list, 's');
            }
        } else if (p.shape == Point.RIGHT_ARROW) {
            list = new List<Point>();
            point = new Point();
            point.x = p.x - p.r;
            point.y = p.y - p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x + p.r;
            point.y = p.y;
            list.Add(point);
            point = new Point();
            point.x = p.x - p.r;
            point.y = p.y + p.r;
            list.Add(point);
            if (p.fill_shape) {
                drawPath(list, 'f');
            } else {
                drawPath(list, 's');
            }
        } else if (p.shape == Point.H_DASH) {
            drawLine(p.x - p.r, p.y, p.x + p.r, p.y);
        } else if (p.shape == Point.V_DASH) {
            drawLine(p.x, p.y - p.r, p.x, p.y + p.r);
        } else if (p.shape == Point.X_MARK) {
            drawLine(p.x - p.r, p.y - p.r, p.x + p.r, p.y + p.r);
            drawLine(p.x - p.r, p.y + p.r, p.x + p.r, p.y - p.r);
        } else if (p.shape == Point.MULTIPLY) {
            drawLine(p.x - p.r, p.y - p.r, p.x + p.r, p.y + p.r);
            drawLine(p.x - p.r, p.y + p.r, p.x + p.r, p.y - p.r);
            drawLine(p.x - p.r, p.y, p.x + p.r, p.y);
            drawLine(p.x, p.y - p.r, p.x, p.y + p.r);
        } else if (p.shape == Point.STAR) {
            double angle = Math.PI / 10;
            double sin18 = Math.Sin(angle);
            double cos18 = Math.Cos(angle);
            double a = p.r * cos18;
            double b = p.r * sin18;
            double c = 2 * a * sin18;
            double d = 2 * a * cos18 - p.r;
            list = new List<Point>();
            point = new Point();
            point.x = p.x;
            point.y = p.y - p.r;
            list.Add(point);
            point = new Point();
            point.x = p.x + c;
            point.y = p.y + d;
            list.Add(point);
            point = new Point();
            point.x = p.x - a;
            point.y = p.y - b;
            list.Add(point);
            point = new Point();
            point.x = p.x + a;
            point.y = p.y - b;
            list.Add(point);
            point = new Point();
            point.x = p.x - c;
            point.y = p.y + d;
            list.Add(point);
            if (p.fill_shape) {
                drawPath(list, 'f');
            } else {
                drawPath(list, 's');
            }
        }
    }


    internal void SetTextRenderingMode(int mode) {
        if (mode == 0 || mode == 1
                || mode == 2 || mode == 3
                || mode == 4 || mode == 5
                || mode == 6 || mode == 7) {
            this.renderingMode = mode;
        } else {
            throw new Exception("Invalid text rendering mode: " + mode);
        }
    }


    internal void SetTextDirection(int degrees) {
        if (degrees > 360) degrees %= 360;
        if (degrees == 0) {
            writingMode = "1 0 0 1 ";
        } else if (degrees == 90) {
            writingMode = "0 1 -1 0 ";
        } else if (degrees == 180) {
            writingMode = "-1 0 0 -1 ";
        } else if (degrees == 270) {
            writingMode = "0 -1 1 0 ";
        } else if (degrees == 360) {
            writingMode = "1 0 0 1 ";
        } else {
            double sinOfAngle = Math.Sin(degrees * (Math.PI / 180));
            double cosOfAngle = Math.Cos(degrees * (Math.PI / 180));
            StringBuilder sb = new StringBuilder();
            sb.Append(cosOfAngle);
            sb.Append(' ');
            sb.Append(sinOfAngle);
            sb.Append(' ');
            sb.Append(-sinOfAngle);
            sb.Append(' ');
            sb.Append(cosOfAngle);
            sb.Append(' ');
            writingMode = sb.ToString().Replace(',', '.');
        }
    }


    internal void Append(String str) {
        for (int i = 0; i < str.Length; i++) {
            buf.WriteByte((byte) str[i]);
        }
    }


    internal void Append(int num) {
        Append(num.ToString());
    }


    internal void Append(double val) {
        Append(val.ToString().Replace(',', '.'));
    }


    internal void Append(char ch) {
        buf.WriteByte((byte) ch);
    }


    internal void Append(byte b) {
        buf.WriteByte(b);
    }

}   // End of Page.cs
}   // End of namespace PDFjet.NET
