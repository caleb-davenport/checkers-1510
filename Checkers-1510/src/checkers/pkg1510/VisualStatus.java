/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers.pkg1510;

import static checkers.pkg1510.Checkers1510.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

/**
 * 
 * @author Caleb Davenport
 */
public class VisualStatus extends GridPane {
    private int BORDER_WIDTH = 4;
    private int STATUS_WIDTH = 196; //200 - BORDER_WIDTH
    Label currentPlayer = new Label("RED");
    VisualStatus() {
        configureStatusPanel();
        setBackground();
        currentPlayer.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
        currentPlayer.setTextFill(Color.web("F00"));
        super.add(currentPlayer, 1, 0);
        super.add(new Rectangle(25, 25, Color.web("00F")), 1, 1);
        if (DEBUG) debug();
    }
    public final void updatePlayer() {
        if (Checkers1510.PlayerIsRed) {
            currentPlayer.setText("RED");
            currentPlayer.setTextFill(Color.web("F00"));
        }
        else {
            currentPlayer.setText("BLACK");
            currentPlayer.setTextFill(Color.web("000"));
        }
    }
    private void setBackground() {
        Color bg = Color.web("EEE");
        Color border = Color.web("555");
        for (int i = 0; i < 4; ++i) {
            Rectangle rect = new Rectangle(STATUS_WIDTH, 100);
            Rectangle side = new Rectangle(BORDER_WIDTH, 100);
            rect.setFill(bg);
            rect.setMouseTransparent(true);
            side.setFill(border);
            side.setMouseTransparent(true);
            super.add(rect, 1, i);
            super.add(side, 0, i);
        }
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
        ColumnConstraints border = new ColumnConstraints();
        border.setMinWidth(BORDER_WIDTH);
        border.setPrefWidth(BORDER_WIDTH);
        border.setMaxWidth(BORDER_WIDTH);
        this.getColumnConstraints().add(border);
        ColumnConstraints main = new ColumnConstraints();
        main.setMinWidth(STATUS_WIDTH);
        main.setPrefWidth(STATUS_WIDTH);
        main.setMaxWidth(STATUS_WIDTH);
        main.setHalignment(HPos.CENTER);
        this.getColumnConstraints().add(main);
    }
    private void debug() {
        this.setGridLinesVisible(true);
    }
}
