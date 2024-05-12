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

public class AssetAllocationViewHolder extends RecyclerView.ViewHolder
{
    ImageView image;
    TextView allocationName;
    TextView allocationEin;
    TextView stats;
    TextView allotment;
    TextView time;
    View view;

    AssetAllocationViewHolder(View itemView)
    {
        super(itemView);

        // Initializing UI fields

        allotment = (TextView) itemView.findViewById(R.id.allotment);

        time = (TextView) itemView.findViewById(R.id.time);

        allocationName =  (TextView) itemView.findViewById(R.id.allocationName);

        allocationEin =  (TextView) itemView.findViewById(R.id.allocationEin);

        stats = (TextView) itemView.findViewById(R.id.stats);

        image = (ImageView) itemView.findViewById(R.id.newImage);

        view  = itemView;
    }

}
