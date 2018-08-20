package com.example.jinzzam.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.jinzzam.musicinstrumentlessoner.R;


public class MySeparatorLayout extends LinearLayout {

    {
        inflate(getContext(), R.layout.my_separator_layout,this);
    }

    public MySeparatorLayout(Context context) {
        super(context);
    }

    public MySeparatorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MySeparatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySeparatorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
