package www.hbj.cloud.baselibrary.ngr_library.net;


import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import www.hbj.cloud.baselibrary.ngr_library.Config;
import www.hbj.cloud.baselibrary.ngr_library.utils.LogUtils;
import www.hbj.cloud.baselibrary.ngr_library.utils.OtherUtils;
import www.hbj.cloud.baselibrary.ngr_library.utils.TextUtil;

/**
 * @author zw
 * @date 2019/12/25.
 * <p>
 * email：861126420@qq.com
 * description：
 */
public class RetrofitClient {

    private static volatile RetrofitClient instance;
    public static String IMG_URL = "https://aimpcdn.app307.com/";
    public static String IMG_URL_OSS = "https://mkd-cesi.oss-cn-beijing.aliyuncs.com";
    public static String baseUrl = "https://api.yy.ttypapp.com/?app_key=1079298169&client=&format=json&partner_id=top-sdk-app-20170903&sign=F88EC2099A1328E93A9F51F2716E17D1&sign_method=md5&timestamp=1577441109455&v=1.0";
    public static String baseUrlForApp307 = "https://csxcx.app307.com/";
    public static String baseUrlForApp307New = "https://csxcx.app307.com/";
    public static Retrofit mRetrofit;
    public static Retrofit mRetrofitForApp307;
    public static Retrofit mRetrofitForApp307New;
    public static final long TIMEOUT = 20;


    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    /**
     * 设置Header
     *
     * @return
     */
    private Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
//                Request original = chain.request();
//                RequestBody requestBody= original.body();
//                TreeMap<String,String> map=URLRequest(original.url().url().toString());
//                Request.Builder requestBuilder = original.newBuilder()
//                        //添加Token
//                        .addHeader("X-Cusmall-Token",  AppSession.cusmallToken)
//                        .addHeader("X-Cusmall-Version", OtherUtils.getVersionOfApp())
//                        .addHeader("X-Cusmall-Origin", "8");//	1:天淘微信小程序，2:天淘支付宝小程序，3:天淘ios , 4:天淘Android ,5:神鸟微信小程序 , 6:神鸟支付宝小程序,7:神鸟IOS,8:神鸟Android
//                Request request = requestBuilder.build();



                Request original = chain.request();
                //获取当前时间戳
                String time = System.currentTimeMillis() + "";
                //验签 请求参数集合有序
                TreeMap<String, String> bodyMap = new TreeMap<>();
                //判断是否是表单提交
                String method = original.method();
                StringBuilder sb = new StringBuilder();
                if ("POST".equals(method)) {
                    if (original.body() instanceof FormBody) {
                        FormBody body = (FormBody) original.body();
                        for (int i = 0; i < body.size(); i++) {
                            if (!TextUtils.isEmpty(body.encodedValue(i))) {
                                bodyMap.put(body.encodedName(i), body.encodedValue(i));
                            }
                        }
                    }
                    else {
                        bodyMap = URLRequest(original.url().url().toString());
                    }
                }
                bodyMap.put("X-Cusmall-Timestamp", time);
                for (Map.Entry<String, String> stringStringEntry : bodyMap.entrySet()) {
                    sb.append(stringStringEntry.getKey())
                            .append("=")
                            .append(stringStringEntry.getValue())
                            .append(",");
                }
                sb.delete(sb.length() - 1, sb.length());
                LogUtils.w(sb.toString() + "mkd-api-sign-key");
                //MD5加密 验签字符串
                String Sign = TextUtil.stringToMD5(sb.toString() + "mkd-api-sign-key");
                Request.Builder requestBuilder = original.newBuilder()
                        //添加Token
                        .addHeader("X-Cusmall-Version", OtherUtils.getVersionOfApp())
                        .addHeader("X-Cusmall-Timestamp", time + "")
                        .addHeader("X-Cusmall-Sign", Sign)
                        .addHeader("X-Cusmall-Origin", "8");//猩家来源 IOS 传9, 安卓传10	1:天淘微信小程序，2:天淘支付宝小程序，3:天淘ios , 4:天淘Android ,5:神鸟微信小程序 , 6:神鸟支付宝小程序,7:神鸟IOS,8:神鸟Android

                Request request = requestBuilder.build();



                return chain.proceed(request);
            }
        };

    }

    /**
     * 设置拦截器
     *
     * @return
     */
    private Interceptor getInterceptor() {

        LoggingInterceptor interceptor = new LoggingInterceptor();
        //显示日志
        return interceptor;
    }

    public void intitRetrofit() {
        File cacheFile = new File(Config.mExternalCacheDir, "cache");
//缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
//创建缓存对象
        Cache cache = new Cache(cacheFile, cacheSize);
        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient().newBuilder()
                .cache(cache)
                //设置Header
                .addInterceptor(getHeaderInterceptor())
            //    .addInterceptor(getLoginIntercepter())
                //设置拦截器
                .addInterceptor(getInterceptor())
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                //设置网络请求的Url地址
                .baseUrl(baseUrl)
                //设置数据解析器
                .addConverterFactory(GsonPHPConverterFactory.create())
                //设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建—— 网络请求接口—— 实例
    }

    public void intitRetrofitForApp307() {
        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient().newBuilder()
                //设置Header
                .addInterceptor(getHeaderInterceptor())
              //  .addInterceptor(getLoginIntercepter())
                //设置拦截器
                .addInterceptor(getInterceptor())
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        mRetrofitForApp307 = new Retrofit.Builder()
                .client(client)
                //设置网络请求的Url地址
                .baseUrl(baseUrlForApp307)
                //设置数据解析器
                .addConverterFactory(GsonJavaConverterFactory.create())
                //设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建—— 网络请求接口—— 实例
    }

    /**
     * 登录拦截器
     * @return
     */
    public Interceptor getLoginIntercepter(){
        return new LoginIntecepter();
    }
    public void intitRetrofitForApp307New() {
        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient().newBuilder()
                //设置Header
                .addInterceptor(getHeaderInterceptor())
              //  .addInterceptor(getLoginIntercepter())
                //设置拦截器
                .addInterceptor(getInterceptor())
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        mRetrofitForApp307New = new Retrofit.Builder()
                .client(client)
                //设置网络请求的Url地址
                .baseUrl(baseUrlForApp307New)
                //设置数据解析器
                .addConverterFactory(GsonJavaConverterFactory.create())
                //设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建—— 网络请求接口—— 实例
    }


    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofit.create(service);
    }

    public <T> T createAppRetrofit(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofitForApp307.create(service);
    }

    public <T> T createAppRetrofitNew(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofitForApp307New.create(service);
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static TreeMap<String, String> URLRequest(String URL) {
        TreeMap<String, String> mapRequest = new TreeMap<>();

        String[] arrSplit = null;

        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                if (!TextUtils.isEmpty(arrSplitEqual[1])) {
                    mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
                }

            }
//            else {
//                if (arrSplitEqual[0] != "") {
//                    //只有参数没有值，不加入
//                    mapRequest.put(arrSplitEqual[0], "");
//                }
//            }
        }
        return mapRequest;
    }


    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }
}
