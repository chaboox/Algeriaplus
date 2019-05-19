package com.chaboox.algeriaplus;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import com.chaboox.algeriaplus.helper.AdapterNewData;
import com.chaboox.algeriaplus.helper.CommonAdapter;
import com.chaboox.algeriaplus.model.JsonDataModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    public static int active_fragment = 1;
    public static Context context;
    public static MainActivity thi;
    /* renamed from: a */
    private Al_adath f25a = null;
    /* renamed from: e */
    private Etude f26e = null;
    /* renamed from: f */
    private Recent f27f = null;
    private List<JsonDataModel> jsonDataList = new ArrayList();
    private Managment ma = null;
    private mounawiate mou = null;
    private sante sa = null;
    private Sport sp = null;
    /* renamed from: t */
    private Techno f28t = null;

    /* renamed from: com.chaboox.algeriaplus.MainActivity$1 */
    class C02441 implements OnQueryTextListener {
        C02441() {
        }

        public boolean onQueryTextSubmit(String query) {
            System.out.println("voila");
            return false;
        }

        public boolean onQueryTextChange(String newText) {
            MainActivity.this.jsonDataList.clear();
            int i;
            if (MainActivity.active_fragment == 1) {
                MainActivity.this.f27f.relativeLayout.setVisibility(8);
                for (i = 0; i < MainActivity.this.f27f.jsonDataList.size(); i++) {
                    if (((JsonDataModel) MainActivity.this.f27f.jsonDataList.get(i)).getTitleRendered().contains(newText)) {
                        MainActivity.this.jsonDataList.add(MainActivity.this.f27f.jsonDataList.get(i));
                    }
                }
                MainActivity.this.f27f.listView.setAdapter(new CommonAdapter(MainActivity.this.getApplicationContext(), MainActivity.this.jsonDataList));
            } else if (MainActivity.active_fragment == 2) {
                for (i = 0; i < MainActivity.this.f25a.jsonDataModel.size(); i++) {
                    if (((JsonDataModel) MainActivity.this.f25a.jsonDataModel.get(i)).getTitleRendered().contains(newText)) {
                        MainActivity.this.jsonDataList.add(MainActivity.this.f25a.jsonDataModel.get(i));
                    }
                }
                MainActivity.this.f25a.listView.setAdapter(new AdapterNewData(MainActivity.this.getApplicationContext(), MainActivity.this.jsonDataList));
            } else if (MainActivity.active_fragment == 3) {
                for (i = 0; i < MainActivity.this.sp.jsonDataModel.size(); i++) {
                    if (((JsonDataModel) MainActivity.this.sp.jsonDataModel.get(i)).getTitleRendered().contains(newText)) {
                        MainActivity.this.jsonDataList.add(MainActivity.this.sp.jsonDataModel.get(i));
                    }
                }
                MainActivity.this.sp.listView.setAdapter(new AdapterNewData(MainActivity.this.getApplicationContext(), MainActivity.this.jsonDataList));
            } else if (MainActivity.active_fragment == 4) {
                for (i = 0; i < MainActivity.this.f28t.jsonDataModel.size(); i++) {
                    if (((JsonDataModel) MainActivity.this.f28t.jsonDataModel.get(i)).getTitleRendered().contains(newText)) {
                        MainActivity.this.jsonDataList.add(MainActivity.this.f28t.jsonDataModel.get(i));
                    }
                }
                MainActivity.this.f28t.listView.setAdapter(new AdapterNewData(MainActivity.this.getApplicationContext(), MainActivity.this.jsonDataList));
            } else if (MainActivity.active_fragment == 5) {
                for (i = 0; i < MainActivity.this.f26e.jsonDataModel.size(); i++) {
                    if (((JsonDataModel) MainActivity.this.f26e.jsonDataModel.get(i)).getTitleRendered().contains(newText)) {
                        MainActivity.this.jsonDataList.add(MainActivity.this.f26e.jsonDataModel.get(i));
                    }
                }
                MainActivity.this.f26e.listView.setAdapter(new AdapterNewData(MainActivity.this.getApplicationContext(), MainActivity.this.jsonDataList));
            } else if (MainActivity.active_fragment == 6) {
                for (i = 0; i < MainActivity.this.sa.jsonDataModel.size(); i++) {
                    if (((JsonDataModel) MainActivity.this.sa.jsonDataModel.get(i)).getTitleRendered().contains(newText)) {
                        MainActivity.this.jsonDataList.add(MainActivity.this.sa.jsonDataModel.get(i));
                    }
                }
                MainActivity.this.sa.listView.setAdapter(new AdapterNewData(MainActivity.this.getApplicationContext(), MainActivity.this.jsonDataList));
            } else if (MainActivity.active_fragment == 7) {
                for (i = 0; i < MainActivity.this.ma.jsonDataModel.size(); i++) {
                    if (((JsonDataModel) MainActivity.this.ma.jsonDataModel.get(i)).getTitleRendered().contains(newText)) {
                        MainActivity.this.jsonDataList.add(MainActivity.this.ma.jsonDataModel.get(i));
                    }
                }
                MainActivity.this.ma.listView.setAdapter(new AdapterNewData(MainActivity.this.getApplicationContext(), MainActivity.this.jsonDataList));
            } else if (MainActivity.active_fragment == 8) {
                for (i = 0; i < MainActivity.this.mou.jsonDataModel.size(); i++) {
                    if (((JsonDataModel) MainActivity.this.mou.jsonDataModel.get(i)).getTitleRendered().contains(newText)) {
                        MainActivity.this.jsonDataList.add(MainActivity.this.mou.jsonDataModel.get(i));
                    }
                }
                MainActivity.this.mou.listView.setAdapter(new AdapterNewData(MainActivity.this.getApplicationContext(), MainActivity.this.jsonDataList));
            }
            return false;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_main);
        thi = this;
        context = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(C0245R.id.toolbar);
        setSupportActionBar(toolbar);
        this.f27f = new Recent();
        getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(C0245R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, C0245R.string.navigation_drawer_open, C0245R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(C0245R.id.nav_view)).setNavigationItemSelectedListener(this);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "font/arabtitre.ttf");
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
        getMenuInflater().inflate(C0245R.menu.main, menu);
        ((SearchView) menu.findItem(C0245R.id.search).getActionView()).setOnQueryTextListener(new C02441());
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0245R.id.refresh) {
            if (active_fragment == 1) {
                setTitle("أخر الاخبار");
                getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, new RecentRefresh()).commit();
            } else if (active_fragment == 2) {
                setTitle("الحدث");
                this.f25a = new Al_adath();
                getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
            } else if (active_fragment == 3) {
                setTitle("رياضة");
                this.sp = new Sport();
                getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
            } else if (active_fragment == 4) {
                setTitle("علوم و تكنولوجيا");
                this.f28t = new Techno();
                getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
            } else if (active_fragment == 5) {
                setTitle("تعليم و توظيف");
                this.f26e = new Etude();
                getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
            } else if (active_fragment == 6) {
                setTitle("صحة و غذاء");
                this.sa = new sante();
                getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
            } else if (active_fragment == 7) {
                setTitle("ريادة الاعمال");
                this.ma = new Managment();
                getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
            } else if (active_fragment == 8) {
                LayoutParams params = new LayoutParams(-1, -1, 17);
                setTitle("منوعات");
                this.mou = new mounawiate();
                getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == C0245R.id.new_news) {
            active_fragment = 1;
            setTitle("أخر الاخبار");
            this.f27f = new Recent();
            getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f27f).commit();
        } else if (id == C0245R.id.adath) {
            active_fragment = 2;
            setTitle("الحدث");
            this.f25a = new Al_adath();
            getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f25a).commit();
        } else if (id == C0245R.id.sport) {
            active_fragment = 3;
            setTitle("رياضة");
            this.sp = new Sport();
            getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.sp).commit();
        } else if (id == C0245R.id.techno) {
            active_fragment = 4;
            setTitle("علوم و تكنولوجيا");
            this.f28t = new Techno();
            getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f28t).commit();
        } else if (id == C0245R.id.etude) {
            active_fragment = 5;
            setTitle("تعليم و توظيف");
            this.f26e = new Etude();
            getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.f26e).commit();
        } else if (id == C0245R.id.sante) {
            active_fragment = 6;
            setTitle("صحة و غذاء");
            this.sa = new sante();
            getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.sa).commit();
        } else if (id == C0245R.id.managment) {
            active_fragment = 7;
            setTitle("ريادة الاعمال");
            this.ma = new Managment();
            getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.ma).commit();
        } else if (id == C0245R.id.other) {
            active_fragment = 8;
            LayoutParams params = new LayoutParams(-1, -1, 17);
            setTitle("منوعات");
            this.mou = new mounawiate();
            getSupportFragmentManager().beginTransaction().replace(C0245R.id.fragment, this.mou).commit();
        }
        ((DrawerLayout) findViewById(C0245R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }
}
