import java.awt.Graphics2D;
import java.io.PrintStream;
import java.util.Scanner;

public interface ILifeModel {
    /**
     * An interface for a model for Conway's Game of Life.
     * You need to keep track of the on or off state of each
     * cell in the game.
     * 
     * You should include a constructor that allows the user
     * to set the number of rows and columns and the size of
     * each cell in the game.
     */
    
    /**
     * returns the correct width of the Life canvas
     */
    int getCanvasWidth();
    
    /**
     * returns the correct height of the Life canvas
     */
    int getCanvasHeight();
    
    /**
     * contains code to draw the canvas correctly
     * you'll probably want to use the Graphics2D
     * fillRect() method
     */
    void drawOnCanvas(Graphics2D g);

    /**
     * given the x and y coordinate where a user clicked,
     * changes the state of the correct cell. If it was on,
     * change it to off, if off, change to on.
     */
    void handleClick(int x, int y);

    /**
     * sets all cells to off
     */
    void clearCells();

    /**
     * determines whether the screen is considered "wrapped",
     * i.e. if the top row considers the bottom row a neighbor
     * and the left-most row considers the right-most a neighbor
     * and vice versa
     */
    void setIsScreenWrapped(boolean isScreenWrapped);

    /**
     * changes the state of the model to the next state
     * off cells with exactly three on neighbors turn on
     * on cells with exactly two or three neighbors stay on
     * all other cells end up off
     */
    void step();

    /**
     * prints a text file from which the current state can be
     * determined. The format is
     * +-------------------+
     * |rows cols cellSize |
     * |r1 c1              |
     * |r2 c2              |
     * |...                |
     * |rn cn              |
     * |                   |
     * +-------------------+
     * where each cell at row r and column c is on
     * for each of the r's and c's
     */
    void printToFile(PrintStream out);

    /**
     * reads a text file in the form given by printToFile and
     * sets the state of this model appropriately
     */
    void readFromScanner(Scanner in);
}
