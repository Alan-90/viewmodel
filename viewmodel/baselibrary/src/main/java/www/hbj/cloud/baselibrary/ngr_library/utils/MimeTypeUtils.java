package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.webkit.MimeTypeMap;

/**
 * 
 * @author zhengji 2015-11-08
 *
 */
public class MimeTypeUtils {

    private MimeTypeUtils() {
    }

    public static String getMimeType(final String fileName) {
        String result = "application/octet-stream";
        int extPos = fileName.lastIndexOf(".");
        if (extPos != -1) {
            String ext = fileName.substring(extPos + 1);
            result = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        }
        return result;
    }
}
