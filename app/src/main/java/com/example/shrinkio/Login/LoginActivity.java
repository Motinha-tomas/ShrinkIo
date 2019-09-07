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

import com.example.shrinkio.MainActivities.BottomActivity;
import com.example.shrinkio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText mEmail, mPassword, mName, mAge, mCountry;
    Button Register;

    RadioGroup radioGroup2;
    RadioButton radioBtn, radioBtn2;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final int CHOOSE_IMAGE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        MultiDex.install(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        Objects.requireNonNull(getSupportActionBar()).hide();


        mEmail = findViewById( R.id.email );
        mPassword = findViewById( R.id.password );
        Register = findViewById( R.id. Register);
        mName = findViewById(R.id.name);
        mAge = findViewById(R.id.Age);
        mCountry = findViewById(R.id.Country);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                startActivity(new Intent(LoginActivity.this, BottomActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return;
            }
        };

        final String es = "Email Sent";


        Register.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                 firebaseAuth.createUserWithEmailAndPassword(
                         mEmail.getText().toString(),
                         mPassword.getText().toString())

                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginActivity.this, BottomActivity.class));
                            overridePendingTransition(0,0);


                            String user_id = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                            String name = mName.getText().toString();
                            String age = mAge.getText().toString();
                            String country = mCountry.getText().toString();

                            Map newPost = new HashMap();
                            newPost.put("Name", name);
                            newPost.put("Age", age);
                            newPost.put("Country", country);

                            current_user_db.setValue(newPost);

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



