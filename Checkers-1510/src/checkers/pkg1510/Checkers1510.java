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
    public static boolean DEBUG = true; //ENABLE/DISABLE DEBUG MODE
    static Board gameBoard = new Board(BOARD_LOCATION);
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
    
    public static void performMove(Move move) {
        if (gameBoard.anyJump() && move.getMoveType() == Move.MoveType.jump) {
            if (gameBoard.getJumpPiece(move).isRed() ^ PlayerIsRed) {
                gameBoard.takePiece(move.getStartY(), move.getStartX(), move.getEndY(), move.getEndX());
            }
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
        } else {
            status.jumpAvailable();
        }
        if (DEBUG) System.out.println(move.getStartY() + ", " + move.getStartX() + ", " + move.getEndY() + ", " + move.getEndX()); 
    }
    public static void endTurn() {
        PlayerIsRed = !PlayerIsRed;
        if (isWon()) {
            System.out.println("You Win!");
            status.winner();
            PlayerIsRed = !PlayerIsRed;
        }
        status.updatePlayer();
        gameBoard.kingPieces();
        VisualBoard.unHighlightAll();
        visualBoard.redrawPieces();
        status.clearNotice();
        if (isWon()) PlayerIsRed = !PlayerIsRed;
    }
    public static boolean isWon() {
      return !(gameBoard.anyStep() || gameBoard.anyJump());  
    }
}
