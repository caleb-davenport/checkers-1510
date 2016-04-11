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
    
    public static void performMove(Move move/*int startx, int starty, int stopx, int stopy*/) {
        if (gameBoard.anyJump() && move.getMoveType() == Move.MoveType.jump) {
            gameBoard.takePiece(move.getStartY(), move.getStartX(), move.getEndY(), move.getEndX());
            if (gameBoard.canJump(move.getEndY(), move.getEndX(), gameBoard.squareAt(move.getEndY(), move.getEndX()).isKing())) {
                VisualBoard.unHighlightAll();
                VisualBoard.highlight(move.getEndY(), move.getEndX());
                visualBoard.redrawPieces();
                return;
            }
            endTurn();
        } else if (!gameBoard.anyJump() && move.getMoveType() == Move.MoveType.step) {
            gameBoard.movePiece(move.getStartY(), move.getStartX(), move.getEndY(), move.getEndX());
            endTurn();
        }
        //gameBoard.movePiece(startx, starty, stopx, stopy);
        //if (DEBUG) System.out.println(startx + ", " + starty + ", " + stopx + ", " + stopy); 
    }
    public static void endTurn() {
        PlayerIsRed = !PlayerIsRed;
        status.updatePlayer();
        gameBoard.kingPieces();
        VisualBoard.unHighlightAll();
        visualBoard.redrawPieces();
        if(isWon())
            System.out.println("You Win!");
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
    public static boolean isWon(){
        boolean win;
        win = true;
        
        if(PlayerIsRed){
          for (int row = 0; row < 8; row++) {
             for (int col = 0; col < 8; col++) {
                 if(gameBoard.squareAt(row, col).isRed() || !gameBoard.squareAt(row, col).isValid()) //change to if you have no moves
                     continue;
                 else;
                 win = false;
             }   
           } 
        }
        if(!PlayerIsRed){
          for (int row = 0; row < 8; row++) {
             for (int col = 0; col < 8; col++) {
                 if(!gameBoard.squareAt(row, col).isRed() || !gameBoard.squareAt(row, col).isValid())
                     continue;
                 else;
                 win = false;
             }   
           } 
        }
        return win;
    }
}
