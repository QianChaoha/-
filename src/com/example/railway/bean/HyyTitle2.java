package com.example.railway.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by QianChao on 2016/9/22.
 */
public class HyyTitle2 implements Parcelable {
    public String title2;
    public ArrayList<String> title3List;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //将对象序列化为一个Parcel对象
        parcel.writeString(title2);
        parcel.writeStringList(title3List);
    }

    public static final Parcelable.Creator<HyyTitle2> CREATOR = new Parcelable.Creator<HyyTitle2>() {

        @Override
        public HyyTitle2 createFromParcel(Parcel parcel) {
            HyyTitle2 hyyTitle2=new HyyTitle2();
            hyyTitle2.title2 = parcel.readString();
            hyyTitle2.title3List = (ArrayList<String>) parcel.readArrayList(ClassLoader.getSystemClassLoader());
            return hyyTitle2;
        }

        @Override
        public HyyTitle2[] newArray(int i) {
            return new HyyTitle2[i];
        }
    };

}
