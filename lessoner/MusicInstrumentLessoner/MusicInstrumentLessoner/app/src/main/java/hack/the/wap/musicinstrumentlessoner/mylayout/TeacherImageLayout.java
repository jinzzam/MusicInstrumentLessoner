package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;

public class TeacherImageLayout extends ImageLayout {
    private MiTeacherDto mainTeacher;
    private TextView tvMyImageName;
    private TextView tvMyImageEmail;
    public TeacherImageLayout(Context context, MiTeacherDto dto) {
        super(context);
        mainTeacher = dto;
        tvMyImageName = findViewById(R.id.tvMyImageName);
        tvMyImageEmail = findViewById(R.id.tvMyImageEmail);
        setImage();
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

    public void setImage(){
        ivMyImage.setImageResource(DebugImageMatch.getImageFromName(mainTeacher.getName()));
        tvMyImageName.setText(mainTeacher.getName());
        tvMyImageEmail.setText(mainTeacher.getEmail());
    }
}
