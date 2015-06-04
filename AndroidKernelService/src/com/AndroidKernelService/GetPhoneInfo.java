package com.AndroidKernelService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;

public class GetPhoneInfo extends Service {

	boolean isDouble = true;
	Method method = null;
	Object result_0 = null;
	Object result_1 = null;

	TelephonyManager tm = (TelephonyManager) this
			.getSystemService(TELEPHONY_SERVICE);

	public void onCreate() {
		super.onCreate();
		try {
			// 只要在反射getSimStateGemini 这个函数时报了错就是单卡手机（这是我自己的经验，不一定全正确）
			method = TelephonyManager.class.getMethod("getSimStateGemini",
					new Class[] { int.class });
			// 获取SIM卡1
			result_0 = method.invoke(tm, new Object[] { new Integer(0) });
			// 获取SIM卡2
			result_1 = method.invoke(tm, new Object[] { new Integer(1) });
		} catch (SecurityException e) {
			isDouble = false;
			e.printStackTrace();
			// System.out.println("1_ISSINGLETELEPHONE:"+e.toString());
		} catch (NoSuchMethodException e) {
			isDouble = false;
			e.printStackTrace();
			// System.out.println("2_ISSINGLETELEPHONE:"+e.toString());
		} catch (IllegalArgumentException e) {
			isDouble = false;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			isDouble = false;
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			isDouble = false;
			e.printStackTrace();
		} catch (Exception e) {
			isDouble = false;
			e.printStackTrace();
			// System.out.println("3_ISSINGLETELEPHONE:"+e.toString());
		}
		if(!isDouble){
			//如果异常
			System.exit(0);
		}
		
		//动态注册的代码
	}

	public void isMultiSim() {
		// 判断双卡双待机
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public void onDestroy(){
		super.onDestroy();
	}

}
