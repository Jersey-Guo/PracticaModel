package com.jersey.practicamodel;

import com.jersey.practicalmodel.base.BaseApplication;
import com.jersey.practicalmodel.constans.HttpConstants;
import com.jersey.practicalmodel.utils.Utils;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        HttpConstants.BASE_URL = "https://www.mocky.io";
    }
}
