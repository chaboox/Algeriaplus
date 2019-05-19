package com.chaboox.algeriaplus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class scroll extends AppCompatActivity {

    /* renamed from: com.chaboox.algeriaplus.scroll$1 */
    class C02541 implements OnClickListener {
        C02541() {
        }

        public void onClick(View view) {
            Snackbar.make(view, (CharSequence) "Replace with your own action", 0).setAction((CharSequence) "Action", null).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_scroll);
        setSupportActionBar((Toolbar) findViewById(C0245R.id.toolbar));
        ((FloatingActionButton) findViewById(C0245R.id.fab)).setOnClickListener(new C02541());
    }
}
