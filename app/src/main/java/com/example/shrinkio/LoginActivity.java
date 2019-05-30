package com.example.shrinkio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    EditText email;
    EditText password;
    Button Register;
    Button Login;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        Objects.requireNonNull( getSupportActionBar() ).hide();

        email = findViewById( R.id.email );
        password = findViewById( R.id.password );
        Register = findViewById( R.id.Register );
        Login = findViewById( R.id.Login );


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();




        if (firebaseUser != null) {
            startActivity(new Intent(LoginActivity.this, BottomActivity.class));
            finish();
        }

        Register.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 firebaseAuth.createUserWithEmailAndPassword( email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            email.setText( "" );
                            password.setText( "");
                            startActivity(new Intent(LoginActivity.this, BottomActivity.class));
                        }
                    }
                } );
            }
        } );




        Login.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity( new Intent(LoginActivity.this, LoginActivity2.class) );
            }
        });
    }
}

