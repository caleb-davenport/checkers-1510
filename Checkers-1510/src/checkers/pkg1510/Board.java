/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.pkg1510;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Shape;


/**
 *
 * @author rmartin-
 */
public class Board {

    public Square board[][] = new Square[8][8];
    // Squares are counted from bottom left to top right.
    // in indexing, first element is row, second is column: [row][column]

    public enum Square {
        invalid  (-2, null,  null,  null,  false, "x"),
        empty    (-1, null,  null,  false, true, "_"),
        blackSerf(0,  false, false, true,  true, "b"),
        redSerf  (1,  true,  false, true,  true, "r"),
        blackKing(2,  false, true,  true,  true, "B"),
        redKing  (3,  true,  true,  true,  true, "R");

        private final double rawNumber;   // number representing state
        private final Boolean isRed; // f=black t=red Null=empty
        private final Boolean isKing; // f=not_king t=king Null=empty
        private final Boolean isOccupied; // f=empty t=occupied Null=invalid
        private final Boolean isValid; // f=invalid, t=valid
        private final String code;

        Square(double rawNumber, Boolean isRed, Boolean isKing, Boolean isOccupied,
                Boolean isValid, String code) {
            this.rawNumber = rawNumber;
            this.isRed = isRed;
            this.isKing = isKing;
            this.isOccupied = isOccupied;
            this.isValid = isValid;
            this.code = code;
        }

        public double rawNumber() { return rawNumber; }
        public Boolean isRed() { return isRed; }
        public Boolean isKing() { return isKing; }
        public Boolean isOccupied() { return isOccupied; }
        public Boolean isValid() { return isValid; }
        public String toString() { return String.valueOf(code); }
    }
    
    /**
     * Returns square object from a given code
     */
    public Square decode (String code) {
        switch (code) {
            case "x": return Square.invalid;
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
     * @auth Roan
     * 
     * @param pathStr path to setup file. Pass "" for default game
     */
    public int setupBoard (String pathStr) {
        Path path;
        int returnVal;
        
        if (pathStr.isEmpty() || pathStr == null) {
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
            returnVal = 0;
        } else {
            try {
                path = Paths.get(pathStr);
                List<String> input = Files.readAllLines(path);
                if (input.size() != 8) throw new Exception("Format Error");
                for (int i = 0; i < 8; ++i) {
                    String[] codes = input.get(i).split("\t");
                    for (int j = 0; j < 8; ++j) {
                        board[i][j] = decode(codes[j]);
                    }
                }
                returnVal = 0;
            } catch (Exception ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("ERROR: Loading board Setup, Loading default");
                setupBoard("");
                returnVal = 1;
            }
        }
        return returnVal;
    }
    
    /**
     * Returns the state of an given square
     * @auth Caleb
     * 
     * @return state of the square as int (perhaps enum)
     */
    public Square squareAt(int y, int x) {
        return board[y][x];
    }
    
    /**
     * Print a visualization of the board
     */
    public void printDebugBoard() {
       for (int i = 0; i < 8; i++) {
	  for (int j = 0; j < 8; j++) {
	     System.out.print(squareAt(i, j).toString() + "\t");
	  }
	  System.out.println("");
       }
    }
    /**
     * Edits a piece at a given square to be a king
     * @auth Ashley
     * 
     * @param square location to find and king a piece
     */
    public void kingPiece(int y, int x) {
        // if x==0 black is a king
        //if x==7 red is a king
        /*if (//space you want to move to x !=0)
        board[//space you want to move to] = 1
        else
        board[//space you want to move to] = 1 * 3 //king multiplyer*/
        
        Square square;
        square = squareAt(y, x);
        if (square.isValid() && square.isOccupied()) {
            if (square.isRed())
                board[y][x] = Square.redKing;
            else if (square.isRed() == false)
                board[y][x] = Square.blackKing;
        }
    } 
    
}
