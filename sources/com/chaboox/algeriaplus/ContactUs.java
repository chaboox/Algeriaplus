package com.chaboox.algeriaplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;
import com.chaboox.algeriaplus.helper.Font;
import com.chaboox.algeriaplus.helper.WebViewLoader;

public class ContactUs extends AppCompatActivity {
    private TextView toolBarTextView;
    private WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_contact_us);
        setSupportActionBar((Toolbar) findViewById(C0245R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Font font = new Font();
        this.toolBarTextView = (TextView) findViewById(C0245R.id.toolbarTextView);
        this.toolBarTextView.setText("Contact Us");
        font.setFont(getApplicationContext(), this.toolBarTextView);
        this.webView = (WebView) findViewById(C0245R.id.webViewContactUs);
        WebViewLoader webViewLoader = new WebViewLoader(this.webView);
        webViewLoader.setWebSettings();
        webViewLoader.setLoadUrl("file:///android_asset/contact_us.html");
    }

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Home.class));
        finish();
    }
}
