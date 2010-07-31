package com.roman.updater;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

//By r0man, for KingKlick, demo'd webview updater application
//Copyleft'd

public class KingKlickUpdater extends Activity {

    private static final String LOG_TAG = "KKU";

    private WebView mWebView;

    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        mWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        mWebView.setWebChromeClient(new MyWebChromeClient());

        mWebView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
//The loadUrl("http://x.com") is where you will want to insert the folder on your
//website that has the subdirectories for each ROM
//Recommended layout:
// kingklick.com/updater -=|slide2g1=|froyo2g1=|etc
        mWebView.loadUrl("http://kingxklick.com/roms");
    }

    final class DemoJavaScriptInterface {

        DemoJavaScriptInterface() {
        }

        public void clickOnAndroid() {
            mHandler.post(new Runnable() {
                public void run() {
                    mWebView.loadUrl("javascript:wave()");
                }
            });

        }
    }

    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(LOG_TAG, message);
            result.confirm();
            return true;
        }
    }
}