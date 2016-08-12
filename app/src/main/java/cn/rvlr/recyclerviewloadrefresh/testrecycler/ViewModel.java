package cn.rvlr.recyclerviewloadrefresh.testrecycler;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import cn.rvlr.recyclerviewloadrefresh.BR;
import cn.rvlr.recyclerviewloadrefresh.R;
import me.tatarka.bindingcollectionadapter.ItemView;

/**
 *FragmentReceiver的Model对象
 */
public class ViewModel {
    private final boolean checkable;



    //要绑定的数据对象
    public final ObservableList<Item> items = new ObservableArrayList<>();


    //初始化列表数据
    public ViewModel(boolean checkable) {
        this.checkable = checkable;
        for (int i = 0; i <10; i++) {
            items.add(new Item(i, checkable,"item+"+i));
        }
    }
    /**
     * ItemView of a single type，添加recycle的item。xml文件
     */
    public final ItemView singleItemView = ItemView.of(BR.item, R.layout.test_recycle_view_item);



    //添加列表数据
    public void addItem() {
        items.add(new Item(items.size(), checkable,"item"));
    }

    //删除列表数据
    public void removeItem() {
        if (items.size() > 1) {
            items.remove(items.size() - 1);
        }
    }



}
