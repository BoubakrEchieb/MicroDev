
package chess.microdev.devfest.tn.chess.game.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class King extends Piece {

    Collection<Square> possibleMoves;
    boolean inCheck;

    public King(PieceColor color, PieceType type) {
        super(color, type);
        possibleMoves = new ArrayList<>();
        inCheck = false;
    }

    @Override
    public Collection<Square> generatePossibleMoves() {
        possibleMoves.clear();
        List<Square> moves = new ArrayList<>();
        int[][] offsets = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {-1, 1},
            {-1, -1},
            {1, -1}
        };
        for (int[] o : offsets) {
            Square square = super.getSquare().neighbour(o[0], o[1]);
            if (square != null && (square.getPiece() == null || isOpponent(square.getPiece()))) {
                moves.add(square);
            }
        }
        possibleMoves.addAll(moves);
        if (getSquare().isSelected()) {
            inCheck = false;
            Piece[] pieces = {
                PieceType.PAWN.create(getPieceColor()),
                PieceType.ROOK.create(getPieceColor()),
                PieceType.BISHOP.create(getPieceColor()),
                PieceType.KNIGHT.create(getPieceColor()),
                PieceType.QUEEN.create(getPieceColor()),
                PieceType.KING.create(getPieceColor())};
            Piece oldKing = this;
            getSquare().removePiece();
            for(Square kingMove:moves){
                if (kingMove.isEmpty()) {
                    for (Piece piece : pieces) {
                        piece.putPieceOnSquareFirstTime(kingMove);
                        piece.generatePossibleMoves();
                        Object[] o = piece.getPossibleMoves().toArray();
                        for(int i=0;i<o.length;i++)
                        {
                            Square enemy =(Square)o[i];
                            if((possibleMoves.contains(kingMove) && !enemy.isEmpty()
                                    && enemy.getPiece().isOpponent(piece)
                                    && enemy.getPiece().getTypeNumber() == piece.getTypeNumber()))
                            {
                                possibleMoves.remove(kingMove);
                                if (!inCheck) {
                                    inCheck = enemy.getPiece().generatePossibleMoves().contains(getSquare());
                                }
                            }
                        }

                    }
                    kingMove.removePiece();
                } else if (isOpponent(kingMove.getPiece())) {
                    Piece oldPiece = kingMove.getPiece();
                    for (Piece piece : pieces) {
                        kingMove.removePiece();
                        piece.putPieceOnSquareFirstTime(kingMove);
                        piece.generatePossibleMoves();
                        Object[] o = piece.getPossibleMoves().toArray();
                        for(int i=0;i<o.length;i++)
                        {
                            Square enemy =(Square)o[i];
                            if((possibleMoves.contains(kingMove) && !enemy.isEmpty()
                                    && enemy.getPiece().isOpponent(piece)
                                    && enemy.getPiece().getTypeNumber() == piece.getTypeNumber()))
                            {
                                possibleMoves.remove(kingMove);
                                if (!inCheck) {
                                    inCheck = enemy.getPiece().generatePossibleMoves().contains(getSquare());
                                }
                            }
                        }

                    }
                    kingMove.removePiece();
                    oldPiece.putPieceOnSquareFirstTime(kingMove);
                }
            }
            oldKing.putPieceOnSquareFirstTime(getSquare());
        }
        return possibleMoves;
    }

    @Override
    public Collection<Square> getPossibleMoves() {
        return possibleMoves;
    }

}
