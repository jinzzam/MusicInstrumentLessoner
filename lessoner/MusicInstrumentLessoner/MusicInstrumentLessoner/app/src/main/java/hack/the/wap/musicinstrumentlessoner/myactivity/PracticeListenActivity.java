package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.TreeMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplatePracticeDto;
import hack.the.wap.musicinstrumentlessoner.model.myservice.UserService;
import hack.the.wap.musicinstrumentlessoner.mylayout.GuideExplainLayout;

public class PracticeListenActivity extends AppCompatActivity {
    private static UserService userService;
    private MusicTemplatePracticeDto mainTemplatePractice;
    private MusicTemplateDto mainTemplate;
    private ImageView ivPracticeListenLayLeftArrow;
    private ImageView ivPracticeListenLayTeacher;
    private ImageView ivPracticeListenLayMusician;
    private TextView tvPracticeListenLayName;
    private TextView tvPracticeListenLayCount;
    private TextView tvPracticeListenLayPercent;
    private TextView tvPracticeListenLayFileName;
    private LinearLayout llPracticeListenLayComment;

    {
        userService = UserService.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_listen);
        initView();
        Intent intent = getIntent();
        mainTemplatePractice = (MusicTemplatePracticeDto) intent.getSerializableExtra("data");
        mainTemplate = (MusicTemplateDto) intent.getSerializableExtra("main");
        viewSetValue();
        viewSetListener();
    }

    private void initView() {
        ivPracticeListenLayLeftArrow = findViewById(R.id.ivPracticeListenLayLeftArrow);
        ivPracticeListenLayTeacher = findViewById(R.id.ivPracticeListenLayTeacher);
        ivPracticeListenLayMusician = findViewById(R.id.ivPracticeListenLayMusician);
        tvPracticeListenLayName = findViewById(R.id.tvPracticeListenLayName);
        tvPracticeListenLayCount = findViewById(R.id.tvPracticeListenLayCount);
        tvPracticeListenLayPercent = findViewById(R.id.tvPracticeListenLayPercent);
        tvPracticeListenLayFileName = findViewById(R.id.tvPracticeListenLayFileName);
        llPracticeListenLayComment = findViewById(R.id.llPracticeListenLayComment);
    }

    private void viewSetListener() {
        ivPracticeListenLayLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }

    private void viewSetValue() {
        ivPracticeListenLayTeacher.setImageResource(DebugImageMatch.getImageFromName(userService.getUserName(mainTemplate.getOwner())));
        ivPracticeListenLayMusician.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getMusician()));
        tvPracticeListenLayName.setText(mainTemplate.getMusicTitle());
        tvPracticeListenLayCount.setText("" + getResources().getText(R.string.LayTemplatePracticeMusicNum) + mainTemplatePractice.getMusicTemplatePracticeId());
        tvPracticeListenLayPercent.setText("" + getResources().getText(R.string.LayTemplatePracticeSuccessPercent) + mainTemplatePractice.getCompletePercent() + getResources().getText(R.string.LayTemplatePracticeSuccessPercentEnd));
        tvPracticeListenLayFileName.setText(getResources().getText(R.string.LayTemplatePracticeFilePath) + mainTemplatePractice.getInnerFilename());
        TreeMap<String, String> tm = new TreeMap<>();
        if (true) {
            for (String key : tm.keySet()) {
                GuideExplainLayout atom = new GuideExplainLayout(this, key, mainTemplate.getGuide());
                llPracticeListenLayComment.addView(atom);
            }
        }
    }

}
