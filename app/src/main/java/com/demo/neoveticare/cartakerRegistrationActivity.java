package com.demo.neoveticare;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class cartakerRegistrationActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";


    private EditText fNameEd,lNameEd,emailAddEd,passEd,confirmpassword;

    private Button joinBtn;

    private FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_careregister);
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
                    Toast.makeText(cartakerRegistrationActivity.this, "home Page:", Toast.LENGTH_SHORT).show();
                    Ho();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(cartakerRegistrationActivity.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    jj();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(cartakerRegistrationActivity.this, "Privacy & security Page:", Toast.LENGTH_SHORT).show();
                    KK();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(cartakerRegistrationActivity.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    k3();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(cartakerRegistrationActivity.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    k4();
                }
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(cartakerRegistrationActivity.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k5();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(cartakerRegistrationActivity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();}
                if (item.getItemId() == R.id.contact) {
                    Intent contact = new Intent(cartakerRegistrationActivity.this, ContactActivity.class);
                    startActivity(contact);

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        fNameEd = (EditText) findViewById(R.id.fname);
        lNameEd = (EditText) findViewById(R.id.lname);
        emailAddEd = (EditText) findViewById(R.id.eml);
        passEd = (EditText) findViewById(R.id.password);
        joinBtn = (Button) findViewById(R.id.register);

        confirmpassword = (EditText) findViewById(R.id.cnfpassword);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = fNameEd.getText().toString().trim();
                String lastname = lNameEd.getText().toString().trim();

                String email = emailAddEd.getText().toString().trim();

                String password = passEd.getText().toString().trim();

                String confrmPasword= confirmpassword.getText().toString().trim();



                if(TextUtils.isEmpty(firstname))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(lastname))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter last name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter email name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter password name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confrmPasword))
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"Please enter confirm password name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6)
                {
                    Toast.makeText(cartakerRegistrationActivity.this,"PASWORD IS TOO SHORT", Toast.LENGTH_SHORT).show();
                }

                if (password.equals(confrmPasword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(cartakerRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    String firstname = fNameEd.getText().toString().trim();
                                                    String email = emailAddEd.getText().toString().trim();


                                                    Intent intent = new Intent(cartakerRegistrationActivity.this, cartakerLoginActivity.class);
                                                    intent.putExtra("email", email);
                                                    intent.putExtra("firstname", firstname);
                                                    startActivity(intent);
                                                    Toast.makeText(cartakerRegistrationActivity.this, "Registation Complete.please check your email for verification", Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    Toast.makeText(cartakerRegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });


                                    } else {

                                        Toast.makeText(cartakerRegistrationActivity.this,"Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
                else {
                    Toast.makeText(cartakerRegistrationActivity.this,"pasword dose not match", Toast.LENGTH_SHORT).show();
                }



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
        Intent intent = new Intent(cartakerRegistrationActivity.this, Adminactivity.class);
        startActivity(intent);
    }

    public void KK() {
        Intent intent = new Intent(cartakerRegistrationActivity.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void Ho() {
        Intent intent = new Intent(cartakerRegistrationActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void k3() {
        Intent intent = new Intent(cartakerRegistrationActivity.this, Ratetheapp.class);
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
        Intent intent = new Intent(cartakerRegistrationActivity.this, Help.class);
        startActivity(intent);
    }

    public void k5() {
        Intent intent = new Intent(cartakerRegistrationActivity.this, About_us.class);
        startActivity(intent);
    }
    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content","Harmful or dangerous Content","Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(cartakerRegistrationActivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(cartakerRegistrationActivity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

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

        Toast.makeText(cartakerRegistrationActivity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }

}
