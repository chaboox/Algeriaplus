package com.chaboox.algeriaplus.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.chaboox.algeriaplus.C0245R;
import com.chaboox.algeriaplus.model.JsonCategoriesModel;
import java.util.List;

public class AdapterCategories extends BaseAdapter {
    private Context context;
    private Font font;
    private List<JsonCategoriesModel> jsonCategoriesDataList;

    private class ViewHolder {
        private TextView categoriesDescription;
        private TextView categoriesName;

        private ViewHolder() {
        }
    }

    public AdapterCategories(Context context, List<JsonCategoriesModel> jsonCategoriesDataList) {
        this.context = context;
        this.jsonCategoriesDataList = jsonCategoriesDataList;
    }

    public int getCount() {
        return this.jsonCategoriesDataList.size();
    }

    public Object getItem(int position) {
        return this.jsonCategoriesDataList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0245R.layout.categories_row_sample_layout, null);
            holder.categoriesDescription = (TextView) convertView.findViewById(C0245R.id.categories_description);
            holder.categoriesName = (TextView) convertView.findViewById(C0245R.id.categories_name);
            this.font = new Font();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.categoriesDescription.setText(((JsonCategoriesModel) this.jsonCategoriesDataList.get(position)).getCategoriesDescription());
        holder.categoriesName.setText(((JsonCategoriesModel) this.jsonCategoriesDataList.get(position)).getCategoriesName());
        this.font.setFont(this.context, holder.categoriesDescription);
        this.font.setFont(this.context, holder.categoriesName);
        return convertView;
    }
}
