package com.example.shrinkio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity2 extends AppCompatActivity {
    ProgressBar progressBar;
    EditText email;
    EditText password;
    Button Register;
    Button Login;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login2 );
        Objects.requireNonNull( getSupportActionBar() ).hide();


        progressBar = findViewById( R.id.login_progress );
        email = findViewById( R.id.email2);
        password = findViewById( R.id.password2 );
        Register = findViewById( R.id.Register2 );
        Login = findViewById( R.id.Login2 );






        firebaseAuth = FirebaseAuth.getInstance();
        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword( email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             Toast.makeText( LoginActivity2.this, "Logged in Successfully", Toast.LENGTH_LONG).show();
                             email.getText();
                             password.getText();
                             startActivity(new Intent(LoginActivity2.this, BottomActivity.class));
                         } else {
                             Toast.makeText( LoginActivity2.this, Objects.requireNonNull( task.getException() ).getMessage(), Toast.LENGTH_SHORT ).show();
                         }
                    }
                } );
            }
        } );


        Register.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity2.this, LoginActivity.class));
            }
        });

    }
}

