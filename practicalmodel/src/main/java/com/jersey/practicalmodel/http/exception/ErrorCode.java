package com.jersey.practicalmodel.http.exception;

public interface ErrorCode {
    /**
     * Not an error!
     */
    int NO_ERROR = 0;
    int UN_KNOW_ERROR = 110;
    int NO_NET_MSG = 404;
    int ERR_CONNECT_OUT_TIME_MSG = 405;
    int ERR_PARSE_MSG = 406;

    int ERR_FILE_IS_EMPTY = 100;

    int PROTOCOL_ERROR = 0;

    int INTERNAL_ERROR = 0;

    int FLOW_CONTROL_ERROR = 0;

    int REFUSED_STREAM = 0;

    int CANCEL = 0;


}
