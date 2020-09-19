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

public class LoginActivity extends AppCompatActivity {

    private EditText emailAddEd,passEd;
    private Button joinBtn;
    TextView registrationTextVIEW,cartaker;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        emailAddEd=(EditText) findViewById(R.id.LogEmaileditText);
        passEd=(EditText) findViewById(R.id.LogpasswordeditText);
        joinBtn=(Button) findViewById(R.id.Logjoinbtn);
        registrationTextVIEW=(TextView)findViewById(R.id.registrationTextView);
        cartaker=(TextView)findViewById(R.id.cartaker);

        cartaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,cartakerLoginActivity.class);
                startActivity(intent);
            }
        });




        registrationTextVIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
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
                    Toast.makeText(LoginActivity.this,"Please enter email name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this,"Please enter password name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6)
                {
                    Toast.makeText(LoginActivity.this,"PASWORD IS TOO SHORT", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth= FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this,"please verified your email address", Toast.LENGTH_SHORT).show();

                                    }
                                } else {

                                    Toast.makeText(LoginActivity.this,"login Failed", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });


            }
        });
    }


}
