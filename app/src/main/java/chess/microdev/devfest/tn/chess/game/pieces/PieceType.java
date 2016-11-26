/* 
 * Copyright (C) 2014 Joseph
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package chess.microdev.devfest.tn.chess.game.pieces;

/**
 *
 * @author Joseph
 */
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
                str = 'B';;
                break;
            case 4:
                str = 'Q';;
                break;
            case 5:
                str = 'K';;
                break;
        }
        return str;
    }

    public int getTypeNumber() {
        return type;
    }

    abstract Piece create(PieceColor color);
}
