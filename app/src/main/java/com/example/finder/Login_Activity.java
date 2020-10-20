package com.example.finder;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {
    Button b1;
    EditText e1,location;
    Register reg;
    FirebaseAuth auth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Hospitals");//A Firebase reference represents a particular location in your Database and can be used for reading or writing data to that Database location.
    String searchBy;
    Query query;//The value of the annotation includes the query that will be run when this method is called

    Spinner find;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        e1 = findViewById(R.id.login_id);
        b1 = findViewById(R.id.welcome);
        find=findViewById(R.id.select);
       // reg = new Register();
        String value=e1.getText().toString().trim();
        value.toLowerCase();
        String[] items = new String[]{"State",};
        ArrayAdapter<String> adaa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.find_by));
        find.setAdapter(adaa);
        find.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        e1.setHint("please select from dropdown");
                        e1.setEnabled(false);
                        e1.setText("");
                        b1.setEnabled(false);
                        break;
                    case 1:
                        e1.setInputType(InputType.TYPE_CLASS_NUMBER);
                        e1.setEnabled(true);
                        e1.setHint("Enter pincode");
                        e1.setText("");
                        b1.setEnabled(true);
                        searchBy = "pincode";
                        break;
                    case 2:
                        e1.setInputType(InputType.TYPE_CLASS_TEXT);
                        e1.setEnabled(true);
                        e1.setHint("Enter speciality");
                        e1.setText("");
                        b1.setEnabled(true);
                        searchBy = "speciality";
                        break;
                    case 3:
                        e1.setInputType(InputType.TYPE_CLASS_TEXT);
                        e1.setEnabled(true);
                        e1.setHint("Enter hospital name");
                        e1.setText("");
                        b1.setEnabled(true);
                        searchBy = "h_name";
                        break;
                    case 4:
                        e1.setInputType(InputType.TYPE_CLASS_TEXT);
                        e1.setEnabled(true);
                        e1.setHint("Enter hospital locality");
                        e1.setText("");
                        b1.setEnabled(true);
                        searchBy = "area";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchQueryString = e1.getText().toString().trim().toLowerCase();
                query = databaseReference.orderByChild(searchBy).equalTo(searchQueryString);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Intent i=new Intent(Login_Activity.this,SearchBy.class);
                            i.putExtra("searchType", searchBy);
                            i.putExtra("searchQuery", searchQueryString);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Login_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
    }

