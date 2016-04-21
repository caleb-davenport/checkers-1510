package checkers.pkg1510;

import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VisualPiece extends Group {
    static Circle circle;
    static Circle kingCircle;
    public VisualPiece(Board.Square square) {
        super();
        if (square.isBlack()) {
            circle = new Circle(25, 25, 20, Color.web("000"));
        } else {
            circle = new Circle(25, 25, 20, Color.web("F00"));
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
