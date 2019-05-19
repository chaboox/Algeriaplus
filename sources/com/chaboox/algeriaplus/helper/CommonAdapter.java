package com.chaboox.algeriaplus.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.chaboox.algeriaplus.C0245R;
import com.chaboox.algeriaplus.model.JsonDataModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.util.List;

public class CommonAdapter extends BaseAdapter {
    public static ImageView un;
    private Context context;
    private Font font;
    /* renamed from: i */
    private int f12i = 0;
    private List<JsonDataModel> jsonData;

    private class ViewHolder {
        private TextView date;
        private ImageView imageView;
        private ProgressBar progressBar;
        private TextView title;

        private ViewHolder() {
        }
    }

    public CommonAdapter(Context context, List<JsonDataModel> jsonData) {
        this.context = context;
        this.jsonData = jsonData;
    }

    public int getCount() {
        return this.jsonData.size();
    }

    public Object getItem(int position) {
        return this.jsonData.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0245R.layout.row, null);
            holder.imageView = (ImageView) convertView.findViewById(C0245R.id.imageView);
            holder.date = (TextView) convertView.findViewById(C0245R.id.tv_date);
            holder.title = (TextView) convertView.findViewById(C0245R.id.tv_title_rendered);
            holder.progressBar = (ProgressBar) convertView.findViewById(C0245R.id.progress_bar_recent);
            this.font = new Font();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DateConverter dateConverter = new DateConverter();
        String nomCategories = "yo";
        int idCategorie = ((JsonDataModel) this.jsonData.get(position)).getCategories();
        if (idCategorie == 45) {
            nomCategories = " الحدث ";
            holder.date.setBackgroundResource(C0245R.drawable.recada);
        } else if (idCategorie == 47) {
            nomCategories = " رياضة ";
            holder.date.setBackgroundResource(C0245R.drawable.recsport);
        } else if (idCategorie == 46) {
            nomCategories = " علوم و تكنولوجيا ";
            holder.date.setBackgroundResource(C0245R.drawable.rectechno);
        } else if (idCategorie == 48) {
            nomCategories = " تعليم و توظيف ";
            holder.date.setBackgroundResource(C0245R.drawable.rectra);
        } else if (idCategorie == 50) {
            nomCategories = " صحة و غذاء ";
            holder.date.setBackgroundResource(C0245R.drawable.recsant);
        } else if (idCategorie == 51) {
            nomCategories = " ريادة الاعمال ";
            holder.date.setBackgroundResource(C0245R.drawable.recri);
        } else if (idCategorie == 71) {
            nomCategories = " منوعات ";
            holder.date.setBackgroundResource(C0245R.drawable.recmoun);
        }
        holder.title.setText(((JsonDataModel) this.jsonData.get(position)).getTitleRendered());
        holder.date.setText(nomCategories);
        this.font.setFont(this.context, holder.title);
        this.font.setFont(this.context, holder.date);
        final ViewHolder finalHolder = holder;
        ImageLoader.getInstance().displayImage(((JsonDataModel) this.jsonData.get(position)).getFeaturedMediaUrl(), holder.imageView, new ImageLoadingListener() {
            public void onLoadingStarted(String s, View view) {
                finalHolder.progressBar.setVisibility(0);
            }

            public void onLoadingFailed(String s, View view, FailReason failReason) {
            }

            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                finalHolder.progressBar.setVisibility(8);
                if (CommonAdapter.this.f12i >= 3) {
                }
            }

            public void onLoadingCancelled(String s, View view) {
            }
        });
        return convertView;
    }
}
