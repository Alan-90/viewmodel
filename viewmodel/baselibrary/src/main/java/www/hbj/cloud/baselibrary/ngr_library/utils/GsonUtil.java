package www.hbj.cloud.baselibrary.ngr_library.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class GsonUtil {
    private static final Gson GSON = new GsonBuilder().enableComplexMapKeySerialization()
            .serializeNulls().create();

    public static JsonElement convert(String json) {
        return GSON.fromJson(json, JsonElement.class);
    }

    public static String convert(Object o) {
        return GSON.toJson(o);
    }

    public static <T> T convert(String json, Type typeof) {
        return GSON.fromJson(json, typeof);
    }

    public static <T> T convert(JsonElement json, Type typeof) {
        return GSON.fromJson(json, typeof);
    }


    public static <T> List<T> toList(String json, Class<T> clazz) {
        return GSON.fromJson(json, TypeToken.getParameterized(List.class, clazz).getType());
    }

    public static LinkedHashMap<String, String> toList(String json) {
        return GSON.fromJson(json, new TypeToken<LinkedHashMap<String, Object>>() {
        }.getType());
    }

}
