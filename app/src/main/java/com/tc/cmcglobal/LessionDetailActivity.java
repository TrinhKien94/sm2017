package com.tc.cmcglobal;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tc.cmcglobal.Adapter.LessonStudentAdapter;
import com.tc.cmcglobal.entities.Lession;
import com.tc.cmcglobal.entities.Student;
import com.tc.cmcglobal.network.ApiManager;
import com.tc.cmcglobal.network.MyJson;
import com.tc.cmcglobal.network.MyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by KingBob on 7/16/2017.
 */

public class LessionDetailActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private int lessionID;
    private String className;
    private String startTime;
    private String date;
    private RecyclerView mListView;

    private SearchView searchView;
    private MenuItem searchMenuItem;

    String url = "http://192.168.10.167/api/v1/lesson/getListUsers/1";


    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

    private static final String KEY_VER = "class_nm";
    private static final String KEY_NAME = "lession_nm";
    private static final String KEY_API = "api";
    private LessonStudentAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lession_detail_activity);
        lessionID = getIntent().getIntExtra("lessionidextra" , -1);
        className = getIntent().getStringExtra("lessionclassnm");
        startTime = getIntent().getStringExtra("lessionstarttime");
        date = getIntent().getStringExtra("lessiondate");
        Log.i( "lessionidextra", lessionID + " lessionidextra");
        Log.i( "lessionclassnm", className);
        Log.i( "lessionstarttime", startTime);
        TextView lsDate = (TextView)findViewById(R.id.ls_date);
        TextView lsClassName= (TextView)findViewById(R.id.ls_name);
        TextView lsStartTime = (TextView)findViewById(R.id.ls_starttime);
        MenuView.ItemView btnSearch = (MenuView.ItemView)findViewById(R.id.mn_search);
        lsDate.setText(date);
        lsClassName.setText("Class name: "+className);
        lsStartTime.setText(startTime);
        final TextView lsAttendance = (TextView)findViewById(R.id.ls_attendance);
        mListView = (RecyclerView) findViewById(R.id.list_students);

        mListView.setLayoutManager(new LinearLayoutManager(LessionDetailActivity.this));
        mListView.setAdapter(mAdapter);

        String url = ApiManager.getUrlGetLessonDetail(lessionID);
        MyRequest request  = new MyRequest(url, new Response.Listener<MyJson>() {
            @Override
            public void onResponse(MyJson myJson) {
                Log.e("Json" , myJson.data);
                if(myJson.isSuccess()){
                    Lession lession = myJson.to(Lession.class);
                    mAdapter=new LessonStudentAdapter(LessionDetailActivity.this , lession.students);
                    mListView.setAdapter(mAdapter);

                    int attendance =0;
                    for(Student student:lession.students){
                        if(student.status.equals("1")||student.status.equals("4")||student.status.equals("5")){
                            attendance++;
                        }
                    }
                    lsAttendance.setText(attendance+"/"+lession.students.size());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("BBBBB", "onResponse: ");
            }
        });
        MainApplication.get().getRequestQueue().add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.mn_search).getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //TODO: Reset your views
                    return false;
                }
            });
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String searchView) {
                    return false; //do the default
                }

                @Override
                public boolean onQueryTextChange(String search) {
                    Log.i("Search string",search);
                    mAdapter.getFilter().filter(search);
                    //NOTE: doing anything here is optional, onNewIntent is the important bit
                    if (search.length() > 1) { //2 chars or more
                        //TODO: filter/return results
                    } else if (search.length() == 0) {
                        //TODO: reset the displayed data
                    }
                    return false;
                }

            });
        }

    return true;
}

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO: actually do some filtering / set results
        }
    }

    private void getInfo(){

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("alahu akbar", "ikaaj");
        return true;
    }
}
