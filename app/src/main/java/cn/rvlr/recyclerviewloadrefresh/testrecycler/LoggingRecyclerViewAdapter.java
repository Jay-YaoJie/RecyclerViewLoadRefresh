package cn.rvlr.recyclerviewloadrefresh.testrecycler;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;

/**
 *列表适配器，也可以不用重新创建，直接在xml中使用（ app:adapter='@{"me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter"}'）
 */
public class LoggingRecyclerViewAdapter<T> extends BindingRecyclerViewAdapter<T> {
    public static final String TAG = "RecyclerView";

    public LoggingRecyclerViewAdapter(@NonNull ItemViewArg<T> arg) {
        super(arg);
    }

    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup viewGroup) {
        ViewDataBinding binding = super.onCreateBinding(inflater, layoutId, viewGroup);
        Log.d(TAG, "created binding: " + binding);
        return binding;
    }

    //滑动的时候显示最后的一个item向上滑动也记录了
    @Override
    public void onBindBinding(ViewDataBinding binding, int bindingVariable, @LayoutRes int layoutRes, int position, T item) {
        super.onBindBinding(binding, bindingVariable, layoutRes, position, item);
        Log.d(TAG, "bound binding: " + binding + " at position: " + position+"   getItemCount() "+   getItemCount());
        //如果列表没有超过屏幕的长度，则不会调用止方法
    }

}
