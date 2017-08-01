package com.tc.cmcglobal.fragments;
/**
 * Created by Quan Lee on 7/10/17.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tc.cmcglobal.Adapter.ListTopAdapter;
import com.tc.cmcglobal.MainApplication;
import com.tc.cmcglobal.R;
import com.tc.cmcglobal.entities.Lession;
import com.tc.cmcglobal.network.ApiManager;
import com.tc.cmcglobal.network.MyJson;
import com.tc.cmcglobal.network.MyRequest;
import java.util.List;



public class TopFragment extends Fragment{
    public static TopFragment getInstance(){
        return new TopFragment();
    }
    private RecyclerView mRecycleView ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_top , container , false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String url = ApiManager.getUrlGetLesson();
        MyRequest request  = new MyRequest(url, new Response.Listener<MyJson>() {
            @Override
            public void onResponse(MyJson myJson) {
                Log.e("Json" , myJson.data);
                if(myJson.isSuccess()){
                    List<Lession> lessions = myJson.toList(Lession.class);
                    mRecycleView.setAdapter(new ListTopAdapter(getContext() , lessions));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        MainApplication.get().getRequestQueue().add(request);
        mRecycleView = (RecyclerView)view.findViewById(R.id.listTop);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}
