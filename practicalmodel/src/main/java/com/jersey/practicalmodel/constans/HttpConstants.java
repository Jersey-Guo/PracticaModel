package com.jersey.practicalmodel.constans;

public class HttpConstants {
    public static String TOKEN = "";
   public static  String BASE_URL = "";
    public static  int RETRY_COUNT = 2;

    public static  long CONNECT_TIME_OUT = 60;
    public static  long READ_TIME_OUT = 60;
    public static long WRITE_TIME_OUT = 60;

    /**
     * 默认错误提醒
     */
    public static  String UN_KNOW_ERROR = "服务器忙，请稍后再试";
    /**
     * 默认错误提醒
     */
    public static   String DEF_ERR_MSG = "服务器忙，请稍后再试";
    /**
     * 没有网络错误
     */
    public static String NO_NET_MSG = "没有网络，请检查网络设置或稍后再试";
    /**
     * 数据解析失败
     */
    public static String ERR_PARSE_MSG = "数据解析失败";

    /**
     * 数据加密失败
     */
    public static  String ERR_ENCRIPT_MSG = "数据加密失败";

    /**
     * 数据解密失败
     */
    public static String ERR_DECRIPT_MSG = "数据解密失败";
    /**
     * 加解密处理错误
     */
    public static String ERR_CIPHER_DEFAULT_MSG = "加解密处理错误";
    /**
     * 没有找到文件错误
     */
    public static String FILE_NOT_FOUND = "文件不存在";

    public static  String FILE_UPLOAD_FAILE = "文件上传失败";

    public static String ERR_FILE_IS_EMPTY = "文件为空或不存在";

    public static  String ERROR_CALLBACK_UNCREATE = "未实例化回调";

    public static String ERR_CONNECT_OUT_TIME_MSG = "连接超时，请稍后再试";
}
