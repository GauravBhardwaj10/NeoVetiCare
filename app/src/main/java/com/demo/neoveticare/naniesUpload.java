package com.demo.neoveticare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class naniesUpload extends AppCompatActivity {


    Button forchildren,forsenior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanies_upload);

        forchildren=findViewById(R.id.childern);
        forsenior=findViewById(R.id.seniorCitizen);


        forchildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(naniesUpload.this,ImageFirebaseUploadDemo.class);
                startActivity(intent);

            }
        });

        forsenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(naniesUpload.this,imageUploadsenior.class);
                startActivity(intent);

            }
        });


    }
}