package com.example.carsinfo.ui.main_activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.carsinfo.R;
import com.example.carsinfo.ui.cars_fragment.CarsFragment;
import com.example.carsinfo.ui.detail_fragment.DetailFragment;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

public class MainActivity extends MvpActivity<MainActivityContract.View, MainActivityContract.Presenter>
        implements MainActivityContract.View {
    private FragmentManager manager;

    @NonNull
    @Override
    public MainActivityContract.Presenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        openCarsFragment(new CarsFragment(), CarsFragment.TAG);
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void openCarsFragment(Fragment fragment, String tag) {
        manager.beginTransaction()
                .replace(R.id.container, fragment, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void openDetailFragment(Fragment fragment, String tag) {
        manager.beginTransaction().hide(manager.findFragmentByTag(CarsFragment.TAG))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.container, fragment, DetailFragment.TAG).commit();
    }

    @Override
    public void onBackPressed() {
        if (!manager.findFragmentByTag(CarsFragment.TAG).isVisible()) {
            manager.beginTransaction().remove(manager.findFragmentByTag(DetailFragment.TAG))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .show(manager.findFragmentByTag(CarsFragment.TAG)).commit();
        } else
            super.onBackPressed();
    }
}
