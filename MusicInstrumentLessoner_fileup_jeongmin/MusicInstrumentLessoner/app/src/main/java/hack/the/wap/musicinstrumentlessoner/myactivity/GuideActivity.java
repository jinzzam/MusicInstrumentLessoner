package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.TreeMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplateDto;
import hack.the.wap.musicinstrumentlessoner.mylayout.GuideExplainLayout;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class GuideActivity extends AppCompatActivity {
    private static GuideActivity instance;
    private static Session session;
    private TemplateDto mainTemplate;
    private ImageView ivGuideActLeftArrow;
    private ImageView ivGuideActTeacher;
    private ImageView ivGuideActMusician;
    private TextView tvGuideActTeacherName;
    private TextView tvGuideActMusicName;
    private TextView tvGuideActMusicMainExplain;
    private LinearLayout llActUserGroupDetailUser;

    {
        session = Session.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ivGuideActLeftArrow = findViewById(R.id.ivGuideActLeftArrow);
        ivGuideActTeacher = findViewById(R.id.ivGuideActTeacher);
        ivGuideActMusician = findViewById(R.id.ivGuideActMusician);
        tvGuideActTeacherName = findViewById(R.id.tvGuideActTeacherName);
        tvGuideActMusicName = findViewById(R.id.tvGuideActMusicName);
        tvGuideActMusicMainExplain = findViewById(R.id.tvGuideActMusicMainExplain);
        llActUserGroupDetailUser = findViewById(R.id.llActUserGroupDetailUser);

        instance = this;

        Intent intent = getIntent();
        mainTemplate = (TemplateDto) intent.getSerializableExtra("main");
        Log.e("SAFE", "onCreate >>> " + mainTemplate);

        viewSetListener();
        viewSetValue();
    }

    private void viewSetListener() {
        ivGuideActLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }

    private void viewSetValue() {
        ivGuideActTeacher.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getOwner().getName()));
        ivGuideActMusician.setImageResource(DebugImageMatch.getImageFromName(mainTemplate.getMusician()));
        tvGuideActTeacherName.setText(mainTemplate.getOwner().getName());
        tvGuideActMusicName.setText(mainTemplate.getMusicTitle());
        if (mainTemplate.getGuide() != null) {
            if (mainTemplate.getGuide().getMain() != null) {
                tvGuideActMusicMainExplain.setText(mainTemplate.getGuide().getMain());
            } else {
                tvGuideActMusicMainExplain.setText(getResources().getText(R.string.tvActGuideNoData));
            }
            if (!mainTemplate.getGuide().getData().isEmpty()) {
                TreeMap<String, String> tm = new TreeMap<>(mainTemplate.getGuide().getData());
                for (String key : tm.keySet()) {
                    GuideExplainLayout atom = new GuideExplainLayout(this, key, mainTemplate.getGuide().getData().get(key));
                    llActUserGroupDetailUser.addView(atom);
                }
            } else {
                TextView tvTmp = new TextView(this);
                tvTmp.setText(getResources().getString(R.string.tvActGuideNoData));
                llActUserGroupDetailUser.addView(tvTmp);
            }
        } else {
            tvGuideActMusicMainExplain.setText(getResources().getText(R.string.tvActGuideNoData));
            TextView tvTmp = new TextView(this);
            tvTmp.setText(getResources().getString(R.string.tvActGuideNoData));
            llActUserGroupDetailUser.addView(tvTmp);
        }
    }

    public GuideActivity getInstance() {
        return instance;
    }
}
