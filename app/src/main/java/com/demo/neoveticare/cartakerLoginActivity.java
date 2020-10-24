package com.demo.neoveticare;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class cartakerLoginActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private EditText emailAddEd, passEd;
    private Button joinBtn;
    TextView registrationTextVIEW;
    private FirebaseAuth firebaseAuth;
    TextView tvforgotPassword;

    TextView textViewemail, textViewfirstname;
    private ActionBarDrawerToggle mtoggle;
    FirebaseFirestore firebaseFirestore;
    boolean isNext = false;
    String selectedGender = "Sexual Content";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main3);
        firebaseFirestore = FirebaseFirestore.getInstance();
        emailAddEd = (EditText) findViewById(R.id.email);
        passEd = (EditText) findViewById(R.id.pass);
        joinBtn = (Button) findViewById(R.id.login);
        registrationTextVIEW = (TextView) findViewById(R.id.textsignup);
        textViewemail = (TextView) findViewById(R.id.emailtextview);
        textViewfirstname = (TextView) findViewById(R.id.firstnametextview);
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
                    Toast.makeText(cartakerLoginActivity.this, "home Page:", Toast.LENGTH_SHORT).show();
                    Hho();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(cartakerLoginActivity.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    jjj();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(cartakerLoginActivity.this, "Privacy & security Page:", Toast.LENGTH_SHORT).show();
                    kKK();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(cartakerLoginActivity.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    k1();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(cartakerLoginActivity.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    k2();
                }
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(cartakerLoginActivity.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k3();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(cartakerLoginActivity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();}
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        String firstname = getIntent().getStringExtra("firstname");
        String email = getIntent().getStringExtra("email");

        textViewfirstname.setText(firstname);
        textViewemail.setText(email);


        //cartaker=(TextView)findViewById(R.id.cartaker);
/*
        cartaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cartakerLoginActivity.this,naniesUpload.class);
                startActivity(intent);
            }
        });


 */
        tvforgotPassword = findViewById(R.id.txtForgot);
        tvforgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartakerLoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });


        registrationTextVIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cartakerLoginActivity.this, cartakerRegistrationActivity.class);
                startActivity(intent);
            }
        });


//        getSupportActionBar().hide();

        registrationTextVIEW.setPaintFlags(registrationTextVIEW.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = emailAddEd.getText().toString().trim();

                String password = passEd.getText().toString().trim();

                String firstnametextview = textViewfirstname.getText().toString().trim();
                String emailtextview = textViewemail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(cartakerLoginActivity.this, "Please enter email name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(cartakerLoginActivity.this, "Please enter password name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(cartakerLoginActivity.this, "PASWORD IS TOO SHORT", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(cartakerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                                    getProfile();
                                    // } else {
                                    //     Toast.makeText(cartakerLoginActivity.this, "please verified your email address", Toast.LENGTH_SHORT).show();

                                    //   }
                                } else {

                                    Toast.makeText(cartakerLoginActivity.this, "login Failed", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

             /*   Intent intent = new Intent(cartakerLoginActivity.this, naniesUpload.class);
                intent.putExtra("email", emailtextview);
                intent.putExtra("firstname", firstnametextview);
                startActivity(intent);*/

            }
        });
    }

    private void getProfile() {

        firebaseFirestore.collection("uploadchildernfulltime").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            //  Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {

                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {

                                Upload upload = uploads.get(i);
                                if (TextUtils.equals(upload.getEmailaddress(), firebaseAuth.getCurrentUser().getEmail())) {
                                    Intent tab = new Intent(getApplicationContext(), TabActivity.class);
                                    tab.putExtra("table", "uploadchildernfulltime");
                                    startActivity(tab);
                                    finish();
                               return;
                                }


                            }
                            getSeniorfulltime();

                        }
                    }


                });


    }

    public void getSeniorfulltime() {
        firebaseFirestore.collection("seniorfulltime").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            //  Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {

                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {

                                Upload upload = uploads.get(i);
                                if (TextUtils.equals(upload.getEmailaddress(), firebaseAuth.getCurrentUser().getEmail())) {
                                    Intent tab = new Intent(getApplicationContext(), TabActivity.class);
                                    tab.putExtra("table", "seniorfulltime");
                                    startActivity(tab);
                                    finish();
                                    return;
                                }


                            }
                            getSeniorParttime();

                        }
                    }


                });
    }

    public void getSeniorParttime() {
        firebaseFirestore.collection("seniorparttime").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            //  Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {

                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {

                                Upload upload = uploads.get(i);
                                if (TextUtils.equals(upload.getEmailaddress(), firebaseAuth.getCurrentUser().getEmail())) {
                                    Intent tab = new Intent(getApplicationContext(), TabActivity.class);
                                    tab.putExtra("table", "seniorparttime");
                                    startActivity(tab);
                                    finish();
                                    return;
                                }


                            }
                            getChildParttime();
                        }
                    }


                });
    }

    public void getChildParttime() {
        firebaseFirestore.collection("childernparttime").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            //  Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {

                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {

                                Upload upload = uploads.get(i);
                                Log.e("here", firebaseAuth.getCurrentUser().getEmail());
                                Log.e("here1", upload.getEmailaddress());
                                if (TextUtils.equals(upload.getEmailaddress(), firebaseAuth.getCurrentUser().getEmail())) {
                                    Intent tab = new Intent(getApplicationContext(), TabActivity.class);
                                    tab.putExtra("table", "childernparttime");
                                    startActivity(tab);
                                    finish();
                                    return;
                                } else {

                                }


                            }
                            Intent intent = new Intent(cartakerLoginActivity.this, naniesUpload.class);
                            startActivity(intent);

                        }
                    }


                });
    }


    public void jjj() {
        Intent intent = new Intent(cartakerLoginActivity.this, Adminactivity.class);
        startActivity(intent);
    }

    public void kKK() {
        Intent intent = new Intent(cartakerLoginActivity.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void Hho() {
        Intent intent = new Intent(cartakerLoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Help1:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                hj();
                break;
            case R.id.back1:
                Toast.makeText(this, "Back to previous Page:", Toast.LENGTH_SHORT).show();
                home();
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
        inflater.inflate(R.menu.menu3, menu);
        return true;
    }

    public void home() {
        Intent intent = new Intent(cartakerLoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void k1() {
        Intent intent = new Intent(cartakerLoginActivity.this, Ratetheapp.class);
        startActivity(intent);
    }

    public void k2() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody = "Your Body Here";
        String sharesub = "Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
        intent.putExtra(Intent.EXTRA_TEXT, sharebody);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    public void k3() {
        Intent intent = new Intent(cartakerLoginActivity.this, About_us.class);
        startActivity(intent);
    }

    public void hj() {
        Intent intent = new Intent(cartakerLoginActivity.this, Help.class);
        startActivity(intent);
    }
    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content","Harmful or dangerous Content","Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(cartakerLoginActivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(cartakerLoginActivity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

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

        Toast.makeText(cartakerLoginActivity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }

}
