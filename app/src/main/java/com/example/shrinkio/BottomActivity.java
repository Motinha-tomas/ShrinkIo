package com.example.shrinkio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Objects;


public class BottomActivity extends AppCompatActivity
        implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary gestureLib;





    RadioGroup radioGroup;
    RadioButton Rd1, Rd2, Rd3, Rd4;
    DrawerLayout drawer;



    @SuppressLint({"RtlHardcoded", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bottom );
        GestureOverlayView gestureOverlayView = new GestureOverlayView(this);



        Objects.requireNonNull( getSupportActionBar() ).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
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
                }
                if (Rd2.isChecked()) {
                    Intent intent1 = new Intent( getApplicationContext(), DashBoard.class );
                    startActivity( intent1 );
                }
                if (Rd3.isChecked()) {
                    Intent intent2 = new Intent( getApplicationContext(), SettingsActivity.class );
                    startActivity( intent2 );
                } else {
                    if (Rd4.isChecked()) {
                        Intent intent3 = new Intent( getApplicationContext(), Messages.class );
                        startActivity( intent3 );
                    }
                }
            }
        } );











    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.END );
        } else {
            super.onBackPressed();
        }
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
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

    }








}



