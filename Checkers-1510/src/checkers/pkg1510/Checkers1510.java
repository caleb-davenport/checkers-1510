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
    
    public static final String BOARD_LOCATION = "BoardSetups\\StandardGame.txt";
    public static boolean DEBUG = false; //ENABLE/DISABLE DEBUG MODE
    static Board gameBoard = new Board(BOARD_LOCATION);
    static VisualBoard visualBoard = new VisualBoard();
    static VisualStatus status = new VisualStatus();
    static boolean PlayerIsBlack = true;
    
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
    
    public static void performMove(Move move) {
        if (gameBoard.anyJump() && move.MoveType() == Move.MoveType.jump) {
            if (gameBoard.getJumpPiece(move).isBlack() ^ PlayerIsBlack) {
                gameBoard.takePiece(move.StartY(), move.StartX(), move.EndY(), move.EndX());
            }
            if (gameBoard.canMove(move.EndY(), move.EndX(), gameBoard.squareAt(move.EndY(), move.EndX()).isKing(), true)) {
                VisualBoard.unHighlightAll();
                VisualBoard.highlight(move.EndY(), move.EndX());
                visualBoard.redrawPieces();
                return;
            }
            endTurn();
        } else if (!gameBoard.anyJump() && move.MoveType() == Move.MoveType.step) {
            gameBoard.movePiece(move.StartY(), move.StartX(), move.EndY(), move.EndX());
            endTurn();
        } else {
            if (gameBoard.anyJump()) status.jumpAvailable();
            else status.illegalMove();
        }
        if (DEBUG) System.out.println(move.StartY() + ", " + move.StartX() + ", " + move.EndY() + ", " + move.EndX()); 
    }
    public static void endTurn() {
        PlayerIsBlack = !PlayerIsBlack;
        if (isWon()) {
            status.winner();
            PlayerIsBlack = !PlayerIsBlack;
        }
        status.updatePlayer();
        gameBoard.kingPieces();
        VisualBoard.unHighlightAll();
        visualBoard.redrawPieces();
        status.clearNotice();
        if (isWon()) PlayerIsBlack = !PlayerIsBlack;
    }
    public static boolean isWon() {
      return !(gameBoard.anyStep() || gameBoard.anyJump());  
    }
}
