package com.sm.cmcglobal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by SAMIR on 4/12/2016.
 */
public class CarScroll extends ScrollView {
    public CarScroll(Context context) {
        super(context);
    }

    public CarScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CarScroll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyUnconsumed, dxUnconsumed, dyConsumed);
    }
}
