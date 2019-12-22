package com.example.shrinkio.MainActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shrinkio.Fragments.HomeFragment;
import com.example.shrinkio.Fragments.MessagesFragment;
import com.example.shrinkio.Fragments.NotFragment;
import com.example.shrinkio.Fragments.PeopleFragment;
import com.example.shrinkio.R;
import com.example.shrinkio.SecondaryActivities.PostActivity;
import com.example.shrinkio.SecondaryActivities.Posts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

import static com.example.shrinkio.R.menu.main_menu;


public class BottomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "";
    RecyclerView rv;
    TextView post_desc;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    Fragment selectedFragment = null;
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

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_home);


        //Action bar configurations


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation1);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        post_desc = findViewById(R.id.post_desc);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        String user_id = firebaseUser.getUid();
        myRef = mDatabase.getReference().child("Users").child(user_id).child("post");








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_post:
                Intent intent = new Intent(this, PostActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    protected void onStart() {
        super.onStart();

        Query query = myRef.child("post");
        FirebaseRecyclerOptions<Posts> options = new FirebaseRecyclerOptions.Builder<Posts>()
                .setQuery(query, Posts.class)
                .build();


        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Posts, PostViewHolder>(
                options) {
            @Override
            protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, Posts model) {
                holder.setPost(model.getPost());

            }

            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts, parent, false);
                return new PostViewHolder(view);

            }
        };


        rv.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();


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
        super.onBackPressed();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setPost(String post) {
            TextView post_title = mView.findViewById(R.id.post_desc);
            post_title.setText(post);
            Log.d(TAG, "It works");
        }
    }
}







