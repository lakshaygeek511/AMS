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

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ams.R;
import com.example.ams.model.TeamData;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamViewHolder>
{
    private final List<TeamData> dataList;


    private final OnItemClickListener mListener;

    private final Dialog teamDialog;

    // Constructor to initialize the data list
    public TeamAdapter(List<TeamData> dataList , OnItemClickListener listener, Dialog teamDialog)
    {
        this.dataList = dataList;
        this.mListener = listener;
        this.teamDialog = teamDialog;
    }


    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.teamrow, parent, false);

        return new TeamViewHolder(photoView);

    }

    // Setting up the View Holder Data into Fields
    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position)
    {
        TeamData item = dataList.get(position);

        holder.teamName.setText(item.getTeam());

        holder.totalMembers.setText(item.getTotal());

        // Set OnClickListener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION)
                {
                    mListener.onItemClick(dataList.get(adapterPosition).getTeam(),2);
                    teamDialog.dismiss();
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public interface OnItemClickListener
    {
        void onItemClick(String type, int dialogType);
    }

}
