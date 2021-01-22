package www.hbj.cloud.baselibrary.common;


import java.io.Serializable;

/**
 * @author zw
 * @date 2019/12/25.
 * <p>
 * Email：861126420@qq.com
 * Description：对象
 */
public class BaseStringBean<T> implements Serializable {

    public String status;
    public String msg;
    public String ret;
    public T model;

    /**
     * status : 1
     * msg : 获取成功
     * result : {} 对象
     */

    public String data;


    public String getResult() {
        return data;
    }

    public void setResult(String result) {
        this.data = result;
    }


}
