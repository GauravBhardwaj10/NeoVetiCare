package com.demo.neoveticare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class Select_Category extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content:";


    Button seniors, kids, messages;
    private RadioGroup radioSTimerGroup, radioCTimerGroup;
    private RadioButton radioSTimerButton, radioCTimerButton;
    String category = "";
    ImageView backbtn;
    String Value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_category);
        seniors = (Button) findViewById(R.id.senior);
        drawerLayout = findViewById(R.id.drawer_layout);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Toast.makeText(Select_Category.this, "home Page:", Toast.LENGTH_SHORT).show();
                    Ho();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(Select_Category.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    jj();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(Select_Category.this, "Privacy & security Page:", Toast.LENGTH_SHORT).show();
                    KK();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(Select_Category.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    k3();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(Select_Category.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    k4();
                }
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(Select_Category.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k5();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(Select_Category.this, "Report this App:", Toast.LENGTH_SHORT).show();
                    showonDialog();
                }
                if (item.getItemId() == R.id.contact) {
                    Intent contact = new Intent(Select_Category.this, ContactActivity.class);
                    startActivity(contact);

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        messages = findViewById(R.id.message);
        kids = (Button) findViewById(R.id.kids);
        radioSTimerGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioCTimerGroup = (RadioGroup) findViewById(R.id.radioGroupchild);
        backbtn = (ImageView) findViewById(R.id.backimg);
        seniors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioSTimerGroup.setVisibility(View.VISIBLE);
                kids.setVisibility(View.GONE);
                seniors.setVisibility(View.GONE);
                messages.setVisibility(View.GONE);
                category = "senior";
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
                    Intent in = new Intent(Select_Category.this, FilterActivity.class);
                    in.putExtra("category", Value);
                    startActivity(in);

                } else {
                    Value = "seniorparttime";
                    Intent in = new Intent(Select_Category.this, FilterActivity.class);
                    in.putExtra("category", Value);
                    startActivity(in);

                }
            }
        });

        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioCTimerGroup.setVisibility(View.VISIBLE);
                kids.setVisibility(View.GONE);
                seniors.setVisibility(View.GONE);
                messages.setVisibility(View.GONE);
                category = "kid";
                int selectedId = radioCTimerGroup.getCheckedRadioButtonId();
                radioCTimerButton = (RadioButton) findViewById(selectedId);

            }
        });

        radioCTimerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioCTimerButton = (RadioButton) findViewById(checkedId);

                if (radioCTimerButton.getText().toString().equals("Full Time")) {
                    Value = "uploadchildernfulltime";

                    Intent in = new Intent(Select_Category.this, FilterActivity.class);
                    in.putExtra("category", Value);
                    startActivity(in);

                } else {
                    Value = "childernparttime";
                    Intent in = new Intent(Select_Category.this, FilterActivity.class);
                    in.putExtra("category", Value);
                    startActivity(in);

                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (category.equals("senior")) {
                    radioSTimerGroup.setVisibility(View.GONE);
                    radioCTimerGroup.setVisibility(View.GONE);

                    kids.setVisibility(View.VISIBLE);
                    seniors.setVisibility(View.VISIBLE);
                } else if (category.equals("kid")) {
                    radioCTimerGroup.setVisibility(View.GONE);
                    radioSTimerGroup.setVisibility(View.GONE);

                    kids.setVisibility(View.VISIBLE);
                    seniors.setVisibility(View.VISIBLE);
                } else {
                    System.exit(0);
                }
            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_Category.this, MessagesActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                h();
                break;
        }
        {
            if (mtoggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;

    }

    public void jj() {
        Intent intent = new Intent(Select_Category.this, Adminactivity.class);
        startActivity(intent);
    }

    public void KK() {
        Intent intent = new Intent(Select_Category.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void Ho() {
        Intent intent = new Intent(Select_Category.this, MainActivity.class);
        startActivity(intent);
    }

    public void k3() {
        Intent intent = new Intent(Select_Category.this, Ratetheapp.class);
        startActivity(intent);
    }

    public void k4() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody = "Your Body Here";
        String sharesub = "Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
        intent.putExtra(Intent.EXTRA_TEXT, sharebody);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    public void h() {
        Intent intent = new Intent(Select_Category.this, Help.class);
        startActivity(intent);
    }

    public void k5() {
        Intent intent = new Intent(Select_Category.this, About_us.class);
        startActivity(intent);
    }

    private void showonDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content", "Harmful or dangerous Content:", "Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Select_Category.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(Select_Category.this, "you Choose:" + selectedGender, Toast.LENGTH_LONG).show();

            }
        });
        builder.setPositiveButton("Report", new DialogInterface.OnClickListener() {
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

    public void go() {
        Toast.makeText(Select_Category.this, "Reporting Done", Toast.LENGTH_LONG).show();
    }


}