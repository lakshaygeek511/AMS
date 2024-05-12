package com.example.ams.views.DashBoard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ams.R;
import com.example.ams.database.rooms.dao.AssetDao;
import com.example.ams.database.rooms.dao.UserDao;
import com.example.ams.database.rooms.entity.MemberAssetMappingEntity;
import com.example.ams.databinding.FragmentAllotAssetBinding;
import com.example.ams.utils.Constants;
import com.example.ams.ui.CustomSpinnerAdapter;
import com.example.ams.utils.DialogUtils;
import com.example.ams.utils.Utils;
import com.example.ams.views.ViewModelMain;
import com.google.i18n.phonenumbers.NumberParseException;

import java.util.Arrays;
import java.util.List;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class AllotAssetFragment extends Fragment
{

    private FragmentAllotAssetBinding mBinding;

    final String[] selectedMember = new String[1];
    final String[] selectedModel = new String[1];
    private ViewModelMain ViewModel;

    public AllotAssetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        ViewModel = new ViewModelProvider(this.requireActivity()).get(ViewModelMain.class);

        // Inflate the layout for this fragment
        mBinding = FragmentAllotAssetBinding.inflate(inflater, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // Setting spinner's & listener's

        setupResSpinner(mBinding.typeSpinner, R.array.asset_type_array);
        setupResSpinner(mBinding.teamSpinner, R.array.team_array);

        listeners();

    }

    private void listeners()
    {
        mBinding.teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<UserDao.UserEinNameTuple> members = ViewModel.getMemberFromTeam(mBinding.teamSpinner.getSelectedItem().toString());

                // Calculate the size of the array
                int size = members.size();

                // Initialize the string array
                String[] stringArray = new String[size];

                // Iterate through the list and populate the string array
                for (int i = 0; i < size; i++) {
                    UserDao.UserEinNameTuple tuple = members.get(i);
                    stringArray[i] = tuple.ein + " - " + tuple.name;
                }

                // Create ArrayAdapter using the array of strings
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, stringArray);
                AutoCompleteTextView textView = mBinding.memberText;
                textView.setThreshold(1);
                textView.setAdapter(arrayAdapter);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.showDropDown();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection if necessary
            }
        });



        mBinding.typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<AssetDao.AssetIdModelTuple> models = ViewModel.getModelFromType(mBinding.typeSpinner.getSelectedItem().toString());

                // Calculate the size of the array
                int size = models.size();

                // Initialize the string array
                String[] stringArray = new String[size];

                // Iterate through the list and populate the string array
                for (int i = 0; i < size; i++) {
                    AssetDao.AssetIdModelTuple tuple = models.get(i);
                    stringArray[i] = tuple.assetId + " - " + tuple.assetModel;
                }

                // Create ArrayAdapter using the array of strings
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, stringArray);
                AutoCompleteTextView textView = mBinding.modelText;
                textView.setThreshold(1);
                textView.setAdapter(arrayAdapter);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.showDropDown();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection if necessary
            }
        });

        mBinding.memberText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] parts = selectedItem.split(" - ");
                selectedMember[0] =  parts[0].trim();
            }
        });

        mBinding.modelText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] parts = selectedItem.split(" - ");
                selectedModel[0] = parts[1].trim();
            }
        });

        mBinding.button.setOnClickListener(view->
        {
            String selectedTeam = mBinding.teamSpinner.getSelectedItem().toString();
            String selectedType = mBinding.typeSpinner.getSelectedItem().toString();

            // Validate input and allot asset
            try {
                if (isValidInput(selectedTeam, selectedType, selectedMember[0], selectedModel[0]))
                {

                    String id = ViewModel.getAllocationAssetID(selectedType,selectedModel[0]);

                    if(id!=null)
                    {
                        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.USERID, Context.MODE_PRIVATE);
                        String userid  = sharedPreferences.getString(Constants.ID,"");

                        MemberAssetMappingEntity entity = new MemberAssetMappingEntity(id, selectedMember[0],true,Utils.getCurrentDateTime(),userid,Utils.getCurrentDateTime(),userid,false);

                        ViewModel.insertMemberAsset(entity);

                        DialogUtils.showDialog(getString(R.string.allot_succesful),this.requireActivity());

                        resetFields();
                    }
                    else
                    {
                        DialogUtils.showDialog(getString(R.string.asset_unavailable),this.requireActivity());
                    }

                }
                else
                {
                    Log.println(Log.INFO, Constants.VALIDATION, String.valueOf(R.string.log_validation));
                }
            }
            catch (NumberParseException e)
            {
                throw new RuntimeException(e);
            }

        });



    }


    // Resetting Asset Allocation Fields
    private void resetFields() {

        mBinding.typeSpinner.setSelection(0);
        mBinding.teamSpinner.setSelection(0);
        mBinding.modelText.setText("");
        mBinding.memberText.setText("");

    }

    // Setting Spinner Dropdown List
    private void setupResSpinner(Spinner spinner, @ArrayRes int arrayRes)
    {
        List<String> itemsList = Arrays.asList(getResources().getStringArray(arrayRes));
        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, itemsList );
        spinner.setAdapter(spinnerAdapter);
    }


    // Validating Input from
    private boolean isValidInput(String team, String type, String member, String model) throws NumberParseException
    {
        if (team.equals(getString(R.string.team_prompt))) {
            DialogUtils.showDialog(getString(R.string.team_text),this.requireActivity());
            return false;
        }

        if(member == null)
        {
            DialogUtils.showDialog(getString(R.string.search_member_text),this.requireActivity());
            return false;
        }

        if (type.equals(getString(R.string.type_prompt))) {
            DialogUtils.showDialog(getString(R.string.type_text),this.requireActivity());
            return false;
        }

        if(model == null)
        {
            DialogUtils.showDialog(getString(R.string.search_model_text),this.requireActivity());
            return false;
        }

        // All validation checks passed
        return true;

    }

}
