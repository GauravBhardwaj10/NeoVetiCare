package com.demo.neoveticare;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageReplyAdapter extends RecyclerView.Adapter<MessageReplyAdapter.ViewHolder> {

    private Message message;
    private Context context;
    private List<Message> messageList;
    String email;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFrom, tvMessage, tvDate;
        Button btnReply;

        ViewHolder(View view) {
            super(view);

            tvFrom = view.findViewById(R.id.tvFrom);
            tvMessage = view.findViewById(R.id.tvMessage);
            tvDate = view.findViewById(R.id.tvDate);
            btnReply = view.findViewById(R.id.btnReply);

            btnReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    message = messageList.get(getAdapterPosition());
                    Intent i = new Intent(context, MessageActivity.class);
                    i.putExtra("email", message.getEmailfrom());
                    context.startActivity(i);

                }
            });

        }


    }

    public MessageReplyAdapter(Context mContext, List<Message> messageList, String email) {
        this.context = mContext;
        this.messageList = messageList;
        this.email = email;


    }

    @NonNull
    @Override
    public MessageReplyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_reply, parent, false);
        return new MessageReplyAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final MessageReplyAdapter.ViewHolder holder, final int position) {

        message = messageList.get(position);

        if (TextUtils.equals(message.getEmailfrom(), email)) {

            holder.tvFrom.setText("From : " + message.getEmailfrom());
            holder.btnReply.setVisibility(View.GONE);

        } else {
            holder.tvFrom.setText("To : " + message.getEmailto());
            holder.btnReply.setVisibility(View.VISIBLE);
        }
        holder.tvMessage.setText(message.getMessage());
        holder.tvDate.setText(message.getDatetime());

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


}