package com.malviya.sqlitedatabasedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.malviya.sqlitedatabasedemo.R;

/**
 * Created by Prafulla on 11/16/2016.
 */

public class MyViewHolder  extends RecyclerView.ViewHolder{
    TextView mName;
    TextView mSurName;

    public MyViewHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.textView_name);
        mSurName = (TextView) itemView.findViewById(R.id.textView_surName);
    }
}
