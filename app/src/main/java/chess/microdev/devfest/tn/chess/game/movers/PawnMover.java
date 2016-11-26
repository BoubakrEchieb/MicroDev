package chess.microdev.devfest.tn.chess.game.movers;

import java.util.Iterator;

import chess.microdev.devfest.tn.chess.game.Table;
import chess.microdev.devfest.tn.chess.game.pieces.Piece;

/**
 * Created by khaled on 26/11/16.
 */
public class PawnMover implements Mover{
    @Override
    public String canMoveTo(Table table, Piece piece, char column, int line)
    {
        int direction = piece.isWhite() ? -1 : 1;
        if(piece.getColumn()==column&&piece.getLine()+direction==line)
        {
            if(table.getPiece(piece.getColumn(),piece.getLine()+direction)==null)
            {
                return ""+piece.getColumn()+""+(piece.getLine()+direction);
            }
        }
        if(piece.getColumn()+1==column&&piece.getLine()+direction==line)
        {
            Piece p = table.getPiece(((char)(piece.getColumn()+1)),piece.getLine()+direction);
            if(p!=null)
            {
                if(p!=null)
                {
                    return "x"+piece.getColumn()+""+(piece.getLine()+direction);
                }
            }
        }





        return null;
    }
}
