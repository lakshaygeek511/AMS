package com.example.ams.database.rooms.dao;

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

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ams.database.rooms.entity.AssetTypeMasterEntity;

import java.util.List;

@Dao
public interface TypeDao
{

    //Insert status entity Data into Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTypeEntity(AssetTypeMasterEntity typeMaster);

    @Query("Select clm_typeId from tbl_asset_type_master where clm_assetType = :type")
    int getTypeID(String type);

    @Query("Select clm_assetType from tbl_asset_type_master")
    List<String> getType();

}
