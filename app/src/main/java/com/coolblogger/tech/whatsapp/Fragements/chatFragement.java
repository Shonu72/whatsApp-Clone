package com.coolblogger.tech.whatsapp.Fragements;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coolblogger.tech.whatsapp.Adapters.UsersAdapter;
import com.coolblogger.tech.whatsapp.Models.users;
import com.coolblogger.tech.whatsapp.R;
import com.coolblogger.tech.whatsapp.databinding.FragmentChatBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chatFragement extends Fragment {

    FragmentChatBinding binding;
    ArrayList<users> list = new ArrayList<>();
    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentChatBinding.inflate(inflater, container, false);
       database = FirebaseDatabase.getInstance();

        UsersAdapter adapter = new UsersAdapter(list,getContext());
        binding.ChatRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.ChatRecyclerView.setLayoutManager(layoutManager);

        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    users users = dataSnapshot.getValue(users.class);
                    users.setUserId(dataSnapshot.getKey());
                    list.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       return binding.getRoot();
    }
}