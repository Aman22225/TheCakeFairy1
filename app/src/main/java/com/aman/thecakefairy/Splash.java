package com.aman.thecakefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=5000;    //5sec
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent=new Intent(Splash.this,Login.class);
                startActivity(splashIntent);
                finish();

            }
        },SPLASH_TIME_OUT);

    }
}
