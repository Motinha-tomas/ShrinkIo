package com.example.shrinkio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Objects;


public class BottomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioGroup radioGroup;
    RadioButton Rd1, Rd2, Rd3, Rd4;



    @SuppressLint({"RtlHardcoded", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bottom );
        new GestureOverlayView( this );


        Objects.requireNonNull( getSupportActionBar() ).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView( R.layout.abs_layout_home );




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


        NavigationView nav_view = findViewById(R.id.nav_view);
    }


    public void onDrawerSlide() {
        DrawerLayout drawerLayout = findViewById( R.id.DrawerLayout);
        drawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull View view, float v) {

                    }

                    @Override
                    public void onDrawerOpened(@NonNull View view) {

                    }

                    @Override
                    public void onDrawerClosed(@NonNull View view) {


                    }

                    @Override
                    public void onDrawerStateChanged(int i) {

                    }
                }
        );
    }


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






