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
			// ֻҪ�ڷ���getSimStateGemini �������ʱ���˴���ǵ����ֻ����������Լ��ľ��飬��һ��ȫ��ȷ��
			method = TelephonyManager.class.getMethod("getSimStateGemini",
					new Class[] { int.class });
			// ��ȡSIM��1
			result_0 = method.invoke(tm, new Object[] { new Integer(0) });
			// ��ȡSIM��2
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
			//����쳣
			System.exit(0);
		}
		
		//��̬ע��Ĵ���
	}

	public void isMultiSim() {
		// �ж�˫��˫����
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
