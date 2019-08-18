package com.example.carsinfo;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;

public class App extends Application {
    private String name, id, key;
    @Override
    public void onCreate() {
        super.onCreate();
        getKeys();
        initParse();
    }

    private void getKeys(){
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            name = (String) applicationInfo.metaData.get("serverName");
            id = (String) applicationInfo.metaData.get("applicationID");
            key = (String) applicationInfo.metaData.get("clientKey");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("CARS", "getKeys: ", e);
        }
    }

    private void initParse(){
        ParseObject.registerSubclass(Car.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(id)
                .clientKey(key)
                .server(name)
                .build());
    }
}