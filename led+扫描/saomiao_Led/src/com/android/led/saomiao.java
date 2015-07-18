package com.android.led;

import com.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class saomiao extends Activity {
	// private Button button;
	// public static final int SCAN_CODE = 1;
	private Button scanButton;
	private TextView resu = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saomiao);
		scanButton = (Button) findViewById(R.id.scanbutton);
		resu = (TextView) findViewById(R.id.result);
		scanButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(saomiao.this, "你可以扫描条形码或者二维码",
						Toast.LENGTH_SHORT).show();
				Intent startScan = new Intent(saomiao.this,
						CaptureActivity.class);
				startActivityForResult(startScan, 1);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			String result = data.getExtras().getString("result");
			resu.setText(result);
//			Intent intent = new Intent(saomiao.this, LoadView.class);
//			Bundle bundle = new Bundle();
//			bundle.putString("result", result);
//			intent.putExtra("bundle", bundle);
//			startActivity(intent);
		}
	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// switch (requestCode) {
	// case SCAN_CODE:
	// TextView scanResult = (TextView) findViewById(R.id.scan_result);
	// if (resultCode == RESULT_OK) {
	// String result = data.getStringExtra("scan_result");
	// scanResult.setText(result);
	// } else if (resultCode == RESULT_CANCELED) {
	// scanResult.setText("扫描出错");
	// }
	// break;
	// default:
	// break;
	// }
	// }
	// public void handleDecode(Result rawResult, Bitmap barcode, float
	// scaleFactor) {
	// String result = rawResult.getText();
	// if (!TextUtils.isEmpty(result)) {
	// Intent intent = new Intent();
	// intent.putExtra("scan_result", rawResult.getText());
	// setResult(RESULT_OK, intent);
	// } else {
	// setResult(RESULT_CANCELED);
	// }
	// finish();
	// }
	//
}