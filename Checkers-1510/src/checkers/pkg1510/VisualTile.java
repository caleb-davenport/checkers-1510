/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers.pkg1510;
import static checkers.pkg1510.Checkers1510.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.*;

/**
 * 
 * @author Caleb Davenport
 */
public class VisualTile extends Group {
    int col, row;
    boolean isValid;
    boolean isOccupied;
    Rectangle main = new Rectangle();
    Rectangle highlight = new Rectangle(50, 50, Color.web("FFF", 0));
    public boolean active = false;
    
    VisualTile(int col, int row) {
        this.col = col;
        this.row = row;
        this.isValid = gameBoard.squareAt(col, row).isValid();
        if (isValid) this.isOccupied = gameBoard.squareAt(col, row).isOccupied();
        
        highlight.setMouseTransparent(true);
        main.setWidth(50);
        main.setHeight(50);
        main.setId("main");
        normalizeColor();
        super.getChildren().addAll(main, highlight);
        TileHandler h = new TileHandler(col, row);
        main.setOnMousePressed(h);
    }
    public final void normalizeColor() {
        if (isValid) {
            main.setFill(Color.web("EEE"));
        } else {
            main.setFill(Color.web("999"));
        }
    }
    public boolean highlight(boolean x) {
        if (x) {
            highlight.setFill(Color.web("4AF", 0.7));
            active = true;
        } else {
            highlight.setFill(Color.web("FFF", 0));
            active = false;
        }
        return true;
    }
    
    class TileHandler implements EventHandler<MouseEvent> {
    int col, row;
    TileHandler(int col, int row) {
        this.col = col;
        this.row = row;
    }
    @Override
    public void handle(MouseEvent e) {
        highlight(row, col);
        if (DEBUG) debug();
    }
    private void highlight(int row, int col) {
        if (active) VisualBoard.unHighlightAll();
        else VisualBoard.highlight(row, col);
    }
    private void debug() {
        System.out.println("You clicked a tile at (" + col + ", " + row + ")");
        System.out.println(Checkers1510.gameBoard.jumpAvailable(col, row));
    }
}
}
