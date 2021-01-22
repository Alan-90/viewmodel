package www.hbj.cloud.baselibrary.ngr_library.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author zhengji 2015-11-08
 *
 * @param <K1>
 * @param <K2>
 * @param <V>
 */
public class DoubleKeyValueMap<K1, K2, V> {

    private ConcurrentHashMap<K1, ConcurrentHashMap<K2, V>> mK1K2VMap;

    public DoubleKeyValueMap() {
        this.mK1K2VMap = new ConcurrentHashMap<K1, ConcurrentHashMap<K2, V>>();
    }

    public void put(K1 key1, K2 key2, V value) {
        if (key1 == null || key2 == null || value == null) {
            return;
        }
        if (mK1K2VMap.containsKey(key1)) {
            ConcurrentHashMap<K2, V> kvmap = mK1K2VMap.get(key1);
            if (kvmap != null) {
                kvmap.put(key2, value);
            } else {
                kvmap = new ConcurrentHashMap<K2, V>();
                kvmap.put(key2, value);
                mK1K2VMap.put(key1, kvmap);
            }
        } else {
            ConcurrentHashMap<K2, V> kvmap = new ConcurrentHashMap<K2, V>();
            kvmap.put(key2, value);
            mK1K2VMap.put(key1, kvmap);
        }
    }

    public Set<K1> getFirstKeys() {
        return mK1K2VMap.keySet();
    }

    public ConcurrentHashMap<K2, V> get(K1 key1) {
        return mK1K2VMap.get(key1);
    }

    public V get(K1 key1, K2 key2) {
        ConcurrentHashMap<K2, V> k2v = mK1K2VMap.get(key1);
        return k2v == null ? null : k2v.get(key2);
    }

    public Collection<V> getAllValues(K1 key1) {
        ConcurrentHashMap<K2, V> k2v = mK1K2VMap.get(key1);
        return k2v == null ? null : k2v.values();
    }

    public Collection<V> getAllValues() {
        Collection<V> result = null;
        Set<K1> k1Set = mK1K2VMap.keySet();
        if (k1Set != null) {
            result = new ArrayList<V>();
            for (K1 k1 : k1Set) {
                Collection<V> values = mK1K2VMap.get(k1).values();
                if (values != null) {
                    result.addAll(values);
                }
            }
        }
        return result;
    }

    public boolean containsKey(K1 key1, K2 key2) {
        if (mK1K2VMap.containsKey(key1)) {
            return mK1K2VMap.get(key1).containsKey(key2);
        }
        return false;
    }

    public boolean containsKey(K1 key1) {
        return mK1K2VMap.containsKey(key1);
    }

    public int size() {
        if (mK1K2VMap.size() == 0) {
            return 0;
        }

        int result = 0;
        for (ConcurrentHashMap<K2, V> kvmap : mK1K2VMap.values()) {
            result += kvmap.size();
        }
        return result;
    }

    public void remove(K1 key1) {
        mK1K2VMap.remove(key1);
    }

    public void remove(K1 key1, K2 key2) {
        ConcurrentHashMap<K2, V> k2v = mK1K2VMap.get(key1);
        if (k2v != null) {
            k2v.remove(key2);
        }
    }

    public void clear() {
        if (mK1K2VMap.size() > 0) {
            for (ConcurrentHashMap<K2, V> kvmap : mK1K2VMap.values()) {
                kvmap.clear();
            }
            mK1K2VMap.clear();
        }
    }
}
