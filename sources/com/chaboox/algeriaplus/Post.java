package com.chaboox.algeriaplus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chaboox.algeriaplus.constant.Const;
import com.chaboox.algeriaplus.helper.Font;
import com.chaboox.algeriaplus.helper.SingletonVolley;
import com.chaboox.algeriaplus.helper.WebViewLoader;
import com.github.clans.fab.FloatingActionMenu;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;
import org.json.JSONException;
import org.json.JSONObject;

public class Post extends AppCompatActivity implements OnClickListener {
    public static final String POST_DATE = "date";
    public static final String POST_ID = "id";
    public static final String POST_IMAGE = "image";
    public static final String POST_TITLE = "post_title";
    public static final String POST_URL = "url";
    WebView content;
    private TextView date;
    private FloatingActionMenu fabMore;
    private ImageView imageView;
    private String postTitle;
    ProgressDialog progressDialog;
    private FloatingActionButton share;

    /* renamed from: com.chaboox.algeriaplus.Post$1 */
    class C05151 implements ImageLoadingListener {
        C05151() {
        }

        public void onLoadingStarted(String s, View view) {
        }

        public void onLoadingFailed(String s, View view, FailReason failReason) {
        }

        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
        }

        public void onLoadingCancelled(String s, View view) {
        }
    }

    /* renamed from: com.chaboox.algeriaplus.Post$3 */
    class C05173 implements ErrorListener {
        C05173() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            Post.this.progressDialog.dismiss();
            Toast.makeText(Post.this, "Unable to Load", 1).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(C0245R.id.toolbar);
        int id = getIntent().getExtras().getInt("id");
        this.postTitle = "l " + getIntent().getExtras().getString("post_title");
        toolbar.setTitle(this.postTitle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.imageView = (ImageView) findViewById(C0245R.id.imageView2);
        this.content = (WebView) findViewById(C0245R.id.content);
        final WebViewLoader webViewLoader = new WebViewLoader(this.content);
        webViewLoader.setWebSettings();
        this.share = (FloatingActionButton) findViewById(C0245R.id.share);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(C0245R.id.collaps);
        collapsingToolbar.setCollapsedTitleTextAppearance(C0245R.style.CollapsedAppBar);
        collapsingToolbar.setExpandedTitleTextAppearance(C0245R.style.ExpandedAppBar);
        Font font = new Font();
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage("Loading...");
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.show();
        this.share.setOnClickListener(this);
        String url = Const.get_content_by_id.replace("POST_ID", String.valueOf(id));
        System.out.println("yoo " + url);
        ImageLoader.getInstance().displayImage(getIntent().getExtras().getString("image"), this.imageView, new C05151());
        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(new StringRequest(0, url, new Listener<String>() {
            public void onResponse(String s) {
                try {
                    webViewLoader.setLoadDataWithBaseUrl("<style>img{display: inline;height: auto;max-width: 100%;}@font-face{font-family: MyFont;src: url(\"file:///android_asset/font/arabarticle.ttf\")}body{font-family: MyFont;font-size:70%}</style><html dir=\"rtl\" lang=\"\"><body>" + new JSONObject(s).getJSONObject("content").getString("rendered") + "</body></html>");
                    Post.this.progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new C05173()));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case C0245R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", getIntent().getExtras().getString("url"));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Choose any"));
                return;
            default:
                return;
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
