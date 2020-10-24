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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Message message;
    private Context context;
    private List<Message> messageList;
    String email;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFrom, tvMessage, tvDate;

        ViewHolder(View view) {
            super(view);

            tvFrom = view.findViewById(R.id.tvFrom);
            tvMessage = view.findViewById(R.id.tvMessage);
            tvDate = view.findViewById(R.id.tvDate);

        }


    }

    public MessageAdapter(Context mContext, List<Message> messageList, String email) {
        this.context = mContext;
        this.messageList = messageList;
        this.email = email;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        message = messageList.get(position);

        if (TextUtils.equals(message.getEmailfrom(), email)) {

            holder.tvFrom.setText("From : " + message.getEmailfrom());

        }else{
            holder.tvFrom.setText("To : " + message.getEmailto());
        }
        holder.tvMessage.setText(message.getMessage());
        holder.tvDate.setText(message.getDatetime());


    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


}