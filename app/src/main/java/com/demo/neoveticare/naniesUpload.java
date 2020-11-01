package com.demo.neoveticare;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class naniesUpload extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;


    Button forchildren, forsenior;
    TextView textViewemail, textViewfirstname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main4);
        drawerLayout = findViewById(R.id.drawer_layout);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_view);
        forchildren = findViewById(R.id.childern);
        forsenior = findViewById(R.id.seniorCitizen);
        textViewemail = (TextView) findViewById(R.id.emailtextview);
        textViewfirstname = (TextView) findViewById(R.id.firstnametextview);

        String firstname = getIntent().getStringExtra("firstname");
        String email = getIntent().getStringExtra("email");

        textViewfirstname.setText(firstname);
        textViewemail.setText(email);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Toast.makeText(naniesUpload.this, "home Page:", Toast.LENGTH_SHORT).show();
                    o();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(naniesUpload.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    j();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(naniesUpload.this, "Privacy & security Page:", Toast.LENGTH_SHORT).show();
                    K();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(naniesUpload.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    k9();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(naniesUpload.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    k10();
                }
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(naniesUpload.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k11();
                }
                if (item.getItemId() == R.id.contact) {
                    Intent contact = new Intent(naniesUpload.this, ContactActivity.class);
                    startActivity(contact);

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        forchildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstnametextview = textViewfirstname.getText().toString().trim();
                String emailtextview = textViewemail.getText().toString().trim();
                Intent intent = new Intent(naniesUpload.this, ImageFirebaseUploadDemo.class);
                intent.putExtra("email", emailtextview);
                intent.putExtra("firstname", firstnametextview);
                startActivity(intent);

            }
        });

        forsenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstnametextview = textViewfirstname.getText().toString().trim();
                String emailtextview = textViewemail.getText().toString().trim();
                Intent intent = new Intent(naniesUpload.this, imageUploadsenior.class);
                intent.putExtra("email", emailtextview);
                intent.putExtra("firstname", firstnametextview);
                startActivity(intent);

            }
        });


    }

    public void j() {
        Intent intent = new Intent(naniesUpload.this, Adminactivity.class);
        startActivity(intent);
    }

    public void K() {
        Intent intent = new Intent(naniesUpload.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void o() {
        Intent intent = new Intent(naniesUpload.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Hellp:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                help();
                break;
            case R.id.back:
                Toast.makeText(this, "Back to previous Page:", Toast.LENGTH_SHORT).show();
                homee();
                break;
            case R.id.LogOut:
                Toast.makeText(this, "Log Out:", Toast.LENGTH_SHORT).show();
                a5();
                break;
        }
        {
            if (mtoggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void homee() {
        Intent intent = new Intent(naniesUpload.this, cartakerLoginActivity.class);
        startActivity(intent);
    }

    public void a5() {
        finish();
    }

    public void k9() {
        Intent intent = new Intent(naniesUpload.this, Ratetheapp.class);
        startActivity(intent);
    }

    public void k10() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody = "Your Body Here";
        String sharesub = "Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
        intent.putExtra(Intent.EXTRA_TEXT, sharebody);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    public void k11() {
        Intent intent = new Intent(naniesUpload.this, About_us.class);
        startActivity(intent);
    }

    public void help() {
        Intent intent = new Intent(naniesUpload.this, Help.class);
        startActivity(intent);
    }
}