package cn.rvlr.recyclerviewloadrefresh.network;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.rvlr.recyclerviewloadrefresh.R;
import cn.rvlr.recyclerviewloadrefresh.mvvmframework.viewmodel.BaseRecyclerViewModel;


/**
 * 列表ViewModel对象
 */
public class RecycleModel extends BaseRecyclerViewModel implements View.OnClickListener {
    public RecycleModel() {
        super(R.layout.recycle_view_item);
        setPageSize(10);//设置一次拿100条数据

    }

    public SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            onListRefresh();
        }
    };


    @Override
    public void onItemClick(View v, int position, View itemView, Object item) {//重写父类的item点击事件
        if ("加载更多".equals(item)){
            return;
        }
        Log.d("添加的列表点击事件", "position=" + position + "+++ Object item=((Ticket)item).getSpotName()=" + ((Ticket) item).getSpotName());

    }

    public List<Ticket> setData() {
        List<Ticket> list = new ArrayList<>();
        Ticket ticket = null;
        int page = getPage() *getPageSize();
        if (getPage() > 5) {
            Bundle bundle = new Bundle();
            int code = RecycleBindingActivity.CODE_ITEM;
            bundle.putSerializable("model", "没有更多数据了");
            onViewModelNotify(bundle, code);
            return null;
        }
        for (int i = 0; i <page; i++) {
            ticket = new Ticket();
            /**
             * productId : 923010086
             * spotName : 蓝水河漂流
             * spotAliasName : ["蓝水河"]
             */

            ticket.setProductId("923010086-" + i);
            ticket.setSpotName("蓝水河漂流" + i);
            ticket.setPrice("i+" + i);
            ticket.setDetailInfo("蓝水河漂流" + i + "923010086-" + i);
            List<String> li = new ArrayList<>();
            li.add("蓝水河漂流" + i + "923010086-" + i);
            ticket.setSpotAliasName(li);
            list.add(ticket);
        }
        return list;
    }

    /***
     * 列表的子项点击事件也可以调用止方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        //网络异常,点击重新加载
        onListRefresh();
    }

    /**
     * 可选重写，如果是第一页并且有更多可以加载，则加入footer
     */
    @Override
    public void onLoadListComplete() {
        if (isFirstPage() && getHasMore().get() && getFooterSize() == 0) {
            //加入footer
            addFooter(R.layout.recycle_view_item_loadmore, "加载更多");
        } else if (!getHasMore().get() && getFooterSize() != 0) {
            removeFooters();
        }
    }
}
