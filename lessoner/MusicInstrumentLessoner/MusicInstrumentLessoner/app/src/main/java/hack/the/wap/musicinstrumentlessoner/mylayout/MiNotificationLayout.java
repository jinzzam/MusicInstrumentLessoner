package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiNotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.myservice.TemplateService;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class MiNotificationLayout extends LinearLayout {
    private TemplateService templateService;
    private ImageView ivMiNotificationLayUserImage;
    private TextView tvMiNotificationLayName;
    private TextView tvMiNotificationLayDate;
    private TextView tvMiNotificationLayMain;
    private TextView tvMiNotificationLayMusicTitle;


    {
        initView();
        templateService = TemplateService.getInstance();
    }

    public MiNotificationLayout(Context context) {
        super(context);
    }

    public MiNotificationLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public MiNotificationLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MiNotificationLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_mi_notification, this);
        ivMiNotificationLayUserImage = findViewById(R.id.ivMiNotificationUserImage);
        tvMiNotificationLayName = findViewById(R.id.tvMiNotificationLayName);
        tvMiNotificationLayDate = findViewById(R.id.tvMiNotificationLayDate);
        tvMiNotificationLayMain = findViewById(R.id.tvMiNotificationLayMain);
        tvMiNotificationLayMusicTitle = findViewById(R.id.tvMiNotificationLayMusicTitle);
    }

    private void getAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.MiNotificationLayout);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attributeSet, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.MiNotificationLayout, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        String miNotificationLayDate = typedArray.getString(R.styleable.MiNotificationLayout_mi_notification_lay_date);
        String miNotificationLayMain = typedArray.getString(R.styleable.MiNotificationLayout_mi_notification_lay_main);
        String miNotificationLayMusicTitle = typedArray.getString(R.styleable.MiNotificationLayout_mi_notification_lay_music_title);
        tvMiNotificationLayDate.setText(miNotificationLayDate);
        tvMiNotificationLayMain.setText(miNotificationLayMain);
        tvMiNotificationLayMusicTitle.setText(miNotificationLayMusicTitle);
        typedArray.recycle();
    }

    public void setCustomAttr(MiNotificationDto dto) {
        tvMiNotificationLayName.setText(getResources().getText(R.string.app_name_kor));
        String time = dto.getRegistDateTime().replace("T", " ");
        time = time.substring(0, time.length() - 5);
        tvMiNotificationLayDate.setText(time);
        tvMiNotificationLayMain.setText(dto.getComment());
        tvMiNotificationLayMusicTitle.setText(templateService.getTemplateTitleById(dto.getMusicTemplateId()));
    }
}
