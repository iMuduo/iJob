package cn.edu.ustc.common;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;

public abstract class ActiveRecord {
	public void setMap(Map<String, ?> map) {
		if (map != null) {
			try {
				Field[] fields = this.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(this, map.get(field.getName()));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public Map<String, Object> getMap() {
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object obj = field.get(this);
				map.put(field.getName(), obj == null ? "" : obj);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	}

	public void setField(String name, Object value) {
		try {
			Field field = this.getClass().getDeclaredField(name);
			field.setAccessible(true);
			field.set(this, value);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public Object getField(String name) {
		Object obj = null;
		try {
			Field field = this.getClass().getDeclaredField(name);
			field.setAccessible(true);
			obj = field.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
