package com.chaboox.algeriaplus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.chaboox.algeriaplus.helper.Font;

public class AboutTheAppActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_about_the_app);
        setSupportActionBar((Toolbar) findViewById(C0245R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Font font = new Font();
        TextView toolBarTextView = (TextView) findViewById(C0245R.id.toolbarTextView);
        toolBarTextView.setText("About the app");
        font.setFont(getApplicationContext(), toolBarTextView);
        TextView version_number = (TextView) findViewById(C0245R.id.version_number);
        TextView open_source_libraries = (TextView) findViewById(C0245R.id.open_source_libraries);
        TextView volley = (TextView) findViewById(C0245R.id.volley);
        TextView gson = (TextView) findViewById(C0245R.id.gson);
        TextView clans_fab = (TextView) findViewById(C0245R.id.clans_fab);
        TextView app_copyrights = (TextView) findViewById(C0245R.id.app_copyrights);
        TextView copyright = (TextView) findViewById(C0245R.id.copyright);
        TextView desc = (TextView) findViewById(C0245R.id.desc);
        font.setFont(getApplicationContext(), (TextView) findViewById(C0245R.id.version));
        font.setFont(getApplicationContext(), version_number);
        font.setFont(getApplicationContext(), open_source_libraries);
        font.setFont(getApplicationContext(), volley);
        font.setFont(getApplicationContext(), gson);
        font.setFont(getApplicationContext(), clans_fab);
        font.setFont(getApplicationContext(), app_copyrights);
        font.setFont(getApplicationContext(), copyright);
        font.setFont(getApplicationContext(), desc);
    }
}
