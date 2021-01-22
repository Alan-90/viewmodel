package www.hbj.cloud.baselibrary.common.service;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import www.hbj.cloud.baselibrary.common.BaseObjectBean;
import www.hbj.cloud.baselibrary.common.model.ActionItem;
import www.hbj.cloud.baselibrary.common.model.ActionItem1;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public interface ApiService {

    @GET("web/tenet/getTenetIdByTenetName")
    Flowable<BaseObjectBean<ActionItem>> test(@Query("tenetName") String tenetName);

    @GET("web/tenet/getTenetIdByTenetName")
    Flowable<BaseObjectBean<ActionItem1>> test1(@Query("tenetName") String tenetName);

}
