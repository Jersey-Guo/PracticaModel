package com.jersey.practicalmodel.base;

import android.app.Application;
import android.content.Context;



public class BaseApplication extends Application {

    private static BaseApplication sApp;

    public static Context getContext() {
        return sApp.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

}
