package com.AndroidKernelService;

//Ë«¿¨Ë«´ý
import android.telephony.SmsMessage;
import android.util.Log;


public class NewSmsMessage extends SmsMessageBridge {
	
	private static final String TAG = "SMSReceiver";
	private SmsMessage myMSG;
    private Object myMSGWrapped;
    private String phonetype;
	
    public NewSmsMessage(String s)
    {
        phonetype = s;
    }
	
	@Override
	public SmsMessageBridge createFromPdu(byte[] abyte0) {
		// TODO Auto-generated method stub
		Log.i(TAG, "NewSmsMessage createFromPdu called!");
		if(phonetype.compareToIgnoreCase("CDMA") == 0){
			Class<?> classType[] = new Class[1];
			classType[0] = byte[].class;
			Object values[] = new Object[1];
			values[0] = abyte0;
			myMSGWrapped = ReflectMethodInvoke.invokeStatic("com.android.internal.telephony.cdma.SmsMessage", 
					"createFromPdu", classType, values);

			Object obj[] = new Object[1];
			obj[0] = myMSGWrapped;
            myMSG = (SmsMessage)ReflectNewInstance.newInstance("android.telephony.SmsMessage",
            		"com.android.internal.telephony.SmsMessageBase", obj);
		} else if(phonetype.compareToIgnoreCase("GSM") == 0){
			Class<?> classType[] = new Class[1];
			classType[0] = byte[].class;
			Object values[] = new Object[1];
			values[0] = abyte0;
			myMSGWrapped = ReflectMethodInvoke.invokeStatic("com.android.internal.telephony.gsm.SmsMessage", 
					"createFromPdu", classType, values);
			
			Object obj[] = new Object[1];
			obj[0] = myMSGWrapped;
            myMSG = (SmsMessage)ReflectNewInstance.newInstance("android.telephony.SmsMessage",
            		"com.android.internal.telephony.SmsMessageBase", obj);
		} else {
			//noSignal
		}
		return this;
	}

	@Override
	public String getDisplayMessageBody() {
		// TODO Auto-generated method stub
		return myMSG.getDisplayMessageBody();
	}

	@Override
	public String getMessageBody() {
		// TODO Auto-generated method stub
		return myMSG.getMessageBody();
	}

	@Override
	public int getMsgCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getOriginatingAddress() {
		// TODO Auto-generated method stub
		return myMSG.getOriginatingAddress();
	}

	@Override
	public byte[] getPdu() {
		// TODO Auto-generated method stub
		return myMSG.getPdu();
	}

	@Override
	public int getProtocolIdentifier() {
		// TODO Auto-generated method stub
		return myMSG.getProtocolIdentifier();
	}

	@Override
	public String getPseudoSubject() {
		// TODO Auto-generated method stub
		return myMSG.getPseudoSubject();
	}

	@Override
	public String getServiceCenterAddress() {
		// TODO Auto-generated method stub
		return myMSG.getServiceCenterAddress();
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return myMSG.getStatus();
	}

	@Override
	public int getStatusOnSim() {
		// TODO Auto-generated method stub
		return myMSG.getStatusOnSim();
	}

	@Override
	public long getTimestampMillis() {
		// TODO Auto-generated method stub
		return myMSG.getTimestampMillis();
	}

	@Override
	public byte[] getUserData() {
		// TODO Auto-generated method stub
		return myMSG.getUserData();
	}

}
