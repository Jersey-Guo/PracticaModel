package com.jersey.practicalmodel.http.interceptor;

import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 公共参数
 */
public class PublicParamsInterceptor implements Interceptor {

    private Map<String, String> publicParams;

    public PublicParamsInterceptor(Map<String, String> publicParams) {
        this.publicParams = publicParams;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request currentRequest = chain.request();
        String url = currentRequest.url().toString();
        if (TextUtils.equals(currentRequest.method().toLowerCase(), "get")) {
            url = getRequestUrlForGet(url);
            Request request = currentRequest.newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        } else {
            RequestBody body = currentRequest.body();
            if (body != null && body instanceof FormBody) {
                FormBody formBody = (FormBody) body;
                FormBody.Builder builder = new FormBody.Builder();
                Map<String, String> temMap = new HashMap<>();
                for (int i = 0; i < formBody.size(); i++) {
                    builder.add(formBody.encodedName(i), formBody.encodedValue(i));
                    temMap.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
                for (Map.Entry<String, String> entry : publicParams.entrySet()) {
                    if (!temMap.containsKey(entry.getKey())) {
                        builder.add(entry.getKey(), entry.getValue());
                    }
                }
                FormBody newFormBody = builder.build();
                Request newRequest = currentRequest.newBuilder()
                        .post(newFormBody)
                        .build();
                return chain.proceed(newRequest);
            }
        }
        return chain.proceed(currentRequest);
    }

    private String getRequestUrlForGet(String url) {
        if (publicParams == null || publicParams.isEmpty()) {
            return "";
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        for (Map.Entry<String, String> entry : publicParams.entrySet()) {
            urlBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
        }
        url = urlBuilder.toString();
        if (!url.contains("?")) {
            url = url.replaceFirst("&", "?");
        }
        return url;
    }
}
