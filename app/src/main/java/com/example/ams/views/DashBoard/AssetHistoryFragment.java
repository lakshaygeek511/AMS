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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.ams.database.rooms.dao.AssetAllocationInfo;
import com.example.ams.databinding.FragmentAssetHistoryBinding;
import com.example.ams.ui.AssetAllocationAdapter;
import com.example.ams.views.ViewModelMain;

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
 */
@AndroidEntryPoint
public class AssetHistoryFragment extends Fragment {

    private FragmentAssetHistoryBinding mBinding;
    private ViewModelMain viewModel;
    private List<AssetAllocationInfo> assetAllocationInfoList;
    private List<String> availableAssetIds;
    private AssetAllocationAdapter adapter;
    private RecyclerView recyclerView;

    public AssetHistoryFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        mBinding = FragmentAssetHistoryBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = mBinding.allocationDetailsListRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ViewModelMain.class);

        viewModel.getAllAssetIds().observe(getViewLifecycleOwner(), assetIds -> {
            if (assetIds != null) {
                availableAssetIds = assetIds;

                String[] stringArray = availableAssetIds.toArray(new String[0]);

                // Create ArrayAdapter using the array of strings
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, stringArray);
                AutoCompleteTextView textView = mBinding.idText;
                textView.setThreshold(1);
                textView.setAdapter(arrayAdapter);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.showDropDown();
                    }
                });

                textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = (String) parent.getItemAtPosition(position);
                        viewModel.getAssetAllocationInfo(selectedItem).observe(getViewLifecycleOwner(), assetAllocationInfoList -> {

                            // Update RecyclerView with asset allocation information
                            adapter = new AssetAllocationAdapter(assetAllocationInfoList);
                            recyclerView.setAdapter(adapter);
                        });
                    }
                });
            }
        });

    }

}