package www.hbj.cloud.baselibrary.common;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public interface PermissionListener {
    void onGranted();

    void onDenied(String[] deniedPermissions);
}
