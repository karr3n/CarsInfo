package com.example.carsinfo.ui.detail_fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.carsinfo.Car;
import com.example.carsinfo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public static final String TAG = "DetailFragment";
    private static final String KEY_CAR = "CAR";
    private TextView name, text;
    private ImageView photo;
    private Car car;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Car car) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_CAR, car);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        car = getArguments().getParcelable(KEY_CAR);
        initViews(view);
        setTexts();
        loadPhoto();
    }

    private void initViews(View v) {
        name = v.findViewById(R.id.detail_name);
        text = v.findViewById(R.id.detail_text);
        photo = v.findViewById(R.id.detail_photo);
    }

    private void setTexts() {
        name.setText(car.getName());
        text.setText(car.getDetailInfo());
        text.setMovementMethod(new ScrollingMovementMethod());
    }

    private void loadPhoto() {
        Glide.with(text)
                .load(car.getPhotoUrl())
                .into(photo);
    }
}
