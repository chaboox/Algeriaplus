package com.chaboox.algeriaplus.helper;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonVolley {
    private static Context context;
    private static SingletonVolley instance;
    private RequestQueue requestQueue = getRequestQueue();

    private SingletonVolley(Context context) {
        context = context;
    }

    public RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(context);
        }
        return this.requestQueue;
    }

    public static synchronized SingletonVolley getInstance(Context context) {
        SingletonVolley singletonVolley;
        synchronized (SingletonVolley.class) {
            if (instance == null) {
                instance = new SingletonVolley(context);
            }
            singletonVolley = instance;
        }
        return singletonVolley;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        this.requestQueue.add(request);
    }
}
