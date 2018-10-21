package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplatePracticeDto;

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
        ivTemplatePositivePracticeLayListen = findViewById(R.id.ivTemplatePositivePracticeLayListen);
        ivTemplatePositivePracticeLayView = findViewById(R.id.ivTemplatePositivePracticeLayView);
    }

    public void setCustomAttr(MusicTemplatePracticeDto dto) {
        String comment = successComment(dto.getCompletePercent());
        tvTemplatePositivePracticeLayCount.setText(getResources().getText(R.string.LayTemplatePracticeMusicNum) + (dto.getMusicTemplatePracticeId() + ""));
        tvTemplatePositiveLayPercent.setText("" + getResources().getText(R.string.LayTemplatePracticeSuccessPercent)
                + " " + dto.getCompletePercent() + getResources().getText(R.string.LayTemplatePracticeSuccessPercentEnd) + " " + comment);

        if (DebugMode.DEBUG_MOD) {
            tvTemplatePositiveLayPercent.setText("" + getResources().getText(R.string.LayTemplatePracticeSuccessPercent)
                    + " " + dto.getCompletePercent() + getResources().getText(R.string.LayTemplatePracticeSuccessPercentEnd));
        }
    }

    public String successComment(int Percent) {
        if (Percent < 50) {
            return "\n연습이 더 필요해요!";
        } else if (50 <= Percent && Percent < 70) {
            return "\n시작이 반이에요, 힘을내요";
        } else if (70 <= Percent && Percent < 80) {
            return "\n조금만 더 노력해봐요, 화이팅";
        } else if (80 <= Percent && Percent < 95) {
            return "\n아주 훌륭한 솜씨네요!";
        } else if (95 <= Percent && Percent <= 100) {
            return "\n정말 완벽해요!";
        }
        return null;
    }

    public ImageView getIvTemplatePositivePracticeLayListen() {
        return ivTemplatePositivePracticeLayListen;
    }

    public ImageView getIvTemplatePositivePracticeLayView() {
        return ivTemplatePositivePracticeLayView;
    }
}
