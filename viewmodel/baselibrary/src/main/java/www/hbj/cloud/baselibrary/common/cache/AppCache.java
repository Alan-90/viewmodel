package www.hbj.cloud.baselibrary.common.cache;


import www.hbj.cloud.baselibrary.common.utils.CacheUtil;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class AppCache {
    private static final String USER_LOING_STATUS = "userLoginStatus";

    private static final String language = "appLanguage";


    public static void isLogined(boolean isLogined) {
        CacheUtil.put(USER_LOING_STATUS, isLogined);
    }

    public static boolean isLogined() {
        return CacheUtil.get(USER_LOING_STATUS, false);
    }

    //语言设置,默认中文
    public static void setLanguage(int language) {
        CacheUtil.put("language", language);
    }

    public static int getLanguage() {
        return CacheUtil.get("language", 1);
    }

}
