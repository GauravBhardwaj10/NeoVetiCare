package com.demo.neoveticare;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class cartakerLoginActivity extends AppCompatActivity {

    private EditText emailAddEd,passEd;
    private Button joinBtn;
    TextView registrationTextVIEW;
    private FirebaseAuth firebaseAuth;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home)
                {
                    Toast.makeText(cartakerLoginActivity.this,"Home Page:",Toast.LENGTH_SHORT).show();
                    okk();
                }
                if(item.getItemId()==R.id.Admin)
                {
                    Toast.makeText(cartakerLoginActivity.this,"Admin Page:",Toast.LENGTH_SHORT).show();
                    ll();
                }
                if(item.getItemId()==R.id.security)
                {
                    Toast.makeText(cartakerLoginActivity.this,"Privacy & security Page:",Toast.LENGTH_SHORT).show();
                    oo();
                }
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        emailAddEd=(EditText) findViewById(R.id.email);
        passEd=(EditText) findViewById(R.id.pass);
        joinBtn=(Button) findViewById(R.id.login);
        registrationTextVIEW=(TextView)findViewById(R.id.textsignup);
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



        registrationTextVIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cartakerLoginActivity.this,cartakerRegistrationActivity.class);
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

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(cartakerLoginActivity.this,"Please enter email name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(cartakerLoginActivity.this,"Please enter password name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6)
                {
                    Toast.makeText(cartakerLoginActivity.this,"PASWORD IS TOO SHORT", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth= FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(cartakerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                        startActivity(new Intent(getApplicationContext(), naniesUpload.class));
                                    }
                                    else {
                                        Toast.makeText(cartakerLoginActivity.this,"please verified your email address", Toast.LENGTH_SHORT).show();

                                    }
                                } else {

                                    Toast.makeText(cartakerLoginActivity.this,"login Failed", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu3,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Help1:
                Toast.makeText(this,"Help1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.back1:
                Toast.makeText(this,"Back to Previous Page",Toast.LENGTH_SHORT).show();
                go();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void go()
    {
        Intent intent=new Intent(cartakerLoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void okk()
    {
        Intent intent=new Intent(cartakerLoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void oo()
    {
        Intent intent=new Intent(cartakerLoginActivity.this,privaceandsecurity.class);
        startActivity(intent);
    }
    public void ll()
    {
        Intent intent=new Intent(cartakerLoginActivity.this,Adminactivity.class);
        startActivity(intent);
    }
}
