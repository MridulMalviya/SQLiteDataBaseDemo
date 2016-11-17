package com.malviya.sqlitedatabasedemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prafulla on 11/16/2016.
 */

public class UserNameDataModel implements Parcelable {

    private int Id;
    private  String Name;
    private String Surname;


    public UserNameDataModel(int id,String name, String surname) {
        Id=id;
        Name = name;
        Surname = surname;
    }

    public UserNameDataModel(){}

    private UserNameDataModel(Parcel in) {
        Id=in.readInt();
        Name = in.readString();
        Surname = in.readString();
    }

    public static final Creator<UserNameDataModel> CREATOR = new Creator<UserNameDataModel>() {
        @Override
        public UserNameDataModel createFromParcel(Parcel in) {
            return new UserNameDataModel(in);
        }

        @Override
        public UserNameDataModel[] newArray(int size) {
            return new UserNameDataModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Name);
        dest.writeString(Surname);
    }

    public int  getId() {
        return Id;
    }
    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

}
