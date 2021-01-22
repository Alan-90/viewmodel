package www.hbj.cloud.baselibrary.common.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import www.hbj.cloud.baselibrary.common.ApiRetrofit;
import www.hbj.cloud.baselibrary.common.service.ApiService;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class BaseViewModel extends ViewModel {

    public ApiService mApiService = ApiRetrofit.getInstance().getApiService();

    public MutableLiveData<Boolean> LoadingState = new MutableLiveData();

    public void apiRequest(Observable observable, Observer disposable) {
         observable.subscribeOn(Schedulers.io()) //使用调度器把请求数据切换到子线程
                //请求完数据指向主线程
                .observeOn(AndroidSchedulers.mainThread())
                //订阅
                .subscribeWith(disposable);
    }


    protected void showLoading() {
        LoadingState.setValue(true);
    }

    protected void disLoading() {
        LoadingState.setValue(false);
    }

}
