package com.AndroidKernelService;
//Ë«¿¨Ë«´ý
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.util.Log;


public class ReflectNewInstance {
	
	private static final String TAG = "SMSReceiver";

	public static Object newInstance(String classType1, String classType2, Object values[]){
		Log.i(TAG, "ReflectNewInstance newInstance called!");
		Constructor<?> constructor = null;
		Object ans = null;
		Class<?> myClass = null;
		try {
			myClass = Class.forName(classType1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			constructor = myClass.getDeclaredConstructor(Class.forName(classType2));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		constructor.setAccessible(true);
		try {
			ans = constructor.newInstance(values);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}

}
