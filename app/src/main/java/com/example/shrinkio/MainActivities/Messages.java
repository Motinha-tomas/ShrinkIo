package com.example.shrinkio.MainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDex;

import com.example.shrinkio.Fragments.HomeFragment;
import com.example.shrinkio.Fragments.MessagesFragment;
import com.example.shrinkio.Fragments.NotFragment;
import com.example.shrinkio.Fragments.PeopleFragment;
import com.example.shrinkio.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class Messages extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton Rd1, Rd2, Rd3, Rd4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_messages);
        MultiDex.install(this);


        Objects.requireNonNull( getSupportActionBar() ).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.abs_layout_messages );
        setTitle( "Messages" );


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            startActivity(new Intent(Messages.this, BottomActivity.class));
                            overridePendingTransition(0,0);
                            break;
                        case R.id.navigation_dashboard:
                            selectedFragment = new NotFragment();
                            startActivity(new Intent(Messages.this, DashBoard.class));
                            overridePendingTransition(0,0);
                            break;
                        case R.id.navigation_people:
                            selectedFragment = new PeopleFragment();
                            startActivity(new Intent(Messages.this, PeopleActivity.class));
                            overridePendingTransition(0,0);
                            break;
                        case R.id.navigation_messages:
                            selectedFragment = new MessagesFragment();
                            startActivity(new Intent(Messages.this, Messages.class));
                            overridePendingTransition(0,0);
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}


