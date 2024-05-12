package com.example.ams.views.DashBoard;

/*
 * Project Name : AMS
 *
 * @author VE00YM572
 * @company YMSLI
 * @date 15-1-2024
 * Copyright (c) 2024, Yamaha Motor Solutions (INDIA) Pvt Ltd.
 * Revision History
 * ----------------------------------------------------------
 * Modified By          Modified On
 * VE00YM572            15-1-2024
 */

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ams.R;
import com.example.ams.utils.Constants;
import com.example.ams.utils.DialogUtils;
import com.example.ams.views.Login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardActivity extends AppCompatActivity
{
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_logout) {
                // Handle logout
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                saveInSharedPreferences( Constants.BOOL_USER_LOGGED_IN,Constants.LOGGED_IN,false);

            } else {
                // Handle other navigation item clicks
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            return true;
        });

        View headerView = navigationView.getHeaderView(0);

        SharedPreferences idsharedPreferences = getSharedPreferences(Constants.USERID, Context.MODE_PRIVATE);
        String userid  = idsharedPreferences.getString(Constants.ID,"");

        SharedPreferences namesharedPreferences = getSharedPreferences(Constants.USERNAME, Context.MODE_PRIVATE);
        String username  = namesharedPreferences.getString(Constants.NAME,"");

        TextView usernameTextView = headerView.findViewById(R.id.username);
        usernameTextView.setText(username);

        TextView useridTextView = headerView.findViewById(R.id.userid);
        useridTextView.setText(userid);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            TextView text = findViewById(R.id.toolbarText);
            text.setText(R.string.home_text);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }

        // Setting Up Bottom Navigation View & Fragment Loading Listener

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemid = item.getItemId();

            if (itemid == R.id.bottom_home)
            {
                TextView text = findViewById(R.id.toolbarText);
                text.setText(R.string.home_text);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            }
            else if (itemid == R.id.bottom_registration)
            {
                DialogUtils.showSelectionDialog(this);
                return true;
            }
            else if (itemid == R.id.bottom_allocation)
            {
                DialogUtils.showAllocationDialog(this);
                return true;
            }
            return false;
        });

    }

    // Saving in Shared Preferences
    private void saveInSharedPreferences(String prefsname, String prefskey ,boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences(prefsname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(prefskey, status);
        editor.apply();
    }

    // Drawer Navigation Setup on Back Press
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}