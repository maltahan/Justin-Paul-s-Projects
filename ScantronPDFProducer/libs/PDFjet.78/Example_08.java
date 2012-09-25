import java.lang.*;
import java.io.*;
import java.util.*;

import com.pdfjet.*;


/**
 *  Example_08.java
 *
 */
public class Example_08 {

    public Example_08() throws Exception {

        FileOutputStream fos = new FileOutputStream("Example_08.pdf");

        PDF pdf = new PDF(fos);

        Font f1 = new Font(pdf, "Helvetica-Bold");
        f1.setSize(7.0);
        Font f2 = new Font(pdf, "Helvetica");
        f2.setSize(7.0);
        Font f3 = new Font(pdf, "Helvetica-BoldOblique");
        f3.setSize(7.0);

        Page page = new Page(pdf, Letter.PORTRAIT);

        Table table = new Table(f1, f2);
        List<List<Cell>> tableData = getData(
        		"data/world-communications.txt", "|", Table.DATA_HAS_2_HEADER_ROWS, f1, f2);
        table.setData(tableData, Table.DATA_HAS_2_HEADER_ROWS);
        
        table.setLineWidth(0.2);
        table.setPosition(70.0, 30.0);
        table.setTextColorInRow(6, RGB.BLUE);
        table.setTextColorInRow(39, RGB.RED);
        table.setTextFontInRow(26, f3, 7);
        table.removeLineBetweenRows(0, 1);  
        table.autoAdjustColumnWidths();
        table.setColumnWidth(0, 120);
        table.rightAlignNumbers();
        int numOfPages = table.getNumberOfPages(page);
        while (true) {
            table.drawOn(page);
            // TO DO: Draw "Page 1 of N" here
            if (!table.hasMoreData()) break;
            page = new Page(pdf, Letter.PORTRAIT);
        }

        pdf.flush();
        fos.close();
    }
    
    
    public List<List<Cell>> getData(
            String fileName,
            String delimiter,
            int numOfHeaderRows,
            Font f1,
            Font f2) throws Exception {

        List<List<Cell>> tableData = new ArrayList<List<Cell>>();

        int currentRow = 0;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        while ((line = reader.readLine()) != null) {
            List<Cell> row = new ArrayList<Cell>();
            String[] cols = null;
            if (delimiter.equals("|")) {
                cols = line.split("\\|", -1);
            } else if (delimiter.equals("\t")) {
                cols = line.split("\t", -1);
            } else {
                throw new Exception(
                		"Only pipes and tabs can be used as delimiters");
            }
            for (int i = 0; i < cols.length; i++) {
                Cell cell = new Cell(f2, cols[i].trim());
                if (currentRow < numOfHeaderRows) {
                    cell.setFont(f1);
                }
                row.add(cell);
            }
            tableData.add(row);
            currentRow++;
        }
        reader.close();

        appendMissingCells(tableData, f2);
        
        return tableData;
    }
    

    private void appendMissingCells(List<List<Cell>> tableData, Font f2) {
        List<Cell> firstRow = tableData.get(0);
        int numOfColumns = firstRow.size();
        for (int i = 0; i < tableData.size(); i++) {
            List<Cell> dataRow = tableData.get(i);
            int dataRowColumns = dataRow.size();
            if (dataRowColumns < numOfColumns) {
                for (int j = 0; j < (numOfColumns - dataRowColumns); j++) {
                    dataRow.add(new Cell(f2));
                }
                dataRow.get(dataRowColumns - 1).setColspan((numOfColumns - dataRowColumns) + 1);
            }
        }
    }


    public static void main(String[] args) {
        try {
            new Example_08();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}   // End of Example_08.java
