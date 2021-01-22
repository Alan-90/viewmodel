package www.hbj.cloud.baselibrary.ngr_library;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 本应用中用到的配置项定义
 *
 * @author Administrator
 */
public class Config {
    //Configuration
    public static int TOAST_TIME_1000 = 1000;
    public static int TOAST_TIME_2000 = 2000;
    public static int TOAST_TIME_3000 = 3000;

    //此版本是否需要引导页
    public static boolean needGuidePage = false;
    public static Context mContext;
    public static String mExternalCacheDir = "";
    public static DevelopmentEnvironment mEnvironment;
    public final static String ALIAUTH = "apiname=com.alipay.account.auth&app_id=2021001101687890&app_name=mc&auth_type=AUTHACCOUNT&biz_type=openservice&method=alipay.open.auth.sdk.code.get&pid=2088731198458349&product_id=APP_FAST_LOGIN&scope=kuaijie&sign_type=RSA2&target_id=20200307&sign=dk27Bjg9KA%2fntnGQjjuEN%2beMZ2GOW0G0I1E1gqX058s8QJ7qzZxQ1LzZ14sgeHnNOwahYaVfg0K%2baHBXgeNKo%2b12ycQb9LkoosOlr2nuGSIajvJBlBxbyiH26WcX8TEbR%2bG8zNs6MYsuoeCY9s2ZZrF41JwiC5xm26AL6PFp941aFLW9xCLn7VekwvsJZjBKMWtyMsp%2bUWN3GquuBNyd5yRM%2fU3SeDZdmoR1DJkuYULdRQGs9tgQfl69XVhyXDiWsoYM6P7zBb2LT5M62cjjcYa0FmXd2nqVq8h4XDQp%2bwe%2fe5V0o4brO6GIEZwbU6ID18SfVl2NINsskRWKf6SImQ%3d%3d";
    public final static String TerminalCode = "CT31";
    public static String YOUMENG_ALIAN = "snjk_test";
    public static String TUANYOU_SECRET_URL = "https://mcs.czb365.com/";
    public static String TUANYOU_URL = "https://open.czb365.com/";
    public static String EVENTLIVE_URL = "http://h5.cs.ttypapp.com";
    public static String EXPRESS_DELIVERY = "http://h5.cs.ttypapp.com/appH5/logistics.html";


    static {
//        SnjkTest
        mEnvironment = DevelopmentEnvironment.SnjkTest;
        if (mEnvironment == DevelopmentEnvironment.SnjkRelease) {
            YOUMENG_ALIAN = "snjk";
            EXPRESS_DELIVERY = "http://h5.ttmb.aiboom.cn/appH5/logistics.html";
            EVENTLIVE_URL = "http://h5.ttmb.aiboom.cn/appH5/marketing/groupbuying";
        }
        if (mEnvironment == DevelopmentEnvironment.SnjkTest) {
            EXPRESS_DELIVERY = "http://h5.cs.ttypapp.com/appH5/logistics.html";
            EVENTLIVE_URL = "http://h5.cs.ttypapp.com/appH5/marketing/groupbuying";
            YOUMENG_ALIAN = "snjk_test";
//            TUANYOU_SECRET_URL="https://test-mcs.czb365.com/";
//            TUANYOU_URL="https://test-open.czb365.com/";
        }
    }

    //永久文件缓存地址
    public static String PERSIST_DIR = "";


    //微信分享appkey
    public static String WEIXIN_API = "";

    //信鸽ACCESS_ID
    public static long XG_ACCESSID;

    //信鸽ACCESS_KEY
    public static String XG_ACCESSKEY = "";


    public Config(Context context) {


        mContext = context.getApplicationContext();
        PERSIST_DIR = "/data/data/"
                + mContext.getPackageName() + "/persist";
        mExternalCacheDir = Environment.getExternalStorageDirectory() + "/" + context.getPackageName();
        //建立缓存文件夹以及不会被清除的文件夹
        File file = new File(mExternalCacheDir);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                // TODO: handle exception  
            }
        }

        file = new File(PERSIST_DIR);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                // TODO: handle exception  
            }
        }

    }
}

