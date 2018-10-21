package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiStudentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.myservice.UserService;
import hack.the.wap.musicinstrumentlessoner.myactivity.DebugActivity;

public class UserImageLayout extends ImageLayout {
    private UserService userService;
    private MiStudentDto mainUser;
    private TextView tvMyImageName;
    private TextView tvMyImageEmail;

    public UserImageLayout(Context context, MiStudentDto dto) {
        super(context);
        userService = UserService.getInstance();
        mainUser = new MiStudentDto();
        mainUser = dto;
        tvMyImageName = findViewById(R.id.tvMyImageName);
        tvMyImageEmail = findViewById(R.id.tvMyImageEmail);
        setImage(mainUser);
        setListener();
    }

    public UserImageLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UserImageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UserImageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setImage(MiStudentDto mainUser) {
        ivMyImage.setImageResource(DebugImageMatch.getImageFromName(userService.getUserName(mainUser.getStudentEmail())));
        tvMyImageName.setText(userService.getUserName(mainUser.getStudentEmail()));
        tvMyImageEmail.setText(mainUser.getStudentEmail());
    }

    public void setListener() {
        setOnClickListener((v) -> {
            Intent intent = new Intent(getContext(), DebugActivity.class);
            intent.putExtra("data", userService.getUserName(mainUser.getStudentEmail()));
            getContext().startActivity(intent);
        });
    }
}
