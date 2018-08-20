package com.example.jinzzam.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.jinzzam.musicinstrumentlessoner.R;


public class MySearchLayout extends LinearLayout {

    {
        inflate(getContext(), R.layout.my_search_layout, this);
    }

    public MySearchLayout(Context context) {
        super(context);

    }

    public MySearchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MySearchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySearchLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
