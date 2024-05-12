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

import com.example.ams.database.rooms.entity.RoleMasterEntity;

@Dao
public interface RoleDao
{

    //Insert role entity Data into Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoleEntity(RoleMasterEntity role);

    @Query("Select clm_roleId from tbl_role_master where clm_role= :role")
    int getRoleID(String role);

}
