using System;
using System.IO;
using System.Collections;using System.Collections.Generic;

using PDFjet.NET;


/**
 *  Example_13.cs
 *
 */
public class Example_13 {

    public Example_13() {

        FileStream fos = new FileStream("Example_13.pdf", FileMode.Create);
        BufferedStream bos = new BufferedStream(fos);

        PDF pdf = new PDF(bos);
        pdf.setCompressor(Compressor.ORIGINAL_ZLIB);

        Font f1 = new Font(pdf, "Helvetica-Bold");
        Font f2 = new Font(pdf, "Helvetica");
        f1.SetSize(7.0);
        f2.SetSize(7.0);
        
        List<List<Cell>> tableData = new List<List<Cell>>();
        StreamReader reader = new StreamReader(
                new FileStream("data/winter-2009.txt", FileMode.Open));
        String line;
        while (( line = reader.ReadLine()) != null) {
            List<Cell> row = new List<Cell>();
            String[] columns = line.Split(new Char[] {'|'});
            for ( int i = 0; i < columns.Length; i++ ) {
                row.Add(new Cell(f2, columns[i]));
            }
            tableData.Add(row);
        }
        reader.Close();

        Table table = new Table(f1, f2);
        table.SetData(tableData, Table.DATA_HAS_2_HEADER_ROWS);
        table.SetPosition(100.0, 50.0);
        table.setCellMargin(2.0);
        
        table.RemoveLineBetweenRows(0, 1);  

        Cell cell3 = table.GetCellAt(1, 1);
        cell3.border.top = true;

        cell3 = table.GetCellAt(1, 2);
        cell3.border.top = true;

        SetFontForRow(table, 0, f1);
        SetFontForRow(table, 1, f1);
        
        table.AutoAdjustColumnWidths();

        List<Cell> column = table.GetColumn(7);
        for ( int i = 0; i < column.Count; i++ ) {
            Cell cell = column[i];
            cell.SetTextAlignment(Align.CENTER);
        }

        column = table.GetColumn(4);
        for ( int i = 2; i < column.Count; i++ ) {
            Cell cell = column[i];
            try {
                cell.SetTextAlignment(Align.CENTER);
                if ( Int32.Parse( cell.GetText()) > 40 ) {
                    cell.SetBgColor( new double[] { 0.0, 0.85, 0.0 } );
                } else {
                    cell.SetBgColor( new double[] { 1.0, 1.0, 0.0 } );
                }
            }
            catch (Exception e) {
                Console.WriteLine(e);
            }
        }

        Cell cell2 = table.GetCellAt(0, 1);
        cell2.SetColSpan( 2 );
        cell2.SetTextAlignment(Align.CENTER);
        
        SetBgColorForRow(table, 0, new double[] { 0.85, 0.85, 0.85 });
        SetBgColorForRow(table, 1, new double[] { 0.85, 0.85, 0.85 });
        
        table.SetColumnWidth(3, 10);
        blankOutColumn(table, 3);

        table.SetColumnWidth(8, 10);
        blankOutColumn(table, 8);

        Page page = new Page(pdf, Letter.PORTRAIT);
        int numOfPages = table.GetNumberOfPages(page);
        int pageNumber = 1;
        while (true) {
            table.DrawOn(page);

            TextLine text = new TextLine(f1);
            text.SetText("Page " + pageNumber++ + " of " + numOfPages);
            text.SetPosition(300.0, 780.0);
            text.DrawOn(page);

            if (!table.HasMoreData()) break;
            page = new Page(pdf, Letter.PORTRAIT);
        }

        pdf.Flush();
        bos.Close();
    }

    
    public void blankOutColumn(Table table, int index) {
        List<Cell> column = table.GetColumn(index);
        for ( int i = 0; i < column.Count; i++ ) {
            Cell cell = column[i];
            cell.SetBgColor(new double[] { 1.0, 1.0, 1.0 });
            cell.border.top = false;
            cell.border.bottom = false;
        }
    }


    public void SetBgColorForRow(Table table, int index, double[] color) {
        List<Cell> row = table.GetRow(index);
        for (int i = 0; i < row.Count; i++) {
            Cell cell = row[i];
            cell.SetBgColor(color);
        }
    }


    public void SetFontForRow(Table table, int index, Font font) {
        List<Cell> row = table.GetRow(index);
        for (int i = 0; i < row.Count; i++) {
            row[i].SetFont(font);
        }
    }

    
    public static void Main(String[] args) {
        try {
            new Example_13();
        } catch (Exception e) {
            Console.WriteLine(e.StackTrace);
        }
    }

}   // End of Example_13.cs
