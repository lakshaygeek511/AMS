package com.example.ams.views.DashBoard;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.ams.R;
import com.example.ams.database.rooms.entity.UserEntity;
import com.example.ams.ui.CustomSpinnerAdapter;
import com.example.ams.utils.DialogUtils;
import com.example.ams.utils.Utils;
import com.example.ams.databinding.FragmentRegisterUserBinding;
import com.example.ams.utils.Constants;
import com.example.ams.views.ViewModelMain;

import java.util.Arrays;
import java.util.List;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

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
public class RegisterUserFragment extends Fragment {

    private FragmentRegisterUserBinding mBinding;
    private ViewModelMain ViewModel;
    PhoneNumberUtil phoneNumberUtil;
    private Activity mAactivity;

    public RegisterUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ViewModel = new ViewModelProvider(this.requireActivity()).get(ViewModelMain.class);

        // Inflate the layout for this fragment
        mBinding = FragmentRegisterUserBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mAactivity = getActivity();
        }

        // Setting up spinners & listeners

        setupSpinner(mBinding.roleSpinner, R.array.role_array);
        setupSpinner(mBinding.teamSpinner, R.array.team_array);
        listener();

       phoneNumberUtil = PhoneNumberUtil.getInstance();

    }

    // Setting Dropdown data in Spinner
    private void setupSpinner(Spinner spinner, @ArrayRes int arrayRes)
    {
        List<String> itemsList = Arrays.asList(getResources().getStringArray(arrayRes));
        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, itemsList );
        spinner.setAdapter(spinnerAdapter);
    }

    // Set up click listeners for buttons
    private void listener() {

        mBinding.button.setOnClickListener(view -> {
            String name = mBinding.name.getText().toString();
            String phone = mBinding.phone.getText().toString().trim();
            String password = mBinding.password.getText().toString().trim();
            String selectedRole = mBinding.roleSpinner.getSelectedItem().toString();
            String selectedTeam = mBinding.teamSpinner.getSelectedItem().toString();

            // Validate input and register user
            try {
                if (isValidInput(name,phone, selectedRole, selectedTeam, password))
                {
                    String id = Utils.generateId();

                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.USERID, Context.MODE_PRIVATE);
                    String userid  = sharedPreferences.getString(Constants.ID,"");

                    UserEntity entity = new UserEntity(id,name,phone,password,ViewModel.getTeamID(selectedTeam),ViewModel.getRoleID(selectedRole),Utils.getCurrentDateTime(),userid,Utils.getCurrentDateTime(),userid,false);
                    ViewModel.insertUser(entity);
                    DialogUtils.showDialog(getString(R.string.signup_succesful)+Constants.EIN_ID+id, mAactivity);

                    resetFields();

                }
                else
                {
                    Log.println(Log.INFO, Constants.VALIDATION, String.valueOf(R.string.log_validation));
                }
            } catch (NumberParseException e) {
                throw new RuntimeException(e);
            }

        });

    }


    // Reset Fields
    private void resetFields() {
        mBinding.name.setText("");
        mBinding.phone.setText("");
        mBinding.password.setText("");

        mBinding.roleSpinner.setSelection(0);
        mBinding.teamSpinner.setSelection(0);
    }


    private boolean isValidInput(String name, String phone, String role,
                                 String team, String password) throws NumberParseException {

        // Check if the name is empty
        if (name.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_name_text),this.requireActivity());
            return false;
        }

        // Check if the name is valid
        if (!name.matches(Constants.NAME_REGEX))
        {
            DialogUtils.showDialog(getString(R.string.valid_name_text),this.requireActivity());
            return false;
        }

        // Check if the password is empty
        if (password.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_pwd_text),this.requireActivity());
            return false;
        }

        // Check password length and complexity
        if (password.length() < 8 || !password.matches(Constants.PWD_REGEX)) {
            DialogUtils.showDialog(getString(R.string.invalid_pwd_text),this.requireActivity());
            return false;
        }


        // Check if the phone number is empty
        if (phone.isEmpty()) {
            DialogUtils.showDialog(getString(R.string.empty_no_text),this.requireActivity());
            return false;
        }

        Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phone, "IN");

        // Check if the phone number is valid
        if (!phoneNumberUtil.isValidNumber(phoneNumber))
        {
                DialogUtils.showDialog(getString(R.string.invalid_no_text), this.requireActivity());
                return false;
        }

        // Check if the selected role is the default prompt
        if (role.equals(getString(R.string.role_prompt))) {
            DialogUtils.showDialog(getString(R.string.role_text),this.requireActivity());
            return false;
        }

        // Check if the selected team is the default prompt
        if (team.equals(getString(R.string.team_prompt))) {
            DialogUtils.showDialog(getString(R.string.team_text),this.requireActivity());
            return false;
        }

        // All validation checks passed
        return true;
    }

}