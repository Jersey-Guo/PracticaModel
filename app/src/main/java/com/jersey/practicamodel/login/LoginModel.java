package com.jersey.practicamodel.login;

import android.app.Activity;

import com.jersey.practicalmodel.base.BaseCallback;
import com.jersey.practicalmodel.http.HttpManager;
import com.jersey.practicalmodel.mvp.BaseModel;
import com.jersey.practicamodel.interfaces.JerseyCallback;

public class LoginModel implements BaseModel<LoginEntity> {

    @Override
    public void getData(BaseCallback<LoginEntity> callback) {

    }

    public void login(Activity activity,String name, String pwd, JerseyCallback<LoginEntity> callback) {
        LoginService service =  HttpManager.getInstance().create(LoginService.class);
        HttpManager.getInstance().observer(activity,true,service.login(),callback);

    }



    public void sendCode(String mobile,BaseCallback<LoginEntity> callback){
        LoginEntity entity = new LoginEntity();
        entity.setCode( 200);
        entity.setMsg( "success");
        callback.onCallback(entity);


    }
}
