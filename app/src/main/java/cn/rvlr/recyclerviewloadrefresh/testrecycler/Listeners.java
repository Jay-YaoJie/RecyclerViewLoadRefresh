package cn.rvlr.recyclerviewloadrefresh.testrecycler;

import android.view.View;

/**
 * 最下面的两个点击事件，如果点击事件单独新建一个类对要在创建view的时候绑定
 * <p>
 * //点击事件如果是单独写的类对象，则先设置点击事件的类
 * binding.setListeners(new Listeners(viewModel));
 * //执行
 * binding.executePendingBindings();
 */
public class Listeners {
    private ViewModel viewModel;

    public Listeners(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    //添加的点击事件
    public final View.OnClickListener addItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onAddItem();
        }
    };

    //删除的点击事件
    public final View.OnClickListener removeItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onRemoveItem();
        }
    };

    public void onAddItem() {
        viewModel.addItem();
    }

    public void onRemoveItem() {
        viewModel.removeItem();
    }
}
