package com.example.carbuyer;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class regactivityCB extends AppCompatActivity {

    EditText Bname,ptmbno,ptmail,ptcpass,ptpass,ptdmail,ptage;
    RadioGroup ptgender;
    Button register;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regactivity);
        mAuth = FirebaseAuth.getInstance();
        Bname=findViewById(R.id.ptname);
        ptmbno=findViewById(R.id.ptmbno);
        ptmail=findViewById(R.id.ptmail);
        ptpass=findViewById(R.id.ptpass);
        ptcpass=findViewById(R.id.ptcpass);
        ptdmail=findViewById(R.id.ptdmail);
        ptage=findViewById(R.id.ptage);
        ptgender=findViewById(R.id.ptgender);
        register=findViewById(R.id.radioButton);
        progressBar=findViewById(R.id.progressBar2);




    }
    public void Registerbuttonclicked(View v){

        String name=Bname.getText().toString().trim();
        String mbno=ptmbno.getText().toString().trim();
        String mail=ptmail.getText().toString().trim();
        String ppass=ptpass.getText().toString().trim();
        String cpass=ptcpass.getText().toString().trim();
        String cmail=ptdmail.getText().toString().trim();
        String age=ptage.getText().toString().trim();
        int radioButtonID = ptgender.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) ptgender.findViewById(radioButtonID);
        String gender = (String) radioButton.getText();

        if(name.isEmpty()){
            Bname.setError("Please Enter Name");
            Bname.requestFocus();
        }
        if(mbno.isEmpty() ){
            ptmbno.setError("Please Enter Mobile No");
            ptmbno.requestFocus();
        }
        if(mbno.length()>10 ){
            ptmbno.setError("Mobile No should not more than 10 digit");
            ptmbno.requestFocus();
        }
        if(mail.isEmpty()){
            ptmail.setError("Please Enter Mail ID");
            ptmail.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            ptmail.setError("Please Enter valid Email");
            ptmail.requestFocus();
        }

        if(ppass.isEmpty() || ppass.length() < 8 ){
            ptpass.setError("Please Enter Password containing atleast 8 digit");
            ptpass.requestFocus();
        }
        if(cpass.isEmpty() || cpass.length() < 8 ||cpass.length() > 8){
            ptcpass.setError("Please Enter Password of 8 digit");
            ptcpass.requestFocus();
        }
        if(!cpass.equals(ppass)){
            ptcpass.setError("Password Didn't Match");
            ptcpass.requestFocus();
        }
        if(cmail.isEmpty()){
            ptdmail.setError("Please Enter Email Address");
            ptdmail.requestFocus();
        }
//        if(!cmail.equals(mail)){
//            ptdmail.setError("Both Email Didn't Match");
//            ptdmail.requestFocus();
//        }
        if(age.isEmpty()){
            ptage.setError("Please Enter Age");
            ptage.requestFocus();
        }
        if(gender.isEmpty()){
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        }

        progressBar.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(mail,cpass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            CarBuyer CB = new CarBuyer(name, mbno, mail, cpass, cmail, age, gender);

                            FirebaseDatabase.getInstance().getReference("CarBuyer").child(cpass)
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                             .setValue(CB).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(regactivityCB.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
//                                        Intent intent = new Intent(regactivity.this, fragment1.class);
//                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(regactivityCB.this, "Fail to Register ", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }

                            });
                        }

                        else{
                            Toast.makeText(regactivityCB.this, "Fail to Register ", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE); }

                    }

                });
    }
}