package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songlei on 2017/6/8.
 */

public class HighLightedUtils {

    /**
     * 关键字高亮变色
     *
     * @param color
     *            变化的色值
     * @param text
     *            文字
     * @param keyword
     *            文字中的关键字
     * @return
     */
    public static SpannableString matcherSearchTitle(int color, String text,
                                                     String keyword) {
        SpannableString s = new SpannableString(text);
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    /**
     * 多个关键字高亮变色
     *
     * @param color
     *            变化的色值
     * @param text
     *            文字
     * @param keyword
     *            文字中的关键字数组
     * @return
     */
    public static SpannableString matcherSearchTitle(int color, String text,
                                                     List<String> keyword) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyword.size(); i++) {
            Pattern p = Pattern.compile(keyword.get(i));
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color), start, end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }
}
