package com.demo.neoveticare;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class cartakerLoginActivity extends AppCompatActivity {

    private EditText emailAddEd,passEd;
    private Button joinBtn;
    TextView registrationTextVIEW,cartaker;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartaker_login);



        emailAddEd=(EditText) findViewById(R.id.email);
        passEd=(EditText) findViewById(R.id.pass);
        joinBtn=(Button) findViewById(R.id.login);
        //registrationTextVIEW=(TextView)findViewById(R.id.registrationTextView);
        //cartaker=(TextView)findViewById(R.id.cartaker);
/*
        cartaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cartakerLoginActivity.this,naniesUpload.class);
                startActivity(intent);
            }
        });


 */



        registrationTextVIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cartakerLoginActivity.this,cartakerRegistrationActivity.class);
                startActivity(intent);
            }
        });


//        getSupportActionBar().hide();

        registrationTextVIEW.setPaintFlags(registrationTextVIEW.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailAddEd.getText().toString().trim();

                String password = passEd.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(cartakerLoginActivity.this,"Please enter email name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(cartakerLoginActivity.this,"Please enter password name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6)
                {
                    Toast.makeText(cartakerLoginActivity.this,"PASWORD IS TOO SHORT", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth= FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(cartakerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                        startActivity(new Intent(getApplicationContext(), naniesUpload.class));
                                    }
                                    else {
                                        Toast.makeText(cartakerLoginActivity.this,"please verified your email address", Toast.LENGTH_SHORT).show();

                                    }
                                } else {

                                    Toast.makeText(cartakerLoginActivity.this,"login Failed", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });


            }
        });
    }


}
