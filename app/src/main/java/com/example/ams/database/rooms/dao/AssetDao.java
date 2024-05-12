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
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import com.example.ams.database.rooms.entity.AssetEntity;

@Dao
public interface AssetDao
{
    //Insert asset entity Data into Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssetEntity(AssetEntity assetEntity);

    @Query("Select clm_asset_id from tbl_asset where clm_type_id = :id")
    List<String> assetCount(int id);

    @Query("SELECT clm_asset_id FROM tbl_asset")
    LiveData<List<String>> getAllAssetIds();

    @Query("Select clm_asset_status from tbl_asset where clm_asset_status = :id")
    List<Integer> statusCount(int id);


    @Query("Select clm_asset_model , clm_asset_id from tbl_asset where clm_type_id = :id")
    List<AssetIdModelTuple> getModelFromType(int id);


    @Query("SELECT clm_asset_id , clm_asset_status FROM tbl_asset WHERE clm_type_id = :typeId AND clm_asset_model = :assetModel")
    List<AssetIdStatusTuple> getAssetIdsByTypeAndModel(int typeId, String assetModel);


    // Method to update asset status based on asset_id
    @Query("UPDATE tbl_asset SET clm_asset_status = :newStatus WHERE clm_asset_id = :assetId")
    void updateAssetStatus(String assetId, int newStatus);

    @Query("SELECT DISTINCT " +
            "tbl_team_master.clm_team AS clm_team, " +
            "tbl_user.clm_ein AS clm_ein " +
            "FROM " +
            "tbl_member_asset_mapping " +
            "JOIN tbl_user ON tbl_member_asset_mapping.clm_ein = tbl_user.clm_ein " +
            "JOIN tbl_team_master ON tbl_user.clm_teamId = tbl_team_master.clm_teamId " +
            "JOIN tbl_asset ON tbl_member_asset_mapping.clm_asset_id = tbl_asset.clm_asset_id " +
            "JOIN tbl_asset_type_master ON tbl_asset.clm_type_id = tbl_asset_type_master.clm_typeId " +
            "WHERE tbl_asset_type_master.clm_assetType = :providedAssetType")
    List<TeamEinTuple> getTeamAndEinByAssetType(String providedAssetType);


    @Query("SELECT DISTINCT " +
            "tbl_user.clm_name AS name, " +
            "tbl_user.clm_ein AS ein " +
            "FROM " +
            "tbl_member_asset_mapping " +
            "JOIN tbl_user ON tbl_member_asset_mapping.clm_ein = tbl_user.clm_ein " +
            "JOIN tbl_team_master ON tbl_user.clm_teamId = tbl_team_master.clm_teamId " +
            "JOIN tbl_asset ON tbl_member_asset_mapping.clm_asset_id = tbl_asset.clm_asset_id " +
            "JOIN tbl_asset_type_master ON tbl_asset.clm_type_id = tbl_asset_type_master.clm_typeId " +
            "WHERE tbl_team_master.clm_team = :providedTeamName AND tbl_asset_type_master.clm_assetType = :providedAssetType")
    List<TeamMemberTuple> getMembersInTeamWithAssetType(String providedTeamName, String providedAssetType);


    @Query("SELECT DISTINCT " +
            "tbl_asset.clm_asset_id AS assetId, " +
            "tbl_asset.clm_asset_name AS assetName, " +
            "tbl_asset.clm_asset_model AS assetModel, " +
            "tbl_asset_type_master.clm_assetType AS assetType, " +
            "tbl_asset.clm_serial_no AS serialNo, " +
            "tbl_asset.clm_purchase_date AS purchaseDate, " +
            "tbl_asset.clm_warranty AS warranty, " +
            "tbl_asset.clm_asset_status AS status " +
            "FROM " +
            "tbl_member_asset_mapping " +
            "JOIN tbl_user ON tbl_member_asset_mapping.clm_ein = tbl_user.clm_ein " +
            "JOIN tbl_team_master ON tbl_user.clm_teamId = tbl_team_master.clm_teamId " +
            "JOIN tbl_asset ON tbl_member_asset_mapping.clm_asset_id = tbl_asset.clm_asset_id " +
            "JOIN tbl_asset_type_master ON tbl_asset.clm_type_id = tbl_asset_type_master.clm_typeId " +
            "WHERE tbl_user.clm_ein = :providedEin AND tbl_team_master.clm_team = :providedTeamName AND tbl_asset_type_master.clm_assetType = :providedAssetType")
    List<UserAssetTuple> getAssetsInfoByEin(String providedEin, String providedTeamName, String providedAssetType);


    class AssetIdStatusTuple
    {
        @NonNull
        @ColumnInfo(name = "clm_asset_id")
        public String assetId;

        @ColumnInfo(name = "clm_asset_status")
        public int assetStatus;
    }

    class AssetIdModelTuple
    {
        @NonNull
        @ColumnInfo(name = "clm_asset_id")
        public String assetId;

        @ColumnInfo(name = "clm_asset_model")
        public String assetModel;

        public AssetIdModelTuple(@NonNull String assetId, String assetModel) {
            this.assetId = assetId;
            this.assetModel = assetModel;
        }
    }


    class TeamEinTuple
    {
        @NonNull
        @ColumnInfo(name = "clm_team")
        private String teamName;

        @NonNull
        @ColumnInfo(name = "clm_ein")
        private String ein;

        // Constructor
        public TeamEinTuple(String teamName, String ein)
        {
            this.teamName = teamName;
            this.ein = ein;
        }

        // Getters
        public String getTeamName() {
            return teamName;
        }

        public String getEin() {
            return ein;
        }
    }

    public class TeamMemberTuple {
        @NonNull
        @ColumnInfo(name = "name")
        private String name;

        @NonNull
        @ColumnInfo(name = "ein")
        private String ein;

        // Constructor
        public TeamMemberTuple(String name, String ein) {
            this.name = name;
            this.ein = ein;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getEin() {
            return ein;
        }
    }

    public class UserAssetTuple {
        @NonNull
        @ColumnInfo(name = "assetId")
        private String assetId;

        @ColumnInfo(name = "assetName")
        private String assetName;

        @ColumnInfo(name = "assetModel")
        private String assetModel;
        @ColumnInfo(name = "assetType")
        private String assetType;

        @ColumnInfo(name = "serialNo")
        private String serialNo;

        @ColumnInfo(name = "purchaseDate")
        private String purchaseDate;

        @ColumnInfo(name = "warranty")
        private String warranty;

        @ColumnInfo(name = "status")
        private int status;

        // Constructor
        public UserAssetTuple(String assetId, String assetName, String assetType,String assetModel,String serialNo, String purchaseDate, String warranty,int status) {
            this.assetId = assetId;
            this.assetName = assetName;
            this.assetType = assetType;
            this.assetModel = assetModel;
            this.serialNo = serialNo;
            this.purchaseDate = purchaseDate;
            this.warranty = warranty;
            this.status = status;
        }


        public int getStatusId() {
            return status;
        }

        public void setStatusId(int status) {
            this.status = status;
        }

        // Getters
        public String getAssetId() {
            return assetId;
        }

        public String getAssetName() {
            return assetName;
        }

        public String getAssetType() {
            return assetType;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public String getWarranty() {
            return warranty;
        }

        public String getAssetModel() {
            return assetModel;
        }

        public void setAssetModel(String assetModel) {
            this.assetModel = assetModel;
        }
    }



}
