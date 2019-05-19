package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache.Entry;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool pool) {
        this.mHttpStack = httpStack;
        this.mPool = pool;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r19) throws com.android.volley.VolleyError {
        /*
        r18 = this;
        r12 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r10 = 0;
        r7 = 0;
        r14 = new java.util.HashMap;
        r14.<init>();
        r9 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r9.<init>();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r3 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r0 = r18;
        r0.addCacheHeaders(r9, r3);	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r0 = r18;
        r3 = r0.mHttpStack;	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r0 = r19;
        r10 = r3.performRequest(r0, r9);	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r8 = r10.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r15 = r8.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r3 = r10.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r14 = convertHeaders(r3);	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r3 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r15 != r3) goto L_0x0053;
    L_0x0037:
        r3 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r16 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r6 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        if (r6 != 0) goto L_0x004c;
    L_0x0041:
        r6 = 0;
    L_0x0042:
        r17 = 1;
        r0 = r16;
        r1 = r17;
        r3.<init>(r0, r6, r14, r1);	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
    L_0x004b:
        return r3;
    L_0x004c:
        r6 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r6 = r6.data;	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        goto L_0x0042;
    L_0x0053:
        r3 = r10.getEntity();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        if (r3 == 0) goto L_0x008d;
    L_0x0059:
        r3 = r10.getEntity();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r0 = r18;
        r7 = r0.entityToBytes(r3);	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
    L_0x0063:
        r16 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r4 = r16 - r12;
        r3 = r18;
        r6 = r19;
        r3.logSlowRequests(r4, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r15 < r3) goto L_0x0078;
    L_0x0074:
        r3 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r15 <= r3) goto L_0x0091;
    L_0x0078:
        r3 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r3.<init>();	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        throw r3;	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
    L_0x007e:
        r2 = move-exception;
        r3 = "socket";
        r6 = new com.android.volley.TimeoutError;
        r6.<init>();
        r0 = r19;
        attemptRetryOnException(r3, r0, r6);
        goto L_0x0004;
    L_0x008d:
        r3 = 0;
        r7 = new byte[r3];	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        goto L_0x0063;
    L_0x0091:
        r3 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        r6 = 0;
        r3.<init>(r15, r7, r14, r6);	 Catch:{ SocketTimeoutException -> 0x007e, ConnectTimeoutException -> 0x0098, MalformedURLException -> 0x00a7, IOException -> 0x00c9 }
        goto L_0x004b;
    L_0x0098:
        r2 = move-exception;
        r3 = "connection";
        r6 = new com.android.volley.TimeoutError;
        r6.<init>();
        r0 = r19;
        attemptRetryOnException(r3, r0, r6);
        goto L_0x0004;
    L_0x00a7:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r16 = "Bad URL ";
        r0 = r16;
        r6 = r6.append(r0);
        r16 = r19.getUrl();
        r0 = r16;
        r6 = r6.append(r0);
        r6 = r6.toString();
        r3.<init>(r6, r2);
        throw r3;
    L_0x00c9:
        r2 = move-exception;
        r15 = 0;
        r11 = 0;
        if (r10 == 0) goto L_0x010c;
    L_0x00ce:
        r3 = r10.getStatusLine();
        r15 = r3.getStatusCode();
        r3 = "Unexpected response code %d for %s";
        r6 = 2;
        r6 = new java.lang.Object[r6];
        r16 = 0;
        r17 = java.lang.Integer.valueOf(r15);
        r6[r16] = r17;
        r16 = 1;
        r17 = r19.getUrl();
        r6[r16] = r17;
        com.android.volley.VolleyLog.m10e(r3, r6);
        if (r7 == 0) goto L_0x0118;
    L_0x00f0:
        r11 = new com.android.volley.NetworkResponse;
        r3 = 0;
        r11.<init>(r15, r7, r14, r3);
        r3 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r15 == r3) goto L_0x00fe;
    L_0x00fa:
        r3 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r15 != r3) goto L_0x0112;
    L_0x00fe:
        r3 = "auth";
        r6 = new com.android.volley.AuthFailureError;
        r6.<init>(r11);
        r0 = r19;
        attemptRetryOnException(r3, r0, r6);
        goto L_0x0004;
    L_0x010c:
        r3 = new com.android.volley.NoConnectionError;
        r3.<init>(r2);
        throw r3;
    L_0x0112:
        r3 = new com.android.volley.ServerError;
        r3.<init>(r11);
        throw r3;
    L_0x0118:
        r3 = new com.android.volley.NetworkError;
        r3.<init>(r11);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
    }

    private void logSlowRequests(long requestLifetime, Request<?> request, byte[] responseContents, StatusLine statusLine) {
        if (DEBUG || requestLifetime > ((long) SLOW_REQUEST_THRESHOLD_MS)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(requestLifetime);
            objArr[2] = responseContents != null ? Integer.valueOf(responseContents.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.m9d(str, objArr);
        }
    }

    private static void attemptRetryOnException(String logPrefix, Request<?> request, VolleyError exception) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int oldTimeout = request.getTimeoutMs();
        try {
            retryPolicy.retry(exception);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{logPrefix, Integer.valueOf(oldTimeout)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{logPrefix, Integer.valueOf(oldTimeout)}));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> headers, Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                headers.put("If-None-Match", entry.etag);
            }
            if (entry.serverDate > 0) {
                headers.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.serverDate)));
            }
        }
    }

    protected void logError(String what, String url, long start) {
        long now = SystemClock.elapsedRealtime();
        VolleyLog.m12v("HTTP ERROR(%s) %d ms to fetch %s", what, Long.valueOf(now - start), url);
    }

    private byte[] entityToBytes(HttpEntity entity) throws IOException, ServerError {
        PoolingByteArrayOutputStream bytes = new PoolingByteArrayOutputStream(this.mPool, (int) entity.getContentLength());
        byte[] buffer = null;
        try {
            InputStream in = entity.getContent();
            if (in == null) {
                throw new ServerError();
            }
            buffer = this.mPool.getBuf(1024);
            while (true) {
                int count = in.read(buffer);
                if (count == -1) {
                    break;
                }
                bytes.write(buffer, 0, count);
            }
            byte[] toByteArray = bytes.toByteArray();
            return toByteArray;
        } finally {
            try {
                entity.consumeContent();
            } catch (IOException e) {
                VolleyLog.m12v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(buffer);
            bytes.close();
        }
    }

    private static Map<String, String> convertHeaders(Header[] headers) {
        Map<String, String> result = new HashMap();
        for (int i = 0; i < headers.length; i++) {
            result.put(headers[i].getName(), headers[i].getValue());
        }
        return result;
    }
}
