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
import hack.the.wap.musicinstrumentlessoner.model.dto.TeacherDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.UserGroupDto;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class GroupLayout extends LinearLayout {
    private ImageView ivGroupLayEduImage;
    private TextView tvGroupLayName;
    private TextView tvGroupLayMain;
    private TextView tvGroupLayMusicTitle;

    {
        initView();
    }

    public GroupLayout(Context context) {
        super(context);
    }

    public GroupLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public GroupLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GroupLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.group_layout, this);
        ivGroupLayEduImage = findViewById(R.id.ivGroupEduImage);
        tvGroupLayName = findViewById(R.id.tvGroupLayName);
        tvGroupLayMain = findViewById(R.id.tvGroupLayMain);
        tvGroupLayMusicTitle = findViewById(R.id.tvGroupLayMusicTitle);

    }

    private void getAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.GroupLayout);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attributeSet, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.GroupLayout, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        int notificationLayEduImage = typedArray.getResourceId(R.styleable.GroupLayout_group_lay_edu_image, R.drawable.music_place_round);
        String notificationLayName = typedArray.getString(R.styleable.GroupLayout_group_lay_name);
        String notificationLayMain = typedArray.getString(R.styleable.GroupLayout_group_lay_main);
        String notificationLaySub = typedArray.getString(R.styleable.GroupLayout_group_lay_sub);
        ivGroupLayEduImage.setImageResource(notificationLayEduImage);
        tvGroupLayName.setText(notificationLayName);
        tvGroupLayMain.setText(notificationLayMain);
        tvGroupLayMusicTitle.setText(notificationLaySub);
        typedArray.recycle();
    }

    public void setCustomAttr(String name, String date, String main, String musicTitle) {
        tvGroupLayName.setText(name);
        tvGroupLayMain.setText(main);
        tvGroupLayMusicTitle.setText(musicTitle);
    }

    public void setCustomAttr(UserGroupDto dto) {
        ivGroupLayEduImage.setImageResource(DebugImageMatch.getImageFromName(dto.getName()));
        tvGroupLayName.setText(dto.getName());
        tvGroupLayMain.setText(""
                + getResources().getText(R.string.group_lay_teacher) + dto.getTeachers().size() + getResources().getText(R.string.punit)
                + getResources().getText(R.string.sep)
                + getResources().getText(R.string.group_lay_user) + dto.getUsers().size() + getResources().getText(R.string.punit));
        int templateCount = 0;
        for (TeacherDto atom : dto.getTeachers().values()) {
            templateCount += atom.getTemplates().size();
        }
        tvGroupLayMusicTitle.setText("" + getResources().getText(R.string.group_lay_tempate_count)
                + templateCount + getResources().getText(R.string.cunit));
    }
}
