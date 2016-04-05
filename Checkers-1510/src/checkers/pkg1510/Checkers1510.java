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
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Checkers1510 extends Application {
    
    static Board gameBoard = new Board();
    static boolean debug = false;
    
    @Override
    public void start(Stage primaryStage) {
        VisualBoard visualBoard = new VisualBoard();
       
        GridPane status = new GridPane();
        status.getChildren().add(new Rectangle(25, 25, Color.web("00F")));
        configureStatusPanel(status);
        status.setGridLinesVisible(true);
        
        visualBoard.redrawPieces();
        gameBoard.takePiece(5, 2, 3, 0);
        visualBoard.redrawPieces();
        gameBoard.printDebugBoard();
        
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
        gameBoard.setupBoard("BoardSetups\\takePieceTest.txt");
        gameBoard.printDebugBoard();
        launch(args);
        System.exit(0);
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
