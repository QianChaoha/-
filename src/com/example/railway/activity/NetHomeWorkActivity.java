package com.example.railway.activity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.railway.R;
import com.example.railway.base.BaseActivity;

public class NetHomeWorkActivity extends BaseActivity {
	WebView mWebView;
	ProgressBar mProgressBar;
	private String mURL = "";

	@Override
	protected int getLayoutId() {
		return R.layout.activity_bill_form;
	}


	@Override
	protected boolean setNoTitle() {
		return true;
	}

	private class WebClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onLoadResource(WebView view, String url) {
			super.onLoadResource(view, url);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			mProgressBar.setVisibility(View.GONE);
		}
	}

	@Override
	protected void initView() {
		mURL=getIntent().getStringExtra("url");
		mWebView = (WebView) findViewById(R.id.webView);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		mWebView.setWebViewClient(new WebClient());
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl(mURL);
		
	}

	@Override
	protected void initData() {
		
	}
}
