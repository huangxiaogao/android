package com.android.led;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoadView extends Activity {
	private WebView web = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.load_url);
		web = (WebView) findViewById(R.id.webview);
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		String result = bundle.getString("result");
		web.loadUrl(result);
	

		     

	}

		        

		   } 
	


