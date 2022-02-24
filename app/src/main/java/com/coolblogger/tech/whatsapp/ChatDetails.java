package com.coolblogger.tech.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.coolblogger.tech.whatsapp.Adapters.chatAdapter;
import com.coolblogger.tech.whatsapp.Models.msgModels;
import com.coolblogger.tech.whatsapp.databinding.ActivityChatDetailsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetails extends AppCompatActivity {

    ActivityChatDetailsBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();


        final String senderId = auth.getUid();
        String receiveId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String ProfilePic = getIntent().getStringExtra("ProfilePic");

        binding.userName.setText(userName);
        Picasso.get().load(ProfilePic).placeholder(R.drawable.gamer).into(binding.profileImage);

        binding.leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });
    final ArrayList<msgModels> msgsModel = new ArrayList<>();
    final chatAdapter chatAdapter = new chatAdapter(msgsModel, this);
    binding.ChatRecyclerView.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.ChatRecyclerView.setLayoutManager(layoutManager);

        final String senderRoom =  senderId + receiveId;
        final String receiverRoom = receiveId + senderId;

        database.getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                msgsModel.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    msgModels models = snapshot1.getValue(msgModels.class);
                    msgsModel.add(models);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        binding.sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String msg =   binding.msgBox.getText().toString();
              final msgModels models = new msgModels(senderId,msg);
              models.setTimeStamp(new Date().getTime());
              binding.msgBox.setText("");
              database.getReference().child("chats").child(senderRoom).push().setValue(models)
                      .addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
                      database.getReference().child("chats").child(receiverRoom).push().setValue(models)
                              .addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void unused) {

                          }
                      });
                  }
              });
            }
        });
    }
}