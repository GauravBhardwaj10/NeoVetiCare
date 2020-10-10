package com.demo.neoveticare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class naniesUpload extends AppCompatActivity {


    Button forchildren,forsenior;
    TextView textViewemail,textViewfirstname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanies_upload);

        forchildren=findViewById(R.id.childern);
        forsenior=findViewById(R.id.seniorCitizen);
        textViewemail=(TextView)findViewById(R.id.emailtextview);
        textViewfirstname=(TextView)findViewById(R.id.firstnametextview);

        String firstname=getIntent().getStringExtra("firstname");
        String email=getIntent().getStringExtra("email");

        textViewfirstname.setText(firstname);
        textViewemail.setText(email);



        forchildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstnametextview=textViewfirstname.getText().toString().trim();
                String emailtextview=textViewemail.getText().toString().trim();
                Intent intent = new Intent(naniesUpload.this, ImageFirebaseUploadDemo.class);
                intent.putExtra("email", emailtextview);
                intent.putExtra("firstname", firstnametextview);
                startActivity(intent);

            }
        });

        forsenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstnametextview=textViewfirstname.getText().toString().trim();
                String emailtextview=textViewemail.getText().toString().trim();
                Intent intent = new Intent(naniesUpload.this, imageUploadsenior.class);
                intent.putExtra("email", emailtextview);
                intent.putExtra("firstname", firstnametextview);
                startActivity(intent);

            }
        });


    }
}