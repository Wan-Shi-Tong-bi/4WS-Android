package com.example.candidascore;

import android.os.Parcel;
import android.os.Parcelable;

public class Buttons implements Parcelable {
    boolean b1;
    boolean b2;
    boolean b3;
    boolean b4;
    boolean needTherapy;
    int count;


    public Buttons(boolean b1, boolean b2, boolean b3, boolean b4, int count) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.count = count;
    }

    public Buttons(boolean b1, boolean b2, boolean b3, boolean b4, boolean needTherapy, int count) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.needTherapy = needTherapy;
        this.count = count;
    }

    public boolean isNeedTherapy() {
        return needTherapy;
    }

    public void setNeedTherapy(boolean needTherapy) {
        this.needTherapy = needTherapy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isB1() {
        return b1;
    }

    public void setB1(boolean b1) {
        this.b1 = b1;
    }

    public boolean isB2() {
        return b2;
    }

    public void setB2(boolean b2) {
        this.b2 = b2;
    }

    public boolean isB3() {
        return b3;
    }

    public void setB3(boolean b3) {
        this.b3 = b3;
    }

    public boolean isB4() {
        return b4;
    }

    public void setB4(boolean b4) {
        this.b4 = b4;
    }

    @Override
    public String toString() {
        return "1: "+b1+" 2: "+b2+" 3: "+b3+" 4: "+b4;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
