package com.coolblogger.tech.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.coolblogger.tech.whatsapp.databinding.ActivityGroupChatBinding;

public class groupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}