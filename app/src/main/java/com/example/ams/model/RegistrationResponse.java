package com.example.ams.model;

import java.util.List;


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

public class RegistrationResponse {
    private String message;
    private List<DataItem> data;
    private int status;

    public RegistrationResponse(String message, List<DataItem> data, int status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataItem {
        private int registerUserData;

        public DataItem(int registerUserData) {
            this.registerUserData = registerUserData;
        }

        @Override
        public String toString() {
            return "RegisterUserData: " + registerUserData;
        }
    }
}
