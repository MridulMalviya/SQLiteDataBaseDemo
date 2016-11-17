package com.malviya.sqlitedatabasedemo.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.malviya.sqlitedatabasedemo.R;
import com.malviya.sqlitedatabasedemo.constant.Constant;
import com.malviya.sqlitedatabasedemo.model.UserNameDataModel;
import com.malviya.sqlitedatabasedemo.utility.SQLiteOperation;

/**
 * Created by Prafulla on 11/16/2016.
 */

public class FragmentUpdate extends Fragment implements View.OnClickListener {
    EditText name;
    EditText surName;
    Button update;
    Button cancel;
    Button delete;

    private UserNameDataModel dataModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         dataModel=getArguments().getParcelable(Constant.KEY);
         }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_update,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        name= (EditText) view.findViewById(R.id.editText_Name_update);
        surName= (EditText) view.findViewById(R.id.editText_SurName_update);
        update=(Button)view.findViewById(R.id.button_update);
        cancel=(Button)view.findViewById(R.id.button_back_to_insert);
        delete=(Button)view.findViewById(R.id.button_delete);
        update.setOnClickListener(this);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
        name.setText(dataModel.getName());
        surName.setText(dataModel.getSurname());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_update:
               updateData();
                break;
            case R.id.button_back_to_insert:
               backToInsert();
                break;
            case  R.id.button_delete:
                confirmDeleteDialog();
                break;
        }
    }

    private void backToInsert() {
        FragmentManager fM =getActivity().getSupportFragmentManager();
        FragmentTransaction fT =fM.beginTransaction();
        Fragment registerFragment = new RegistrationFragment();
        fT.replace(R.id.fragment_container,registerFragment);
        fT.commit();
    }

    private void showListData() {
        FragmentManager fM =getActivity().getSupportFragmentManager();
        FragmentTransaction fT =fM.beginTransaction();
        Fragment showListFragment = new ShowDatalnList();
        fT.replace(R.id.fragment_container,showListFragment);
        fT.commit();
    }

    private void updateData() {
        if(SQLiteOperation.getInstance().updateData(getContext(),name,surName,dataModel.getId())) {
            Toast.makeText(getContext(),"Update record successfully  ",Toast.LENGTH_SHORT).show();

            FragmentManager fM =getActivity().getSupportFragmentManager();
            FragmentTransaction fT =fM.beginTransaction();
            Fragment showListFragment = new ShowDatalnList();
            fT.replace(R.id.fragment_container,showListFragment);
            fT.commit();
        }
    }

    private void confirmDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure you want to delete? "+name.getText().toString())
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       deleteData();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void deleteData() {
        if(SQLiteOperation.getInstance().deleteData(getContext(),dataModel.getId())) {
            Toast.makeText(getContext(),"Record is deleted Successfully  ",Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment showListFragment1 = new ShowDatalnList();
            fragmentTransaction.replace(R.id.fragment_container, showListFragment1);
            fragmentTransaction.commit();
        }
    }
}
