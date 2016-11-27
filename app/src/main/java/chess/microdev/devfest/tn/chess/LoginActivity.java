package chess.microdev.devfest.tn.chess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @InjectView(R.id.input_email) EditText emailText;
    @InjectView(R.id.input_password) EditText emailPassword;
    @InjectView(R.id.btn_login) Button btnLogin;
    @InjectView(R.id.link_signup) TextView linkSignup;

    String email ;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        //getSupportActionBar().hide();
        // getActionBar().hide();
        btnLogin.setOnClickListener(this);
        linkSignup.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_login){
            login();
        }
        if(view.getId() == R.id.link_signup){
            startActivity(new Intent(getApplicationContext(),SignupActivity.class));
        }
    }

    private void login() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,android.R.style.Theme_DeviceDefault_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        email = String.valueOf(emailText.getText());
        password = String.valueOf(emailPassword.getText());

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithEmail", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
