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

@Entity(tableName = "tbl_member_asset_mapping")
public class MemberAssetMappingEntity
{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "clm_allocation_id")
    private int allocation_id;

    @ColumnInfo(name = "clm_asset_id")
    private String asset_id;

    @ColumnInfo(name = "clm_ein")
    private String ein;

    @ColumnInfo(name = "clm_allocation_status")
    private boolean allocation_status ;

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


    public MemberAssetMappingEntity(String asset_id, String ein, boolean allocation_status, String createdAt, String createdBy, String updatedAt, String updatedBy, boolean isDeleted) {
        this.asset_id = asset_id;
        this.ein = ein;
        this.allocation_status = allocation_status;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.isDeleted = isDeleted;
    }


    public int getAllocation_id() {
        return allocation_id;
    }

    public void setAllocation_id(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public boolean isAllocation_status() {
        return allocation_status;
    }

    public void setAllocation_status(boolean allocation_status) {
        this.allocation_status = allocation_status;
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
