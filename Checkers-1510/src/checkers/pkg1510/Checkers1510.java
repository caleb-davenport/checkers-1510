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
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Checkers1510 extends Application {
    
    static Board gameBoard = new Board();
    
    @Override
    public void start(Stage primaryStage) {
        GridPane visualBoard = new GridPane();
       
        GridPane status = new GridPane();
        BorderPane.setAlignment(status, Pos.TOP_LEFT);
        status.getChildren().add(new Rectangle(25, 25, Color.web("00F")));
        configureStatusPanel(status);
        visualBoard.setGridLinesVisible(true);
        status.setGridLinesVisible(true);
        configureBoardLayout(visualBoard);
        colorBoard(visualBoard);
        redrawPieces(visualBoard);
        BorderPane root = new BorderPane(visualBoard);

        root.setRight(status);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gameBoard.setupBoard("BoardSetups\\takePieceTest.txt");
        gameBoard.printDebugBoard();
        launch(args);
        gameBoard.takePiece(5, 2, 3, 0);
        System.out.println("\n\n\n");
        gameBoard.printDebugBoard();
        System.exit(0);
    }
    private void colorBoard(GridPane board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.add(new Rectangle(50, 50, squareColor(col, row)), col, row);
            }
        }
    }
    
    private void redrawPieces(GridPane board) { // !!!Run this at the end of every turn!!!
        Board.Square square;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                square = gameBoard.squareAt(row, col);
                for (Node node : board.getChildren()) {
                    if (node instanceof Circle
                            && GridPane.getColumnIndex(node) == col
                            && GridPane.getRowIndex(node) == row) {
                        board.getChildren().remove(node);
                        break;
                    }
                }
                if (square.isValid() && square.isOccupied()) {
                    Circle circle;
                    Circle kingCircle;
                    if (square.isRed()) {
                        circle = new Circle(25, 25, 20, Color.web("F00"));
                    } else {
                        circle = new Circle(25, 25, 20, Color.web("000"));
                    }
                    circle.setMouseTransparent(true);
                    board.add(circle, col, row);
                    if (square.isKing()) {
                        kingCircle = new Circle(25, 25, 8, Color.web("FFF"));
                        kingCircle.setMouseTransparent(true);
                        board.add(kingCircle, col, row);
                    }
                }
            }
        }
    }
    
    private Color squareColor(int col, int row) {
        if (gameBoard.squareAt(col, row).isValid()) {
            return Color.web("EEE");
        } else {
            return Color.web("999");
        }
    }
    private void configureBoardLayout(GridPane board) {
       for (int i=0; i<8; i++) {
	  RowConstraints rowConstraints = new RowConstraints();
	  rowConstraints.setMinHeight(50);
	  rowConstraints.setPrefHeight(50);
	  rowConstraints.setMaxHeight(50);
	  rowConstraints.setValignment(VPos.CENTER);
	  board.getRowConstraints().add(rowConstraints);
          
          ColumnConstraints colConstraints = new ColumnConstraints();
	  colConstraints.setMinWidth(50);
	  colConstraints.setPrefWidth(50);
	  colConstraints.setMaxWidth(50);
	  colConstraints.setHalignment(HPos.CENTER);
	  board.getColumnConstraints().add(colConstraints);
       }
    }
    private void configureStatusPanel(GridPane status) {
       for (int i=0; i<4; i++) {
	  RowConstraints rowConstraints = new RowConstraints();
	  rowConstraints.setMinHeight(100);
	  rowConstraints.setPrefHeight(100);
	  rowConstraints.setMaxHeight(100);
	  rowConstraints.setValignment(VPos.CENTER);
	  status.getRowConstraints().add(rowConstraints);
       }
          ColumnConstraints colConstraints = new ColumnConstraints();
	  colConstraints.setMinWidth(200);
	  colConstraints.setPrefWidth(200);
	  colConstraints.setMaxWidth(200);
	  colConstraints.setHalignment(HPos.CENTER);
	  status.getColumnConstraints().add(colConstraints);
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
