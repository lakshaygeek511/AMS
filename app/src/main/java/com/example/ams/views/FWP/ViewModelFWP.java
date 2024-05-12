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

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.ams.network.AppRepo;
import com.example.ams.R;
import com.example.ams.model.FWPResponse;
import com.example.ams.model.RequestBodyFWP;
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
public class ViewModelFWP extends AndroidViewModel
{
    private final AppRepo mAppRepo;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public MutableLiveData<String> fwpResponse = new MutableLiveData<>();
    public MutableLiveData<String> errorResponse = new MutableLiveData<>();
    public  MutableLiveData<Boolean> progress = new MutableLiveData<>();

    @SuppressLint("StaticFieldLeak")
    public Context context;

    @Inject
    public ViewModelFWP(Application applicationContext, AppRepo mAppRepo) {
        super(applicationContext);
        this.mAppRepo = mAppRepo;
        context = getApplication().getApplicationContext();
    }

    // Method for sending OTP to user
    public void getOTP(RequestBodyFWP bodyFWP)
    {
        // Check internet connection before making the otp request
        if (InternetCheck.checkConnection(context))
        {
            progress.postValue(true);
            mCompositeDisposable.add(mAppRepo.getOTP(Constants.ENDPOINTE, bodyFWP)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<FWPResponse>() {

                        // Handle successful phone verification response
                        @Override
                        public void onSuccess(FWPResponse fwpRes) {
                            if(fwpRes.getStatus() == 0)
                            {
                                progress.postValue(false);
                                fwpResponse.postValue(String.valueOf(R.string.otp_success));
                                Log.println(Log.INFO,Constants.SUCCESS, String.valueOf(R.string.otp_success));
                            }
                        }

                        // Handle phone verification failure, including HTTP error codes
                        @Override
                        public void onError(Throwable error) {
                            if(error instanceof HttpException)
                            {
                                HttpException httpException = (HttpException) error;
                                int statusCode = httpException.code();
                                if (statusCode == 404) {
                                    progress.postValue(false);
                                    errorResponse.postValue(context.getString(R.string.notfound_text));
                                    Log.println(Log.INFO,Constants.FAILURE, String.valueOf(R.string.otp_fail));
                                }
                            }
                            else
                            {
                                // Handle non-HTTP errors
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
            Log.println(Log.INFO,Constants.NETWORK_ERROR, String.valueOf(R.string.log_network));
        }
    }

}
