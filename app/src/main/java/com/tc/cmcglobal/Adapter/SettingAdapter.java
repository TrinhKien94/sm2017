package com.tc.cmcglobal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tc.cmcglobal.R;
import com.tc.cmcglobal.entities.Setting;

import java.util.List;

/**
 * Created by Admin on 7/24/2017.
 */

public class SettingAdapter extends ArrayAdapter<Setting> {
    public SettingAdapter(Context context, int resource, List<Setting> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.setting, null);

        }

        return v;
    }
}