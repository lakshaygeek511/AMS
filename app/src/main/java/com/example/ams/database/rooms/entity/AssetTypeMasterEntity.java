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

@Entity(tableName = "tbl_asset_type_master")
public class AssetTypeMasterEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "clm_typeId")
    private int typeId;

    @ColumnInfo(name = "clm_assetType")
    private String assetType;

    public AssetTypeMasterEntity(String assetType) {
        this.assetType = assetType;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
}
