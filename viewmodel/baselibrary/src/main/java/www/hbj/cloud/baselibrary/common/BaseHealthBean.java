package www.hbj.cloud.baselibrary.common;


import java.io.Serializable;

/**
 * @author zw
 * @date 2019/12/25.
 * <p>
 * Email：861126420@qq.com
 * Description：对象
 */
public class BaseHealthBean<T> implements Serializable {

    public String showapi_res_code;
    public String showapi_res_error;
    public String ret;
    public T showapi_res_body;

    /**
     * status : 1
     * msg : 获取成功
     * result : {} 对象
     */

    public T data;


    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }


}
