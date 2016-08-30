package cn.rvlr.recyclerviewloadrefresh.utils.photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class Img {
    @Expose
    @SerializedName("src")
    private String src;
    @Expose
    @SerializedName("thumb")
    private String thumb;
    //保存本地图片路径
    private String picPath;
    //保存传入的类型
    private String type;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
