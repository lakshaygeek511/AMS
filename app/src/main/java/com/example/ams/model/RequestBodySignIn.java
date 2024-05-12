package com.example.ams.model;

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

public class RequestBodySignIn
{
    private LoginData data;

    public RequestBodySignIn(LoginData data) {
        this.data = data;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
