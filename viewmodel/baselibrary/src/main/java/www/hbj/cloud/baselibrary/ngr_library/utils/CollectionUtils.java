package www.hbj.cloud.baselibrary.ngr_library.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * CollectionUtils
 */
public class CollectionUtils {
    public static <T> boolean isValid(Collection<T> c) {
        return !(c == null || c.isEmpty());
    }

    /**
     * @param <T>
     * @param arrs
     * @param index
     * @return
     */
    public static <T> T[] removeArrayItem(T[] arrs, int index) {
        int len = arrs.length;
        if (index < 0 || index >= len) {
            throw new IllegalArgumentException("index");
        }
        List<T> list = new LinkedList<T>();
        for (int i = 0; i < len; i++) {
            if (i != index) {
                list.add(arrs[i]);
            }
        }

        arrs = list.toArray(arrs);
        return java.util.Arrays.copyOf(arrs, arrs.length - 1);
    }



    public static Map<String, Object> transStringToMap(String mapString, String separator, String pairSeparator) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] fSplit = mapString.split(separator);
        for (int i = 0; i < fSplit.length; i++) {
            if (fSplit[i] == null || fSplit[i].length() == 0) {
                continue;
            }
            String[] sSplit = fSplit[i].split(pairSeparator);
            String value = fSplit[i].substring(fSplit[i].indexOf('=') + 1, fSplit[i].length());
            map.put(sSplit[0], value);
        }

        return map;
    }
}
