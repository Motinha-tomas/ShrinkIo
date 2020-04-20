package com.example.shrinkio.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.example.shrinkio.Fragments.HomeFragment;
import com.example.shrinkio.R;
import com.example.shrinkio.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button welcome;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    private DatabaseReference mDataBase;
    private FirebaseAuth mAuth;
    ArrayAdapter adapter_city;

    ArrayList<String> City, array_Afghanistan, Akrotiri, Algeria, aAnguila, Antarctida, Australia, Austria, Azerbaijan, Bahamas,
            Bangladesh, Belgium, Bermudas, Belarus, Bolivia, Bósnia, Brazil, Bulgaria, Cabo_Verde, Camaroon, Canada, Cazaquistan,
            Chad, Chile, China, Cyprus, Colombia, CostaRica, Croatia, Cuba, Denmark, Dominica,
            Egypt, Estonia, Finland, France, Germany, Greece, Grenada, Guatemala, Haiti, Honduras, Hungary,
            India, Indonesia, Iran, Iraq, Ireland, Israel, Italy, Jamaica, Japan, Jordan, Kazakhstan, Kenya,
            Kuwait, Kyrgyzstan, Laos, Lebanon, Liberia, Libya, Lithuania, Luxembourg, Madagascar, Malawi, Malaysia,
            Maldives, Mali, Mauritania, Mexico, Moldova, Monaco, Mongolia, Morocco, Mozambique, Myanmar, Namibia,
            Nepal, Netherlands, New_Zealand, Nicaragua, Niger, Nigeria, North_Korea, North_Macedonia, Norway, Oman, Pakistan,
            Palestine_State, Panama, Papua_New_Guinea, Paraguay, Peru, Philippines, Poland, Portugal, Qatar, array_Romania,
            Russia, Rwanda, Saudi_Arabia, Senegal, Serbia, Sierra_Leone, Singapore, Slovakia, Slovenia, Somalia, South_Africa, South_Korea,
            South_Sudan, Spain, Sri_Lanka, Sudan, Sweden, Switzerland, Syria, Tajikistan, Tanzania, Thailand, Timor_Leste, Togo, Trinidad_and_Tobago,
            Tunisia, Turkey, Turkmenistan, Uganda, Ukraine, U_A_E, United_Kingdom, United_States_of_America, Uruguay, Uzbekistan, Venezuela, Vietnam,
            Yemen, Zambia, Zimbabwe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        MultiDex.install(this);


        firebaseAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        //Cities

        City = new ArrayList<>();
        City.add("Where are you from?");

        array_Afghanistan = new ArrayList<>();
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kandahar");
        array_Afghanistan.add("Herat");
        array_Afghanistan.add("Mazar-i-Sharif");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");
        array_Afghanistan.add("Kabul");

        Akrotiri = new ArrayList<>();
        Akrotiri.add("AFJF");
        Akrotiri.add("AFFURALJFJARSJF");
        Akrotiri.add("AFRFOJRJF");
        Akrotiri.add("FRAIJF");
        Akrotiri.add("PKFPKAPF");
        Akrotiri.add("FRJFIOSF");
        Akrotiri.add("FO;IRJOAJF");

        Algeria = new ArrayList<>();
        Algeria.add("DNAJSGJLG");
        Algeria.add("DNAJSGJLG");
        Algeria.add("DNAJSGJLG");
        Algeria.add("DNAJSGJLG");
        Algeria.add("DNAJSGJLG");
        Algeria.add("DNAJSGJLG");
        Algeria.add("DNAJSGJLG");
        Algeria.add("DNAJSGJLG");

        Portugal = new ArrayList<>();
        Portugal.add("LISBOA");
        Portugal.add("PORT");


        //************      SPINNER FOR COUNTRY AND CITY      ************

        Spinner spinner = findViewById(R.id.spinner_country);
        Spinner spinner1 = findViewById(R.id.spinner_city);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner1.setAdapter(adapter_city);
        spinner.setOnItemSelectedListener(this);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if (i == 0) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Afeghanistan, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                }

                if (i == 1) {
                    adapter_city = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, Akrotiri);
                }

                if (i == 2) {
                    adapter_city = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, Algeria);
                }

                if (i == 3) {
                    adapter_city = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, Portugal);
                }

                spinner1.setAdapter(adapter_city);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mDataBase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();


        welcome = findViewById(R.id.welcome);

        welcome.setOnClickListener(view -> {


            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users");
            String userId = mDataBase.push().getKey();

            String city = spinner.getContext().toString();
            String country = spinner1.getContext().toString();
            User user = new User(country, city);
            mDataBase.child(userId).setValue(user);


            if (validateForm()) {
                if (mAuth.getCurrentUser() != null) {
                    Intent i = new Intent(LoginActivity3.this, HomeFragment.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);

                    String user_id = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                    String Country = spinner.getContext().toString().trim();
                    String City = spinner1.getContext().toString().trim();

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").push();
                    mDataBase.child("Users").child("Name").setValue(Country);
                    mDataBase.child("Users").child("Country").setValue(City);
                    mDataBase.child("Users").setValue(firebaseUser);


                    Map newPost = new HashMap();
                    newPost.put("Country", Country);
                    newPost.put("City", City);


                    current_user_db.setValue(newPost);







                }
            }
        });
    }


    public boolean validateForm() {
        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner1 = findViewById(R.id.spinner_city);

        String Country = spinner.getContext().toString();
        String CITY = spinner1.getContext().toString().trim();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String country = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), country, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


