package com.demo.neoveticare;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class cartakerRegistrationActivity extends AppCompatActivity {


    private EditText fNameEd,lNameEd,emailAddEd,passEd,confirmpassword;

    private Button joinBtn;

    private FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartaker_registration);

        fNameEd=(EditText) findViewById(R.id.fname);
        lNameEd=(EditText) findViewById(R.id.lname);
        emailAddEd=(EditText) findViewById(R.id.eml);
        passEd=(EditText) findViewById(R.id.password);
        joinBtn=(Button) findViewById(R.id.register);

        //confirmpassword=(EditText)findViewById(R.id.confirmpasswordeditText);



        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = fNameEd.getText().toString().trim();
                String lastname = lNameEd.getText().toString().trim();

                String email = emailAddEd.getText().toString().trim();

                String password = passEd.getText().toString().trim();

                String confrmPasword= confirmpassword.getText().toString().trim();



                if(TextUtils.isEmpty(firstname))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(lastname))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter last name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter email name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter password name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confrmPasword))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter confirm password name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6)
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"PASWORD IS TOO SHORT", Toast.LENGTH_SHORT).show();
                }

                if (password.equals(confrmPasword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(cartakerRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {


                                                    startActivity(new Intent(getApplicationContext(), cartakerLoginActivity.class));
                                                    Toast.makeText(cartakerRegistrationActivity.this, "Registation Complete.please check your email for verification", Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    Toast.makeText(cartakerRegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });


                                    } else {

                                        Toast.makeText(cartakerRegistrationActivity.this,"Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
                else {
                    Toast.makeText(cartakerRegistrationActivity.this,"pasword dose not match", Toast.LENGTH_SHORT).show();
                }


            }


        });


    }


}
