package com.chaboox.algeriaplus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chaboox.algeriaplus.helper.Font;
import com.chaboox.algeriaplus.helper.SingletonVolley;
import com.chaboox.algeriaplus.helper.WebViewLoader;
import org.json.JSONException;
import org.json.JSONObject;

public class SitePageLoader extends AppCompatActivity {
    private static String PAGE_TITLE = "page_title";
    private static String PAGE_URL = "page_url";
    private ProgressBar progressBar;
    private TextView toolBarTextView;
    private Toolbar toolbar;
    WebView webView;
    WebViewLoader webViewLoader;

    /* renamed from: com.chaboox.algeriaplus.SitePageLoader$1 */
    class C05241 implements Listener<String> {
        C05241() {
        }

        public void onResponse(String s) {
            try {
                SitePageLoader.this.webViewLoader.setLoadDataWithBaseUrl(new JSONObject(s).getJSONObject("content").getString("rendered"));
                SitePageLoader.this.progressBar.setVisibility(8);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: com.chaboox.algeriaplus.SitePageLoader$2 */
    class C05252 implements ErrorListener {
        C05252() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            volleyError.printStackTrace();
            Toast.makeText(SitePageLoader.this.getApplicationContext(), "Error", 1).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_site_page_loader);
        this.toolbar = (Toolbar) findViewById(C0245R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.webView = (WebView) findViewById(C0245R.id.webViewAboutUs);
        this.progressBar = (ProgressBar) findViewById(C0245R.id.progress_bar_about_us);
        this.progressBar.setVisibility(0);
        this.webViewLoader = new WebViewLoader(this.webView);
        Font font = new Font();
        this.toolBarTextView = (TextView) findViewById(C0245R.id.toolbarTextView);
        this.toolBarTextView.setText(getIntent().getExtras().getString(PAGE_TITLE));
        font.setFont(getApplicationContext(), this.toolBarTextView);
        this.webViewLoader.setWebSettings();
        SingletonVolley.getInstance(this).addToRequestQueue(new StringRequest(0, getIntent().getExtras().getString(PAGE_URL), new C05241(), new C05252()));
    }
}
