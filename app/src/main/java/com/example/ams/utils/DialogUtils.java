package com.example.ams.utils;

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

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ams.R;
import com.example.ams.database.rooms.dao.AssetAllocationInfo;
import com.example.ams.database.rooms.dao.AssetDao;
import com.example.ams.model.AllocationData;
import com.example.ams.model.MemberData;
import com.example.ams.model.TeamData;
import com.example.ams.ui.AllocationAdapter;
import com.example.ams.ui.MemberAdapter;
import com.example.ams.ui.TeamAdapter;
import com.example.ams.views.DashBoard.AllotAssetFragment;
import com.example.ams.views.DashBoard.AssetHistoryFragment;
import com.example.ams.views.DashBoard.DashboardActivity;
import com.example.ams.views.DashBoard.HomeFragment;
import com.example.ams.views.DashBoard.RegisterAssetFragment;
import com.example.ams.views.DashBoard.RegisterUserFragment;
import com.example.ams.views.ViewModelMain;

import java.util.ArrayList;
import java.util.List;

public class DialogUtils
{
    private static String selectedType;
    private static String selectedTeam;


    public static void showErrorDialog(String errorTitle, String errorMessage, Context context){
        Dialog dialog=new Dialog(context);
        //TO DISABLE DEFAULT TITLE
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TO CANCEL THE DIALOG BY CLICKING ANYWHERE OUTSIDE DIALOG
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_error_dialog_layout);

        /*
         * Setting dialog window size */
        Window window=dialog.getWindow();
        if(window!=null){
            WindowManager.LayoutParams layoutParams=window.getAttributes();
            layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.85); // Set width to 85% of screen width
            window.setAttributes(layoutParams);
            window.setBackgroundDrawableResource(R.drawable.dialog_border);
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }


        TextView errorTitleTextView=dialog.findViewById(R.id.errorTitle);
        TextView errorMessageTextView=dialog.findViewById(R.id.errorMessage);
        Button closeButton =dialog.findViewById(R.id.btnClose);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        errorMessageTextView.setText(errorMessage);
        errorTitleTextView.setText(errorTitle);
        dialog.show();
    }



    public static void showSelectionDialog(DashboardActivity activity) {
        Dialog dialog = new Dialog(activity);
        //TO DISABLE DEFAULT TITLE
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TO CANCEL THE DIALOG BY CLICKING ANYWHERE OUTSIDE DIALOG
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_selection_dialog_layout);

        /*
         * Setting dialog window size */
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.85); // Set width to 85% of screen width
            window.setAttributes(layoutParams);
            window.setBackgroundDrawableResource(R.drawable.dialog_border);
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }

        Button registerUser = dialog.findViewById(R.id.btnregisteruser);
        Button registerAsset = dialog.findViewById(R.id.btnregisterasset);

        registerUser.setOnClickListener(view -> {
            TextView text = activity.findViewById(R.id.toolbarText);
            text.setText(R.string.register_text);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RegisterUserFragment()).commit();
            dialog.dismiss();
        });

        registerAsset.setOnClickListener(view -> {
            TextView text = activity.findViewById(R.id.toolbarText);
            text.setText(R.string.register_asset_text);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RegisterAssetFragment()).commit();
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void showAllocationDialog(DashboardActivity activity) {
        Dialog dialog = new Dialog(activity);
        //TO DISABLE DEFAULT TITLE
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TO CANCEL THE DIALOG BY CLICKING ANYWHERE OUTSIDE DIALOG
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_allocation_dialog_layout);

        /*
         * Setting dialog window size */
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.85); // Set width to 85% of screen width
            window.setAttributes(layoutParams);
            window.setBackgroundDrawableResource(R.drawable.dialog_border);
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }

        Button allotAsset = dialog.findViewById(R.id.btnAllotAsset);
        Button assetHistory = dialog.findViewById(R.id.btnAssetHistory);

        allotAsset.setOnClickListener(view -> {
            TextView text = activity.findViewById(R.id.toolbarText);
            text.setText(R.string.allot_asset_text);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllotAssetFragment()).commit();
            dialog.dismiss();
        });

        assetHistory.setOnClickListener(view -> {
            TextView text = activity.findViewById(R.id.toolbarText);
            text.setText(R.string.asset_history_text);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AssetHistoryFragment()).commit();
            dialog.dismiss();
        });

        dialog.show();
    }


    public static void showDialog(String errorMessage, Context context)
    {
        Dialog dialog=new Dialog(context);
        //TO DISABLE DEFAULT TITLE
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TO CANCEL THE DIALOG BY CLICKING ANYWHERE OUTSIDE DIALOG
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_layout);

        /*
         * Setting dialog window size */
        Window window=dialog.getWindow();
        if(window!=null){
            WindowManager.LayoutParams layoutParams=window.getAttributes();
            layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.85); // Set width to 85% of screen width
            window.setAttributes(layoutParams);
            window.setBackgroundDrawableResource(R.drawable.dialog_border);
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }


        TextView errorMessageTextView=dialog.findViewById(R.id.errorMessage);
        Button closeButton =dialog.findViewById(R.id.btnClose);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        errorMessageTextView.setText(errorMessage);
        dialog.show();
    }


    public static void showTeamRecyclerDialog(String type, FragmentActivity activity, HomeFragment homeFragment)
    {
        Dialog dialog=new Dialog(activity);
        //TO DISABLE DEFAULT TITLE
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TO CANCEL THE DIALOG BY CLICKING ANYWHERE OUTSIDE DIALOG
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_list_dialog_layout);

        /*
         * Setting dialog window size */
        Window window=dialog.getWindow();
        if(window!=null){
            WindowManager.LayoutParams layoutParams=window.getAttributes();
            layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.85); // Set width to 85% of screen width
            window.setAttributes(layoutParams);
            window.setBackgroundDrawableResource(R.drawable.dialog_border);
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }


        RecyclerView recyclerView =  dialog.findViewById(R.id.ListRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        ViewModelMain viewModelMain = new ViewModelProvider(activity).get(ViewModelMain.class);

        selectedType = type;

        List<ViewModelMain.TeamsCountTuple> teams = viewModelMain.getTeamAndEinByAssetType(type);

        List<TeamData> dataList = new ArrayList<>();

        if(teams.isEmpty()) {
            TextView text = dialog.findViewById(R.id.noTeams);

            AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
            fadeInAnimation.setDuration(1000); // Duration in milliseconds
            text.startAnimation(fadeInAnimation);

            text.setVisibility(View.VISIBLE);
        }

        for(int i=0; i<teams.size(); i++)
        {
            dataList.add(new TeamData(String.valueOf(teams.get(i).getEinCount()), teams.get(i).getTeamName()));
        }

        TeamAdapter adapter = new TeamAdapter(dataList,homeFragment,dialog);

        recyclerView.setAdapter(adapter);

        Button closeButton =dialog.findViewById(R.id.btnClose);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public static void showMemberRecyclerDialog(String team, FragmentActivity activity, HomeFragment homeFragment)
    {
        Dialog dialog=new Dialog(activity);
        //TO DISABLE DEFAULT TITLE
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TO CANCEL THE DIALOG BY CLICKING ANYWHERE OUTSIDE DIALOG
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_member_list_dialog_layout);

        /*
         * Setting dialog window size */
        Window window=dialog.getWindow();
        if(window!=null){
            WindowManager.LayoutParams layoutParams=window.getAttributes();
            layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.85); // Set width to 85% of screen width
            window.setAttributes(layoutParams);
            window.setBackgroundDrawableResource(R.drawable.dialog_border);
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }

        selectedTeam = team;

        RecyclerView recyclerView =  dialog.findViewById(R.id.MemListRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        ViewModelMain viewModelMain = new ViewModelProvider(activity).get(ViewModelMain.class);

        List<AssetDao.TeamMemberTuple> memberTupleList = viewModelMain.getMembersInTeamWithAssetType(selectedType,team);

        List<MemberData> dataList = new ArrayList<>();

        for(int i=0; i<memberTupleList.size(); i++)
        {
            dataList.add(new MemberData(memberTupleList.get(i).getName(),memberTupleList.get(i).getEin()));
        }

        MemberAdapter adapter = new MemberAdapter(dataList,homeFragment,dialog);

        recyclerView.setAdapter(adapter);

        Button closeButton =dialog.findViewById(R.id.btnClose);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void showAllocationRecyclerDialog(String member, FragmentActivity activity, HomeFragment homeFragment)
    {
        Dialog dialog=new Dialog(activity);
        //TO DISABLE DEFAULT TITLE
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TO CANCEL THE DIALOG BY CLICKING ANYWHERE OUTSIDE DIALOG
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_asset_allot_dialog_layout);

        /*
         * Setting dialog window size */
        Window window=dialog.getWindow();
        if(window!=null){
            WindowManager.LayoutParams layoutParams=window.getAttributes();
            layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95); // Set width to 85% of screen width
            window.setAttributes(layoutParams);
            window.setBackgroundDrawableResource(R.drawable.dialog_border);
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }

        RecyclerView recyclerView =  dialog.findViewById(R.id.AllotListRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        ViewModelMain viewModelMain = new ViewModelProvider(activity).get(ViewModelMain.class);

        List<AssetDao.UserAssetTuple> assetTupleList = viewModelMain.getAssetsInfoByEin(selectedType,selectedTeam,member);

        List<AllocationData> dataList = new ArrayList<>();

        for(int i=0; i<assetTupleList.size(); i++)
        {
            dataList.add(new AllocationData(assetTupleList.get(i).getAssetId(),assetTupleList.get(i).getStatusId(),assetTupleList.get(i).getAssetName(),assetTupleList.get(i).getAssetType(),assetTupleList.get(i).getAssetModel(),assetTupleList.get(i).getSerialNo(),assetTupleList.get(i).getPurchaseDate(),assetTupleList.get(i).getWarranty()));
        }

        AllocationAdapter adapter = new AllocationAdapter(dataList,activity,dialog);

        recyclerView.setAdapter(adapter);


        Button closeButton =dialog.findViewById(R.id.btnClose);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });

        dialog.show();
    }



}
