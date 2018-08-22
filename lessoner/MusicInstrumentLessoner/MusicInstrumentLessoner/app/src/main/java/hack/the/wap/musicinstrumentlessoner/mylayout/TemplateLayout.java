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
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplateDto;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class TemplateLayout extends LinearLayout {
    private ImageView ivTemplateLayUserImage;
    private TextView tvTemplateLayMusicTitle;
    private TextView tvTemplateLayMain;
    private TextView tvTemplateLaySub;
    private ImageView ivTemplateLayTeacherImage;

    {
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
        inflate(getContext(), R.layout.template_layout, this);
        ivTemplateLayUserImage = findViewById(R.id.ivTemplateUserImage);
        tvTemplateLayMusicTitle = findViewById(R.id.tvTemplateLayMusicTitle);
        tvTemplateLayMain = findViewById(R.id.tvTemplateLayMain);
        tvTemplateLaySub= findViewById(R.id.tvTemplateLaySub);
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
        int templateLayTeacherImage = typedArray.getResourceId(R.styleable.TemplateLayout_template_lay_teacher_image,R.drawable.choa_round);
        ivTemplateLayUserImage.setImageResource(templateLayUserImage);
        tvTemplateLayMusicTitle.setText(templateLayMusicTitle);
        tvTemplateLayMain.setText(templateLayMain);
        tvTemplateLaySub.setText(templateLaySub);
        ivTemplateLayTeacherImage.setImageResource(templateLayTeacherImage);
        typedArray.recycle();
    }

    public void setCustomAttr(TemplateDto dto){
        ivTemplateLayUserImage.setImageResource(DebugImageMatch.getImageFromName(dto.getMusician()));
        tvTemplateLayMusicTitle.setText(dto.getMusicTitle());
        tvTemplateLayMain.setText(dto.getMain());
        tvTemplateLaySub.setText(dto.getSub());
        ivTemplateLayTeacherImage.setImageResource(DebugImageMatch.getImageFromName(dto.getOwner().getName()));
    }
}
