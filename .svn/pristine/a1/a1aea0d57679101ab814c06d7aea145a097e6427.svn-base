package com.tc.cmcglobal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tc.cmcglobal.Adapter.LessonAdapter;
import com.tc.cmcglobal.LoginActivity;
import com.tc.cmcglobal.MainActivity;
import com.tc.cmcglobal.MainApplication;
import com.tc.cmcglobal.R;
import com.tc.cmcglobal.entities.Account;
import com.tc.cmcglobal.entities.Classes;
import com.tc.cmcglobal.entities.LessionList;
import com.tc.cmcglobal.network.ApiManager;
import com.tc.cmcglobal.network.MyJson;
import com.tc.cmcglobal.network.MyRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by KingBob on 7/16/2017.
 */

public class LessionFragment extends Fragment{
    public static LessionFragment getInstance(){
        LessionFragment fragment = new LessionFragment();
        return fragment;
    }
    private RecyclerView mRecycleView ;
    private List<LessionList> mLessions = new ArrayList<LessionList>();
    private List<Classes> mClass = new ArrayList<Classes>();
    private LessonAdapter mAdapter;
    private CalendarView mCalendarView;
    private Spinner mSpinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson , container , false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycleView = (RecyclerView)view.findViewById(R.id.list_lesson);
        mAdapter = new LessonAdapter(getContext() , mLessions);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleView.setAdapter(mAdapter);


        mCalendarView = (CalendarView)view.findViewById(R.id.calendar);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month +1;
                String dateFilter = year +"-"+month+"-"+dayOfMonth;
                Log.d("monthmonthmonth", "onSelectedDayChange: "+dateFilter);
                Spinner spinner = (Spinner)getView().findViewById(R.id.planets_spinner);
                String spinnerSelect = spinner.getSelectedItem().toString();
                int classId = getIdByString(mClass,spinnerSelect);
                getLessionByDate(classId, dateFilter);
            }
        });

        mSpinner = (Spinner)view.findViewById(R.id.planets_spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int classId = getIdByString(mClass,mSpinner.getSelectedItem().toString());
                mCalendarView = (CalendarView)view.findViewById(R.id.calendar);
                final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String dateFilter = dateFormat.format((mCalendarView.getDate()));
                Log.d("dateFilterdateFilter", "onItemSelected: "+dateFilter);
               //getLessionByDate(classId, dateFilter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        getLessionByDate(-1, null);


    }
    private int getIdByString(List<Classes> ls , String name){
        for(int i=0 ; i< ls.size() ; i++){
            if(ls.get(i).class_nm.equals(name)){
                return ls.get(i).system_id;
            }
        }
        return -1;
    }
    private void getLessionByDate(int classId, String date){


        String urlGetClass = ApiManager.getUrlGetClass();
        MyRequest requestClass  = new MyRequest(urlGetClass, new Response.Listener<MyJson>() {
            @Override
            public void onResponse(MyJson myJson) {
                Log.e("Json", myJson.data);
                if(myJson.isSuccess()){

                    List<Classes> classList = myJson.toList(Classes.class);
                    mClass = classList;
                    String [] titleClasses = new String[(classList.size()+1)];
                    titleClasses[0] = "全て";
                    for(int i = 0 ; i < classList.size() ; i++ )
                    {
                        titleClasses[(i+1)] = classList.get(i).getTitle();
                    }

                    Spinner dropdown = (Spinner)getView().findViewById(R.id.planets_spinner);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, titleClasses);
                    dropdown.setAdapter(adapter);


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("onErrorResponse" ,"onErrorResponse");
            }
        });
        MainApplication.get().getRequestQueue().add(requestClass);

        String urlGetLesson = ApiManager.getUrlGetLesson();
        HashMap<String , String> params = new HashMap<>();
        if(date != null){
            params.put("class_id" ,""+classId);
            params.put("date" ,date);
            Log.d("getLessionByDate", "getLessionByDate: "+params);
        }


        MyRequest request  = new MyRequest(urlGetLesson,params, new Response.Listener<MyJson>() {
            @Override
            public void onResponse(MyJson myJson) {
                Log.e("urlGetLesson" , myJson.data);
                if(myJson.isSuccess()){
                    List<LessionList> lessionList = myJson.toList(LessionList.class);

                    mRecycleView.setAdapter(new LessonAdapter(getContext() , lessionList));
                    //mLessions.addAll(myJson.toList(LessionList.class));
                    //mAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Quan", "urlGetLesson: eror");
            }
        });
        MainApplication.get().getRequestQueue().add(request);

//          Old get lesson
//        String url = ApiManager.getUrlGetLesson();
//        MyRequest request  = new MyRequest(url, new Response.Listener<MyJson>() {
//            @Override
//            public void onResponse(MyJson myJson) {
//                Log.e("Json", myJson.data);
//                if(myJson.isSuccess()){
//                    List<LessionList> lessionList = myJson.toList(LessionList.class);
//
//                    mRecycleView.setAdapter(new LessonAdapter(getContext() , lessionList));
//                    //mLessions.addAll(myJson.toList(LessionList.class));
//                    //mAdapter.notifyDataSetChanged();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.e("onErrorResponse" ,"onErrorResponse");
//            }
//        });
//        MainApplication.get().getRequestQueue().add(request);

    }

}
