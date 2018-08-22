package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.NotificationDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class NotificationLayout extends LinearLayout {
    private ImageView ivNotificationLayUserImage;
    private TextView tvNotificationLayName;
    private TextView tvNotificationLayDate;
    private TextView tvNotificationLayMain;
    private TextView tvNotificationLayMusicTitle;

    {
        initView();
    }

    public NotificationLayout(Context context) {
        super(context);
    }

    public NotificationLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public NotificationLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NotificationLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.notification_layout, this);
        ivNotificationLayUserImage = findViewById(R.id.ivNotificationUserImage);
        tvNotificationLayName = findViewById(R.id.tvNotificationLayName);
        tvNotificationLayDate = findViewById(R.id.tvNotificationLayDate);
        tvNotificationLayMain = findViewById(R.id.tvNotificationLayMain);
        tvNotificationLayMusicTitle = findViewById(R.id.tvNotificationLayMusicTitle);

    }

    private void getAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.NotificationLayout);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attributeSet, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.NotificationLayout, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        int notificationLayUserImage = typedArray.getResourceId(R.styleable.NotificationLayout_notification_lay_user_image, R.drawable.choa_round);
        String notificationLayName = typedArray.getString(R.styleable.NotificationLayout_notification_lay_name);
        String notificationLayDate = typedArray.getString(R.styleable.NotificationLayout_notification_lay_date);
        String notificationLayMain = typedArray.getString(R.styleable.NotificationLayout_notification_lay_main);
        String notificationLayMusicTitle = typedArray.getString(R.styleable.NotificationLayout_notification_lay_music_title);
        ivNotificationLayUserImage.setImageResource(notificationLayUserImage);
        tvNotificationLayName.setText(notificationLayName);
        tvNotificationLayDate.setText(notificationLayDate);
        tvNotificationLayMain.setText(notificationLayMain);
        tvNotificationLayMusicTitle.setText(notificationLayMusicTitle);
        typedArray.recycle();
    }

    public void setCustomAttr(String name,String date,String main,String musicTitle){
        tvNotificationLayName.setText(name);
        tvNotificationLayDate.setText(date);
        tvNotificationLayMain.setText(main);
        tvNotificationLayMusicTitle.setText(musicTitle);
    }

    public void setCustomAttr(NotificationDto dto){
        ivNotificationLayUserImage.setImageResource(DebugImageMatch.getImageFromName(dto.getTemplate().getOwner().getName()));
        tvNotificationLayName.setText(dto.getTemplate().getOwner().getName());
        tvNotificationLayDate.setText(dto.getDate());
        tvNotificationLayMain.setText(dto.getMain());
        tvNotificationLayMusicTitle.setText(dto.getTemplate().getMusicTitle());
    }
}
