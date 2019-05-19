package com.google.android.gms.internal;

import com.android.volley.toolbox.HttpClientStack.HttpPatch;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class zzw implements zzy {
    protected final HttpClient zzaC;

    public static final class zza extends HttpEntityEnclosingRequestBase {
        public zza(String str) {
            setURI(URI.create(str));
        }

        public String getMethod() {
            return HttpPatch.METHOD_NAME;
        }
    }

    public zzw(HttpClient httpClient) {
        this.zzaC = httpClient;
    }

    private static void zza(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, zzk<?> zzk) throws zza {
        byte[] zzm = zzk.zzm();
        if (zzm != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(zzm));
        }
    }

    private static void zza(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    static HttpUriRequest zzb(zzk<?> zzk, Map<String, String> map) throws zza {
        HttpEntityEnclosingRequestBase httpPost;
        switch (zzk.getMethod()) {
            case -1:
                byte[] zzj = zzk.zzj();
                if (zzj == null) {
                    return new HttpGet(zzk.getUrl());
                }
                HttpUriRequest httpPost2 = new HttpPost(zzk.getUrl());
                httpPost2.addHeader("Content-Type", zzk.zzi());
                httpPost2.setEntity(new ByteArrayEntity(zzj));
                return httpPost2;
            case 0:
                return new HttpGet(zzk.getUrl());
            case 1:
                httpPost = new HttpPost(zzk.getUrl());
                httpPost.addHeader("Content-Type", zzk.zzl());
                zza(httpPost, (zzk) zzk);
                return httpPost;
            case 2:
                httpPost = new HttpPut(zzk.getUrl());
                httpPost.addHeader("Content-Type", zzk.zzl());
                zza(httpPost, (zzk) zzk);
                return httpPost;
            case 3:
                return new HttpDelete(zzk.getUrl());
            case 4:
                return new HttpHead(zzk.getUrl());
            case 5:
                return new HttpOptions(zzk.getUrl());
            case 6:
                return new HttpTrace(zzk.getUrl());
            case 7:
                httpPost = new zza(zzk.getUrl());
                httpPost.addHeader("Content-Type", zzk.zzl());
                zza(httpPost, (zzk) zzk);
                return httpPost;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    public HttpResponse zza(zzk<?> zzk, Map<String, String> map) throws IOException, zza {
        HttpUriRequest zzb = zzb(zzk, map);
        zza(zzb, (Map) map);
        zza(zzb, zzk.getHeaders());
        HttpParams params = zzb.getParams();
        int zzp = zzk.zzp();
        HttpConnectionParams.setConnectionTimeout(params, BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, zzp);
        return this.zzaC.execute(zzb);
    }
}
