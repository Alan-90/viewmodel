package www.hbj.cloud.baselibrary.common;


import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author zw
 * @date 2019/12/25.
 * <p>
 * Email：861126420@qq.com
 * Description：对象
 */
public class BaseObjectBean<T> implements Serializable {

    public String code;
    public String message;

    public T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        if (null == message) {
            return "";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
