package com.example.shrinkio.MainActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDex;

import com.example.shrinkio.Fragments.HomeFragment;
import com.example.shrinkio.Fragments.MessagesFragment;
import com.example.shrinkio.Fragments.NotFragment;
import com.example.shrinkio.Fragments.PeopleFragment;
import com.example.shrinkio.R;
import com.example.shrinkio.SecondaryActivities.DrawerLayout;
import com.example.shrinkio.SecondaryActivities.ProfileActivity;
import com.example.shrinkio.SecondaryActivities.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;


public class BottomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioGroup radioGroup;
    RadioButton Rd1, Rd2, Rd3, Rd4;


    private ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private StorageReference StorageRef;



    @SuppressLint({"RtlHardcoded", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(this);


        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bottom );
        new GestureOverlayView( this );

        //Action bar configurations
        Objects.requireNonNull( getSupportActionBar() ).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView( R.layout.abs_layout_home );





        NavigationView nav_view;

        nav_view = findViewById(R.id.nav_view);   //select nav_view from activity_drawer_layout

        nav_view.setNavigationItemSelectedListener(this);

        View nav_header = nav_view.getHeaderView(0);



        //Navigation Drawer header menu items on click actions

        LinearLayout header = nav_header.findViewById( R.id.nav_header);
        header.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( BottomActivity.this, ProfileActivity.class ) );
            }
        } );

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if sta   tement to keep the selected fragment when rotating the device
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
                            break;
                        case R.id.navigation_dashboard:
                            selectedFragment = new NotFragment();
                            break;
                        case R.id.navigation_people:
                            selectedFragment = new PeopleFragment();
                            break;
                        case R.id.navigation_messages:
                            selectedFragment = new MessagesFragment();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


    // Navigation menu items on click action
    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity( new Intent( BottomActivity.this, ProfileActivity.class ) );
                overridePendingTransition( 0, 0 );
                return true;
            case R.id.nav_settings:
                startActivity( new Intent( BottomActivity.this, SettingsActivity.class ) );
                overridePendingTransition( 0, 0 );
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }

    }
}



