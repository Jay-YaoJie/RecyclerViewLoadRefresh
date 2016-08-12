package cn.rvlr.recyclerviewloadrefresh.testrecycler;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.rvlr.recyclerviewloadrefresh.BR;
import cn.rvlr.recyclerviewloadrefresh.R;

/**
 * 保存的列表对象（实体数据对象）
 */
public class Item extends BaseObservable {
    public final boolean checkable;
    @Bindable
    private int index;
    @Bindable
    private boolean checked;
    @Bindable
    private String item;

    public Item(int index, boolean checkable, String item) {
        this.index = index;
        this.checkable = checkable;
        this.item = item;
    }

    public int getIndex() {
        return index;
    }

    public boolean isChecked() {
        return checked;
    }

    //长按选择，长按取消
    public boolean onToggleChecked(View v) {
        //修改当前选择的item值
        if (!checkable) {
            return false;
        }
        checked = !checked;
        notifyPropertyChanged(BR.checked);
        return true;
    }

    public String getItem() {
        return item;
    }

    //添加一个点击事件
    public void onClick(View v) {
        if (v.getId() == R.id.item2) {
            Log.d("获取当前index",index+"");
            item = "你点击了当前View"+index;
            //使用BR查找刷新当前页面
            notifyPropertyChanged(BR.item);
        }
    }
}
