package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.myservice.UserService;

public class TeacherImageLayout extends ImageLayout {
    private UserService userService;
    private MiTeacherDto mainTeacher = new MiTeacherDto();
    private TextView tvMyImageName;
    private TextView tvMyImageEmail;

    public TeacherImageLayout(Context context, MiTeacherDto dto) {
        super(context);
        mainTeacher = dto;
        userService = UserService.getInstance();
        tvMyImageName = findViewById(R.id.tvMyImageName);
        tvMyImageEmail = findViewById(R.id.tvMyImageEmail);
        setImage(mainTeacher);
    }

    public TeacherImageLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TeacherImageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TeacherImageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setImage(MiTeacherDto mainTeacher) {
        Log.e("선생이미지레이아웃", "setImage: " + mainTeacher);
        ivMyImage.setImageResource(DebugImageMatch.getImageFromName(userService.getUserName(mainTeacher.getTeacherEmail())));
        tvMyImageName.setText(userService.getUserName(mainTeacher.getTeacherEmail()));
        tvMyImageEmail.setText(mainTeacher.getTeacherEmail());
    }
}
