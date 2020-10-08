package com.demo.neoveticare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CareTakerDescription extends AppCompatActivity {
    TextView ctgender,ctname,ctcity,ctprovince,currency,ctexperience,ctrate,ctdesc,ctavaialbility;
    ImageView ctimg;
    Button Hire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_taker_description);

        Intent in=getIntent();
        String name=in.getStringExtra("name");
        String price=in.getStringExtra("price");
        String city=in.getStringExtra("city");
        String gender=in.getStringExtra("gender");
        String province=in.getStringExtra("province");
        String writaboutyourself=in.getStringExtra("writaboutyourself");
        String rate=in.getStringExtra("rate");
        String experience=in.getStringExtra("experience");
        String availability=in.getStringExtra("availablity");

        //ctimg=(ImageView)findViewById(R.id.ctimage);
        ctname=(TextView)findViewById(R.id.ctname);
        ctgender=(TextView)findViewById(R.id.ctgender);
        ctcity=(TextView)findViewById(R.id.ctcity);
        ctprovince=(TextView)findViewById(R.id.ctprovince);
        ctdesc=(TextView)findViewById(R.id.ctDesc);
        currency=(TextView)findViewById(R.id.currency);
        ctrate=(TextView)findViewById(R.id.ctrate);
        ctexperience=(TextView)findViewById(R.id.experience);
        ctavaialbility=(TextView)findViewById(R.id.ctavailibility);

        ctname.setText(name);
        ctgender.setText(gender);
        ctcity.setText(city);
        ctprovince.setText(province);
        ctdesc.setText(writaboutyourself);
        ctrate.setText(rate);
        ctexperience.setText(experience);
        ctavaialbility.setText(availability);

    }
}