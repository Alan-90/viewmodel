package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import www.hbj.cloud.baselibrary.ngr_library.Config;

/**
 * @author easygroup
 */
public class OtherUtils {
    private OtherUtils() {
    }

    // 根据年月日计算年龄,birthTimeString:"1994-11-14
    public static int getAgeFromBirthTime(String birth) {
        String data = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 得到当前时间的年、月、日
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayNow = cal.get(Calendar.DATE);
            //得到输入时间的年，月，日
            cal.setTime(date);
            int selectYear = cal.get(Calendar.YEAR);
            int selectMonth = cal.get(Calendar.MONTH) + 1;
            int selectDay = cal.get(Calendar.DATE);
            // 用当前年月日减去生日年月日
            int yearMinus = yearNow - selectYear;
            int monthMinus = monthNow - selectMonth;
            int dayMinus = dayNow - selectDay;
            int age = yearMinus;// 先大致赋值
            if (yearMinus <= 0) {
                age = 0;
            }
            if (monthMinus < 0) {
                age = age - 1;
            } else if (monthMinus == 0) {
                if (dayMinus < 0) {
                    age = age - 1;
                }
            }
            return age;
        }
        return 0;
    }

    public static String getCustomeAgeFromBirthTime(String birth, String format) {
        String customeDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 得到当前时间的年、月、日
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayNow = cal.get(Calendar.DATE);
            //得到输入时间的年，月，日
            cal.setTime(date);
            int selectYear = cal.get(Calendar.YEAR);
            int selectMonth = cal.get(Calendar.MONTH) + 1;
            int selectDay = cal.get(Calendar.DATE);
            // 用当前年月日减去生日年月日
            int yearMinus = yearNow - selectYear;
            int monthMinus = monthNow - selectMonth;
            int dayMinus = dayNow - selectDay;
            int age = yearMinus;// 先大致赋值
            if (yearMinus <= 0) {
                age = 0;
                if (monthMinus <= 0) {
                    if (dayMinus <= 0) {
                        return "1天";
                    } else {
                        return dayMinus + "天";
                    }
                } else {
                    return monthMinus + "月";

                }


            } else {

                if (yearMinus * 12 + monthMinus > 24) {
                    return yearMinus + "岁";
                }

                return (age * 12 + monthMinus) + "月";

            }
        }
        return customeDate;
    }



    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
        }
        return false;
    }

    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    /**
     * @param context if null, use the default format (Mozilla/5.0 (Linux; U;
     *                Android %s) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0
     *                %sSafari/534.30).
     * @return
     */
    public static String getUserAgent(Context context) {
        String webUserAgent = null;
        if (context != null) {
            try {
                Class sysResCls = Class
                        .forName("com.android.internal.R$string");
                Field webUserAgentField = sysResCls
                        .getDeclaredField("web_user_agent");
                Integer resId = (Integer) webUserAgentField.get(null);
                webUserAgent = context.getString(resId);
            } catch (Throwable ignored) {
            }
        }
        if (TextUtils.isEmpty(webUserAgent)) {
            webUserAgent = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) "
                    + "Version/4.0 %sSafari/533.1";
        }

        Locale locale = Locale.getDefault();
        StringBuffer buffer = new StringBuffer();
        // Add version
        final String version = Build.VERSION.RELEASE;
        if (version.length() > 0) {
            buffer.append(version);
        } else {
            // default to "1.0"
            buffer.append("1.0");
        }
        buffer.append("; ");
        final String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language.toLowerCase());
            final String country = locale.getCountry();
            if (country != null) {
                buffer.append("-");
                buffer.append(country.toLowerCase());
            }
        } else {
            // default to "en"
            buffer.append("en");
        }
        // add the model for the release build
        if ("REL".equals(Build.VERSION.CODENAME)) {
            final String model = Build.MODEL;
            if (model.length() > 0) {
                buffer.append("; ");
                buffer.append(model);
            }
        }
        final String id = Build.ID;
        if (id.length() > 0) {
            buffer.append(" Build/");
            buffer.append(id);
        }
        return String.format(webUserAgent, buffer, "Mobile ");
    }

    /**
     * 获取建立缓存文件夹的路径，优先使用t卡，其次/data/data/cache目录
     *
     * @param context
     * @param dirName Only the folder name, not full path.
     * @return app_cache_path/dirName
     */
    public static String getDiskCacheDir(Context context, String dirName) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                cachePath = externalCacheDir.getPath();
            }
        }
        if (cachePath == null) {
            File cacheDir = context.getCacheDir();
            if (cacheDir != null && cacheDir.exists()) {
                cachePath = cacheDir.getPath();
            }
        }

        return cachePath + File.separator + dirName;
    }

    /**
     * 得到当前剩余的磁盘空间
     *
     * @param dir
     * @return
     */
    public static long getAvailableSpace(File dir) {
        try {
            final StatFs stats = new StatFs(dir.getPath());
            return (long) stats.getBlockSize()
                    * (long) stats.getAvailableBlocks();
        } catch (Throwable e) {
            LogUtils.e(e.getMessage(), e);
            return -1;
        }

    }


    /**
     * private static final int STRING_BUFFER_LENGTH = 100;
     * <p>
     * public static long sizeOfString(final String str, String charset)
     * throws UnsupportedEncodingException {
     * if (TextUtils.isEmpty(str)) {
     * return 0;
     * }
     * int len = str.length();
     * if (len < STRING_BUFFER_LENGTH) {
     * return str.getBytes(charset).length;
     * }
     * long size = 0;
     * for (int i = 0; i < len; i += STRING_BUFFER_LENGTH) {
     * int end = i + STRING_BUFFER_LENGTH;
     * end = end < len ? end : len;
     * String temp = getSubString(str, i, end);
     * size += temp.getBytes(charset).length;
     * }
     * return size;
     * }
     * <p>
     * // get the sub string for large string
     * public static String getSubString(final String str, int start, int end) {
     * return new String(str.substring(start, end));
     * }
     * <p>
     * public static StackTraceElement getCurrentStackTraceElement() {
     * return Thread.currentThread().getStackTrace()[3];
     * }
     * <p>
     * public static StackTraceElement getCallerStackTraceElement() {
     * return Thread.currentThread().getStackTrace()[4];
     * }
     * <p>
     * private static SSLSocketFactory sslSocketFactory;
     * <p>
     * public static void trustAllHttpsURLConnection() {
     * // Create a trust manager that does not validate certificate chains
     * if (sslSocketFactory == null) {
     * TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
     *
     * @return
     * @Override public X509Certificate[] getAcceptedIssuers() {
     * return null;
     * }
     * @Override public void checkClientTrusted(X509Certificate[] certs,
     * String authType) {
     * }
     * @Override public void checkServerTrusted(X509Certificate[] certs,
     * String authType) {
     * }
     * }
     * };
     * try {
     * SSLContext sslContext = SSLContext.getInstance("TLS");
     * sslContext.init(null, trustAllCerts, null);
     * sslSocketFactory = sslContext.getSocketFactory();
     * } catch (Throwable e) {
     * LogUtils.e(e.getMessage(), e);
     * }
     * }
     * <p>
     * if (sslSocketFactory != null) {
     * HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
     * HttpsURLConnection
     * .setDefaultHostnameVerifier(
     * org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
     * }
     * }
     * <p>
     * <p>
     * /**
     * 查看当前网络是否已经连接
     */
    @SuppressLint("MissingPermission")
    public static boolean isNetworkConnected() {

        ConnectivityManager connManager = (ConnectivityManager) Config.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo info = null;
        try {
            info = connManager.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (info != null && (info.getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        }

        return false;
    }


    public static String floatromovedian(double d) {
        String s = String.valueOf(d);
        String newD = s.substring(0, s.indexOf("."));
        return newD;
    }

    public static String floatromovedian(String d) {
        if (TextUtil.isNull(d)) {
            return "";
        }
        String newD = d.substring(0, d.indexOf("."));
        return newD;
    }

    /**
     * 3.0类似的数据去除小数点
     *
     * @param d
     * @return
     */
    public static String floatromovedianOther(double d) {
        String s = String.valueOf(d);
        String newD = s.substring(0, s.indexOf("."));
        if (d == Double.parseDouble(newD)) {
            return newD;
        } else {
            return s;
        }
    }

    public static String romovedianOther(double d) {
        try {
            String s = String.valueOf(d);
            String newD = s.substring(0, s.indexOf("."));
            return newD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 得到应用版本号
     *
     * @return
     */
    public static String getVersionOfApp() {
        PackageManager manager = Config.mContext
                .getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(Config.mContext
                    .getPackageName(), 0);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String version = info.versionName;
        return version;
    }

    public static boolean isNewVersion(String remoteVersion) {
        PackageManager manager = Config.mContext
                .getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(Config.mContext
                    .getPackageName(), 0);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String version = info.versionName;
        String[] remoteVersions = remoteVersion.split("\\.");
        String[] localVersions = version.split("\\.");
        for (int i = 0; i < remoteVersions.length; i++) {
            int remoteCode = Integer.valueOf(remoteVersions[i]);
            if (i >= localVersions.length) {
                return true;
            }
            int localCode = Integer.valueOf(localVersions[i]);
            if (remoteCode > localCode) {
                return true;
            }
        }
        return false;

    }

    /**
     * 得到厂商信息
     *
     * @return
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    //    /**
//     * 得到IMEI
//     *仅仅只对Android手机有效:
//     * 6.0之后需要动态申请android.permission.READ_PHONE_STATE权限
//     * @return
//     */
    @SuppressLint("MissingPermission")
    public static String getIMEI() {
        try {
            TelephonyManager mTm = (TelephonyManager) Config.mContext.getSystemService(Context.TELEPHONY_SERVICE);
            return mTm.getDeviceId();
        } catch (Exception e) {

        }
        return "";
    }

    public static String getUniqueId(Context context) {
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        return TextUtil.stringToMD5(id);
    }

    public static String getPseudoUniquePseudoID() {
        String szDevIDShort = "35" + //we make this look like a valid IMEI

                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10
                + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
                + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
                + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10
                + Build.USER.length() % 10; //13 digits
        String serial = Build.SERIAL;
        return new UUID(szDevIDShort.hashCode(), serial.hashCode()).toString();
    }


    /**
     * 得到wifi信息
     *
     * @return
     */
    public static String getWifiInfo() {
        WifiManager wifi = (WifiManager) Config.mContext
                .getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String ssid = info.getSSID();
        return ssid;
    }

    /**
     * 判断当前应用是否已经被授权如下权限
     *
     * @param permission 要判断的权限数组
     */
    public static boolean checkPermissionsGranted(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String value : permission) {
                if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                    denyPermissions.add(value);
                    LogUtils.d(value + " is not granted.");
                }
            }
        }

        if (denyPermissions.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 保存图片到本地
     *
     * @param bitName
     * @param bitmap
     */
    public static void saveMyBitmap(String bitName, Bitmap bitmap) {
        File f = new File(bitName);
        try {
            f.createNewFile();
            FileOutputStream outputStream = null;
            outputStream = new FileOutputStream(f);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * bitmap转换成 byte[ ]
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int wxthumbSize = 120;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, wxthumbSize, wxthumbSize, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        thumbBmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        boolean flag = false;
        if (TextUtil.isNull(path)) {
            return flag;
        }
        File file = new File(path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 获取Android系统版本号
     *
     * @return
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 跳转应用市场打分
     *
     * @param context
     * @param pkgName 包名
     */
    public static void markInMarket(Context context, String pkgName) {
        String address = "market://details?id=" + pkgName;
        Intent marketIntent = new Intent("android.intent.action.VIEW");
        marketIntent.setData(Uri.parse(address));
        context.startActivity(Intent.createChooser(marketIntent, "请选择要查看的市场软件"));
    }


    /**
     * 得到当前的进程名
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        try {
            int pid = android.os.Process.myPid();
            ActivityManager activityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                    .getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    return appProcess.processName;
                }
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }


    /**
     * 判断屏幕是否锁屏
     *
     * @return
     */
    public static boolean isScreenOn(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return !keyguardManager.inKeyguardRestrictedInputMode();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
//            DisplayManager dm = (DisplayManager) getApplicationContext().getSystemService(Context.DISPLAY_SERVICE);
//            for (Display display : dm.getDisplays()) {
//                if (display.getState() != Display.STATE_OFF) {
//                    return true;
//                }
//            }
//            return false;
//
//        } else{
//            PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
//            return pm.isScreenOn();//如果为true，则表示屏幕“亮”了，否则屏幕“暗”了。
//        }
    }

    /**
     * 获取进程是否是前台进程
     *
     * @param context
     * @return
     */
    public static boolean isForgroundProcess(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return isForgroundForL(context);
        } else {
            return isForgroundForPreL(context);
        }
    }

    private static Field processStateField;


    private static boolean isForgroundForL(Context c) {
        final int PROCESS_STATE_TOP = 2;
        if (processStateField == null) {
            try {
                processStateField = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        ActivityManager am = (ActivityManager) c.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            //前台程序
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && processInfo.importanceReasonCode == 0) {
                int state = 0;
                try {
                    state = processStateField.getInt(processInfo);
                    if (state == PROCESS_STATE_TOP) {
                        for (String activeProcess : processInfo.pkgList) {
                            if (activeProcess.equals(c.getPackageName())) {
                                return true;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static boolean isForgroundForPreL(Context c) {
        ActivityManager activityManager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = activityManager.getRunningTasks(1);
        if (rti.get(0).topActivity.getPackageName().equals(c.getPackageName())
                && rti.get(0).baseActivity.getPackageName().equals(c.getPackageName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前activity是否还在运行
     *
     * @param activityClassName
     * @return
     */
    public static boolean isActivityRunning(Context context, String activityClassName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if (info != null && info.size() > 0) {
            ComponentName component = info.get(0).topActivity;
            if (activityClassName.equals(component.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前界面是否是桌面
     */

    public static boolean isHome(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = activityManager.getRunningTasks(1);
        List<String> strs = getHomes(context);
        if (strs != null && strs.size() > 0) {
            return strs.contains(rti.get(0).topActivity.getPackageName());
        } else {
            return false;
        }
    }

    public static void showViews(View... views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void hideViews(View... views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private static List<String> getHomes(Context context) {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = context.getPackageManager();
        //属性
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }

    /**
     * 获取程序名称
     *
     * @param context
     * @return
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isChineseChar(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    private static final String MARSHMAL_LOW_MAC_ADDRESS = "02:00:00:00:00:00";
    private static final String FILE_ADDRESS_MAC = "/sys/class/net/wlan0/address";

    public static String getAdresseMAC(Context context) {
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();

        if (wifiInf != null && MARSHMAL_LOW_MAC_ADDRESS.equals(wifiInf.getMacAddress())) {
            String result = null;
            try {
                result = getAdressMacByInterface();
                if (result != null) {
                    return result;
                } else {
                    result = getAddressMacByFile(wifiMan);
                    return result;
                }
            } catch (IOException e) {
                Log.e("MobileAccess", "Erreur lecture propriete Adresse MAC");
            } catch (Exception e) {
                Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
            }
        } else {
            if (wifiInf != null && wifiInf.getMacAddress() != null) {
                return wifiInf.getMacAddress();
            } else {
                return "";
            }
        }
        return MARSHMAL_LOW_MAC_ADDRESS;
    }

    private static String getAdressMacByInterface() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }

        } catch (Exception e) {
            Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
        }
        return null;
    }

    private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
        String ret;
        int wifiState = wifiMan.getWifiState();

        wifiMan.setWifiEnabled(true);
        File fl = new File(FILE_ADDRESS_MAC);
        FileInputStream fin = new FileInputStream(fl);
        ret = crunchifyGetStringFromStream(fin);
        fin.close();

        boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
        wifiMan.setWifiEnabled(enabled);
        return ret;
    }

    private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
        if (crunchifyStream != null) {
            Writer crunchifyWriter = new StringWriter();

            char[] crunchifyBuffer = new char[2048];
            try {
                Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            } finally {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        } else {
            return "No Contents";
        }
    }

    /**
     * 患者名字固定几个字+...
     *
     * @param name
     * @param lenth
     * @return
     */
    public static String getNameByLimitLength(String name, int lenth) {
        if (name.length() <= lenth) {
            return name;
        } else {
            return name.substring(0, lenth) + "...";
        }
    }


    private static final int PHONE_INDEX_6 = 6;
    private static final int PHONE_INDEX_7 = 7;
    private static final int PHONE_INDEX_15 = 15;
    private static final int PHONE_INDEX_16 = 16;

    /**
     * 身份证格式6-8-4 ，6-8-2
     *
     * @param idcard
     * @return
     */
    public static String formatIdCard(String idcard) {

        if (TextUtil.isNull(idcard)) {
            return idcard;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idcard.length(); i++) {
            if (i != PHONE_INDEX_6 && i != PHONE_INDEX_15 && idcard.charAt(i) == ' ') {
                continue;
            } else {
                sb.append(idcard.charAt(i));
                if ((sb.length() == PHONE_INDEX_7 || sb.length()
                        == PHONE_INDEX_16) && sb.charAt(sb.length() - 1) != ' ') {
                    sb.insert(sb.length() - 1, ' ');
                }
            }
        }


        return sb.toString();
    }

    private static final int PHONE_INDEX_3 = 3;
    private static final int PHONE_INDEX_4 = 4;
    private static final int PHONE_INDEX_8 = 8;
    private static final int PHONE_INDEX_9 = 9;

    /**
     * 手机号格式3-4-4
     *
     * @param phone
     * @return
     */
    public static String formatPhoneCard(String phone) {

        if (phone == "" || phone.length() == 0) {
            return phone;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phone.length(); i++) {
            if (i != PHONE_INDEX_3 && i != PHONE_INDEX_8 && phone.charAt(i) == ' ') {
                continue;
            } else {
                sb.append(phone.charAt(i));
                if ((sb.length() == PHONE_INDEX_4 || sb.length()
                        == PHONE_INDEX_9) && sb.charAt(sb.length() - 1) != ' ') {
                    sb.insert(sb.length() - 1, ' ');
                }
            }
        }

        return sb.toString();
    }

    /**
     * 点赞数--【0，9999】显示具体数字，如1933；
     * <p>
     * 【>9999】以万为单位，取整加1，保留小数点一位数字，如 11098，，就显示为1.2w；10000，显示为1.0 w  ;  11898，显示为1.2w
     * <p>
     * • 浏览量---同上
     *
     * @param number
     * @return
     */
    public static String getNubmerText(long number) {
        if (number < 10000) {
            return number + "";
        }
        float f = 0;
        String b = number * 1.0 / 10000 + "";
        if (b.length() - b.lastIndexOf(".") <= 2) {
            f = Float.parseFloat(b);
        } else {
            f = Float.parseFloat(b) + 0.1f;
        }
        BigDecimal bd = new BigDecimal(f);
        Double tem = bd.setScale(1, BigDecimal.ROUND_FLOOR).doubleValue();
        return tem + "w";
    }

    /**
     * 获得保留两个小数的
     *
     * @param money
     * @return
     */
    public static String getMoneyText(String money) {
        DecimalFormat fmt = new DecimalFormat("##0.00");
        String origin = fmt.format(Float.parseFloat(money) / 100.0);
        return origin;
    }

    /**
     * 获得保留两个小数的
     *
     * @param money
     * @return
     */
    public static String getMoneyTextNotDiv(String money) {
        DecimalFormat fmt = new DecimalFormat("##0.00");
        String origin = fmt.format(Float.parseFloat(money));
        return origin;
    }

    /**
     * 获得保留1个小数的
     *
     * @param money
     * @return
     */
    public static String getMoneyOneText(String money) {
        DecimalFormat fmt = new DecimalFormat("##0.0");
        String origin = fmt.format(Float.parseFloat(money) / 100.0);
        return origin;
    }

    public static String getDistanceStr(String distance) {
        try {
            if (TextUtil.isNull(distance)) {
                return "";
            }
            if (Float.parseFloat(distance) < 1000) {
                if (Float.parseFloat(distance) < 1) {
                    return "1 米";
                }

                return romovedianOther(Double.parseDouble(distance)) + "米";
            } else {
                return new BigDecimal(Double.parseDouble(distance) / 1000.00 + "")
                        .setScale(2, BigDecimal.ROUND_HALF_UP) + "公里";

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "1m";
        }
    }

    public static String getDistancekm(String distance) {

        return new BigDecimal(Double.parseDouble(distance) / 1000.0 + "")
                .setScale(1, BigDecimal.ROUND_HALF_UP) + "公里";

    }

    public static String getContent(String content) {
        return TextUtil.isNull(content) ? "" : content;


    }



    public static List<String> getImgs(String imgs) {
        if (TextUtil.isNull(imgs)) {
            List<String> list = new ArrayList<>();
            list.add("");
            return new ArrayList<>();
        } else {
            String[] arr = imgs.split(",");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
            }
            return list;
        }


    }

    public static boolean isPad(Context context) {
        return DensityUtil.getWidth() > DensityUtil.getHeight() ? true : false;
    }

    //检测地图应用是否安装
    public static boolean checkMapAppsIsExist(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+(.[0-9]+)?");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static final String PW_PATTERN = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    public static final String PW_PATTERN_ = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z`~!@#$%^&*()+=|{}':;"
            + "',\\[\\].<>/?~！@#¥%……&*（）——+|{}【】‘；：”“’。，、？]{8,16}$";


    public static String listToString(List<String> list) {
        if (CollectionUtils.isValid(list)) {
            String str = "";
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    str = list.get(i);
                } else {
                    str = str + "," + list.get(i);
                }

            }
            return str;
        }

        return "";

    }

}
