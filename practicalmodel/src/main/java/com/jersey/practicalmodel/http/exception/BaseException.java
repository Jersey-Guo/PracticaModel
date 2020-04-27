package com.jersey.practicalmodel.http.exception;

import androidx.annotation.Nullable;

public class BaseException extends Throwable {
    private String errorMsg;
    private String errorCode;

    public BaseException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorMsg, String errorCode) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
}
