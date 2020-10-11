package com.demo.neoveticare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class displayProfile extends AppCompatActivity {

    ImageView getImageview;
    TextView firstnametextview,phonetexview,addresstexview,writeaboutyourselftexview,experiencetexview,timingstexview,
           jobtypetexview, gendertexview,proviencetexview,citytexview,emailaddresstexview,pricetexview,agetexview;
    Button returnhome;

    private LinearLayoutManager linearLayoutManager;
    private List<ModelClass> itemlist;
    private modelAdapter modelAdapter;
    ArrayList<String> schedulelist=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        getImageview=findViewById(R.id.profilepicture);
        firstnametextview=findViewById(R.id.firstname);
        phonetexview=findViewById(R.id.phonedisplayprofile);
        addresstexview=findViewById(R.id.addressdisplayprofile);
        writeaboutyourselftexview=findViewById(R.id.writeaboutyourselfisplayprofile);
        experiencetexview=findViewById(R.id.experiencedisplayprofile);
        timingstexview=findViewById(R.id.timingsdisplayprofile);
        jobtypetexview=findViewById(R.id.jobTypedisplayprofile);
        gendertexview=findViewById(R.id.genderdisplayprofile);
        proviencetexview=findViewById(R.id.proviencedisplayprofile);
        pricetexview=findViewById(R.id.pricedisplayprofile);
        citytexview=findViewById(R.id.citydisplayprofile);
        emailaddresstexview=findViewById(R.id.emailaddressdisplayprofile);
        agetexview=findViewById(R.id.agedisplayprofile);
        returnhome=findViewById(R.id.returnbutton);


        String imageurl=getIntent().getStringExtra("url");
        String firstname=getIntent().getStringExtra("firstname");
        String phone=getIntent().getStringExtra("phone");
        String addrress=getIntent().getStringExtra("address");
        String writeaboutyouself=getIntent().getStringExtra("writeaboutyourself");
        String experience=getIntent().getStringExtra("experience");
        String timings=getIntent().getStringExtra("timings");
        String jobtype=getIntent().getStringExtra("jobtype");
        String gender=getIntent().getStringExtra("gender");
        String provience=getIntent().getStringExtra("provience");;
        String city=getIntent().getStringExtra("city");
        String emailaddress=getIntent().getStringExtra("emailaddress");
        String price=getIntent().getStringExtra("price");
        String age=getIntent().getStringExtra("age");

        Intent intent=getIntent();
        schedulelist=intent.getStringArrayListExtra("arraylist");


        System.out.println("imageurl"+imageurl);
        Picasso.with(displayProfile.this)
                .load(imageurl)
                .placeholder(R.mipmap.ic_launcher)
                .into(getImageview);

        firstnametextview.setText(firstname);
        phonetexview.setText(phone);
        addresstexview.setText(addrress);
        writeaboutyourselftexview.setText(writeaboutyouself);
        experiencetexview.setText(experience);
        timingstexview.setText(timings);
        jobtypetexview.setText(jobtype);
        gendertexview.setText(gender);
        proviencetexview.setText(provience);
        citytexview.setText(city);
        emailaddresstexview.setText(emailaddress);
        pricetexview.setText(price);
        agetexview.setText(age);


        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recyclerlistview);

        recyclerView.setLayoutManager(linearLayoutManager);
        modelAdapter=new modelAdapter(this,schedulelist);

        // linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(modelAdapter);
        //itemlist.add((ModelClass) schedulelist);


    }
}