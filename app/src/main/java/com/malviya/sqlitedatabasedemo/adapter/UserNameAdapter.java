package com.malviya.sqlitedatabasedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malviya.sqlitedatabasedemo.R;
import com.malviya.sqlitedatabasedemo.model.UserNameDataModel;

import java.util.ArrayList;

/**
 * Created by Prafulla on 11/16/2016.
 */

public class UserNameAdapter extends RecyclerView.Adapter<MyViewHolder> {
     private ArrayList<UserNameDataModel> mArrayList;

    public UserNameAdapter(ArrayList<UserNameDataModel> pArrayList) {
        mArrayList = pArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row,parent,false);
        MyViewHolder myViewHolder =new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mName.setText(mArrayList.get(position).getName());
            holder.mSurName.setText(mArrayList.get(position).getSurname());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
}
