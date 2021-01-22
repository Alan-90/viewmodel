package www.hbj.cloud.baselibrary.ngr_library.component.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import www.hbj.cloud.baselibrary.ngr_library.component.SysBaseAdapter;

/**
 * ListView适配器抽象类
 *
 *
 *
 * @author huangzhongwen
 * 
 * @param <T>
 */

public abstract class AbsListAdapter<T> extends SysBaseAdapter {

    protected List<T> mList; // Arraylist数据源
    protected Context mContext; // 上下文对象
    protected LayoutInflater mInflater;

    /**
     * 构造方法
     * 
     * @param context
     *            上下文对象
     * @param list
     *            数据源
     */
    public AbsListAdapter(Context context, List<T> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        setList(list);
    }

    /**
     * 
     * @param context
     * @param array
     */
    public AbsListAdapter(Context context, T[] array) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        setList(array);
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 设置数据源
     * 
     * @param list2
     */
    private void setList(List<T> list2) {
        this.mList = list2 != null ? list2 : new ArrayList<T>();
    }

    /**
     * 
     * @param array
     */
    private void setList(T[] array) {
        List<T> list = new ArrayList<T>();
        if (array != null) {
            for (T t : array) {
                list.add(t);
            }
        }
        setList(list);
    }

    /**
     * 更新数据源
     * 
     * @param list
     *            ArrayList<T>
     */
    public void changeData(List<T> list) {
        setList(list);
        this.notifyDataSetChanged();
    }

    /**
     * 更新数据源
     * 
     * @param array
     */
    public void changeData(T[] array) {
        setList(array);
        this.notifyDataSetChanged();
    }

    /**
     * 增加一条数据
     * 
     * @param t
     */
    public void add(T t) {
        this.mList.add(t);
        this.notifyDataSetChanged();
    }

    /**
     * 增加一个集合的数据
     * 
     * @param list
     */
    public void addAll(List<T> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (!this.mList.contains(t)) {
                this.mList.add(t);
            }
        }
        this.notifyDataSetChanged();
    }

    /**
     * 插入一条数据
     * 
     * @param position
     *            插入的位置
     * @param t
     *            数据对象
     */
    public void insert(int position, T t) {
        this.mList.add(position, t);
        this.notifyDataSetChanged();
    }

    /**
     * 删除一条数据
     * 
     * @param t
     *            数据对象
     * @return 删除是否成功
     */
    public boolean remove(T t) {
        boolean removed = this.mList.remove(t);
        this.notifyDataSetChanged();
        return removed;
    }

    /**
     * 删除指定位置的数据
     * 
     * @param position
     *            要删除的位置
     * @return 被删除的对象
     */
    public T remove(int position) {
        T t = this.mList.remove(position);
        this.notifyDataSetChanged();
        return t;
    }

    /**
     * 清除所有
     */
    public void clear() {
        this.mList.clear();
        this.notifyDataSetChanged();
    }

    /**
     * 更新指定位置的对象
     * 
     * @param position
     *            指定位置
     * @param t
     *            数据对象
     */
    public void set(int position, T t) {
        this.mList.set(position, t);
        this.notifyDataSetChanged();
    }

    /**
     * 根据指定的比较器进行排序
     * 
     * @param comparator
     *            比较器
     */
    public void sort(Comparator<T> comparator) {
        Collections.sort(mList, comparator);
        this.notifyDataSetChanged();
    }

    @Override
    public abstract View getView(int position, View convertView,
                                 ViewGroup parent);

}
