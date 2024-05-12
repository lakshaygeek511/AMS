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

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ams.database.rooms.entity.MemberAssetMappingEntity;

import java.util.List;

@Dao
public interface MemberAssetDao
{
    //Insert member entity Data into Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMemberAssetEntity(MemberAssetMappingEntity memberAsset);

    @Query("UPDATE tbl_member_asset_mapping SET clm_allocation_status = :newStatus WHERE clm_asset_id = :assetId")
    void updateAllocationStatus(String assetId, boolean newStatus);

    @Query("SELECT u.clm_name AS name, u.clm_ein AS ein, mam.clm_allocation_status AS allocation_status, mam.clm_createdAt as allocation_date " +
            "FROM tbl_member_asset_mapping mam " +
            "INNER JOIN tbl_user u ON mam.clm_ein = u.clm_ein " +
            "WHERE mam.clm_asset_id = :assetId")
    LiveData<List<AssetAllocationInfo>> getAssetAllocationInfo(String assetId);

}


