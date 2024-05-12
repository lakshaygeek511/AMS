package com.example.ams.views.DashBoard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ams.R;
import com.example.ams.database.rooms.entity.AssetEntity;
import com.example.ams.databinding.FragmentRegisterAssetBinding;
import com.example.ams.utils.Constants;
import com.example.ams.ui.CustomSpinnerAdapter;
import com.example.ams.utils.DialogUtils;
import com.example.ams.utils.Utils;
import com.example.ams.views.ViewModelMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
public class RegisterAssetFragment extends Fragment {

    private FragmentRegisterAssetBinding mBinding;
    private ViewModelMain ViewModel;

    public RegisterAssetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewModel = new ViewModelProvider(this.requireActivity()).get(ViewModelMain.class);

        // Inflate the layout for this fragment
        mBinding = FragmentRegisterAssetBinding.inflate(inflater, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // Setting up spinners & listeners

        setupSpinner(mBinding.typeSpinner, R.array.asset_type_array);
        setupSpinner(mBinding.locationSpinner, R.array.location_array);
        listener();

        mBinding.purchase.setOnClickListener(this::showDatePickerDialog);

    }

    // Set up click listeners for buttons
    private void listener() {

        mBinding.button.setOnClickListener(view -> {
            String name = mBinding.name.getText().toString();
            String model = mBinding.model.getText().toString();
            String serial = mBinding.serial.getText().toString().trim();
            String date = mBinding.purchase.getText().toString().trim();
            String warranty = mBinding.warranty.getText().toString().trim();
            String period = mBinding.periodSpinner.getSelectedItem().toString();
            String cost = mBinding.cost.getText().toString().trim();
            String selectedType = mBinding.typeSpinner.getSelectedItem().toString();
            String selectedLocation = mBinding.locationSpinner.getSelectedItem().toString();

            // Validate input and register asset
            if (isValidInput(name,model,serial,date,warranty,cost,selectedType,selectedLocation))
            {
                String id = Utils.generateAssetId(String.valueOf(selectedLocation.charAt(0)));
                int status = ViewModel.getStatusID(Constants.UNALLOTED);

                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.USERID, Context.MODE_PRIVATE);
                String userid  = sharedPreferences.getString(Constants.ID,"");

                AssetEntity entity = new AssetEntity(id,name,ViewModel.getTypeID(selectedType),model,serial,date,warranty + " " + period ,cost,selectedLocation,status,Utils.getCurrentDateTime(),userid,Utils.getCurrentDateTime(),userid,false);
                ViewModel.insertAsset(entity);
                DialogUtils.showDialog(getString(R.string.signup_succesful)+Constants.ASSET_ID+id,this.requireActivity());

                resetFields();
            }
            else
            {
                Log.println(Log.INFO, Constants.VALIDATION, String.valueOf(R.string.log_validation));
            }

        });

    }

    // Resets the fields
    private void resetFields() {
        mBinding.name.setText("");
        mBinding.model.setText("");
        mBinding.serial.setText("");
        mBinding.purchase.setText("");
        mBinding.warranty.setText("");
        mBinding.cost.setText("");

        mBinding.typeSpinner.setSelection(0);
        mBinding.locationSpinner.setSelection(0);
    }

    public void showDatePickerDialog(View view)
    {
        // Get the currently selected date from the purchase TextView
        String purchaseDate = mBinding.purchase.getText().toString();

        // Parse the currently selected date to get year, month, and day
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        if (!purchaseDate.isEmpty()) {
            // Parse the currently selected date
            String[] parts = purchaseDate.split("/");
            if (parts.length == 3) {
                dayOfMonth = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]) - 1; // Month is zero-based
                year = Integer.parseInt(parts[2]);
            }
        }

        // Create DatePickerDialog instance with the parsed date or current date if not available
        DatePickerDialog datePickerDialog = new DatePickerDialog(this.requireActivity(), (view1, selectedYear, selectedMonth, selectedDay) -> {
            // Update the TextView with the selected date
            String selectedDate = selectedDay + "/" + (selectedMonth + 1)  + "/" + selectedYear;
            mBinding.purchase.setText(selectedDate);
        }, year, month, dayOfMonth);

        // Set the minimum date to the current date to disable dates in the future
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        // Show the dialog
        datePickerDialog.show();

    }


    private boolean isValidInput(String name, String model, String serial, String date, String warranty, String cost ,String type, String location)
    {

        // Check if the name is empty
        if (name.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_asset_text),this.requireActivity());
            return false;
        }

        // Check if the name is valid
        if (!name.matches(Constants.NAME_REGEX))
        {
            DialogUtils.showDialog(getString(R.string.valid_name_text),this.requireActivity());
            return false;
        }

        // Check if the selected type is the default prompt
        if (type.equals(getString(R.string.type_prompt))) {
            DialogUtils.showDialog(getString(R.string.type_text),this.requireActivity());
            return false;
        }

        // Check if the model is empty
        if (model.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_model_text),this.requireActivity());
            return false;
        }

        // Check if the model is valid
        if (!model.matches(Constants.MODEL_REGEX))
        {
            DialogUtils.showDialog(getString(R.string.valid_model_text),this.requireActivity());
            return false;
        }

        // Check if the serial number is empty
        if (serial.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_serial_text),this.requireActivity());
            return false;
        }

        // Check if the serial number has digits
        if (!TextUtils.isDigitsOnly(serial) || serial.length() != 10)  {
            DialogUtils.showDialog(getString(R.string.invalid_serial_text),this.requireActivity());
            return false;
        }

        // Check if the date is empty
        if (date.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_date_text),this.requireActivity());
            return false;
        }

        // Check if the date is in the correct format (dd/MM/yyyy)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setLenient(false); // To strict parse the date format

        try {
            Date inputDate = sdf.parse(date);

            // Check if the input date is not greater than the current date
            Date currentDate = new Date();
            if(inputDate!= null) {
                if (inputDate.after(currentDate)) {
                    DialogUtils.showDialog(getString(R.string.future_date_text), this.requireActivity());
                    return false;
                }
            }
            else
            {
                DialogUtils.showDialog(getString(R.string.empty_date_text), this.requireActivity());
                return false;
            }

        } catch (ParseException e) {
            // Date format is incorrect
            DialogUtils.showDialog(getString(R.string.invalid_date_format_text), this.requireActivity());
            return false;
        }


        // Check if the warranty is empty
        if (warranty.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_warranty_text),this.requireActivity());
            return false;
        }

        // Check if the warranty has digits
        if (!TextUtils.isDigitsOnly(warranty)) {
            DialogUtils.showDialog(getString(R.string.invalid_warranty_text),this.requireActivity());
            return false;
        }

        // Check if the cost is empty
        if (cost.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_cost_text),this.requireActivity());
            return false;
        }

        // Check if the cost has digits
        if (!TextUtils.isDigitsOnly(cost)) {
            DialogUtils.showDialog(getString(R.string.invalid_cost_text),this.requireActivity());
            return false;
        }

        // Check if the selected location is the default prompt
        if (location.equals(getString(R.string.location_prompt))) {
            Toast.makeText(this.requireActivity(), R.string.location_text, Toast.LENGTH_SHORT).show();
            return false;
        }

        // All validation checks passed
        return true;
    }

    private void setupSpinner(Spinner spinner, @ArrayRes int arrayRes)
    {
        List<String> itemsList = Arrays.asList(getResources().getStringArray(arrayRes));
        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, itemsList );
        spinner.setAdapter(spinnerAdapter);
    }

}