package com.example.carsinfo.ui.cars_fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.carsinfo.Car;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;

class CarsFragmentPresenter extends MvpBasePresenter<CarsFragmentContract.View> implements CarsFragmentContract.Presenter {

    @Override
    public ArrayList<Car> getQuery(final ArrayList<Car> cars, final Exception e, Context c) {
        if (e != null) {
            ifViewAttached(new ViewAction<CarsFragmentContract.View>() {
                @Override
                public void run(@NonNull CarsFragmentContract.View view) {
                    view.toast(e.getMessage());
                }
            });
        } else return cars;
        return new ArrayList<>();
    }

    @Override
    public void checkConnection(final boolean isConnected) {
        ifViewAttached(new ViewAction<CarsFragmentContract.View>() {
            @Override
            public void run(@NonNull CarsFragmentContract.View view) {
                if (isConnected)
                    view.queryData();
                else
                    view.toast("No Internet Connection");
            }
        });
    }

    @Override
    public void updateData(final boolean isConnected) {
        ifViewAttached(new ViewAction<CarsFragmentContract.View>() {
            @Override
            public void run(@NonNull CarsFragmentContract.View view) {
                if (!isConnected) {
                    view.toast("No Internet Connection");
                    view.disableRefreshing();
                } else view.refreshList();
            }
        });
    }
}
