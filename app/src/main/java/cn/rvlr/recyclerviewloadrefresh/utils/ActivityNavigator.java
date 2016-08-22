package cn.rvlr.recyclerviewloadrefresh.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import cn.rvlr.recyclerviewloadrefresh.AndroidApplication;

/**
 * Activity Navigator
 */
public class ActivityNavigator {

    /**
     * @param targetActivity 要跳转的页面
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity) {
        Intent mIntent = new Intent(AndroidApplication.getInstance().getCurrentActivity(), targetActivity);
        if (mIntent == null) {
            return;
        }
        navigateTo(targetActivity, mIntent);
    }

    /**
     * @param targetActivity
     * @param intent
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent) {
        if (intent == null) {
            return;
        }
        Activity currentActivity = AndroidApplication.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        navigateTo(currentActivity, targetActivity, intent);
    }

    /**
     * Used in onCreate(before onResume) method to ensure current activity is not null.
     *
     * @param context
     * @param targetActivity
     * @param intent
     */
    public static void navigateTo(@NonNull Context context, @NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent) {
        if (intent == null) {
            return;
        }
        context.startActivity(intent);
    }


    /**
     * @param targetActivity 要跳转的Activity
     * @param intent
     * @param i              传入要得到返回的值标识
     */
    public static void startResult(@NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent, int i) {
        if (intent == null) {
            return;
        }
        Activity currentActivity = AndroidApplication.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        currentActivity.startActivityForResult(intent, i);

    }

    /**
     * @param targetActivity 传入的是当前的Activity
     * @param bundle         要返回的值 intent.putExtras(bundle);
     * @param i              传入返回的值标识
     */
    public static void setResult(@NonNull Class<? extends Activity> targetActivity, Bundle bundle, int i) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        Activity currentActivity = AndroidApplication.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        currentActivity.setResult(i, intent);

    }

    public static void finish() {//关闭当前页面
        AndroidApplication.getInstance().getCurrentActivity().finish();

    }
}
