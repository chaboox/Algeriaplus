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
import com.chaboox.algeriaplus.helper.AdapterCategories;
import com.chaboox.algeriaplus.helper.SingletonVolley;
import com.chaboox.algeriaplus.model.JsonCategoriesModel;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Categories extends Fragment implements OnItemClickListener {
    public static final String CATEGORIES_ID = "categories_id";
    public static final String CATEGORIES_NAME = "categories_name";
    public List<JsonCategoriesModel> JsonCategoriesData;
    private ListView listView;
    ProgressBar progressBar;

    /* renamed from: com.chaboox.algeriaplus.Categories$1 */
    class C05091 implements Listener<String> {
        C05091() {
        }

        public void onResponse(String s) {
            try {
                Gson gson = new Gson();
                JSONArray ParentArray = new JSONArray(s);
                for (int i = 0; i < ParentArray.length(); i++) {
                    JSONObject ParentObject = ParentArray.getJSONObject(i);
                    JsonCategoriesModel jsonCategoriesModel = (JsonCategoriesModel) gson.fromJson(ParentObject.toString(), JsonCategoriesModel.class);
                    jsonCategoriesModel.setCategoriesId(ParentObject.getInt("id"));
                    jsonCategoriesModel.setCategoriesDescription(ParentObject.getString("description"));
                    jsonCategoriesModel.setCategoriesName(ParentObject.getString("name"));
                    Categories.this.JsonCategoriesData.add(jsonCategoriesModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                Categories.this.listView.setAdapter(new AdapterCategories(Categories.this.getActivity(), Categories.this.JsonCategoriesData));
                Categories.this.progressBar.setVisibility(8);
            }
        }
    }

    /* renamed from: com.chaboox.algeriaplus.Categories$2 */
    class C05102 implements ErrorListener {
        C05102() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            volleyError.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0245R.layout.fragment_categories, container, false);
        this.listView = (ListView) view.findViewById(C0245R.id.categories_list_view);
        this.progressBar = (ProgressBar) view.findViewById(C0245R.id.progress_bar_categories);
        this.JsonCategoriesData = new ArrayList();
        this.listView.setOnItemClickListener(this);
        SingletonVolley.getInstance(getActivity()).addToRequestQueue(new StringRequest(0, Const.get_categories_url, new C05091(), new C05102()));
        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PostsOfParticularCategory.class);
        intent.putExtra("categories_id", ((JsonCategoriesModel) this.JsonCategoriesData.get(position)).getCategoriesId());
        intent.putExtra("categories_name", ((JsonCategoriesModel) this.JsonCategoriesData.get(position)).getCategoriesName());
        startActivity(intent);
    }
}
