package com.example.ams.network;
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

import com.example.ams.database.rooms.dao.AssetAllocationInfo;
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
import com.example.ams.model.FWPResponse;
import com.example.ams.model.LoginData;
import com.example.ams.model.LoginResponse;
import com.example.ams.model.PWDRequestBody;
import com.example.ams.model.RegistrationResponse;
import com.example.ams.model.RequestBodyFWP;
import com.example.ams.model.RequestBodyLogin;
import com.example.ams.model.RequestBodyRegister;
import com.example.ams.model.RequestBodySignIn;
import com.example.ams.views.ViewModelMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppRepo {

    private final APIServices mApiServices;
    private final UserDao mUserDao;
    private final RoleDao mRoleDao;
    private final TeamDao mTeamDao;
    private final StatusDao mStatusDao;
    private final TypeDao mTypeDao;
    private final AssetDao mAssetDao;
    private final MemberAssetDao mMemberAssetDao;


    @Inject
    public AppRepo(UserDao mUserDao,RoleDao mRoleDao,TeamDao mTeamDao,StatusDao mStatusDao,AssetDao mAssetDao,TypeDao mTypeDao,MemberAssetDao mMemberAssetDao,APIServices mApiServices)
    {
        this.mApiServices = mApiServices;
        this.mUserDao = mUserDao;
        this.mRoleDao = mRoleDao;
        this.mTeamDao = mTeamDao;
        this.mStatusDao = mStatusDao;
        this.mTypeDao = mTypeDao;
        this.mAssetDao = mAssetDao;
        this.mMemberAssetDao = mMemberAssetDao;
    }


    public LiveData<List<AssetAllocationInfo>> getAssetAllocationInfo(String assetId) {
        return mMemberAssetDao.getAssetAllocationInfo(assetId);
    }

    public LiveData<List<String>> getAllAssetIds()
    {
        return mAssetDao.getAllAssetIds();
    }

    public void insertUser(UserEntity userEntity) {
        mUserDao.insertUserEntity(userEntity);
    }

    public void updateAssetStatus(String asset, int id)
    {
        mAssetDao.updateAssetStatus(asset, id);
    }

    public void updateAllocationStatus(String asset, boolean status)
    {
        mMemberAssetDao.updateAllocationStatus(asset, status);
    }

    public void insertMemberAsset(MemberAssetMappingEntity memberAssetMapping)
    {
       mMemberAssetDao.insertMemberAssetEntity(memberAssetMapping);
    }

    public String getAllocationAssetID(String type, String model)
    {
        List<AssetDao.AssetIdStatusTuple> assets = mAssetDao.getAssetIdsByTypeAndModel(getTypeID(type),model);

        // Iterate over the list of AssetIdStatusTuple
        for (AssetDao.AssetIdStatusTuple asset : assets)
        {
            if (asset.assetStatus == 2)
            {
                updateAssetStatus(asset.assetId,1);
                return asset.assetId;
            }
        }

        return null;

    }

    public List<ViewModelMain.TeamsCountTuple> getTeamAndEinByAssetType(String type) {
        List<AssetDao.TeamEinTuple> teams = mAssetDao.getTeamAndEinByAssetType(type);
        return calculateTeamsCount(teams);
    }

    public List<AssetDao.TeamMemberTuple> getMembersInTeamWithAssetType(String type,String team)
    {
        return mAssetDao.getMembersInTeamWithAssetType(team,type);
    }

    public List<AssetDao.UserAssetTuple> getAssetsInfoByEin(String type,String team,String ein)
    {
        return mAssetDao.getAssetsInfoByEin(ein,team,type);
    }

    private List<ViewModelMain.TeamsCountTuple> calculateTeamsCount(List<AssetDao.TeamEinTuple> teams) {
        // Map to store the count of EINs for each team
        Map<String, Integer> einCountMap = new HashMap<>();

        // Calculate count of EINs for each team
        for (AssetDao.TeamEinTuple teamEinTuple : teams) {
            String teamName = teamEinTuple.getTeamName();
            // Increment count for the team or initialize with 1 if not present
            einCountMap.put(teamName, einCountMap.getOrDefault(teamName, 0) + 1);
        }

        // Convert the map to a list of TeamsCountTuple
        List<ViewModelMain.TeamsCountTuple> teamsCountTuples = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : einCountMap.entrySet()) {
            String teamName = entry.getKey();
            int einCount = entry.getValue();
            teamsCountTuples.add(new ViewModelMain.TeamsCountTuple(teamName, einCount));
        }

        return teamsCountTuples;
    }

    public List<UserDao.UserEinNameTuple> getMemberFromTeam(int id)
    {
        return mUserDao.getMemberFromTeam(id);
    }

    public List<AssetDao.AssetIdModelTuple> getModelFromType(int id)
    {
        return mAssetDao.getModelFromType(id);
    }

    public void insertAsset(AssetEntity assetEntity) {mAssetDao.insertAssetEntity(assetEntity);}

    public void insertRole(RoleMasterEntity role) {
        mRoleDao.insertRoleEntity(role);
    }

    public List<String> getType()
    {
        return mTypeDao.getType();
    }


    public int typeCount(int typeId)
    {
        List<String> count =  mAssetDao.assetCount(typeId);

        return count.size();
    }

    public int statusCount(int statusId)
    {
        List<Integer> count =  mAssetDao.statusCount(statusId);

        return count.size();
    }



    public int getRoleID(String role) {
        return mRoleDao.getRoleID(role);
    }

    public int getTypeID(String type) {return mTypeDao.getTypeID(type);}

    public int getStatusID(String status) {return mStatusDao.getStatusID(status);}

    public int getTeamID(String team) {
        return mTeamDao.getTeamID(team);
    }

    public void insertTeam(TeamMasterEntity team) {
        mTeamDao.insertTeamEntity(team);
    }


    public void insertStatus(AssetStatusMasterEntity statusMaster) {
        mStatusDao.insertStatusEntity(statusMaster);
    }

    public void insertType(AssetTypeMasterEntity typeMaster)
    {
        mTypeDao.insertTypeEntity(typeMaster);
    }


    public UserEntity loginUser(LoginData loginData)
    {
        return mUserDao.getUserDataByEin(loginData.getEin());
    }

    /*
     * Registers a new user through the remote API.
     *
     * endpoint   The API endpoint for user registration.
     * RequestBody The request body containing user registration information.
     * A Single emitting the response containing registration information.
     */

    public Single<RegistrationResponse> registerUser(String endpoint, RequestBodyRegister RequestBody) {
        return mApiServices.register(endpoint,RequestBody);
    }

    /*
     * Requests a one-time password (OTP) from the remote API.
     * endpoint   The API endpoint for OTP request.
     * RequestBody The request body containing necessary information for OTP generation.
     * A Single emitting the response containing OTP information.
     */
    public Single<FWPResponse> getOTP(String endpoint, RequestBodyFWP RequestBody) {
        return mApiServices.getOTP(endpoint,RequestBody);
    }

    /*
     * Updates user password through the remote API.
     *
     * endpoint   The API endpoint for updating the password.
     * RequestBody The request body containing necessary information for password update.
     * A Single emitting the response containing password update information.
     */
    public Single<FWPResponse> updatePassword(String endpoint, PWDRequestBody RequestBody) {
        return mApiServices.updatePassword(endpoint,RequestBody);
    }

    /*
     * Logs in a user through the remote API.
     *
     * endpoint   The API endpoint for user login.
     * RequestBody The request body containing user login information.
     * A Single emitting the response containing login information.
     */
    public Single<LoginResponse> loginUser(String endpoint, RequestBodyLogin RequestBody) {
        return mApiServices.login(endpoint,RequestBody);
    }


    public Single<LoginResponse> signInUser(String endpoint, RequestBodySignIn RequestBody) {
        return mApiServices.signIn(endpoint,RequestBody);
    }

}
