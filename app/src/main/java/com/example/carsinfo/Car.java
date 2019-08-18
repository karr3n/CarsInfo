package com.example.carsinfo;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Car")
public class Car extends ParseObject implements Parcelable {
    public String getName() {
        return getString("Name");
    }

    public String getPhotoUrl() {
        return getString("PhotoUrl");
    }

    public String getDetailInfo() {
        return getString("DetailInfo");
    }
}
