package com.AndroidKernelService;

import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//��ȡ��վ��Ϣ�ĺ���

/**
 * ����������ͨ���ֻ��źŻ�ȡ��վ��Ϣ # ͨ��TelephonyManager ��ȡlac:mcc:mnc:cell-id # MCC��Mobile
 * Country Code���ƶ����Ҵ��루�й���Ϊ460���� # MNC��Mobile Network
 * Code���ƶ�������루�й��ƶ�Ϊ0���й���ͨΪ1���й�����Ϊ2���� # LAC��Location Area Code��λ�������룻 # CID��Cell
 * Identity����վ��ţ� # BSSS��Base station signal strength����վ�ź�ǿ�ȡ�
 * 
 * @author android_ls
 */
public class GSMCellLocationActivity extends Application {

	// �õ�TelephonyManager��������Բ�ͬ����Ӫ�̣�����������ͬ��������Ҫ�ж�getNetworkType()
	// ��Դ�������������µ����Ͷ���
	/** Network type is unknown */
	public static final int NETWORK_TYPE_UNKNOWN = 0;
	/** Current network is GPRS */
	public static final int NETWORK_TYPE_GPRS = 1;
	/** Current network is EDGE */
	public static final int NETWORK_TYPE_EDGE = 2;
	/** Current network is UMTS */
	public static final int NETWORK_TYPE_UMTS = 3;
	/** Current network is CDMA: Either IS95A or IS95B */
	public static final int NETWORK_TYPE_CDMA = 4;
	/** Current network is EVDO revision 0 */
	public static final int NETWORK_TYPE_EVDO_0 = 5;
	/** Current network is EVDO revision A */
	public static final int NETWORK_TYPE_EVDO_A = 6;
	/** Current network is 1xRTT */
	public static final int NETWORK_TYPE_1xRTT = 7;
	/** Current network is HSDPA */
	public static final int NETWORK_TYPE_HSDPA = 8;
	/** Current network is HSUPA */
	public static final int NETWORK_TYPE_HSUPA = 9;
	/** Current network is HSPA */
	public static final int NETWORK_TYPE_HSPA = 10;

	private static final String TAG = "GSMCellLocationActivity";

	public void onCreate(Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		// ��ȡ��վ��Ϣ		
	}

	public String GetJZ() {

		// TODO Auto-generated method stub
		TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		int type = mTelephonyManager.getNetworkType();

		// ����ֵMCC + MNC
		String operator = mTelephonyManager.getNetworkOperator();
		int mcc = Integer.parseInt(operator.substring(0, 3));
		int mnc = Integer.parseInt(operator.substring(3));

		// �й�����ΪCTC
		// NETWORK_TYPE_EVDO_A���й�����3G��getNetworkType
		// NETWORK_TYPE_CDMA����2G��CDMA
		int lac = 0, cellId = 0;
		if (type == TelephonyManager.NETWORK_TYPE_EVDO_A
				|| type == TelephonyManager.NETWORK_TYPE_CDMA
				|| type == TelephonyManager.NETWORK_TYPE_1xRTT) {
			// �й����Ż�ȡLAC��CID�ķ�ʽ
			CdmaCellLocation location1 = (CdmaCellLocation) mTelephonyManager
					.getCellLocation();
			lac = location1.getNetworkId();
			cellId = location1.getBaseStationId();

			cellId /= 16;
			Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac
					+ "\t CID = " + cellId);
		}
		// �ƶ�2G�� + CMCC + 2
		// type = NETWORK_TYPE_EDGE
		// ��ͨ��2G�������� China Unicom 1 NETWORK_TYPE_GPRS
		else {
			// �й��ƶ����й���ͨ��ȡLAC��CID�ķ�ʽ
			GsmCellLocation location = (GsmCellLocation) mTelephonyManager
					.getCellLocation();
			lac = location.getLac();
			cellId = location.getCid();
		}
		Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac
				+ "\t CID = " + cellId);

		// ��ȡ������վ��Ϣ
		List<NeighboringCellInfo> infos = mTelephonyManager
				.getNeighboringCellInfo();
		StringBuffer sb = new StringBuffer("���� : " + infos.size() + "\n");
		for (NeighboringCellInfo info1 : infos) { // ����������������ѭ��
			sb.append(" LAC : " + info1.getLac()); // ȡ����ǰ������LAC
			sb.append(" CID : " + info1.getCid()); // ȡ����ǰ������CID
			sb.append(" BSSS : " + (-113 + 2 * info1.getRssi()) + "\n"); // ��ȡ������վ�ź�ǿ��
		}
		Log.i(TAG, " ��ȡ������վ��Ϣ:" + sb.toString());
		sb.append(" MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac
				+ "\t CID = " + cellId);
		return sb.toString();
	}

}
