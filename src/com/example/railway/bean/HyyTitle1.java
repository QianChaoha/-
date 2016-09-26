package com.example.railway.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by QianChao on 2016/9/22.
 */
public class HyyTitle1 implements Parcelable {
    public String mtitle1;
    public ArrayList<HyyTitle2> title2List;
    public HyyTitle2 mTitle2;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //将对象序列化为一个Parcel对象
        parcel.writeString(mtitle1);
        parcel.writeTypedList(title2List);
        parcel.writeParcelable(mTitle2,0);
    }
    public static final Parcelable.Creator<HyyTitle1> CREATOR=new Parcelable.Creator<HyyTitle1>(){

        @Override
        public HyyTitle1 createFromParcel(Parcel parcel) {
            HyyTitle1 huoYunYuanTitle=new HyyTitle1();
            huoYunYuanTitle.mtitle1 = parcel.readString();
            huoYunYuanTitle.title2List = (ArrayList<HyyTitle2>) parcel.readArrayList(ClassLoader.getSystemClassLoader());
            huoYunYuanTitle.mTitle2 = parcel.readParcelable(ClassLoader.getSystemClassLoader());
            return huoYunYuanTitle;
        }

        @Override
        public HyyTitle1[] newArray(int i) {
            return new HyyTitle1[i];
        }
    };


}
