/*
 *
 * Honors Project: Checkers
 * Joshua Blohm, Caleb Davenport, Ashley Fredenburg, Roan Martin-Hayden
 * EECS 1510-091: Dr. Ledgard
 * 
 */
package checkers.pkg1510;

import java.io.File;
import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
       //visualBoard.setGridLinesVisible(true);
       configureBoardLayout(visualBoard);
       colorBoard(visualBoard);
        BorderPane root = new BorderPane(visualBoard);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       gameBoard.setupBoard();
       gameBoard.printDebugBoard();
       launch(args);
       System.exit(0);
    }
    private void colorBoard(GridPane board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.add(new Rectangle(50, 50, squareColor(col, row)), col, row);
            }
        }
    }
    
    private Color squareColor(int col, int row) {
        if (gameBoard.coord(col, row).isValid()) {
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
