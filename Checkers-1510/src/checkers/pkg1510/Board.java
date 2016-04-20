
package checkers.pkg1510;

import static checkers.pkg1510.Checkers1510.*;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Board {

    public Square board[][] = new Square[8][8];
    // Squares are counted from bottom left to top right.
    // in indexing, first element is row, second is column: [row][column]
    
    Board(String pathStr) {
        setupBoard(pathStr);
        if (DEBUG) debug();
    }
    public enum Square {
        invalid  (-2, null,  null,  null,  false, "X"),
        empty    (-1, null,  null,  false, true,  "_"),
        blackSerf(0,  true, false, true,  true,  "b"),
        redSerf  (1,  false,  false, true,  true,  "r"),
        blackKing(2,  true, true,  true,  true,  "B"),
        redKing  (3,  false,  true,  true,  true,  "R");

        private final double rawNumber;   // number representing state
        private final Boolean isBlack; // f=black t=red Null=empty
        private final Boolean isKing; // f=not_king t=king Null=empty
        private final Boolean isOccupied; // f=empty t=occupied Null=invalid
        private final Boolean isValid; // f=invalid, t=valid
        private final String code;

        Square(double rawNumber, Boolean isBlack, Boolean isKing, 
                Boolean isOccupied, Boolean isValid, String code) {
            this.rawNumber  = rawNumber;
            this.isBlack      = isBlack;
            this.isKing     = isKing;
            this.isOccupied = isOccupied;
            this.isValid    = isValid;
            this.code       = code;
        }

        public double rawNumber()   { return rawNumber; }
        public Boolean isBlack()      { return isBlack; }
        public Boolean isKing()     { return isKing; }
        public Boolean isOccupied() { return isOccupied; }
        public Boolean isValid()    { return isValid; }
        @Override
        public String toString()    { return String.valueOf(code); }
    }

    public Square decode (String code) {
        switch (code) {
            case "X": return Square.invalid;
            case "_": return Square.empty;
            case "b": return Square.blackSerf;
            case "r": return Square.redSerf;
            case "B": return Square.blackKing;
            case "R": return Square.redKing;
            default: return null;
        }
    }
    
     /**
     * Loads board into program variables from text file
     * @param pathStr path to setup file. Pass "" for default game
     */
    public final void setupBoard (String pathStr) {
        Path path;
        //int returnVal;
        
        if (pathStr == null || pathStr.isEmpty()) {
            //Initialize board as empty, but with invalid and valid squares
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        board[i][j] = Square.invalid;
                    }
                    else {
                        board[i][j] = Square.empty;
                    }
                }
            }
            //returnVal = 0;
        } else {
            try {
                path = Paths.get(pathStr);
                List<String> input = Files.readAllLines(path);
                if (input.size() < 8) throw new Exception("Format Error");
                for (int i = 0; i < 8; ++i) {
                    String[] codes = input.get(i).split("\t");
                    for (int j = 0; j < 8; ++j) {
                        board[i][j] = decode(codes[j]);
                    }
                }
                try {
                    switch (input.get(8)) {
                        case "R":
                            if (PlayerIsBlack) endTurn();
                            break;
                        case "B":
                        default:
                            if (!PlayerIsBlack) endTurn();
                            break;
                    }
                    switch (input.get(9)) {
                        case "": break;
                        default: status.setPl1Name(input.get(9));
                    }
                    switch (input.get(10)) {
                        case "": break;
                        default: status.setPl2Name(input.get(10));
                    }
                } catch (Exception e) {
                    if (DEBUG) System.out.println("Couldn't load " +
                            "custom settings - Loading default");
                }
                //returnVal = 0;
            } catch (Exception ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE,
                        null, ex);
                System.err.println("ERROR: Loading board Setup, "
                        + "Loading default");
                setupBoard("");
                //returnVal = 1;
            }
        }
        //return returnVal;
    }
    public boolean saveBoard() {
        String s = "BoardSetups\\";
        s = s.concat("CHKRS");
        s = s + String.valueOf(System.currentTimeMillis());
        s = s.concat(".txt");
        try (PrintStream P = new PrintStream(s)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    P.print(squareAt(i, j).toString() + "\t");
                }
                P.println("");
            }
            if (PlayerIsBlack) P.println("B");
            else P.println("R");
            P.println(VisualStatus.player1Name);
            P.println(VisualStatus.player2Name);
            P.close();
            if (DEBUG) System.out.println("Game Saved.");
        } catch (Exception e) {
            System.err.println("ERROR: Couldn't save game");
            return false;
        }
        return true;
    }
    public boolean loadBoard() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open the checkers game");
        fileChooser.setInitialDirectory(new File("BoardSetups"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) return false;
        gameBoard.setupBoard(file.getPath());
        return true;
    }
    
    /**
     * @param y row of the board
     * @param x column of the board
     * @return state of the square as an enum)
     */
    public Square squareAt(int y, int x) {
        return board[y][x];
    }
    
    public void movePiece(int startx, int starty, int stopx, int stopy){
        if (board[startx][starty] == Square.redSerf)
            board[stopx][stopy] = Square.redSerf;
        
        if(board[startx][starty] == Square.redKing)
           board[stopx][stopy] = Square.redKing;
        
        if(board[startx][starty] == Square.blackSerf)
           board[stopx][stopy] = Square.blackSerf;
        
        if(board[startx][starty] == Square.blackKing)
           board[stopx][stopy] = Square.blackKing; 
            
        board[startx][starty] = Square.empty;
        
    }
    public Square getJumpPiece(Move move) {
        int takeny = (move.EndY() + move.StartY()) / 2;
        int takenx = (move.EndX() + move.StartX()) / 2;
        
        return squareAt(takeny, takenx);
    }
   
    public void printDebugBoard() {
       for (int i = 0; i < 8; i++) {
	  for (int j = 0; j < 8; j++) {
	     System.out.print(squareAt(i, j).toString() + "\t");
	  }
	  System.out.println("");
       }
        System.out.println("-----------------------------------------------");
    }
    
    public void kingPieces() {
        for (int i = 0; i < 8; ++i) {
            if (squareAt(0, i).isValid()) {
                if (squareAt(0, i).isOccupied()) {
                    if (squareAt(0, i).isBlack()) {
                        board[0][i] = Square.blackKing; 
                    }
                }
            }
            if (squareAt(7, i).isValid()) {
                if (squareAt(7, i).isOccupied()) {
                    if (!squareAt(7, i).isBlack()) {
                        board[7][i] = Square.redKing; 
                    }
                }
            }
        }
    }
    
    public void takePiece(int starty, int startx, int endy, int endx) {
        Square initSquare;
        
        int takeny;
        int takenx;
        
        initSquare = squareAt(starty, startx);
        if (initSquare.isValid() && initSquare.isOccupied()) {
            board[endy][endx] = decode(initSquare.code);
        }
        
        takeny = (endy + starty) / 2;
        takenx = (endx + startx) / 2;
        
        board[takeny][takenx] = Square.empty;
        board[starty][startx] = Square.empty;
    }
    
    public boolean canMove(int starty, int startx, boolean isKing,
            boolean jumpMove) {
        if (PlayerIsBlack || isKing) {
            for (int i = -1; i < 2; i = i + 2) {
                try {
                    if (!squareAt(starty - 1, startx + i).isOccupied() &&
                            !jumpMove) return true;
                    else if ((squareAt(starty - 1, startx + i).isBlack 
                            ^ PlayerIsBlack) && !squareAt(starty - 2,
                            startx + 2*i).isOccupied()) return true;
                } catch (Exception e) {}
            }
        }
        if (!PlayerIsBlack || isKing) {
            for (int i = -1; i < 2; i = i + 2) {
                try {
                    if (!squareAt(starty + 1, startx + i).isOccupied() &&
                            !jumpMove) return true;
                    else if ((squareAt(starty + 1, startx + i).isBlack 
                            ^ PlayerIsBlack) && !squareAt(starty + 2, 
                            startx + 2*i).isOccupied()) return true;
                } catch (Exception e) {}
            }
        }
        return false;
    }

    //step is considered to be a move from one tile to an adjacent one
    public boolean anyStep() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                try {
                    if ((!squareAt(i, j).isBlack() ^ PlayerIsBlack)
                            && canMove(i, j, squareAt(i, j).isKing(), false))
                        return true;
                } catch (Exception e) {
                }
            }
        }
        return false;
    }
    public boolean anyJump() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                try {
                    if ((!squareAt(i, j).isBlack() ^ PlayerIsBlack)
                            && canMove(i, j, squareAt(i, j).isKing(), true))
                        return true;
                } catch (Exception e) {
                }
            }
        }
        return false;
    }
    private void debug() {
        printDebugBoard();
    }
}
