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

@Entity(tableName = "tbl_user")
public class UserEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "clm_ein")
    private String ein;

    @ColumnInfo(name = "clm_name")
    private String name;

    @ColumnInfo(name = "clm_phoneNo")
    private String phoneNo;

    @ColumnInfo(name = "clm_password")
    private String password;

    @ColumnInfo(name = "clm_teamId")
    private int teamId;

    @ColumnInfo(name = "clm_roleId")
    private int roleId;

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


    public UserEntity(String ein, String name, String phoneNo, String password, int teamId, int roleId, String createdAt, String createdBy, String updatedAt, String updatedBy, boolean isDeleted) {
        this.ein = ein;
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
        this.teamId = teamId;
        this.roleId = roleId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.isDeleted = isDeleted;
    }

    @NonNull
    public String getEin() {
        return ein;
    }

    public void setEin(@NonNull String ein) {
        this.ein = ein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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


