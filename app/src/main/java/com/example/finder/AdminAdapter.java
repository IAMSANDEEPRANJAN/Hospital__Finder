package com.example.finder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminviewHolder> {
    private Context mctx;//It provides access to things such as databases
    private List<Register> registerList;
    private static final String TAG = "AdminAdapter";
    DatabaseReference reference;

    public AdminAdapter(Context mctx, List<Register> registerList) {
        this.mctx = mctx;
        this.registerList = registerList;
    }

    @NonNull
    @Override
    // Implementations should assume that individual item views will hold strong references to ViewHolder objects and that RecyclerView
    public AdminviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
        LayoutInflater inflater=LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.listadmin,viewGroup,false);
        AdminviewHolder holder=new AdminviewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminviewHolder holder, int i) {


        final Register register=registerList.get(i);
        holder.t1.setText(register.getH_name());
        holder.t2.setText(register.getAddress());
        holder.ed1.setText(register.getStatus()+"");
        Log.e(TAG, "onBindViewHolder: " + registerList.size() );
        Log.e(TAG, "onBindViewHolder: hospital name " + register.getH_name() );
        Log.e(TAG, "onBindViewHolder: status "+ register.getStatus());

        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int updatedStatus = Integer.parseInt(holder.ed1.getText().toString().trim());
                Log.e(TAG, "onClick: new Value " + updatedStatus);
                //The entry point for accessing a Firebase Database. You can get an instance by calling getInstance(). To access a location in the database and read or write data, use getReference()
                reference = FirebaseDatabase.getInstance().getReference("Hospitals").child(register.getHospitalPushKey());
                Map<String, Object> updateStatusMap = new HashMap<>();
                updateStatusMap.put("status", updatedStatus);
                reference.updateChildren(updateStatusMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(mctx, "Updated", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(mctx, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return registerList.size();
    }

    class AdminviewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3;
        EditText ed1;
        Button b1;
        public View clickedItemView;

        public AdminviewHolder(@NonNull final View itemView) {
            super(itemView);
            clickedItemView = itemView;
            t1=itemView.findViewById(R.id.ad1);
            t2=itemView.findViewById(R.id.ad2);
            ed1=itemView.findViewById(R.id.ae);
            b1=itemView.findViewById(R.id.ab);


        }



    }
}
