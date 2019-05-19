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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.chaboox.algeriaplus.helper.CommonAdapter;
import com.chaboox.algeriaplus.helper.DateConverter;
import com.chaboox.algeriaplus.model.JsonDataModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Recent extends Fragment implements OnItemClickListener {
    public static final String POST_DATE = "date";
    public static final String POST_ID = "id";
    public static final String POST_IMAGE = "image";
    public static final String POST_TITLE = "post_title";
    public static final String POST_URL = "url";
    private static boolean sliderActive = true;
    CommonAdapter commonAdapter;
    DateConverter dateConverter;
    private ImageView[] dots;
    private int dotscount;
    /* renamed from: i */
    int f23i = 10;
    public List<JsonDataModel> jsonDataList = SplashScreen.JsonDatas;
    public ListView listView;
    public RelativeLayout relativeLayout;
    LinearLayout sliderDotspanel;
    ViewPager viewPager;

    /* renamed from: com.chaboox.algeriaplus.Recent$1 */
    class C02461 implements OnScrollListener {
        C02461() {
        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem != 0 || !Recent.this.listIsAtTop()) {
                Recent.this.relativeLayout.setVisibility(8);
                Recent recent = Recent.this;
                recent.f23i++;
            } else if (Recent.this.f23i > 2) {
                Recent.this.relativeLayout.setVisibility(0);
                Recent.this.f23i = 0;
            }
        }
    }

    public class MyTimerTask extends TimerTask {

        /* renamed from: com.chaboox.algeriaplus.Recent$MyTimerTask$1 */
        class C02471 implements Runnable {
            C02471() {
            }

            public void run() {
                if (Recent.this.viewPager.getCurrentItem() == 0) {
                    Recent.this.viewPager.setCurrentItem(1);
                } else if (Recent.this.viewPager.getCurrentItem() == 1) {
                    Recent.this.viewPager.setCurrentItem(2);
                } else if (Recent.this.viewPager.getCurrentItem() == 2) {
                    Recent.this.viewPager.setCurrentItem(3);
                } else {
                    Recent.this.viewPager.setCurrentItem(0);
                }
            }
        }

        public void run() {
            MainActivity.thi.runOnUiThread(new C02471());
        }
    }

    /* renamed from: com.chaboox.algeriaplus.Recent$2 */
    class C05202 implements OnPageChangeListener {
        C05202() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            for (int i = 0; i < Recent.this.dotscount; i++) {
                Recent.this.dots[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.nonactive_dot));
            }
            Recent.this.dots[position].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.active_dot));
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewPagerAdapter.test1[0] = ((JsonDataModel) this.jsonDataList.get(7)).getTitleRendered();
        ViewPagerAdapter.test1[1] = ((JsonDataModel) this.jsonDataList.get(8)).getTitleRendered();
        ViewPagerAdapter.test1[2] = ((JsonDataModel) this.jsonDataList.get(9)).getTitleRendered();
        ViewPagerAdapter.test1[3] = ((JsonDataModel) this.jsonDataList.get(10)).getTitleRendered();
        ViewPagerAdapter.id[0] = ((JsonDataModel) this.jsonDataList.get(7)).getId();
        ViewPagerAdapter.id[1] = ((JsonDataModel) this.jsonDataList.get(8)).getId();
        ViewPagerAdapter.id[2] = ((JsonDataModel) this.jsonDataList.get(9)).getId();
        ViewPagerAdapter.id[3] = ((JsonDataModel) this.jsonDataList.get(10)).getId();
        ViewPagerAdapter.title[0] = ((JsonDataModel) this.jsonDataList.get(7)).getTitleRendered();
        ViewPagerAdapter.title[1] = ((JsonDataModel) this.jsonDataList.get(8)).getTitleRendered();
        ViewPagerAdapter.title[2] = ((JsonDataModel) this.jsonDataList.get(9)).getTitleRendered();
        ViewPagerAdapter.title[3] = ((JsonDataModel) this.jsonDataList.get(10)).getTitleRendered();
        ViewPagerAdapter.url[0] = ((JsonDataModel) this.jsonDataList.get(7)).getLink();
        ViewPagerAdapter.url[1] = ((JsonDataModel) this.jsonDataList.get(8)).getLink();
        ViewPagerAdapter.url[2] = ((JsonDataModel) this.jsonDataList.get(9)).getLink();
        ViewPagerAdapter.url[3] = ((JsonDataModel) this.jsonDataList.get(10)).getLink();
        ViewPagerAdapter.date[0] = ((JsonDataModel) this.jsonDataList.get(7)).getDate();
        ViewPagerAdapter.date[1] = ((JsonDataModel) this.jsonDataList.get(8)).getDate();
        ViewPagerAdapter.date[2] = ((JsonDataModel) this.jsonDataList.get(9)).getDate();
        ViewPagerAdapter.date[3] = ((JsonDataModel) this.jsonDataList.get(10)).getDate();
        View view = inflater.inflate(C0245R.layout.fragment_recent, container, false);
        this.relativeLayout = (RelativeLayout) view.findViewById(C0245R.id.slide);
        ImageLoader.getInstance().init(new Builder(getActivity()).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build()).build());
        ViewPagerAdapter.bitmapTab[0] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) this.jsonDataList.get(7)).getFeaturedMediaUrl());
        ViewPagerAdapter.bitmapTab[1] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) this.jsonDataList.get(8)).getFeaturedMediaUrl());
        ViewPagerAdapter.bitmapTab[2] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) this.jsonDataList.get(9)).getFeaturedMediaUrl());
        ViewPagerAdapter.bitmapTab[3] = ImageLoader.getInstance().loadImageSync(((JsonDataModel) this.jsonDataList.get(10)).getFeaturedMediaUrl());
        this.listView = (ListView) view.findViewById(C0245R.id.recent_list_view);
        this.dateConverter = new DateConverter();
        this.listView.setOnScrollListener(new C02461());
        this.listView.setOnItemClickListener(this);
        this.commonAdapter = new CommonAdapter(getActivity(), this.jsonDataList);
        this.listView.setAdapter(this.commonAdapter);
        this.viewPager = (ViewPager) view.findViewById(C0245R.id.viewPager);
        this.sliderDotspanel = (LinearLayout) view.findViewById(C0245R.id.SliderDots);
        this.viewPager.setAdapter(new ViewPagerAdapter(MainActivity.context));
        this.dotscount = 4;
        this.dots = new ImageView[this.dotscount];
        for (int i = 0; i < this.dotscount; i++) {
            this.dots[i] = new ImageView(MainActivity.context);
            this.dots[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.nonactive_dot));
            LayoutParams params = new LayoutParams(-2, -2);
            params.setMargins(8, 0, 8, 0);
            this.sliderDotspanel.addView(this.dots[i], params);
        }
        this.dots[0].setImageDrawable(ContextCompat.getDrawable(MainActivity.context, C0245R.drawable.active_dot));
        this.viewPager.addOnPageChangeListener(new C05202());
        new Timer().scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), Post.class);
        intent.putExtra("id", ((JsonDataModel) this.jsonDataList.get(position)).getId());
        intent.putExtra("post_title", ((JsonDataModel) this.jsonDataList.get(position)).getTitleRendered());
        intent.putExtra("url", ((JsonDataModel) this.jsonDataList.get(position)).getLink());
        intent.putExtra("image", ((JsonDataModel) this.jsonDataList.get(position)).getFeaturedMediaUrl());
        intent.putExtra("date", this.dateConverter.getDate(((JsonDataModel) this.jsonDataList.get(position)).getDate()) + " " + this.dateConverter.getMonth(((JsonDataModel) this.jsonDataList.get(position)).getDate()));
        startActivity(intent);
    }

    private boolean listIsAtTop() {
        if (this.listView.getChildCount() == 0 || this.listView.getChildAt(0).getTop() == 0) {
            return true;
        }
        return false;
    }
}
