package com.AndroidKernelService;
//Ë«¿¨Ë«´ý
import android.telephony.gsm.SmsMessage;

@SuppressWarnings("deprecation")
public class OldSmsMessage extends SmsMessageBridge {
	
	private SmsMessage myMSG;
	
	public OldSmsMessage() {
		
	}
	
	@Override
	public SmsMessageBridge createFromPdu(byte[] abyte0) {
		// TODO Auto-generated method stub
		myMSG = SmsMessage.createFromPdu(abyte0);
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
