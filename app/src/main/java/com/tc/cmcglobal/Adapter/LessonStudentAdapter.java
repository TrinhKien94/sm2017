package com.tc.cmcglobal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.tc.cmcglobal.R;
import com.tc.cmcglobal.entities.Student;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Quan Lee on 7/10/17.
 */

public class LessonStudentAdapter extends RecyclerView.Adapter implements Filterable{
    List<Student> mDisplayedValues;
    private ArrayList<Student> mOriginalValues; // Original Values
    private ArrayList<Student> mDisplayedValues1;    // Values to be displayed
    Context context;
    public LessonStudentAdapter(Context context , List mDisplayedValues){
        this.context = context;
        this.mDisplayedValues = mDisplayedValues;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(student , parent, false);
       View view = LayoutInflater.from(context).inflate(R.layout.list_student , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // set onclick
        final Student student = mDisplayedValues.get(position);

        Log.d("studentstudent", "onBindViewHolder: "+ student);
        ((ViewHolder)holder).mTextView.setText("講義名: "+student.first_nm);
        ((ViewHolder)holder).mTextView1.setText("担当教員： "+student.last_nm);
        ((ViewHolder)holder).mTextView2.setText("担当教員： "+student.user_id);
        ((ViewHolder)holder).mTextView3.setText(student.status);
        ((ViewHolder) holder).mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
//        ((ViewHolder)holder).mTextView4.setImageResource(student.avatar););


        //((ViewHolder)holder).mTextView2.setText("日付："+student.card_id+"（金）"+student.card_type+"－"+student.user_id+"\n");

    }

    @Override
    public int getItemCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter;
        filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<Student>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Student> FilteredArrList = new ArrayList<Student>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Student>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String first_nm = mOriginalValues.get(i).first_nm;
                        String last_nm = mOriginalValues.get(i).last_nm;
                        String user_cd = mOriginalValues.get(i).user_cd;
                        String status = mOriginalValues.get(i).status;
                        Log.i("Test string contain",first_nm.contains(constraint)+" "+first_nm);
                        if (first_nm.contains(constraint)||
                            last_nm.contains(constraint)||
                            status.contains(constraint)||
                            user_cd.contains(constraint)) {
                            FilteredArrList.add(mOriginalValues.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView ;
        private TextView mTextView1 ;
        private TextView mTextView2 ;
        private TextView mTextView3 ;
        private ImageView mTextView4 ;
        ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.std_nm_cana);
            mTextView1 = (TextView)itemView.findViewById(R.id.std_nm_canji);
            mTextView2 = (TextView)itemView.findViewById(R.id.std_id);
            mTextView3 = (TextView)itemView.findViewById(R.id.std_stt);
            mTextView4 = (ImageView)itemView.findViewById(R.id.img_location);

        }
    }


}
