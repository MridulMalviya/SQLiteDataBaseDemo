package com.malviya.sqlitedatabasedemo.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malviya.sqlitedatabasedemo.R;
import com.malviya.sqlitedatabasedemo.adapter.UserNameAdapter;
import com.malviya.sqlitedatabasedemo.constant.Constant;
import com.malviya.sqlitedatabasedemo.model.UserNameDataModel;
import com.malviya.sqlitedatabasedemo.utility.DBCreateClass;
import com.malviya.sqlitedatabasedemo.utility.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 *  * Created by Prafulla on 11/16/2016.
 */

public class ShowDatalnList extends Fragment {
    RecyclerView recyclerView;
    UserNameAdapter userNameAdapter;
    private ArrayList<UserNameDataModel> modelArrayList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelArrayList =new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview,container,false);
        addInRecyclerView(view);
        return view;
    }

    private void addInRecyclerView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        DBCreateClass dbCreateClass = new DBCreateClass(getContext());
        SQLiteDatabase sqLiteDatabase = dbCreateClass.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + Constant.TABLE_NAME, null);

        if (cursor.getCount() == 0) {
            Log.i("DB++", " cursor :" + cursor.getCount());
            redirectToRegistrationFragment();
        } else {
            if (cursor.moveToFirst())
                do {
                      Log.i("DB++"," "+cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getString(2));
                    UserNameDataModel userNameDataModel1 = new UserNameDataModel(cursor.getInt(0),cursor.getString(1), cursor.getString(2));
                    modelArrayList.add(userNameDataModel1);
                } while (cursor.moveToNext());
            cursor.close();
            userNameAdapter = new UserNameAdapter(modelArrayList);
            recyclerView.setAdapter(userNameAdapter);
            userNameAdapter.notifyDataSetChanged();
        }
    }

    private void redirectToRegistrationFragment() {
        FragmentManager fM =getActivity().getSupportFragmentManager();
        FragmentTransaction fT =fM.beginTransaction();
        Fragment registerFragment = new RegistrationFragment();
        fT.replace(R.id.fragment_container,registerFragment);
        fT.commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putParcelable(Constant.KEY,modelArrayList.get(position));

              //  Toast.makeText(getContext(),""+modelArrayList.get(position).getName(),Toast.LENGTH_SHORT).show();

                FragmentManager fM =getActivity().getSupportFragmentManager();
                FragmentTransaction fT =fM.beginTransaction();
                Fragment updateFragment = new FragmentUpdate();
                updateFragment.setArguments(bundle);
                fT.replace(R.id.fragment_container,updateFragment);
                fT.commit();
            }
        }));
    }
}
