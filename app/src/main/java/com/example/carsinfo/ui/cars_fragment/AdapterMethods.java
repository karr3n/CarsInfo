package com.example.carsinfo.ui.cars_fragment;


import android.widget.ImageView;
import android.widget.TextView;

import com.example.carsinfo.Car;

public interface AdapterMethods {
    void setName(String name, TextView textView);

    void loadImage(String link, ImageView imageView);

    void openDetails(Car car);
}
