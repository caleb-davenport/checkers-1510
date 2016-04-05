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

/**
 * 
 * @author Caleb Davenport
 */
public class VisualStatus extends GridPane {
    VisualStatus() {
        configureStatusPanel();
        super.getChildren().add(new Rectangle(25, 25, Color.web("00F")));
        if (DEBUG) debug();
    }
    private void configureStatusPanel() {
       for (int i=0; i<4; i++) {
	  RowConstraints rowConstraints = new RowConstraints();
	  rowConstraints.setMinHeight(100);
	  rowConstraints.setPrefHeight(100);
	  rowConstraints.setMaxHeight(100);
	  rowConstraints.setValignment(VPos.CENTER);
	  this.getRowConstraints().add(rowConstraints);
       }
          ColumnConstraints colConstraints = new ColumnConstraints();
	  colConstraints.setMinWidth(200);
	  colConstraints.setPrefWidth(200);
	  colConstraints.setMaxWidth(200);
	  colConstraints.setHalignment(HPos.CENTER);
	  this.getColumnConstraints().add(colConstraints);
    }
    private void debug() {
        this.setGridLinesVisible(true);
    }
}
