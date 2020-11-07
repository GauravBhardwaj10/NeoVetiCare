package com.demo.neoveticare;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminContactAdapter extends RecyclerView.Adapter<AdminContactAdapter.ViewHolder> {

    private Context context;
    private List<AdminContact> adminContactList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvEmail, tvPhone, tvMessage, tvService;

        ViewHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tvName);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvPhone = view.findViewById(R.id.tvPhone);
            tvMessage = view.findViewById(R.id.tvMessage);
            tvService = view.findViewById(R.id.tvService);

        }


    }

    public AdminContactAdapter(Context mContext, List<AdminContact> adminContactList) {
        this.context = mContext;
        this.adminContactList = adminContactList;


    }

    @NonNull
    @Override
    public AdminContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_contacts, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdminContactAdapter.ViewHolder holder, final int position) {

        AdminContact adminContact = adminContactList.get(position);

        holder.tvName.setText(adminContact.getName());
        holder.tvEmail.setText(adminContact.getEmail());
        holder.tvPhone.setText(adminContact.getPhone());
        holder.tvMessage.setText(adminContact.getMessage());
        holder.tvService.setText(String.format("Service Required : %s", adminContact.getService()));


    }

    @Override
    public int getItemCount() {
        return adminContactList.size();
    }


}