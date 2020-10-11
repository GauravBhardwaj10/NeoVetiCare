package com.demo.neoveticare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Select_Category extends AppCompatActivity {


    Button seniors,kids;
    private RadioGroup radioSTimerGroup,radioCTimerGroup;
    private RadioButton radioSTimerButton,radioCTimerButton;
    String category="";
    ImageView backbtn;
    String Value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__category);
        seniors=(Button) findViewById(R.id.senior);
        kids=(Button) findViewById(R.id.kids);
        radioSTimerGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioCTimerGroup=(RadioGroup)findViewById(R.id.radioGroupchild);
        backbtn =(ImageView) findViewById( R.id.backimg);
        seniors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioSTimerGroup.setVisibility(View.VISIBLE);
                kids.setVisibility(View.GONE);
                seniors.setVisibility(View.GONE);
                category="senior";
//                int selectedId=radioSTimerGroup.getCheckedRadioButtonId();
//                radioSTimerButton=(RadioButton)findViewById(selectedId);
//
//                String radioval= (String) radioSTimerButton.getText();
//                if(radioSTimerButton.getText().toString().equals("Full Time")){
//                    Value="seniorfulltime";
//                }else{
//                    Value="seniorparttime";
//                }
            }
        });

        radioSTimerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioSTimerButton = (RadioButton) findViewById(checkedId);
                if (radioSTimerButton.getText().toString().equals("Full Time")) {
                    Value = "seniorfulltime";
                } else {
                    Value = "seniorparttime";
                }
            }
        });

        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioCTimerGroup.setVisibility(View.VISIBLE);
                kids.setVisibility(View.GONE);
                seniors.setVisibility(View.GONE);
                category="kid";
                int selectedId=radioCTimerGroup.getCheckedRadioButtonId();
                radioCTimerButton=(RadioButton)findViewById(selectedId);

            }
        });

        radioCTimerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioCTimerButton = (RadioButton) findViewById(checkedId);

                if(radioCTimerButton.getText().toString().equals("Full Time")){
                    Value="uploadchildernfulltime";
                }else{
                    Value="childernparttime";
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(category.equals("senior")){
                radioSTimerGroup.setVisibility(View.GONE);
                radioCTimerGroup.setVisibility(View.GONE);

                kids.setVisibility(View.VISIBLE);
                seniors.setVisibility(View.VISIBLE);
            }else if(category.equals("kid")){
                radioCTimerGroup.setVisibility(View.GONE);
                radioSTimerGroup.setVisibility(View.GONE);

                kids.setVisibility(View.VISIBLE);
                seniors.setVisibility(View.VISIBLE);
            }else{
                System.exit(0);
            }
            }
        });


        Button nxt=(Button) findViewById(R.id.nxtpage);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Select_Category.this, RetreivedataActivity.class);
                in.putExtra("category",Value);
                startActivity(in);
            }
        });
    }

}