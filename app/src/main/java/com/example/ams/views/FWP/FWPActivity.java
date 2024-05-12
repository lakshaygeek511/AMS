package com.example.ams.views.FWP;

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
 * VE00YM572            18-1-2024
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.ams.databinding.ActivityFwpactivityBinding;
import com.example.ams.utils.Constants;
import com.example.ams.utils.DialogUtils;
import com.example.ams.R;
import com.example.ams.model.FWPData;
import com.example.ams.model.RequestBodyFWP;
import com.example.ams.views.OTP.EnterOTPActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FWPActivity extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = Constants.PHONE;
    private static final String PREFS_KEY = Constants.PHONE_KEY;
    private ActivityFwpactivityBinding mBinding;
    private ViewModelFWP mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fwpactivity);

        // Set content view using binding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_fwpactivity);
        // Initialize ViewModel using Hilt
        mViewModel = new ViewModelProvider(this).get(ViewModelFWP.class);

        // Set up listeners and observers
        listener();
        observer();
    }

    // Set up click listeners for buttons
    private void listener() {
        mBinding.button.setOnClickListener(view -> {
            String phone = mBinding.phone.getText().toString();

            // Validate input and provide otp to user
            if (isValidInput(phone)) {
                FWPData Data = new FWPData(phone);
                RequestBodyFWP  request = new RequestBodyFWP(Data);
                mViewModel.getOTP(request);

            }

        });

        // Move to login activity on back button click
        mBinding.back.setOnClickListener(view -> {
            finish();
        });
    }

    // Observe ViewModel LiveData for OTP status and error messages
    private void observer()
    {
        mViewModel.fwpResponse.observe(this, response -> {
            saveInSharedPreferences(mBinding.phone.getText().toString());
            Toast.makeText(FWPActivity.this, R.string.otp_sent_text, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(FWPActivity.this, EnterOTPActivity.class);
            startActivity(intent);
        });

        mViewModel.errorResponse.observe(this, error -> {
            DialogUtils.showErrorDialog(Constants.ERROR,error, FWPActivity.this);
        });

        mViewModel.progress.observe(this,aBoolean -> {
            if(aBoolean)
            {
                mBinding.progressBar.setVisibility(View.VISIBLE);
            }
            else
            {
                new Handler().postDelayed(() -> {
                    mBinding.progressBar.setVisibility(View.INVISIBLE);
                }, 1000);
            }
        });
    }

    // Saving Phone No in Shared Preferences
    private void saveInSharedPreferences(String phone) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFS_KEY, phone);
        editor.apply();
    }

    // Validate user input
    private boolean isValidInput(String phone) {

        // Check if the phone number is empty
        if (phone.isEmpty())
        {
            Toast.makeText(this, R.string.empty_no_text, Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if the phone number has 10 digits
        if (!TextUtils.isDigitsOnly(phone) || phone.length() != 10) {
            Toast.makeText(this, R.string.invalid_no_text, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}