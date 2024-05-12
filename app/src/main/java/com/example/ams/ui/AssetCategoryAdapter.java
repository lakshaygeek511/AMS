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
import com.example.ams.model.AssetCategoryData;

import java.util.List;


public class AssetCategoryAdapter extends RecyclerView.Adapter<AssetCategoryViewHolder>
{

    private final List<AssetCategoryData> dataList;

    private final OnItemClickListener mListener;

    // Constructor to initialize the data list
    public AssetCategoryAdapter(List<AssetCategoryData> dataList , OnItemClickListener listener)
    {
        this.dataList = dataList;
        this.mListener = listener;
    }

    // ViewHolder class and other methods remain the same

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }

    @NonNull
    @Override
    public AssetCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.projectrow, parent, false);

        return new AssetCategoryViewHolder(photoView);

    }

    // Setting up the View Holder Data into Fields
    @Override
    public void onBindViewHolder(AssetCategoryViewHolder holder, int position)
    {
        AssetCategoryData item = dataList.get(position);

        holder.categoryImage.setImageResource(item.getImage());

        holder.totalAssets.setText(item.getTotal());

        holder.assetType.setText(item.getType());

        // Set OnClickListener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the clicked item to the listener
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(dataList.get(adapterPosition).getType(),1);
                }
            }
        });

    }

    public interface OnItemClickListener
    {
        void onItemClick(String type ,int dialogType);
    }
}
