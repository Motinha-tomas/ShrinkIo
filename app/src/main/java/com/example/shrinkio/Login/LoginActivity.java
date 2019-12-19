package com.example.shrinkio.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.example.shrinkio.MainActivities.BottomActivity;
import com.example.shrinkio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText mEmail, mPassword, mName, mAge, mCountry;
    Button Register;
    Spinner spinner;

    RadioGroup radioGroup2;
    RadioButton radioBtn, radioBtn2;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final int CHOOSE_IMAGE = 101;


    String[] SPINNERLIST = {"Select a Country...", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria",
            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina",
            "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African", "Chad",
            "Chile", "China", "Colombia", "Comoros", "Democratic Republic of the Congo", "Costa Rica", "Côte d’Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic",
            "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor (Timor-Leste)", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France",
            "Gabon", "The Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
            "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya",
            "Kiribati", "Korea, North", "Korea, South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
            "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius",
            "Mexico", "Micronesia, Federated States of", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)", "Namibia", "Nauru",
            "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Macedonia", "Norway", "Oman",
            "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis",
            "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore",
            "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "Spain", "Sri Lanka", "Sudan", "Sudan, South", "Suriname", "Sweden", "Switzerland", "Syria",
            "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago",
            "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu",
            "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        MultiDex.install(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        Objects.requireNonNull(getSupportActionBar()).hide();


        mEmail = findViewById( R.id.email );
        mPassword = findViewById( R.id.password );
        Register = findViewById( R.id. Register);
        mName = findViewById(R.id.name);
        mAge = findViewById(R.id.Age);
        mCountry = findViewById(R.id.Country);
        spinner = findViewById(R.id.spinner);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, SPINNERLIST);
        final Spinner betterSpinner = spinner;
        betterSpinner.setAdapter(arrayAdapter);




        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                startActivity(new Intent(LoginActivity.this, BottomActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return;
            }
        };

        final String es = "Email Sent";


        Register.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                 firebaseAuth.createUserWithEmailAndPassword(
                         mEmail.getText().toString(),
                         mPassword.getText().toString())

                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginActivity.this, Forms.class));
                            overridePendingTransition(0,0);


                            String user_id = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                            String name = mName.getText().toString();
                            String age = mAge.getText().toString();
                            String spinner = betterSpinner.getSelectedItem().toString();

                            Map newPost = new HashMap();
                            newPost.put("Name", name);
                            newPost.put("Age", age);
                            newPost.put("Country", spinner);

                            current_user_db.setValue(newPost);
                        }
                        }
                } );
            }
        } );

        radioGroup2 = findViewById( R.id.radioGroup3 );
        radioBtn = findViewById( R.id.radioBtn );
        radioBtn2 = findViewById( R.id.radioBtn2 );
        radioGroup2.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioBtn.isChecked()) {
                    startActivity( new Intent( LoginActivity.this, LoginActivity.class ) );
                    overridePendingTransition( 0, 0 );
                }if (radioBtn2.isChecked()) {
                        startActivity( new Intent( LoginActivity.this, LoginActivity2.class ) );
                        overridePendingTransition( 0, 0 );
                    }

            }
        } );

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}



