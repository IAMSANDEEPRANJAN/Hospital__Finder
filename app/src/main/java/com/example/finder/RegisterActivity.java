package com.example.finder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10;
    Button b1;
    Spinner state;
    DatabaseReference databaseReference;//A Firebase reference represents a particular location in your Database and can be used for reading or writing data to that Database location.
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //findViewById is a method that finds the view from the layout resource file that are attached with current Activity.
        //R is a Class in android that are having the id’s of all the view’s
        b1 = findViewById(R.id.Register);
        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.phone_no);
        ed4=findViewById(R.id.specialist);
        ed5 = findViewById(R.id.city);
        ed6 = findViewById(R.id.Address);
        ed7 = findViewById(R.id.Area);
        ed8 = findViewById(R.id.cityr);
        ed9 = findViewById(R.id.pincode);
        ed10=findViewById(R.id.manager);
        FirebaseApp.initializeApp(this);
        state = findViewById(R.id.select);

        String[] items = new String[]{"State",};
        // An adapter is an object that provides views for a list view. Whenever list view needs to draw a view at a particular list position, it gets it from the adapter.

        // ArrayAdapter, it actually stores the data in a list.
        ArrayAdapter<String> ada = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.india_states));
        state.setAdapter(ada);
        //The entry point for accessing a Firebase Database. You can get an instance by calling getInstance(). To access a location in the database and read or write data, use getReference().
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Hospitals");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Default constuctor called
                Adddata();
            }

            public void Adddata() {
                //the getText() method get the content inside editText and toString() will convert it to string format.
                String H_name = ed1.getText().toString().trim().toLowerCase();
                String Reg_no = ed2.getText().toString().trim();
                String State = state.getSelectedItem().toString().trim();
                String contact = ed5.getText().toString().trim();
                String Address = ed6.getText().toString().trim().toLowerCase();
                String Area = ed7.getText().toString().trim().toLowerCase();
                String City = ed8.getText().toString().trim().toLowerCase();
                String Pincode = ed9.getText().toString().trim();
                String Speciality=ed4.getText().toString().trim().toLowerCase();
                String manager=ed10.getText().toString().trim();

                //databaseReference.child(name);
                String hospitalPushKey = databaseReference.push().getKey();
                Register reg = new Register(H_name, Reg_no, State, contact, Address, Area, City, Pincode, Speciality, 2, hospitalPushKey,manager);
                //sucessfully registered and value will store in unique key
                databaseReference.child(hospitalPushKey).setValue(reg).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //On succesfully registration alert dialog will generate
                        new AlertDialog.Builder(RegisterActivity.this).setTitle("Registered Successfully")
                                .setMessage("Your hospital succesfully registered waiting for verification")
                                .setIcon(R.drawable.ic_check_24dp)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        finish();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //on failure alert will generate
                        new AlertDialog.Builder(RegisterActivity.this).setTitle("Registered Unsuccessful")
                                .setMessage("Some Error occurred, Please Retry")
                                .setIcon(R.drawable.ic_check_24dp)
                                .setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        finish();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                });
            }
        });
    }
}
