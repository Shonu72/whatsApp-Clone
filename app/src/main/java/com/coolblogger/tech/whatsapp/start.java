package com.coolblogger.tech.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start extends AppCompatActivity {
Button startbtn, login, signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startbtn = findViewById(R.id.startbtn);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start.this, signup.class);
                startActivity(intent);
            }
        });

    }
}