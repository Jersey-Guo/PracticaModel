package com.jersey.practicalmodel.http.interceptor;

import android.text.format.DateUtils;

import com.jersey.practicalmodel.base.BaseApplication;
import com.jersey.practicalmodel.utils.LogUtil;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {
    private static final String TAG = LogInterceptor.class.getName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString();
        LogUtil.d(TAG, url);
        LogUtil.i(TAG, DateUtils.formatDateTime(BaseApplication.getContext(), System.currentTimeMillis(), DateUtils.FORMAT_ABBREV_TIME));
        Response response = chain.proceed(request);
        LogUtil.i(TAG, DateUtils.formatDateTime(BaseApplication.getContext(), System.currentTimeMillis(), DateUtils.FORMAT_ABBREV_TIME));

        return response;
    }
}
