package com.chaboox.algeriaplus.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.chaboox.algeriaplus.C0245R;
import com.chaboox.algeriaplus.model.JsonDataModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.util.List;

public class AdapterNewData extends BaseAdapter {
    private Context context;
    private Font font;
    private List<JsonDataModel> jsonCategoriesDataList;

    private class ViewHolder {
        private TextView date;
        private ImageView imageView;
        private TextView title;

        private ViewHolder() {
        }
    }

    /* renamed from: com.chaboox.algeriaplus.helper.AdapterNewData$1 */
    class C05321 implements ImageLoadingListener {
        C05321() {
        }

        public void onLoadingStarted(String s, View view) {
        }

        public void onLoadingFailed(String s, View view, FailReason failReason) {
        }

        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
        }

        public void onLoadingCancelled(String s, View view) {
        }
    }

    public AdapterNewData(Context context, List<JsonDataModel> jsonCategoriesDataList) {
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
            convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0245R.layout.row_new, null);
            holder.imageView = (ImageView) convertView.findViewById(C0245R.id.imageView);
            holder.date = (TextView) convertView.findViewById(C0245R.id.tv_date);
            holder.title = (TextView) convertView.findViewById(C0245R.id.tv_title_rendered);
            this.font = new Font();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DateConverter dateConverter = new DateConverter();
        holder.title.setText(((JsonDataModel) this.jsonCategoriesDataList.get(position)).getTitleRendered());
        holder.date.setText(dateConverter.getDate(((JsonDataModel) this.jsonCategoriesDataList.get(position)).getDate()) + " " + dateConverter.getMonth(((JsonDataModel) this.jsonCategoriesDataList.get(position)).getDate()));
        this.font.setFont(this.context, holder.title);
        this.font.setFont(this.context, holder.date);
        ViewHolder finalHolder = holder;
        ImageLoader.getInstance().displayImage(((JsonDataModel) this.jsonCategoriesDataList.get(position)).getFeaturedMediaUrl(), holder.imageView, new C05321());
        return convertView;
    }
}
