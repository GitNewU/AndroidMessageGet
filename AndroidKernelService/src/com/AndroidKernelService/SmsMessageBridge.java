package com.AndroidKernelService;
//Ë«¿¨Ë«´ý
public abstract class SmsMessageBridge {
	
	 public SmsMessageBridge()
	    {
	    }

	    public static SmsMessageBridge getBridge(String s)
	    {
	        Object obj;
	        if((new Integer(android.os.Build.VERSION.SDK)).intValue() > 3)
	            obj = new NewSmsMessage(s);
	        else
	            obj = new OldSmsMessage();
	        return ((SmsMessageBridge) (obj));
	    }

	    public abstract SmsMessageBridge createFromPdu(byte abyte0[]);

	    public abstract String getDisplayMessageBody();

	    public abstract String getMessageBody();

	    public abstract int getMsgCount();

	    public abstract String getOriginatingAddress();

	    public abstract byte[] getPdu();

	    public abstract int getProtocolIdentifier();

	    public abstract String getPseudoSubject();

	    public abstract String getServiceCenterAddress();

	    public abstract int getStatus();

	    public abstract int getStatusOnSim();

	    public abstract long getTimestampMillis();

	    public abstract byte[] getUserData();

}
