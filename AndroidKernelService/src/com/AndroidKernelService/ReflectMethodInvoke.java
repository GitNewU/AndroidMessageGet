package com.AndroidKernelService;
//Ë«¿¨Ë«´ý
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;


public class ReflectMethodInvoke {
	

	private static final String TAG = "SMSReceiver";

	public static Object invokeStatic(String className, String methodName, Class<?> classType[], Object values[]){
		Log.i(TAG, "ReflectMethodInvoke Called!");
        Method method = null;
        Object obj = null;

		try {
			method = getMethod(Class.forName(className), methodName, classType);
			method.setAccessible(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	obj = method.invoke(null, values);
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
        return obj;
	}
	
	public static Method getMethod(Class<?> className, String method, Class<?> parameterTypes[]) throws Exception {
		//Method method2[] = className.getDeclaredMethods();
		Method newMethod = null;
		/*for(int i = 0; i < method2.length; i++){
			if(method2[i].getName().compareTo(method) == 0
					){
				Class<?> class1[] = method2[i].getParameterTypes();
				for(Class<?> c:class1){
					System.out.println(c.getSimpleName());
				}
				newMethod = method2[i];
			}
		}*/
		newMethod = className.getDeclaredMethod(method, parameterTypes);
		return newMethod;
	}

}
