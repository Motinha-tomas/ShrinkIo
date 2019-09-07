package com.example.shrinkio.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.example.shrinkio.MainActivities.BottomActivity;
import com.example.shrinkio.R;
import com.example.shrinkio.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class LoginActivity3 extends AppCompatActivity {

    Button welcome;
    EditText name, country;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    private DatabaseReference mDataBase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        Objects.requireNonNull(getSupportActionBar()).hide();
        MultiDex.install(this);


        firebaseAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        name = findViewById(R.id.name);
        country = findViewById(R.id.country);
        welcome = findViewById(R.id.welcome);

        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( validateForm()) {
                    final String username = name.getText().toString().trim();
                    final String Country = country.getText().toString().trim();

                    final User user = new User();
                    user.setUsername(username);
                    user.setCountry(Country);
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").push();


                    mDataBase.child("Users").child("Name").setValue(username);
                    mDataBase.child("Users").child("Country").setValue(Country);
                    mDataBase.child("Users").setValue(firebaseUser);

                    if(mAuth.getCurrentUser() != null) {
                        mDataBase.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(user, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                             if(databaseError != null) {
                                 Toast.makeText(getApplicationContext(), "Data is saved", Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(LoginActivity3.this, BottomActivity.class));
                                 finish();
                                 mDataBase.child("Users").child("Name").setValue(username);
                                 mDataBase.child("Users").child("Country").setValue(Country);

                             }
                            }
                        });
                    }
                }




            }
        });





    }


    public boolean validateForm() {
        boolean allDone = true;

        String displayName = name.getText().toString().trim();
        String Country = country.getText().toString().trim();

        if (TextUtils.isEmpty(displayName)) {
            name.setError("Enter your username please");
            return false;
        }
        else {
                allDone = true;
                name.setError(null);
            }
            if (TextUtils.isEmpty(Country)) {
                country.setError("Please tell us where you are from");
                return false;

            } else {
                country.setError(null);
                allDone = true;
            }

            return allDone;
        }

    }


