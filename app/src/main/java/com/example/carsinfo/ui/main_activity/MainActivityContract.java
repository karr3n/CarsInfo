package com.example.carsinfo.ui.main_activity;

import androidx.fragment.app.Fragment;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

interface MainActivityContract {
    interface View extends MvpView {
        void openCarsFragment(Fragment fragment, String tag);
        void openDetailFragment(Fragment fragment, String tag);
    }

    interface Presenter extends MvpPresenter<View> {

    }
}
