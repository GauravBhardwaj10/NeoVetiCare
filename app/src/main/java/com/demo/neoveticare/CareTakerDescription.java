package com.demo.neoveticare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CareTakerDescription extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";
    TextView ctgender, ctname, ctcity, ctprovince, currency, ctimings, ctexperience, ctrate, ctdesc, ctavaialbility, ctage;
    ImageView ctimg;
    ArrayList<String> list;
    Button btnMessage, btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_listofnanny);
        btnMessage = findViewById(R.id.btnMessage);
        btnChat = findViewById(R.id.btnChat);

        Intent in = getIntent();
        final String email = in.getStringExtra("email");
        String name = in.getStringExtra("name");
        String price = in.getStringExtra("price");
        String city = in.getStringExtra("city");
        String gender = in.getStringExtra("gender");
        String timings = in.getStringExtra("timings");
        String age = in.getStringExtra("age");
        String province = in.getStringExtra("province");
        String writaboutyourself = in.getStringExtra("description");
        String rate = in.getStringExtra("price");
        String experience = in.getStringExtra("experience");
        String availability = in.getStringExtra("availablity");
        final String image = in.getStringExtra("image");
        list = in.getStringArrayListExtra("list");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        StorageReference str = firebaseStorage.getReferenceFromUrl(image);

        //ctimg=(ImageView)findViewById(R.id.ctimage);
        ctname = (TextView) findViewById(R.id.ctname);
        ctgender = (TextView) findViewById(R.id.ctgender);
        ctcity = (TextView) findViewById(R.id.ctcity);
        ctprovince = (TextView) findViewById(R.id.ctprovince);
        ctdesc = (TextView) findViewById(R.id.ctDesc);
        ctimings = (TextView) findViewById(R.id.ctimings);
        currency = (TextView) findViewById(R.id.currency);
        ctrate = (TextView) findViewById(R.id.ctrate_single);
        ctage = (TextView) findViewById(R.id.ctage);
        ctexperience = (TextView) findViewById(R.id.experience);
        ctavaialbility = (TextView) findViewById(R.id.ctavailibility);
        ctimg = (ImageView) findViewById(R.id.ctimage);
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
                    Toast.makeText(CareTakerDescription.this, "home Page:", Toast.LENGTH_SHORT).show();
                    Ho();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(CareTakerDescription.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    jj();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(CareTakerDescription.this, "Terms & Conditions:", Toast.LENGTH_SHORT).show();
                    KK();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(CareTakerDescription.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    k3();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(CareTakerDescription.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    k4();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(CareTakerDescription.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();
                }
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(CareTakerDescription.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k5();
                }
                if (item.getItemId() == R.id.contact) {
                    Intent contact = new Intent(CareTakerDescription.this, ContactActivity.class);
                    startActivity(contact);

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        RecyclerView aval_days = (RecyclerView) findViewById(R.id.availabledays);
        aval_days.setLayoutManager(new LinearLayoutManager(this));

        AvailableDaysList availabledayslist = new AvailableDaysList(getApplicationContext(), list);

        aval_days.setAdapter(availabledayslist);
        str.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext())
                        .load(image)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(ctimg);
            }
        });
        ctname.setText(name);
        ctgender.setText(gender);
        ctcity.setText(city);
        ctprovince.setText(province);
        ctdesc.setText(writaboutyourself);
        ctimings.setText(timings);
        ctrate.setText(rate);
        ctage.setText(age);
        ctexperience.setText(experience);
        ctavaialbility.setText(availability);
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CareTakerDescription.this, MessageActivity.class);
                i.putExtra("email", email);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(CareTakerDescription.this, ChatActivity.class);
                i.putExtra("email", email);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });

    }

    private class AvailableDaysList extends RecyclerView.Adapter<AvailableDaysList.ViewHolder> {
        private Context context;
        ArrayList<String> values;

        public AvailableDaysList(Context context, ArrayList<String> values) {
            this.context = context;
            this.values = values;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.availabledays, parent, false);
            return new AvailableDaysList.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final AvailableDaysList.ViewHolder holder, final int position) {
            holder.daystext.setText(values.get(position));
        }

        @Override
        public int getItemCount() {
            return values.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView daystext;
            Button details;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                daystext = (TextView) itemView.findViewById(R.id.textdays);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Help:
                Toast.makeText(this, "Back to Previous Page:", Toast.LENGTH_SHORT).show();
                h1();
                break;

        }
        {
            if (mtoggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void jj() {
        Intent intent = new Intent(CareTakerDescription.this, Adminactivity.class);
        startActivity(intent);
    }

    public void KK() {
        Intent intent = new Intent(CareTakerDescription.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void Ho() {
        Intent intent = new Intent(CareTakerDescription.this, MainActivity.class);
        startActivity(intent);
    }

    public void k3() {
        Intent intent = new Intent(CareTakerDescription.this, Ratetheapp.class);
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

    public void h1() {
        Intent intent = new Intent(CareTakerDescription.this, MainActivity.class);
        startActivity(intent);
    }

    public void k5() {
        Intent intent = new Intent(CareTakerDescription.this, About_us.class);
        startActivity(intent);
    }

    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content", "Harmful or dangerous Content", "Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CareTakerDescription.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(CareTakerDescription.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

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

    public void go() {

        Toast.makeText(CareTakerDescription.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }
}