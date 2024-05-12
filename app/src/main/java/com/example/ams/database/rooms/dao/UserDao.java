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

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ams.database.rooms.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    //Insert user entity Data into Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserEntity(UserEntity userEntity);

    @Query("Select * from tbl_user where clm_ein = :ein ")
    UserEntity getUserDataByEin(String ein);

    @Query("Select clm_ein , clm_name from tbl_user where clm_teamId = :id")
    List<UserEinNameTuple> getMemberFromTeam(int id);

    class UserEinNameTuple
    {
        @NonNull
        @ColumnInfo(name = "clm_ein")
        public String ein;

        @ColumnInfo(name = "clm_name")
        public String name;

        public UserEinNameTuple(@NonNull String ein, String name) {
            this.ein = ein;
            this.name = name;
        }
    }


}
