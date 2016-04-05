/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers.pkg1510;

import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @author Caleb Davenport
 */
public class VisualPiece extends Group {
    static Circle circle;
    static Circle kingCircle;
    public VisualPiece(Board.Square square) {
        super();
        if (square.isRed()) {
            circle = new Circle(25, 25, 20, Color.web("F00"));
        } else {
            circle = new Circle(25, 25, 20, Color.web("000"));
        }
        circle.setMouseTransparent(true);
        super.getChildren().add(circle);
        if (square.isKing()) {
            kingCircle = new Circle(25, 25, 8, Color.web("FFF"));
            kingCircle.setMouseTransparent(true);
            super.getChildren().add(kingCircle);
        }
    }
}
