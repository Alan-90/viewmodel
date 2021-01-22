package www.hbj.cloud.baselibrary.common.listener;

/**
 * @author Alan-kun
 * @date 2020/12/17.
 * description：
 */
public interface NetModelListener<T> {
    void onSuccess(T result);

    void onFail(String errorCode, String errorStr);
}
