package www.hbj.cloud.baselibrary.ngr_library.component.textview;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/12/25 0025.
 */
public class TextSpan {


    public static String getTextLimitLenth(String str, int maxLen) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int count = 0;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char item = str.charAt(i);
            sb.append(item);
            if (item < 128) {
                count = count + 1;
            } else {
                count = count + 2;
            }
            index = i;
            if (maxLen == count || (item >= 128 && maxLen + 1 == count)) {
                break;
            }
        }
        if (index < str.length() - 1) {
            sb.append("...");
        }
        return sb.toString();

    }

    public static void setText(TextView tv, String big, String small, int bigStyle, int smalStyle) {
        int bigLength = 0;
        int totalLength = 0;
        SpannableString spanStr = new SpannableString(big + small);
//        new AbsoluteSizeSpan(20), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        if (!TextUtils.isEmpty(big)) {
            spanStr.setSpan(new TextAppearanceSpan(tv.getContext(), bigStyle), 0, big.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            bigLength = big.length();
            totalLength += bigLength;
        }
        if (!TextUtils.isEmpty(small)) {
            totalLength += small.length();
            spanStr.setSpan(new TextAppearanceSpan(tv.getContext(), smalStyle), bigLength, totalLength,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(spanStr, TextView.BufferType.SPANNABLE);
    }

    public static void setTextMultColor(TextView tv, CharSequence s1, CharSequence s2, int color1, int color2) {
        SpannableStringBuilder builder = new SpannableStringBuilder(s1.toString() + s2.toString());
        ForegroundColorSpan cs1 = new ForegroundColorSpan(color1);
        ForegroundColorSpan cs2 = new ForegroundColorSpan(color2);
        int start = 0;
        if (!TextUtils.isEmpty(s1)) {
            start = s1.length();
            builder.setSpan(cs1, 0, start, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!TextUtils.isEmpty(s2)) {
            builder.setSpan(cs2, start, start + s2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(builder);
    }

    //    pannable WordtoSpan = new SpannableString("大字小字");
//    WordtoSpan.setSpan(new AbsoluteSizeSpan(20), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    WordtoSpan.setSpan(new AbsoluteSizeSpan(14), 2, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    remoteViews.setCharSequence(R.id.text11, "setText", WordtoSpan);
//    ComponentName com = new ComponentName("com.jftt.widget", "com.jftt.widget.MyWidgetProvider");
//    appWidgetManager.updateAppWidget(com, remoteViews);
    public static void setTextSize(TextView tv, String big, String small, int bigSize, int smalSize) {
        int bigLength = 0;
        int totalLength = 0;
        SpannableString spanStr = new SpannableString(big + small);
//        new AbsoluteSizeSpan(20), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        if (!TextUtils.isEmpty(big)) {
            spanStr.setSpan(new AbsoluteSizeSpan(bigSize), 0, big.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            bigLength = big.length();
            totalLength += bigLength;
        }
        if (!TextUtils.isEmpty(small)) {
            totalLength += small.length();
            spanStr.setSpan(new AbsoluteSizeSpan(smalSize), bigLength, totalLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(spanStr, TextView.BufferType.SPANNABLE);
    }

}
