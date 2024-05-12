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
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ams.R;

public class MemberViewHolder extends RecyclerView.ViewHolder
{
    TextView memberName;
    TextView memberEin;
    View view;

    MemberViewHolder(View itemView)
    {
        super(itemView);

        // Initializing UI fields

        memberName = (TextView)itemView.findViewById(R.id.name);

        memberEin = (TextView)itemView.findViewById(R.id.ein);

        view  = itemView;
    }

}
