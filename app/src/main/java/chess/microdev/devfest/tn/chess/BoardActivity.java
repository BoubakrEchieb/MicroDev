package chess.microdev.devfest.tn.chess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import chess.microdev.devfest.tn.chess.game.pieces.Board;
import chess.microdev.devfest.tn.chess.game.pieces.Piece;
import chess.microdev.devfest.tn.chess.game.pieces.Square;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PAWN= 0;
    private static final int ROOK= 1;
    private static final int KNIGHT= 2;
    private static final int BISHOP= 3;
    private static final int QuEEN= 4;
    private static final int KING= 5;

    private AdView mAdView;

    // Firebase instance variables
    private DatabaseReference mFirebaseDatabaseReference;


    private Map<String,Integer> cases = new HashMap<>();
    /*@InjectView(R.id.board)
    GridLayout board;*/
    private Board board;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG="BoardActivity";
    private String gamekey;
    private String last;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ButterKnife.inject(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        intHashmap();

        /*Intent intent = getIntent();
        Board board = (Board) intent.getSerializableExtra("board");*/
        board = new Board();
        drawPieces(board);
        last="000";
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Firebase.setAndroidContext(this);
        DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("requests");
        reqRef.push();
        DatabaseReference matchRef = FirebaseDatabase.getInstance().getReference("games");
        key="match";
        Map<String,String> a = new HashMap<String,String>();
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("games/"+key+"/moves/moves");


        mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               /* for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                    for(int i = 0 ; i < board.SIZE ; i++){
                        for(int j = 0 ; j < board.SIZE ; j++){
                            Square ss = BoardActivity.this.board.getBoard()[i][j];
                            if(ss.toString().equals(dataSnapshot.getValue())){
                                ss.select();
                                drawPieces(board);



                            }

                        }
                    }
                }*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String a=dataSnapshot.getValue().toString();
                for(int i = 0 ; i < board.SIZE ; i++){
                    for(int j = 0 ; j < board.SIZE ; j++){
                        Square ss = BoardActivity.this.board.getBoard()[i][j];
                        if(ss.toString().equals(a)){
                            ss.select();
                            drawPieces(board);
                        }

                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
        /*ref.child("games").child(key).child("uidwhite").setValue("&122");
        ref.child("games").child(key).child("finished").setValue(Boolean.FALSE);*/




    }

    private void drawPieces(Board board)
    {

        Square [][]squares=board.getBoard();
        for (int i=0;i<board.SIZE;i++)
        {
            for (int j=0;j<board.SIZE;j++)
            {
                String position = squares[i][j].toString();
                ImageView carree = (ImageView) findViewById(cases.get(position));
                carree.setImageDrawable(null);
                if (squares[i][j].getColor())
                {
                    carree.setBackgroundColor(getResources().getColor(R.color.board_anti_case_color));
                }
                else
                {
                    carree.setBackgroundColor(getResources().getColor(R.color.board_case_color));
                }

                if (!squares[i][j].isEmpty())
                {
                    Piece p = squares[i][j].getPiece();
                    if(p.isWhite())
                    {
                        switch (p.getTypeNumber()){
                            case (PAWN):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.p_w));
                                break;
                            case (ROOK):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.r_w));
                                break;
                            case (KNIGHT):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.n_w));
                                break;
                            case (BISHOP):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.b_w));
                                break;
                            case (QuEEN):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.q_w));
                                break;
                            case (KING):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.k_w));
                                break;
                        }
                    }
                    else
                    {
                        switch (p.getTypeNumber()){
                            case (PAWN):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.p_b));
                                break;
                            case (ROOK):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.r_b));
                                break;
                            case (KNIGHT):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.n_b));
                                break;
                            case (BISHOP):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.b_b));
                                break;
                            case (QuEEN):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.q_b));
                                break;
                            case (KING):
                                carree.setImageDrawable(getResources().getDrawable(R.drawable.k_b));
                                break;
                        }
                    }



                }
                if (squares[i][j].isSelected())
                {
                    carree.setBackgroundColor(getResources().getColor(R.color.board_selected_case_color));
                }
            }
        }
    }
    private void intHashmap() {
        //row 1
        cases.put("a_1",R.id.a_1);
        cases.put("b_1",R.id.b_1);
        cases.put("c_1",R.id.c_1);
        cases.put("d_1",R.id.d_1);
        cases.put("e_1",R.id.e_1);
        cases.put("f_1",R.id.f_1);
        cases.put("g_1",R.id.g_1);
        cases.put("h_1",R.id.h_1);
        //end row 1

        //row 2
        cases.put("a_2",R.id.a_2);
        cases.put("b_2",R.id.b_2);
        cases.put("c_2",R.id.c_2);
        cases.put("d_2",R.id.d_2);
        cases.put("e_2",R.id.e_2);
        cases.put("f_2",R.id.f_2);
        cases.put("g_2",R.id.g_2);
        cases.put("h_2",R.id.h_2);
        //end row 2

        //row 3
        cases.put("a_3",R.id.a_3);
        cases.put("b_3",R.id.b_3);
        cases.put("c_3",R.id.c_3);
        cases.put("d_3",R.id.d_3);
        cases.put("e_3",R.id.e_3);
        cases.put("f_3",R.id.f_3);
        cases.put("g_3",R.id.g_3);
        cases.put("h_3",R.id.h_3);
        //end row 3

        //row 4
        cases.put("a_4",R.id.a_4);
        cases.put("b_4",R.id.b_4);
        cases.put("c_4",R.id.c_4);
        cases.put("d_4",R.id.d_4);
        cases.put("e_4",R.id.e_4);
        cases.put("f_4",R.id.f_4);
        cases.put("g_4",R.id.g_4);
        cases.put("h_4",R.id.h_4);
        //end row 4

        //row 5
        cases.put("a_5",R.id.a_5);
        cases.put("b_5",R.id.b_5);
        cases.put("c_5",R.id.c_5);
        cases.put("d_5",R.id.d_5);
        cases.put("e_5",R.id.e_5);
        cases.put("f_5",R.id.f_5);
        cases.put("g_5",R.id.g_5);
        cases.put("h_5",R.id.h_5);
        //end row 5

        //row 6
        cases.put("a_6",R.id.a_6);
        cases.put("b_6",R.id.b_6);
        cases.put("c_6",R.id.c_6);
        cases.put("d_6",R.id.d_6);
        cases.put("e_6",R.id.e_6);
        cases.put("f_6",R.id.f_6);
        cases.put("g_6",R.id.g_6);
        cases.put("h_6",R.id.h_6);
        //end row 6

        //row 7
        cases.put("a_7",R.id.a_7);
        cases.put("b_7",R.id.b_7);
        cases.put("c_7",R.id.c_7);
        cases.put("d_7",R.id.d_7);
        cases.put("e_7",R.id.e_7);
        cases.put("f_7",R.id.f_7);
        cases.put("g_7",R.id.g_7);
        cases.put("h_7",R.id.h_7);
        //end row 7

        //row 8
        cases.put("a_8",R.id.a_8);
        cases.put("b_8",R.id.b_8);
        cases.put("c_8",R.id.c_8);
        cases.put("d_8",R.id.d_8);
        cases.put("e_8",R.id.e_8);
        cases.put("f_8",R.id.f_8);
        cases.put("g_8",R.id.g_8);
        cases.put("h_8",R.id.h_8);
        //end row 7

        Set<String> keys = cases.keySet();
        for(String key : keys){
            ImageView c = (ImageView) findViewById(cases.get(key));
            c.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        for(int i = 0 ; i < board.SIZE ; i++){
            for(int j = 0 ; j < board.SIZE ; j++){
                Square s = board.getBoard()[i][j];
                if(cases.get(s.toString()).equals(view.getId())){


                    DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("games/"+key+"/moves");

                    reqRef.child("moves").child("move").setValue(s.toString());
                }

            }
        }

    }
}
