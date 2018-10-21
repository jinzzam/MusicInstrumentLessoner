package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateGuideDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateWrongDto;

public class GuideExplainLayout extends LinearLayout {
    private TextView tvExplainLayUnit;
    private TextView tvExplainLayMain;
    private String unit;
    private String main;

    {
        initView();
    }

    public GuideExplainLayout(Context context, String unit, String main) {
        super(context);
        this.unit = unit;
        this.main = main;
        initValue();
    }

    public GuideExplainLayout(Context context, MusicTemplateGuideDto dto) {
        super(context);
        initValue(dto);
    }

    public GuideExplainLayout(Context context, MusicTemplateWrongDto dto) {
        super(context);
        initValue(dto);
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
        inflate(getContext(), R.layout.layout_guide_explain, this);
        tvExplainLayUnit = findViewById(R.id.tvExplainLayUnit);
        tvExplainLayMain = findViewById(R.id.tvExplainLayMain);
    }

    private void initValue() {
        tvExplainLayUnit.setText(unit);
        tvExplainLayMain.setText(main);
    }

    private void initValue(MusicTemplateGuideDto dto) {
        tvExplainLayUnit.setText(dto.getPlayTime());
        tvExplainLayMain.setText(dto.getComment());
    }

    private void initValue(MusicTemplateWrongDto dto) {
        if (dto.getWrongTimeEnd() != null) {
            tvExplainLayUnit.setText(dto.getWrongTimeStart() + "-" + dto.getWrongTimeEnd());
        } else {
            tvExplainLayUnit.setText(dto.getWrongTimeStart());
        }
        tvExplainLayMain.setText(dto.getComment());
    }
}
