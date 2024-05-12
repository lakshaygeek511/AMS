package com.example.ams.views.DashBoard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.example.ams.R;
import com.example.ams.databinding.FragmentHomeBinding;
import com.example.ams.model.AssetCategoryData;
import com.example.ams.ui.AllocationAdapter;
import com.example.ams.ui.AssetCategoryAdapter;
import com.example.ams.ui.MemberAdapter;
import com.example.ams.ui.TeamAdapter;
import com.example.ams.utils.DialogUtils;
import com.example.ams.views.ViewModelMain;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class HomeFragment extends Fragment implements AssetCategoryAdapter.OnItemClickListener, TeamAdapter.OnItemClickListener , MemberAdapter.OnItemClickListener
{
    private RecyclerView recyclerView;
    private FragmentHomeBinding mBinding;
    private List<AssetCategoryData> dataList;
    private ViewModelMain ViewModel;
    private AssetCategoryAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewModel = new ViewModelProvider(this.requireActivity()).get(ViewModelMain.class);

        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = mBinding.assetcategoryListRecyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setAssetMetrics();

        dataList = new ArrayList<>();

        List<String> data = ViewModel.getAssetType();

        for(int i=0; i<data.size(); i++)
        {
            int drawable = getDrawble(ViewModel.getTypeID(data.get(i)));

            dataList.add(new AssetCategoryData( drawable,String.valueOf(ViewModel.getAssetTypeCount(ViewModel.getTypeID(data.get(i)))),data.get(i)));
        }

        // Setting adapter for the recycler view

        adapter = new AssetCategoryAdapter(dataList,this);

        recyclerView.setAdapter(adapter);

    }

    // Setting the Asset Metrics
    public void setAssetMetrics()
    {
        String[] statusItemsList = getResources().getStringArray(R.array.status_array);

        int alloted = ViewModel.getStatusCount(ViewModel.getStatusID(statusItemsList[1]));

        int unalloted = ViewModel.getStatusCount(ViewModel.getStatusID(statusItemsList[2]));

        int damaged = ViewModel.getStatusCount(ViewModel.getStatusID(statusItemsList[3]));

        mBinding.alloted.setText(String.valueOf(alloted));

        mBinding.unalloted.setText(String.valueOf(unalloted));

        mBinding.damaged.setText(String.valueOf(damaged));

        mBinding.total.setText(String.valueOf(alloted + unalloted + damaged ));

    }


    // Setting the drawable of recycler view
    public int getDrawble(int typeid)
    {
        if (typeid == 1)
        {
            return R.drawable.laptop;
        }
        else if (typeid == 2)
        {
            return R.drawable.mouse;
        }
        else if (typeid == 3)
        {
            return R.drawable.desktop;
        }
        else if (typeid == 4)
        {
            return R.drawable.phone;
        }
        else if (typeid == 5)
        {
            return R.drawable.watch;
        }

        return R.drawable.device;
    }


    @Override
    public void onItemClick(String data,int dialog)
    {
        if (dialog == 1)
        {
            DialogUtils.showTeamRecyclerDialog(data,getActivity(),this);
        }
        else if (dialog == 2)
        {
            DialogUtils.showMemberRecyclerDialog(data,getActivity(),this);
        }
        else if (dialog == 3)
        {
            DialogUtils.showAllocationRecyclerDialog(data,getActivity(),this);
        }

    }
}
