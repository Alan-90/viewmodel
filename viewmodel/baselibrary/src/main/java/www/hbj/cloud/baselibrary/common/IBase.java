package www.hbj.cloud.baselibrary.common;

import android.content.Context;

import androidx.annotation.StringRes;

/**
 * IBase2020/11/23 5:13 PM
 *
 * @desc :
 */
public interface IBase {

    boolean useEventBus();

    void showLoading();

    void disLoading();

    void toastSuc(String message);

    void toastSuc(@StringRes int strId);

    void toastFailed(String message);

    void toastFailed(@StringRes int strId);

    Context getContext();

}
