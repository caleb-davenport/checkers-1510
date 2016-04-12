/*
 *
 * Honors Project: Checkers
 * Joshua Blohm, Caleb Davenport, Ashley Fredenburg, Roan Martin-Hayden
 * EECS 1510-091: Dr. Ledgard
 * 
 */
package checkers.pkg1510;

import java.util.Iterator;
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
}
