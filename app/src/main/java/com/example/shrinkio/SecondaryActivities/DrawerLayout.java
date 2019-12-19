package com.example.shrinkio.SecondaryActivities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shrinkio.R;
import com.example.shrinkio.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DrawerLayout extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser user;
    DatabaseReference mUsername;
    private String userID;

    private static final String TAG = "ViewDatabase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_drawer_layout );

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("Users");
        mUsername = reference.child(userID).child("Name");





        user = firebaseAuth.getCurrentUser();
        userID =  user.getUid();
        reference = firebaseDatabase.getReference();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if (user != null) {
                Log.d(TAG, "onAuthStateChanged:sign_in" + firebaseUser.getUid());
            } else {
                Log.d(TAG, "onAuthStateChanged:sign_out" + firebaseUser.getUid());

            }}
        };


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           showdata(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void showdata(DataSnapshot dataSnapshot) {
       for (DataSnapshot ds : dataSnapshot.getChildren() ){
       User uInfo = new User();
       uInfo.setUsername(ds.child(userID).getValue(User.class).getUsername());
       uInfo.setAge(ds.child(userID).getValue(User.class).getAge());
       uInfo.setCountry(ds.child(userID).getValue(User.class).getCountry());


       Log.d(TAG, uInfo.getUsername());
       Log.d(TAG, uInfo.getAge());
       Log.d(TAG, uInfo.getCountry());

           ArrayList<String> array = new ArrayList<>();
           array.add(uInfo.getUsername());
           array.add(uInfo.getCountry());
       }
    }


    @Override
        public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
        }


        @Override
        public void onStop()
        {
            super.onStop();
            if (mAuthStateListener != null) {
                firebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
        }
    }



