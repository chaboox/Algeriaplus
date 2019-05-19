package com.chaboox.algeriaplus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chaboox.algeriaplus.constant.Const;
import com.chaboox.algeriaplus.helper.Font;
import com.chaboox.algeriaplus.helper.SingletonVolley;
import com.chaboox.algeriaplus.model.JsonDataModel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {
    public static List<JsonDataModel> JsonDatas;
    private String newString;
    private ProgressBar progressBar;
    private String url = Const.url;

    /* renamed from: com.chaboox.algeriaplus.SplashScreen$1 */
    class C05261 implements Listener<String> {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onResponse(java.lang.String r13) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x015f in list [B:9:0x013f]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1336735375.run(Unknown Source)
*/
            /*
            r12 = this;
            r11 = 8;
            r7 = ",53";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",45";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",48";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",51";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",47";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",50";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",46";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",49";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",71";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = ",1";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "1";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[53]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "53";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[45]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "45";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[48]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "48";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[51]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "51";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[47]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "47";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[50]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "50";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[46]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "46";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[49]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "49";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[71]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "71";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "[1]";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "1";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r13 = r13.replace(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r3 = new com.google.gson.Gson;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r3.<init>();	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r0 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r0.<init>(r13);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r4 = 0;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
        L_0x00ad:
            r7 = r0.length();	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            if (r4 >= r7) goto L_0x013b;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
        L_0x00b3:
            r1 = r0.getJSONObject(r4);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r1.toString();	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = com.chaboox.algeriaplus.model.JsonDataModel.class;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r6 = r3.fromJson(r7, r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r6 = (com.chaboox.algeriaplus.model.JsonDataModel) r6;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "link";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r1.getString(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r6.setLink(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "id";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r1.getInt(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r6.setId(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "categories";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r1.getInt(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r6.setCategorie(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "date";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r1.getString(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = 0;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r9 = 10;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r7.substring(r8, r9);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r6.setDate(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = com.chaboox.algeriaplus.SplashScreen.this;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "title";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = r1.getJSONObject(r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r9 = "rendered";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = r8.getString(r9);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r9 = "&#8220;";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r10 = "\"";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = r8.replace(r9, r10);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7.newString = r8;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = com.chaboox.algeriaplus.SplashScreen.this;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = com.chaboox.algeriaplus.SplashScreen.this;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = r8.newString;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r9 = "&#8221;";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r10 = "\"";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = r8.replace(r9, r10);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7.newString = r8;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = com.chaboox.algeriaplus.SplashScreen.this;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r7.newString;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r6.setTitleRendered(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = "better_featured_image";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r1.getJSONObject(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r8 = "source_url";	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = r7.getString(r8);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r6.setFeaturedMediaUrl(r7);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = com.chaboox.algeriaplus.SplashScreen.JsonDatas;	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7.add(r6);	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r4 = r4 + 1;
            goto L_0x00ad;
        L_0x013b:
            r7 = com.chaboox.algeriaplus.SplashScreen.JsonDatas;
            if (r7 == 0) goto L_0x015f;
        L_0x013f:
            r7 = com.chaboox.algeriaplus.SplashScreen.this;
            r7 = r7.progressBar;
            r7.setVisibility(r11);
            r5 = new android.content.Intent;
            r7 = com.chaboox.algeriaplus.SplashScreen.this;
            r7 = r7.getApplicationContext();
            r8 = com.chaboox.algeriaplus.MainActivity.class;
            r5.<init>(r7, r8);
            r7 = com.chaboox.algeriaplus.SplashScreen.this;
            r7.startActivity(r5);
            r7 = com.chaboox.algeriaplus.SplashScreen.this;
            r7.finish();
        L_0x015f:
            return;
        L_0x0160:
            r2 = move-exception;
            r2.printStackTrace();	 Catch:{ JSONException -> 0x0160, all -> 0x0189 }
            r7 = com.chaboox.algeriaplus.SplashScreen.JsonDatas;
            if (r7 == 0) goto L_0x015f;
        L_0x0168:
            r7 = com.chaboox.algeriaplus.SplashScreen.this;
            r7 = r7.progressBar;
            r7.setVisibility(r11);
            r5 = new android.content.Intent;
            r7 = com.chaboox.algeriaplus.SplashScreen.this;
            r7 = r7.getApplicationContext();
            r8 = com.chaboox.algeriaplus.MainActivity.class;
            r5.<init>(r7, r8);
            r7 = com.chaboox.algeriaplus.SplashScreen.this;
            r7.startActivity(r5);
            r7 = com.chaboox.algeriaplus.SplashScreen.this;
            r7.finish();
            goto L_0x015f;
        L_0x0189:
            r7 = move-exception;
            r8 = com.chaboox.algeriaplus.SplashScreen.JsonDatas;
            if (r8 == 0) goto L_0x01ae;
        L_0x018e:
            r8 = com.chaboox.algeriaplus.SplashScreen.this;
            r8 = r8.progressBar;
            r8.setVisibility(r11);
            r5 = new android.content.Intent;
            r8 = com.chaboox.algeriaplus.SplashScreen.this;
            r8 = r8.getApplicationContext();
            r9 = com.chaboox.algeriaplus.MainActivity.class;
            r5.<init>(r8, r9);
            r8 = com.chaboox.algeriaplus.SplashScreen.this;
            r8.startActivity(r5);
            r8 = com.chaboox.algeriaplus.SplashScreen.this;
            r8.finish();
        L_0x01ae:
            throw r7;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chaboox.algeriaplus.SplashScreen.1.onResponse(java.lang.String):void");
        }

        C05261() {
        }
    }

    /* renamed from: com.chaboox.algeriaplus.SplashScreen$2 */
    class C05272 implements ErrorListener {
        C05272() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            volleyError.printStackTrace();
            Toast.makeText(SplashScreen.this.getApplicationContext(), SplashScreen.this.getResources().getString(C0245R.string.toast_network_error), 1).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0245R.layout.activity_splash_screen);
        this.progressBar = (ProgressBar) findViewById(C0245R.id.progress_bar_splash_screen);
        new Font().setFont(getApplicationContext(), (TextView) findViewById(C0245R.id.tv_copyright));
        JsonDatas = new ArrayList();
        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(new StringRequest(0, this.url, new C05261(), new C05272()));
    }

    public void writedToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("dont_delete.txt", 0));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public String readdFromFile() {
        String result = "";
        try {
            InputStream inputStream = openFileInput("dont_delete.txt");
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    tempString = bufferedReader.readLine();
                    if (tempString == null) {
                        break;
                    }
                    stringBuilder.append(tempString);
                }
                inputStream.close();
                result = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return result;
    }
}
