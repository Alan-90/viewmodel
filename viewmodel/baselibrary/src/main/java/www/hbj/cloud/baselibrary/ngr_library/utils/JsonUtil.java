package www.hbj.cloud.baselibrary.ngr_library.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JsonUtil
 */
public class JsonUtil {

	JSONArray newArray = new JSONArray();
	JSONObject newJson = new JSONObject();

	// public static List getList4JsonStr(String key,String str) {
	// JSONObject obj = new JSONObject(str);
	// JSONArray array = obj.getJSONArray(key);
	// }
	public static List<Object> getList4Json(String key, Class cls,
                                            JSONObject obj, Boolean fatherClass) {
		List<Object> list = new ArrayList<Object>();
		JSONArray array = null;
		try {
			array = obj.getJSONArray(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonobject = null;
				try {
					jsonobject = array.getJSONObject(i);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object tmpObj = null;
				try {
					tmpObj = cls.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				JsonUtil.getClass4Json(tmpObj, jsonobject, fatherClass);
				list.add(tmpObj);
			}
		}
		return list;
	}

	public static JSONObject getJson4String(String str) {
		JSONObject obj = null;
		try {
			obj = new JSONObject(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public static String getString4JsonString(String key, String json) {
		String ret = "";
		try {
			JSONObject obj = new JSONObject(json);
			ret = getString4Json(key, obj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static String getString4Json(String key, JSONObject object) {
		try {
			return object.get(key).toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static JSONObject getJson4Class(Object cls, Boolean fatherClass) {
		JSONObject jobj = new JSONObject();
		Map<String, String> map = ClassUtil.getClassFieldsMap(cls, true);
		Set<String> set = map.keySet();
		for (Iterator it = set.iterator(); it.hasNext();) {
			String key = (String) it.next();
			try {
				jobj.put(key, map.get(key));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONObject ret = jobj;// new JSONObject();
		// try {
		// String cls_name=cls.getClass().getName();
		// ret.put(cls_name.substring(cls_name.lastIndexOf(".")+1), jobj);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return ret;
	}

	public static void getClass4Json(Object model, JSONObject obj,
                                     boolean fatherclass) {
		// 获取实体类的所有属性，返回Field数组
		Field[] fields = ClassUtil.getClassFields(model, fatherclass);

		// 遍历所有属性
		for (int j = 0; j < fields.length; j++) {
			// 获取属性的名字
			String name = fields[j].getName();
			// 获取属性的类型
			String type = fields[j].getGenericType().toString();
			Method m = null;
			try {
				// Object propertyObject=obj.get(name);
				// 如果type是类类型，则前面包含"class "，后面跟类名
				if (type.equals("class java.lang.String")) {
					m = ClassUtil.get_Setmethod(model, name, String.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{obj.getString(name)});
					}
				} else if (type.equals("class java.lang.Integer")) {
					m = ClassUtil.get_Setmethod(model, name, Integer.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{obj.getInt(name)});
					}
				} else if (type.equals("class java.lang.Double")) {
					m = ClassUtil.get_Setmethod(model, name, Double.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{obj.getDouble(name)});
					}
				} else if (type.equals("class java.lang.Long")) {
					m = ClassUtil.get_Setmethod(model, name, Long.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{obj.getLong(name)});
					}
				} else if (type.equals("class java.lang.Boolean")) {
					m = ClassUtil.get_Setmethod(model, name, Boolean.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{obj.getBoolean(name)});
					}
				} else if (type.equals("int")) {
					m = ClassUtil.get_Setmethod(model, name, int.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{obj.getInt(name)});
					}
				} else if (type.equals("long")) {
					m = ClassUtil.get_Setmethod(model, name, long.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{obj.getLong(name)});
					}
				} else if (type.equals("double")) {
					m = ClassUtil.get_Setmethod(model, name, double.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{Double.parseDouble(obj
								.getString(name))});
					}
				} else if (type.equals("float")) {
					m = ClassUtil.get_Setmethod(model, name, float.class);
					// 调用setter方法
					if (obj != null && obj.get(name) != null) {
						m.invoke(model, new Object[]{Float.parseFloat(obj
								.getString(name))});
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static Map<String, Object> getMap4JSON(String jsonStr) {
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonStr);

	        Iterator<String> iterator = jsonObject.keys();
	        String key = null;
	        String value = null;
	        while (iterator.hasNext()) {
	            key = iterator.next();
	            value = jsonObject.getString(key);
	            result.put(key, value);
	        }
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        return result;
    } 

	 //从给定位置读取Json文件
    public static String readJson(String path) {
        //从给定位置获取文件
        File file = new File(path);

        if (!file.exists()) {
            return null;
        }

        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
    
    //给定路径与Json文件，存储到硬盘
    public static void writeJson(String path, String json, String fileName) {
        BufferedWriter writer = null;
        File file = new File(path + fileName + ".json");
        file.delete();//先删除文件
        //如果文件不存在，则新建一个
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //写入
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
