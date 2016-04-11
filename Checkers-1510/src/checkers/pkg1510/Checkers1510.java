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
    
    public static boolean DEBUG = true; //ENABLE/DISABLE DEBUG MODE
    static Board gameBoard = new Board("BoardSetups\\kingJumpTest.txt");
    static VisualBoard visualBoard = new VisualBoard();
    static VisualStatus status = new VisualStatus();
    static boolean PlayerIsRed = true;
    
    @Override
    public void start(Stage primaryStage) {
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
        gameBoard.kingPieces();
        visualBoard.redrawPieces();
    }
    
    /*public Move[] getAvailMoves(int y, int x) {
        int[][] spcSeed;
        Move[] possibleMoves;
        
        if (gameBoard.squareAt(y, x).isKing()) {
            spcSeed = new int[][]{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        } else {
            spcSeed = new int[][]{{1, 1}, {1, -1}};
        } 
        for (int i = 0; i < spcSeed.length; ++i) {
            if (!gameBoard.squareAt(y + spcSeed[i, 0], x + spcSeed[i, 1]).isOccupied()) {
            possibleMoves;
        }
        }
    }*/
    
}
