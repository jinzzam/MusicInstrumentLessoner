package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplatePracticeDto;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class TemplatePositivePracticeLayout extends LinearLayout {
    private static Random random;
    private TextView tvTemplatePositivePracticeLayCount;
    private TextView tvTemplatePositiveLayPercent;
    private ImageView ivTemplatePositivePracticeLayListen;
    private ImageView ivTemplatePositivePracticeLayView;

    {
        initView();
        random = new Random();
    }

    public TemplatePositivePracticeLayout(Context context) {
        super(context);
    }

    public TemplatePositivePracticeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TemplatePositivePracticeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TemplatePositivePracticeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.template_positive_practice_layout, this);
        tvTemplatePositivePracticeLayCount = findViewById(R.id.tvTemplatePositivePracticeLayCount);
        tvTemplatePositiveLayPercent = findViewById(R.id.tvTemplatePositiveLayPercent);
        ivTemplatePositivePracticeLayListen=findViewById(R.id.ivTemplatePositivePracticeLayListen);
        ivTemplatePositivePracticeLayView=findViewById(R.id.ivTemplatePositivePracticeLayView);
    }

    public void setCustomAttr(TemplatePracticeDto dto) {
        tvTemplatePositivePracticeLayCount.setText(getResources().getText(R.string.template_practice_lay_count) + (dto.getPracticeId() + ""));
        if (DebugMode.DEBUG_MOD) {
            tvTemplatePositiveLayPercent.setText(""+getResources().getText(R.string.template_practice_lay_percent)
                    + dto.getPercent() + getResources().getText(R.string.template_practice_lay_percent_end));
        }
    }

    public ImageView getIvTemplatePositivePracticeLayListen() {
        return ivTemplatePositivePracticeLayListen;
    }

    public ImageView getIvTemplatePositivePracticeLayView() {
        return ivTemplatePositivePracticeLayView;
    }
}
