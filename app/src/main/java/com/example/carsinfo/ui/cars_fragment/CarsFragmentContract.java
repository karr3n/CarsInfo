package com.example.carsinfo.ui.cars_fragment;


import android.content.Context;

import com.example.carsinfo.Car;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.ArrayList;

public interface CarsFragmentContract {
    interface View extends MvpView {
        void findViews(android.view.View v);

        void queryData();

        void initRecyclerView(ArrayList<Car> cars);

        void refreshRecyclerView(ArrayList<Car> newCars);

        void toast(String message);

        void refreshList();

        void disableRefreshing();
    }

    interface Presenter extends MvpPresenter<View> {
        ArrayList<Car> getQuery(ArrayList<Car> cars, Exception e, Context c);

        void checkConnection(boolean isConnected);

        void updateData(final boolean isConnected);
    }
}
