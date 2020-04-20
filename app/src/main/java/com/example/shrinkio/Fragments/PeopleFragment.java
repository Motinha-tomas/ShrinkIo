package com.example.shrinkio.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shrinkio.R;
import com.example.shrinkio.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PeopleFragment extends Fragment {

    ListView listView;
    private List<User> mUsers;
    private TextInputEditText search_bar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        //construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();

        //create the adapter to convert the array into view



        search_bar = view.findViewById(R.id.search_bar);
        mUsers = new ArrayList<>();
        readUsers();


        return view;
    }


    //Used to read the users from the database

    public void readUsers() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("Users");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> list = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("Name").getValue(String.class);
                    list.add(name);
                    Log.d("TAG", name);
                }
                //Out of the loop
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.user_item, list);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

    }

}
