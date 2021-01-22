package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.lang.reflect.Type;

import www.hbj.cloud.baselibrary.ngr_library.Config;

/**
 * 保存数据助手
 *
 * @author : zw
 */
public class SharedPreferenceHelper {
    private SharedPreferences preference;
    private String mTag = "SharedPreferenceHelper";

    public SharedPreferenceHelper(Context context, String name) {
        // TODO Auto-generated constructor stub
        if (context == null) {
            this.preference = Config.mContext.getSharedPreferences(name, 0);
            return;
        }
        this.preference = context.getSharedPreferences(name, 0);
    }

    public boolean saveData(String name, String val) {
        try {
            Editor editor = this.preference.edit();
            editor.putString(name, val);
            return editor.commit();
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 保存数据
     *
     * @param name
     * @param val
     * @return
     */
    public boolean saveData(String name, int val) {
        try {
            Editor editor = this.preference.edit();
            editor.putInt(name, val);
            return editor.commit();
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 移除数据
     *
     * @param name
     * @return
     */
    public boolean remove(String name) {
        try {
            Editor editor = this.preference.edit();
            editor.remove(name);
            return editor.commit();
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 保存数据
     *
     * @param name
     * @param val
     * @return
     */
    public boolean saveData(String name, Long val) {
        try {
            Editor editor = this.preference.edit();
            editor.putLong(name, val);
            return editor.commit();
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 获取值
     *
     * @param name
     * @param defVal
     * @return
     */
    public String getString(String name, String defVal) {
        return this.preference.getString(name, defVal);
    }

    /**
     * 保存数据
     *
     * @param name
     * @param val
     * @return
     */
    public boolean saveData(String[] name, String[] val) {
        try {
            if (name == null || val == null || name.length != val.length) {
                return false;
            }

            Editor editor = this.preference.edit();
            int len = name.length;

            for (int i = 0; i < len; i++) {
                editor.putString(name[i], val[i]);
            }

            return editor.commit();
        } catch (Exception e) {
        }
        return false;
    }

    //------------------------------------------------------------------------------------------------------
    /**
     * add by 杨腾蛟
     */
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param
     * @param key
     * @param object
     */
    public static void saveValue(String name, String key, Object object) {
        if (object == null) {
            return;
        }

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = Config.mContext
                .getSharedPreferences(name, Context.MODE_PRIVATE);
        Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.apply();
    }
    //------------------------------------------------------------------------------------------------------
    /**
     * add by 杨腾蛟
     */
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param
     * @param key
     * @param object
     */
    public static void saveValueAppend(String name, String key, Object object) {
        if (object == null) {
            return;
        }

        String type = object.getClass().getSimpleName();
        @SuppressLint("WrongConstant") SharedPreferences sp = Config.mContext
                .getSharedPreferences(name, Context.MODE_APPEND);
        Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.apply();
    }
    public static int getInt(String name, String key, int defaultValue) {
        SharedPreferences sp = Config.mContext
                .getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static String getString(String name, String key, String defaultValue) {
        SharedPreferences sp = Config.mContext
                .getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static boolean getBoolean(String name, String key, boolean defaultValue) {
        SharedPreferences sp = Config.mContext
                .getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static float getFloat(String name, String key, float defaultValue) {
        SharedPreferences sp = Config.mContext
                .getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
    }

    public static long getLong(String name, String key, long defaultValue) {
        SharedPreferences sp = Config.mContext
                .getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

    public static void setStringPreferences(Context context, String preference, String key, String value) {
        saveValue(preference, key, value);
    }

    public static String getStringPreference(Context context, String preference, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }


    public static <T> T getObject(Class<T> clazz) {
        String json = getString(clazz.getName(), clazz.getName(), "");
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return GsonUtil.convert(json,clazz);
    }

    public static void save(String name, String key, Object o) {
        String json = GsonUtil.convert(o);
        saveValue(name, key, json);
    }

    public static <T> T getObject(String name, String key, Type type) {
        String json = getString(name, key, "");
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        if (type != String.class && (TextUtil.isNull(json) || json.equals("\"\""))) {
            return null;
        }

        return GsonUtil.convert(json, type);
    }

    public static void clear(String name) {
        Config.mContext.getSharedPreferences(
                name, Context.MODE_PRIVATE).edit().clear().commit();
    }

    public static SharedPreferences getSharedPreference(String name) {
        return Config.mContext.getSharedPreferences(
                name, Context.MODE_PRIVATE);
    }
}
