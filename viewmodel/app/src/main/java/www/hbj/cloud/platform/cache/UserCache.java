package www.hbj.cloud.platform.cache;

import www.hbj.cloud.platform.ui.bean.UserBean;
import www.hbj.cloud.baselibrary.common.utils.CacheUtil;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class UserCache {

    private static final String KEY_USER_TOKEN = "keyUserToken";
    private static final String KEY_USER = "keyUser";

    public static void cacheUserToken(String token) {
        CacheUtil.put(KEY_USER_TOKEN, token);
    }

    public static String userToken() {
        return CacheUtil.get(KEY_USER_TOKEN, "");
    }

    public static String userName() {
        return null == userInfo() ? "" : userInfo().getTel();
    }

    public static String uid() {
        return null == userInfo() ? "" : userInfo().getUid();
    }

    public static String userTel() {
        return null == userInfo() ? "" : userInfo().getTel();
    }

    public static String userEmail() {
        return null == userInfo() ? "" : userInfo().getEmail();
    }

    public static void cacheUser(UserBean userBean) {
        CacheUtil.put(KEY_USER, userBean);
    }

    public static UserBean userInfo() {
        return CacheUtil.get(KEY_USER, null);
    }
    public static int userCountry() {
        return null == userInfo() ? 0 : userInfo().getCountryCode();
    }

}
