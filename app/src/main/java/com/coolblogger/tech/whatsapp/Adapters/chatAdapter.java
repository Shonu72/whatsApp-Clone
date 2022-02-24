package com.coolblogger.tech.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coolblogger.tech.whatsapp.Models.msgModels;
import com.coolblogger.tech.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter {
ArrayList<msgModels> msgModelsArrayList;
Context context;
int SENDER_VIEW_TYPE = 1;
int RECEIVER_VIEW_TYPE = 2;

    public chatAdapter(ArrayList<com.coolblogger.tech.whatsapp.Models.msgModels> msgModels, Context context) {
        this.msgModelsArrayList = msgModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new senderViewholder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
            return new receiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (msgModelsArrayList.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }else{
            return RECEIVER_VIEW_TYPE;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        msgModels msgModel = msgModelsArrayList.get(position);
        if (holder.getClass()== senderViewholder.class){
            ((senderViewholder)holder).senderMsg.setText(msgModel.getMessage());
        }else{
            ((receiverViewHolder)holder).receiverMsg.setText(msgModel.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return msgModelsArrayList.size();
    }

    public class receiverViewHolder extends RecyclerView.ViewHolder {
        TextView receiverMsg, receiverTime;
         public receiverViewHolder(@NonNull View itemView) {
            super(itemView);
                receiverMsg = itemView.findViewById(R.id.receiverText);
                receiverTime = itemView.findViewById(R.id.receiverTime);

}   }

    public class senderViewholder extends  RecyclerView.ViewHolder {
        TextView senderMsg, senderTime;
        public senderViewholder(@NonNull View itemView) {
            super(itemView);

            senderMsg = itemView.findViewById(R.id.senderMsg);
            senderTime = itemView.findViewById(R.id.senderTime);

        }
    }


}

