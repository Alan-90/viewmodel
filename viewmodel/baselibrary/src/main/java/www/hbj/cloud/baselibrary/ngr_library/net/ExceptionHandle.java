package www.hbj.cloud.baselibrary.ngr_library.net;

import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ConcurrentModificationException;

import www.hbj.cloud.baselibrary.ngr_library.component.toast.SysToast;

/**
 * ExceptionHandle
 */
public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int REQUEST_CONFLICT = 409;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        Log.i("tag", "e.toString = " + e.toString());
     /*   if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;

            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case REQUEST_CONFLICT:
                    ex = new ResponeThrowable("登录信息已经过期，请重新登陆...", e, ERROR.TOKEN_ERROR);
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex = new ResponeThrowable("网络错误", e, ERROR.HTTP_ERROR);
                    //ex.code = httpException.code();
                    break;
            }
            return ex;
        } else */
        if (e instanceof NullPointerException) {
            ex = new ResponeThrowable(e.getMessage(), e, 0 + "");
        } else if (e instanceof ResultException) {
            ex = new ResponeThrowable(((ResultException) e).msg, e, ((ResultException) e).code);
        } else if (e instanceof UnknownHostException) {
            ex = new ResponeThrowable("网络不健康，请检查网络", e, ERROR.NETWORD_ERROR);
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable("当前网络不支持", e, ERROR.NETWORD_ERROR);
        } else if (e instanceof ConcurrentModificationException) {
            ex = new ResponeThrowable("网络不健康，请检查网络", e, ERROR.NETWORD_ERROR);
        } else {
            ex = new ResponeThrowable("服务器有小情绪了，请再试试", e, ERROR.UNKNOWN);
            SysToast.show("服务器有小情绪了，请再试试", Toast.LENGTH_SHORT);
        }
        return ex;
    }


    /**
     * 约定异常
     */
    public static final class ERROR {
        /**
         * 未知错误
         */
        public static final String UNKNOWN = 1000 + "";
        /**
         * 解析错误
         */
        public static final String PARSE_ERROR = 1001 + "";
        /**
         * 网络错误
         */
        public static final String NETWORD_ERROR = 1002 + "";
        /**
         * 协议出错
         */
        public static final String HTTP_ERROR = 1003 + "";

        /**
         * 证书出错
         */
        public static final String SSL_ERROR = 1005 + "";
        /**
         * 证书出错
         */
        public static final String TOKEN_ERROR = 1006 + "";
        /**
         * 证书出错
         */
        public static final String AUTH_ERROR = 1007 + "";
    }

    /**
     * ResponeThrowable
     */
    public static class ResponeThrowable extends RuntimeException {
        public String code;

        public ResponeThrowable(String message, Throwable throwable, String code) {
            super(message, throwable);
            this.code = code;
        }
    }
}
