package com.example.ams.views;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;

import com.example.ams.database.rooms.dao.AssetAllocationInfo;
import com.example.ams.database.rooms.dao.AssetDao;
import com.example.ams.database.rooms.dao.UserDao;
import com.example.ams.database.rooms.entity.AssetEntity;
import com.example.ams.database.rooms.entity.AssetStatusMasterEntity;
import com.example.ams.database.rooms.entity.AssetTypeMasterEntity;
import com.example.ams.database.rooms.entity.MemberAssetMappingEntity;
import com.example.ams.database.rooms.entity.RoleMasterEntity;
import com.example.ams.database.rooms.entity.TeamMasterEntity;
import com.example.ams.database.rooms.entity.UserEntity;
import com.example.ams.network.AppRepo;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
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

@HiltViewModel
public class ViewModelMain extends AndroidViewModel
{
    private final AppRepo mAppRepo;

    public Context context;

    @Inject
    public ViewModelMain(Application applicationContext, AppRepo mAppRepo) {
        super(applicationContext);
        this.mAppRepo = mAppRepo;
        context = getApplication().getApplicationContext();
    }


    public LiveData<List<AssetAllocationInfo>> getAssetAllocationInfo(String assetId) {
        return mAppRepo.getAssetAllocationInfo(assetId);
    }

    public LiveData<List<String>> getAllAssetIds()
    {
        return mAppRepo.getAllAssetIds();
    }

    public void insertUser(UserEntity user)
    {
        mAppRepo.insertUser(user);
    }

    public void updateStatus(int id,String asset)
    {
        mAppRepo.updateAssetStatus(asset, id);
    }

    public void updateAllocationStatus(boolean status,String asset)
    {
        mAppRepo.updateAllocationStatus(asset, status);
    }

    public void insertAsset(AssetEntity asset)
    {
        mAppRepo.insertAsset(asset);
    }

    public void insertRole(RoleMasterEntity role)
    {
        mAppRepo.insertRole(role);
    }

    public List<String> getAssetType()
    {
        return  mAppRepo.getType();
    }


    public int getAssetTypeCount(int typeid)
    {
        return mAppRepo.typeCount(typeid);
    }

    public int getStatusCount(int statusId)
    {
        return mAppRepo.statusCount(statusId);
    }

    public int getRoleID(String role)
    {
        return mAppRepo.getRoleID(role);
    }

    public int getTeamID(String team)
    {
        return mAppRepo.getTeamID(team);
    }

    public int getTypeID(String type)
    {
        return mAppRepo.getTypeID(type);
    }

    public int getStatusID(String status)
    {
        return mAppRepo.getStatusID(status);
    }

    public void insertTeam(TeamMasterEntity team)
    {
        mAppRepo.insertTeam(team);
    }

    public void insertStatus(AssetStatusMasterEntity statusMaster)
    {
        mAppRepo.insertStatus(statusMaster);
    }

    public void insertType(AssetTypeMasterEntity typeMaster)
    {
        mAppRepo.insertType(typeMaster);
    }

    public void insertMemberAsset(MemberAssetMappingEntity member)
    {
        mAppRepo.insertMemberAsset(member);
    }

    public List<TeamsCountTuple> getTeamAndEinByAssetType(String type)
    {
        return mAppRepo.getTeamAndEinByAssetType(type);
    }

    public List<AssetDao.TeamMemberTuple> getMembersInTeamWithAssetType(String type,String team)
    {
        return mAppRepo.getMembersInTeamWithAssetType(type,team);
    }

    public List<AssetDao.UserAssetTuple> getAssetsInfoByEin(String type,String team,String ein)
    {
        return mAppRepo.getAssetsInfoByEin(type, team, ein);
    }

    public String getAllocationAssetID(String type, String model)
    {
        return mAppRepo.getAllocationAssetID(type, model);
    }

    public List<UserDao.UserEinNameTuple> getMemberFromTeam(String team)
    {
       return mAppRepo.getMemberFromTeam(getTeamID(team));
    }

    public List<AssetDao.AssetIdModelTuple> getModelFromType(String type)
    {
        return mAppRepo.getModelFromType(getTypeID(type));
    }


    public static class TeamsCountTuple
    {
        private String teamName;
        private int einCount;

        public TeamsCountTuple(String teamName, int einCount) {
            this.teamName = teamName;
            this.einCount = einCount;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public int getEinCount() {
            return einCount;
        }

        public void setEinCount(int einCount) {
            this.einCount = einCount;
        }
    }


}