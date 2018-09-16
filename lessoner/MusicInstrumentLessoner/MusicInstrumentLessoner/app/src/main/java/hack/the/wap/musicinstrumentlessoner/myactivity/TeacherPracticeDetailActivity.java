package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.myfragment.CustomWaveformFragment;
import hack.the.wap.musicinstrumentlessoner.myservice.UserService;
import hack.the.wap.musicinstrumentlessoner.session.PresentFile;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class TeacherPracticeDetailActivity extends AppCompatActivity {
    private static Session session;
    public static String fileName;
    private Fragment customWaveformFragment;
    private ImageView ivTeacherPracticeDetailLayLeftArrow;
    private ImageView ivTeacherPracticeDetailLayTeacher;
    private ImageView ivTeacherPracticeDetailLayMusician;
    private TextView tvTeacherPracticeDetailLayName;
    private TextView tvTeacherPracticeDetailLayTeacherName;
    private TextView tvTeacherPracticeDetailLayFileName;

    private static UserService userService;
    private MusicTemplateDto mainTemplate;

    {
        session = Session.getInstance();
        userService = UserService.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_practice_detail);
        Intent intent = getIntent();
        customWaveformFragment = new CustomWaveformFragment();
        mainTemplate = (MusicTemplateDto) intent.getSerializableExtra("main");
        Log.e("SAFE", "onCreate >>> " + mainTemplate);
        initView();
        viewSetValue();
        viewSetListener();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.flTeacherPracticeFragment, customWaveformFragment).commit();
        }

    }

    private void initView() {
        ivTeacherPracticeDetailLayLeftArrow = findViewById(R.id.ivTeacherPracticeDetailLayLeftArrow);
        ivTeacherPracticeDetailLayTeacher = findViewById(R.id.ivTeacherPracticeDetailLayTeacher);
        ivTeacherPracticeDetailLayMusician = findViewById(R.id.ivTeacherPracticeDetailLayMusician);
        tvTeacherPracticeDetailLayName = findViewById(R.id.tvTeacherPracticeDetailLayName);
        tvTeacherPracticeDetailLayTeacherName = findViewById(R.id.tvTeacherPracticeDetailLayTeacherName);
        tvTeacherPracticeDetailLayFileName = findViewById(R.id.tvTeacherPracticeDetailLayFileName);
    }

    private void viewSetListener() {
        ivTeacherPracticeDetailLayLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }

    private void viewSetValue() {
        ivTeacherPracticeDetailLayTeacher.setImageResource(DebugImageMatch.getImageFromName(userService.getUserName(mainTemplate.getOwner())));
        ivTeacherPracticeDetailLayMusician.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getMusician()));
        tvTeacherPracticeDetailLayName.setText("" + mainTemplate.getMusicTitle());
        tvTeacherPracticeDetailLayTeacherName.setText(getResources().getText(R.string.template_teacher_practice_act_pre) + userService.getUserName(mainTemplate.getOwner()));
        PresentFile.fileName = "" + getResources().getText(R.string.fileDefaultDir)
                + mainTemplate.getMusicTitle() + "/"
                + getResources().getText(R.string.fileDefaultTeacher)
                + getResources().getText(R.string.fileDefaultNameMp3);
        tvTeacherPracticeDetailLayFileName.setText(getResources().getText(R.string.LayTemplatePracticeFilePath) + PresentFile.fileName);
    }
}
