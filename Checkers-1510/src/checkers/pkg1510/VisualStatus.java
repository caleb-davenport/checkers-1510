
package checkers.pkg1510;

import static checkers.pkg1510.Checkers1510.*;
import static checkers.pkg1510.VisualBoard.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class VisualStatus extends GridPane {
    private final int BORDER_WIDTH = 4;
    private final int STATUS_WIDTH = 196; //200 - BORDER_WIDTH
    Label currentPlayer = new Label("RED");
    Label winner = new Label("Wins!");
    Label notice = new Label("");
    Button newGame = new Button("New Game");
    VisualStatus() {
        configureStatusPanel();
        setBackground();
        currentPlayer.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
        currentPlayer.setTextFill(Color.web("F00"));
        winner.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
        winner.setTextFill(Color.web("F00", 0));
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            PlayerIsRed = true;
            winner.setTextFill(Color.web("F00", 0));
            gameBoard.setupBoard(BOARD_LOCATION);
            visualBoard.redrawPieces();
            updatePlayer();
            unHighlightAll();
            }
        });
        super.add(currentPlayer, 1, 0);
        super.add(winner, 1, 1);
        super.add(newGame, 1, 2);
        super.add(notice, 1, 3);
        
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

    public final void jumpAvailable() {
        notice.setText("You have a jump available!");
    }
    public final void clearNotice() {
        notice.setText("");
    }
    public void winner() {
        if (Checkers1510.PlayerIsRed)     
            winner.setTextFill(Color.web("F00"));
        else
            winner.setTextFill(Color.web("000"));
    }
        
}
