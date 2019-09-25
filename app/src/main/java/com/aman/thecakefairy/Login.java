package com.aman.thecakefairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login Form");
        final EditText txtEmail=(EditText)findViewById(R.id.email);
        final EditText txtPassword=(EditText)findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        Button login=(Button)findViewById(R.id.btn_login);
        Button register=(Button)findViewById(R.id.btn_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String password = txtPassword.getText().toString().trim();
                 String email = txtEmail.getText().toString().trim();
                 if (TextUtils.isEmpty(email)) {
                     Toast.makeText(Login.this, "please Enter Email", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (TextUtils.isEmpty(password)) {
                     Toast.makeText(Login.this, "please Enter password", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (password.length() < 6) {
                     Toast.makeText(Login.this, "password too short", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 mAuth.signInWithEmailAndPassword(email, password)
                         .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if (task.isSuccessful()) {
                                     startActivity(new Intent(getApplicationContext(), Home.class));
                                     Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                 } else {
                                     Toast.makeText(Login.this, "Login Failed Or user not available", Toast.LENGTH_SHORT).show();

                                 }
                             }
                         });
            }
        });


    }



    public void go_to_register(View view) {
        startActivity(new Intent(getApplicationContext(), Signup.class));
    }


}
