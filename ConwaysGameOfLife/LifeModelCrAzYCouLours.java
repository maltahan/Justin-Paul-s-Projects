import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintStream;
import java.util.Scanner;


public class LifeModelCrAzYCouLours implements ILifeModel {
    private int rows;
    private int cols;
    private int cellSize;
    private int[][] grid;
    private boolean screenWrapped;
    
    public LifeModelCrAzYCouLours(int rows, int cols, int cellSize) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.grid = new int[rows][cols];
    }
    
    public int getCanvasWidth() {
        return this.rows * this.cellSize;
    }
    
    public int getCanvasHeight() {
        return this.cols * this.cellSize;
    }
    
    public void drawOnCanvas(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getCanvasWidth(), this.getCanvasHeight());
        g.setColor(Color.BLACK);
        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                g.setColor(Color.BLACK);
                if (grid[i][j] == 1) {
                    g.setColor(Color.BLACK);
                }
                else if (grid[i][j] == 2) {
                    g.setColor(Color.DARK_GRAY );
                }
                else if (grid[i][j] == 3) {
                    g.setColor(Color.GRAY);
                }
                else if (grid[i][j] == 4) {
                    g.setColor(Color.BLUE);
                }
                else if (grid[i][j] == 5) {
                    g.setColor(Color.GREEN);
                }
                else if (grid[i][j] == 6) {
                    g.setColor(Color.CYAN);
                }
                else if (grid[i][j] == 7) {
                    g.setColor(Color.RED);
                }
                else if (grid[i][j] == 8) {
                    g.setColor(Color.ORANGE);
                }
                else if (grid[i][j] == 9) {
                    g.setColor(Color.PINK);
                }
                else if (grid[i][j] == 10) {
                    g.setColor(Color.MAGENTA);
                }
                else if (grid[i][j] == 11) {
                    g.setColor(Color.LIGHT_GRAY);
                }
                else if (grid[i][j] > 11) {
                    g.setColor(Color.YELLOW);
                }
                if (grid[i][j] != 0) g.fillRect(i * this.cellSize, j * this.cellSize,
                    this.cellSize,this.cellSize);
            }
        }
    }
        //do a for loop that checks if the cell is on, then if it is,
        //color it a certain color by using fillRect()
      //  for (int i = 0; i < this.rows * this.cols; i++) {
        //    if (
        //}
    
    //PRINTFILE
    //for int row ...
    // spaces[0].length; col++
    //sop (spaces[row][col] + " ");
    //}
    //sopln
    
    public void handleClick(int x, int y) {
        x = x / this.cellSize;
        y = y / this.cellSize;
        /*if(grid[x][y] == 0) grid[x][y] = 1;
        if(grid[x][y] != 0) grid[x][y] = 0;
        */
       grid[x][y]++;
       if(grid[x][y] > 1) grid[x][y] = 0;
    }
    
    public void clearCells() {
        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                grid[i][j] = 0;
            }
        }
    }
   
    public void setIsScreenWrapped(boolean isScreenWrapped) {
        this.screenWrapped = isScreenWrapped;
    }
    
    public void step(){
        int[][] jank = new int[this.rows][this.cols];
        int neigh = 0;
        
        for(int i = 0 ; i < this.rows; i++){
            for(int j = 0 ; j < this.cols; j++){
                if(!screenWrapped) neigh = this.Public(i, j);
                if(screenWrapped) neigh = this.Private(i, j);
                
                
                
                if(neigh <=1) jank[i][j] = 0;
                if(neigh >= 4) jank[i][j] = 0;
                if(neigh == 2 || neigh == 3) jank[i][j] = grid[i][j] + 1;
                if(neigh == 2 && grid[i][j] == 0) jank[i][j] = 0;
            }
        }
        this.grid = jank;
        
    }
    
    private int Public(int i, int j){
        int neigh = 0;
        if(j != 0){
            if(grid[i][j-1] > 0) neigh++;
            if(i != (this.rows - 1) && grid[i+1][j-1] > 0) neigh++;
            if(i != 0 && grid[i-1][j-1] > 0) neigh++;
        }
        if(j != this.cols-1){
            if(grid[i][j+1] > 0) neigh++;
            if(i != this.rows -1 && grid[i+1][j+1] > 0) neigh++;
            if(i != 0 && grid[i-1][j+1] > 0) neigh++;
        }
        if(i != this.rows-1 && grid[i+1][j] > 0) neigh++;
        if((i != 0) && (grid[i-1][j] > 0)) neigh++;
        return neigh;
    }
    
    public int Private(int i, int j){
        int neigh = 0;
        if(j != 0){
            if(grid[i][j-1] > 0) neigh++;
            if(i != (this.rows - 1) && grid[i+1][j-1] > 0) neigh++;
            if(i == (this.rows - 1) && grid[this.rows-1][j-1] > 0) neigh++;
            
            if(i != 0 && grid[i-1][j-1] > 0) neigh++;
            if(i == 0 && grid[this.rows-1][j-1] > 0) neigh++;
        }
        
        if(j == 0){
            if(grid[i][this.cols-1] > 0) neigh++;
            if(i != (this.rows - 1) && grid[i+1][cols-1] > 0) neigh++;
            if(i == (this.rows - 1) && grid[0][cols-1] > 0) neigh++;
            
            if(i != 0 && grid[i-1][cols-1] > 0) neigh++;
            if(i == 0 && grid[this.rows-1][cols-1] > 0) neigh++;
        }
        if(j != this.cols-1){
            if(grid[i][j+1] > 0) neigh++;
            if(i != this.rows -1 && grid[i+1][j+1] > 0) neigh++;
            if(i == this.rows -1 && grid[0][j+1] > 0) neigh++;
            
            if(i != 0 && grid[i-1][j+1] > 0) neigh++;
            if(i == 0 && grid[this.rows-1][j+1] > 0) neigh++;
        }
        if(j == this.cols-1){
            if(grid[i][0] > 0) neigh++;
            if(i != this.rows -1 && grid[i+1][0] > 0) neigh++;
            if(i == this.rows -1 && grid[0][0] > 0) neigh++;
            
            if(i != 0 && grid[i-1][0] > 0) neigh++;
            if(i == 0 && grid[this.rows-1][0] > 0) neigh++;
        }
        if(i != this.rows-1 && grid[i+1][j] > 0) neigh++;
        if(i == this.rows-1 && grid[0][j] > 0) neigh++;
        
        if((i != 0) && (grid[i-1][j] > 0)) neigh++;
        if((i == 0) && (grid[this.rows-1][j] > 0)) neigh++;
        
        return neigh;
    }
    
    public void printToFile(PrintStream out) {
         for (int i = 0; i < this.rows; i++) {
             for (int j = 0; j < this.cols; j++) {
                 if(this.grid[i][j] > 0) out.println(i + " " + j + " " + grid[i][j]);
             }
             System.out.println();
         }
    }
    // spaces[0].length; col++
    //sop (spaces[row][col] + " ");
    //}
    //sopln
     
    public void readFromScanner(Scanner in){
        this.clearCells();
        while (in.hasNextInt()){
            this.grid[in.nextInt()][in.nextInt()] = 1;
        }
    }
}