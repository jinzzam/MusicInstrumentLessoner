package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import hack.the.wap.musicinstrumentlessoner.R;

public class ImageLayout extends LinearLayout {
    protected ImageView ivMyImage;

    {
        initView();
    }

    public ImageLayout(Context context) {
        super(context);
    }

    public ImageLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setImageResource(int imageId) {
        ivMyImage.setImageResource(imageId);
    }

    private void initView() {
        inflate(getContext(), R.layout.image_layout, this);
        ivMyImage = findViewById(R.id.ivMyImage);
    }
}
