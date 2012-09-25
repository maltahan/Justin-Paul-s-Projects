using System;
using System.IO;
using System.Collections;using System.Collections.Generic;

using PDFjet.NET;


/**
 *  Example_08.cs
 *
 */
public class Example_08 {

    public Example_08() {

        FileStream fos = new FileStream("Example_08.pdf", FileMode.Create);
        BufferedStream bos = new BufferedStream(fos);

        PDF pdf = new PDF(bos);
        pdf.setCompressor(Compressor.ORIGINAL_ZLIB);

        // Before you enable this flag please read README.ZLIB.TXT
        // in the 'optional' directory.

        Font f1 = new Font(pdf, "Helvetica-Bold");
        f1.SetSize(7.0);
        Font f2 = new Font(pdf, "Helvetica");
        f2.SetSize(7.0);
        Font f3 = new Font(pdf, "Helvetica-BoldOblique");
        f3.SetSize(7.0);

        Page page = new Page(pdf, Letter.PORTRAIT);

        Table table = new Table(f1, f2);
        List<List<Cell>> tableData = GetData(
        		"data/world-communications.txt", "|", Table.DATA_HAS_2_HEADER_ROWS, f1, f2);
        table.SetData(tableData, Table.DATA_HAS_2_HEADER_ROWS);
        
        table.SetLineWidth(0.2);
        table.SetPosition(70.0, 30.0);
        table.SetTextColorInRow(6, RGB.BLUE);
        table.SetTextColorInRow(39, RGB.RED);
        table.SetTextFontInRow(26, f3, 7);
        table.RemoveLineBetweenRows(0, 1);  
        table.AutoAdjustColumnWidths();
        table.SetColumnWidth(0, 120);
        table.RightAlignNumbers();
        int numOfPages = table.GetNumberOfPages(page);
        while (true) {
            table.DrawOn(page);
            // TO DO: Draw "Page 1 of N" here
            if (!table.HasMoreData()) break;
            page = new Page(pdf, Letter.PORTRAIT);
        }

        pdf.Flush();
        bos.Close();
    }
    
    
    public List<List<Cell>> GetData(
            String fileName,
            String delimiter,
            int numOfHeaderRows,
            Font f1,
            Font f2) {

        List<List<Cell>> tableData = new List<List<Cell>>();

        int currentRow = 0;
        StreamReader reader = new StreamReader(fileName);
        String line = null;
        while ((line = reader.ReadLine()) != null) {
            List<Cell> row = new List<Cell>();
            String[] cols = null;
            if (delimiter.Equals("|")) {
                cols = line.Split(new Char[] {'|'});
            } else if (delimiter.Equals("\t")) {
                cols = line.Split(new Char[] {'\t'});
            } else {
                throw new Exception(
                		"Only pipes and tabs can be used as delimiters");
            }
            for (int i = 0; i < cols.Length; i++) {
                Cell cell = new Cell(f2, cols[i].Trim());
                if (currentRow < numOfHeaderRows) {
                    cell.SetFont(f1);
                }
                row.Add(cell);
            }
            tableData.Add(row);
            currentRow++;
        }
        reader.Close();

        appendMissingCells(tableData, f2);
        
        return tableData;
    }
    

    private void appendMissingCells(List<List<Cell>> tableData, Font f2) {
        List<Cell> firstRow = tableData[0];
        int numOfColumns = firstRow.Count;
        for (int i = 0; i < tableData.Count; i++) {
            List<Cell> dataRow = tableData[i];
            int dataRowColumns = dataRow.Count;
            if (dataRowColumns < numOfColumns) {
                for (int j = 0; j < (numOfColumns - dataRowColumns); j++) {
                    dataRow.Add(new Cell(f2));
                }
                dataRow[dataRowColumns - 1].setColspan(
                        (numOfColumns - dataRowColumns) + 1);
            }
        }
    }


    public static void Main(String[] args) {
        try {
            new Example_08();
        } catch (Exception e) {
            Console.WriteLine(e.StackTrace);
        }
    }

}   // End of Example_08.cs
