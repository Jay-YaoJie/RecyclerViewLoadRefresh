package cn.rvlr.recyclerviewloadrefresh.mvvmframework.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.util.SparseArray;
import android.view.View;

import cn.rvlr.recyclerviewloadrefresh.BR;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;


/**
 * 列表类型通用的ViewModel基类
 */
public abstract class BaseListViewModel<T> extends BaseViewModel {
    private int firstPage = 1;
    //分页页码
    private int page = firstPage;
    //分页每页Item数量
    private int pageSize = 10;
    private final ObservableBoolean refreshing = new ObservableBoolean(false);
    private final ObservableBoolean hasMore = new ObservableBoolean(false);
    private final ObservableBoolean loadingMore = new ObservableBoolean(false);
    //特殊的item集合，传入对应的 position位置和 layoutId,把原本默认的item layout 转为 指定的 layout，item数据不是插进 items里面额外的数据，而是原本存在的数据。
    private final SparseArray<Integer> specialViews = new SparseArray<>();
    //header集合，layout 和 Object，Object作为额外的 item 插进 items
    private final ArrayList<HeaderFooterMapping> headers = new ArrayList<>();
    //footer集合，layout 和 Object，Object作为额外的 item 插进 items
    private final ArrayList<HeaderFooterMapping> footers = new ArrayList<>();
    //正常的item对应的layout
    private int itemLayout;
    //控制loading状态只有一次,对于列表的loading概念，就是首次加载数据，其余加载分别是刷新和更多
    private boolean once = false;
    /**
     * 要绑定的数据对象
     */
    protected final ObservableList<Object> items = new ObservableArrayList<>();

    /**
     * ItemView of a single type，添加recycle的item。xml文件
     */
    public BaseListViewModel(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    private ItemViewSelector<Object> itemViews = new ItemViewSelector<Object>() {
        @Override
        public void select(ItemView itemView, int position, Object item) {
            int layout = itemLayout;
            //如果符合header条件则使用header中object对应的layout
            if (position < headers.size()) {
                layout = headers.get(position).getLayout();
            }
            //如果符合footer条件则使用footer中object对应的layout
            else if (position >= items.size() - footers.size()) {
                int footerPosition = footers.size() - (items.size() - position);
                layout = footers.get(footerPosition).getLayout();
            }

            Integer layoutRes = specialViews.get(position);
            //如果specialViews中能匹配位置key则使用对应的value，否则使用正常的itemLayout
            //注意 无论是 itemLayout 还是 specialViews 中对应的 value Layout，都必须有 viewModel 属性
            itemView.setBindingVariable(BR.viewModel)
                    .setLayoutRes((layoutRes == null) ? layout : layoutRes);
        }

        // Only needed if you are using in a ListView
        @Override
        public int viewTypeCount() {
            return specialViews.size() + 1;
        }
    };

    /**
     * 刷新数据
     */
    public void onListRefresh() {
//      if(refreshing.get())return;
        refreshing.set(true);
        //把分页配置还原成加载第一页状态
        page = firstPage;
        hasMore.set(false);
        loadingMore.set(false);
        if (!once) {
            //在第一次加载页面数据，显示页面   加载中。。。。。
            setStatusLoading(true);
        }
        // onLoadListHttpRequest().enqueue(callBack);
    getData(setData());
    }

    /**
     * 加载数据
     */
    public void onListLoadMore() {
        //判断是否已经在进行加载更多 或 没有更多了，是则直接返回等待加载完成。
        if (loadingMore.get() || !hasMore.get()) {
            return;
        }
        //刷新中也直接返回不加载更多
        if (refreshing.get()) {
            return;
        }
        //分页增加
        page++;
        loadingMore.set(true);

        //  onLoadListHttpRequest().enqueue(callBack);
        getData(setData());
    }

    /**
     * 加载数据，初始化数据也要调用止方法
     */
    public abstract List<T> setData();

    private void getData(List<T> resultData) {

        setStatusError(false);
          setStatusNetworkError(false);

            if(isFirstPage()) {
                items.clear();
                //把header和footer的数据先加回来
                reloadHeaderAndFooter();
            }
            if(resultData != null) {
                items.addAll(items.size() - footers.size(),resultData);
                //如果获取的数据数量比申请的数量少 则为没有更多了
                hasMore.set(resultData.size() < pageSize ? false : true);
            }



            once = true;
            setStatusLoading(false);//不需要在显示  加载中。。。。。
       // setStatusError(true);//出错后的显示
      //  setStatusNetworkError(true);//网络异常后的显示
            if(!getStatusError().get()&&!getStatusNetworkError().get())
                setStatusEmpty(items.isEmpty());

            if(isFirstPage())
                refreshing.set(false);
            else
                loadingMore.set(false);

            onLoadListComplete();

    }

//    public HttpServiceCallBack callBack = new HttpServiceCallBack<List<T>>() {
//
//        @Override
//        public void onHttpSuccess(List<T> resultData, String msg) {
//            setStatusError(false);
    //           setStatusNetworkError(false);
//
//            if(isFirstPage()) {
//                items.clear();
//                //把header和footer的数据先加回来
//                reloadHeaderAndFooter();
//            }
//            if(resultData != null) {
//                items.addAll(items.size() - footers.size(),resultData);
//                //如果获取的数据数量比申请的数量少 则为没有更多了
//                hasMore.set(resultData.size() < pageSize ? false : true);
//            }
//        }
//
//        @Override
//        public void onHttpFail(int code, String msg) {
//            setStatusError(true);
//        }
//
//        @Override
//        public void onNetWorkError() {
//            setStatusNetworkError(true);
//        }
//
//        @Override
//        public void onHttpComplete() {
//            once = true;
//            setStatusLoading(false);
//            if(!getStatusError().get()&&!getStatusNetworkError().get())
//                setStatusEmpty(items.isEmpty());
//
//            if(isFirstPage())
//                refreshing.set(false);
//            else
//                loadingMore.set(false);
//
//            onLoadListComplete();
//        }
//    };

    /**
     * 可选重写，通常用于加载完成后判定是否有更多而进行加入或减去footer
     */
    public void onLoadListComplete() {}
    /**
     * 重新加载header和footer中的对象到items中
     */
    private void reloadHeaderAndFooter() {
        for (int i = 0; i < headers.size(); i++) {
            items.add(headers.get(i).getObject());
        }
        for (int i = 0; i < footers.size(); i++) {
            items.add(footers.get(i).getObject());
        }
    }

    /**
     * 网络请求
     *
     * @return
     */
    //  public abstract Call<HttpResult<List<T>>> onLoadListHttpRequest();

    /**
     * 添加列表的item点击事件
     *
     * @param v
     * @param position
     * @param itemView
     * @param item
     */
    public abstract void onItemClick(View v, int position, View itemView, T item);

    public boolean isFirstPage() {
        return page == firstPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ObservableBoolean getRefreshing() {
        return refreshing;
    }
    public ObservableBoolean getHasMore() {
        return hasMore;
    }

    public ObservableBoolean getLoadingMore() {
        return loadingMore;
    }

    public ObservableList<Object> getItems() {
        return items;
    }

    public int getItemLayout() {
        return itemLayout;
    }

    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    /**
     * 设置特殊的样式
     *
     * @param position item的位置
     * @param layout   改为对应的layout
     */
    public void setSpecialView(int position, int layout) {
        specialViews.append(position, layout);
    }

    /**
     * 清除所有特殊样式
     */
    public void clearSpecialViews() {
        specialViews.clear();
    }

    /**
     * 清除指定位置的样式
     *
     * @param position
     */
    public void removeSpecialView(int position) {
        specialViews.remove(position);
    }

    public ItemViewSelector<Object> getItemViews() {
        return itemViews;
    }

    /**
     * 加入Header
     *
     * @param layout
     * @param o      对应ViewModel的object
     */
    public void addHeader(int layout, Object o) {
        headers.add(new HeaderFooterMapping(layout, o));
        items.add(headers.size() - 1, o);
    }

    /**
     * 加入Footer
     *
     * @param layout
     * @param o      对应ViewModel的object
     */
    public void addFooter(int layout, Object o) {
        footers.add(new HeaderFooterMapping(layout, o));
        items.add(o);
    }

    /**
     * 移除 layout 对应的 Header
     *
     * @param layout
     * @param o
     */
    public void removeHeader(int layout, Object o) {
        headers.remove(layout);
        items.remove(o);
    }

    /**
     * 移除Footer
     *
     * @param layout
     * @param o
     */
    public void removeFooter(int layout, Object o) {
        footers.remove(layout);
        items.remove(o);
    }

    /**
     * 移除所有 Header
     */
    public void removeHeaders() {
        for (HeaderFooterMapping headerFooterMapping : headers)
            items.remove(headerFooterMapping.getObject());
        headers.clear();
    }

    /**
     * 移除所有 Footer
     */
    public void removeFooters() {
        for (HeaderFooterMapping headerFooterMapping : footers)
            items.remove(headerFooterMapping.getObject());
        footers.clear();
    }

    public int getHeaderSize() {
        return headers.size();
    }

    public int getFooterSize() {
        return footers.size();
    }

}

