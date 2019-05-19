package com.chaboox.algeriaplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chaboox.algeriaplus.constant.Const;
import com.chaboox.algeriaplus.helper.Font;

public class SettingsActivity extends AppCompatActivity implements OnClickListener {
    private static String PAGE_TITLE = "page_title";
    private static String PAGE_URL = "page_url";
    private LinearLayout llAboutTheApp;
    private LinearLayout llPrivacyPolicy;
    private LinearLayout llReportAnIssue;
    private LinearLayout llTermsAndConditions;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_settings);
        this.toolbar = (Toolbar) findViewById(C0245R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolBarTextView = (TextView) findViewById(C0245R.id.toolbarTextView);
        Font font = new Font();
        toolBarTextView.setText("Settings");
        font.setFont(getApplicationContext(), toolBarTextView);
        this.llTermsAndConditions = (LinearLayout) findViewById(C0245R.id.llTermsAndConditions);
        this.llPrivacyPolicy = (LinearLayout) findViewById(C0245R.id.llPrivacyPolicy);
        this.llReportAnIssue = (LinearLayout) findViewById(C0245R.id.llReportAnIssue);
        this.llAboutTheApp = (LinearLayout) findViewById(C0245R.id.llAboutTheApp);
        TextView privacy_policy_tv = (TextView) findViewById(C0245R.id.privacy_policy_tv);
        TextView report_an_issue_tv = (TextView) findViewById(C0245R.id.report_an_issue_tv);
        TextView about_the_app_tv = (TextView) findViewById(C0245R.id.about_the_app_tv);
        font.setFont(getApplicationContext(), (TextView) findViewById(C0245R.id.terms_and_conditions_tv));
        font.setFont(getApplicationContext(), privacy_policy_tv);
        font.setFont(getApplicationContext(), report_an_issue_tv);
        font.setFont(getApplicationContext(), about_the_app_tv);
        this.llTermsAndConditions.setOnClickListener(this);
        this.llPrivacyPolicy.setOnClickListener(this);
        this.llReportAnIssue.setOnClickListener(this);
        this.llAboutTheApp.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case C0245R.id.llTermsAndConditions:
                Intent intent1 = new Intent(this, SitePageLoader.class);
                intent1.putExtra(PAGE_TITLE, "Terms and Conditions");
                intent1.putExtra(PAGE_URL, Const.pages.replace("PAGE_ID", "636"));
                startActivity(intent1);
                return;
            case C0245R.id.llPrivacyPolicy:
                Intent intent2 = new Intent(this, SitePageLoader.class);
                intent2.putExtra(PAGE_TITLE, "Privacy policy");
                intent2.putExtra(PAGE_URL, Const.pages.replace("PAGE_ID", "622"));
                startActivity(intent2);
                return;
            case C0245R.id.llReportAnIssue:
                Intent intent3 = new Intent(this, SitePageLoader.class);
                intent3.putExtra(PAGE_TITLE, "Report an issue");
                intent3.putExtra(PAGE_URL, Const.pages.replace("PAGE_ID", "625"));
                startActivity(intent3);
                return;
            case C0245R.id.llAboutTheApp:
                startActivity(new Intent(getApplicationContext(), AboutTheAppActivity.class));
                return;
            default:
                return;
        }
    }
}
