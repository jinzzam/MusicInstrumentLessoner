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
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplatePracticeDto;
import hack.the.wap.musicinstrumentlessoner.mylayout.GuideExplainLayout;

public class PracticeListenActivity extends AppCompatActivity {
    private TemplatePracticeDto mainTemplatePractice;
    private TemplateDto mainTemplate;
    private ImageView ivPracticeListenLayLeftArrow;
    private ImageView ivPracticeListenLayTeacher;
    private ImageView ivPracticeListenLayMusician;
    private TextView tvPracticeListenLayName;
    private TextView tvPracticeListenLayCount;
    private TextView tvPracticeListenLayPercent;
    private TextView tvPracticeListenLayFileName;
    private LinearLayout llPracticeListenLayComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_listen);
        ivPracticeListenLayLeftArrow = findViewById(R.id.ivPracticeListenLayLeftArrow);
        ivPracticeListenLayTeacher = findViewById(R.id.ivPracticeListenLayTeacher);
        ivPracticeListenLayMusician = findViewById(R.id.ivPracticeListenLayMusician);
        tvPracticeListenLayName = findViewById(R.id.tvPracticeListenLayName);
        tvPracticeListenLayCount = findViewById(R.id.tvPracticeListenLayCount);
        tvPracticeListenLayPercent = findViewById(R.id.tvPracticeListenLayPercent);
        tvPracticeListenLayFileName = findViewById(R.id.tvPracticeListenLayFileName);
        llPracticeListenLayComment = findViewById(R.id.llPracticeListenLayComment);

        Intent intent = getIntent();
        mainTemplatePractice = (TemplatePracticeDto) intent.getSerializableExtra("data");
        mainTemplate = (TemplateDto) intent.getSerializableExtra("main");

        viewSetValue();
        viewSetListener();
    }

    private void viewSetListener() {
        ivPracticeListenLayLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }
    private void viewSetValue() {
        ivPracticeListenLayTeacher.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getOwner().getName()));
        ivPracticeListenLayMusician.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getMusician()));
        tvPracticeListenLayName.setText("" + mainTemplate.getMusicTitle());
        tvPracticeListenLayCount.setText("" + getResources().getText(R.string.template_practice_lay_count) + mainTemplatePractice.getPracticeId());
        tvPracticeListenLayPercent.setText("" + getResources().getText(R.string.template_practice_lay_percent) + mainTemplatePractice.getPercent() + getResources().getText(R.string.template_practice_lay_percent_end));
        tvPracticeListenLayFileName.setText("" + getResources().getText(R.string.template_practice_lay_fileName_pre) + mainTemplatePractice.getFileName());
        TreeMap<String, String> tm = new TreeMap<>(mainTemplatePractice.getData());
        if(!mainTemplatePractice.getData().isEmpty()){
            for(String key:tm.keySet()){
                GuideExplainLayout atom = new GuideExplainLayout(this, key, mainTemplatePractice.getData().get(key));
                llPracticeListenLayComment.addView(atom);
            }
        }
    }

}
