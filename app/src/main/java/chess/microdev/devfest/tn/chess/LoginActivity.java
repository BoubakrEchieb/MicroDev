package chess.microdev.devfest.tn.chess;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText emailText;
    @InjectView(R.id.input_password) EditText emailPassword;
    @InjectView(R.id.btn_login) Button btnLogin;
    @InjectView(R.id.link_signup) TextView linkSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        //getSupportActionBar().hide();
        // getActionBar().hide();
        btnLogin.setOnClickListener(this);
        linkSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_login){
            login();
        }
        if(view.getId() == R.id.link_signup){
            signup();
        }
    }

    private void signup() {
    }

    private void login() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,android.R.style.Theme_DeviceDefault_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }
}
