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
 * VE00YM572            15-1-2024
 */

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.ams.R;
import com.example.ams.model.FWPResponse;
import com.example.ams.model.PWDRequestBody;
import com.example.ams.network.AppRepo;
import com.example.ams.utils.Constants;
import com.example.ams.utils.InternetCheck;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

@HiltViewModel
public class ViewModelUpdatePassword extends AndroidViewModel
{
    private final AppRepo mAppRepo;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public MutableLiveData<String> response = new MutableLiveData<>();
    public MutableLiveData<String> errorResponse = new MutableLiveData<>();
    public  MutableLiveData<Boolean> progress = new MutableLiveData<>();

    @SuppressLint("StaticFieldLeak")
    public Context context;

    @Inject
    public ViewModelUpdatePassword(Application applicationContext, AppRepo mAppRepo) {
        super(applicationContext);
        this.mAppRepo = mAppRepo;
        context = getApplication().getApplicationContext();
    }

    // Method for updating password
    public void updatePassword(PWDRequestBody bodyPWD)
    {
        // Check internet connection before making the update request
        if (InternetCheck.checkConnection(context))
        {
            progress.postValue(true);
            mCompositeDisposable.add(mAppRepo.updatePassword(Constants.ENDPOINTF, bodyPWD)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<FWPResponse>() {
                        // Handle successful update response
                        @Override
                        public void onSuccess(FWPResponse fwpRes) {
                            if(fwpRes.getStatus() == 0)
                            {
                                progress.postValue(false);
                                response.postValue(String.valueOf(R.string.pwd_update_success));
                                Log.println(Log.INFO,Constants.SUCCESS, String.valueOf(R.string.log_pwd));
                            }
                        }

                        // Handle update failure, including HTTP error codes
                        @Override
                        public void onError(Throwable error) {
                            if(error instanceof HttpException)
                            {
                                HttpException httpException = (HttpException) error;
                                int statusCode = httpException.code();
                                if (statusCode == 500) {
                                    progress.postValue(false);
                                    errorResponse.postValue(context.getString(R.string.update_text));
                                    Log.println(Log.INFO,Constants.FAILURE, String.valueOf(R.string.log_pwd_fail));
                                }
                            }
                            else
                            {
                                // Handle non-HTTP errors
                                progress.postValue(false);
                                errorResponse.postValue(context.getString(R.string.server_text));
                                Log.println(Log.INFO, Constants.FAILURE, String.valueOf(R.string.log_server_fail));
                            }
                        }
                    }));
        }
        else
        {
            // Handle poor network connection
            errorResponse.postValue(context.getString(R.string.wentwrong_text));
            Log.println(Log.INFO,Constants.NETWORK_ERROR,"POOR NETWORK");
        }
    }

}
