package com.jtvr.view.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by yy on 2017/4/5.
 */
public class MovieViewPager extends ViewPager{
    public MovieViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    private ViewGroup parent;

    public void setParent(ViewGroup parent) {
        this.parent = parent;
    }
}
