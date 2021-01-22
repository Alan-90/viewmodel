package www.hbj.cloud.baselibrary.ngr_library.net;

import org.reactivestreams.Publisher;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * @author zw
 * @date 2019/12/25.
 * <p>
 * email：861126420@qq.com
 * description：RXjava 线程调度
 */
public class RxScheduler {
    public static Function<? super Flowable<Throwable>, ? extends Publisher<?>> getRetryFunc1() {

        return new Function<Flowable, Publisher<?>>() {

            private int retryDelaySecond = 5;
            private int retryCount = 0;
            private int maxRetryCount = 1;

            @Override
            public Publisher<?> apply(Flowable throwableFlowable) throws Exception {
                return throwableFlowable.flatMap(new Function<Throwable, Flowable<?>>() {
                    @Override
                    public Flowable<?> apply(Throwable throwable) {
                        return checkApiError(throwable);
                    }
                });
            }


            private Flowable<?> checkApiError(Throwable throwable) {
                retryCount++;
                if (retryCount < maxRetryCount) {
                    if (throwable instanceof ConnectException
                            || throwable instanceof SocketTimeoutException
                            || throwable instanceof TimeoutException
                            || throwable instanceof UnknownHostException
                            || throwable instanceof EOFException) {
                        retryCount = maxRetryCount;
                        return retry(throwable);
                    }
                    if (throwable instanceof HttpException) {
                        HttpException he = (HttpException) throwable;
                        if (he.code() != 401 && he.code() != 403 && he.code() != 409) {
                            return Flowable.error(ExceptionHandle.handleException(throwable));
                        } else {
                            return retry(throwable);
                        }
                    }
                    return Flowable.error(ExceptionHandle.handleException(throwable));
                } else {
                    if (throwable instanceof ResultException) {
                        ResultException he = (ResultException) throwable;
//                        if (he.code.equals("1002") || he.code.equals("-4000") || he.code.equals("-4001") || he.code.equals("-4002") || he.code.equals("-4003")) {
//                            login();
//                        }
                        if (he.code.equals("-4000")) {
                            login();
                        }
                    }
                    return Flowable.error(ExceptionHandle.handleException(throwable));
                }

            }

            /**
             *
             * @param throwable
             * @return
             */
            private Flowable<?> retry(Throwable throwable) {
                if (retryCount <= maxRetryCount) {
                    return Flowable.timer(retryDelaySecond,
                            TimeUnit.SECONDS).observeOn(Schedulers.io());
                } else {
                    return Flowable.error(throwable);
                }
            }

            private void login() {



            }
        };
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> Flo_io_main() {


        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).retryWhen(getRetryFunc1());
            }
        };
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> Obs_io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
