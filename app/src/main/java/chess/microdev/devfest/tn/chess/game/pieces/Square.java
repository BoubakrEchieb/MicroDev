
package chess.microdev.devfest.tn.chess.game.pieces;


import java.util.Collection;

public class Square {

    public final int ROW;
    public final int COLUMN;
    private Piece piece;
    private final Board board;
    private boolean selected;

    public Square(int row, int column, Board board) {
        this.board = board;
        ROW = row;
        COLUMN = column;
        selected = false;
    }

    public boolean getColor() {
        return (ROW + COLUMN) % 2 == 1;
    }

    private void select()
    {
        if (piece != null)
        {
            if ((board.getTurn() && !piece.isWhite()) || (!board.getTurn() && piece.isWhite()) && !selected) {
                if (piece.getTypeNumber() == 5 || !board.kingInCheck()) {
                    if (board.getSelected() != null && !board.getSelected().isEmpty())
                    {
                        //board.getSelected().getPiece().getPossibleMoves().stream().forEach((item) -> {item.deselect();});
                        Square[] coll= (Square[]) board.getSelected().getPiece().getPossibleMoves().toArray();
                        for (int i=0;i<coll.length;i++)
                        {
                            coll[i].deselect();
                        }
                    }
                    board.deselect();
                    board.setSelected(this);
                    selected = true;
                } else {
                    if (board.getReminder() > 1) {
                        board.resetReminder();
                    }
                }
            } else if ((board.getTurn() && piece.isWhite()) || (!board.getTurn() && !piece.isWhite())) {
                if (board.getSelected() != null && !board.getSelected().isEmpty()) {
                    if (board.getSelected().getPiece().getPossibleMoves().contains(this)) {
                        //Capture move
                        Square from = board.getSelected();
                        Square to = this;
                        if (from.getPiece() != null) {
                            //from.getPiece().getPossibleMoves().stream().forEach((square) -> {square.deselect();});
                            Square[] coll= (Square[]) from.getPiece().getPossibleMoves().toArray();
                            for (int i=0;i<coll.length;i++)
                            {
                                coll[i].deselect();
                            }
                        }
                        board.move(from, to);
                        from.deselect();
                        to.deselect();
                    }
                }
            }
        } else {
            if (board.getSelected() != null && !board.getSelected().isEmpty()) {
                if (board.getSelected().getPiece().getPossibleMoves().contains(this)) {
                    //Natural move
                    Square from = board.getSelected();
                    Square to = this;
                    from.getPiece().getPossibleMoves().stream().forEach((square) -> {
                        square.deselect();
                    });
                    board.move(from, to);
                    board.deselect();
                    board.setSelected(null);
                }
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public Square neighbour(int row, int column) {
        return board.getSquare(ROW + row, COLUMN + column);
    }
    public String toString() {
        String a="";
        switch (COLUMN)
        {
            case 0 :
                a+="a";
                break;
            case 1 :
                a+="b";
                break;
            case 2 :
                a+="c";
                break;
            case 3 :
                a+="d";
                break;
            case 4 :
                a+="e";
                break;
            case 5 :
                a+="f";
                break;
            case 6 :
                a+="g";
                break;
            case 7 :
                a+="h";
                break;

        }
        a+="_"+(ROW+1);
        return a;
    }

    public Square getBoardSquare(int row, int column) {
        return board.getSquare(row, column);
    }

    public void deselect() {
        selected = false;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void movePiece(Piece piece) {
        this.piece = piece;
        this.piece.setMoved();
        this.piece.setSquare(this);
        this.piece.generatePossibleMoves();
    }
    public void removePiece() {
        piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public boolean isSelected() {
        return selected;
    }


}
