/**
 *  Table.cs
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


namespace PDFjet.NET {
public class Table {

    public static int DATA_HAS_0_HEADER_ROWS = 0;
    public static int DATA_HAS_1_HEADER_ROWS = 1;
    public static int DATA_HAS_2_HEADER_ROWS = 2;
    public static int DATA_HAS_3_HEADER_ROWS = 3;
    public static int DATA_HAS_4_HEADER_ROWS = 4;
    public static int DATA_HAS_5_HEADER_ROWS = 5;
    public static int DATA_HAS_6_HEADER_ROWS = 6;
    public static int DATA_HAS_7_HEADER_ROWS = 7;
    public static int DATA_HAS_8_HEADER_ROWS = 8;
    public static int DATA_HAS_9_HEADER_ROWS = 9;

    private int rendered = 0;

    private List<List<Cell>> tableData = null;
    private int numOfHeaderRows = 0;

    private double x1 = 0.0;
    private double y1 = 0.0;
    private double w1 = 50.0;
    private double h1 = 30.0;

    private double lineWidth = 0.2;
    private double[] lineColor = RGB.BLACK;
    private double margin = 1.0;
    private double bottom_margin = 30.0;

    private Font f1 = null;     // head font
    private Font f2 = null;     // body font

    private Exception e = null;


    public Table(Font f1, Font f2) {
        this.f1 = f1;
        this.f2 = f2;
        tableData = new List<List<Cell>>();
    }


    public void SetPosition(double x, double y) {
        this.x1 = x;
        this.y1 = y;
    }


    public void SetSize(double w, double h) {
        this.w1 = w;
        this.h1 = h;
    }


    public void SetLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }


    public void SetLineColor(double[] color) {
        this.lineColor = color;
    }


    public void SetLineColor(int[] rgb) {
        this.lineColor =
                new double[] {rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0};
    }


    public void SetCellPadding(double margin) {
        this.margin = margin;
    }


    public void setCellMargin(double margin) {
        this.margin = margin;
    }


    public void setBottomMargin(double bottom_margin) {
        this.bottom_margin = bottom_margin;
    }


    public void SetData(
            List<List<Cell>> tableData) {
        this.tableData = tableData;
    }


    public void SetData(
            List<List<Cell>> tableData, int numOfHeaderRows) {
        this.tableData = tableData;
        this.numOfHeaderRows = numOfHeaderRows;
        this.rendered = numOfHeaderRows;
    }

    
    public void AutoAdjustColumnWidths() {
        // Find the maximum text width for each column
        double[] max_col_widths = new double[tableData[0].Count];
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            for (int j = 0; j < row.Count; j++) {
                Cell cell = row[j];
                if (cell.colspan > 1) continue;
                cell.width = cell.font.StringWidth(cell.text);
                if (max_col_widths[j] == 0.0 ||
                        cell.width > max_col_widths[j]) {
                    max_col_widths[j] = cell.width;
                }
            }
        }

        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            for (int j = 0; j < row.Count; j++) {
                Cell cell = row[j];
                if (max_col_widths[j] != 0.0) {
                    cell.width = max_col_widths[j] + 3 * margin;
                } else {
                    cell.width = cell.font.body_height;
                }
            }
        }
    }


    public void RightAlignNumbers() {
        for (int i = numOfHeaderRows; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            for (int j = 0; j < row.Count; j++) {
                Cell cell = row[j];
                try {
                    Double.Parse(cell.text.Replace(",", ""));
                    cell.align = Align.RIGHT;
                } catch (Exception e) {
                    this.e = e;
                }
            }
        }
    }


    public void RemoveLineBetweenRows(int index1, int index2) {
        List<Cell> row = tableData[index1];
        Cell cell = null;
        for (int i = 0; i < row.Count; i++) {
            cell = row[i];
            cell.border.bottom = false;
        }
        row = tableData[index2];
        for (int i = 0; i < row.Count; i++) {
            cell = row[i];
            cell.border.top = false;
        }
    }


    public void SetTextAlignInColumn(
            int index, int alignment) {
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            if (index < row.Count) {
                row[index].align = alignment;
            }
        }
    }


    public void SetTextColorInColumn(
            int index, double[] color) {
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            if (index < row.Count) {
                row[index].brushColor = color;
            }
        }
    }


    public void SetTextColorInColumn(
            int index, int[] rgb) {
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            if (index < row.Count) {
                row[index].brushColor =
                        new double[] {rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0};
            }
        }
    }


    public void SetTextFontInColumn(
            int index, Font font, double size) {
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            if (index < row.Count) {
                row[index].font = font;
                row[index].font.SetSize(size);
            }
        }
    }


    public void SetTextColorInRow(
            int index, double[] color) {
        List<Cell> row = tableData[index];
        for (int i = 0; i < row.Count; i++) {
            row[i].brushColor = color;
        }
    }


    public void SetTextColorInRow(
            int index, int[] rgb) {
        List<Cell> row = tableData[index];
        for (int i = 0; i < row.Count; i++) {
            row[i].brushColor =
                    new double[] {rgb[0]/255.0, rgb[1]/255.0, rgb[2]/255.0};
        }
    }


    public void SetTextFontInRow(
            int index, Font font, double size) {
        List<Cell> row = tableData[index];
        for (int i = 0; i < row.Count; i++) {
            row[i].font = font;
            row[i].font.SetSize(size);
        }
    }


    [Obsolete("")]
    public void SetWidthForColumn(int index, double width) {
        SetColumnWidth(index, width);
    }


    public void SetColumnWidth(
            int index, double width) {
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            if (index < row.Count) {
                row[index].width = width;
            }
        }
    }


    public Cell GetCellAtRowColumn(
            int row, int col) {
        if (row >= 0) {
            return tableData[row][col];
        }
        return tableData[tableData.Count + row][col];
    }


    public List<Cell> GetRow(int index) {
        return tableData[index];
    }


    public List<Cell> GetColumn(int index) {
        List<Cell> column = new List<Cell>();
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            if (index < row.Count) {
                column.Add(row[index]);
            }
        }
        return column;
    }


    public int GetNumberOfPages(Page page) {
        int numOfPages = 1;
        int j = numOfHeaderRows;
        double cell_h = 0.0;

        while (j != tableData.Count) {
        	double y = y1;

        	for (int i = 0; i < numOfHeaderRows; i++) {
                Cell cell = tableData[i][0];
                cell_h = cell.font.body_height + 2 * margin;
                y += cell_h;
            }

            for (; j < tableData.Count; j++) {
                Cell cell = tableData[j][0];
                cell_h = cell.font.body_height + 2 * margin;
                y += cell_h;

                if ((y + cell_h) > (page.height - bottom_margin)) {
                    numOfPages++;
                	break;
                }
            }
        }

        return numOfPages;
    }


    public void DrawOn(Page page) {
        double x = x1;
        double y = y1;
        double cell_w = 0.0;
        double cell_h = 0.0;

        page.setPenWidth(lineWidth);
        page.SetPenColor(lineColor[0], lineColor[1], lineColor[2]);

        for (int i = 0; i < numOfHeaderRows; i++) {
            List<Cell> dataRow = tableData[i];
            for (int j = 0; j < dataRow.Count; j++) {
                Cell cell = dataRow[j];
                cell_h = cell.font.body_height + 2 * margin;
                cell_w = cell.width;
                for (int k = 1; k < cell.colspan; k++) {
                    cell_w += dataRow[++j].width;
                }

                page.SetBrushColor(
                        cell.brushColor[0],
                        cell.brushColor[1],
                        cell.brushColor[2]);
                cell.Paint(page, x, y, cell_w, cell_h, margin);

                x += cell_w;
            }
            x = x1;
            y += cell_h;
        }

        for (int i = rendered; i < tableData.Count; i++) {
            List<Cell> dataRow = tableData[i];
            for (int j = 0; j < dataRow.Count; j++) {
                Cell cell = dataRow[j];
                cell_h = cell.font.body_height + 2 * margin;
                cell_w = cell.width;
                for (int k = 1; k < cell.colspan; k++) {
                    cell_w += dataRow[++j].width;
                }

                page.SetBrushColor(
                        cell.brushColor[0],
                        cell.brushColor[1],
                        cell.brushColor[2]);
                cell.Paint(page, x, y, cell_w, cell_h, margin);

                x += cell_w;
            }
            x = x1;
            y += cell_h;

            if ((y + cell_h) > (page.height - bottom_margin)) {
                if (i == tableData.Count - 1) {
                    rendered = -1;
                } else {
                    rendered = i + 1;
                }
                return;
            }
        }

        rendered = -1;
    }


    public bool HasMoreData() {
        if (rendered == -1) {
            return false;
        }
        return true;
    }


    public double GetWidth() {
        double table_width = 0.0;
        List<Cell> row = tableData[0];
        for (int i = 0; i < row.Count; i++) {
            table_width += row[i].width;

        }
        return table_width;
    }


    public double GetHeight() {
        double table_height = 0.0;
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> row = tableData[i];
            table_height += row[0].height;

        }
        return table_height;
    }

}   // End of Table.cs
}   // End of namespace PDFjet.NET
