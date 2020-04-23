package com.jersey.practicalmodel.mvp;

import com.jersey.practicalmodel.base.BaseCallback;

public interface BaseModel<T> {
    void getData(BaseCallback<T> callback);
}
