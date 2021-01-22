package www.hbj.cloud.baselibrary.common.base;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author Alan-kun
 * @date 2020/12/17.
 * description：
 */
public class BaseResponse <T> implements Serializable {


    String code = "";
    String message;
    T data;



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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}