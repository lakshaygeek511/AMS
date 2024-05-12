package com.example.ams.ui;

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

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ams.R;

public class TeamViewHolder extends RecyclerView.ViewHolder
{
    TextView totalMembers;
    TextView teamName;
    View view;

    TeamViewHolder(View itemView)
    {
        super(itemView);

        // Initializing UI fields

        totalMembers = (TextView)itemView.findViewById(R.id.totalMembers);

        teamName = (TextView)itemView.findViewById(R.id.teamName);

        view  = itemView;
    }
}