package chess.microdev.devfest.tn.chess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import butterknife.InjectView;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    @InjectView(R.id.input_email)
    EditText emailText;
    @InjectView(R.id.input_password) EditText emailPassword;
    @InjectView(R.id.btn_signup)
    Button btnLogin;
    @InjectView(R.id.link_signup)
    TextView linkSignup;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

    }

    private void signup() {
        //getting email and password from edit texts
        String email = emailText.getText().toString().trim();
        String password  = emailPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

       /* progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();*/

        //creating a new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            finish();
                            Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                        }else{
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_signup){
            signup();
        }
        if(view.getId() == R.id.link_login){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
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
