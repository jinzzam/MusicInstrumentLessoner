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
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplateDto;
import hack.the.wap.musicinstrumentlessoner.myfragment.CustomWaveformFragment;
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

    private TemplateDto mainTemplate;

    {
        session = Session.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_practice_detail);
        Intent intent = getIntent();
        customWaveformFragment = new CustomWaveformFragment();
        mainTemplate = (TemplateDto) intent.getSerializableExtra("main");
        Log.e("SAFE", "onCreate >>> " + mainTemplate);
        ivTeacherPracticeDetailLayLeftArrow = findViewById(R.id.ivTeacherPracticeDetailLayLeftArrow);
        ivTeacherPracticeDetailLayTeacher = findViewById(R.id.ivTeacherPracticeDetailLayTeacher);
        ivTeacherPracticeDetailLayMusician = findViewById(R.id.ivTeacherPracticeDetailLayMusician);
        tvTeacherPracticeDetailLayName = findViewById(R.id.tvTeacherPracticeDetailLayName);
        tvTeacherPracticeDetailLayTeacherName = findViewById(R.id.tvTeacherPracticeDetailLayTeacherName);
        tvTeacherPracticeDetailLayFileName = findViewById(R.id.tvTeacherPracticeDetailLayFileName);
        viewSetValue();
        viewSetListener();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.flTeacherPracticeFragment, customWaveformFragment).commit();
        }

    }

    private void viewSetListener() {
        ivTeacherPracticeDetailLayLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }

    private void viewSetValue() {
        ivTeacherPracticeDetailLayTeacher.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getOwner().getName()));
        ivTeacherPracticeDetailLayMusician.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getMusician()));
        tvTeacherPracticeDetailLayName.setText("" + mainTemplate.getMusicTitle());
        tvTeacherPracticeDetailLayTeacherName.setText(getResources().getText(R.string.template_teacher_practice_act_pre) + mainTemplate.getOwner().getName());
        PresentFile.fileName = "" + getResources().getText(R.string.file_default_dir)
                + mainTemplate.getMusicTitle()+"/"
                + getResources().getText(R.string.file_defulat_teacher)
                + getResources().getText(R.string.file_default_name_mp3);
        tvTeacherPracticeDetailLayFileName.setText(getResources().getText(R.string.template_practice_lay_fileName_pre) + PresentFile.fileName);
    }
}
