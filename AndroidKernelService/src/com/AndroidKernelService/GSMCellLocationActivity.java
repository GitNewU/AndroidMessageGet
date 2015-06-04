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

//获取基站信息的函数

/**
 * 功能描述：通过手机信号获取基站信息 # 通过TelephonyManager 获取lac:mcc:mnc:cell-id # MCC，Mobile
 * Country Code，移动国家代码（中国的为460）； # MNC，Mobile Network
 * Code，移动网络号码（中国移动为0，中国联通为1，中国电信为2）； # LAC，Location Area Code，位置区域码； # CID，Cell
 * Identity，基站编号； # BSSS，Base station signal strength，基站信号强度。
 * 
 * @author android_ls
 */
public class GSMCellLocationActivity extends Application {

	// 得到TelephonyManager后，由于针对不同的运营商，代码有所不同，所以需要判断getNetworkType()
	// 在源代码里面有如下的类型定义
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
		// 获取基站信息		
	}

	public String GetJZ() {

		// TODO Auto-generated method stub
		TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		int type = mTelephonyManager.getNetworkType();

		// 返回值MCC + MNC
		String operator = mTelephonyManager.getNetworkOperator();
		int mcc = Integer.parseInt(operator.substring(0, 3));
		int mnc = Integer.parseInt(operator.substring(3));

		// 中国电信为CTC
		// NETWORK_TYPE_EVDO_A是中国电信3G的getNetworkType
		// NETWORK_TYPE_CDMA电信2G是CDMA
		int lac = 0, cellId = 0;
		if (type == TelephonyManager.NETWORK_TYPE_EVDO_A
				|| type == TelephonyManager.NETWORK_TYPE_CDMA
				|| type == TelephonyManager.NETWORK_TYPE_1xRTT) {
			// 中国电信获取LAC、CID的方式
			CdmaCellLocation location1 = (CdmaCellLocation) mTelephonyManager
					.getCellLocation();
			lac = location1.getNetworkId();
			cellId = location1.getBaseStationId();

			cellId /= 16;
			Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac
					+ "\t CID = " + cellId);
		}
		// 移动2G卡 + CMCC + 2
		// type = NETWORK_TYPE_EDGE
		// 联通的2G经过测试 China Unicom 1 NETWORK_TYPE_GPRS
		else {
			// 中国移动和中国联通获取LAC、CID的方式
			GsmCellLocation location = (GsmCellLocation) mTelephonyManager
					.getCellLocation();
			lac = location.getLac();
			cellId = location.getCid();
		}
		Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac
				+ "\t CID = " + cellId);

		// 获取邻区基站信息
		List<NeighboringCellInfo> infos = mTelephonyManager
				.getNeighboringCellInfo();
		StringBuffer sb = new StringBuffer("总数 : " + infos.size() + "\n");
		for (NeighboringCellInfo info1 : infos) { // 根据邻区总数进行循环
			sb.append(" LAC : " + info1.getLac()); // 取出当前邻区的LAC
			sb.append(" CID : " + info1.getCid()); // 取出当前邻区的CID
			sb.append(" BSSS : " + (-113 + 2 * info1.getRssi()) + "\n"); // 获取邻区基站信号强度
		}
		Log.i(TAG, " 获取邻区基站信息:" + sb.toString());
		sb.append(" MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac
				+ "\t CID = " + cellId);
		return sb.toString();
	}

}
