package com.jersey.practicamodel.login;


import io.reactivex.Observable;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/v2/5ea686d03200005172ac2a87")
    Observable<LoginEntity> login();

}
