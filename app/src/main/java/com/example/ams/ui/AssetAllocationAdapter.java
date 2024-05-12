package com.example.ams.ui;

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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ams.R;
import com.example.ams.database.rooms.dao.AssetAllocationInfo;

import java.util.List;

public class AssetAllocationAdapter extends RecyclerView.Adapter<AssetAllocationViewHolder>
{

    private final List<AssetAllocationInfo> dataList;

    // Constructor to initialize the data list
    public AssetAllocationAdapter(List<AssetAllocationInfo> dataList)
    {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AssetAllocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.historyrow, parent, false);

        return new AssetAllocationViewHolder(photoView);
    }

    // Setting up the View Holder Data into Fields
    @Override
    public void onBindViewHolder(@NonNull AssetAllocationViewHolder holder, int position)
    {

        AssetAllocationInfo item = dataList.get(position);

        holder.allocationName.setText(item.getName());
        holder.allocationEin.setText(item.getEin());

        if(item.isAllocation_status())
        {
            holder.stats.setText("Alloted");
            holder.image.setImageResource(R.drawable.alloted);
        }
        else
        {
            holder.stats.setText("Unalloted");
            holder.image.setImageResource(R.drawable.unalloted);
        }

        String[] parts = item.getAllocation_date().split(" ");
        holder.allotment.setText(parts[0]);
        holder.time.setText(parts[1]);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
