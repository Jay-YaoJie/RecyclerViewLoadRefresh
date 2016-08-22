package cn.rvlr.recyclerviewloadrefresh;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class AndroidApplication extends Application {
    private static AndroidApplication instance;

    private Activity mCurrentActivity;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static AndroidApplication getContext() {
        return instance;
    }

    public static AndroidApplication getInstance() {
        return instance;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(@NonNull Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

}
