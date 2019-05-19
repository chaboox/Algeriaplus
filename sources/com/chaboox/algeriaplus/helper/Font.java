package com.chaboox.algeriaplus.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class Font {
    public void setFont(Context _context, TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(_context.getAssets(), "font/arabtitre.ttf"));
    }
}
