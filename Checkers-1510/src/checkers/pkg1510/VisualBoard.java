
package checkers.pkg1510;

import static checkers.pkg1510.Checkers1510.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;

public class VisualBoard extends GridPane {
    static VisualTile[][] tiles = new VisualTile[8][8];
    static int[] activeCoord = new int[2];
    
    VisualBoard() {
        colorBoard();
        configureBoardLayout();
        redrawPieces();
        if (DEBUG) debug();
    }
    public final void redrawPieces() { //Run this at the end of every turn!
        Board.Square square;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                square = gameBoard.squareAt(row, col);
                for (Node node : this.getChildren()) {
                    if (node instanceof VisualPiece && 
                            GridPane.getColumnIndex(node) != null) {
                        if (GridPane.getColumnIndex(node) == col
                                && GridPane.getRowIndex(node) == row) {
                            this.getChildren().remove(node);
                            break;
                        }
                    }
                }
                if (square.isValid() && square.isOccupied()) {
                    VisualPiece piece = new VisualPiece(square);
                    this.add(piece, col, row);
                }
            }
        }
        if (DEBUG) gameBoard.printDebugBoard();
    }
    private void configureBoardLayout() {
       for (int i=0; i<8; i++) {
	  RowConstraints rowConstraints = new RowConstraints();
	  rowConstraints.setMinHeight(50);
	  rowConstraints.setPrefHeight(50);
	  rowConstraints.setMaxHeight(50);
	  rowConstraints.setValignment(VPos.CENTER);
	  getRowConstraints().add(rowConstraints);
          
          ColumnConstraints colConstraints = new ColumnConstraints();
	  colConstraints.setMinWidth(50);
	  colConstraints.setPrefWidth(50);
	  colConstraints.setMaxWidth(50);
	  colConstraints.setHalignment(HPos.CENTER);
	  getColumnConstraints().add(colConstraints);
       }
    }

    private void colorBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tiles[row][col] = new VisualTile(col, row);
                add(tiles[row][col], col, row);
            }
        }
    }
    private static boolean anyHighlight() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (tiles[row][col].active == true) {
                    return true;
                }
            }
        }
        return false; 
    }
    public static void highlight(int row, int col) {
        Board.Square square = gameBoard.squareAt(row, col);
        if (square.isValid() && square.isOccupied() && !(square.isBlack() 
                ^ Checkers1510.PlayerIsBlack)) {
            unHighlightAll();
            tiles[row][col].highlight(true);
            activeCoord[0] = row;
            activeCoord[1] = col;
        }else if (anyHighlight() && square.isValid() && !square.isOccupied()){
            Checkers1510.performMove(new Move(activeCoord[0], activeCoord[1],
               row, col, gameBoard.squareAt(activeCoord[0], activeCoord[1])));
        }
    }
    public static void unHighlightAll() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tiles[row][col].highlight(false);
            }
        }
    }
    private void debug() {
       setGridLinesVisible(true); //debug only
    }
}
