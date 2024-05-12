package com.example.ams.views;

/**
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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


import com.example.ams.database.sharedprefs.SharedPref;
import com.example.ams.views.DashBoard.DashboardActivity;
import com.example.ams.views.Login.LoginActivity;
import com.example.ams.R;
import com.example.ams.utils.Constants;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPref.init(getApplicationContext());
        moveFromSplashScreen();

    }

    /*
     * This function navigates user to screens based on userLogin Status.
     * If user is Logged In he/she moves directly to Dashboard
     * else Login Screen.
     * */
    private void moveFromSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(userDetailsSaved()){
                    moveToDashboard();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        },3000);
    }

//  This function checks whether user details are saved in Shared Preferences

    public boolean userDetailsSaved(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.BOOL_USER_LOGGED_IN, Context.MODE_PRIVATE);
         return sharedPreferences.getBoolean(Constants.LOGGED_IN, false);
    }


//  This function is used to navigate the user to Dashboard Screen

    public void moveToDashboard(){
        Intent intent=new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}