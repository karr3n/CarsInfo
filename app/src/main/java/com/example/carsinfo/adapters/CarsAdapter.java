package com.example.carsinfo.adapters;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsinfo.Car;
import com.example.carsinfo.R;
import com.example.carsinfo.ui.cars_fragment.AdapterMethods;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsHolder> {
    private ArrayList<Car> cars;
    private AdapterMethods methods;
    private long lastClickTime = 0;

    public CarsAdapter(ArrayList<Car> cars, AdapterMethods methods) {
        this.cars = cars;
        this.methods = methods;
    }

    @NonNull
    @Override
    public CarsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CarsHolder holder, int position) {
        final Car car = cars.get(position);
        methods.setName(car.getName(), holder.name);
        methods.loadImage(car.getPhotoUrl(), holder.photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastClickTime < 400) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                methods.openDetails(car);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class CarsHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView photo;

        CarsHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.car_name);
            photo = itemView.findViewById(R.id.car_image);
        }
    }
}