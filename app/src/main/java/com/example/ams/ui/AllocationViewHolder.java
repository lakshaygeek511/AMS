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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ams.R;

public class AllocationViewHolder extends RecyclerView.ViewHolder
{
    ImageView statusImage;
    TextView statusText;
    TextView assetName;
    TextView assetType;
    TextView assetModel;
    TextView serialNo;
    TextView purchaseDate;
    TextView warranty;
    TextView assetId;
    Button status;
    Button unallotedstatus;
    Button damagedstatus;
    View view;
    AllocationViewHolder(View itemView)
    {
        super(itemView);

        // Initializing UI fields

        statusImage = (ImageView) itemView.findViewById(R.id.statusImage);

        statusText = (TextView) itemView.findViewById(R.id.statusText);

        assetId = (TextView) itemView.findViewById(R.id.id_pop);

        assetName = (TextView)itemView.findViewById(R.id.assetName);

        assetType = (TextView)itemView.findViewById(R.id.popassetType);

        assetModel = (TextView)itemView.findViewById(R.id.assetModel);

        serialNo  = (TextView) itemView.findViewById(R.id.serialNo);

        purchaseDate = (TextView) itemView.findViewById(R.id.purchaseDate);

        warranty = (TextView) itemView.findViewById(R.id.warranty_pop);

        status = (Button) itemView.findViewById(R.id.btnStatus);

        unallotedstatus = (Button) itemView.findViewById(R.id.btnUnAlloted);

        damagedstatus = (Button) itemView.findViewById(R.id.btnDamaged);

        view  = itemView;

    }
}
