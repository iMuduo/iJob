package cn.edu.ustc.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IntervalThread implements Runnable {
	private long millis;
	private Map<Pair<Object, String>, Object[]> delegates = null;
	private Map<String,Pair<Object, String>> map=null; 

	public IntervalThread(long millis) {
		this.millis = millis;
		this.delegates=new HashMap<Pair<Object,String>, Object[]>();
		this.map=new HashMap<>();
	}

	@Override
	public void run() {
		Method method;
		Object[] args;
		Class<?>[] types;
		for (Pair<Object, String> pair : delegates.keySet()) {
			args = delegates.get(pair);
			types = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++)
				types[i] = args[i].getClass();

			try {
				method = pair.getFirst().getClass()
						.getMethod(pair.getSecond(), types);
				method.invoke(pair.getFirst(), args);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		try {
			Thread.sleep(millis);
			this.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public IntervalThread delegate(Object obj, String method,Object[] args)
	{
		Pair<Object, String> pair;
		this.delegates.put(pair=new Pair<Object, String>(obj, method), args);
		map.put(pair.toString(), pair);
		return this;
	}

	public IntervalThread removeDelegate(Object obj, String method)
	{
		this.delegates.remove(map.get(new Pair<Object, String>(obj, method).toString()));
		return this;
	}
}
