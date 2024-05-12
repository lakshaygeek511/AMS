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

import com.example.ams.database.rooms.entity.AssetStatusMasterEntity;

@Dao
public interface StatusDao
{

    //Insert status entity Data into Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStatusEntity(AssetStatusMasterEntity statusMaster);

    @Query("Select clm_statusId from tbl_asset_status_master where clm_statusType = :status")
    int getStatusID(String status);

}
