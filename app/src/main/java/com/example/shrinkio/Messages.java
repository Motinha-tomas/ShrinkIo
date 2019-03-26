package com.example.shrinkio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Objects;

public class Messages extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton Rd1, Rd2, Rd3, Rd4;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

         recyclerView = findViewById(R.id.card_view);


        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card, menu);
        return true;

    }

    public boolean onOptionsMenuSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}


