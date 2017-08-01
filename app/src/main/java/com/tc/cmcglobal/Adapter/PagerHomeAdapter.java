package com.tc.cmcglobal.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tc.cmcglobal.fragments.LessionFragment;
import com.tc.cmcglobal.fragments.LogOutFragment;
import com.tc.cmcglobal.fragments.TopFragment;

/**
 * Created by Quan Lee on 7/10/17.
 */

public class PagerHomeAdapter extends FragmentPagerAdapter{

    public PagerHomeAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return TopFragment.getInstance();
            //return LessionFragment.getInstance();
        }else if(position == 1){
            return LessionFragment.getInstance();
        } else if (position == 2){
            return LogOutFragment.getInstance();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 3;
    }
}
