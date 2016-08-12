package cn.rvlr.recyclerviewloadrefresh.server.utils;

import android.util.Log;

import cn.rvlr.recyclerviewloadrefresh.BuildConfig;


/**
 * 统一管理log类
 * 
 */
public class LogUtils {
	
	private static final String TAG = "LogUtils";
	
	private static final boolean LOGGER = BuildConfig.DEBUG;//如果当前为debug模式下才打印日志

	public static void v(String tag, String msg) {
		if (LOGGER) {
			Log.v(TAG, tag + "-->" + msg);
		}
	}

	public static void d(String tag, String msg) {
		if (LOGGER) {
			Log.d(TAG, tag + "-->" + msg);
		}
	}

	public static void i(String tag, String msg) {
		if (LOGGER) {
			Log.i(TAG, tag + "-->" + msg);
		}
	}

	public static void w(String tag, String msg) {
		if (LOGGER) {
			Log.v(TAG, tag + "-->" + msg);
		}
	}

	public static void e(String tag, String msg) {
		if (LOGGER) {
			Log.e(TAG, tag + "-->" + msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (LOGGER) {
			Log.e(TAG, tag + "-->" + msg);
		}
	}
}
