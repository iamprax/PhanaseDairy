package in.prax.phanasedairy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import in.prax.phanasedairy.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText mEdtEmailID, mEdtPassword;
    private Button mBtnSignIn;
    private ProgressBar mProgressBar;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null) {

            //Main Activity

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {

            buttonClickListener();

        }


    }

    private void buttonClickListener() {


        mEdtEmailID = (EditText) findViewById(R.id.edtEmailID);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mBtnSignIn = (Button) findViewById(R.id.btnSignIn);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgressBar.setVisibility(View.VISIBLE);

                final String email = mEdtEmailID.getText().toString().trim();
                final String pass = mEdtPassword.getText().toString().trim();

                if (email.equals("")) {

                    Toast.makeText(LoginActivity.this, "Enter E-mail ID", Toast.LENGTH_SHORT).show();

                    mProgressBar.setVisibility(View.GONE);
                } else if (pass.equals("")) {

                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.GONE);

                } else {


                    mFirebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            mProgressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();


                            } else {

                                if (pass.length() < 6) {
                                    mEdtPassword.setError("Password too short, enter minimum 6 characters!");
                                } else {
                                    register(email,pass);
                                    Toast.makeText(LoginActivity.this, "Sign In failed, check your email and password!", Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                    });


                }


            }
        });

    }

    void register(final String email, String password) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        String data[] = email.split("@");
                        String newdata[] = data[1].split(".");

                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + data[0]);

                        } else {

                        }
                    }
                });
    }

}
