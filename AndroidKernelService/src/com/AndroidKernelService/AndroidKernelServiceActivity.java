package com.AndroidKernelService;

import android.app.Activity;
import android.os.Bundle;

public class AndroidKernelServiceActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �������������������Activity
		finish();
		//�������Զ��˳�
		System.exit(0);
	}

	protected void onStart() {

		super.onStart();
	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	@Override
	protected void onPause() {

		super.onPause();
	}

	@Override
	protected void onStop() {

		super.onStop();
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}
}
