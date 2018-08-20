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
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplatePracticeDto;
import hack.the.wap.musicinstrumentlessoner.myfragment.CustomWaveformFragment;
import hack.the.wap.musicinstrumentlessoner.session.PresentFile;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class PracticeDetailActivity extends AppCompatActivity {
    private static Session session;
    private Fragment customWaveformFragment;
    private ImageView ivPracticeDetailLayLeftArrow;
    private TextView tvPracticeDetailLayName;
    private TextView tvPracticeDetailLayCount;
    private TextView tvPracticeDetailLayPercent;
    private TextView tvPracticeDetailLayFileName;
    private ImageView ivPracticeDetailLayTeacher;
    private ImageView ivPracticeDetailLayMusician;

    private TemplatePracticeDto mainTemplatePractice;
    private TemplateDto mainTemplate;

    {
        session = Session.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_practice_detail);
        Intent intent = getIntent();
        customWaveformFragment = new CustomWaveformFragment();
        mainTemplatePractice = (TemplatePracticeDto) intent.getSerializableExtra("data");
        mainTemplate = (TemplateDto) intent.getSerializableExtra("main");
        Log.e("SAFE", "onCreate >>> " + mainTemplate);
        Log.e("SAFE", "onCreate >>> " + mainTemplatePractice);
        ivPracticeDetailLayLeftArrow = findViewById(R.id.ivPracticeDetailLayLeftArrow);
        tvPracticeDetailLayName = findViewById(R.id.tvPracticeDetailLayName);
        tvPracticeDetailLayCount = findViewById(R.id.tvPracticeDetailLayCount);
        tvPracticeDetailLayPercent = findViewById(R.id.tvPracticeDetailLayPercent);
        tvPracticeDetailLayFileName = findViewById(R.id.tvPracticeDetailLayFileName);
        ivPracticeDetailLayTeacher = findViewById(R.id.ivPracticeDetailLayTeacher);
        ivPracticeDetailLayMusician = findViewById(R.id.ivPracticeDetailLayMusician);

        viewSetValue();
        viewSetListener();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.flPracticeFragment, customWaveformFragment).commit();
        }

    }

    private void viewSetListener() {
        ivPracticeDetailLayLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }

    private void viewSetValue() {
        ivPracticeDetailLayTeacher.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getOwner().getName()));
        ivPracticeDetailLayMusician.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getMusician()));
        tvPracticeDetailLayName.setText("" + mainTemplate.getMusicTitle());
        tvPracticeDetailLayCount.setText("" + getResources().getText(R.string.template_practice_lay_count) + mainTemplatePractice.getPracticeId());
        tvPracticeDetailLayPercent.setText("" + getResources().getText(R.string.template_practice_lay_percent) + mainTemplatePractice.getPercent() + getResources().getText(R.string.template_practice_lay_percent_end));
        tvPracticeDetailLayFileName.setText("" + getResources().getText(R.string.template_practice_lay_fileName_pre) + mainTemplatePractice.getFileName());
        PresentFile.fileName = mainTemplatePractice.getFileName();
    }
}
