
package chess.microdev.devfest.tn.chess.game.pieces;


import java.io.Serializable;
import java.util.ArrayList;

public final class Board implements Serializable{

    private ArrayList<Piece> pieces;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private Square[][] board;
    private Square selectedSquare;
    private boolean turn;
    private boolean check;
    private int reminder;
    public static final int SIZE = 8;
    private static final int GAP = 5;
    private King whiteKingPiece;
    private King blackKingPiece;

    public Board() {
        create();
    }



    public static String printRow(int row) {
        return String.valueOf(SIZE - row);
    }

    public static String printColumn(int column) {
        return String.valueOf((char) ('A' + column));
    }



    public void create() {
        pieces = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        turn = false;
        check = false;
        reminder = 0;
        board = new Square[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                board[row][column] = new Square(row, column, this);
            }
        }



        createStandardPieceSet();
    }

    public Piece getOpponentKing(Piece piece) {
        Piece returnPiece = piece.isOpponent(blackKingPiece) ? blackKingPiece : whiteKingPiece;
        return returnPiece;
    }

    public void createStandardPieceSet() {
        PieceColor whitePieceColor = PieceColor.WHITE;
        Piece[] whitePiecesFirstRow = new Piece[]{
            PieceType.ROOK.create(whitePieceColor),
            PieceType.KNIGHT.create(whitePieceColor),
            PieceType.BISHOP.create(whitePieceColor),
            PieceType.QUEEN.create(whitePieceColor),
            PieceType.KING.create(whitePieceColor),
            PieceType.BISHOP.create(whitePieceColor),
            PieceType.KNIGHT.create(whitePieceColor),
            PieceType.ROOK.create(whitePieceColor)};
        Piece[] whitePiecesSecondRow = new Piece[SIZE];
        for (int i = 0; i < whitePiecesSecondRow.length; i++) {
            whitePiecesSecondRow[i] = PieceType.PAWN.create(whitePieceColor);
        }
        int count = 0;
        for (Piece piece : whitePiecesFirstRow) {
            if (piece != null) {
                piece.putPieceOnSquareFirstTime(board[7][count]);
                pieces.add(piece);
                whitePieces.add(piece);
                if (piece.isKing()) {
                    whiteKingPiece = (King) piece;
                }
                count++;
            }
        }
        count = 0;
        for (Piece piece : whitePiecesSecondRow) {
            if (piece != null) {
                piece.putPieceOnSquareFirstTime(board[6][count]);
                pieces.add(piece);
                whitePieces.add(piece);
                count++;
            }
        }
        PieceColor blackPieceColor = PieceColor.BLACK;
        Piece[] blackPiecesFirstRow = new Piece[]{
            PieceType.ROOK.create(blackPieceColor),
            PieceType.KNIGHT.create(blackPieceColor),
            PieceType.BISHOP.create(blackPieceColor),
            PieceType.QUEEN.create(blackPieceColor),
            PieceType.KING.create(blackPieceColor),
            PieceType.BISHOP.create(blackPieceColor),
            PieceType.KNIGHT.create(blackPieceColor),
            PieceType.ROOK.create(blackPieceColor)};
        Piece[] blackPiecesSecondRow = new Piece[SIZE];
        for (int i = 0; i < blackPiecesSecondRow.length; i++) {
            blackPiecesSecondRow[i] = PieceType.PAWN.create(blackPieceColor);
        }
        count = 0;
        for (Piece piece : blackPiecesFirstRow) {
            if (piece != null) {
                piece.putPieceOnSquareFirstTime(board[0][count]);
                pieces.add(piece);
                blackPieces.add(piece);
                if (piece.isKing()) {
                    blackKingPiece = (King) piece;
                }
                count++;
            }
        }
        count = 0;
        for (Piece piece : blackPiecesSecondRow) {
            if (piece != null) {
                piece.putPieceOnSquareFirstTime(board[1][count]);
                pieces.add(piece);
                blackPieces.add(piece);
                count++;
            }
        }
    }

    public void resetReminder() {
        reminder = 0;
    }

    public int getReminder() {
        return reminder++;
    }

    public boolean getTurn() {
        return turn;
    }

    public Square[][] getBoard() {
        return board;
    }

    public void move(Square from, Square to) {
        to.movePiece(from.getPiece());
        from.removePiece();
        turn = !turn;
    }

    public Square getSquare(int row, int column) {
        Square square = null;
        if (row < SIZE && column < SIZE && row >= 0 && column >= 0) {
            square = board[row][column];
        }
        return square;
    }

    public boolean kingInCheck() {
        if (turn) {
            return blackKingPiece.inCheck;
        } else {
            return whiteKingPiece.inCheck;
        }
    }

    public Square getSelected() {
        return selectedSquare;
    }

    public void setSelected(Square square) {
        selectedSquare = square;
    }

    public void deselect() {
        if (selectedSquare != null) {
            selectedSquare.deselect();
            for (Square[] squares : board) {
                for (Square square : squares) {
                }
            }
        }
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }
}
