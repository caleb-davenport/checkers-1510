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
public class Move {
    
    private final int startY, startX;
    private final int endY, endX;
    
    public enum MoveType {
        step,
        jump
    }
    private MoveType movetype;
    
    private boolean moveError;
    
    Move(int stY, int stX, int enY, int enX) {
        startY = stY;
        startX = stX;
        endY = enY;
        endX = enX;
        
        moveError = false;
        
        discernType();
    }
    
    private void discernType() {
        if (startY - endY == 2 && startX - endX == 2) {
            movetype = MoveType.jump;
        } else if (startY - endY == 1 && startX - endX == 1) {
            movetype = MoveType.step;
        } else {
            movetype = null;
            moveError = true;
        }
    }
    
    public int getStartY() { return startY; }
    
    public int getStartX() { return startX; }
    
    public int getEndY() { return endY; }
    
    public int getEndX() { return endX; }
    
    public int[] getStartPos() { return new int[]{startY, startX}; }
    
    public int[] getEndPos() { return new int[]{startY, startX}; }
    
    public MoveType getMoveType() { return movetype; }
    
    public boolean HasError() { return moveError; }
    
    
    
}
