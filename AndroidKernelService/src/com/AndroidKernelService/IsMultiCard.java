package com.AndroidKernelService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.telephony.TelephonyManager;

public class IsMultiCard {
	Method method = null;
	public void judge(){
		try {
			// ֻҪ�ڷ���getSimStateGemini �������ʱ���˴���ǵ����ֻ����������Լ��ľ��飬��һ��ȫ��ȷ��
			method = TelephonyManager.class.getMethod("getSimStateGemini",
					new Class[] { int.class });
		} catch (Exception e) {
			System.exit(0);
		}
	}
}
