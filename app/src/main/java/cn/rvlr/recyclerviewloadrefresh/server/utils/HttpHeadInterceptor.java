package cn.rvlr.recyclerviewloadrefresh.server.utils;

import java.io.IOException;
import cn.rvlr.recyclerviewloadrefresh.server.constants.HttpConstants;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *  自动添加token 和 user-agent拦截器,//使用OkHttp拦截器可以指定需要的header给每一个Http请求
 *
 */
public final class HttpHeadInterceptor implements Interceptor {

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