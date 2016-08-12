package cn.rvlr.recyclerviewloadrefresh.server.constants;

import android.os.Build;

import cn.rvlr.recyclerviewloadrefresh.BuildConfig;

/**
 * 使用网络时返回的的码值
 */
public class HttpConstants {
    public static final int RESULT_OK = 0;

    public static final String TOKEN = "Auth";
    public static final String USER_AGENT = "User-Agent";
    public static final int LOGIN_CODE = 999;
    public static final int SUCCESS_CODE = 100;
    public static String USER_AGENT_VALUE= "recyclerviewloadrefresh "+ BuildConfig.APPLICATION_ID + " " + Build.MODEL + " " + Build.BRAND + " " + Build.VERSION.SDK_INT + " " + BuildConfig.VERSION_NAME + " " + BuildConfig.VERSION_CODE;

}
