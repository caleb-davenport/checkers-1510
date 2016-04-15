package checkers.pkg1510;

import static checkers.pkg1510.Checkers1510.*;
import static checkers.pkg1510.VisualBoard.*;
import com.sun.javafx.tk.Toolkit;
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
    Label currentPlayer = new Label(player1Name);
    Label winner = new Label("Wins!");
    Label notice = new Label("");
    Button newGame = new Button("New Game");
    Button saveGame = new Button("Save");
    Button loadGame = new Button("Load");
   
    static String player1Name = "Player1";
    static String player2Name = "Player2";
    
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
        double textWidth = statusBound.getWidth();
        
        if (Checkers1510.PlayerIsBlack) {
            currentPlayer.setText(player1Name);
            currentPlayer.setTextFill(Color.web("000"));
            textWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth(player1Name, currentPlayer.getFont());
        } else {
            currentPlayer.setText(player2Name);
            currentPlayer.setTextFill(Color.web("F00"));
            textWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth(player2Name, currentPlayer.getFont());
        }

        double scale = statusBound.getWidth()/textWidth;
        currentPlayer.setScaleX(scale);
        currentPlayer.setScaleY(scale);
        
        currentPlayer.setText(PlayerIsBlack ? player1Name : player2Name);
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
        
        BorderPane buttons = new BorderPane();
        buttons.setPadding(new Insets(20, 0, 20, 20));
        
        newGame.setMaxWidth (STATUS_WIDTH * 0.7);
        saveGame.setMaxWidth(STATUS_WIDTH * 0.7);
        loadGame.setMaxWidth(STATUS_WIDTH * 0.7);
        
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(newGame, saveGame, loadGame);
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
        player1Name = pl1Name;
    }
    public void setPl2Name (String pl2Name) {
        player2Name = pl2Name;
    }        
}
