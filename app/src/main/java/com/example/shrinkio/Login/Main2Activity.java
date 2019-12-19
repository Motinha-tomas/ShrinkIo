package com.example.shrinkio.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.example.shrinkio.MainActivities.BottomActivity;
import com.example.shrinkio.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Main2Activity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

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
        setContentView( R.layout.activity_main2 );
        MultiDex.install(this);

        Objects.requireNonNull( getSupportActionBar() ).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView( R.layout.abs_layout_home );
        getSupportActionBar().hide();




        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public  void  onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(Main2Activity.this, BottomActivity.class);
                    overridePendingTransition(0,0);
                    startActivity(intent);
                    finish();
                }
            }


        };





        Button LoginBtn = findViewById( R.id.LoginBtn );
        LoginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Main2Activity.this, LoginActivity2.class) );
                overridePendingTransition( 0,0 );
            }
        } );


        Button CaBtn = findViewById( R.id.caButton );
        CaBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Main2Activity.this, LoginActivity.class) );
                overridePendingTransition( 0,0 );
            }
        } );



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
