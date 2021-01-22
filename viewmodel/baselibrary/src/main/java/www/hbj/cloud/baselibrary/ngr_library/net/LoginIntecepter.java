package www.hbj.cloud.baselibrary.ngr_library.net;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

/**
 * @Author loy
 * 创建时间：2020/11/14 17:39
 * description: 创建登录拦截器
 */
public class LoginIntecepter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody body = response.body();
        BufferedSource source = body.source();
        source.request(Long.MAX_VALUE);
        String respString = source.buffer().clone().readString(Charset.defaultCharset());
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(respString);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (jsonObject!=null&&jsonObject.has("ret")){
            int ret = 0;
            try {
                ret = jsonObject.getInt("ret");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (ret==-4000){
            }
        }
        return response;
    }

}
