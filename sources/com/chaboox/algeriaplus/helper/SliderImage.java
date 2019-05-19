package com.chaboox.algeriaplus.helper;

import android.content.Context;
import android.widget.ImageView;
import com.chaboox.algeriaplus.model.JsonDataModel;
import java.util.List;

public class SliderImage {
    public static ImageView un;
    private Context context;
    private Font font;
    /* renamed from: i */
    private int f13i = 0;
    private List<JsonDataModel> jsonData;

    public SliderImage(Context context, List<JsonDataModel> jsonData) {
        this.context = context;
        this.jsonData = jsonData;
    }
}
