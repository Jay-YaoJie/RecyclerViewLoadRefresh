package cn.rvlr.recyclerviewloadrefresh.utils;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * activity堆栈管理
 *
 * @author dewyze
 */
public class ActivitiesManager {

    private static final String TAG = "ActivitiesManager";
    private static Stack<Activity> mActivityStack;
    private static ActivitiesManager mActivitiesManager;

    private ActivitiesManager() {

    }

    //初始货Activity堆管理
    public static ActivitiesManager getInstance() {
        if (null == mActivitiesManager) {
            mActivitiesManager = new ActivitiesManager();
            if (null == mActivityStack) {
                mActivityStack = new Stack<Activity>();
            }
        }
        return mActivitiesManager;
    }

    //获得保存的Activity数量
    public int stackSize() {
        return mActivityStack.size();
    }

    //获得当前的Activity
    public Activity getCurrentActivity() {
        Activity activity = null;

        try {
            activity = mActivityStack.lastElement();
        } catch (Exception e) {
            return null;
        }

        return activity;
    }

    //退出当前Activity
    public void popActivity() {
        Activity activity = mActivityStack.lastElement();
        if (null != activity) {
            LogUtils.i(TAG, "popActivity-->"
                    + activity.getClass().getSimpleName());
            activity.finish();
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    //退出当前指定的Activity
    public void popActivity(Activity activity) {
        if (null != activity) {
            LogUtils.i(TAG, "popActivity-->"
                    + activity.getClass().getSimpleName());
            // activity.finish();
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    //添加Activity
    public void pushActivity(Activity activity) {
        mActivityStack.add(activity);
        LogUtils.i(TAG, "pushActivity-->" + activity.getClass().getSimpleName());
    }

    public void popAllActivities() {
        while (!mActivityStack.isEmpty()) {
            Activity activity = getCurrentActivity();
            if (null == activity) {
                break;
            }
            activity.finish();
            popActivity(activity);
        }
    }

    //关闭所有的Activity
    public void popSpecialActivity(Class<?> cls) {
        try {
            Iterator<Activity> iterator = mActivityStack.iterator();
            Activity activity = null;
            while (iterator.hasNext()) {
                activity = iterator.next();
                if (activity.getClass().equals(cls)) {
                    activity.finish();
                    iterator.remove();
                    activity = null;
                }
            }
        } catch (Exception e) {

        }
    }

    public void peekActivity() {
        for (Activity activity : mActivityStack) {
            if (null == activity) {
                break;
            }
            LogUtils.i(TAG, "peekActivity()-->"
                    + activity.getClass().getSimpleName());
        }
    }

}
