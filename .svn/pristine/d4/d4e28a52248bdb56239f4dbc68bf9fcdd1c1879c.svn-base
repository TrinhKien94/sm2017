package com.tc.cmcglobal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tc.cmcglobal.R;
import com.tc.cmcglobal.entities.LessionList;

import java.util.List;

/**
 * Created by Quan Lee on 7/10/17.
 */

public class LessonAdapter extends RecyclerView.Adapter{
    List<LessionList> lessionList;
    Context context ;
    public LessonAdapter(Context context , List lessionList){
        this.context = context;
        this.lessionList = lessionList;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lession , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // set onclick
        LessionList lession = lessionList.get(position);
        ((ViewHolder)holder).mTextView.setText("講義名: "+lession.lesson_nm);
        ((ViewHolder)holder).mTextView1.setText("担当教員： "+lession.teacher_name);
        ((ViewHolder)holder).mTextView2.setText("日付："+lession.date+"（金）"+lession.start_time+"－"+lession.end_time+"\n");

    }

    @Override
    public int getItemCount() {
        return lessionList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView ;
        private TextView mTextView1 ;
        private TextView mTextView2 ;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.leson_nm);
            mTextView1 = (TextView)itemView.findViewById(R.id.teacher_nm);
            mTextView2 = (TextView)itemView.findViewById(R.id.lesson_time);
        }
    }


}
