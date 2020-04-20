package com.example.shrinkio.MainActivities;

import android.annotation.SuppressLint;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shrinkio.Fragments.HomeFragment;
import com.example.shrinkio.Fragments.MessagesFragment;
import com.example.shrinkio.Fragments.NotFragment;
import com.example.shrinkio.Fragments.PeopleFragment;
import com.example.shrinkio.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BottomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "";
    RecyclerView rv;
    TextView post_desc;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    Fragment selectedFragment = null;
    ImageButton add;
    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
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

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
            }
            return true;
        }

    };

    @SuppressLint({"RtlHardcoded", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(this);
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bottom );
        new GestureOverlayView( this );


        //Action bar configurations


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation1);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);







    }




    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
        return loadFragment(new HomeFragment());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
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

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}







