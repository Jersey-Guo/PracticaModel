package com.jersey.practicalmodel.http;

import android.app.Activity;
import android.app.Dialog;

import com.jersey.practicalmodel.base.BaseApi;
import com.jersey.practicalmodel.base.BaseFileBean;
import com.jersey.practicalmodel.constans.HttpConstants;
import com.jersey.practicalmodel.http.exception.ErrorCode;
import com.jersey.practicalmodel.http.interceptor.HeaderInterceptor;
import com.jersey.practicalmodel.http.interceptor.PublicParamsInterceptor;
import com.jersey.practicalmodel.http.interfaces.HttpCallback;
import com.jersey.practicalmodel.uis.HttpsUtils;
import com.jersey.practicalmodel.utils.NetworkUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 */
public class HttpManager {

    private Retrofit mRetrofit;

    private static class InstanceHolder {
        private static HttpManager Instance = new HttpManager();
    }

    private HttpManager() {
        initOkHttp();
    }

    public static HttpManager getInstance() {
        return InstanceHolder.Instance;
    }

    private void initOkHttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(HttpConstants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(HttpConstants.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(HttpConstants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .sslSocketFactory(HttpsUtils.getSslSocketFactory().sSLSocketFactory, HttpsUtils.getSslSocketFactory().trustManager)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new PublicParamsInterceptor(getPublicParams()))
                .addInterceptor(new HeaderInterceptor());

        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

    }

    public <E> E create(Class<E> cls) {
        return mRetrofit.create(cls);
    }


    public <T> void observer(Observable<T> o, HttpCallback<T> callback) {
        observer(null, false, null, o, callback);
    }

    public <T> void observer(Activity activity, boolean isShowDialog, Observable<T> o, HttpCallback<T> callback) {
        observer(activity, isShowDialog, null, o, callback);
    }

    /**
     * @param dialog 可以传自定义dialog
     */
    public <T> void observer(Activity activity, Dialog dialog, Observable<T> o, HttpCallback<T> callback) {
        observer(activity, true, dialog, o, callback);
    }


    /**
     * 普通请求
     */
    public <T> void observer(Activity activity, boolean isShowDialog, Dialog dialog
            , Observable<T> o, HttpCallback<T> callback) {

        if (!NetworkUtils.isConnected()) {
            callback.onError(ErrorCode.NO_ERROR, HttpConstants.NO_NET_MSG);
            return;
        }
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(HttpConstants.RETRY_COUNT)//请求失败重连次数
                .subscribe(new PracticalObserver<T>(activity, dialog, isShowDialog, callback));
    }


    /**
     * 上传文件
     */
    public <T> void uplodaFile(String url, List<BaseFileBean> fileList, HttpCallback<T> callback) {
        uplodaFile(url, null, fileList, callback);
    }

    /**
     * 上传文件
     */
    public <T> void uplodaFile(String url, Map<String, String> params, List<BaseFileBean> fileList, HttpCallback<T> callback) {
        uplodaFile(null, url, params, fileList, callback);
    }

    /**
     * 上传文件
     */
    public <T> void uplodaFile(Activity activity, String url, Map<String, String> params, List<BaseFileBean> fileList, HttpCallback<T> callback) {
        uplodaFile(activity, true, null, url, params, fileList, callback);
    }


    /**
     * 上传文件
     */
    public <T> void uplodaFile(Activity activity, boolean isShowDialog, Dialog dialog
            , String url, Map<String, String> params, List<BaseFileBean> fileList, HttpCallback<T> callback) {
        if (!NetworkUtils.isConnected()) {
            callback.onError(ErrorCode.NO_ERROR, HttpConstants.NO_NET_MSG);
            return;
        }
        if (fileList == null || fileList.isEmpty()) {
            callback.onError(ErrorCode.ERR_FILE_IS_EMPTY, HttpConstants.ERR_FILE_IS_EMPTY);
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM); //表单类型
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                builder.addFormDataPart(key, value);
            }
        }
        for (int i = 0; i < fileList.size(); i++) {
            File file = new File(fileList.get(i).filePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart(fileList.get(i).fileName, file.getName(), requestBody);//后台接收图片流的参数名
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        Observable<T> o = mRetrofit.create(BaseApi.class).uploadFile(url, parts);
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(HttpConstants.RETRY_COUNT)//请求失败重连次数
                .subscribe(new PracticalObserver<T>(activity, dialog, isShowDialog, callback));
    }


    private Map<String, String> getPublicParams() {
        Map<String, String> map = new HashMap<>();
        map.put("os", "android");
        return map;
    }



}
