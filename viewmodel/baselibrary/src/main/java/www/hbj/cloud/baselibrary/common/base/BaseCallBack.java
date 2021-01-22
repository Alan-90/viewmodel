package www.hbj.cloud.baselibrary.common.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import www.hbj.cloud.baselibrary.common.cache.AppCache;
import www.hbj.cloud.baselibrary.common.dialog.BaseProgressDialog;
import www.hbj.cloud.baselibrary.common.listener.NetModelListener;
import www.hbj.cloud.baselibrary.common.utils.NetWorkUtils;
import www.hbj.cloud.baselibrary.common.utils.ToastUtils;

/**
 * @author Alan-kun
 * @date 2020/12/17.
 * description：
 */
public class BaseCallBack<T> implements Observer<T> {

    private Context mContext;
    Dialog dialog;

    private NetModelListener netModelListener;


    public BaseCallBack(NetModelListener netModelListener) {
        this.netModelListener = netModelListener;
    }

    public BaseCallBack(Context context,NetModelListener netModelListener) {
        this.netModelListener = netModelListener;
        this.mContext = context;
        dialog = BaseProgressDialog.getDialogInstance(context);
    }



    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            ToastUtils.showToast( "网络请求失败，请检查网络设置");
        }
        if (mContext != null) showDialog();

    }

    @Override
    public void onNext(T tBaseResponse) {
        result((BaseResponse) tBaseResponse);
    }


    @Override
    public void onError(Throwable e) {
        if (mContext != null) dissMissDialog();
    }

    @Override
    public void onComplete() {
        if (mContext != null) dissMissDialog();
    }


    private <T> void result(BaseResponse<T> response) {
        if ("6".equals(response.getCode())) {//登录失效时 跳到登录页面 清除缓存的数据
            AppCache.isLogined(false);
//            LoginActivity.start(mContext);
        } else {
            if (response != null && response.getCode().equals("0") || response.getCode().equals("200")) {
                netModelListener.onSuccess(response);
            } else {
                netModelListener.onFail(response.getCode(),response.getMessage());
            }
        }
    }
    public void showDialog() {
        ((Activity) mContext).runOnUiThread(() -> dialog.show());
    }

    public void dissMissDialog() {
        ((Activity) mContext).runOnUiThread(() -> dialog.dismiss());
    }
}
