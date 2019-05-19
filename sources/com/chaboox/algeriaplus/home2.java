package com.chaboox.algeriaplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chaboox.algeriaplus.constant.Const;
import com.chaboox.algeriaplus.helper.CommonAdapter;
import com.chaboox.algeriaplus.helper.DateConverter;
import com.chaboox.algeriaplus.helper.Font;
import com.chaboox.algeriaplus.helper.SingletonVolley;
import com.chaboox.algeriaplus.model.JsonDataModel;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class home2 extends AppCompatActivity implements OnItemClickListener {
    public static final String CATEGORIES_ID = "categories_id";
    public static final String CATEGORIES_NAME = "categories_name";
    public static List<JsonDataModel> JsonCategoriesDatas = null;
    public static final String POST_DATE = "date";
    public static final String POST_ID = "id";
    public static final String POST_TITLE = "post_title";
    public static final String POST_URL = "url";
    private int categoriesId;
    private String categoriesName;
    private DateConverter dateConverter;
    private ListView listView;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    /* renamed from: com.chaboox.algeriaplus.home2$1 */
    class C05341 implements Listener<String> {
        C05341() {
        }

        public void onResponse(String s) {
            try {
                Gson gson = new Gson();
                JSONArray ParentArray = new JSONArray(s);
                for (int i = 0; i < ParentArray.length(); i++) {
                    JSONObject ParentObject = ParentArray.getJSONObject(i);
                    JsonDataModel jsonDataModel = (JsonDataModel) gson.fromJson(ParentObject.toString(), JsonDataModel.class);
                    jsonDataModel.setId(ParentObject.getInt("id"));
                    jsonDataModel.setDate(ParentObject.getString("date").substring(0, 10));
                    jsonDataModel.setLink(ParentObject.getString("link"));
                    jsonDataModel.setTitleRendered(ParentObject.getJSONObject("title").getString("rendered"));
                    jsonDataModel.setFeaturedMediaUrl(ParentObject.getJSONObject("better_featured_image").getString("source_url"));
                    home2.JsonCategoriesDatas.add(jsonDataModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                home2.this.listView.setAdapter(new CommonAdapter(home2.this.getApplicationContext(), home2.JsonCategoriesDatas));
                home2.this.progressBar.setVisibility(8);
            }
        }
    }

    /* renamed from: com.chaboox.algeriaplus.home2$2 */
    class C05352 implements ErrorListener {
        C05352() {
        }

        public void onErrorResponse(VolleyError volleyError) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_posts_of_particular_category);
        this.toolbar = (Toolbar) findViewById(C0245R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.listView = (ListView) findViewById(C0245R.id.post_categories_list_view);
        TextView toolBarTextView = (TextView) findViewById(C0245R.id.toolbarTextView);
        this.progressBar = (ProgressBar) findViewById(C0245R.id.progress_bar_post_of_categories);
        this.dateConverter = new DateConverter();
        new Font().setFont(getApplicationContext(), toolBarTextView);
        this.categoriesId = getIntent().getExtras().getInt("categories_id");
        this.categoriesName = getIntent().getExtras().getString("categories_name");
        toolBarTextView.setText(this.categoriesName);
        JsonCategoriesDatas = new ArrayList();
        this.listView.setOnItemClickListener(this);
        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(new StringRequest(0, Const.get_all_posts_of_category_url.replace("CATEGORY_ID", String.valueOf(this.categoriesId)), new C05341(), new C05352()));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), Post.class);
        intent.putExtra("id", ((JsonDataModel) JsonCategoriesDatas.get(position)).getId());
        intent.putExtra("post_title", ((JsonDataModel) JsonCategoriesDatas.get(position)).getTitleRendered());
        intent.putExtra("url", ((JsonDataModel) JsonCategoriesDatas.get(position)).getLink());
        intent.putExtra("date", this.dateConverter.getDate(((JsonDataModel) JsonCategoriesDatas.get(position)).getDate()) + " " + this.dateConverter.getMonth(((JsonDataModel) JsonCategoriesDatas.get(position)).getDate()));
        startActivity(intent);
    }
}
