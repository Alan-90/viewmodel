package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TextUtil
 */
public class TextUtil {
    /**
     * 判断字符串是否为空
     *
     * @param text
     * @return
     */
    public static boolean isNull(String text) {
        if (text == null) {
            return true;
        } else if (text.trim().equals("")) {
            return true;
        } else if (text.trim().equals("null")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getTextNotnull(String text) {
        if (text == null) {
            return "";
        } else if (text.trim().equals("")) {
            return "";
        } else if (text.trim().equals("null")) {
            return "";
        } else {
            return text;
        }
    }

    /**
     * 判断字符串是否为数字字符串
     *
     * @param text
     * @return
     */
    public static boolean isNumber(String text) {
        if (isNull(text)) {
            return false;
        }

        text = text.trim();
        String str = "^[0-9]*$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    /**
     * 判断是否正确的手机号
     *
     * @param mobiles 号码
     */
    public static boolean isMobileNumber(String mobiles) {
        if (mobiles != null && mobiles.replaceAll(" ", "").length() != 11) {
            return false;
        }
//        Pattern p = Pattern
//                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//        Matcher m = p.matcher(mobiles);
        return true;

    }


    /**
     * 判断是否正确的密码长度
     *
     * @param pwd 密码
     */
    public static boolean isPassword(String pwd) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z]*$");
        if (pwd != null && (pwd.length() < 17 && pwd.length() > 5) && pattern.matcher(pwd).matches()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 将字符串转成MD5值
     *
     * @param string
     * @return
     */
    public static String stringToMD5(String string) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(string.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};

    public static String encryptByMD5(String source) {
        if (source == null || "".equals(source))
            return null;
        try {
            source = source + "huaer-(365)";
            MessageDigest md = MessageDigest.getInstance("MD5");
            if ("utf-8" != null && !"".equals("utf-8"))
                md.update(source.getBytes("utf-8"));
            else
                md.update(source.getBytes("utf-8"));
            byte[] encryptStr = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = encryptStr[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检测是否是email地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)"
                + "|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);


        return m.matches();
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    public static String desensitize(String content, int start, int length) {
        if (TextUtils.isEmpty(content) || start + length >= content.length()) {
            return content;
        }
        StringBuilder sb = new StringBuilder(content.substring(0, start));
        for (int i = 0; i < length; i++) {
            sb.append(" *");
        }
        sb.append(content.substring(start + length, content.length()));
        return sb.toString();
    }

    public Bitmap aa(String base64) {
        byte[] bytes = Base64.decode(base64, Base64.URL_SAFE);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }


    public static long getFreeSpaceBytes(final String path) {
        long freeSpaceBytes;
        final StatFs statFs = new StatFs(path);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            freeSpaceBytes = statFs.getAvailableBytes();
        } else {
            //noinspection deprecation
            freeSpaceBytes = statFs.getAvailableBlocks() * (long) statFs.getBlockSize();
        }

        return freeSpaceBytes;
    }

    /**
     * 中文转utf8
     *
     * @param xml
     * @return
     */
    public static String getUTF8XMLString(String xml) {
        String xmString = "";
        String xmlUTF8 = "";
        try {
            xmString = new String(xml.getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return to String Formed
        return xmlUTF8;
    }

    public static String getPriceStr(String price) {
        if(TextUtil.isNull(price)){
            return "¥0";
        }
        if (price.startsWith("")) {
            price = price;
        } else {
            price = "" + price;
        }
        return price;
    }

    public static void adaptTextSize(TextView tv, float maxWidth, String value) {
        if (tv == null) {
            return;
        }
        if (maxWidth <= 0) {
            return;
        }
        if (TextUtils.isEmpty(value)) {
            return;
        }

        float tvWidth = tv.getPaint().measureText(value);

        // 如果超出边界，调整字体
        int count = 0;
        while (tvWidth > maxWidth) {
            //防止无限循环导致anr等问题
            if (count > 100) {
                break;
            }
            count++;
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv.getTextSize() - 1);
            tvWidth = tv.getPaint().measureText(value);
        }
        if (value.equals("¥0.00")) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
        tv.setText(value);
    }
}
