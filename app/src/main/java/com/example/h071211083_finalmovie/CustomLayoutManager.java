package com.example.h071211083_finalmovie;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

public class CustomLayoutManager extends GridLayoutManager {

    public CustomLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public int getSpanSize(int position) {
        if (position % 2 == 0) {
            return 1;
        } else {
            return getSpanCount();
        }
    }
}
