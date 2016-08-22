package cn.rvlr.recyclerviewloadrefresh.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.util.DisplayMetrics;
import android.view.View;

import android.view.inputmethod.InputMethodManager;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;

import java.util.TimeZone;

/**
 * 通用工具类
 */
public class CommonUtils {

    private static final String TAG = "CommonUtils";

    /**
     * 开启activity
     */
    public static void launchActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    public static void launchActivityForResult(Activity context,
                                               Class<?> activity, int requestCode) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftKeybord(Activity activity) {

        if (null == activity) {
            return;
        }
        try {
            final View v = activity.getWindow().peekDecorView();
            if (v != null && v.getWindowToken() != null) {
                InputMethodManager imm = (InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 判断是否为合法的json
     *
     * @param jsonContent 需判断的字串
     */
    public static boolean isJsonFormat(String jsonContent) {
        try {
            new JsonParser().parse(jsonContent);
            return true;
        } catch (JsonParseException e) {
            LogUtils.i(TAG, "bad json: " + jsonContent);
            return false;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param text
     * @return true null false !null
     */
    public static boolean isNull(String text) {
        if (text == null || "".equals(text.trim()) || "null".equals(text))
            return true;
        return false;
    }


    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    /**
     * 系统显示相关工具类
     *
     */

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         *
         * @param context 上下文
         * @param dpValue 尺寸dip
         * @return 像素值
         */
        public static int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         *
         * @param context 上下文
         * @param pxValue 尺寸像素
         * @return DIP值
         */
        public static int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
         *
         * @param context 上下文
         * @param pxValue 尺寸像素
         * @return SP值
         */
        public static int px2sp(Context context, float pxValue) {
            float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (pxValue / fontScale + 0.5f);
        }

        /**
         * 根据手机的分辨率从 sp 的单位 转成为 px
         *
         * @param context 上下文
         * @param spValue SP值
         * @return 像素值
         */
        public static int sp2px(Context context, float spValue) {
            float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (spValue * fontScale + 0.5f);
        }

        /**
         * 获取dialog宽度
         *
         * @param activity Activity
         * @return Dialog宽度
         */
        public static int getDialogW(Activity activity) {
            DisplayMetrics dm = new DisplayMetrics();
            dm = activity.getResources().getDisplayMetrics();
            int w = dm.widthPixels - 100;
            // int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
            return w;
        }

        /**
         * 获取屏幕宽度
         *
         * @param activity Activity
         * @return 屏幕宽度
         */
        public static int getScreenW(Activity activity) {
            DisplayMetrics dm = new DisplayMetrics();
            dm = activity.getResources().getDisplayMetrics();
            int w = dm.widthPixels;
            // int w = aty.getWindowManager().getDefaultDisplay().getWidth();
            return w;
        }

        /**
         * 获取屏幕高度
         *
         * @param activity Activity
         * @return 屏幕高度
         */
        public static int getScreenH(Activity activity) {
            DisplayMetrics dm = new DisplayMetrics();
            dm = activity.getResources().getDisplayMetrics();
            int h = dm.heightPixels;
            // int h = aty.getWindowManager().getDefaultDisplay().getHeight();
            return h;
        }


        /**
         * 将int值转换为分钟和秒的格式
         *
         * @param value
         * @return
         */
        public static String formatToString(int value) {
            SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
            //需要减去时区差，否则计算结果不正确(中国时区会相差8个小时)
            value -= TimeZone.getDefault().getRawOffset();
            String result = ft.format(value);
            return result;
        }



}
