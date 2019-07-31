package com.example.shrinkio.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shrinkio.MainActivities.BottomActivity;
import com.example.shrinkio.R;
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
    Button Login;
    RadioGroup radioGroup2;
    RadioButton radioBtn, radioBtn2;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login2 );
        Objects.requireNonNull( getSupportActionBar() ).hide();


        email = findViewById( R.id.email2);
        password = findViewById( R.id.password2 );
        Login = findViewById( R.id.Login2 );


        radioGroup2 = findViewById( R.id.radioGroup3 );
        radioBtn = findViewById( R.id.radioBtn );
        radioBtn2 = findViewById( R.id.radioBtn2 );
        radioGroup2.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioBtn.isChecked()) {
                    startActivity( new Intent( LoginActivity2.this, LoginActivity.class ) );
                    overridePendingTransition( 0, 0 );
                } else {
                    if (radioBtn2.isChecked()) {
                        startActivity( new Intent( LoginActivity2.this, LoginActivity2.class ) );
                        overridePendingTransition( 0, 0 );
                    }
                }
            }
        } );



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
                                    overridePendingTransition(0, 0);
                                } else {
                                    Toast.makeText( LoginActivity2.this, Objects.requireNonNull( task.getException() ).getMessage(), Toast.LENGTH_SHORT ).show();
                                }
                            }
                        } );
            }
        } );




    }
}

