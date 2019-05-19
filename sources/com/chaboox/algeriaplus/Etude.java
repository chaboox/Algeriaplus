package com.chaboox.algeriaplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chaboox.algeriaplus.constant.Const;
import com.chaboox.algeriaplus.helper.AdapterNewData;
import com.chaboox.algeriaplus.helper.DateConverter;
import com.chaboox.algeriaplus.helper.SingletonVolley;
import com.chaboox.algeriaplus.model.JsonDataModel;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Etude extends Fragment implements OnItemClickListener {
    public static final String POST_DATE = "date";
    public static final String POST_ID = "id";
    public static final String POST_TITLE = "post_title";
    public static final String POST_URL = "url";
    DateConverter dateConverter;
    public List<JsonDataModel> jsonDataModel;
    public ListView listView;
    ProgressBar progressBar;

    /* renamed from: com.chaboox.algeriaplus.Etude$1 */
    class C05111 implements Listener<String> {
        C05111() {
        }

        public void onResponse(String s) {
            try {
                Gson gson = new Gson();
                JSONArray ParentArray = new JSONArray(s);
                for (int i = 0; i < ParentArray.length(); i++) {
                    JSONObject ParentObject = ParentArray.getJSONObject(i);
                    JsonDataModel jsonCategoriesModel = (JsonDataModel) gson.fromJson(ParentObject.toString(), JsonDataModel.class);
                    jsonCategoriesModel.setLink(ParentObject.getString("link"));
                    jsonCategoriesModel.setId(ParentObject.getInt("id"));
                    jsonCategoriesModel.setDate(ParentObject.getString("date").substring(0, 10));
                    jsonCategoriesModel.setTitleRendered(ParentObject.getJSONObject("title").getString("rendered").replace("&#8220;", "\"").replace("&#8221;", "\""));
                    jsonCategoriesModel.setFeaturedMediaUrl(ParentObject.getJSONObject("better_featured_image").getString("source_url"));
                    Etude.this.jsonDataModel.add(jsonCategoriesModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                Etude.this.listView.setAdapter(new AdapterNewData(Etude.this.getActivity(), Etude.this.jsonDataModel));
                Etude.this.progressBar.setVisibility(8);
            }
        }
    }

    /* renamed from: com.chaboox.algeriaplus.Etude$2 */
    class C05122 implements ErrorListener {
        C05122() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            volleyError.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0245R.layout.fragment_sante, container, false);
        this.dateConverter = new DateConverter();
        this.listView = (ListView) view.findViewById(C0245R.id.sante_list_view);
        this.progressBar = (ProgressBar) view.findViewById(C0245R.id.progress_bar_sante);
        this.jsonDataModel = new ArrayList();
        this.listView.setOnItemClickListener(this);
        SingletonVolley.getInstance(getActivity()).addToRequestQueue(new StringRequest(0, Const.get_all_posts_of_category_url.replace("CATEGORY_ID", String.valueOf(48)), new C05111(), new C05122()));
        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), Post.class);
        intent.putExtra("id", ((JsonDataModel) this.jsonDataModel.get(position)).getId());
        intent.putExtra("image", ((JsonDataModel) this.jsonDataModel.get(position)).getFeaturedMediaUrl());
        intent.putExtra("post_title", ((JsonDataModel) this.jsonDataModel.get(position)).getTitleRendered());
        intent.putExtra("url", ((JsonDataModel) this.jsonDataModel.get(position)).getLink());
        intent.putExtra("date", this.dateConverter.getDate(((JsonDataModel) this.jsonDataModel.get(position)).getDate()) + " " + this.dateConverter.getMonth(((JsonDataModel) this.jsonDataModel.get(position)).getDate()));
        startActivity(intent);
    }
}
