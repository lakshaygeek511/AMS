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

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;

import com.example.ams.database.rooms.entity.UserEntity;
import com.example.ams.model.LoginData;
import com.example.ams.network.AppRepo;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ViewModelLogin extends AndroidViewModel
{
    private final AppRepo mAppRepo;

    public Context context;

    @Inject
    public ViewModelLogin(Application applicationContext, AppRepo mAppRepo) {
        super(applicationContext);
        this.mAppRepo = mAppRepo;
        context = getApplication().getApplicationContext();
    }

    public UserEntity loginUser(LoginData loginData)
    {
        return mAppRepo.loginUser(loginData);
    }

}
