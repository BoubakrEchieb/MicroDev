package chess.microdev.devfest.tn.chess.game;

import java.util.ArrayList;
import java.util.Iterator;

import chess.microdev.devfest.tn.chess.game.pieces.Piece;

/**
 * Created by khaled on 26/11/16.
 */
public class Table
{
    public ArrayList<Piece> whitePieces;
    public ArrayList<Piece> blackPieces;
    public ArrayList<Piece> whiteOutPieces;
    public ArrayList<Piece> blackOutPieces;
    public Table()
    {
        whitePieces= new ArrayList<Piece>();
        blackPieces= new ArrayList<Piece>();
        whiteOutPieces= new ArrayList<Piece>();
        blackOutPieces= new ArrayList<Piece>();
        init();
    }
    public Piece getPiece(char column,int line)
    {
        Iterator<Piece> iterator = whitePieces.iterator();
        while (iterator.hasNext())
        {
            Piece p = iterator.next();
            if(p.getLine()==line&&p.getColumn()==column)
                return p;
        }
        iterator = blackPieces.iterator();
        while (iterator.hasNext())
        {
            Piece p = iterator.next();
            if(p.getLine()==line&&p.getColumn()==column)
                return p;
        }
        return null;
    }
    private void init()
    {
        //whitePieces.add(new Piece());
    }
}
