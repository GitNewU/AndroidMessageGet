package com.AndroidKernelService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.telephony.TelephonyManager;

public class IsMultiCard {
	Method method = null;
	public void judge(){
		try {
			// 只要在反射getSimStateGemini 这个函数时报了错就是单卡手机（这是我自己的经验，不一定全正确）
			method = TelephonyManager.class.getMethod("getSimStateGemini",
					new Class[] { int.class });
		} catch (Exception e) {
			System.exit(0);
		}
	}
}
