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
import com.example.ams.model.MemberData;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberViewHolder>
{
    private final List<MemberData> dataList;
    private final Dialog memberDialog;
    private final OnItemClickListener mListener;

    // Constructor to initialize the data list
    public MemberAdapter(List<MemberData> dataList , OnItemClickListener listener,Dialog memberDialog)
    {
        this.dataList = dataList;
        this.mListener = listener;
        this.memberDialog = memberDialog;
    }


    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.memberow, parent, false);

        return new MemberViewHolder(photoView);

    }

    // Setting up the View Holder Data into Fields
    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position)
    {
        MemberData item = dataList.get(position);

        holder.memberEin.setText(item.getEin());

        holder.memberName.setText(item.getName());

        // Set OnClickListener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION)
                {
                    mListener.onItemClick(dataList.get(adapterPosition).getEin(),3);
                    memberDialog.dismiss();

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
