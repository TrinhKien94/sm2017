package com.tc.cmcglobal.network;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;
import com.tc.cmcglobal.MainApplication;
import com.tc.cmcglobal.entities.Account;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyRequest extends Request<MyJson> {
    public static final String error = "errorCode";
    public static final String message = "message";
    public static final String total = "total";
    public static final String data = "data";
    private Listener<MyJson> _responseListener;
    private String url = "";
    private int initialTimeoutMs = 20 * 1000;
    private int maxNumRetries = 1;
    private float backoffMultiplier = 1.0f;
    private Map<String, String> params;
    private int statusCode;
    private String defautHeader = "";
    private Map<String, String > headerParam ;
    public MyRequest(int method, String url, Map<String, String> _params,
                        Listener<MyJson> responseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        // TODO Auto-generated constructor stub
        _responseListener = responseListener;
        this.url = url;
        this.params = _params;
        setRetryPolicy(new DefaultRetryPolicy(initialTimeoutMs, maxNumRetries,
                backoffMultiplier));

    }

    public MyRequest(String url, Map<String, String> _params,
                        Listener<MyJson> responseListener, ErrorListener errorListener) {
        super(Request.Method.POST, url, errorListener);
        // TODO Auto-generated constructor stub
        _responseListener = responseListener;
        this.url = url;
        this.params = _params;
        setRetryPolicy(new DefaultRetryPolicy(initialTimeoutMs, maxNumRetries,
                backoffMultiplier));

    }

    public MyRequest(String url, Listener<MyJson> responseListener,
                        ErrorListener errorListener) {
        super(Request.Method.GET, url, errorListener);
        // TODO Auto-generated constructor stub
        _responseListener = responseListener;
        this.url = url;
        setRetryPolicy(new DefaultRetryPolicy(initialTimeoutMs, maxNumRetries,
                backoffMultiplier));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        // TODO Auto-generated method stub
        return params;
    }

    @Override
    protected Response<MyJson> parseNetworkResponse(NetworkResponse response) {
        // TODO Auto-generated method stub
        Log.e("URL" , url);
        KeepSession.get().checkSessionCookie(response.headers);
        statusCode = response.statusCode;
//		Log.e("statusCode", statusCode + "");
        MyJson vegaJson = null;
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            if (json.length() == 0) {
                vegaJson = new MyJson();
                vegaJson.setStatusCode(statusCode);
                return Response.success(vegaJson,
                        HttpHeaderParser.parseCacheHeaders(response));
            } else if (json.startsWith("DATA")) {
                vegaJson = new MyJson();
                vegaJson.json = json;
                vegaJson.setStatusCode(statusCode);
                return Response.success(vegaJson,
                        HttpHeaderParser.parseCacheHeaders(response));
            }
            vegaJson = new MyJson(json);
            vegaJson.setStatusCode(statusCode);
//			Log.e("RESPONES::", json);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            return Response.error(new ParseError(e));
        }
        return Response.success(vegaJson,
                HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(MyJson response) {
        // TODO Auto-generated method stub
        _responseListener.onResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        // TODO Auto-generated method stub
        if (volleyError.networkResponse != null
                && volleyError.networkResponse.data != null) {
            String errorStr = new String(volleyError.networkResponse.data);
            errorStr += " in " + url;
            VolleyError error = new VolleyError(errorStr);
            volleyError = error;
        } else {
            String errorStr = "Network error in ";
            errorStr += url;
            VolleyError error = new VolleyError(errorStr);
            volleyError = error;
        }
        // VegaLog.e(url);
        return volleyError;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
        Account account = MainApplication.get().getAccount();
        headers.put("Accept", "application/json");

        if(account !=null){
            Log.d("account.token", account.token);
            headers.put("Authorization", account.token);
        }else{
            Log.d("account.token", "Error ");
        }

        KeepSession.get().addSessionCookie(headers);

        return headers;

    }
    public void setHeaderParams(Map<String , String> params){
        this.headerParam = params;
    }
//    public void setHeaderValueParams(String value) {
//        this.defautHeader = value;
//    }
}
