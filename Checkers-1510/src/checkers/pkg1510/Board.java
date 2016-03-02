/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.pkg1510;


/**
 *
 * @author rmartin-
 */
public class Board {

    int board[][] = {        //0=blank space  2=red piece  1=black piece
    {0,2,0,2,0,2,0,2},
    {2,0,2,0,2,0,2,0},
    {0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},
    {1,0,1,0,1,0,1,0},
    {0,1,0,1,0,1,0,1}
    };
    
    /**
     * Updates board by moving one piece.
     * @auth Josh
     * 
     * @param move a four int array with [strt x, strt y, end x, end y]
     */
    public void movePiece(int[] move) {
        // parse input
        // get piece type on the board
        // set old position to empty
        // set new sqaure with proper piece type
    }
    
    /**
     * Loads board into program variables from text file
     * @auth Roan
     * 
     * @param path path to setup file
     */
    public void setupBoard(String path) {
        
    }
    
    /**
     * Returns the state of an given square
     * @auth Caleb
     * 
     * @return state of the square as int (perhaps enum)
     */
    public int getSquareState(int[] square) {
        return -42;
    }
    
    /**
     * Edits a piece at a given square to be a king
     * @auth Ashley
     * 
     * @param square location to find and king a piece
     */
    public void kingPiece(int[] square) {
        // if x==0 black is a king
        //if x==7 red is a king
        if (//space you want to move to x !=0)
        board[//space you want to move to] = 1
        else
        board[//space you want to move to] = 1 * 3 //king multiplyer 
    } 
    
}
