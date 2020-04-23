package com.jersey.practicamodel.login;

public class LoginEntity {
    public int code;
    public String errorMsg;
    public String name;
    public String pwd;

    public boolean isSuccess() {
        return code == 200;
    }
}
