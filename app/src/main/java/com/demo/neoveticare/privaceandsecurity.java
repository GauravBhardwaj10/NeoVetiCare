package com.demo.neoveticare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.navigation.NavigationView;

public class privaceandsecurity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";

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
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(privaceandsecurity.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k5();
                }
                if(item.getItemId()==R.id.share)
                {
                    Toast.makeText(privaceandsecurity.this,"Share the link of app by:",Toast.LENGTH_SHORT).show();
                    e5();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(privaceandsecurity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();}
                if (item.getItemId() == R.id.contact) {
                    Intent contact = new Intent(privaceandsecurity.this, ContactActivity.class);
                    startActivity(contact);

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
                l();
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
    public void k5()
    {
        Intent intent=new Intent(privaceandsecurity.this,About_us.class);
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
    public void l()
    {
        Intent intent=new Intent(privaceandsecurity.this,Help.class);
        startActivity(intent);
    }
    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content","Harmful or dangerous Content","Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(privaceandsecurity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(privaceandsecurity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

            }
        });
        builder.setPositiveButton("Proceesd", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                go();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    public void go()
    {

        Toast.makeText(privaceandsecurity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }
}