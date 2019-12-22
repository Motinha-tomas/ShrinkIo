package com.example.shrinkio.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.example.shrinkio.MainActivities.BottomActivity;
import com.example.shrinkio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity2 extends AppCompatActivity {
    EditText email;
    EditText password;
    Button Login;
    RadioGroup radioGroup2;
    RadioButton radioBtn, radioBtn2;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    public void onStart () {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null) {
            startActivity(new  Intent(this, BottomActivity.class));
            overridePendingTransition(0,0);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login2 );
        MultiDex.install(this);


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



        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
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
                                    finish();
                                } else {
                                    Toast.makeText( LoginActivity2.this, Objects.requireNonNull( task.getException() ).getMessage(), LENGTH_SHORT ).show();
                                }
                            }
                        } );
            }
        } );

    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Required");
            result = false;
        } else {
            email.setError(null);
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Required");
            result = false;
        } else {
            password.setError(null);
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity2.class));
    }
}