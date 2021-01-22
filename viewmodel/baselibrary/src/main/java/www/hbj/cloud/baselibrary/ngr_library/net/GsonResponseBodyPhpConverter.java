package www.hbj.cloud.baselibrary.ngr_library.net;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import www.hbj.cloud.baselibrary.common.BaseObjectBean;

class GsonResponseBodyPhpConverter<T> implements Converter<ResponseBody,
        T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyPhpConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 针对数据返回成功、错误不同类型字段处理
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        //先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
        BaseObjectBean httpResult = gson.fromJson(response, BaseObjectBean.class);
        T t = null;
        if(httpResult.code!=null) {
            if (httpResult.code.equals("0000")) {
                //0000的时候就直接解析，不可能出现解析异常。因为我们实体基类中传入的泛型，就是数据成功时候的格式

                try {
                    t = gson.fromJson(response, type);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    throw new ResultException(httpResult.code, httpResult.message);
                }
                return t;
            } else {
                throw new ResultException(httpResult.code, httpResult.message);
            }
        }else{
            t = gson.fromJson(response, type);
            return t;
        }
    }
}