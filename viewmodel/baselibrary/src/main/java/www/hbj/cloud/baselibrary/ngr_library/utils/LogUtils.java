/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.text.TextUtils;
import android.util.Log;


/**
 * Log工具，类似android.util.Log。
 * tag自动产生，格式: customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)。
 * <p>
 * Author: wyouflf
 * Date: 13-7-24
 * Time: 下午12:23
 */
public class LogUtils {

    public static String customTagPrefix = "";
    private static final int maxLength = 3200;
    public static boolean showAllLog = false;//true studio只能打印一部分日志 cmd adb可以打印所有的日志, false相反


    private LogUtils() {
    }


    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;

    //add by zj
    static {
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static CustomLogger customLogger;

    public interface CustomLogger {
        void d(String tag, String content);

        void d(String tag, String content, Throwable tr);

        void e(String tag, String content);

        void e(String tag, String content, Throwable tr);

        void i(String tag, String content);

        void i(String tag, String content, Throwable tr);

        void v(String tag, String content);

        void v(String tag, String content, Throwable tr);

        void w(String tag, String content);

        void w(String tag, String content, Throwable tr);

        void w(String tag, Throwable tr);

        void wtf(String tag, String content);

        void wtf(String tag, String content, Throwable tr);

        void wtf(String tag, Throwable tr);
    }

    public static void d(String content) {
        if (!allowD) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.d(tag, content);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.d(tag, content);
                return;
            }
            if (content.length() <= maxLength) {
                Log.d(tag, content);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.d(tag, sub);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.d(tag, content.substring(index, content.length()));
        }
    }

    public static void d(String content, Throwable tr) {
        if (!allowD) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.d(tag, content, tr);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.d(tag, content, tr);
                return;
            }
            if (content.length() <= maxLength) {
                Log.d(tag, content, tr);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.d(tag, sub, tr);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.d(tag, content.substring(index, content.length()), tr);

        }
    }

    public static void e(String content) {
        if (!allowE) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.e(tag, content);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.e(tag, content);
                return;
            }
            if (content.length() <= maxLength) {
                Log.e(tag, content);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.e(tag, sub);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.e(tag, content.substring(index, content.length()));
        }
    }

    public static void e(String content, Throwable tr) {
        if (!allowE) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.e(tag, content, tr);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.e(tag, content, tr);
                return;
            }
            if (content.length() <= maxLength) {
                Log.e(tag, content, tr);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.e(tag, sub, tr);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.e(tag, content.substring(index, content.length()), tr);
        }
    }

    public static void i(String content) {
        if (!allowI) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.i(tag, content);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.i(tag, content);
                return;
            }
            if (content.length() <= maxLength) {
                Log.i(tag, content);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.i(tag, sub);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.i(tag, content.substring(index, content.length()));
        }
    }

    public static void i(String content, Throwable tr) {
        if (!allowI) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.i(tag, content, tr);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.i(tag, content, tr);
                return;
            }
            if (content.length() <= maxLength) {
                Log.i(tag, content, tr);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.i(tag, sub, tr);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.i(tag, content.substring(index, content.length()), tr);
        }
    }

    public static void v(String content) {
        if (!allowV) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.v(tag, content);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.v(tag, content);
                return;
            }
            if (content.length() <= maxLength) {
                Log.v(tag, content);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.v(tag, sub);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.v(tag, content.substring(index, content.length()));
        }
    }

    public static void v(String content, Throwable tr) {
        if (!allowV) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.v(tag, content, tr);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.v(tag, content, tr);
                return;
            }
            if (content.length() <= maxLength) {
                Log.v(tag, content, tr);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.v(tag, sub, tr);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.v(tag, content.substring(index, content.length()), tr);
        }
    }

    public static void w(String content) {
        if (!allowW) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, content);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.w(tag, content);
                return;
            }
            if (content.length() <= maxLength) {
                Log.w(tag, content);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.w(tag, sub);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.w(tag, content.substring(index, content.length()));
        }
    }

    public static void w(String content, Throwable tr) {
        if (!allowW) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, content, tr);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.w(tag, content, tr);
                return;
            }
            if (content.length() <= maxLength) {
                Log.w(tag, content, tr);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.w(tag, sub, tr);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.w(tag, content.substring(index, content.length()), tr);
        }
    }

    public static void w(Throwable tr) {
        if (!allowW) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, tr);
        } else {
            Log.w(tag, tr);
        }
    }


    public static void wtf(String content) {
        if (!allowWtf) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, content);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.wtf(tag, content);
                return;
            }
            if (content.length() <= maxLength) {
                Log.wtf(tag, content);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.wtf(tag, sub);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.wtf(tag, content.substring(index, content.length()));
        }
    }

    public static void wtf(String content, Throwable tr) {
        if (!allowWtf) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, content, tr);
        } else {
            if (content == null)
                return;
            if (showAllLog) {
                Log.wtf(tag, content, tr);
                return;
            }
            if (content.length() <= maxLength) {
                Log.wtf(tag, content, tr);
                return;
            }
            int index = 0;
            String sub;
            while (index + maxLength <= content.length()) {
                sub = content.substring(index, index + maxLength);
                Log.wtf(tag, sub);
                index += maxLength;
            }
            if (content.length() % maxLength != 0)
                Log.wtf(tag, content.substring(index, content.length()), tr);
        }
    }

    public static void wtf(Throwable tr) {
        if (!allowWtf) return;
        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, tr);
        } else {
            Log.wtf(tag, tr);
        }
    }
}
