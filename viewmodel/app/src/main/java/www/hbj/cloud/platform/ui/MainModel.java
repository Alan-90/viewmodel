package www.hbj.cloud.platform.ui;

import androidx.lifecycle.MutableLiveData;

import www.hbj.cloud.baselibrary.common.BaseObjectBean;
import www.hbj.cloud.baselibrary.common.bean.Result;
import www.hbj.cloud.baselibrary.common.model.ActionItem;
import www.hbj.cloud.baselibrary.common.service.ApiService;
import www.hbj.cloud.baselibrary.modelpresent.BaseViewModel;
import www.hbj.cloud.baselibrary.ngr_library.net.RetrofitClient;
import www.hbj.cloud.baselibrary.ngr_library.net.RxScheduler;

/**
 * @author Alan-kun
 * @date 2020/12/17.
 * description：
 */
public class MainModel extends BaseViewModel {
    public MutableLiveData<Result<BaseObjectBean<ActionItem>>> liveData = new MutableLiveData<>();
    public MutableLiveData<Result<String>> liveData1 = new MutableLiveData<>();
    public MutableLiveData<Result<String>> liveData2 = new MutableLiveData<>();


    public void test(String tenetName) {
        RetrofitClient.getInstance().createAppRetrofitNew(ApiService.class).test("").compose(RxScheduler.Flo_io_main())
                .subscribe(objectBean -> {
                    liveData.setValue(Result.success(objectBean));


                }, throwable -> {
                    Result.failed(1,throwable.getMessage());
                });



    }

}
