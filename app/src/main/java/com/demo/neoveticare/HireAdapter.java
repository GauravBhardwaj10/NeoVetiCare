package com.demo.neoveticare;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HireAdapter extends RecyclerView.Adapter<HireAdapter.ViewHolder> {

    private Context context;
    private List<Hire> hireList;
    Hire hire;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvEmail, tvTotalHours, tvTotalPrice, tvDate;
        ConstraintLayout constraintLayout;

        ViewHolder(View view) {
            super(view);

            tvEmail = view.findViewById(R.id.tvEmail);
            tvTotalHours = view.findViewById(R.id.tvTotalHours);
            tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
            tvDate = view.findViewById(R.id.tvDate);
            constraintLayout = view.findViewById(R.id.constraintlayout);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hire = hireList.get(getAdapterPosition());
                    Intent i = new Intent(context, HireDetailsActivity.class);
                    i.putExtra("documentId", hire.getId());
                    context.startActivity(i);


                }
            });

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

        hire = hireList.get(position);

        holder.tvDate.setText(hire.getDatetime());
        holder.tvEmail.setText(hire.getParentEmail());
        holder.tvTotalPrice.setText("Total Weekly Pay : " + hire.getTotal());
        holder.tvTotalHours.setText("From : "+ hire.getFromDate() + " To : " + hire.getToDate());

    }

    @Override
    public int getItemCount() {
        return hireList.size();
    }


}