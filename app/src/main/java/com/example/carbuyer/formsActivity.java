package com.example.carbuyer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class formsActivity extends AppCompatActivity {

    EditText name;
    RadioGroup rbtn,rb2,rb3,rb4,rb5,rb6,rb7;
    Button button;
    String uid;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms);

        name=findViewById(R.id.name);
        rbtn=findViewById(R.id.rbtn);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);
        rb5=findViewById(R.id.rb5);
        rb6=findViewById(R.id.rb6);
        rb7=findViewById(R.id.rb7);



        button=findViewById(R.id.sndout);
        String pass=getIntent().getStringExtra("pass");
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String cname=name.getText().toString().trim();

                        int radioButtonID = rbtn.getCheckedRadioButtonId();
                        RadioButton radioButton = (RadioButton) rbtn.findViewById(radioButtonID);
                        String tran = (String) radioButton.getText();

                        int radioButtonID2 = rb2.getCheckedRadioButtonId();
                        RadioButton radioButton2 = (RadioButton) rb2.findViewById(radioButtonID2);
                        String body = (String) radioButton2.getText();

                        int radioButtonID3 = rb3.getCheckedRadioButtonId();
                        RadioButton radioButton3 = (RadioButton) rb3.findViewById(radioButtonID3);
                        String fuel = (String) radioButton3.getText();

                        int radioButtonID4 = rb4.getCheckedRadioButtonId();
                        RadioButton radioButton4 = (RadioButton) rb4.findViewById(radioButtonID4);
                        String budget = (String) radioButton4.getText();

                        int radioButtonID5 = rb5.getCheckedRadioButtonId();
                        RadioButton radioButton5 = (RadioButton) rb5.findViewById(radioButtonID5);
                        String roadtype = (String) radioButton5.getText();

                        int radioButtonID6 = rb6.getCheckedRadioButtonId();
                        RadioButton radioButton6 = (RadioButton) rb6.findViewById(radioButtonID6);
                        String dailydrive = (String) radioButton6.getText();

                        int radioButtonID7 = rb7.getCheckedRadioButtonId();
                        RadioButton radioButton7 = (RadioButton) rb7.findViewById(radioButtonID7);
                        String maxpeople = (String) radioButton7.getText();

                        if(cname.isEmpty()){
                            name.setError("Please Enter Name");
                            name.requestFocus();
                        }

                        Forms car = new Forms(cname,tran,body,fuel,budget,roadtype,dailydrive,maxpeople);

                        FirebaseDatabase.getInstance().getReference("CarBuyer").child(pass).child("Form")
                                .setValue(car).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(formsActivity
                                            .this, "You form is submitted", Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(formsActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(formsActivity.this, "Fail ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }}
