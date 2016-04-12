
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
        if (startSquare.isRed() && (endY - startY) > 0 && !startSquare.isKing()) {
            movetype = MoveType.illegal;
            moveError = true;
        } else if (!startSquare.isRed() && (endY - startY) < 0 && !startSquare.isKing()) {
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
    
    public int getStartY() { return startY; }
    
    public int getStartX() { return startX; }
    
    public int getEndY() { return endY; }
    
    public int getEndX() { return endX; }
    
    public int[] getStartPos() { return new int[]{startY, startX}; }
    
    public int[] getEndPos() { return new int[]{startY, startX}; }
    
    public MoveType getMoveType() { return movetype; }
    
    public boolean HasError() { return moveError; }
    
    
    
}
