package com.example.shrinkio.SecondaryActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shrinkio.Login.LoginActivity;
import com.example.shrinkio.MainActivities.BottomActivity;
import com.example.shrinkio.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {


    RadioGroup radioGroup;
    RadioButton Rd1, Rd2, Rd3, Rd4;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.settings_activity );

        Objects.requireNonNull( getSupportActionBar() ).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );


        logout = findViewById( R.id.logout );

        logout.setOnClickListener(v -> {
         FirebaseAuth.getInstance().signOut();
         startActivity( new Intent (SettingsActivity.this, LoginActivity.class) );
         Toast.makeText( SettingsActivity.this, "Logged Out", Toast.LENGTH_SHORT ).show();
        });

    }


     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_button:
                startActivity( new Intent(SettingsActivity.this, BottomActivity.class) );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



