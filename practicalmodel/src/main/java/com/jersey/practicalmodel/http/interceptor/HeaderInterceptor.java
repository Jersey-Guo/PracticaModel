package com.jersey.practicalmodel.http.interceptor;

import com.jersey.practicalmodel.constans.HttpConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求头
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .method(originalRequest.method(), originalRequest.body());
        requestBuilder.addHeader("Authorization", HttpConstants.TOKEN);//添加请求头信息，服务器进行token有效性验证
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
