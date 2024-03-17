package com.example.carbuyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class expertview extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    Button sndout;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expertview);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        t6=findViewById(R.id.t6);
        t7=findViewById(R.id.t7);
        t8=findViewById(R.id.t8);
        sndout=findViewById(R.id.sndout);

        String pass=getIntent().getStringExtra("pass").toString();
//        String mbno=getIntent().getStringExtra("mbno").toString();
        reference = FirebaseDatabase.getInstance().getReference("CarBuyer").child(pass);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String body=snapshot.child("Form").child("body").getValue(String.class);
                String budget=snapshot.child("Form").child("budget").getValue(String.class);
                String cname=snapshot.child("Form").child("cname").getValue(String.class);
                String dailydrive=snapshot.child("Form").child("dailydrive").getValue(String.class);
                String fuel=snapshot.child("Form").child("fuel").getValue(String.class);
                String maxpeople=snapshot.child("Form").child("maxpeople").getValue(String.class);
                String roadtype=snapshot.child("Form").child("roadtype").getValue(String.class);
                String tran=snapshot.child("Form").child("tran").getValue(String.class);

                t1.setText(cname);
                t2.setText(tran);
                t3.setText(body);
                t4.setText(fuel);
                t5.setText(budget);
                t6.setText(roadtype);
                t7.setText(dailydrive);
                t8.setText(maxpeople);
                sndout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(expertview.this,sms.class);
                        i.putExtra("mbno",cname);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}