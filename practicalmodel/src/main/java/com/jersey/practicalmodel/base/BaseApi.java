package com.jersey.practicalmodel.base;



import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BaseApi<T> {
    @GET
    Observable<T> get(@Url String url);

    @POST
    Observable<T> post(@Url String url, @QueryMap Map<String, Object> map);

    @Multipart
    @POST
    Observable<ResponseBody> postFormBody(@Url String url, @PartMap Map<String, RequestBody> requestBodyMap);

    @PUT
    Observable<ResponseBody> put(@Url String url, @QueryMap Map<String, Object> map);

    @Multipart
    @POST
    Observable<T> uploadFile(@Url String url,@Part List<MultipartBody.Part> fileList);
}
