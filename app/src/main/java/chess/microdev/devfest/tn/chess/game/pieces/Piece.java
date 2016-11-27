package chess.microdev.devfest.tn.chess.game.pieces;

import java.io.Serializable;
import java.util.Collection;


public abstract class Piece implements Serializable{

    private final PieceType pieceType;
    private final PieceColor pieceColor;
    private Square square;
    private boolean moved;

    public Piece(PieceColor color, PieceType type) {
        pieceColor = color;
        pieceType = type;
        moved = false;
    }
    
    public PieceColor getPieceColor() {
        return pieceColor;
    }
    
    public void setMoved() {
        moved = true;
    }

    public boolean hasMoved() {
        return moved;
    }

    public int getTypeNumber() {
        return pieceType.getTypeNumber();
    }

    public String getColorString() {
        return pieceColor.toString();
    }

    public String getType() {
        return pieceType.toString();
    }



    public Square getSquare() {
        return square;
    }

    public boolean isWhite() {
        return pieceColor.isWhite();
    }
    
    public Piece getOpponentKing() {
        return getSquare().getBoard().getOpponentKing(this);
    }

    public boolean isOpponent(Piece piece) {
        return piece != null && isWhite() != piece.isWhite();
    }

    public boolean legalMove(Square to) {
        return to.getPiece() == null || isOpponent(to.getPiece());
    }
    public void removeCheck()
    {

    }

    public abstract Collection<Square> getPossibleMoves();

    public abstract Collection<Square> generatePossibleMoves();


    public void putPieceOnSquareFirstTime(Square square) {
        this.square = square;
        this.square.setPiece(this);
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    @Override
    public String toString() {
        return pieceColor.toString().substring(0, 1) + " " + pieceType.toString().substring(0, 1);
    }

    public boolean isKing() {
        return this instanceof King;
    }



}
