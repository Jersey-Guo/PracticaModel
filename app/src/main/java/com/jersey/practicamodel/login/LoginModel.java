package com.jersey.practicamodel.login;

import com.jersey.practicalmodel.base.BaseCallback;
import com.jersey.practicalmodel.mvp.BaseModel;

public class LoginModel implements BaseModel<LoginEntity> {

    @Override
    public void getData(BaseCallback<LoginEntity> callback) {

    }

    public void login(String name, String pwd, BaseCallback<LoginEntity> callback) {
        LoginEntity entity = new LoginEntity();
        entity.code = 200;
        entity.errorMsg = "success";
        entity.name = name;
        entity.pwd = pwd;
        callback.onCallback(entity);
    }

    public void sendCode(String mobile,BaseCallback<LoginEntity> callback){
        LoginEntity entity = new LoginEntity();
        entity.code = 200;
        entity.errorMsg = "success";
        entity.name = mobile;
        callback.onCallback(entity);
    }
}
