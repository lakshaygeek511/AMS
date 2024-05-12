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

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ams.R;

public class AssetCategoryViewHolder extends RecyclerView.ViewHolder
{
    ImageView categoryImage;
    TextView totalAssets;
    TextView assetType;
    View view;

    AssetCategoryViewHolder(View itemView)
    {
        super(itemView);

        // Initializing UI fields

        categoryImage = (ImageView) itemView.findViewById(R.id.listImage);

        totalAssets = (TextView)itemView.findViewById(R.id.totalAssets);

        assetType = (TextView)itemView.findViewById(R.id.assetType);

        view  = itemView;
    }
}