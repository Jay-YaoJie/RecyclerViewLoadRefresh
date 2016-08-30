package cn.rvlr.recyclerviewloadrefresh.server.callback;
import cn.rvlr.recyclerviewloadrefresh.server.constants.HttpConstants;
import cn.rvlr.recyclerviewloadrefresh.server.constants.HttpResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 回调网络公共处理
 */

public abstract class HttpCallback<T> implements Callback<HttpResult> {
    @Override
    public void onResponse(Call<HttpResult> call, Response<HttpResult> response) {
        if (response==null){
            onError(call, "服务忙,请稍后重试");
            return;
        }
        HttpResult baseEntity = response.body();
        if(baseEntity == null){
            onError(call, "服务忙,请稍后重试");
            return;
        }
        if (HttpConstants.SUCCESS_CODE == baseEntity.getStatus()) {
            //如果请求数据成功，则返回的token值保存到本地，保存之前先查询一下本地的token是否相同，如果相同则可以不用保存
//            if(response.headers().get(HttpConstants.TOKEN) != null && !StringUtils.equals(SharedPreferencesUtils.getToken(),response.headers().get(HttpConstants.TOKEN))){
//               // SharedPreferencesUtils.setToken(response.headers().get(HttpConstants.TOKEN));
//            }
            onSuccess(call, (T)baseEntity.getContent());
        }else if(HttpConstants.LOGIN_CODE == baseEntity.getStatus()){
            //如果返回999则可以退出登录或着还有其他码值时
//            Intent intent = new Intent(AndroidApplication.getInstance().getCurrentActivity(), LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//            ActivityNavigator.navigateTo(LoginActivity.class);

        }else{
            onError(call, baseEntity.getMessage());
        }
    }

    /**
     * 调用成功
     * @param call
     * @param response
     */
    protected abstract void onSuccess(Call<HttpResult> call, T response);

    /**
     * 返回错误
     * @param call
     * @param msg
     */
    protected abstract void onError(Call<HttpResult> call, String msg);

}
