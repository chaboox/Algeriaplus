package com.chaboox.algeriaplus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.chaboox.algeriaplus.constant.Const;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private static String PAGE_TITLE = "page_title";
    private static String PAGE_URL = "page_url";
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public Fragment getItem(int position) {
            return (Fragment) this.mFragmentList.get(position);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            this.mFragmentList.add(fragment);
        }

        public CharSequence getPageTitle(int position) {
            return "";
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_home);
        this.toolbar = (Toolbar) findViewById(C0245R.id.toolbar);
        setSupportActionBar(this.toolbar);
        this.viewPager = (ViewPager) findViewById(C0245R.id.viewpager);
        setupViewPager(this.viewPager);
        this.tabLayout = (TabLayout) findViewById(C0245R.id.tabs);
        this.tabLayout.setupWithViewPager(this.viewPager);
        DrawerLayout drawer = (DrawerLayout) findViewById(C0245R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, this.toolbar, C0245R.string.navigation_drawer_open, C0245R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(C0245R.id.nav_view)).setNavigationItemSelectedListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Recent(), getResources().getString(C0245R.string.recent_posts));
        viewPager.setAdapter(adapter);
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(C0245R.id.drawer_layout);
        if (drawer.isDrawerOpen((int) GravityCompat.START)) {
            drawer.closeDrawer((int) GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0245R.menu.home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0245R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case C0245R.id.nav_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
            case C0245R.id.nav_contact_us:
                startActivity(new Intent(this, ContactUs.class));
                break;
            case C0245R.id.nav_about_us:
                Intent intent = new Intent(this, SitePageLoader.class);
                intent.putExtra(PAGE_TITLE, "About Us");
                intent.putExtra(PAGE_URL, Const.pages.replace("PAGE_ID", "53"));
                startActivity(intent);
                break;
            case C0245R.id.nav_facebook:
                startActivity(openFacebook(getApplicationContext()));
                break;
            case C0245R.id.nav_youtube:
                startActivity(openYoutube(getApplicationContext()));
                break;
            case C0245R.id.nav_website:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.gmonetix.com")));
                break;
        }
        ((DrawerLayout) findViewById(C0245R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    public static Intent openFacebook(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent("android.intent.action.VIEW", Uri.parse("fb://page/122591891284060"));
        } catch (Exception e) {
            return new Intent("android.intent.action.VIEW", Uri.parse("https://facebook.com/gmonetix"));
        }
    }

    public static Intent openYoutube(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.google.android.youtube", 0);
            return new Intent("android.intent.action.VIEW", Uri.parse("https://www.youtube.com/channel/UCpfLFzQqRl01sXP7dxIQ_rg"));
        } catch (Exception e) {
            return new Intent("android.intent.action.VIEW", Uri.parse("https://www.youtube.com/channel/UCpfLFzQqRl01sXP7dxIQ_rg"));
        }
    }
}
