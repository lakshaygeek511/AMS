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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ams.R;
import com.example.ams.model.AllocationData;
import com.example.ams.views.ViewModelMain;

import java.util.List;

public class AllocationAdapter extends RecyclerView.Adapter<AllocationViewHolder>
{
    private final List<AllocationData> dataList;
    private final ViewModelMain ViewModel;
    private final Dialog allocationDialog;

    // Constructor to initialize the data list
    public AllocationAdapter(List<AllocationData> dataList, FragmentActivity fragmentActivity, Dialog allocationDialog)
    {
        this.dataList = dataList;
        ViewModel = new ViewModelProvider(fragmentActivity).get(ViewModelMain.class);
        this.allocationDialog = allocationDialog;
    }


    @NonNull
    @Override
    public AllocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.allocationrow, parent, false);

        return new AllocationViewHolder(photoView);

    }

    // Setting up the View Holder Data into Fields
    @Override
    public void onBindViewHolder(AllocationViewHolder holder, int position)
    {
        AllocationData item = dataList.get(position);

        holder.assetId.setText(item.getAsset_id());
        holder.assetName.setText(item.getAsset_name());
        holder.assetType.setText(item.getAsset_type());
        holder.assetModel.setText(item.getAsset_model());
        holder.purchaseDate.setText(item.getPurchase_date());
        holder.serialNo.setText(item.getSerial_no());
        holder.warranty.setText(item.getWarranty());

        if(item.getStatus() == 1)
        {
            holder.statusImage.setImageResource(R.drawable.alloted);
            holder.statusText.setText(R.string.alloted);
        }

        if(item.getStatus() == 2)
        {
            holder.statusImage.setImageResource(R.drawable.unalloted);
            holder.statusText.setText(R.string.unalloted);
        }

        if(item.getStatus() == 3)
        {
            holder.statusImage.setImageResource(R.drawable.damaged);
            holder.statusText.setText(R.string.damaged);
        }

        holder.status.setOnClickListener(view ->
        {
            holder.unallotedstatus.setVisibility(View.VISIBLE);
            holder.damagedstatus.setVisibility(View.VISIBLE);

        });


        holder.unallotedstatus.setOnClickListener(view ->
        {
            ViewModel.updateStatus(2, item.getAsset_id());
            ViewModel.updateAllocationStatus(false,item.getAsset_id());
            holder.unallotedstatus.setVisibility(View.INVISIBLE);
            holder.damagedstatus.setVisibility(View.INVISIBLE);
            holder.statusImage.setImageResource(R.drawable.unalloted);
            holder.statusText.setText(R.string.unalloted);
        });

        holder.damagedstatus.setOnClickListener(view ->
        {
            ViewModel.updateStatus(3, item.getAsset_id());
            holder.unallotedstatus.setVisibility(View.INVISIBLE);
            holder.damagedstatus.setVisibility(View.INVISIBLE);
            holder.statusImage.setImageResource(R.drawable.damaged);
            holder.statusText.setText(R.string.damaged);
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
