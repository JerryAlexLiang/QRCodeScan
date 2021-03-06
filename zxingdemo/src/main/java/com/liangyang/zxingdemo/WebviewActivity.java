package com.liangyang.zxingdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 扫描结果如果是URL，则跳转到webView
 */
public class WebviewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        //接收数据
        Intent intent = getIntent();
        String url = intent.getStringExtra("data");
        System.out.println("======>  " + url);
        //初始化视图
        mWebView = (WebView) findViewById(R.id.webView);
        //设置
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                /**
                 * webView加载网页出现("找不到网页net:err_unknown_url_scheme")
                 * 解决方法：以"http","https"开头的url在本页用webview进行加载，其他链接进行跳转
                 */
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return true;
                }
                return true;
            }
        });
        mWebView.loadUrl(url);
    }
}
