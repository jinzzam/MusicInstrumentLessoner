package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplatePracticeDto;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class TemplateNegativePracticeLayout extends LinearLayout {
    TextView tvTemplateNegativePracticeLayCount;

    {
        initView();
    }

    public TemplateNegativePracticeLayout(Context context) {
        super(context);
    }

    public TemplateNegativePracticeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TemplateNegativePracticeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TemplateNegativePracticeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.template_negative_practice_layout, this);
        tvTemplateNegativePracticeLayCount = findViewById(R.id.tvTemplateNegativePracticeLayCount);
    }

    public void setCustomAttr(TemplatePracticeDto dto) {
        tvTemplateNegativePracticeLayCount.setText(getResources().getText(R.string.template_practice_lay_count) + (dto.getPracticeId() + ""));
    }
}
