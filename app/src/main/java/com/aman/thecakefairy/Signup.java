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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Signup Form");
        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("User");


        final EditText txtName=(EditText) findViewById(R.id.name);
        final EditText txtMob=(EditText) findViewById(R.id.mobile);
        final EditText txtAddess =findViewById(R.id.address);
        final EditText txtEmail=(EditText)findViewById(R.id.email);
        final EditText txtPassword=(EditText)findViewById(R.id.password);
        final EditText ConfirmPassword=(EditText) findViewById(R.id.cnfpass);
        Button login=(Button)findViewById(R.id.btn_login);
        Button register=(Button)findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String name = txtName.getText().toString().trim();
                final String mobile = txtMob.getText().toString().trim();
                final String address = txtAddess.getText().toString().trim();
                final String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String cnfpass= ConfirmPassword.getText().toString().trim();
                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(Signup.this,"please Enter name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mobile))
                {
                    Toast.makeText(Signup.this,"please Enter Mobile number",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(address))
                {
                    Toast.makeText(Signup.this,"please Enter Address",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Signup.this,"please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Signup.this,"please Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(cnfpass))
                {
                    Toast.makeText(Signup.this,"please Enter Confirm password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<5)
                {
                    Toast.makeText(Signup.this,"password too short",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.equals(cnfpass))
                {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        User information=new User(name, mobile, email,address);
                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(Signup.this,"Registration Complete",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext() ,Home.class));
                                            }
                                        });
                                    }
                                    else {
                                        Toast.makeText(Signup.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }


            }
        });


    }
}
