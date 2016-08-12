package cn.rvlr.recyclerviewloadrefresh.server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/8/10 0010.
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
}
