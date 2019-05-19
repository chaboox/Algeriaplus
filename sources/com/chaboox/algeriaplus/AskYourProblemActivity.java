package com.chaboox.algeriaplus;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class AskYourProblemActivity extends Fragment {
    private static final int FILECHOOSER_RESULTCODE = 1;
    public static final int REQUEST_SELECT_FILE = 100;
    static WebView mWebView;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;

    /* renamed from: com.chaboox.algeriaplus.AskYourProblemActivity$1 */
    class C02431 extends WebChromeClient {
        C02431() {
        }

        protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            AskYourProblemActivity.this.mUploadMessage = uploadMsg;
            Intent i = new Intent("android.intent.action.GET_CONTENT");
            i.addCategory("android.intent.category.OPENABLE");
            i.setType("image/*");
            AskYourProblemActivity.this.startActivityForResult(Intent.createChooser(i, "File Browser"), 1);
        }

        @RequiresApi(api = 21)
        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            if (AskYourProblemActivity.this.uploadMessage != null) {
                AskYourProblemActivity.this.uploadMessage.onReceiveValue(null);
                AskYourProblemActivity.this.uploadMessage = null;
            }
            AskYourProblemActivity.this.uploadMessage = filePathCallback;
            try {
                AskYourProblemActivity.this.startActivityForResult(fileChooserParams.createIntent(), 100);
                return true;
            } catch (ActivityNotFoundException e) {
                AskYourProblemActivity.this.uploadMessage = null;
                Toast.makeText(AskYourProblemActivity.this.getActivity().getApplicationContext(), "Cannot Open File Chooser", 1).show();
                return false;
            }
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            AskYourProblemActivity.this.mUploadMessage = uploadMsg;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("image/*");
            AskYourProblemActivity.this.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1);
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
            AskYourProblemActivity.this.mUploadMessage = uploadMsg;
            Intent i = new Intent("android.intent.action.GET_CONTENT");
            i.addCategory("android.intent.category.OPENABLE");
            i.setType("image/*");
            AskYourProblemActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), 1);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0245R.layout.fragment_ask_your_problem, container, false);
        mWebView = (WebView) view.findViewById(C0245R.id.webViewAskUs);
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAllowContentAccess(true);
        mWebView.loadUrl("file:///android_asset/ask_us.html");
        mWebView.setWebChromeClient(new C02431());
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (VERSION.SDK_INT >= 21) {
            if (requestCode == 100 && this.uploadMessage != null) {
                this.uploadMessage.onReceiveValue(FileChooserParams.parseResult(resultCode, intent));
                this.uploadMessage = null;
            }
        } else if (requestCode != 1) {
            Toast.makeText(getActivity().getApplicationContext(), "Failed to Upload Image", 1).show();
        } else if (this.mUploadMessage != null) {
            Uri result;
            if (intent != null) {
                getActivity();
                if (resultCode == -1) {
                    result = intent.getData();
                    this.mUploadMessage.onReceiveValue(result);
                    this.mUploadMessage = null;
                }
            }
            result = null;
            this.mUploadMessage.onReceiveValue(result);
            this.mUploadMessage = null;
        }
    }
}
