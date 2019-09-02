package com.example.shrinkio.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.example.shrinkio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText editText;
    Button Register;
    ImageView imageView;

    RadioGroup radioGroup2;
    RadioButton radioBtn, radioBtn2;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private static final int CHOOSE_IMAGE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        MultiDex.install(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        Objects.requireNonNull(getSupportActionBar()).hide();


        email = findViewById( R.id.email );
        password = findViewById( R.id.password );
        Register = findViewById( R.id. Register);




        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final String es = "Email Sent";


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
                            startActivity(new Intent(LoginActivity.this, LoginActivity3.class));
                            overridePendingTransition(0, 0);
                        }

                    }
                } );
            }
        } );







        radioGroup2 = findViewById( R.id.radioGroup3 );
        radioBtn = findViewById( R.id.radioBtn );
        radioBtn2 = findViewById( R.id.radioBtn2 );
        radioGroup2.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioBtn.isChecked()) {
                    startActivity( new Intent( LoginActivity.this, LoginActivity.class ) );
                    overridePendingTransition( 0, 0 );
                }if (radioBtn2.isChecked()) {
                        startActivity( new Intent( LoginActivity.this, LoginActivity2.class ) );
                        overridePendingTransition( 0, 0 );
                    }

            }
        } );

    }



    }



