package com.example.ams.database.rooms.entity;

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

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_asset")
public class AssetEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "clm_asset_id")
    private String asset_id;

    @ColumnInfo(name = "clm_asset_name")
    private String asset_name;

    @ColumnInfo(name = "clm_type_id")
    private int type_id;

    @ColumnInfo(name = "clm_asset_model")
    private String asset_model;

    @ColumnInfo(name = "clm_serial_no")
    private String serial_no;

    @ColumnInfo(name = "clm_purchase_date")
    private String purchase_date;

    @ColumnInfo(name = "clm_warranty")
    private String warranty;

    @ColumnInfo(name = "clm_cost")
    private String cost;

    @ColumnInfo(name = "clm_location")
    private String location;

    @ColumnInfo(name = "clm_asset_status")
    private int asset_status;

    @ColumnInfo(name = "clm_createdAt")
    private String createdAt;

    @ColumnInfo(name = "clm_createdBy")
    private String createdBy;

    @ColumnInfo(name = "clm_updatedAt")
    private String updatedAt;

    @ColumnInfo(name = "clm_updatedBy")
    private String updatedBy;

    @ColumnInfo(name = "clm_isDeleted")
    private boolean isDeleted;

    // Constructor, getters, and setters


    public AssetEntity(String asset_id, String asset_name, int type_id, String asset_model, String serial_no, String purchase_date, String warranty, String cost, String location, int asset_status, String createdAt, String createdBy, String updatedAt, String updatedBy, boolean isDeleted) {
        this.asset_id = asset_id;
        this.asset_name = asset_name;
        this.type_id = type_id;
        this.asset_model = asset_model;
        this.serial_no = serial_no;
        this.purchase_date = purchase_date;
        this.warranty = warranty;
        this.cost = cost;
        this.location = location;
        this.asset_status = asset_status;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.isDeleted = isDeleted;
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAsset_status() {
        return asset_status;
    }

    public void setAsset_status(int asset_status) {
        this.asset_status = asset_status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

