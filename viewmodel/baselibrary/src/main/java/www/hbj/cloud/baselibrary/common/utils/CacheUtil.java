package www.hbj.cloud.baselibrary.common.utils;

import com.orhanobut.hawk.Hawk;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class CacheUtil {

    public static <T> boolean put(String key, T value) {
        return Hawk.put(key, value);
    }


    public static <T> T get(String key) {
        return Hawk.get(key);
    }

    public static <T> T get(String key, T defaulValue) {
        return Hawk.get(key, defaulValue);
    }


    public static boolean contains(String key) {
        return Hawk.contains(key);
    }

    public static long count() {
        return Hawk.count();
    }

    public static boolean delete(String key) {
        return Hawk.delete(key);
    }


    public static boolean deleteAll() {
        return Hawk.deleteAll();
    }

}
