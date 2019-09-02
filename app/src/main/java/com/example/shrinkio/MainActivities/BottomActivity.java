package com.example.shrinkio.MainActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.example.shrinkio.R;
import com.example.shrinkio.SecondaryActivities.DrawerLayout;
import com.example.shrinkio.SecondaryActivities.ProfileActivity;
import com.example.shrinkio.SecondaryActivities.SettingsActivity;
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



        // Radio group section

        radioGroup = findViewById( R.id.radioGroup );
        Rd1 = findViewById( R.id.radioButton );
        Rd2 = findViewById( R.id.radioButton2 );
        Rd3 = findViewById( R.id.radioButton3 );
        Rd4 = findViewById( R.id.radioButton4 );
        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (Rd1.isChecked()) {
                    Intent intent = new Intent( getApplicationContext(), BottomActivity.class );
                    startActivity( intent );
                    overridePendingTransition(0, 0);
                }
                if (Rd2.isChecked()) {
                    Intent intent1 = new Intent( getApplicationContext(), DashBoard.class );
                    startActivity( intent1 );
                    overridePendingTransition(0, 0);
                }
                if (Rd3.isChecked()) {
                    Intent intent2 = new Intent( getApplicationContext(), PeopleActivity.class );
                    startActivity( intent2 );
                    overridePendingTransition(0, 0);
                } else {
                    if (Rd4.isChecked()) {
                        Intent intent3 = new Intent( getApplicationContext(), Messages.class );
                        startActivity( intent3 );
                        overridePendingTransition(0, 0);
                    }
                }
            }
        } );


        NavigationView nav_view;

        nav_view = findViewById(R.id.nav_view);   //select nav_view from activity_drawer_layout

        nav_view.setNavigationItemSelectedListener(this);

        View nav_header = nav_view.getHeaderView(0);

        ImageButton Profilepic = nav_header.findViewById( R.id.ProfilePicture1);



        //Navigation Drawer header menu items on click actions

        LinearLayout header = nav_header.findViewById( R.id.nav_header);
        header.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( BottomActivity.this, ProfileActivity.class ) );
            }
        } );



    }











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



