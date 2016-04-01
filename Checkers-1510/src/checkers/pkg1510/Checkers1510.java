/*
 *
 * Honors Project: Checkers
 * Joshua Blohm, Caleb Davenport, Ashley Fredenburg, Roan Martin-Hayden
 * EECS 1510-091: Dr. Ledgard
 * 
 */
package checkers.pkg1510;

import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;

public class Checkers1510 extends Application {
    
    static Board gameBoard = new Board();
    
    @Override
    public void start(Stage primaryStage) {
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gameBoard.setupBoard("BoardSetups\\standardGame.txt");
        gameBoard.printDebugBoard();
        gameBoard.kingPiece(0, 1);
        gameBoard.printDebugBoard();
        System.exit(0);
    }
    
    /**
     * Function to set up game based on a profile held in text file.
     * 
     * @param profile File to load for artifical start of game
     */
    private void init(File profile /*=newGame*/) {
        //read file
        
        //implement changes to board
    };
    
    /**
     * Function to determine a winner, or lack thereof
     * 
     * @return Who has won or null for no winner
     */
    private String isWon() {
        String winner = null;
        return winner;
    }
    
    /**
     * Convert move from String input
     * 
     * @return Move as char[x1,y1,x2,y2]
     */
    private char[] getMove(String move) {
        char[] loc = new char[4];
        return loc;
    }
    
    /**
     * Function to test if a move is valid
     * 
     * @return Boolean value of whether move is valid or not
     */
    private boolean isValid() {
        boolean valid = false;
        return valid;
    }
     /**
      * Function to update game state variables
      * @param move Move to be added in char[x1,y1, x2, y2] format
      */
    private void update(char[] move) {
    }
}
