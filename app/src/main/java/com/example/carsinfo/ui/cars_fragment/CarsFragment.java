package com.example.carsinfo.ui.cars_fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.carsinfo.Car;
import com.example.carsinfo.R;
import com.example.carsinfo.adapters.CarsAdapter;
import com.example.carsinfo.ui.detail_fragment.DetailFragment;
import com.example.carsinfo.ui.main_activity.MainActivity;
import com.example.carsinfo.utils.ConnectionUtils;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarsFragment extends MvpFragment<CarsFragmentContract.View, CarsFragmentPresenter>
        implements CarsFragmentContract.View, SwipeRefreshLayout.OnRefreshListener, AdapterMethods {
    public static final String TAG = "CarsFragment";

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout srl;
    private ArrayList<Car> cars = new ArrayList<>();

    public CarsFragment() {
        // Required empty public constructor
    }

    @Override
    public CarsFragmentPresenter createPresenter() {
        return new CarsFragmentPresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cars, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        getPresenter().checkConnection(ConnectionUtils.hasConnection(getContext()));
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void findViews(View v) {
        rv = v.findViewById(R.id.cars_list);
        srl = v.findViewById(R.id.refresh_swipe);
        srl.setOnRefreshListener(this);
    }

    @Override
    public void initRecyclerView(ArrayList<Car> cars) {
        adapter = new CarsAdapter(cars, this);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        rv.setMotionEventSplittingEnabled(false);
    }

    @Override
    public void refreshRecyclerView(ArrayList<Car> newCars) {
        if (!cars.isEmpty()) {
            cars.clear();
            cars.addAll(newCars);
            adapter.notifyDataSetChanged();
        } else {
            initRecyclerView(newCars);
        }
    }

    @Override
    public void queryData() {
        ParseQuery<Car> query = ParseQuery.getQuery("Car");
        query.findInBackground(new FindCallback<Car>() {
            @Override
            public void done(List<Car> objects, ParseException e) {
                cars = getPresenter().getQuery((ArrayList<Car>) objects, e, getContext());
                initRecyclerView(cars);
            }
        });
    }

    @Override
    public void onRefresh() {
        getPresenter().updateData(ConnectionUtils.hasConnection(getContext()));
    }

    @Override
    public void refreshList() {
        ParseQuery<Car> query = ParseQuery.getQuery("Car");
        query.findInBackground(new FindCallback<Car>() {
            @Override
            public void done(List<Car> objects, ParseException e) {
                ArrayList<Car> newCars = getPresenter().getQuery((ArrayList<Car>) objects, e, getContext());
                refreshRecyclerView(newCars);
                srl.setRefreshing(false);
            }
        });
    }

    @Override
    public void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void disableRefreshing() {
        srl.setRefreshing(false);
    }

    @Override
    public void setName(String name, TextView textView) {
        textView.setText(name);
    }

    @Override
    public void loadImage(String link, ImageView imageView) {
        Glide.with(this)
                .load(link)
                .into(imageView);

    }

    @Override
    public void openDetails(Car car) {
        ((MainActivity) getActivity()).openDetailFragment(DetailFragment.newInstance(car), DetailFragment.TAG);
    }
}
