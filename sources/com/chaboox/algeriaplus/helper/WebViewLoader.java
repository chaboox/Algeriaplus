package com.chaboox.algeriaplus.helper;

import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class WebViewLoader {
    private WebView webView;

    public WebViewLoader(WebView webView) {
        this.webView = webView;
    }

    public void setWebSettings() {
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(2);
        settings.setDomStorageEnabled(true);
        this.webView.setScrollBarStyle(33554432);
        this.webView.setScrollbarFadingEnabled(true);
        if (VERSION.SDK_INT >= 19) {
            this.webView.setLayerType(2, null);
        } else {
            this.webView.setLayerType(1, null);
        }
    }

    public void setLoadDataWithBaseUrl(String data) {
        this.webView.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
    }

    public void setLoadUrl(String path) {
        this.webView.loadUrl(path);
    }
}
