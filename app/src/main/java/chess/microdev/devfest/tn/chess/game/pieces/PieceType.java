
package chess.microdev.devfest.tn.chess.game.pieces;


public enum PieceType {

    PAWN(0) {
                @Override
                Piece create(PieceColor color) {
                    return new Pawn(color, this);
                }
            }, ROOK(1) {
                @Override
                Piece create(PieceColor color) {
                    return new Rook(color, this);
                }
            }, KNIGHT(2) {
                @Override
                Piece create(PieceColor color) {
                    return new Knight(color, this);
                }
            }, BISHOP(3) {
                @Override
                Piece create(PieceColor color) {
                    return new Bishop(color, this);
                }
            }, QUEEN(4) {
                @Override
                Piece create(PieceColor color) {
                    return new Queen(color, this);
                }
            }, KING(5) {
                @Override
                Piece create(PieceColor color) {
                    return new King(color, this);
                }
            };
    private final int type;

    PieceType(int type) {
        this.type = type;
    }

    public char getTypeChar() {
        char str = ' ';
        switch (type) {
            case 1:
                str = 'R';
                break;
            case 2:
                str = 'N';
                break;
            case 3:
                str = 'B';
                break;
            case 4:
                str = 'Q';
                break;
            case 5:
                str = 'K';
                break;
        }
        return str;
    }

    public int getTypeNumber() {
        return type;
    }

    abstract Piece create(PieceColor color);
}
