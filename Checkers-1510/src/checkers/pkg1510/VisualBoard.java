/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers.pkg1510;

import static checkers.pkg1510.Checkers1510.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.*;

/**
 * 
 * @author Caleb Davenport
 */
public class VisualBoard extends GridPane {
    VisualBoard() {
        colorBoard();
        configureBoardLayout();
        if (DEBUG) debug();
    }
    public void redrawPieces() { // !!!Run this at the end of every turn!!!
        Board.Square square;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                square = gameBoard.squareAt(row, col);
                for (Node node : this.getChildren()) {
                    if (node instanceof VisualPiece && GridPane.getColumnIndex(node) != null) {
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
    private Color squareColor(int col, int row) {
        if (gameBoard.squareAt(col, row).isValid()) {
            return Color.web("EEE");
        } else {
            return Color.web("999");
        }
    }
    private void colorBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle rect = new Rectangle(50, 50, squareColor(col, row));
                add(rect, col, row);
            }
        }
    }
    private void debug() {
       setGridLinesVisible(true); //debug only
    }
}
