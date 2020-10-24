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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Adminactivity extends AppCompatActivity {
    public EditText ed1,ed2;
    public Button btn;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_menu8);
        ed1=findViewById(R.id.email1);
        ed2=findViewById(R.id.pass1);
        btn=findViewById(R.id.login1);
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
                    Toast.makeText(Adminactivity.this,"home Page:",Toast.LENGTH_SHORT).show();
                    c6();
                }
                if(item.getItemId()==R.id.Admin){
                    Toast.makeText(Adminactivity.this,"Admin Login page:",Toast.LENGTH_SHORT).show();
                    c7();
                }
                if(item.getItemId()==R.id.security)
                {
                    Toast.makeText(Adminactivity.this,"Privacy & security Page:",Toast.LENGTH_SHORT).show();
                    c8();
                }
                if(item.getItemId()==R.id.rating)
                {
                    Toast.makeText(Adminactivity.this,"Rate this app:",Toast.LENGTH_SHORT).show();
                 c9();
                }
                if(item.getItemId()==R.id.share)
                {
                    Toast.makeText(Adminactivity.this,"Share the link of app by:",Toast.LENGTH_SHORT).show();
                    c10();
                }
                if(item.getItemId()==R.id.About)
                {
                    Toast.makeText(Adminactivity.this,"Share the link of app by:",Toast.LENGTH_SHORT).show();
                    c11();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(Adminactivity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();}
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if((ed1.getText().toString().equals("A"))&&(ed2.getText().toString().equals("1"))){
                  AlertDialog.Builder builder=new AlertDialog.Builder(Adminactivity.this);
                  builder.setIcon(R.drawable.ic_baseline_security_24);
                  builder.setTitle(" Admin Login sucessfully");
                  builder.setMessage("Now, Do you want to open a page to see the List of nannies  and CareTakers as well as Manage Users:");
                  builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.cancel();
                          done();
                      }
                  });
              AlertDialog alertDialog=builder.create();
              alertDialog.show();
              }
              else
              {
                  Toast.makeText(getApplicationContext(),"NOT VALID",Toast.LENGTH_SHORT).show();
              }
          }
      });

    }
    public void done()
    {
        Intent intent= new Intent(Adminactivity.this,Dialogboxofreport.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Help:
                Toast.makeText(this,"Help",Toast.LENGTH_SHORT).show();
                hi();
                break;
        }{if(mtoggle.onOptionsItemSelected(item)){
            return true;
        }}
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return true;
    }
    public void c7()
    {
        Intent intent=new Intent(Adminactivity.this,Adminactivity.class);
        startActivity(intent);
    }
    public void c8()
    {
        Intent intent=new Intent(Adminactivity.this,privaceandsecurity.class);
        startActivity(intent);
    }
    public void c6(){
        Intent intent=new Intent(Adminactivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void c9()
    {
        Intent intent=new Intent(Adminactivity.this,Ratetheapp.class);
        startActivity(intent);
    }
    public void c11()
    {
        Intent intent=new Intent(Adminactivity.this,About_us.class);
        startActivity(intent);
    }
    public void c10()
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody="Your Body Here";
        String sharesub="Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
        intent.putExtra(Intent.EXTRA_TEXT,sharebody);
        startActivity(Intent.createChooser(intent,"Share using"));
    }
    public void hi()
    {
        Intent intent=new Intent(Adminactivity.this,Help.class);
        startActivity(intent);
    }
    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content","Harmful or dangerous Content","Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Adminactivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(Adminactivity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

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

        Toast.makeText(Adminactivity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }
}