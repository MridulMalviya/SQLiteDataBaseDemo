package com.malviya.sqlitedatabasedemo.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.malviya.sqlitedatabasedemo.R;
import com.malviya.sqlitedatabasedemo.fragment.RegistrationFragment;
import com.malviya.sqlitedatabasedemo.fragment.ShowDatalnList;

/**
 * Created by Prafulla on 11/16/2016.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button floatingActionButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton= (Button) findViewById(R.id.floatingBtn_showList);
        floatingActionButton.setOnClickListener(this);
        addRegistrationFragment();
    }
    //Add Fragment
    private void addRegistrationFragment() {
        FragmentManager fM =this.getSupportFragmentManager();
        FragmentTransaction fT =fM.beginTransaction();
        Fragment registrationFragment = new RegistrationFragment();
        fT.add(R.id.fragment_container,registrationFragment);
        fT.commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.floatingBtn_showList:
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment showListFragment1 = new ShowDatalnList();
                fragmentTransaction.replace(R.id.fragment_container, showListFragment1);
                fragmentTransaction.commit();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        exitDialog();

    }
    private void exitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit from this Application ? ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
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
}
