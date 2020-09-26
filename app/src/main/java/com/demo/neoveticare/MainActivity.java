package com.demo.neoveticare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnbabysitter(View view) {
        Intent intent=new Intent(MainActivity.this,cartakerLoginActivity.class);
        startActivity(intent);
    }

    public void btncaretaker(View view) {
        Intent intent=new Intent(MainActivity.this,cartakerLoginActivity.class);
        startActivity(intent);
    }

    public void btnboth(View view) {
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}