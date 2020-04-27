package com.jersey.practicalmodel.http.interfaces;

public interface HttpCallback<T> extends BaseObserve<T>{
    void onStart();
    void onFinish();
    void onError(int errorCode,String errorMsg);
}
