
package chess.microdev.devfest.tn.chess.game.pieces;

import java.util.ArrayList;
import java.util.Collection;


class Queen extends Piece {

    Collection<Square> possibleMoves;

    public Queen(PieceColor color, PieceType type) {
        super(color, type);
        possibleMoves = new ArrayList<>();
    }

    @Override
    public Collection<Square> generatePossibleMoves() {
        possibleMoves.clear();
        Piece[] pieces = {
            PieceType.ROOK.create(getPieceColor()),
            PieceType.BISHOP.create(getPieceColor())
        };
        for (Piece piece : pieces) {
            piece.setSquare(getSquare());
            possibleMoves.addAll(piece.generatePossibleMoves());
        }
        return possibleMoves;
    }

    @Override
    public Collection<Square> getPossibleMoves() {
        return possibleMoves;
    }
}
