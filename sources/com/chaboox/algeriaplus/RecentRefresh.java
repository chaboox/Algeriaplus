package com.chaboox.algeriaplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chaboox.algeriaplus.constant.Const;
import com.chaboox.algeriaplus.helper.CommonAdapter;
import com.chaboox.algeriaplus.helper.DateConverter;
import com.chaboox.algeriaplus.helper.SingletonVolley;
import com.chaboox.algeriaplus.model.JsonDataModel;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecentRefresh extends Fragment implements OnItemClickListener {
    public static final String POST_DATE = "date";
    public static final String POST_ID = "id";
    public static final String POST_TITLE = "post_title";
    public static final String POST_URL = "url";
    DateConverter dateConverter;
    private ImageView[] dots;
    private int dotscount;
    /* renamed from: i */
    int f24i = 10;
    public List<JsonDataModel> jsonDatas;
    private ListView listView;
    ProgressBar progressBar;
    LinearLayout sliderDotspanel;
    ViewPager viewPager;

    public class MyTimerTask extends TimerTask {

        /* renamed from: com.chaboox.algeriaplus.RecentRefresh$MyTimerTask$1 */
        class C02491 implements Runnable {
            C02491() {
            }

            public void run() {
                if (RecentRefresh.this.viewPager.getCurrentItem() == 0) {
                    RecentRefresh.this.viewPager.setCurrentItem(1);
                } else if (RecentRefresh.this.viewPager.getCurrentItem() == 1) {
                    RecentRefresh.this.viewPager.setCurrentItem(2);
                } else if (RecentRefresh.this.viewPager.getCurrentItem() == 2) {
                    RecentRefresh.this.viewPager.setCurrentItem(3);
                } else {
                    RecentRefresh.this.viewPager.setCurrentItem(0);
                }
            }
        }

        public void run() {
            MainActivity.thi.runOnUiThread(new C02491());
        }
    }

    /* renamed from: com.chaboox.algeriaplus.RecentRefresh$1 */
    class C05221 implements Listener<String> {

        /* renamed from: com.chaboox.algeriaplus.RecentRefresh$1$2 */
        class C05212 implements OnPageChangeListener {
            C05212() {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                for (int i = 0; i < RecentRefresh.this.dotscount; i++) {
                    RecentRefresh.this.dots[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.nonactive_dot));
                }
                RecentRefresh.this.dots[position].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.active_dot));
            }

            public void onPageScrollStateChanged(int state) {
            }
        }

        C05221() {
        }

        public void onResponse(String s) {
            RelativeLayout relativeLayout;
            final RelativeLayout relativeLayout2;
            LayoutParams layoutParams;
            int i;
            try {
                s = s.replace(",53", "").replace(",45", "").replace(",48", "").replace(",51", "").replace(",47", "").replace(",50", "").replace(",46", "").replace(",49", "").replace(",71", "").replace(",1", "1").replace("[53]", "53").replace("[45]", "45").replace("[48]", "48").replace("[51]", "51").replace("[47]", "47").replace("[50]", "50").replace("[46]", "46").replace("[49]", "49").replace("[71]", "71").replace("[1]", "1");
                Gson gson = new Gson();
                JSONArray ParentArray = new JSONArray(s);
                for (i = 0; i < ParentArray.length(); i++) {
                    JSONObject ParentObject = ParentArray.getJSONObject(i);
                    JsonDataModel jsonDataModel = (JsonDataModel) gson.fromJson(ParentObject.toString(), JsonDataModel.class);
                    jsonDataModel.setLink(ParentObject.getString("link"));
                    jsonDataModel.setId(ParentObject.getInt("id"));
                    jsonDataModel.setCategorie(ParentObject.getInt("categories"));
                    jsonDataModel.setDate(ParentObject.getString("date").substring(0, 10));
                    jsonDataModel.setTitleRendered(ParentObject.getJSONObject("title").getString("rendered").replace("&#8220;", "\"").replace("&#8221;", "\""));
                    jsonDataModel.setFeaturedMediaUrl(ParentObject.getJSONObject("better_featured_image").getString("source_url"));
                    RecentRefresh.this.jsonDatas.add(jsonDataModel);
                }
                ViewPagerAdapter.test1[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getTitleRendered();
                ViewPagerAdapter.test1[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getTitleRendered();
                ViewPagerAdapter.test1[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getTitleRendered();
                ViewPagerAdapter.test1[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getTitleRendered();
                ViewPagerAdapter.id[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getId();
                ViewPagerAdapter.id[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getId();
                ViewPagerAdapter.id[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getId();
                ViewPagerAdapter.id[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getId();
                ViewPagerAdapter.title[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getTitleRendered();
                ViewPagerAdapter.title[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getTitleRendered();
                ViewPagerAdapter.title[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getTitleRendered();
                ViewPagerAdapter.title[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getTitleRendered();
                ViewPagerAdapter.url[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getLink();
                ViewPagerAdapter.url[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getLink();
                ViewPagerAdapter.url[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getLink();
                ViewPagerAdapter.url[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getLink();
                ViewPagerAdapter.date[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getDate();
                ViewPagerAdapter.date[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getDate();
                ViewPagerAdapter.date[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getDate();
                ViewPagerAdapter.date[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getDate();
                relativeLayout = (RelativeLayout) MainActivity.thi.findViewById(C0245R.id.slide);
                ImageLoader.getInstance().init(new Builder(RecentRefresh.this.getActivity()).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build()).build());
                ViewPagerAdapter.bitmapTab[0] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[1] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[2] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[3] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getFeaturedMediaUrl());
                RecentRefresh.this.listView.setAdapter(new CommonAdapter(RecentRefresh.this.getActivity(), RecentRefresh.this.jsonDatas));
                relativeLayout2 = relativeLayout;
                RecentRefresh.this.listView.setOnScrollListener(new OnScrollListener() {
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                    }

                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        if (firstVisibleItem != 0 || !RecentRefresh.this.listIsAtTop()) {
                            relativeLayout2.setVisibility(8);
                            RecentRefresh recentRefresh = RecentRefresh.this;
                            recentRefresh.f24i++;
                        } else if (RecentRefresh.this.f24i > 2) {
                            relativeLayout2.setVisibility(0);
                            RecentRefresh.this.f24i = 0;
                        }
                    }
                });
                RecentRefresh.this.progressBar.setVisibility(8);
                RecentRefresh.this.viewPager = (ViewPager) MainActivity.thi.findViewById(C0245R.id.viewPager);
                RecentRefresh.this.sliderDotspanel = (LinearLayout) MainActivity.thi.findViewById(C0245R.id.SliderDots);
                RecentRefresh.this.viewPager.setAdapter(new ViewPagerAdapter(MainActivity.context));
                RecentRefresh.this.dotscount = 4;
                RecentRefresh.this.dots = new ImageView[RecentRefresh.this.dotscount];
                for (i = 0; i < RecentRefresh.this.dotscount; i++) {
                    RecentRefresh.this.dots[i] = new ImageView(MainActivity.context);
                    RecentRefresh.this.dots[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.nonactive_dot));
                    layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(8, 0, 8, 0);
                    RecentRefresh.this.sliderDotspanel.addView(RecentRefresh.this.dots[i], layoutParams);
                }
                RecentRefresh.this.dots[0].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.active_dot));
                RecentRefresh.this.viewPager.addOnPageChangeListener(new C05212());
                new Timer().scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
            } catch (JSONException e) {
                e.printStackTrace();
                ViewPagerAdapter.test1[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getTitleRendered();
                ViewPagerAdapter.test1[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getTitleRendered();
                ViewPagerAdapter.test1[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getTitleRendered();
                ViewPagerAdapter.test1[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getTitleRendered();
                ViewPagerAdapter.id[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getId();
                ViewPagerAdapter.id[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getId();
                ViewPagerAdapter.id[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getId();
                ViewPagerAdapter.id[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getId();
                ViewPagerAdapter.title[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getTitleRendered();
                ViewPagerAdapter.title[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getTitleRendered();
                ViewPagerAdapter.title[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getTitleRendered();
                ViewPagerAdapter.title[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getTitleRendered();
                ViewPagerAdapter.url[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getLink();
                ViewPagerAdapter.url[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getLink();
                ViewPagerAdapter.url[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getLink();
                ViewPagerAdapter.url[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getLink();
                ViewPagerAdapter.date[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getDate();
                ViewPagerAdapter.date[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getDate();
                ViewPagerAdapter.date[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getDate();
                ViewPagerAdapter.date[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getDate();
                relativeLayout = (RelativeLayout) MainActivity.thi.findViewById(C0245R.id.slide);
                ImageLoader.getInstance().init(new Builder(RecentRefresh.this.getActivity()).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build()).build());
                ViewPagerAdapter.bitmapTab[0] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[1] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[2] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[3] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getFeaturedMediaUrl());
                RecentRefresh.this.listView.setAdapter(new CommonAdapter(RecentRefresh.this.getActivity(), RecentRefresh.this.jsonDatas));
                relativeLayout2 = relativeLayout;
                RecentRefresh.this.listView.setOnScrollListener(/* anonymous class already generated */);
                RecentRefresh.this.progressBar.setVisibility(8);
                RecentRefresh.this.viewPager = (ViewPager) MainActivity.thi.findViewById(C0245R.id.viewPager);
                RecentRefresh.this.sliderDotspanel = (LinearLayout) MainActivity.thi.findViewById(C0245R.id.SliderDots);
                RecentRefresh.this.viewPager.setAdapter(new ViewPagerAdapter(MainActivity.context));
                RecentRefresh.this.dotscount = 4;
                RecentRefresh.this.dots = new ImageView[RecentRefresh.this.dotscount];
                for (i = 0; i < RecentRefresh.this.dotscount; i++) {
                    RecentRefresh.this.dots[i] = new ImageView(MainActivity.context);
                    RecentRefresh.this.dots[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.nonactive_dot));
                    layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(8, 0, 8, 0);
                    RecentRefresh.this.sliderDotspanel.addView(RecentRefresh.this.dots[i], layoutParams);
                }
                RecentRefresh.this.dots[0].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.active_dot));
                RecentRefresh.this.viewPager.addOnPageChangeListener(new C05212());
                new Timer().scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
            } catch (Throwable th) {
                Throwable th2 = th;
                ViewPagerAdapter.test1[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getTitleRendered();
                ViewPagerAdapter.test1[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getTitleRendered();
                ViewPagerAdapter.test1[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getTitleRendered();
                ViewPagerAdapter.test1[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getTitleRendered();
                ViewPagerAdapter.id[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getId();
                ViewPagerAdapter.id[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getId();
                ViewPagerAdapter.id[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getId();
                ViewPagerAdapter.id[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getId();
                ViewPagerAdapter.title[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getTitleRendered();
                ViewPagerAdapter.title[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getTitleRendered();
                ViewPagerAdapter.title[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getTitleRendered();
                ViewPagerAdapter.title[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getTitleRendered();
                ViewPagerAdapter.url[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getLink();
                ViewPagerAdapter.url[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getLink();
                ViewPagerAdapter.url[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getLink();
                ViewPagerAdapter.url[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getLink();
                ViewPagerAdapter.date[0] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getDate();
                ViewPagerAdapter.date[1] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getDate();
                ViewPagerAdapter.date[2] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getDate();
                ViewPagerAdapter.date[3] = ((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getDate();
                relativeLayout = (RelativeLayout) MainActivity.thi.findViewById(C0245R.id.slide);
                ImageLoader.getInstance().init(new Builder(RecentRefresh.this.getActivity()).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build()).build());
                ViewPagerAdapter.bitmapTab[0] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(7)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[1] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(8)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[2] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(9)).getFeaturedMediaUrl());
                ViewPagerAdapter.bitmapTab[3] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) RecentRefresh.this.jsonDatas.get(10)).getFeaturedMediaUrl());
                RecentRefresh.this.listView.setAdapter(new CommonAdapter(RecentRefresh.this.getActivity(), RecentRefresh.this.jsonDatas));
                relativeLayout2 = relativeLayout;
                RecentRefresh.this.listView.setOnScrollListener(/* anonymous class already generated */);
                RecentRefresh.this.progressBar.setVisibility(8);
                RecentRefresh.this.viewPager = (ViewPager) MainActivity.thi.findViewById(C0245R.id.viewPager);
                RecentRefresh.this.sliderDotspanel = (LinearLayout) MainActivity.thi.findViewById(C0245R.id.SliderDots);
                RecentRefresh.this.viewPager.setAdapter(new ViewPagerAdapter(MainActivity.context));
                RecentRefresh.this.dotscount = 4;
                RecentRefresh.this.dots = new ImageView[RecentRefresh.this.dotscount];
                for (i = 0; i < RecentRefresh.this.dotscount; i++) {
                    RecentRefresh.this.dots[i] = new ImageView(MainActivity.context);
                    RecentRefresh.this.dots[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.nonactive_dot));
                    layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(8, 0, 8, 0);
                    RecentRefresh.this.sliderDotspanel.addView(RecentRefresh.this.dots[i], layoutParams);
                }
                RecentRefresh.this.dots[0].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.active_dot));
                RecentRefresh.this.viewPager.addOnPageChangeListener(new C05212());
                new Timer().scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
            }
        }
    }

    /* renamed from: com.chaboox.algeriaplus.RecentRefresh$2 */
    class C05232 implements ErrorListener {
        C05232() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            volleyError.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0245R.layout.fragment_recent, container, false);
        this.dateConverter = new DateConverter();
        this.listView = (ListView) view.findViewById(C0245R.id.recent_list_view);
        this.progressBar = (ProgressBar) view.findViewById(C0245R.id.progress_bar_recent);
        this.progressBar.setVisibility(0);
        this.jsonDatas = new ArrayList();
        this.listView.setOnItemClickListener(this);
        SingletonVolley.getInstance(getActivity()).addToRequestQueue(new StringRequest(0, Const.url, new C05221(), new C05232()));
        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), Post.class);
        intent.putExtra("id", ((JsonDataModel) this.jsonDatas.get(position)).getId());
        intent.putExtra("image", ((JsonDataModel) this.jsonDatas.get(position)).getFeaturedMediaUrl());
        intent.putExtra("post_title", ((JsonDataModel) this.jsonDatas.get(position)).getTitleRendered());
        intent.putExtra("url", ((JsonDataModel) this.jsonDatas.get(position)).getLink());
        intent.putExtra("date", this.dateConverter.getDate(((JsonDataModel) this.jsonDatas.get(position)).getDate()) + " " + this.dateConverter.getMonth(((JsonDataModel) this.jsonDatas.get(position)).getDate()));
        startActivity(intent);
    }

    private boolean listIsAtTop() {
        if (this.listView.getChildCount() == 0 || this.listView.getChildAt(0).getTop() == 0) {
            return true;
        }
        return false;
    }
}
