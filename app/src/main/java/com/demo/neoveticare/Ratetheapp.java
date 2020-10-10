package com.demo.neoveticare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Ratetheapp extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    public Button btn1;
    public RatingBar ratingBar;
    float myrating=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main9);
        drawerLayout=findViewById(R.id.drawer_layout);
        mtoggle=new ActionBarDrawerToggle(this,drawerLayout, R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView=findViewById(R.id.nav_view);
navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.nav_home)
        {
            Toast.makeText(Ratetheapp.this,"home Page:",Toast.LENGTH_SHORT).show();
            d1();
        }
        if(item.getItemId()==R.id.Admin){
            Toast.makeText(Ratetheapp.this,"Admin Login page:",Toast.LENGTH_SHORT).show();
            d2();
        }
        if(item.getItemId()==R.id.security)
        {
            Toast.makeText(Ratetheapp.this,"Privacy & security Page:",Toast.LENGTH_SHORT).show();
            d3();
        }
        if(item.getItemId()==R.id.rating)
        {
            Toast.makeText(Ratetheapp.this,"Rate this app:",Toast.LENGTH_SHORT).show();
            d4();
        }
        if(item.getItemId()==R.id.share)
        {
            Toast.makeText(Ratetheapp.this,"Share the link of app by:",Toast.LENGTH_SHORT).show();
            d5();
        }
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
});
        btn1=findViewById(R.id.buttonrating);
        ratingBar=findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating=(int)v;
                String meassage=null;
                myrating=ratingBar.getRating();

                switch (rating){
                    case 1:
                        meassage="Sorry to hear That";
                    case 2:
                        meassage="You always accept suggestion!";
                    case 3:
                        meassage="Good enough:";
                    case 4:
                        meassage="Great! ThankYou:";
                    case 5:
                        meassage="Awesome! You are the Best:";
                }
                Toast.makeText(Ratetheapp.this,meassage,Toast.LENGTH_SHORT).show();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Ratetheapp.this,String.valueOf(myrating), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Help:
                Toast.makeText(this,"Help",Toast.LENGTH_SHORT).show();
                break;
        }{if(mtoggle.onOptionsItemSelected(item)){
            return true;
        }}
        return super.onOptionsItemSelected(item);
    }
    public void d2()
    {
        Intent intent=new Intent(Ratetheapp.this,Adminactivity.class);
        startActivity(intent);
    }
    public void d3()
    {
        Intent intent=new Intent(Ratetheapp.this,privaceandsecurity.class);
        startActivity(intent);
    }
    public void d1(){
        Intent intent=new Intent(Ratetheapp.this,MainActivity.class);
        startActivity(intent);
    }
    public void d4()
    {
        Intent intent=new Intent(Ratetheapp.this,Ratetheapp.class);
        startActivity(intent);
    }
    public void d5()
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody="Your Body Here";
        String sharesub="Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
        intent.putExtra(Intent.EXTRA_TEXT,sharebody);
        startActivity(Intent.createChooser(intent,"Share using"));
    }
}