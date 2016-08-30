package cn.rvlr.recyclerviewloadrefresh.data.http;

import cn.rvlr.recyclerviewloadrefresh.data.constants.HttpResult;
import cn.rvlr.recyclerviewloadrefresh.utils.photo.Img;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 使用
 */
public class HpptApi extends HttpServiceGenerator {
    public interface ApiManager{
        //充值（key）加密过后的key值
        @GET("/wallet/v1/unifiedorder")
        Call<HttpResult> getPayment(@Query("key") String AESkey);

    }
    //使用的apid地址为：API_BASE_URL
    private static final ApiManager apiManager = getRetrofit().create(ApiManager.class);

    // 取验证码的接口
    public static Call<HttpResult> getPayment(String loginName,String password) {
        return apiManager.getPayment( loginName);
    }

    private interface ApiManagerServiceImg {
        //上传图片
        @Multipart
        @POST("/image/upload")
        Call<HttpResult<Img>> getImgUploading(@Part("image\"; filename=\"filename.png") RequestBody file);
    }

    private static final ApiManagerServiceImg apiManagerImg = getRetrofit().create(ApiManagerServiceImg.class);

    //用户认证
    public static Call<HttpResult<Img>> getImgUploading(RequestBody file) {
        return apiManagerImg.getImgUploading(file);
    }

}
