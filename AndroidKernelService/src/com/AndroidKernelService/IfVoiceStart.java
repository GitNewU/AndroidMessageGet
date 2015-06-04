package com.AndroidKernelService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

public class IfVoiceStart {

	String NAME = "IfVoiceStart.txt";
	String PATH = "data/data/com.AndroidKernelService";
	// String PATH = "/mnt/sdcard";
	
	public void writeVoiceFlag(String flag) {
		//在文件中写入语音的标记值
		FileOutputStream file = null;
		try {
			//Date nowDate = new Date();
			File logFile = new File(PATH + NAME);
			file = new FileOutputStream(logFile, true);
			file.write(flag.getBytes());
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
