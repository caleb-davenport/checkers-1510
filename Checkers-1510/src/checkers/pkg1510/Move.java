
package checkers.pkg1510;

public class Move {
    
    private final int startY, startX;
    private final int endY, endX;
    private final Board.Square startSquare;
    
    public enum MoveType {
        step,
        jump,
        illegal
    }
    private MoveType movetype;
    
    private boolean moveError;
    
    Move(int stY, int stX, int enY, int enX, Board.Square stSq) {
        startY = stY;
        startX = stX;
        endY = enY;
        endX = enX;
        startSquare = stSq;
        
        moveError = false;
        
        discernType();
    }
    
    private void discernType() {
        if (startSquare.isBlack() && (endY - startY) > 0 && !startSquare.isKing()) {
            movetype = MoveType.illegal;
            moveError = true;
        } else if (!startSquare.isBlack() && (endY - startY) < 0 && !startSquare.isKing()) {
            movetype = MoveType.illegal;
            moveError = true;
        } else if (Math.abs(startY - endY) == 2 && Math.abs(startX - endX) == 2) {
            movetype = MoveType.jump;
        } else if (Math.abs(startY - endY) == 1 && Math.abs(startX - endX) == 1) {
            movetype = MoveType.step;
        } else {
            movetype = MoveType.illegal;
            moveError = true;
        }
    }
    
    public int StartY() { return startY; }
    
    public int StartX() { return startX; }
    
    public int EndY() { return endY; }
    
    public int EndX() { return endX; }
    
    public int[] StartPos() { return new int[]{startY, startX}; }
    
    public int[] EndPos() { return new int[]{startY, startX}; }
    
    public MoveType MoveType() { return movetype; }
    
    public boolean HasError() { return moveError; }
}
