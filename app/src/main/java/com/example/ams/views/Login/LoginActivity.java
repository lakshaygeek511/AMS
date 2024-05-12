package com.example.ams.views.Login;

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
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.example.ams.database.rooms.entity.AssetStatusMasterEntity;
import com.example.ams.database.rooms.entity.AssetTypeMasterEntity;
import com.example.ams.database.rooms.entity.RoleMasterEntity;
import com.example.ams.database.rooms.entity.TeamMasterEntity;
import com.example.ams.database.rooms.entity.UserEntity;
import com.example.ams.utils.Utils;
import com.example.ams.views.DashBoard.DashboardActivity;
import com.example.ams.views.FWP.FWPActivity;
import com.example.ams.R;
import com.example.ams.model.LoginData;
import com.example.ams.utils.Constants;
import com.example.ams.utils.DialogUtils;
import com.example.ams.databinding.ActivityLoginBinding;
import com.example.ams.views.ViewModelMain;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;
    private ViewModelLogin mViewModel;
    private ViewModelMain ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Binding initialization
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        // ViewModel initialization
        mViewModel = new ViewModelProvider(this).get(ViewModelLogin.class);

        ViewModel = new ViewModelProvider(this).get(ViewModelMain.class);

        // Set up listeners
        listener();

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MASTER_STATUS, Context.MODE_PRIVATE);
        boolean masterStatus  = sharedPreferences.getBoolean(Constants.STATUS,false);

        if (!masterStatus)
        {
            setupMasters();
        }

    }


    private void setupMasters()
    {
        String[] roleItemsList = getResources().getStringArray(R.array.role_array);
        for (int i = 1; i < roleItemsList.length; i++) {
            String roleItem = roleItemsList[i];
            RoleMasterEntity role = new RoleMasterEntity(roleItem);
            ViewModel.insertRole(role);
        }

        String[] teamItemsList = getResources().getStringArray(R.array.team_array);
        for (int i = 1; i < teamItemsList.length; i++) {
            String teamItem = teamItemsList[i];
            TeamMasterEntity team = new TeamMasterEntity(teamItem);
            ViewModel.insertTeam(team);
        }


        String[] statusItemsList = getResources().getStringArray(R.array.status_array);
        for (int i = 1; i < statusItemsList.length; i++) {
            String statusItem = statusItemsList[i];
            AssetStatusMasterEntity statusMaster = new AssetStatusMasterEntity(statusItem);
            ViewModel.insertStatus(statusMaster);
        }

        String[] typeItemsList = getResources().getStringArray(R.array.asset_type_array);
        for (int i = 1; i < typeItemsList.length; i++) {
            String typeItem = typeItemsList[i];
            AssetTypeMasterEntity typeMaster = new AssetTypeMasterEntity(typeItem);
            ViewModel.insertType(typeMaster);
        }

        String ID = Constants.ADMIN_ID;
        UserEntity entity = new UserEntity(ID,Constants.ADMIN_NAME,Constants.ADMIN_NO,Constants.ADMIN_PWD,ViewModel.getTeamID(Constants.ADMIN_TEAM),ViewModel.getRoleID(Constants.ADMIN_ROLE),Utils.getCurrentDateTime(),ID,Utils.getCurrentDateTime(),ID,false);
        ViewModel.insertUser(entity);

        saveInSharedPreferences(Constants.MASTER_STATUS,Constants.STATUS,true);

    }

    // Set up click listeners for buttons
    private void listener() {
        mBinding.button.setOnClickListener(view -> {
            String id = mBinding.id.getText().toString().trim();
            String password = mBinding.password.getText().toString().trim();

            // Validate input and login user
            if (isValidInput(id, password))
            {
                LoginData loginData = new LoginData(id,password);
                UserEntity user  = mViewModel.loginUser(loginData);

                boolean status = false;

                if (user != null) {
                    if(user.getRoleId() == 2)
                    {
                        if (user.getPassword().equals(loginData.getPassword()))
                        {
                            status = true;
                        }
                        else
                        {
                            DialogUtils.showErrorDialog(Constants.ERROR, getString(R.string.invalid_cred) ,LoginActivity.this);

                        }
                    }
                    else
                    {
                        DialogUtils.showErrorDialog(Constants.ERROR, getString(R.string.invalid_user) ,LoginActivity.this);

                    }
                }

                if (status)
                {
                    saveInSharedPreferences( Constants.BOOL_USER_LOGGED_IN,Constants.LOGGED_IN,true);
                    saveSharedPreferences(Constants.USERNAME,Constants.NAME,user.getName());
                    saveSharedPreferences(Constants.USERID,Constants.ID,user.getEin());

                    // Use an Alpha Animation to make the msg appear gradually
                    AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
                    fadeInAnimation.setDuration(1000); // Duration in milliseconds
                    mBinding.msg.startAnimation(fadeInAnimation);

                    mBinding.msg.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(() ->
                    {
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }, 1000);


                    resetFields();
                }

            }

        });


    }

    // Reset Login Screen Fields
    private void resetFields() {
        mBinding.id.setText("");
        mBinding.password.setText("");

    }

    // Saves in Boolean Shared Preferences
    private void saveInSharedPreferences(String prefsname, String prefskey ,boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences(prefsname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(prefskey, status);
        editor.apply();
    }

    // Saves in String Shared Preferences
    private void saveSharedPreferences(String prefsname, String prefskey , String status) {
        SharedPreferences sharedPreferences = getSharedPreferences(prefsname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefskey, status);
        editor.apply();
    }

    // Validate User Login Input
    private boolean isValidInput(String id, String password) {

        // Check if the name is empty
        if (id.isEmpty())
        {
            DialogUtils.showDialog(getString(R.string.empty_ein),this);
            return false;
        }

        id = id.toUpperCase();

        if (!id.matches(Constants.ID_REGEX)) {
            DialogUtils.showDialog(getString(R.string.valid_ein),this);
            return false;
        }

        // Check if the password is empty
        if (password.isEmpty())
        {
            DialogUtils.showDialog(getString(R.string.empty_pwd_text),this);
            return false;
        }

        // Check password length and complexity
        if (password.length() < 8 || !password.matches(Constants.PWD_REGEX)) {
            DialogUtils.showDialog(getString(R.string.invalid_pwd_text),this);
            return false;
        }

        return true;
    }

}