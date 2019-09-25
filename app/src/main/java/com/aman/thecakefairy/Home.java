package com.aman.thecakefairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView mRecyclerView;
    List<Cakedata> myCakeList;
    Cakedata mCakedata;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("The Cake Fairy");
        getSupportActionBar().getCustomView();
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Home.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        myCakeList = new ArrayList<>();
        mCakedata = new Cakedata("Chocolate", "it is a chocolate Cake", "Rs.450", R.drawable.chocolate);
        myCakeList.add(mCakedata);
        mCakedata = new Cakedata("BirdhouseCake", "It is Birdhouse Cake", "Rs.500", R.drawable.birdhousehousewormingcake);
        myCakeList.add(mCakedata);
        mCakedata = new Cakedata("ChocolateWithHappyBirthDayPrint", "Its is ChocolateWithHappyBirthDayPrint Cake", "Rs.600", R.drawable.chococakewithhappybirthday);
        myCakeList.add(mCakedata);
        mCakedata = new Cakedata("peach_Ricotta-LayerCAke", "It is peach_Ricotta-LayerCAke Cake", "Rs.1200", R.drawable.peachricottalayercake);
        myCakeList.add(mCakedata);
        mCakedata = new Cakedata("ChocMintDrilCake", "It is ChochMint DrilCAke Cake", "Rs.1200", R.drawable.chocmintdripcake);
        myCakeList.add(mCakedata);
        mCakedata = new Cakedata("ChocolateFudgeCake", "It is Chocolatefudge Cake", "Rs.400", R.drawable.chocklatefudgecake);
        myCakeList.add(mCakedata);
        mCakedata = new Cakedata("LemonMoussetorte", "It is LemonMoussetorte Cake", "Rs.400", R.drawable.lemonmoussetorte);
        myCakeList.add(mCakedata);

        CakeAdapter cakeAdapter = new CakeAdapter(Home.this, myCakeList);
        mRecyclerView.setAdapter(cakeAdapter);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id= menuItem.getItemId();
        if(id==R.id.share)
        {

            Toast.makeText(this,"this is Share",Toast.LENGTH_SHORT).show();
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        }
        if(id==R.id.exit)
        {
            Toast.makeText(this,"this is Logout",Toast.LENGTH_SHORT).show();
            System.exit(0);
            finish();
        }
        if(id==R.id.about)
        {
            startActivity(new Intent(getApplicationContext(),AboutUs.class));

            Toast.makeText(this,"this is about",Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.contactUs)
        {
            startActivity(new Intent(getApplicationContext(),ContactUs.class));

            Toast.makeText(this,"this is contactUs",Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.feedback)
        {
            startActivity(new Intent(getApplicationContext(),Feedback.class));

            Toast.makeText(this,"this is feedback",Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Leave Application?");
        alertDialogBuilder.setIcon(R.drawable.mainlogo);
        alertDialogBuilder
                .setMessage("Are you sure want to leave the application!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}









  /**
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if (id==R.id.share)
        {
            Toast.makeText(getApplicationContext(), "you Click Share", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.about)
        {
            Toast.makeText(getApplicationContext(), "you Click About", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.exit)
        {
        Toast.makeText(getApplicationContext(), "you Click Exit", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.cart)
        {
            Toast.makeText(getApplicationContext(), "you Click cart", Toast.LENGTH_SHORT).show();
        }
        return  true;
    }*/

