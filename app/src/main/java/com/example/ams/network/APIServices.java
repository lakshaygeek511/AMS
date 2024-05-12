package com.example.ams.network;

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

import com.example.ams.model.FWPResponse;
import com.example.ams.model.LoginResponse;
import com.example.ams.model.PWDRequestBody;
import com.example.ams.model.RegistrationResponse;
import com.example.ams.model.RequestBodyFWP;
import com.example.ams.model.RequestBodyLogin;
import com.example.ams.model.RequestBodyRegister;
import com.example.ams.model.RequestBodySignIn;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Interface defining the API services using Retrofit annotations.
 */
public interface APIServices {

//    @GET
//    Single<EmployeeResponse> getEmployee(@Url String url);

    /*
     * Registers a new user through the remote API.
     *
     * endpoint   The API endpoint for user registration.
     * RequestBody The request body containing user registration information.
     * A Single emitting the response containing registration information.
     */
    @POST
    Single<RegistrationResponse> register(@Url String url , @Body RequestBodyRegister requestBody);

    /*
     * Logs in a user through the remote API.
     *
     * endpoint   The API endpoint for user login.
     * RequestBody The request body containing user login information.
     * A Single emitting the response containing login information.
     */
    @POST
    Single<LoginResponse> login(@Url String url , @Body RequestBodyLogin requestBody);

    /*
     * Logs in a user through the remote API.
     *
     * endpoint   The API endpoint for user login.
     * RequestBody The request body containing user login information.
     * A Single emitting the response containing login information.
     */
    @POST
    Single<LoginResponse> signIn(@Url String url , @Body RequestBodySignIn requestBody);

    /*
     * Requests a one-time password (OTP) from the remote API.
     *
     * endpoint   The API endpoint for OTP request.
     * RequestBody The request body containing necessary information for OTP generation.
     * A Single emitting the response containing OTP information.
     */
    @POST
    Single<FWPResponse> getOTP(@Url String url , @Body RequestBodyFWP requestBody);

    /*
     * Updates user password through the remote API.
     *
     * endpoint   The API endpoint for updating the password.
     * RequestBody The request body containing necessary information for password update.
     * A Single emitting the response containing password update information.
     */
    @POST
    Single<FWPResponse> updatePassword(@Url String url , @Body PWDRequestBody requestBody);

}
