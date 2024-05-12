package com.example.ams.views.DashBoard;

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

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;

import com.example.ams.network.AppRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ViewModelDashboard extends AndroidViewModel
{

    private final AppRepo mAppRepo;

    public Context context;

    @Inject
    public ViewModelDashboard(Application applicationContext, AppRepo mAppRepo) {
        super(applicationContext);
        this.mAppRepo = mAppRepo;
        context = getApplication().getApplicationContext();
    }


}
