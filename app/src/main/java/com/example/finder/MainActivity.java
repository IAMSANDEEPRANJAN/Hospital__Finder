package com.example.finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button Login_Page,Register_page,admin1;         //Intializing Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login_Page=findViewById(R.id.welcome);  //call button from activity_main.xml by id
        Register_page=findViewById(R.id.Login); //call button from activity_main.xml by id
        admin1=findViewById(R.id.Admin);//call button from activity_main.xml by id
        Login_Page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),Login_Activity.class);//on button click intent is used for move from this activity to login activity
                startActivity(i1);
            }
        });
        Register_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),RegisterActivity.class);//on button click intent is used for move from this activity to Register activity
                startActivity(i2);
            }
        });

        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getApplicationContext(),Admin.class);//on button click intent is used for move this activity to Admin activity
                startActivity(i3) ;
            }
        });
    }
}
