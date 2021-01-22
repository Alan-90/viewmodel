package www.hbj.cloud.baselibrary.ngr_library.utils;

/**
 * Created by dell on 2018/9/18.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import www.hbj.cloud.baselibrary.ngr_library.Config;

/**
 * LogcatUtil
 */
public class LogcatUtil extends Service {
    Thread thread;
    boolean readlog = true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("hhp", "onCreate");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log2();//个人觉得这个方法更实用
            }
        });
        thread.start();
    }

    /**
     * 方法1
     */
    private void log2() {
        String[] cmds = { "logcat", "-c" };
        String shellCmd = "logcat -v time -s *:V "; // adb logcat -v time *:W
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        BufferedReader reader = null;
        try {
            runtime.exec(cmds).waitFor();
            process = runtime.exec(shellCmd);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains(String.valueOf(android.os.Process.myPid()))) {
                    // line = new String(line.getBytes("iso-8859-1"), "utf-8");
                    writeTofile(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeTofile(String line) {
        String content = line + "\r\n";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + Config.mContext.getPackageName()
                + "/myLog.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file, true);
            fos.write(content.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        readlog = false;
        stopSelf();
    }
}
