package com.coolblogger.tech.whatsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coolblogger.tech.whatsapp.ChatDetails;
import com.coolblogger.tech.whatsapp.Models.users;
import com.coolblogger.tech.whatsapp.R;
import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.viewHolder> {

ArrayList<users> list;
  Context context;

    public UsersAdapter(ArrayList<users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.show_sample_users, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        users users = list.get(position);
        Picasso.get().load(users.getProfileimg()).placeholder(R.drawable.gamer).into(holder.image);
        holder.userName.setText(users.getUserName());
        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatDetails.class);
                    intent.putExtra("userId", users.getUserId());
                    intent.putExtra("ProfilePic", users.getProfileimg());
                    intent.putExtra("userName", users.getUserName());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView userName, lastMsg;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.userNameList);
            lastMsg = itemView.findViewById(R.id.lastMsg);

        }
    }
}
