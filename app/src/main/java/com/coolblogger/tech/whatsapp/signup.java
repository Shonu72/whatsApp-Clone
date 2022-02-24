package com.coolblogger.tech.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.coolblogger.tech.whatsapp.Models.users;
import com.coolblogger.tech.whatsapp.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog =  new ProgressDialog(signup.this);
        progressDialog.setTitle("Creating your account");
        progressDialog.setMessage("We are creating your account ");

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword
                        (binding.etEmail.getText().toString(), binding.password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            users user = new users(binding.etusername.getText().toString(), binding.etEmail.getText().toString(),
                                    binding.password.getText().toString());

                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("users").child(id).setValue(user);
                            // for redirecting to main page
                            Intent intent = new Intent(signup.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(signup.this, "Profile created successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        binding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,login.class);
                startActivity(intent);
            }
        });

    }
}