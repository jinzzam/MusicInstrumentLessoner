package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;

public class GuideExplainLayout extends LinearLayout {
    private TextView tvExplainLayUnit;
    private TextView tvExplainLayMain;
    private String unit;
    private String main;

    {
        initView();
    }

    public GuideExplainLayout(Context context,String unit,String main) {
        super(context);
        this.unit = unit;
        this.main = main;
        initValue();
    }

    public GuideExplainLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GuideExplainLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GuideExplainLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.guide_explain_layout, this);
        tvExplainLayUnit = findViewById(R.id.tvExplainLayUnit);
        tvExplainLayMain = findViewById(R.id.tvExplainLayMain);
    }

    private void initValue(){
        tvExplainLayUnit.setText(unit);
        tvExplainLayMain.setText(main);
    }
}
