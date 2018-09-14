package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.mylayout.TeacherImageLayout;
import hack.the.wap.musicinstrumentlessoner.mylayout.UserImageLayout;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class UserGroupDetailActivity extends AppCompatActivity {
    private static Session session;
    private MiGroupDto mainUserGroup;
    private ImageView ivActUserGroupDetailLeftArrow;
    private ImageView ivActUserGroupDetailMusicPlace;
    private TextView tvActUserGroupDetailName;
    private TextView tvActUserGroupDetailMain;
    private TextView tvActUserGroupDetailSub;
    private LinearLayout llActUserGroupDetailTeacher;
    private LinearLayout llActUserGroupDetailUser;


    {
        session = Session.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_group_detail);
        Intent intent = getIntent();
        mainUserGroup = (MiGroupDto) intent.getSerializableExtra("data");
        Log.e("SAFE", "onCreate >>> " + mainUserGroup);
        initView();
        viewSetListener();
        viewSetValue();
    }

    private void initView() {
        ivActUserGroupDetailLeftArrow = findViewById(R.id.ivActUserGroupDetailLeftArrow);
        ivActUserGroupDetailMusicPlace = findViewById(R.id.ivActUserGroupDetailMusicPlace);
        tvActUserGroupDetailName = findViewById(R.id.tvActUserGroupDetailName);
        tvActUserGroupDetailMain = findViewById(R.id.tvActUserGroupDetailMain);
        tvActUserGroupDetailSub = findViewById(R.id.tvActUserGroupDetailSub);
        llActUserGroupDetailTeacher = findViewById(R.id.llActUserGroupDetailTeacher);
        llActUserGroupDetailUser = findViewById(R.id.llActUserGroupDetailUser);
        for (MiTeacherDto dto : session.getGroupTeachers().values()) {
            llActUserGroupDetailTeacher.addView(new TeacherImageLayout(this, dto));
        }
        for (MiUserDto dto : session.getGroupStudents().values()) {
            llActUserGroupDetailUser.addView(new UserImageLayout(this, dto));
        }
    }

    private void viewSetListener() {
        ivActUserGroupDetailLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }

    private void viewSetValue() {
        ivActUserGroupDetailMusicPlace.setImageResource(DebugImageMatch.getImageFromName(mainUserGroup.getGroupName()));
        tvActUserGroupDetailName.setText(mainUserGroup.getGroupName());
        if (mainUserGroup.getGroupName() != null) {
            tvActUserGroupDetailMain.setText(mainUserGroup.getGroupName());
        } else {
            tvActUserGroupDetailMain.setText(getResources().getString(R.string.userGroupEmptyDefault));
        }
        if (mainUserGroup.getInfo() != null) {
            tvActUserGroupDetailSub.setText(mainUserGroup.getInfo());
        } else {
            tvActUserGroupDetailSub.setText(getResources().getString(R.string.userGroupEmptyDefault));
        }
    }
}
