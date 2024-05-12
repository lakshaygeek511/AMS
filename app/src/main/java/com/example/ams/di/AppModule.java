package com.example.ams.di;

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

import android.content.Context;

import androidx.room.Room;

import com.example.ams.database.rooms.AppDatabase;
import com.example.ams.database.rooms.dao.AssetDao;
import com.example.ams.database.rooms.dao.MemberAssetDao;
import com.example.ams.database.rooms.dao.RoleDao;
import com.example.ams.database.rooms.dao.StatusDao;
import com.example.ams.database.rooms.dao.TeamDao;
import com.example.ams.database.rooms.dao.TypeDao;
import com.example.ams.database.rooms.dao.UserDao;
import com.example.ams.network.APIServices;
import com.example.ams.utils.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    String baseURL = Constants.BASE_URL;
    //Configured Timeout time for 10 Seconds
    int timeout = 10;

    // Provides a Retrofit instance
    @Singleton
    @Provides
    public Retrofit getRetroInstance() {
        // Create an instance of HttpLoggingInterceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // Set the logging level to show the body of the response
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // Set the logging level to show the body of the response
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build();
        // Create a Retrofit instance with base URL, Gson converter and RxJava call adapter
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }


    // Provides a APIServices instance
    @Singleton
    @Provides
    public APIServices getRetrofitRequest(Retrofit retrofit) {
        return retrofit.create(APIServices.class);
    }


    @Singleton
    @Provides
    public String getDatabase() {
        return Constants.APP_DATABASE;
    }


    // Provides a AppDatabase instance
    @Singleton
    @Provides
    public AppDatabase getRoomDatabaseInstance(@ApplicationContext Context context, String databaseName) {
        return Room.databaseBuilder(context, AppDatabase.class, databaseName).allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public UserDao userDao(AppDatabase appDatabase)
    {
        return appDatabase.userDao();
    }

    @Singleton
    @Provides
    public RoleDao roleDao(AppDatabase appDatabase)
    {
        return appDatabase.roleDao();
    }

    @Singleton
    @Provides
    public TeamDao teamDao(AppDatabase appDatabase)
    {
        return appDatabase.teamDao();
    }

    @Singleton
    @Provides
    public StatusDao statusDao(AppDatabase appDatabase)
    {
        return appDatabase.statusDao();
    }

    @Singleton
    @Provides
    public TypeDao typeDao(AppDatabase appDatabase)
    {
        return appDatabase.typeDao();
    }

    @Singleton
    @Provides
    public AssetDao assetDao(AppDatabase appDatabase)
    {
        return appDatabase.assetDao();
    }


    @Singleton
    @Provides
    public MemberAssetDao memberAssetDao(AppDatabase appDatabase)
    {
        return appDatabase.memberAssetDao();
    }

}
