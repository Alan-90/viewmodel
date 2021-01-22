package www.hbj.cloud.baselibrary.common;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import www.hbj.cloud.platform.BaseApplication;
import www.hbj.cloud.baselibrary.common.service.ApiService;
import www.hbj.cloud.baselibrary.common.utils.LogUtils;
import www.hbj.cloud.baselibrary.common.utils.NetWorkUtils;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class ApiRetrofit {

    private static ApiRetrofit mApiRetrofit;
    private final Retrofit mRetrofit;
    private OkHttpClient mClient;
    private ApiService mApiService;

    /*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    Interceptor mCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            if (!NetWorkUtils.isNetworkAvailable()) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetworkAvailable()) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 2; //  设缓存有效期为两天
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    /**
     * 转换地址
     */
    Interceptor mSecretInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request oldRequest = chain.request();
            HttpUrl oldHttpUrl = oldRequest.url();

            Request.Builder builder = oldRequest.newBuilder();
            //交易登录成功后，交易页面其他的接口都需要的参数
            String urlName = oldRequest.header("baseUrl");

            System.out.println("baseUrl == :" + urlName);
            if (!TextUtils.isEmpty(urlName)) {
                HttpUrl baseURL = null;
                if ("api".equals(urlName)) {
//                    baseURL = HttpUrl.parse(BuildConfig.API_HOST + "uc");
                } else if ("ex".equals(urlName)) {
//                    baseURL = HttpUrl.parse(BuildConfig.API_HOST);
                }

//                if (BuildConfig.API_HOST.equals(AppConstant.API_HOST_TEST)) {//测试环境
//                    //本地保存的api全部用测试的，测试环境下网页界面是测试api
//                    if ("api".equals(urlName)) {//api接口
//                        baseURL = HttpUrl.parse(AppConstant.API_HOST_TEST);
//                    }
//
//                } else if (BuildConfig.API_HOST.equals(AppConstant.API_HOST)) {//正式环境
//                    if ("api".equals(urlName)) {//api接口
//                        baseURL = HttpUrl.parse(AppConstant.API_HOST);
//                    }
//                }
                LogUtils.e("newUrl == "+ baseURL.host());
                //重建新的HttpUrl，需要重新设置的url部分
                HttpUrl newHttpUrl = oldHttpUrl.newBuilder()
                        .scheme(baseURL.scheme())//http协议如：http或者https
                        .host(baseURL.host())//主机地址
                        .port(baseURL.port())//端口
                        .build();
                //获取处理后的新newRequest
                Request newRequest = builder.url(newHttpUrl).build();
                return chain.proceed(newRequest);
            } else {
                return chain.proceed(oldRequest);
            }

        }
    };

    /**
     * 增加头部信息的拦截器
     */
    Interceptor mHeaderInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Content-Type", "application/json; charset=UTF-8");

//            builder.addHeader("platform", "Android");
//            builder.addHeader("authorization_token", UserCache.userToken());
//            builder.addHeader("authorization_username", UserCache.userName());
//            builder.addHeader("authorization_uid", UserCache.uid());

//            builder.addHeader("version_code", AppUtils.getVersionName());


            return chain.proceed(builder.build());

        }
    };

    /**
     * 请求访问quest和response拦截器
     */
    Interceptor mLogInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            //获取请求体内容
            RequestBody requestBody = request.body();
            String bodyString = "";
            if (requestBody != null) {
                Buffer buffer = new Buffer();
                try {
                    requestBody.writeTo(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //编码设为UTF-8
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(Charset.forName("UTF-8"));
                }
                bodyString = buffer.readString(charset);
            }

            try {
                JSONObject jsonObject = new JSONObject(content);

                LogUtils.e("----------Request Start----------------");
                LogUtils.e("| " + request.toString());
                LogUtils.e("| " + request.headers().toString());
                LogUtils.e("| 入参body:" + bodyString);

//                KLog.json(jsonObject.toString());
                LogUtils.e("----------Request End:" + duration + "毫秒----------");
            } catch (JSONException e) {
                e.printStackTrace();
                LogUtils.e(request.toString());
            }

            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    };

    public ApiRetrofit() {
        //cache url
        File httpCacheDirectory = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//请求/响应行 + 头 + 体

        mClient = new OkHttpClient.Builder()
                .addInterceptor(mHeaderInterceptor)//添加头部信息拦截器
                .addInterceptor(loggingInterceptor) //添加log拦截器
                .addInterceptor(mLogInterceptor)    //交易弹窗
//                .addInterceptor(mSecretInterceptor) //地址拦截
                .cache(cache)
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(2 * 60 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(2 * 60 * 1000, TimeUnit.MILLISECONDS)
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        mRetrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl(AppCommon.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    public static ApiRetrofit getInstance() {
        if (mApiRetrofit == null) {
            synchronized (Object.class) {
                if (mApiRetrofit == null) {
                    mApiRetrofit = new ApiRetrofit();
                }
            }
        }
        return mApiRetrofit;
    }


    public ApiService getApiService() {
        return mApiService;
    }
}
