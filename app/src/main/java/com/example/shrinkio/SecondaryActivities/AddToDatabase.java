package com.example.shrinkio.SecondaryActivities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shrinkio.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by User on 2/5/2017.
 */

public class AddToDatabase extends AppCompatActivity {

    private static final String TAG = "AddToDatabase";

    private EditText NewName;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_to_database );
        //declare variables in oncreate
        Button mAddToDB = findViewById( R.id.Register );
        NewName = findViewById( R.id.name );

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d( TAG, "onAuthStateChanged:signed_in:" + user.getUid() );
                    toastMessage( "Successfully signed in with: " + user.getEmail() );
                } else {
                    // User is signed out
                    Log.d( TAG, "onAuthStateChanged:signed_out" );
                    toastMessage( "Successfully signed out." );
                }
                // ...
            }
        };

        // Read from the database
        myRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d( TAG, "Value is: " + value );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w( TAG, "Failed to read value.", error.toException() );
            }
        } );

        mAddToDB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d( TAG, "onClick: Attempting to add object to database." );
                String newName = NewName.getText().toString();
                if (!newName.equals( "" )) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    assert user != null;
                    String userID = user.getUid();
                    myRef.child( userID ).child( "Users" ).child( "User Info" ).child( newName ).setValue( "true" );
                    toastMessage( "Adding " + newName + " to database..." );
                    //reset the text
                    NewName.setText( "" );
                }
            }
        } );

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener( mAuthListener );
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener( mAuthListener );
        }
    }

    //add a toast to show when successfully signed in

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }
}
