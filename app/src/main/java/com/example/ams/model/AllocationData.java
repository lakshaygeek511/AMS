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

public class AllocationData
{
    String asset_id;
    int status;
    String asset_name;
    String asset_type;
    String asset_model;
    String serial_no;
    String purchase_date;
    String warranty;

    public AllocationData(String asset_id,int status,String asset_name, String asset_type, String asset_model, String serial_no, String purchase_date, String warranty) {
        this.asset_id = asset_id;
        this.asset_name = asset_name;
        this.asset_type = asset_type;
        this.asset_model = asset_model;
        this.serial_no = serial_no;
        this.purchase_date = purchase_date;
        this.warranty = warranty;
        this.status = status;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getAsset_model() {
        return asset_model;
    }

    public void setAsset_model(String asset_model) {
        this.asset_model = asset_model;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
