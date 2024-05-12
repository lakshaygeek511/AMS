package com.example.ams.views.UpdatePassword;

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
import android.view.View;
import android.widget.Toast;

import com.example.ams.databinding.ActivityUpdatePasswordBinding;
import com.example.ams.R;
import com.example.ams.model.PWDData;
import com.example.ams.model.PWDRequestBody;
import com.example.ams.utils.Constants;
import com.example.ams.utils.DialogUtils;
import com.example.ams.views.Login.LoginActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdatePasswordActivity extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = Constants.PHONE;
    private static final String PHONE_KEY = Constants.PHONE_KEY;
    private ActivityUpdatePasswordBinding mBinding;
    private ViewModelUpdatePassword mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        // Set content view using binding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_password);
        // Initialize ViewModel using Hilt
        mViewModel = new ViewModelProvider(this).get(ViewModelUpdatePassword.class);
        // Set up listeners and observers
        listener();
        observer();
    }

    // Set up click listeners for buttons
    private void listener() {

        mBinding.button.setOnClickListener(view -> {
            String password = mBinding.password.getText().toString();
            String newpassword = mBinding.newpassword.getText().toString();
            // Validate input and set password
            if (isValidInput(password) && isValidInput(newpassword) && password.equals(newpassword))
            {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String storedPhoneNo = sharedPreferences.getString(PHONE_KEY, "");

                PWDData pwdData = new PWDData(storedPhoneNo,password);
                PWDRequestBody pwdRequestBody = new PWDRequestBody(pwdData);

                mViewModel.updatePassword(pwdRequestBody);
            }
            else
            {
                Toast.makeText(this, R.string.pwd_mismatch, Toast.LENGTH_SHORT).show();
            }

        });

        // Move to verify otp activity on back button click
        mBinding.back.setOnClickListener(view -> {
            finish();
        });
    }

    // Observe ViewModel LiveData for password update status and error messages
    private void observer()
    {
        mViewModel.response.observe(this, response -> {
            Toast.makeText(UpdatePasswordActivity.this, R.string.pwd_update, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        });

        mViewModel.errorResponse.observe(this, error -> {
            DialogUtils.showErrorDialog(Constants.ERROR,error, UpdatePasswordActivity.this);
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

    private boolean isValidInput(String password) {

        // Check if the password is empty
        if (password.isEmpty())
        {
            Toast.makeText(this, R.string.empty_pwd_text, Toast.LENGTH_SHORT).show();
            return false;
        }
        // Check password length and complexity
        if (password.length() < 8 || !password.matches(Constants.PWD_REGEX)) {
            Toast.makeText(this, R.string.invalid_pwd_text, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}