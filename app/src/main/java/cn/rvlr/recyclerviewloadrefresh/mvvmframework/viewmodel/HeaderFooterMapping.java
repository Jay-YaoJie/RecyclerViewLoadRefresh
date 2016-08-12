package cn.rvlr.recyclerviewloadrefresh.mvvmframework.viewmodel;

/**
 *View视图对象，头部/底部对象
 */
public class HeaderFooterMapping {
    private int layout;
    private Object object;

    public HeaderFooterMapping(int layout, Object object) {
        this.layout = layout;
        this.object = object;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
