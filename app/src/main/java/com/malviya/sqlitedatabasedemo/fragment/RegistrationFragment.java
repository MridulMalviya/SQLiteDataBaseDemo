package com.malviya.sqlitedatabasedemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.malviya.sqlitedatabasedemo.R;
import com.malviya.sqlitedatabasedemo.utility.SQLiteOperation;

/**
 * Created by Prafulla on 11/16/2016.
 */

public class RegistrationFragment extends Fragment implements View.OnClickListener {
    EditText mName;
    EditText mSurName;
    Button mSave;
    Button mCancel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registration,container,false);
        init(view);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void init(View view) {
        mName=(EditText) view.findViewById(R.id.editText_Name);
        mSurName=(EditText) view.findViewById(R.id.editText_SurName);
        mSave=(Button)view.findViewById(R.id.button_save);
        mCancel=(Button)view.findViewById(R.id.button_cancel);
        mSave.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_save:
                if(mName.getText().toString().equals("")){
                    mName.setError("Plz write some name ");
                }
                else if (mSurName.getText().toString().equals("")){
                    mSurName.setError("Plz write some surname ");
                }
                else
                {
                    if(SQLiteOperation.getInstance().insertData(getContext(),mName,mSurName)) {
                        Toast.makeText(getContext(),"Save record ",Toast.LENGTH_SHORT).show();

                        FragmentManager fM =getActivity().getSupportFragmentManager();
                        FragmentTransaction fT =fM.beginTransaction();
                        Fragment showListFragment = new ShowDatalnList();
                        fT.replace(R.id.fragment_container,showListFragment);
                        fT.commit();
                    }
                }

                break;
            case R.id.button_cancel:
                     mName.setText(" ");
                    mName.setHint("Name");
                    mSurName.setText(" ");
                     mSurName.setHint("SurName");
                break;
        }
    }
}
