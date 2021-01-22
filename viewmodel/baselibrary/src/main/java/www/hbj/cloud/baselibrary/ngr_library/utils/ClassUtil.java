package www.hbj.cloud.baselibrary.ngr_library.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassUtil
 */
public class ClassUtil {

	public static Field[] getClassFields(Object model, boolean iffatherclass) {
		Field[] fields;
		
		Field[] fields1 = model.getClass().getDeclaredFields();
		Field[] fields2 = null;
		if (model.getClass().getSuperclass() != null) {
			fields2 = model.getClass().getSuperclass().getDeclaredFields();	
			fields = new Field[fields1.length + fields2.length];
			System.arraycopy(fields1, 0, fields, 0, fields1.length);
			System.arraycopy(fields2, 0, fields, fields1.length, fields2.length);
		} else {
			fields = new Field[fields1.length];
			System.arraycopy(fields1, 0, fields, 0, fields1.length);
		}
		
		return fields;
	}
	 
	public static Map<String, String> getClassFieldsMap(Object model, boolean iffatherclass) {
		Map<String, String> map = new HashMap<String, String>();
		
		Field[] fields = ClassUtil.getClassFields(model, iffatherclass);
		
		// 遍历所有属性
		for (int j = 0; j < fields.length; j++) {
			// 获取属性的名字
			String name = fields[j].getName();
			// 将属性的首字符大写，方便构造get，set方法
			String upname = name.substring(0, 1).toUpperCase()
					+ name.substring(1);
			Method m = null;
			try {
				m = model.getClass().getMethod("get" + upname);
				if (m == null) {
					m = model.getClass().getSuperclass().getMethod("get" + upname);
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 调用getter方法获取属性值
			String value = "";
			try {
				Object o = m.invoke(model);
				if (o != null) {
					value = ((Object) o).toString();
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put(name, value);
		}

		return map;
	}
	
	public static <T> void updateField(Object model, String fieldName, Object value, Class<T> cls) {
		Method m = get_Setmethod(model,fieldName,cls);
		if (m != null) {
			try {
				m.invoke(model, value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Method get_Getmethod(Object model, String fieldName) {
		// 将属性的首字符大写，方便构造get，set方法
		String upname = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Method m = null;
		try {
			m = model.getClass().getMethod("get" + upname);
			if (m == null) {
				m = model.getClass().getSuperclass().getMethod("get" + upname);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	public static <T> Method get_Setmethod(Object model, String fieldName, Class<T> cls) {
		// 将属性的首字符大写，方便构造get，set方法
		String upname = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Method m = null;
		try {
			m = model.getClass().getMethod("set" + upname,cls);
			if (m == null) {
				m = model.getClass().getSuperclass().getMethod("set" + upname, cls);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
	public static void copyProperties(Object src, Object dest) {
		Field[] fields = getClassFields(dest,true);
		Map<String, String> map = getClassFieldsMap(src,true);
		for (int i = 0;i < fields.length; i++) {
			updateField(dest,
					fields[i].getName(),
					map.get(fields[i].getName()),
					fields[i].getType());
		}
	}
	
}
