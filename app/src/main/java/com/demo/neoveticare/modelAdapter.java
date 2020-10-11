package com.demo.neoveticare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class modelAdapter extends RecyclerView.Adapter<modelAdapter.modelViewholder> {


    Context mcontext;
    ArrayList<String> itemlist;

    public modelAdapter(Context mcontext, ArrayList<String> itemlist) {
        this.mcontext = mcontext;
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public modelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlistview,parent,false);
        return new modelViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull modelViewholder holder, int position) {
holder.schedule.setText(itemlist.get(position));
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class modelViewholder extends RecyclerView.ViewHolder{
TextView schedule;
        public modelViewholder(@NonNull View itemView) {
            super(itemView);
            schedule=(TextView)itemView.findViewById(R.id.schedule);

        }
    }
}
