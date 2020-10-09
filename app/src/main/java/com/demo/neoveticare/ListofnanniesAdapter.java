package com.demo.neoveticare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class ListofnanniesAdapter extends RecyclerView.Adapter<ListofnanniesAdapter.ViewHolder> {
    private Context context;
    ArrayList<Upload> values;

    public ListofnanniesAdapter(Context context,ArrayList<Upload> values) {
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.lisofnannies,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();

        holder.ctdesc.setText(values.get(position).getWritaboutyourself());
        holder.ctrate.setText(values.get(position).getAddress());
        holder.ctname.setText(values.get(position).getName());
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(context,CareTakerDescription.class);
                in.putExtra("image",values.get(position).getUrl());
                in.putExtra("price",values.get(position).getAddress());
                in.putExtra("name",values.get(position).getName());
                in.putExtra("gender",values.get(position).getGender());
                in.putExtra("city",values.get(position).getCity());
                in.putExtra("province",values.get(position).getProvience());
                in.putExtra("description",values.get(position).getWritaboutyourself());
                in.putExtra("rate",values.get(position).getAddress());
                in.putExtra("experience",values.get(position).getExperience());
                //in.putExtra("availability",values.get(position).getSchedulelist());
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });
//        StorageReference str=firebaseStorage.getReferenceFromUrl(values.get(position).getUrl());
//        str.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Glide.with(context)
//                        .load(uri.toString())
//                        .into(values.myImg);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ctimg;
        TextView ctname,ctdesc,ctrate;
        Button details;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ctimg=(ImageView) itemView.findViewById(R.id.ctimage);
            ctname=(TextView)itemView.findViewById(R.id.ctname);
            ctdesc=(TextView)itemView.findViewById(R.id.ctDesc);
            ctrate=(TextView)itemView.findViewById(R.id.ctrate);
            details=(Button) itemView.findViewById(R.id.bookct);
        }
    }
}