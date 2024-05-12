package com.example.ams.views.OTP;

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

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;

import com.example.ams.views.FWP.FWPActivity;
import com.example.ams.views.UpdatePassword.UpdatePasswordActivity;
import com.example.ams.R;
import com.example.ams.databinding.ActivityEnterOtpBinding;
import com.example.ams.utils.Constants;

import java.util.Random;

public class EnterOTPActivity extends AppCompatActivity {

    private ActivityEnterOtpBinding mBinding;
    private static final String SHARED_PREF_NAME = Constants.OTP;
    private static final String OTP_KEY = Constants.OTP_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        // Set content view using binding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_enter_otp);
        // Generate random OTP
        String randomOtp = generateRandomOtp();
        // Show OTP as a notification
        showNotification(randomOtp);
        // Save OTP in Shared Preferences
        saveOtpInSharedPreferences(randomOtp);
        // Set up listeners
        listeners();
    }

    // Set up click listeners for buttons
    private void listeners()
    {
        // Validate input and confirm otp
        mBinding.button.setOnClickListener(view -> {
            String otp = mBinding.otp.getText().toString();
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String storedOtp = sharedPreferences.getString(OTP_KEY, "");

            if(storedOtp.equals(otp))
            {
                Intent intent = new Intent(EnterOTPActivity.this, UpdatePasswordActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(EnterOTPActivity.this, R.string.invalid_otp, Toast.LENGTH_LONG).show();
            }

        });

        // Move to fwp activity on back button click
        mBinding.back.setOnClickListener(view -> {
            Intent intent = new Intent(EnterOTPActivity.this, FWPActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Generate random OTP
    private String generateRandomOtp() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otp = random.nextInt(999999);
        return String.valueOf(otp);
    }

    // Showing otp notification
    private void showNotification(String otp) {
        // Creating Notification channel
        String CHANNEL_ID = Constants.OTP_CHANNEL_ID;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.mobile_no_icon)
                .setContentTitle("Your OTP")
                .setContentText("Your OTP is: " + otp)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = 1;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
        {
            // Notifying the otp
            notificationManager.notify(notificationId, builder.build());
        }

    }

    // Save OTP in Shared Preferences
    private void saveOtpInSharedPreferences(String otp) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(OTP_KEY, otp);
        editor.apply();
    }
}
