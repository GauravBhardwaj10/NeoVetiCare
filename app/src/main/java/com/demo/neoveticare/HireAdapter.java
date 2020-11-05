package com.demo.neoveticare;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HireAdapter extends RecyclerView.Adapter<HireAdapter.ViewHolder> {

    private Context context;
    private List<Hire> hireList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvEmail, tvTotalHours, tvTotalPrice, tvDate;

        ViewHolder(View view) {
            super(view);


            tvEmail = view.findViewById(R.id.tvEmail);
            tvTotalHours = view.findViewById(R.id.tvTotalHours);
            tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
            tvDate = view.findViewById(R.id.tvDate);

        }


    }

    public HireAdapter(Context mContext, List<Hire> hireList) {
        this.context = mContext;
        this.hireList = hireList;


    }

    @NonNull
    @Override
    public HireAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hire, parent, false);
        return new HireAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final HireAdapter.ViewHolder holder, final int position) {

        Hire hire = hireList.get(position);

        holder.tvDate.setText(hire.getDatetime());
        holder.tvEmail.setText(hire.getParentEmail());
        holder.tvTotalHours.setText(hire.getTotal());
        holder.tvTotalPrice.setText(hire.getRate());

    }

    @Override
    public int getItemCount() {
        return hireList.size();
    }


}