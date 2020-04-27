package com.jersey.practicalmodel.utils;

import android.util.Log;


import com.jersey.practicalmodel.BuildConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class LogUtil {
    /**
     * cannot be instantiated
     */
    private LogUtil() {

        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static String classname;
    private static ArrayList<String> methods;

    static {
        classname = LogUtil.class.getName();
        methods = new ArrayList<>();

        Method[] ms = LogUtil.class.getDeclaredMethods();
        for (Method m : ms) {
            methods.add(m.getName());
        }
    }

    /**
     * 输出IS_OUTPUT_LOG级别的日志，自动获取类名作为TAG。
     *
     * @param msg 输出内容，带有方法名和行号。
     */
    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.d(content[0], content[1]);
        }
    }

    /**
     * 输出IS_OUTPUT_LOG级别的日志。
     *
     * @param tag TAG.
     * @param msg 输出内容，带有方法名和行号。
     */
    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * 输出IS_OUTPUT_LOG级别的异常日志。
     *
     * @param t 异常对象。
     */
    public static void d(Throwable t) {
        d(t.getMessage());
    }

    /**
     * 输出Error级别的日志。
     *
     * @param msg 输出内容，带有方法名和行号。
     */
    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.e(content[0], content[1]);
        }
    }

    /**
     * 输出Error级别的日志。
     *
     * @param tag TAG.
     * @param msg 输出内容，带有方法名和行号。
     */
    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * 输出Error级别的异常日志。
     *
     * @param t 异常对象。
     */
    public static void e(Throwable t) {
        if (BuildConfig.DEBUG && t != null) {
            t.printStackTrace();
        }
    }

    /**
     * 获取日志信息的TAG、带行号的内容。
     *
     * @param msg 日志内容。
     * @return TAG、带行号的内容组成的字符串数组。
     */
    private static String[] getMsgAndTagWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (!classname.equals(st.getClassName()) && !methods.contains(st.getMethodName())) {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String tag = st.getClassName().substring(b);
                    String message = st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    return new String[]{tag, message};
                }

            }
        } catch (Exception e) {
            LogUtil.e(e);
        }
        return new String[]{"Lishang", msg};
    }

    /**
     * 获取带行号的日志信息内容。
     *
     * @param msg 日志内容。
     * @return 带行号的日志信息内容。
     */
    private static String getMsgWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (!classname.equals(st.getClassName()) && !methods.contains(st.getMethodName())) {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String tag = st.getClassName().substring(b);
                    return tag + "->" + st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                }

            }
        } catch (Exception e) {
            LogUtil.e(e);
        }
        return msg;
    }

    /**
     * 输出Info级别的日志。
     *
     * @param msg 日志内容，带有方法名和行号。
     */
    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.i(content[0], content[1]);
        }
    }

    /**
     * 输出Info级别的日志。
     *
     * @param tag TAG.
     * @param msg 日志内容，带有方法名和行号。
     */
    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * 输出Info级别的异常日志。
     *
     * @param t 异常对象。
     */
    public static void i(Throwable t) {
        i(t.getMessage());
    }

    /**
     * 输出Vorbose级别的日志。
     *
     * @param msg 日志内容，带有方法名和行号。
     */
    public static void v(String msg) {
        if (BuildConfig.DEBUG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.v(content[0], content[1]);
        }
    }

    /**
     * 输出Vorbose级别的日志。
     *
     * @param tag TAG.
     * @param msg 日志内容，带有方法名和行号。
     */
    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * 输出Vorbose级别的异常日志。
     *
     * @param t 异常对象。
     */
    public static void v(Throwable t) {
        v(t.getMessage());
    }

    /**
     * 输出Warn级别的日志。
     *
     * @param msg 日志内容，带有方法名和行号。
     */
    public static void w(String msg) {
        if (BuildConfig.DEBUG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.w(content[0], content[1]);
        }
    }

    /**
     * 输出Warn级别的日志。
     *
     * @param tag TAG.
     * @param msg 日志内容，带有方法名和行号。
     */
    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * 输出Warn级别的异常日志。
     *
     * @param t 异常对象。
     */
    public static void w(Throwable t) {
        w(t.getMessage());
    }
}
