package com.demo.neoveticare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {
    public Button bt1,bt2,bt3,bt4,bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        bt1=findViewById(R.id.a1);
        bt2=findViewById(R.id.a2);
        bt3=findViewById(R.id.a3);
        bt4=findViewById(R.id.a4);
        bt5=findViewById(R.id.a5);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
a11();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
a22();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
a33();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
a44();
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
a55();
            }
        });
    }
    public void a11()
    {
        Intent intent=new Intent(Help.this,layout1.class);
        startActivity(intent);
    }
    public void a22()
    {
        Intent intent=new Intent(Help.this,layout2.class);
        startActivity(intent);
    }
    public void a33(){
        Intent intent=new Intent(Help.this,layout3.class);
        startActivity(intent);
    }
    public void a44()
    {
        Intent intent=new Intent(Help.this,layout4.class);
        startActivity(intent);
    }
    public void a55()
    {
        Intent intent=new Intent(Help.this,layout5.class);
        startActivity(intent);
    }
}