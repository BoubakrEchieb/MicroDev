package chess.microdev.devfest.tn.chess;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BoardActivity extends AppCompatActivity {

    private Map<String,Integer> cases = new HashMap<>() ;
    @InjectView(R.id.board)
    GridLayout board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        intHashmap();

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
        cases.put("c_3",R.id.c_2);
        cases.put("d_3",R.id.d_2);
        cases.put("e_3",R.id.e_2);
        cases.put("f_3",R.id.f_2);
        cases.put("g_3",R.id.g_2);
        cases.put("h_3",R.id.h_2);
        //end row 3
    }

}
