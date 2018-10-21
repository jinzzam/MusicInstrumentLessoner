package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateGuideDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplatePracticeDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateWrongDto;
import hack.the.wap.musicinstrumentlessoner.model.myservice.UserService;
import hack.the.wap.musicinstrumentlessoner.mylayout.GuideExplainLayout;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class PracticeListenActivity extends AppCompatActivity {
    private Session session;
    private static UserService userService;
    private MusicTemplatePracticeDto mainTemplatePractice;
    private MusicTemplateDto mainTemplate;
    private ArrayList<MusicTemplateWrongDto> wrongs;
    private ImageView ivPracticeListenLayLeftArrow;
    private ImageView ivPracticeListenLayTeacher;
    private ImageView ivPracticeListenLayMusician;
    private TextView tvPracticeListenLayName;
    private TextView tvPracticeListenLayCount;
    private TextView tvPracticeListenLayPercent;
    private TextView tvPracticeListenLayFileName;
    private LinearLayout llPracticeListenLayComment;

    {
        session = Session.getInstance();
        userService = UserService.getInstance();
        wrongs = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_listen);
        initView();
        Intent intent = getIntent();
        mainTemplatePractice = (MusicTemplatePracticeDto) intent.getSerializableExtra("data");
        mainTemplate = (MusicTemplateDto) intent.getSerializableExtra("main");
        wrongs = session.getTemplateWrongs();
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
        for (int i = 0; i < wrongs.size(); i++) {
            if ((mainTemplate.getMusicTemplateId() == wrongs.get(i).getMusicTemplateId())) {
                if ((mainTemplatePractice.getMusicTemplatePracticeId() == wrongs.get(i).getMusicTemplatePracticeId())) {
                    GuideExplainLayout atom = new GuideExplainLayout(this, wrongs.get(i));
                    llPracticeListenLayComment.addView(atom);
                }
            }

        }
    }
//        TreeMap<String, String> tm = new TreeMap<>();
//        if (true) {
//            for (String key : tm.keySet()) {
//                GuideExplainLayout atom = new GuideExplainLayout(this, key, mainTemplate.getGuide());
//                llPracticeListenLayComment.addView(atom);
//            }
//        }
//    }

}
