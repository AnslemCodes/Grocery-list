package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_TEXT = "com.example.grocerylistapp.EXTRA_TEXT";
    public static final String EXTRA_NUMBER ="com.example.grocerylistapp.EXTRA_NUMBER";


    private ShareActionProvider shareActionProvider;


    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







//since we've remove the default action bar, implement the toolbar to show on our action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //drawer navigation bar
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //lets add the AddInputFragment at the main activity

        //in other to show this fragment immediately we start our app we have to override  the onCreate method
        if (savedInstanceState == null){

        // to start our activity we will open our add  fragment immediately before we click any item
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new AddInputFragment()).commit();

        //to select our first item in the drawer itself
        navigationView.setCheckedItem(R.id.nav_add);}

    }



    //to close the navigation drawer by clicking the back pressed


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    //inflating the menu layout on tool bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.share_item);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent ("your message");
        return super.onCreateOptionsMenu(menu);
    }

    // method for implicit action
    public void setShareActionIntent(String your_message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,your_message);
        shareActionProvider.setShareIntent(intent);

    }

    //setting on click listener on toolbar menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        if (item.getItemId() == R.id.refresh) {
            Toast.makeText(this, "Refresh Activity", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.settings) {
            Toast.makeText(this, "Settings Activity", Toast.LENGTH_LONG).show();
        }


        if (item.getItemId() == R.id.clear_out) {
            Toast.makeText(this, "Clear out Activity", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }



    // this method will open the profile fragment
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {





        switch (item.getItemId()) {


            case R.id.nav_add:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddInputFragment()).commit();
                break;


            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;


            case R.id.phone:
                Toast.makeText(this, "08081000083", Toast.LENGTH_LONG).show();
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}


