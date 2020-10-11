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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class privaceandsecurity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main10);
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
                    Toast.makeText(privaceandsecurity.this,"home Page:",Toast.LENGTH_SHORT).show();
                    e1();
                }
                if(item.getItemId()==R.id.Admin){
                    Toast.makeText(privaceandsecurity.this,"Admin Login page:",Toast.LENGTH_SHORT).show();
                    e2();
                }
                if(item.getItemId()==R.id.security)
                {
                    Toast.makeText(privaceandsecurity.this,"Privacy & security Page:",Toast.LENGTH_SHORT).show();
                    e3();
                }
                if(item.getItemId()==R.id.rating)
                {
                    Toast.makeText(privaceandsecurity.this,"Rate this app:",Toast.LENGTH_SHORT).show();
                    e4();
                }
                if(item.getItemId()==R.id.share)
                {
                    Toast.makeText(privaceandsecurity.this,"Share the link of app by:",Toast.LENGTH_SHORT).show();
                    e5();
                }
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
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
    public void e2()
    {
        Intent intent=new Intent(privaceandsecurity.this,Adminactivity.class);
        startActivity(intent);
    }
    public void e3()
    {
        Intent intent=new Intent(privaceandsecurity.this,privaceandsecurity.class);
        startActivity(intent);
    }
    public void e1(){
        Intent intent=new Intent(privaceandsecurity.this,MainActivity.class);
        startActivity(intent);
    }
    public void e4()
    {
        Intent intent=new Intent(privaceandsecurity.this,Ratetheapp.class);
        startActivity(intent);
    }
    public void e5()
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