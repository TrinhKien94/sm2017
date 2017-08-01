package com.tc.cmcglobal;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tc.cmcglobal.entities.Account;

/**
 * Created by Quan Lee on 7/9/17.
 */

public class MainApplication extends Application{

    protected static MainApplication _instance;
    public static MainApplication get() {
        return _instance;
    }
    private Account mAccount ;
    private RequestQueue _requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        _requestQueue = Volley.newRequestQueue(this);
    }
    public RequestQueue getRequestQueue() {

        return _requestQueue;
    }
    public void setAccount(Account account){
        this.mAccount = account;
    }
    public Account getAccount(){
        return this.mAccount;
    }
}
