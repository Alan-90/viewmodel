package www.hbj.cloud.baselibrary.common;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.json.JSONException;

import java.io.Serializable;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;
import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.platform.BaseApplication;
import www.hbj.cloud.baselibrary.common.utils.NetWorkUtils;

/**
 * ApiException2020/11/24 12:34 PM
 *
 * @desc :
 */
public class ApiException {

    private int code = 0;
    private int httpCode = 0;
    private String message = "";

    private Throwable e;
    private Context mContext;

    public int getCode() {
        return code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getMessage() {
        return message;
    }

    public ApiException(Throwable throwable) {
        this.e = throwable;
        mContext = BaseApplication.getAppContext();
        init();
    }

    private void init() {
        //没有网络
        if (!NetWorkUtils.isNetworkAvailable()) {

            message = mContext.getResources().getString(R.string.no_network);

        } else if (e instanceof JSONException ||
                e instanceof JsonParseException ||
                e instanceof ParseException) {//数据解析失败

            message = mContext.getResources().getString(R.string.json_parse_error);

        } else if (e instanceof SocketTimeoutException) {//网络超时
            message = (mContext.getResources().getString(R.string.network_timeout));
        } else if (e instanceof ConnectException) {//网络连接异常

            message = (mContext.getResources().getString(R.string.network_connect));

        } else if (e instanceof IllegalArgumentException) {//参数错误

            message = mContext.getResources().getString(R.string.illegal_argument);

        } else if (e instanceof UnknownHostException) {

            message = mContext.getResources().getString(R.string.network_connect_error);

        } else if (e instanceof HttpException) {
            httpCode = ((HttpException) e).code();

            try {
                String errorStr = ((HttpException) e).response().errorBody().string();
                ErrorData errorData = new Gson().fromJson(errorStr, ErrorData.class);
                if (null != errorData) {
                    message = errorData.getMessage();
                }
//                switch (httpCode) {
//                    case 401:
//
//                        break;
//                    case 503:
//                        message = mContext.getString(R.string.server_maintance);
//                        break;
//                    default:
//                        if (httpCode >= 500 && httpCode <= 600) {
//                            message = mContext.getString(R.string.server_error);
//                        }
//                        break;
//                }

            } catch (Exception ex) {
//                message = ex.getMessage();
                message = mContext.getString(R.string.data_error);
                ex.printStackTrace();
            }

        }
        Log.i("apiException", "code  :" + code + ",   message :" + message);
    }


    class ErrorData implements Serializable {
        //        {
//            "timestamp":"2020-11-28 18:37:37",
//                "status":500,
//                "error":"Internal Server Error",
//                "exception":"org.apache.kafka.common.errors.ApiException",
//                "message":"登录密码错误,还有8次机会,请重新输入",
//                "path":"/uc/api/user/prelogin"
//        }
        private String timestamp;
        private String status;
        private String error;
        private String message;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
