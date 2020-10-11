package com.demo.neoveticare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CareTakerDescription extends AppCompatActivity {
    TextView ctgender,ctname,ctcity,ctprovince,currency,ctexperience,ctrate,ctdesc,ctavaialbility;
    ImageView ctimg;
    ArrayList<String> list;
    Button Hire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_taker_description);

        Intent in=getIntent();
        String name=in.getStringExtra("name");
        String price=in.getStringExtra("price");
        String city=in.getStringExtra("city");
        String gender=in.getStringExtra("gender");
        String province=in.getStringExtra("province");
        String writaboutyourself=in.getStringExtra("description");
        String rate=in.getStringExtra("price");
        String experience=in.getStringExtra("experience");
        String availability=in.getStringExtra("availablity");
        final String image=in.getStringExtra("image");
        list=in.getStringArrayListExtra("list");
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();

        StorageReference str=firebaseStorage.getReferenceFromUrl(image);

        //ctimg=(ImageView)findViewById(R.id.ctimage);
        ctname=(TextView)findViewById(R.id.ctname);
        ctgender=(TextView)findViewById(R.id.ctgender);
        ctcity=(TextView)findViewById(R.id.ctcity);
        ctprovince=(TextView)findViewById(R.id.ctprovince);
        ctdesc=(TextView)findViewById(R.id.ctDesc);
        currency=(TextView)findViewById(R.id.currency);
        ctrate=(TextView)findViewById(R.id.ctrate_single);
        ctexperience=(TextView)findViewById(R.id.experience);
        ctavaialbility=(TextView)findViewById(R.id.ctavailibility);
        ctimg=(ImageView) findViewById(R.id.ctimage);

        RecyclerView aval_days=(RecyclerView) findViewById(R.id.availabledays);
        aval_days.setLayoutManager(new LinearLayoutManager(this));

        AvailableDaysList availabledayslist =new AvailableDaysList(getApplicationContext(),list);

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
        ctrate.setText(rate);
        ctexperience.setText(experience);
        ctavaialbility.setText(availability);

    }

    private class AvailableDaysList extends RecyclerView.Adapter<AvailableDaysList.ViewHolder> {
        private Context context;
        ArrayList<String> values;

        public AvailableDaysList(Context context,ArrayList<String> values) {
            this.context = context;
            this.values = values;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            view=layoutInflater.inflate(R.layout.availabledays,parent,false);
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

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView daystext;
            Button details;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
              daystext=(TextView) itemView.findViewById(R.id.textdays);
            }
        }
    }
}