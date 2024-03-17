package com.example.carbuyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class expertmain extends AppCompatActivity {
    ListView carbuyers;
    ArrayList<String> myArraylist=new ArrayList<>();
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expertmain);

        ArrayAdapter<String> myArrayAdapter= new ArrayAdapter<String>(expertmain.this, android.R.layout.simple_expandable_list_item_1,myArraylist);

        carbuyers=findViewById(R.id.emerpai);
        carbuyers.setAdapter(myArrayAdapter);

        reference = FirebaseDatabase.getInstance().getReference("CarBuyer");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String pass = snapshot.child("cpass").getValue(String.class);
                    String mbno=snapshot.child("mbno").getValue(String.class);
                    String name=snapshot.child("name").getValue(String.class);

                    String value="Mobile No: "+mbno+"\nId: "+pass+"\nCar Buyer: "+name;

                    myArrayAdapter.add(value);

                    myArrayAdapter.notifyDataSetChanged();


                    carbuyers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String data = parent.getItemAtPosition(position).toString();
                            String mbno=data.substring(11,22);
                            String pass=data.substring(26,34);
                             Intent intent=new Intent(expertmain.this,expertview.class);
                            intent.putExtra("mbno",mbno);
                            intent.putExtra("pass",pass);
                            startActivity(intent);

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}