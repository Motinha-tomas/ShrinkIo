package com.example.shrinkio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Objects;

public class DashBoard extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton Rd1, Rd2, Rd3, Rd4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_notifications);
        setTitle(" Notifications" );

        radioGroup = findViewById(R.id.radioGroup);
        Rd1= findViewById(R.id.radioButton);
        Rd2= findViewById(R.id.radioButton2);
        Rd3= findViewById(R.id.radioButton3);
        Rd4= findViewById(R.id.radioButton4);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (Rd1.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), BottomActivity.class);
                    startActivity(intent);
                }
                if (Rd2.isChecked()) {
                    Intent intent1 = new Intent(getApplicationContext(), DashBoard.class);
                    startActivity(intent1);
                }
                if (Rd3.isChecked()) {
                    Intent intent2 = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intent2);
                } else {
                    if (Rd4.isChecked()) {
                        Intent intent3 = new Intent(getApplicationContext(), Messages.class);
                        startActivity(intent3);
                    }


                }
            }
        });
    }




}


