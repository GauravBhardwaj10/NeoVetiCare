package com.demo.neoveticare;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailAddEd, passEd;
    private Button joinBtn;
    TextView registrationTextVIEW;
    private FirebaseAuth firebaseAuth;
    TextView tvforgotPassword;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_menu7);


        emailAddEd = (EditText) findViewById(R.id.txtemail);
        passEd = (EditText) findViewById(R.id.txtpass);
        emailAddEd.setText("test@test.com");
        passEd.setText("123321");
        joinBtn = (Button) findViewById(R.id.btnlogin);
        registrationTextVIEW = (TextView) findViewById(R.id.txtsignup);
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
                    Toast.makeText(LoginActivity.this,"home Page:",Toast.LENGTH_SHORT).show();
                    b1();
                }
                if(item.getItemId()==R.id.Admin){
                    Toast.makeText(LoginActivity.this,"Admin Login page:",Toast.LENGTH_SHORT).show();
                    b2();
                }
                if(item.getItemId()==R.id.security)
                {
                    Toast.makeText(LoginActivity.this,"Privacy & security Page:",Toast.LENGTH_SHORT).show();
                    b3();
                }
                if(item.getItemId()==R.id.rating)
                {
                    Toast.makeText(LoginActivity.this,"Rate this app:",Toast.LENGTH_SHORT).show();
                    k11();
                }
                if(item.getItemId()==R.id.share)
                {
                    Toast.makeText(LoginActivity.this,"Share the link of app by:",Toast.LENGTH_SHORT).show();
                    k12();
                }

                if(item.getItemId()==R.id.About)
                {
                    Toast.makeText(LoginActivity.this,"About Us:",Toast.LENGTH_SHORT).show();
                    k13();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(LoginActivity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();}
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        //cartaker=(TextView)findViewById(R.id.cartaker);

       /* //cartaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,cartakerLoginActivity.class);
                startActivity(intent);
            }
        });*/

        tvforgotPassword = findViewById(R.id.txtForgot);
        tvforgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });


        registrationTextVIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
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

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Please enter email name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter password name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "PASWORD IS TOO SHORT", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), Select_Category.class));
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                      //  startActivity(new Intent(getApplicationContext(), Select_Category.class));
                                    } else {
                                        Toast.makeText(LoginActivity.this, "please verified your email address", Toast.LENGTH_SHORT).show();

                                    }
                                } else {

                                    Toast.makeText(LoginActivity.this, "login Failed", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });


            }
        });
    }

    public void b2()
    {
        Intent intent=new Intent(LoginActivity.this,Adminactivity.class);
        startActivity(intent);
    }
    public void b3()
    {
        Intent intent=new Intent(LoginActivity.this,privaceandsecurity.class);
        startActivity(intent);
    }
    public void b1(){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Help:
                Toast.makeText(this,"Help",Toast.LENGTH_SHORT).show();
                b4();
                break;

        }
        {if(mtoggle.onOptionsItemSelected(item)){
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
    public void b4()
    {
        Intent intent=new Intent(LoginActivity.this,Help.class);
        startActivity(intent);
    }
    public void k11()
    {
        Intent intent=new Intent(LoginActivity.this,Ratetheapp.class);
        startActivity(intent);
    }
    public void k12()
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody="Your Body Here";
        String sharesub="Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
        intent.putExtra(Intent.EXTRA_TEXT,sharebody);
        startActivity(Intent.createChooser(intent,"Share using"));
    }
    public void k13()
    {
        Intent intent=new Intent(LoginActivity.this,About_us.class);
        startActivity(intent);
    }
    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content","Harmful or dangerous Content","Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(LoginActivity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

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

        Toast.makeText(LoginActivity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }
}
