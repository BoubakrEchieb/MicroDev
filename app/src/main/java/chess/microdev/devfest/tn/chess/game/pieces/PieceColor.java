
package chess.microdev.devfest.tn.chess.game.pieces;




public enum PieceColor {

    BLACK(false), WHITE(true);

    private final boolean color;

    PieceColor(boolean color) {
        this.color = color;
    }


    public boolean isWhite() {
        return color;
    }


}
