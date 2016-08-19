package cn.rvlr.recyclerviewloadrefresh.server;

import com.google.gson.GsonBuilder;


import java.util.concurrent.TimeUnit;

import cn.rvlr.recyclerviewloadrefresh.server.utils.HttpHeadInterceptor;


import okhttp3.OkHttpClient;

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


}
