package com.jersey.practicalmodel.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jersey.practicalmodel.base.BaseApplication;

public class CacheUtils {

    private static String CACHE_NAME = BaseApplication.getContext().getPackageName() + "_cache_file";

    private static class InstanceHolder {
        private static CacheUtils INSTANCE = new CacheUtils();
    }

    private CacheUtils() {
    }

    public static CacheUtils getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void init(String cacheFileName) {
        CACHE_NAME = cacheFileName;
    }

    public void setCache(String cacheKey, String cacheContent) {
        SPUtils.getInstance(CACHE_NAME).put(cacheKey, cacheContent);
    }

    public String getCache(String cacheKey) {
        return SPUtils.getInstance(CACHE_NAME).getString(cacheKey);
    }


    public <T> void setCache(String cacheKey, T cacheContent) {
        if(cacheContent == null){
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(cacheContent);
        setCache(cacheKey, json);
    }

    public <T> T getCache(String cacheKey, Class<T> tClass) {
        String cachJson = getCache(cacheKey);
        if (TextUtils.isEmpty(cacheKey)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(cachJson, tClass);
    }
}
