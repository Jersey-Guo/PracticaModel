package com.jersey.practicalmodel.bus;

import com.jersey.practicalmodel.base.BaseCallback;

public interface RxCallback<T> extends BaseCallback {
    @Override
    void onCallback(Object o);

    void onNext(T t);

    void onError(Throwable throwable);
}
