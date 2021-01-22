package www.hbj.cloud.baselibrary.common.bean;


public class Result<T> {

    public static <T> Result<T> success(T t) {
        return new Result<T>(t);
    }

    public static Result success() {
        return Result.success("");
    }

    public static Result failed(int code, String message) {
        Result result = new Result(code, message);
        return result;
    }

    private Result(T data) {
        this.data = data;
        isSuccess = true;
    }

    private Result(int code, String message) {
        this.code = code;
        this.message = message;

        isFailed = true;
    }

    private boolean isSuccess = false;

    private boolean isFailed = false;

    /**
     * 请求成功信息
     */
    private T data;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;


    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
