package www.hbj.cloud.baselibrary.ngr_library.utils;

/**
 * author：zw
 * on 2016/6/20 17:46
 */
/**
 * Created by kayvan on 10/27/15.
 */
public abstract class Func {
    protected abstract void call();

    protected abstract void call(int requestCode, String[] permissions, int[] grantResults);

    protected abstract void call(String permissionName);
}