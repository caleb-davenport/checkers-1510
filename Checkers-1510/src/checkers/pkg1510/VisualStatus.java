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
    Label currentPlayer = new Label("BLACK");
    Label winner = new Label("Wins!");
    Label notice = new Label("");
    Button newGame = new Button("New Game");
    
    static Text Player1Name = new Text("Player1");
    static Text Player2Name = new Text("Player2");
    
    static Bounds statusBound;
    
    VisualStatus() {
        configureStatusPanel();
        setBackground();
        currentPlayer.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
        currentPlayer.setTextFill(Color.web("000"));
        winner.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
        winner.setTextFill(Color.web("000", 0));
        notice.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
        notice.setTextAlignment(TextAlignment.CENTER);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            PlayerIsBlack = true;
            winner.setTextFill(Color.web("000", 0));
            gameBoard.setupBoard(BOARD_LOCATION);
            visualBoard.redrawPieces();
            updatePlayer();
            unHighlightAll();
            clearNotice();
            }
        });
        saveGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            gameBoard.saveBoard();
            }
        });
        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            // insret function of button   
            System.out.println("Load succesful");
            }
        });
        super.add(currentPlayer, 1, 0);
        super.add(winner, 1, 1);
        super.add(newGame, 1, 2);
        super.add(saveGame, 1, 3);
        super.add(loadGame, 1, 4);
        super.add(notice, 1, 5);
        
        if (DEBUG) debug();
    }
    public final void updatePlayer() {
        if (Checkers1510.PlayerIsRed) {
            currentPlayer.setText(Player1Name.getText());
            currentPlayer.setTextFill(Color.web("F00"));
        }
        else {
            currentPlayer.setText(Player2Name.getText());
            currentPlayer.setTextFill(Color.web("000"));
        }
        else {
            currentPlayer.setText("RED");
            currentPlayer.setTextFill(Color.web("F00"));
        }
    }
    private void setBackground() {
        Color bg = Color.web("EEE");
        Color border = Color.web("555");
        for (int i = 0; i < 6; ++i) {
            Rectangle rect = new Rectangle(STATUS_WIDTH, 100);
            statusBound = rect.getBoundsInLocal();
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
        for (int i=0; i<2; i++){
            RowConstraints row1 = new RowConstraints();
            row1.setMinHeight(100);
            row1.setPrefHeight(100);
            row1.setMaxHeight(100);
            this.getRowConstraints().add(row1); 
        }
        for (int i=0; i<3; i++){
            RowConstraints buttons = new RowConstraints();
            buttons.setMinHeight(100/3);
            buttons.setPrefHeight(100/3);
            buttons.setMaxHeight(100/3);
            this.getRowConstraints().add(buttons);
        }
        RowConstraints row4 = new RowConstraints();
        row4.setMinHeight(100);
        row4.setPrefHeight(100);
        row4.setMaxHeight(100);
        this.getRowConstraints().add(row4);
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
        notice.setText("You have a\njump available!");
    }
    public final void illegalMove() {
        notice.setText("That's an illegal move!");
    }
    public final void clearNotice() {
        notice.setText("");
    }
    public void winner() {
        if (Checkers1510.PlayerIsBlack)     
            winner.setTextFill(Color.web("F00"));
        else
            winner.setTextFill(Color.web("000"));
    }
    
    public void setPl1Name (String pl1Name) {
        Player1Name.setText(pl1Name);
        Bounds textBound = Player1Name.getBoundsInLocal();
        double scalex = statusBound.getWidth()/textBound.getWidth();
        Player1Name.setScaleX(scalex);
    }
    public void setPl2Name (String pl2Name) {
        Player2Name.setText(pl2Name);
        Bounds textBound = Player2Name.getBoundsInLocal();
        double scalex = statusBound.getWidth()/textBound.getWidth();
        Player2Name.setScaleX(scalex);
    }
        
}
