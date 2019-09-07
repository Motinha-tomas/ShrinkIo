package com.example.shrinkio.SecondaryActivities;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shrinkio.R;
import com.example.shrinkio.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xml.sax.DTDHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    DatabaseReference reference;
    TextView username, country, age;
    FirebaseUser user;

    List<String> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        username = findViewById(R.id.username);
        country = findViewById(R.id.Country);
        age = findViewById(R.id.Age);
        user = firebaseAuth.getCurrentUser();



        reference = FirebaseDatabase.getInstance().getReference().child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Username = dataSnapshot.child("Users").getValue().toString();
                String Country = dataSnapshot.child("Country").getValue().toString();
                String Age = dataSnapshot.child("Age").getValue().toString();

                Toast.makeText(getApplicationContext(), Username + Age, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}




