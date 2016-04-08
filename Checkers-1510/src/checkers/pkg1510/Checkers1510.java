/*
 *
 * Honors Project: Checkers
 * Joshua Blohm, Caleb Davenport, Ashley Fredenburg, Roan Martin-Hayden
 * EECS 1510-091: Dr. Ledgard
 * 
 */
package checkers.pkg1510;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Checkers1510 extends Application {
    
    public static boolean DEBUG = false; //ENABLE/DISABLE DEBUG MODE
    static Board gameBoard = new Board("BoardSetups\\takePieceTest.txt");
    static VisualBoard visualBoard = new VisualBoard();
    static VisualStatus status = new VisualStatus();
    static boolean PlayerIsRed = true;
    
    @Override
    public void start(Stage primaryStage) {
        
        visualBoard.redrawPieces();
        gameBoard.takePiece(5, 2, 3, 0);
        visualBoard.redrawPieces();
        
        BorderPane root = new BorderPane();
        root.setCenter(visualBoard);
        root.setRight(status);
        
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
    public static void performMove(int startx, int starty, int stopx, int stopy) {
        gameBoard.movePiece(startx, starty, stopx, stopy);
        if (DEBUG) System.out.println(startx + ", " + starty + ", " + stopx + ", " + stopy);
        
        PlayerIsRed = !PlayerIsRed;
        status.updatePlayer();
    }
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
