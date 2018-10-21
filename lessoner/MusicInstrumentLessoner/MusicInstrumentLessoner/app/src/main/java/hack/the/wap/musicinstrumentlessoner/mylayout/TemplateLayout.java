package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.myservice.TemplateService;
import hack.the.wap.musicinstrumentlessoner.model.myservice.UserService;
import hack.the.wap.musicinstrumentlessoner.session.Session;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class TemplateLayout extends LinearLayout {
    private static Session session;
    private UserService userService = UserService.getInstance();
    private TemplateService templateService = TemplateService.getInstance();

    private ImageView ivTemplateLayUserImage;
    private TextView tvTemplateLayMusicTitle;
    private TextView tvTemplateLayMain;
    private TextView tvTemplateLaySub;
    private ImageView ivTemplateLayTeacherImage;

    {
        session = Session.getInstance();
        initView();
    }

    public TemplateLayout(Context context) {
        super(context);
    }

    public TemplateLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public TemplateLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TemplateLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_template, this);
        ivTemplateLayUserImage = findViewById(R.id.ivTemplateUserImage);
        tvTemplateLayMusicTitle = findViewById(R.id.tvTemplateLayMusicTitle);
        tvTemplateLayMain = findViewById(R.id.tvTemplateLayMain);
        tvTemplateLaySub = findViewById(R.id.tvTemplateLaySub);
        ivTemplateLayTeacherImage = findViewById(R.id.ivTemplateLayTeacherImage);
    }

    private void getAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.TemplateLayout);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attributeSet, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.TemplateLayout, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        int templateLayUserImage = typedArray.getResourceId(R.styleable.TemplateLayout_template_lay_user_image, R.drawable.beethoven_round);
        String templateLayMusicTitle = typedArray.getString(R.styleable.TemplateLayout_template_lay_music_title);
        String templateLayMain = typedArray.getString(R.styleable.TemplateLayout_template_lay_main);
        String templateLaySub = typedArray.getString(R.styleable.TemplateLayout_template_lay_sub);
        int templateLayTeacherImage = typedArray.getResourceId(R.styleable.TemplateLayout_template_lay_teacher_image, R.drawable.choa_round);
        ivTemplateLayUserImage.setImageResource(templateLayUserImage);
        tvTemplateLayMusicTitle.setText(templateLayMusicTitle);
        tvTemplateLayMain.setText(templateLayMain);
        tvTemplateLaySub.setText(templateLaySub);
        ivTemplateLayTeacherImage.setImageResource(templateLayTeacherImage);
        typedArray.recycle();
    }

    public void setCustomAttr(MusicTemplateAssignmentDto dto) {
        ivTemplateLayUserImage.setImageResource(DebugImageMatch.getImageFromName(session.getTemplates().get(templateService.getTemplateTitleById(dto.getMusicTemplateId())).getMusician()));
        tvTemplateLayMusicTitle.setText(session.getTemplates().get(templateService.getTemplateTitleById(dto.getMusicTemplateId())).getMusicTitle());
        tvTemplateLayMain.setText("남은 반복 횟수 : " + dto.getToDoCount() + "/10");
        String comment = successComment(dto.getSuccessPercent());
        tvTemplateLaySub.setText("성공률 : " + dto.getSuccessPercent() + "% " + comment);
        ivTemplateLayTeacherImage.setImageResource(DebugImageMatch.getImageFromName(session.getTemplates().get(templateService.getTemplateTitleById(dto.getMusicTemplateId())).getOwner()));
    }

    public String successComment(int successPercent) {
        if (successPercent < 50) {
            return "\n연습이 더 필요해요!";
        } else if (50 <= successPercent && successPercent < 70) {
            return "\n시작이 반이에요, 힘을내요";
        } else if (70 <= successPercent && successPercent < 80) {
            return "\n조금만 더 노력해봐요, 화이팅";
        } else if (80 <= successPercent && successPercent < 95) {
            return "\n아주 훌륭한 솜씨네요!";
        } else if (95 <= successPercent && successPercent <= 100) {
            return "\n정말 완벽해요!";
        }
        return null;
    }
}
