package com.aman.thecakefairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.view.MenuItem;

public class Feedback extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    EditText name,mobile,subject,message;
    Button submit;
    MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference().child("UserFeedback");

        final UserFeedback userFeedback=new UserFeedback();
        name=(EditText)findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.mobile);
        subject=(EditText)findViewById(R.id.subject);
        message=(EditText)findViewById(R.id.message);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name=name.getText().toString().trim();
                final String Mobile=mobile.getText().toString().trim();
                final String Subject=subject.getText().toString().trim();
                final String Message=message.getText().toString().trim();


                userFeedback.setName(Name);
                userFeedback.setMobile(Mobile);
                userFeedback.setSubject(Subject);
                userFeedback.setMessage(Message);

                myRef.push().setValue(userFeedback);
                Toast.makeText(Feedback.this,"Thanks for Your Feedback",Toast.LENGTH_SHORT).show();

            }
        });



    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
