package com.jersey.practicamodel.login;

import com.jersey.practicalmodel.http.ResponBody;

public class LoginEntity extends ResponBody {
    /**
     * http://www.mocky.io/v2/5ea686d03200005172ac2a87
     */
    private UserInfoBean user_info;

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public boolean isSuccess() {
        return getCode() == 200;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "user_info=" + user_info +
                '}';
    }
}
