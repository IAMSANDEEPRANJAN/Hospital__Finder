package com.example.finder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminRecycle extends AppCompatActivity {
    RecyclerView recyclerview;//RecyclerView in Android uses an adapter to represent the information we want to show as list
    AdminAdapter adapter;
    List<Register> registerList;
    DatabaseReference databaseReference;//A Firebase reference represents a particular location in your Database and can be used for reading or writing data to that Database location.
    private static final String TAG = "AdminRecycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_recycle);
        registerList=new ArrayList<>();
        adapter=new AdminAdapter(this,registerList);
        recyclerview=findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
       // The entry point for accessing a Firebase Database. You can get an instance by calling getInstance(). To access a location in the database and read or write data, use getReference()
        databaseReference= FirebaseDatabase.getInstance().getReference("Hospitals");
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    //Classes implementing this interface can be used to receive events about data changes at a location. Attach the listener to a location user addValueEventListener(ValueEventListener)
    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                Register register = snap.getValue(Register.class);
                registerList.add(register);
                Log.e(TAG, "onDataChange: "+ dataSnapshot.getChildren().iterator() );
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
