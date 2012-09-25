import java.lang.*;
import java.io.*;
import java.util.*;

import com.pdfjet.*;


/**
 *  Example_13.java
 *
 */
public class Example_13 {

    public Example_13() throws Exception {

        FileOutputStream fos = new FileOutputStream("Example_13.pdf");

        PDF pdf = new PDF(fos);

        Font f1 = new Font(pdf, "Helvetica-Bold");
        Font f2 = new Font(pdf, "Helvetica");
        f1.setSize(7.0);
        f2.setSize(7.0);
        
        List<List<Cell>> tableData = new ArrayList<List<Cell>>();
        BufferedReader reader = new BufferedReader(
                new FileReader("data/winter-2009.txt"));
        String line;
        while (( line = reader.readLine()) != null) {
            List<Cell> row = new ArrayList<Cell>();
            String[] columns = line.split("\\|", -1);
            for ( int i = 0; i < columns.length; i++ ) {
                row.add(new Cell(f2, columns[i]));
            }
            tableData.add(row);
        }
        reader.close();

        Table table = new Table(f1, f2);
        table.setData(tableData, Table.DATA_HAS_2_HEADER_ROWS);
        table.setPosition(100.0, 50.0);
        table.setCellMargin(2.0);

        setFontForRow(table, 0, f1);
        setFontForRow(table, 1, f1);

        table.autoAdjustColumnWidths();
        table.removeLineBetweenRows(0, 1);  

        Cell cell = table.getCellAt(1, 1);
        cell.border.top = true;

        cell = table.getCellAt(1, 2);
        cell.border.top = true;

        cell = table.getCellAt(0, 1);
        cell.setColSpan( 2 );
        cell.setTextAlignment(Align.CENTER);


        List<Cell> column = table.getColumn(7);
        for ( int i = 0; i < column.size(); i++ ) {
            cell = column.get(i);
            cell.setTextAlignment(Align.CENTER);
        }


        column = table.getColumn(4);
        for ( int i = 2; i < column.size(); i++ ) {
            cell = column.get(i);
            try {
                cell.setTextAlignment(Align.CENTER);
                if ( Integer.valueOf( cell.getText()) > 40 ) {
                    cell.setBgColor( new double[] { 0.0, 0.85, 0.0 } );
                } else {
                    cell.setBgColor( new double[] { 1.0, 1.0, 0.0 } );
                }
            }
            catch (Exception e) {
            }
        }

        setBgColorForRow(table, 0, new double[] { 0.85, 0.85, 0.85 });
        setBgColorForRow(table, 1, new double[] { 0.85, 0.85, 0.85 });
        
        table.setColumnWidth(3, 10);
        blankOutColumn(table, 3);

        table.setColumnWidth(8, 10);
        blankOutColumn(table, 8);


        Page page = new Page(pdf, Letter.PORTRAIT);
        int numOfPages = table.getNumberOfPages(page);
        int pageNumber = 1;
        while (true) {
            table.drawOn(page);

            TextLine text = new TextLine(f1);
            text.setText("Page " + pageNumber++ + " of " + numOfPages);
            text.setPosition(300.0, 780.0);
            text.drawOn(page);

            if (!table.hasMoreData()) break;
            page = new Page(pdf, Letter.PORTRAIT);
        }

        pdf.flush();
        fos.close();
    }

    
    public void blankOutColumn(Table table, int index) throws Exception {
        List<Cell> column = table.getColumn(index);
        for ( int i = 0; i < column.size(); i++ ) {
            Cell cell = column.get(i);
            cell.setBgColor(new double[] { 1.0, 1.0, 1.0 });
            cell.border.top = false;
            cell.border.bottom = false;
        }
    }


    public void setBgColorForRow(
            Table table, int index, double[] color) throws Exception {
        List<Cell> row = table.getRow(index);
        for (int i = 0; i < row.size(); i++) {
            Cell cell = row.get(i);
            cell.setBgColor(color);
        }
    }


    public void setFontForRow(
            Table table, int index, Font font) throws Exception {
        List<Cell> row = table.getRow(index);
        for (int i = 0; i < row.size(); i++) {
            row.get(i).setFont(font);
        }
    }

    
    public static void main(String[] args) {
        try {
            new Example_13();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}   // End of Example_13.java
