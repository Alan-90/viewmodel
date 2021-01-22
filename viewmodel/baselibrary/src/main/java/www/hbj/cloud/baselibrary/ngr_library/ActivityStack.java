package www.hbj.cloud.baselibrary.ngr_library;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import www.hbj.cloud.baselibrary.ngr_library.utils.CollectionUtils;
import www.hbj.cloud.baselibrary.ngr_library.utils.LogUtils;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class ActivityStack {

    private static List<WeakReference<Activity>> mList = new LinkedList();
    private static Context mContext;

    private static void init() {
        Application application = (Application) mContext;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mList.add(new WeakReference<Activity>(activity));
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                deleteDestroyedActivity(activity);
            }
        });

    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
        init();
    }

    public static void addActivity(Activity target) {
        mList.add(new WeakReference<Activity>(target));
    }

    public static void deleteDestroyedActivity(Activity target) {
        Iterator<WeakReference<Activity>> listIterator = mList.iterator();
        while (listIterator.hasNext()) {
            Activity activity = listIterator.next().get();
            if (activity == null || target == activity || activity.isDestroyed() || activity.isFinishing()) {
                listIterator.remove();
            }
        }
    }

    /**
     * 退出应用时退出所有activity
     */
    public static void exit() {
        try {
            for (WeakReference<Activity> activityRef : mList) {
                Activity activity = activityRef.get();
                if (activity != null && !activity.isFinishing()) {
                    LogUtils.w(activity.getLocalClassName());
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
    public static void killAllactivityWithOutLogin() {
        try {
            for (WeakReference<Activity> activityRef : mList) {
                Activity activity = activityRef.get();
                if (activity != null && !activity.isFinishing()) {
                    if (activity.getClass().getSimpleName().equals("com.snsj.snjk.ui.user.SplashActivity") || activity.getClass().getSimpleName().equals("com.snsj.snjk.ui.user.LoginActivity")) {

                    } else {
                        activity.finish();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void exitApp() {
        try {
            for (WeakReference<Activity> activityRef : mList) {
                Activity activity = activityRef.get();
                if (activity != null && !activity.isFinishing()) {
                    if (activity.getClass().getSimpleName().equals("com.snsj.snjk.ui.user.SplashActivity") || activity.getClass().getSimpleName().equals("com.snsj.snjk.ui.user.UserLoginActivity")) {

                    } else {
                        activity.finish();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Handler mhandler = new Handler();
            mhandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.exit(0);

                }
            }, 300);
        }
    }

    /**
     * 退出某个activity
     *
     * @param cls
     */
    public static void exit(Class cls) {
        try {
            for (WeakReference<Activity> activityRef : mList) {
                Activity activity = activityRef.get();
                if (activity != null && activity.getClass() == cls) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void finishRangActivityExclude(Class<? extends Activity> begin, Class<? extends Activity> end) {
        boolean isBeginFinish = false;
        for (WeakReference<Activity> activityRef : mList) {
            Activity activity = activityRef.get();
            if (activity == null) {
                continue;
            }
            if (activity.getClass() == end) {
                isBeginFinish = false;
                break;
            }
            if (isBeginFinish) {
                activity.finish();
            }
            if (activity.getClass() == begin) {
                isBeginFinish = true;
            }
        }
    }

    public static void finishRangActivity(Class<? extends Activity> begin,
                                          Class<? extends Activity> end) {
        boolean isBeginFinish = false;
        for (WeakReference<Activity> activityRef : mList) {
            Activity activity = activityRef.get();
            if (activity == null) {
                continue;
            }
            if (activity.getClass() == begin) {
                isBeginFinish = true;
            }
            if (isBeginFinish) {
                activity.finish();
            }
            if (activity.getClass() == end) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
                isBeginFinish = false;
                break;
            }
        }
    }

    /**
     * 关闭该activity以上 的所有activity
     *
     * @param clazz
     */
    public static void finishAllUponActivity(Class<? extends Activity> clazz) {
        boolean isBeginFinish = false;
        for (WeakReference<Activity> activityRef : mList) {
            Activity activity = activityRef.get();
            if (activity == null) {
                continue;
            }
            if (activity.getClass() == clazz) {
                isBeginFinish = true;
            }
            if (isBeginFinish) {
                activity.finish();
            }
        }
    }

    /**
     * 关闭该activity以上 的所有activity
     *
     * @param clazz
     */
    public static void finishAllUponActivityWithoutSelf(Class<? extends Activity> clazz) {
        boolean isBeginFinish = false;
        for (WeakReference<Activity> activityRef : mList) {
            Activity activity = activityRef.get();
            if (activity == null) {
                continue;
            }
            Class cls = activity.getClass();
            if (isBeginFinish) {
                activity.finish();
            }
            if (cls == clazz) {
                isBeginFinish = true;
            }
        }
    }

    /**
     * 查看是否在已经打开的activity列表中包含了该activity
     *
     * @param cls
     * @return
     */
    public static boolean haveActivity(Class<? extends Activity> cls) {
        for (WeakReference<Activity> activityRef : mList) {
            Activity activity = activityRef.get();
            if (activity == null) {
                continue;
            }
            if (activity.getClass() == cls) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看是否在已经打开的activity列表中包含了该activity
     *
     * @param cls
     * @return
     */
    public static boolean haveActivity(String cls) {
        for (WeakReference<Activity> activityRef : mList) {
            Activity activity = activityRef.get();
            if (activity == null) {
                continue;
            }
            if (activity.getClass().getSimpleName().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 关闭指定的activity
     *
     * @param cls
     */
    public static void finishActivity(Class<? extends Activity> cls) {
        for (WeakReference<Activity> activityRef : mList) {
            Activity activity = activityRef.get();
            if (activity == null) {
                continue;
            }
            if (activity.getClass() == cls) {
                activity.finish();
            }
        }
    }

    /**
     * 得到最上层的activity
     *
     * @return
     */
    public static Activity getTopActity() {
        if (CollectionUtils.isValid(mList)) {
            return mList.get(mList.size() - 1).get();
        }
        return null;
    }

    public static Activity getActivity(Class<? extends Activity> clazz) {
        Activity result = null;
        for (WeakReference<Activity> activityRef : mList) {
            Activity activity = activityRef.get();
            if (activity == null) {
                continue;
            }
            if (activity.getClass() == clazz) {
                result = activity;
            }
        }
        return result;
    }

    /**
     * 得到已经打开的activity中有多少个activity
     *
     * @param cls
     * @return
     */
    public static int getActivityCount(Class<? extends Activity> cls) {
        if (haveActivity(cls)) {
            int count = 0;
            for (WeakReference<Activity> activityRef : mList) {
                Activity activity = activityRef.get();
                if (activity == null) {
                    continue;
                }
                if (activity.getClass() == cls) {
                    count++;
                }
            }
            return count;
        } else {
            return 0;
        }
    }

    /**
     * 4.4.2 platform issues for FLAG_ACTIVITY_REORDER_TO_FRONT,
     * reordered activity back press will go to home unexpectly,
     * Workaround: move reordered activity current task to front when it's finished.
     *
     * @param activity
     */
    public static void recordToFrontStack(Activity activity, boolean isrestoredToTop) {
        if (android.os.Build.VERSION.SDK_INT >= 19 && !activity.isTaskRoot() && isrestoredToTop) {
            ActivityManager tasksManager = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
            tasksManager.moveTaskToFront(activity.getTaskId(), ActivityManager.MOVE_TASK_NO_USER_ACTION);
        }
    }

}
