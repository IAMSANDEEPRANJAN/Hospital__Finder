package com.example.finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    EditText username,password1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        username=findViewById(R.id.UserName);
        password1=findViewById(R.id.password);
        b1=findViewById(R.id.submit);

       // String password=password1.getText().toString().trim();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String name=username.getText().toString().trim();
                final String password=password1.getText().toString().trim();
                if (name.equals("deepak")&&password.equals("1234")) {
                    Intent i1=new Intent(getApplicationContext(),AdminRecycle.class);
                    startActivity(i1);
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "please enter right username and password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
