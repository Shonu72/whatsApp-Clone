package com.coolblogger.tech.whatsapp;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.coolblogger.tech.whatsapp.Adapters.FragmentsAdapter;
import com.coolblogger.tech.whatsapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
FirebaseAuth auth;
    private String[] titles = {"chats", "status", "calls"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().setElevation(0);

        binding.viewpager.setAdapter(new FragmentsAdapter(this));
        new TabLayoutMediator(binding.tablayout, binding.viewpager,((tab, position) -> tab.setText(titles[position]))).attach();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello world");

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value = snapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this, "" + value, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                break;

            case R.id.groupChat:
                Intent intent2 = new Intent(MainActivity.this, groupChatActivity.class);
                startActivity(intent2);
                break;
        }
        return true;
    }

}