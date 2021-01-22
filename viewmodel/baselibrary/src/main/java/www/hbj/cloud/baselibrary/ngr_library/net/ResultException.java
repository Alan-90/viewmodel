package www.hbj.cloud.baselibrary.ngr_library.net;

public class ResultException extends RuntimeException {
    public String code;
    public String msg;

    public ResultException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
