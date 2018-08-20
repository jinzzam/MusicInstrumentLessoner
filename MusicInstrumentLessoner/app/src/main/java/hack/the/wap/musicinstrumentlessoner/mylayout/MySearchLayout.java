package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.myactivity.MainActivity;

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
