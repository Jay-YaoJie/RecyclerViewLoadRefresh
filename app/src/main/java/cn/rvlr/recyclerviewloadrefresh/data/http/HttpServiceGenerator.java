package cn.rvlr.recyclerviewloadrefresh.data.http;

import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.concurrent.TimeUnit;


import cn.rvlr.recyclerviewloadrefresh.data.constants.HttpConstants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;


import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 在请求网络时的配置
 */
public abstract class HttpServiceGenerator {
    //请求网络时的地址
    public static final String API_BASE_URL = "http://apis.baidu.com/apistore/";


    private static final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private static final HttpHeadInterceptor httpHeadInterceptor = new HttpHeadInterceptor();
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(httpHeadInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).build();
    private static GsonBuilder gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
    private static final Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson.create()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();


    protected static Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     *  自动添加token 和 user-agent拦截器,//使用OkHttp拦截器可以指定需要的header给每一个Http请求
     *
     */
    public static final class HttpHeadInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            final Request requestByHandle = originalRequest.newBuilder()
                    .removeHeader(HttpConstants.USER_AGENT)
                    .removeHeader(HttpConstants.TOKEN)
                    .addHeader(HttpConstants.USER_AGENT, HttpConstants.USER_AGENT_VALUE)
                    .addHeader(HttpConstants.TOKEN,"传入当前保存的token")
                    .build();
            return chain.proceed(requestByHandle);
        }
    }
}
