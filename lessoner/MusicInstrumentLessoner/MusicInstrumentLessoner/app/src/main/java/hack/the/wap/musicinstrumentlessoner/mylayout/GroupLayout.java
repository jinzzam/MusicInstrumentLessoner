package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.model.myservice.GroupService;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class GroupLayout extends LinearLayout {
    private static GroupService groupService;
    private ImageView ivGroupLayEduImage;
    private TextView tvGroupLayName;
    private TextView tvGroupLayMain;
    private TextView tvGroupLayMusicTitle;

    {
        groupService = GroupService.getInstance();
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
        inflate(getContext(), R.layout.layout_group, this);
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

    @SuppressLint("SetTextI18n")
    public void setCustomAttr(MiGroupDto dto) {
        ivGroupLayEduImage.setImageResource(DebugImageMatch.getImageFromName(dto.getGroupName()));
        tvGroupLayName.setText(dto.getGroupName());
        tvGroupLayMain.setText(""
                + getResources().getText(R.string.groupLayTeacherNum) + groupService.teacherCount(dto.getGroupName()) + getResources().getText(R.string.groupLayPunit)
                + getResources().getText(R.string.groupLaySeperator)
                + getResources().getText(R.string.groupLayUserNum) + groupService.studentCount(dto.getGroupName()) + getResources().getText(R.string.groupLayPunit));
        int templateCount = 0;

        tvGroupLayMusicTitle.setText("" + getResources().getText(R.string.groupLayUsageTempates)
                + groupService.teacherTemplateCount(dto.getGroupName()) + getResources().getText(R.string.groupLayCunit));
    }
}
