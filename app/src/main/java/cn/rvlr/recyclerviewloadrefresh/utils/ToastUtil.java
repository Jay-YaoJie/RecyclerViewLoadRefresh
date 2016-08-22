package cn.rvlr.recyclerviewloadrefresh.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;
import cn.rvlr.recyclerviewloadrefresh.AndroidApplication;


public class ToastUtil {

    private ToastUtil() {
    }

    public static void show(CharSequence text) {
        if (text != null) {
            if (text.length() < 10) {
                Toast.makeText(AndroidApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AndroidApplication.getInstance(), text, Toast.LENGTH_LONG).show();
            }
        }
    }
    public static void show(@StringRes int resId) {
        show(AndroidApplication.getInstance().getString(resId));
    }
    public  static void  show(){
        //先检查网络是否可以使用
        if (AndroidApplication.getInstance()!=null&&!CommonUtils.isNetworkAvailable(AndroidApplication.getInstance())){
            show( "当前网络不可用，请稍后重试！");
        }else {
            show("服务器繁忙，请稍后重试！");

        }
    }

}