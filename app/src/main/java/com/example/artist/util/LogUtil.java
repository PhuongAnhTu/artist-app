package com.example.artist.util;

import android.os.Build;
import android.util.Log;

import com.example.artist.BuildConfig;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.concurrent.TimeoutException;

public class LogUtil {
    public static final boolean ENABLE = true;
    private static final String PREFIX = "[MQLog]";   // add prefix for easy filtering my own log

    public static void v(String message) {
        printLog(Log.VERBOSE, PREFIX, message);
    }
    public static void d(String message) {
        printLog(Log.DEBUG, PREFIX, message);
    }
    public static void i(String message) {
        printLog(Log.INFO, PREFIX, message);
    }
    public static void w(String message) {
        printLog(Log.WARN, PREFIX, message);
    }
    public static void e(String message) {
        printLog(Log.ERROR, PREFIX, message);
    }
    public static void logException(Throwable throwable) {
        printThrowable(PREFIX, throwable);
    }

    public static void v(String tag, String message) {
        printLog(Log.VERBOSE, tag, message);
    }
    public static void d(String tag, String message) {
        printLog(Log.DEBUG, tag, message);
    }
    public static void i(String tag, String message) {
        printLog(Log.INFO, tag, message);
    }
    public static void w(String tag, String message) {
        printLog(Log.WARN, tag, message);
    }
    public static void e(String tag, String message) {
        printLog(Log.ERROR, tag, message);
    }
    public static void logException(String tag, Throwable throwable) {
        printThrowable(tag, throwable);
    }

    public static String getThrowableString(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        return throwable.getClass().getSimpleName() +
                (throwable.getMessage() == null ? "" : (" " + throwable.getMessage()));
    }

    public static void printStackTrace(Object e) {
        if (BuildConfig.DEBUG && e instanceof Throwable) {
            Throwable throwable = (Throwable) e;
            boolean notPrint = throwable instanceof UnknownHostException
                    || throwable instanceof UnknownServiceException
                    || throwable instanceof SocketException     // ConnectException, NoRouteToHostException, PortUnreachableException
                    || throwable instanceof TimeoutException
                    || throwable instanceof SocketTimeoutException
                    || throwable instanceof ConnectTimeoutException;
            if (notPrint) {
                // There's no meaning to print stacktrace for connection exceptions
                return;
            }
            throwable.printStackTrace();
        }
    }


    private static String addPrefix(String content) {
        return PREFIX + " " + content;
    }

    private static void printLog(int priority, String tag, String msg) {
        if (!ENABLE) {
            return;
        }
        String message = msg;
        if (!PREFIX.equals(tag)) {
            message = addPrefix(msg);
        }
        Log.println(priority, tag, message + getCallerInfo(new Throwable().getStackTrace()));
    }

    private static void printThrowable(String tag, Throwable throwable) {
        if (!ENABLE) {
            return;
        }
        String message = getThrowableString(throwable);
        if (!PREFIX.equals(tag)) {
            message = addPrefix(message);
        }
        Log.println(Log.ERROR, tag, message + getCallerInfo(new Throwable().getStackTrace()));
        printStackTrace(throwable);
    }

    private static String getCallerInfo(StackTraceElement[] stacks) {
        if (stacks == null || stacks.length < 2) {
            return "";
        }
        StackTraceElement stack = stacks[2];
        return String.format(" (%s:%s)", stack.getFileName(), stack.getLineNumber());
    }

    public static String androidVersion() {
        int sdk = Build.VERSION.SDK_INT;
        switch (sdk) {
            case Build.VERSION_CODES.BASE: return "BASE (Android 1.0)";
            case Build.VERSION_CODES.BASE_1_1: return "BASE_1_1 (Android 1.1)";
            case Build.VERSION_CODES.CUPCAKE: return "CUPCAKE (1.5)";
            case Build.VERSION_CODES.CUR_DEVELOPMENT: return "CUR_DEVELOPMENT";
            case Build.VERSION_CODES.DONUT: return "DONUT (1.6)";
            case Build.VERSION_CODES.ECLAIR: return "ECLAIR (2.0)";
            case Build.VERSION_CODES.ECLAIR_0_1: return "ECLAIR_0_1 (2.0.1)";
            case Build.VERSION_CODES.ECLAIR_MR1: return "ECLAIR_MR1 (2.1)";
            case Build.VERSION_CODES.FROYO: return "FROYO (2.2)";
            case Build.VERSION_CODES.GINGERBREAD: return "GINGERBREAD (2.3)";
            case Build.VERSION_CODES.GINGERBREAD_MR1: return "GINGERBREAD_MR1 (2.3.3)";
            case Build.VERSION_CODES.HONEYCOMB: return "HONEYCOMB (3.0)";
            case Build.VERSION_CODES.HONEYCOMB_MR1: return "HONEYCOMB_MR1 (3.1)";
            case Build.VERSION_CODES.HONEYCOMB_MR2: return "HONEYCOMB_MR2 (3.2)";
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH: return "ICE_CREAM_SANDWICH (4.0)";
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1: return "ICE_CREAM_SANDWICH_MR1 (4.0.3)";
            case Build.VERSION_CODES.JELLY_BEAN: return "JELLY_BEAN (4.1)";
            case Build.VERSION_CODES.JELLY_BEAN_MR1: return "JELLY_BEAN_MR1 (4.2)";
            case Build.VERSION_CODES.JELLY_BEAN_MR2: return "JELLY_BEAN_MR2 (4.3)";
            case Build.VERSION_CODES.KITKAT: return "KITKAT (4.4)";
            case Build.VERSION_CODES.KITKAT_WATCH: return "KITKAT_WATCH (4.4W)";
            case Build.VERSION_CODES.LOLLIPOP: return "LOLLIPOP (5.0)";
            case Build.VERSION_CODES.LOLLIPOP_MR1: return "LOLLIPOP_MR1 (5.1)";
            case Build.VERSION_CODES.M: return "Marshmallow (6.0)";
            case Build.VERSION_CODES.N: return "Nougat (7.0)";
            case Build.VERSION_CODES.N_MR1: return "Nougat++ (7.1)";
            case Build.VERSION_CODES.O: return "Oreo (8.0)";
            case Build.VERSION_CODES.O_MR1: return "Oreo (8.1)";
            case Build.VERSION_CODES.P: return "Pie (9)";
            case Build.VERSION_CODES.Q: return "Android 10";
            //case Build.VERSION_CODES.R: return "Android 11";
            default: return "Unknown";
        }
    }

}
