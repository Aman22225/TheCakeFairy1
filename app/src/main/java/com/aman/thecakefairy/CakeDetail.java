package com.aman.thecakefairy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CakeDetail extends AppCompatActivity {
    TextView cakeDescription,cakeName,cakePrice;
    ImageView cakeImage;
    Button btnPlaceOrder;
    String currentCake;
    DatabaseReference requests;
    FirebaseDatabase Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_detail);
        getSupportActionBar().setTitle("Cake Detail");

        Database=FirebaseDatabase.getInstance();
        requests=Database.getReference("Requests");
        requests = FirebaseDatabase.getInstance().getReference("Request");
        btnPlaceOrder=(Button)findViewById(R.id.btn_placeOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();

            }
        });
        cakeDescription=(TextView)findViewById(R.id.txtDescription);
        cakeName=(TextView)findViewById(R.id.txtTitle);
        cakePrice=(TextView)findViewById(R.id.txtPrice);
        cakeImage=(ImageView)findViewById(R.id.ivImage);

        Bundle mBundle=getIntent().getExtras();
        if(mBundle!=null)
        {
            cakeImage.setImageResource(mBundle.getInt("Image"));
            cakeName.setText(mBundle.getString("Title"));
            cakeDescription.setText(mBundle.getString("Description"));
            cakePrice.setText(mBundle.getString("Price"));

        }

    }

    private void showAlertDialog() {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(CakeDetail.this);
        alertDialog.setTitle("one more Step!");
        alertDialog.setMessage("Enter First Below Fields");
        LinearLayout layout=new LinearLayout(this);

        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText editFullName=new EditText(CakeDetail.this);
        editFullName.setHint("Enter your Full Name");

        final EditText editNumber=new EditText(CakeDetail.this);
        editNumber.setHint("Enter your Mobile Number");

        final EditText editAddress=new EditText(CakeDetail.this);
        editAddress.setHint("Enter your Full Address");

        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editAddress.setLayoutParams(lp);
        editFullName.setLayoutParams(lp);
        layout.addView(editFullName);
        layout.addView(editNumber);
        layout.addView(editAddress);

        alertDialog.setView(layout);

        alertDialog.setPositiveButton("confirm order", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Request request = new Request(
                        editFullName.getText().toString(),
                        editNumber.getText().toString(),
                        cakeName.getText().toString(),
                        editAddress.getText().toString(),
                        cakePrice.getText().toString()



                );

                //Submit to Firebase
                Request information = new Request(cakeName, editAddress, editFullName, cakePrice, editNumber);
                FirebaseDatabase.getInstance().getReference("Request")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(information);
                requests.child(String.valueOf((System.currentTimeMillis())))
                        .setValue(request);
                //delete Detals

                //new Database(getBaseContext()).cleanCakeDetails()
                Toast.makeText(CakeDetail.this, "thank you ,Order Place", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alertDialog.setNegativeButton("cencel order", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
       // alertDialog.show();
        // Initially disable the button
        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

           editNumber.setFocusable(false);
           editAddress.setFocusable(false);


           editFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if edittext is empty
                if (TextUtils.isEmpty(s)) {

                } else {

                    editNumber.setFocusableInTouchMode(true);
                }
            }

            });



           editNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if edittext is empty
                if (TextUtils.isEmpty(s)) {

                } else {

                    editAddress.setFocusableInTouchMode(true);
                }


            }

        });
//this is for address

        editAddress.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if edittext is empty
                if (TextUtils.isEmpty(s)) {
                    // Disable ok button
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);



                } else {
                    // Something into edit text. Enable the button.
                   ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    editFullName.setFocusable(false);
                    editNumber.setFocusable(false);
                   // editAddress.setFocusable(false);

                }


            }

        });


    }

    public void go_to_home(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }
}
