package com.chaboox.algeriaplus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chaboox.algeriaplus.helper.Font;

public class ViewPagerAdapter extends PagerAdapter {
    public static Bitmap[] bitmapTab = new Bitmap[4];
    public static String[] date = new String[4];
    public static int[] id = new int[4];
    public static String[] test1 = new String[4];
    public static String[] title = new String[4];
    public static String[] titleSau = new String[]{"توظيف موقع فلكر في التدريس", "بيع أول خريطة أصلية لـ\"ديزني لاند\" بـ708 آلاف دولار", "رئيس جامعة الشرق الأوسط :نعمل على تأمين فرصة عمل للطالب قبل تخرجه", "تعليم الأطفال للقرصنة الإلكترونية بالمدارس بالمملكة المتحدة"};
    public static String[] url = new String[4];
    public static String[] urlSau = new String[]{"http://algeriaplus.com/index.php/archives/1538", "http://algeriaplus.com/index.php/archives/1535", "http://algeriaplus.com/index.php/archives/1529", "http://algeriaplus.com/index.php/archives/1525"};
    private Context context;
    private Integer[] images = new Integer[]{Integer.valueOf(C0245R.drawable.slide1), Integer.valueOf(C0245R.drawable.slide2), Integer.valueOf(C0245R.drawable.slide3), Integer.valueOf(C0245R.drawable.slide4)};
    private LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    public int getCount() {
        return this.images.length;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, final int position) {
        this.layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        View view = this.layoutInflater.inflate(C0245R.layout.custom_layout, null);
        TextView test = (TextView) view.findViewById(C0245R.id.test);
        ImageView imageView = (ImageView) view.findViewById(C0245R.id.imageView);
        if (bitmapTab[position] != null) {
            imageView.setImageBitmap(bitmapTab[position]);
            test.setText(test1[position]);
        } else {
            imageView.setImageResource(this.images[position].intValue());
            System.out.println(test1[position] + " pos " + position);
            System.out.println(url[position] + " pos " + position);
            test.setText(titleSau[position]);
            url[position] = urlSau[position];
        }
        new Font().setFont(this.context, test);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.thi, Post.class);
                intent.putExtra("id", ViewPagerAdapter.id[position]);
                intent.putExtra("post_title", ViewPagerAdapter.title[position]);
                intent.putExtra("url", ViewPagerAdapter.url[position]);
                intent.putExtra("date", ViewPagerAdapter.date[position]);
                MainActivity.thi.startActivity(intent);
            }
        });
        ((ViewPager) container).addView(view, 0);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / ((float) width);
        float scaleHeight = ((float) newHeight) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }
}
