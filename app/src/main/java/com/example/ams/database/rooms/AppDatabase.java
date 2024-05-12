package com.example.ams.database.rooms;

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

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ams.database.rooms.dao.AssetDao;
import com.example.ams.database.rooms.dao.MemberAssetDao;
import com.example.ams.database.rooms.dao.RoleDao;
import com.example.ams.database.rooms.dao.StatusDao;
import com.example.ams.database.rooms.dao.TeamDao;
import com.example.ams.database.rooms.dao.TypeDao;
import com.example.ams.database.rooms.dao.UserDao;
import com.example.ams.database.rooms.entity.AssetEntity;
import com.example.ams.database.rooms.entity.AssetStatusMasterEntity;
import com.example.ams.database.rooms.entity.AssetTypeMasterEntity;
import com.example.ams.database.rooms.entity.MemberAssetMappingEntity;
import com.example.ams.database.rooms.entity.RoleMasterEntity;
import com.example.ams.database.rooms.entity.TeamMasterEntity;
import com.example.ams.database.rooms.entity.UserEntity;

import javax.inject.Singleton;

@Singleton
@Database(entities = {UserEntity.class, RoleMasterEntity.class, TeamMasterEntity.class, AssetStatusMasterEntity.class, AssetTypeMasterEntity.class, AssetEntity.class, MemberAssetMappingEntity.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract RoleDao roleDao();

    public abstract TeamDao teamDao();

    public abstract StatusDao statusDao();

    public abstract TypeDao typeDao();

    public abstract AssetDao assetDao();

    public abstract MemberAssetDao memberAssetDao();


}
