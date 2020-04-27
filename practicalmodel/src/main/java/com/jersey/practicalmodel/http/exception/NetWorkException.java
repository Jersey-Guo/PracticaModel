package com.jersey.practicalmodel.http.exception;

public class NetWorkException extends BaseException {

    public NetWorkException(String errorMsg) {
        super(errorMsg);
    }

    public NetWorkException(String errorMsg, String errorCode) {
        super(errorMsg, errorCode);
    }
}
