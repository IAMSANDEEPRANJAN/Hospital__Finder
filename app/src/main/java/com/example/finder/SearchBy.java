package com.example.finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchBy extends AppCompatActivity {

    RecyclerView recyclerview;//RecyclerView in Android uses an adapter to represent the information we want to show as list.
    RegisterAdapter adapter;
    List<Register> registerList;
    private static final String TAG = "SearchBy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by);
        registerList=new ArrayList<>();
        recyclerview=findViewById(R.id.recycler_view_for_h_list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String searchBy = intent.getStringExtra("searchType");
        String searchQuery = intent.getStringExtra("searchQuery");
        adapter=new RegisterAdapter(this,registerList);
        recyclerview.setAdapter(adapter);


        //Retrive data from database where searchby equal to search query and adim given the permission
        FirebaseDatabase.getInstance().getReference().child("Hospitals").orderByChild(searchBy).equalTo(searchQuery).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    Register reg = snap.getValue(Register.class);
                    if (reg.getStatus()==1) {
                        registerList.add(reg);
                    }
                    Log.d(TAG, "onDataChange: size "+ registerList.size());
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}